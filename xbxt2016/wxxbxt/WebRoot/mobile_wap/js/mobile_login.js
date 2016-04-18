

function change(e){
    var src=e.target || window.event.srcElement; //获取事件源，兼容chrome/IE
    $("#file_fake_name").val(src.value);
}
var if_do_as_old = true;
function handleSuccess(position){
    // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
    var lng = position.coords.longitude;
    var lat = position.coords.latitude;
    // 调用百度地图api显示
    $("#check_in_lat").val(lat);
	$("#check_in_lng").val(lng);
	doSomethingLater();
}

function showError(str){
	console.log(str);
}

function handleError(error){
	//alert('获取地理信息的时候发生错误：'+error+error.toString());
	switch(error.code) {
    case error.TIMEOUT:
        showError('HTML5:获取经纬度信息超时，请确认GPS开启后重新尝试！');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.POSITION_UNAVAILABLE:
        showError('HTML5:您当前位置无法定位，请稍后再尝试！');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.PERMISSION_DENIED:
        showError('HTML5:请允许访问您的GPS地理位置信息，否则将无法获取经纬度信息！');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.UNKNOWN_ERROR:
        showError('HTML5:获取地理信息时发生未知错误，请确认GPS开启后重新进行尝试！');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
	}
}
var x_pi = 3.14159265358979323846264338327950288 * 3000.0 / 180.0;
function Convert_BD09_To_GCJ02(s_lat,s_lng){
	try{
		var x = parseFloat(s_lng) - 0.0065;
		var y = parseFloat(s_lat) - 0.006;
		var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		var lng = z * Math.cos(theta);
		var lat = z * Math.sin(theta);
		return lng+"#"+lat;
	}
	catch(Exception){
		console.log(Exception);
	}
}

/**
 * 登录
 */
function login_func(){
	var login_name = $("#fname").val();
	var pass_word = $("#fpassword").val();
	if(login_name==""||pass_word==""){
		alert("请输入用户名和密码再进行登陆！");
		return;
	}
	var url="/mobile_login.action";
	var json = {};
	json["login_name"] = login_name;
	json["pass_word"] = pass_word;
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				if(msg.if_success){
					$("#second_page_my_name").text("用户登录名："+msg.returnMap.msg.user_name+" 昵称："+msg.returnMap.msg.user_name);
					$("#my_login_id").val(msg.returnMap.msg.user_id);
					$("#my_login_name").val(msg.returnMap.msg.user_name);
					$("#jump_href").click();
				}else{
					$("#show_text").html(msg.returnMap.msg);
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}
/**
 * 签到
 */
function check_in(){
	if($("#my_login_name").val()&&$("#my_login_id").val()){
		getLatLngForCheckin();
		document.getElementById("check_in_msg").innerHTML = "正在获取地理信息...";
	}else{
		document.getElementById("check_in_msg").innerHTML = "身份信息有误，请重新登录后重试...";
	}
}

function doSomethingLater(){
	if($("#check_in_lat").val()==''||$("#check_in_lat").val()==null||$("#check_in_lat").val()==undefined||
			$("#check_in_lng").val()==''||$("#check_in_lng").val()==null||$("#check_in_lng").val()==undefined){
		alert("经纬度信息不允许为空！");
		return;
	}
	//$("#file_fake_name").val($("#upload").val());
	var check_in_lat = $("#check_in_lat").val();
	var check_in_lng = $("#check_in_lng").val();
//	var mobile_img_url_obj = eval('(' + mobile_img_url + ')');
//	$("#mobile_img_url").val(decodeURIComponent(mobile_img_url_obj[0].value));
	$("#mobile_img_url").val('/home/weblogic/file_folder/mobile_img_folder/');
	if(!if_do_as_old){
		$("#if_wap").val('1');//启用wap百度定位
	}else{
		$("#if_wap").val('2');//启用html5定位
	}
	
	document.getElementById("check_in_msg").innerHTML = "正在上传数据...";
	//document.getElementById("check_in_msg").innerHTML = "经纬度信息："+check_in_lng+","+check_in_lat;
	$("#check_in_frm").submit();
	$("#check_in_btn").button("disable");
}

function callback(msg)
{
	document.getElementById("upload").outerHTML = document.getElementById("upload").outerHTML;
    document.getElementById("check_in_msg").innerHTML = msg;
    $("#check_in_btn").button("enable");
}
var now_num = 0;
var increace_num = 50;
var total_num_for_history = 0;
function getHistory(){
	if($("#my_login_name").val()&&$("#my_login_id").val()){
		$("#get_history_btn").hide();
		var history_time_st = $("#history_select_date_st").val();
		var history_time_ed = $("#history_select_date_ed").val();
		var my_login_name = $("#my_login_name").val();
		var my_login_id = $("#my_login_id").val();
		console.log('开始时间：'+history_time_st+'\n'+'结束时间：'+history_time_ed);
		var url="/mobile_getHistoryTotalNum.action";
		var json = {};
		json["history_time_st"] = history_time_st;
		json["history_time_ed"] = history_time_ed;
		json["my_login_name"] = my_login_name;
		json["my_login_id"] = my_login_id;
		$.ajax({
			type : "POST",
			cache : false,
			async:true,
			url : url,
			dataType : "json",
			data : json,
			success : function(msg) {
				try {
					if(msg.if_success){
						$("#div_history_list").empty();
						$("#check_in_msg3").html(msg.msg);
						now_num = 0;
						total_num_for_history = 0;
						total_num_for_history = Number(msg.returnMap.msg);
						
						
						var url="/mobile_getHistory.action";
						var json = {};
						json["history_time_st"] = history_time_st;
						json["history_time_ed"] = history_time_ed;
						json["my_login_name"] = my_login_name;
						json["my_login_id"] = my_login_id;
						
						json["history_now_num"] = now_num;
						json["history_increace_num"] = increace_num;
						$.ajax({
							type : "POST",
							cache : false,
							async:true,
							url : url,
							dataType : "json",
							data : json,
							success : function(msg) {
								try {
									if(msg.if_success){
										
										$.each(msg.returnMap.msg,function(n,value) {   
											var div_o = "<div data-role=\"collapsible\">" +
													"<h3>"+getMyTime(msg.returnMap.msg[n].check_in_time)+"</h3>" +
													"<p>"+
													"走访内容："+msg.returnMap.msg[n].visit_type_str+"<br>"+
													"走访形式："+msg.returnMap.msg[n].visit_class_str+"<br>"+
													"走访客户："+msg.returnMap.msg[n].visit_custom_name+"<br>"+
													"签到纬度："+msg.returnMap.msg[n].check_in_lat+"<br>"+
													"签到经度："+msg.returnMap.msg[n].check_in_lng+"<br>"+
													"洽谈项目："+msg.returnMap.msg[n].visit_content+"<br>"+
													"备注："+msg.returnMap.msg[n].visit_note
													+"</p></div>";
											$("#div_history_list").append(div_o);
									  	});
										$("#div_history_list").trigger("create");
										
										now_num = now_num + increace_num;
										if(total_num_for_history>now_num){
											$("#get_history_more_btn").show();
										}
									}else{
										$("#check_in_msg3").html(msg.msg);
									}
								}
								catch (exception) {
									alert(exception);
								}
								$("#get_history_btn").show();
							},
							error : function(msg) {
								alert('error!!');
							}
						});
					}else{
						$("#check_in_msg3").html(msg.msg);
						$("#div_history_list").empty();
						$("#div_history_list").trigger("create");
					}
				}
				catch (exception) {
					alert(exception);
				}
				$("#get_history_btn").show();
			},
			error : function(msg) {
				alert('error!!');
			}
		});
	}else{
		document.getElementById("check_in_msg3").innerHTML = "身份信息有误，请重新登录后重试...";
	}
}

function getHistoryMore(){
	if($("#my_login_name").val()&&$("#my_login_id").val()){
		$("#get_history_btn").hide();
		$("#get_history_more_btn").hide();
		var history_time_st = $("#history_select_date_st").val();
		var history_time_ed = $("#history_select_date_ed").val();
		var my_login_name = $("#my_login_name").val();
		var my_login_id = $("#my_login_id").val();
		console.log('开始时间：'+history_time_st+'\n'+'结束时间：'+history_time_ed);
		
		var url="/mobile_getHistory.action";
		var json = {};
		json["history_time_st"] = history_time_st;
		json["history_time_ed"] = history_time_ed;
		json["my_login_name"] = my_login_name;
		json["my_login_id"] = my_login_id;
		
		json["history_now_num"] = now_num;
		json["history_increace_num"] = increace_num;
		$.ajax({
			type : "POST",
			cache : false,
			async:true,
			url : url,
			dataType : "json",
			data : json,
			success : function(msg) {
				try {
					if(msg.if_success){

						$.each(msg.returnMap.msg,function(n,value) {   
							var div_o = "<div data-role=\"collapsible\">" +
									"<h3>"+getMyTime(msg.returnMap.msg[n].check_in_time)+"</h3>" +
									"<p>"+
									"走访内容："+msg.returnMap.msg[n].visit_type_str+"<br>"+
									"走访形式："+msg.returnMap.msg[n].visit_class_str+"<br>"+
									"走访客户："+msg.returnMap.msg[n].visit_custom_name+"<br>"+
									"签到纬度："+msg.returnMap.msg[n].check_in_lat+"<br>"+
									"签到经度："+msg.returnMap.msg[n].check_in_lng+"<br>"+
									"洽谈项目："+msg.returnMap.msg[n].visit_content+"<br>"+
									"备注："+msg.returnMap.msg[n].visit_note
									+"</p></div>";
							$("#div_history_list").append(div_o);
					  	});
						$("#div_history_list").trigger("create");
						
						now_num = now_num + increace_num;
						if(total_num_for_history>now_num){
							$("#get_history_more_btn").show();
						}
					}else{
						$("#check_in_msg3").html(msg.msg);
					}
				}
				catch (exception) {
					alert(exception);
				}
				$("#get_history_btn").show();
			},
			error : function(msg) {
				alert('error!!');
			}
		});
		
	}else{
		document.getElementById("check_in_msg3").innerHTML = "身份信息有误，请重新登录后重试...";
	}
}

function getMyTime(timeStr){
	//20140919142635132
	var year_s = timeStr.substr(0,4);
	var month_s = timeStr.substr(4,2);
	var day_s = timeStr.substr(6,2);
	var hour_s = timeStr.substr(8,2);
	var minute_s = timeStr.substr(10,2);
	var second_s = timeStr.substr(12,2);
	
	var returnS = year_s+"年"+month_s+"月"+day_s+"日"+hour_s+"时"+minute_s+"分"+second_s+"秒";
	return returnS;
}


