

function change(e){
    var src=e.target || window.event.srcElement; //��ȡ�¼�Դ������chrome/IE
    $("#file_fake_name").val(src.value);
}
var if_do_as_old = true;
function handleSuccess(position){
    // ��ȡ����ǰλ�þ�γ��  ��������chrome�����ȡ������google��ͼ�еľ�γ��
    var lng = position.coords.longitude;
    var lat = position.coords.latitude;
    // ���ðٶȵ�ͼapi��ʾ
    $("#check_in_lat").val(lat);
	$("#check_in_lng").val(lng);
	doSomethingLater();
}

function showError(str){
	console.log(str);
}

function handleError(error){
	//alert('��ȡ������Ϣ��ʱ��������'+error+error.toString());
	switch(error.code) {
    case error.TIMEOUT:
        showError('HTML5:��ȡ��γ����Ϣ��ʱ����ȷ��GPS���������³��ԣ�');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.POSITION_UNAVAILABLE:
        showError('HTML5:����ǰλ���޷���λ�����Ժ��ٳ��ԣ�');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.PERMISSION_DENIED:
        showError('HTML5:�������������GPS����λ����Ϣ�������޷���ȡ��γ����Ϣ��');
        getLatLngForCheckin2();
        if_do_as_old = false;
        break;
    case error.UNKNOWN_ERROR:
        showError('HTML5:��ȡ������Ϣʱ����δ֪������ȷ��GPS���������½��г��ԣ�');
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
 * ��¼
 */
function login_func(){
	var login_name = $("#fname").val();
	var pass_word = $("#fpassword").val();
	if(login_name==""||pass_word==""){
		alert("�������û����������ٽ��е�½��");
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
					$("#second_page_my_name").text("�û���¼����"+msg.returnMap.msg.user_name+" �ǳƣ�"+msg.returnMap.msg.user_name);
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
 * ǩ��
 */
function check_in(){
	if($("#my_login_name").val()&&$("#my_login_id").val()){
		getLatLngForCheckin();
		document.getElementById("check_in_msg").innerHTML = "���ڻ�ȡ������Ϣ...";
	}else{
		document.getElementById("check_in_msg").innerHTML = "�����Ϣ���������µ�¼������...";
	}
}

function doSomethingLater(){
	if($("#check_in_lat").val()==''||$("#check_in_lat").val()==null||$("#check_in_lat").val()==undefined||
			$("#check_in_lng").val()==''||$("#check_in_lng").val()==null||$("#check_in_lng").val()==undefined){
		alert("��γ����Ϣ������Ϊ�գ�");
		return;
	}
	//$("#file_fake_name").val($("#upload").val());
	var check_in_lat = $("#check_in_lat").val();
	var check_in_lng = $("#check_in_lng").val();
//	var mobile_img_url_obj = eval('(' + mobile_img_url + ')');
//	$("#mobile_img_url").val(decodeURIComponent(mobile_img_url_obj[0].value));
	$("#mobile_img_url").val('/home/weblogic/file_folder/mobile_img_folder/');
	if(!if_do_as_old){
		$("#if_wap").val('1');//����wap�ٶȶ�λ
	}else{
		$("#if_wap").val('2');//����html5��λ
	}
	
	document.getElementById("check_in_msg").innerHTML = "�����ϴ�����...";
	//document.getElementById("check_in_msg").innerHTML = "��γ����Ϣ��"+check_in_lng+","+check_in_lat;
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
		console.log('��ʼʱ�䣺'+history_time_st+'\n'+'����ʱ�䣺'+history_time_ed);
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
													"�߷����ݣ�"+msg.returnMap.msg[n].visit_type_str+"<br>"+
													"�߷���ʽ��"+msg.returnMap.msg[n].visit_class_str+"<br>"+
													"�߷ÿͻ���"+msg.returnMap.msg[n].visit_custom_name+"<br>"+
													"ǩ��γ�ȣ�"+msg.returnMap.msg[n].check_in_lat+"<br>"+
													"ǩ�����ȣ�"+msg.returnMap.msg[n].check_in_lng+"<br>"+
													"Ǣ̸��Ŀ��"+msg.returnMap.msg[n].visit_content+"<br>"+
													"��ע��"+msg.returnMap.msg[n].visit_note
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
		document.getElementById("check_in_msg3").innerHTML = "�����Ϣ���������µ�¼������...";
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
		console.log('��ʼʱ�䣺'+history_time_st+'\n'+'����ʱ�䣺'+history_time_ed);
		
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
									"�߷����ݣ�"+msg.returnMap.msg[n].visit_type_str+"<br>"+
									"�߷���ʽ��"+msg.returnMap.msg[n].visit_class_str+"<br>"+
									"�߷ÿͻ���"+msg.returnMap.msg[n].visit_custom_name+"<br>"+
									"ǩ��γ�ȣ�"+msg.returnMap.msg[n].check_in_lat+"<br>"+
									"ǩ�����ȣ�"+msg.returnMap.msg[n].check_in_lng+"<br>"+
									"Ǣ̸��Ŀ��"+msg.returnMap.msg[n].visit_content+"<br>"+
									"��ע��"+msg.returnMap.msg[n].visit_note
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
		document.getElementById("check_in_msg3").innerHTML = "�����Ϣ���������µ�¼������...";
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
	
	var returnS = year_s+"��"+month_s+"��"+day_s+"��"+hour_s+"ʱ"+minute_s+"��"+second_s+"��";
	return returnS;
}


