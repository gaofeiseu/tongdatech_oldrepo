/**
 * @author Mr.GaoFei
 */
function upload_TXT(){
	var file_name = '';
	file_name = $('#upload').val();
	if(file_name==''||file_name==undefined||file_name==null){
		$.messager.show({title: "����",msg: "��ѡ�����������TXT�ļ����ٽ����ϴ�������"});
		return;
	}
	var file_exe = '';
	file_exe = file_name.substring(file_name.lastIndexOf('.')+1, file_name.length);
	if(file_exe==''||file_exe==undefined||file_exe==null||(file_exe!='txt'&&file_exe!='TXT')){
		$.messager.show({title: "����",msg: "ֻ����TXT�ļ����ϴ�������"});
		return;
	}
	$('#file_name').val(file_name);
	$('#file_exe').val(file_exe);
	$('#uploadTXT_btn').linkbutton('disable');
	fmSubmit("#uploadTXTfm","map_dataparser_uploadTXT.action",function(result){
		$('#uploadTXT_btn').linkbutton('enable');
	});
}

function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				//
				$("#zip_src").attr("href",result.returnString);
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