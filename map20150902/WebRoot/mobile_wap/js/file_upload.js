function upload_file(){
	var file_msg = $('#file_msg').val();
	if(file_msg==""||file_msg==null||file_msg==undefined){
		$.messager.show({title: "提示",msg: "文件的备注信息最好不要为空！"});
		return;
	}
	var file_name = '';
	file_name = $('#upload').val();
	if(file_name==''||file_name==undefined||file_name==null){
		$.messager.show({title: "错误",msg: "请选中您想上传文件，再进行上传操作！"});
		return;
	}
	var file_exe = '';
	file_exe = file_name.substring(file_name.lastIndexOf('.')+1, file_name.length);
	if(file_exe==''||file_exe==undefined||file_exe==null||(file_exe=='exe'||file_exe=='EXE')||(file_exe=='bat'||file_exe=='BAT')||(file_exe=='jar'||file_exe=='JAR')){
		$.messager.show({title: "错误",msg: "不允许上传EXE,BAT,JAR格式的文件！"});
		return;
	}
	if(user_name==""||user_name==null||user_name==undefined||user_id==''||user_id==null||user_id==undefined){
		$.messager.show({title: "错误",msg: "登录用户信息有误，请返回或重新登录后重试！"});
		return;
	}
	$('#user_name').val(user_name);
	$('#user_id').val(user_id);
	$('#file_name').val(file_name);
	$('#file_exe').val(file_exe);
	var mobile_img_url_obj = eval('(' + mobile_img_url + ')');
	$("#file_save_path").val(decodeURIComponent(mobile_img_url_obj[0].value));
	$('#uploadFile_btn').linkbutton('disable');
	$("#hiddendiv1").text("");
	fmSubmit("#uploadFilefm","/fileop_fileupload.action",function(result){
		$('#uploadFile_btn').linkbutton('enable');
	});
}

function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				//
				$("#hiddendiv1").text(result.msg);
				$("#hiddendiv1").show();
				$.messager.show({title: "成功",msg: result.msg});
			}else{
				//
				$.messager.show({title: "失败",msg: result.msg});
			}
			callback(result);
		},
		error: function(){
			alert('error!!');
		}
	});
}