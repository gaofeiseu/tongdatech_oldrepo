/**
 *
 * @author Mr.GaoFei
 *
**/

function doOpenUserIconDlg(){
	if(select_rowData==null||select_rowData==undefined){
		$.messager.show({title: "提示",msg: "请选中需要进行用户标识设置的用户！"});
		return;
	}
	var ss1 = select_rowData.user_name+'(昵称：'+select_rowData.nick_name+'   ID：'+select_rowData.user_id+')';
	$("#show_str1").text(ss1);
	getMyIconHtml();
	$('#addoredit_usericon_dlg').dialog('open');
}

function getMyIconHtml(){
	var url="user_getMyIconSelectHtml.action";
	var json = {};
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
					$("#show_str2").html(msg.returnHtml);
				}else{
					alert(msg.msg);
					return;
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


function doAddOrEditUserIcon(){
	var ss = "确认将用户"+select_rowData.user_name+"{昵称："+select_rowData.nick_name+"   ID："+select_rowData.user_id+"}的标识设置为"+"<img src='"+$('#hidden_user_icon').val()+"' />？";
	$('#show_str4').html(ss);
	$('#confirm_usericon_dlg').dialog('open');
}
function submitUserIcon(){
	var icon_user_id = select_rowData.user_id;
	var icon_user_icon = $('#hidden_user_icon').val();
	
	var url="user_submitUserIcon.action";
	var json = {};
	json["icon_user_id"]=icon_user_id;
	json["icon_user_icon"]=icon_user_icon;
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
					$.messager.show({title: "成功",msg: msg.msg});
					$('#confirm_usericon_dlg').dialog('close');
					$('#addoredit_usericon_dlg').dialog('close');
					$('#userTab').datagrid('reload');
				}else{
					$.messager.show({title: "失败",msg: msg.msg});
					$('#confirm_usericon_dlg').dialog('close');
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

function setMarkerIconToMySelect(this_src){
	//this.src----http://localhost:7001/mapfiles/markers/0blue-ball.png 
	var a_1=[];
	a_1 = this_src.split("/mapfiles/");
	this_src = /mapfiles/+a_1[1];
	//console.log("this_src:"+this_src);
	$('#hidden_user_icon').val(this_src);
	var img_html = "<img src='"+this_src+"' />";
	$("#show_str3").html(img_html);
}