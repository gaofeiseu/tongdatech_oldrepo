<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- 解决页面显示比例不协调的问题 -->
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui" />
		<!-- 屏蔽移动端浏览器的自身导航 -->
		<meta name="format-detection" content="telephone=no"/>
		<meta content=”email=no” name=”format-detection” />
		<!-- 
		<meta content="yes" name="apple-mobile-web-app-capable" />
		 -->
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<!-- 设定自己的WEBAPP图标样式 -->
		<link rel="apple-touch-icon" href="/mobile_wap/img/G(64).png" />
		<!-- 采用CDN分发机制，加快网页加载速度 -->
		<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.2/jquery.mobile.min.css"><!-- http://apps.bdimg.com/libs/jquerymobile/1.4.2/jquery.mobile.min.css -->
		<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script><!-- http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js -->
		<script	src="http://apps.bdimg.com/libs/jquerymobile/1.4.2/jquery.mobile.min.js"></script><!-- http://apps.bdimg.com/libs/jquerymobile/1.4.2/jquery.mobile.min.js -->
		<script src="/mobile_wap/js/mobile_login.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C5dae37ba0a97216555b8bf2b76bacfd"></script>
		<script type="text/javascript">
		
		function getLatLngForCheckin(){
			if (window.navigator.geolocation) {
			    var options = {
			        enableHighAccuracy: true
			    };
			    window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
			} else {
			    alert("浏览器不支持html5来获取地理位置信息");
			}
		}
		
		
		function getLatLngForCheckin2(){
			console.log('HTML5定位失败！开始调用百度定位服务！');
			document.getElementById("check_in_msg").innerHTML = 'HTML5定位失败！开始调用百度定位服务！';
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){
				if(this.getStatus() == BMAP_STATUS_SUCCESS){
					var ss_s = Convert_BD09_To_GCJ02(r.point.lat,r.point.lng);
					if(ss_s){
						var ss_a = [];
						ss_a = ss_s.split("#");
						if(ss_a.length==2){
							document.getElementById("check_in_msg").innerHTML = '您的位置：'+ss_a[0]+','+ss_a[1];
							$("#check_in_lat").val(r.point.lat);
							$("#check_in_lng").val(r.point.lng);
							doSomethingLater();
						}else{
							document.getElementById("check_in_msg").innerHTML = '错误：经纬度转换发生错误！'+ss_a.length;
						}
					}else{
						document.getElementById("check_in_msg").innerHTML = '错误：经纬度转换发生错误！';
					}
				}
				else if(this.getStatus() == BMAP_STATUS_UNKNOWN_LOCATION){
					document.getElementById("check_in_msg").innerHTML = '错误：位置结果未知';
				}
				else if(this.getStatus() == BMAP_STATUS_UNKNOWN_ROUTE){
					document.getElementById("check_in_msg").innerHTML = '错误：导航结果未知';
				} 
				else if(this.getStatus() == BMAP_STATUS_INVALID_KEY){
					document.getElementById("check_in_msg").innerHTML = '错误：非法密钥';
				}
				else if(this.getStatus() == BMAP_STATUS_INVALID_REQUEST){
					document.getElementById("check_in_msg").innerHTML = '错误：非法请求';
				}
				else if(this.getStatus() == BMAP_STATUS_PERMISSION_DENIED){
					document.getElementById("check_in_msg").innerHTML = '错误：没有权限';
				}
				else if(this.getStatus() == BMAP_STATUS_SERVICE_UNAVAILABLE){
					document.getElementById("check_in_msg").innerHTML = '错误：服务不可用';
				}
				else if(this.getStatus() == BMAP_STATUS_TIMEOUT){
					document.getElementById("check_in_msg").innerHTML = '错误：超时';
				}
			},{enableHighAccuracy: true})
		}
		
		
		//var mobile_img_url = "${application.ParamJson.MOBILE_IMG_URL}";
		var if_first_do_init_three = true;
		
		$(document).on("pageinit","#pagethree",function(){
			/*
			$("li").on("taphold",function(){//taphold
				alert('a:'+this.find('a').innerHTML);
			});
			$("li").on("swipe",function(){
				alert('啊，弹信息~\(≧▽≦)/~啦啦啦');
			});
			*/
			if(if_first_do_init_three){
				$("#get_history_more_btn").hide();
				if_first_do_init_three = false;
			}
			
		});
		
		</script>
	</head>
	<body>

		<div data-role="page" id="pageone" data-fullscreen="true">
			<div data-role="header" data-position="fixed">
				<h1>
					走访签到
				</h1>
			</div>

			<div data-role="content">
				<!-- 
				<p>
					点击链接查看滑动效果。
				</p>
				
			 	-->
			 	<a id="jump_href" href="#pagetwo" data-transition="slide"></a>
				<form method="post" action="/">
					<label for="fname" class="ui-hidden-accessible">
						登录名
					</label>
					<label for="fname" class="ui-hidden-accessible">
						密码
					</label>
					<input autocapitalize="off" type="text" name="fname" id="fname" placeholder="请输入您的登录名...">
					<input type="password" name="fpassword" id="fpassword" placeholder="请输入您的密码...">
  					<a href="#" data-role="button" onclick="login_func();">登录</a>
					<a href="#" data-role="button" onclick="alert('退出');">退出</a>
					<font color="red"><p id="show_text">请输入完整的用户名和密码！</p></font>
				</form>
				<a href="/checkUpdate/CheckIn.apk" target='_blank'>安卓版下载</a>
				<a href="/checkUpdate/ChromeStandalone_46.0.2490.80_Setup.1445829883.exe" target='_blank'>chrome浏览器下载</a>
			</div>

			<div data-role="footer" data-position="fixed">
				<h1>
					<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">工信部备案号: 苏ICP备10006317号</a>  版权所有：江苏省邮政电子商务有限公司<!-- Copyright 中国邮政 2014-2014 -->
				</h1>
			</div>
		</div>

		<div data-role="page" id="pagetwo">
			<div data-role="content">
				<form id="check_in_frm" method="post" action="/mobile_check_in.action" data-ajax="false" enctype="multipart/form-data" target="hidden_frame">
					<!-- enctype="multipart/form-data" -->
					<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
					<label id="second_page_my_name">
						用户姓名:
					</label>
					<select name="visit_type" id="visit_type">
						<option value="1" selected="selected">跨赛走访</option>
						<option value="2">转型外拓</option>
						<option value="3">邮务类走访</option>
						<option value="4">报刊揽收</option>
					</select>
					<select name="custom_type" id="custom_type">
						<option value="1" selected="selected">商贸结算户</option>
						<option value="2">规模企业户</option>
						<option value="3">种养殖客户</option>
						<option value="4">老年客户</option>
						<option value="5">社区客户</option>
						<option value="6">外出务工人群</option>
						<option value="7">外来务工人群</option>
						<option value="8">邮务类客户</option>
						<option value="9">客户转介客户</option>
						<option value="10">他行客户</option>
						<option value="11">其他客户</option>
					</select>
					<select name="visit_class" id="visit_class">
						<option value="1" selected="selected">入户走访</option>
						<option value="2">人流密集区X进</option>
						<option value="3">联谊活动</option>
						<option value="4">广场路演</option>
						<option value="5">沿街店铺走访</option>
						<option value="6">客户答谢会</option>
						<option value="7">网点沙龙</option>
						<option value="8">产说会</option>
					</select>
					<input type="hidden" name="my_login_id" id="my_login_id" >
					<input type="hidden" name="my_login_name" id="my_login_name" >
					<input type="hidden" name="check_in_lat" id="check_in_lat" >
					<input type="hidden" name="check_in_lng" id="check_in_lng" >
					<input type="hidden" name="mobile_img_url" id="mobile_img_url" >
					<input type="hidden" name="if_wap" id="if_wap" >
					<input autocapitalize="off" type="text" name="visit_custom_name" id="visit_custom_name" placeholder="走访客户名（可选项）...">
					<input autocapitalize="off" type="text" name="visit_content" id="visit_content" placeholder="洽谈项目（可选项）..." >
					<input type="file" name="upload" id="upload" onchange="change(event);" >
					<input type="hidden" name="file_fake_name" id="file_fake_name" >
					<textarea autocapitalize="off" name="visit_note" id="visit_note" placeholder="备注信息（可选项）..."></textarea>
  					<a href="#" id="check_in_btn" data-role="button" onclick="check_in();" data-transition="slide" >签到</a>
  					<a href="#pagethree" data-role="button" data-transition="slide" >历史</a>
  					<a href="#pageone" data-role="button" data-transition="slide" >注销</a>
  					<font color="red"><p id="check_in_msg"></p></font>
				</form>
			</div>
		</div>
		<div data-role="page" id="pagethree">
			<div data-role="content">
				<form id="get_history_frm" method="post" action="/" >
					<label for="history_select_date_st">开始日期：</label>
					<input type="date" name="history_select_date_st" id="history_select_date_st" value="" >
					<label for="history_select_date_ed">结束日期：</label>
					<input type="date" name="history_select_date_ed" id="history_select_date_ed" value="" >
					<a href="#" id="get_history_btn" data-role="button" onclick="getHistory();" >查询</a>
					<font color="red"><p id="check_in_msg3"></p></font>
    				<div data-role="collapsible-set" id="div_history_list">
    				</div>
    				<a href="#" id="get_history_more_btn" data-role="button" onclick="getHistoryMore();">更多</a>
				</form>
			</div>
		</div>

	</body>
</html>

