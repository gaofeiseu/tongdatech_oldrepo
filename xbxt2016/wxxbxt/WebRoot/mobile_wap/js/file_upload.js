function upload_file(){
	var file_msg = $('#file_msg').val();
	if(file_msg==""||file_msg==null||file_msg==undefined){
		$.messager.show({title: "��ʾ",msg: "�ļ��ı�ע��Ϣ��ò�ҪΪ�գ�"});
		return;
	}
	var file_name = '';
	file_name = $('#upload').val();
	if(file_name==''||file_name==undefined||file_name==null){
		$.messager.show({title: "����",msg: "��ѡ�������ϴ��ļ����ٽ����ϴ�������"});
		return;
	}
	var file_exe = '';
	file_exe = file_name.substring(file_name.lastIndexOf('.')+1, file_name.length);
	if(file_exe==''||file_exe==undefined||file_exe==null||(file_exe=='exe'||file_exe=='EXE')||(file_exe=='bat'||file_exe=='BAT')||(file_exe=='jar'||file_exe=='JAR')){
		$.messager.show({title: "����",msg: "�������ϴ�EXE,BAT,JAR��ʽ���ļ���"});
		return;
	}
	if(user_name==""||user_name==null||user_name==undefined||user_id==''||user_id==null||user_id==undefined){
		$.messager.show({title: "����",msg: "��¼�û���Ϣ�����뷵�ػ����µ�¼�����ԣ�"});
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
				$.messager.show({title: "�ɹ�",msg: result.msg});
			}else{
				//
				$.messager.show({title: "ʧ��",msg: result.msg});
			}
			callback(result);
		},
		error: function(){
			alert('error!!');
		}
	});
}