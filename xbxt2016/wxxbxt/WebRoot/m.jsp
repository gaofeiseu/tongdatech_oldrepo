<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- ���ҳ����ʾ������Э�������� -->
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui" />
		<!-- �����ƶ���������������� -->
		<meta name="format-detection" content="telephone=no"/>
		<meta content=��email=no�� name=��format-detection�� />
		<!-- 
		<meta content="yes" name="apple-mobile-web-app-capable" />
		 -->
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<!-- �趨�Լ���WEBAPPͼ����ʽ -->
		<link rel="apple-touch-icon" href="/mobile_wap/img/G(64).png" />
		<!-- ����CDN�ַ����ƣ��ӿ���ҳ�����ٶ� -->
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
			    alert("�������֧��html5����ȡ����λ����Ϣ");
			}
		}
		
		
		function getLatLngForCheckin2(){
			console.log('HTML5��λʧ�ܣ���ʼ���ðٶȶ�λ����');
			document.getElementById("check_in_msg").innerHTML = 'HTML5��λʧ�ܣ���ʼ���ðٶȶ�λ����';
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){
				if(this.getStatus() == BMAP_STATUS_SUCCESS){
					var ss_s = Convert_BD09_To_GCJ02(r.point.lat,r.point.lng);
					if(ss_s){
						var ss_a = [];
						ss_a = ss_s.split("#");
						if(ss_a.length==2){
							document.getElementById("check_in_msg").innerHTML = '����λ�ã�'+ss_a[0]+','+ss_a[1];
							$("#check_in_lat").val(r.point.lat);
							$("#check_in_lng").val(r.point.lng);
							doSomethingLater();
						}else{
							document.getElementById("check_in_msg").innerHTML = '���󣺾�γ��ת����������'+ss_a.length;
						}
					}else{
						document.getElementById("check_in_msg").innerHTML = '���󣺾�γ��ת����������';
					}
				}
				else if(this.getStatus() == BMAP_STATUS_UNKNOWN_LOCATION){
					document.getElementById("check_in_msg").innerHTML = '����λ�ý��δ֪';
				}
				else if(this.getStatus() == BMAP_STATUS_UNKNOWN_ROUTE){
					document.getElementById("check_in_msg").innerHTML = '���󣺵������δ֪';
				} 
				else if(this.getStatus() == BMAP_STATUS_INVALID_KEY){
					document.getElementById("check_in_msg").innerHTML = '���󣺷Ƿ���Կ';
				}
				else if(this.getStatus() == BMAP_STATUS_INVALID_REQUEST){
					document.getElementById("check_in_msg").innerHTML = '���󣺷Ƿ�����';
				}
				else if(this.getStatus() == BMAP_STATUS_PERMISSION_DENIED){
					document.getElementById("check_in_msg").innerHTML = '����û��Ȩ��';
				}
				else if(this.getStatus() == BMAP_STATUS_SERVICE_UNAVAILABLE){
					document.getElementById("check_in_msg").innerHTML = '���󣺷��񲻿���';
				}
				else if(this.getStatus() == BMAP_STATUS_TIMEOUT){
					document.getElementById("check_in_msg").innerHTML = '���󣺳�ʱ';
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
				alert('��������Ϣ~\(�R���Q)/~������');
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
					�߷�ǩ��
				</h1>
			</div>

			<div data-role="content">
				<!-- 
				<p>
					������Ӳ鿴����Ч����
				</p>
				
			 	-->
			 	<a id="jump_href" href="#pagetwo" data-transition="slide"></a>
				<form method="post" action="/">
					<label for="fname" class="ui-hidden-accessible">
						��¼��
					</label>
					<label for="fname" class="ui-hidden-accessible">
						����
					</label>
					<input autocapitalize="off" type="text" name="fname" id="fname" placeholder="���������ĵ�¼��...">
					<input type="password" name="fpassword" id="fpassword" placeholder="��������������...">
  					<a href="#" data-role="button" onclick="login_func();">��¼</a>
					<a href="#" data-role="button" onclick="alert('�˳�');">�˳�</a>
					<font color="red"><p id="show_text">�������������û��������룡</p></font>
				</form>
				<a href="/checkUpdate/CheckIn.apk" target='_blank'>��׿������</a>
				<a href="/checkUpdate/ChromeStandalone_46.0.2490.80_Setup.1445829883.exe" target='_blank'>chrome���������</a>
			</div>

			<div data-role="footer" data-position="fixed">
				<h1>
					<a href="http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action" target="_blank">���Ų�������: ��ICP��10006317��</a>  ��Ȩ���У�����ʡ���������������޹�˾<!-- Copyright �й����� 2014-2014 -->
				</h1>
			</div>
		</div>

		<div data-role="page" id="pagetwo">
			<div data-role="content">
				<form id="check_in_frm" method="post" action="/mobile_check_in.action" data-ajax="false" enctype="multipart/form-data" target="hidden_frame">
					<!-- enctype="multipart/form-data" -->
					<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
					<label id="second_page_my_name">
						�û�����:
					</label>
					<select name="visit_type" id="visit_type">
						<option value="1" selected="selected">�����߷�</option>
						<option value="2">ת������</option>
						<option value="3">�������߷�</option>
						<option value="4">��������</option>
					</select>
					<select name="custom_type" id="custom_type">
						<option value="1" selected="selected">��ó���㻧</option>
						<option value="2">��ģ��ҵ��</option>
						<option value="3">����ֳ�ͻ�</option>
						<option value="4">����ͻ�</option>
						<option value="5">�����ͻ�</option>
						<option value="6">�������Ⱥ</option>
						<option value="7">��������Ⱥ</option>
						<option value="8">������ͻ�</option>
						<option value="9">�ͻ�ת��ͻ�</option>
						<option value="10">���пͻ�</option>
						<option value="11">�����ͻ�</option>
					</select>
					<select name="visit_class" id="visit_class">
						<option value="1" selected="selected">�뻧�߷�</option>
						<option value="2">�����ܼ���X��</option>
						<option value="3">����</option>
						<option value="4">�㳡·��</option>
						<option value="5">�ؽֵ����߷�</option>
						<option value="6">�ͻ���л��</option>
						<option value="7">����ɳ��</option>
						<option value="8">��˵��</option>
					</select>
					<input type="hidden" name="my_login_id" id="my_login_id" >
					<input type="hidden" name="my_login_name" id="my_login_name" >
					<input type="hidden" name="check_in_lat" id="check_in_lat" >
					<input type="hidden" name="check_in_lng" id="check_in_lng" >
					<input type="hidden" name="mobile_img_url" id="mobile_img_url" >
					<input type="hidden" name="if_wap" id="if_wap" >
					<input autocapitalize="off" type="text" name="visit_custom_name" id="visit_custom_name" placeholder="�߷ÿͻ�������ѡ�...">
					<input autocapitalize="off" type="text" name="visit_content" id="visit_content" placeholder="Ǣ̸��Ŀ����ѡ�..." >
					<input type="file" name="upload" id="upload" onchange="change(event);" >
					<input type="hidden" name="file_fake_name" id="file_fake_name" >
					<textarea autocapitalize="off" name="visit_note" id="visit_note" placeholder="��ע��Ϣ����ѡ�..."></textarea>
  					<a href="#" id="check_in_btn" data-role="button" onclick="check_in();" data-transition="slide" >ǩ��</a>
  					<a href="#pagethree" data-role="button" data-transition="slide" >��ʷ</a>
  					<a href="#pageone" data-role="button" data-transition="slide" >ע��</a>
  					<font color="red"><p id="check_in_msg"></p></font>
				</form>
			</div>
		</div>
		<div data-role="page" id="pagethree">
			<div data-role="content">
				<form id="get_history_frm" method="post" action="/" >
					<label for="history_select_date_st">��ʼ���ڣ�</label>
					<input type="date" name="history_select_date_st" id="history_select_date_st" value="" >
					<label for="history_select_date_ed">�������ڣ�</label>
					<input type="date" name="history_select_date_ed" id="history_select_date_ed" value="" >
					<a href="#" id="get_history_btn" data-role="button" onclick="getHistory();" >��ѯ</a>
					<font color="red"><p id="check_in_msg3"></p></font>
    				<div data-role="collapsible-set" id="div_history_list">
    				</div>
    				<a href="#" id="get_history_more_btn" data-role="button" onclick="getHistoryMore();">����</a>
				</form>
			</div>
		</div>

	</body>
</html>

