/**
 * 
 * @author Mr.GaoFei
 * @Time 2014��7��22��9:43:24
 * 
 */

function loadChildComboForComboSelectChange(a,b){
	if(a==''||a==undefined||a==null||b==''||b==undefined||b==null){
		
	}else{
		console.log('main_class:'+a+'   user_type:'+b);
		var url="data_input_getChildClassCombo.action";
		var json = {};
		json["comboselect_main_class"] = a;
		json["comboselect_user_type"] = b;
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
						if(msg.list_map.length==0||msg.list_map==null){
							$('#childclass_sn').combobox('loaddata',[{'value': '0','text': '--��--'}]);
						}else{
							console.log('JSON.stringify(msg.list_map)------>'+JSON.stringify(msg.list_map));
							$('#childclass_sn').combobox('loadData',msg.list_map);
							$('#childclass_sn').combobox('select', msg.list_map[0].value);
						}
					}else{
						$.messager.show({title: "ʧ��",msg: msg.msg});
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
}

function getTemplat(){

	var class_name = $('#childclass_sn').combobox('getValue');//�����͵�SN
	var childclass_name = $('#childclass_sn').combobox('getText');
 	
	if(childclass_name==null||childclass_name=="")
	{
		$.messager.show({title: "��Ϣ",msg: "����ѡ����Ҫ��������������"});
		return ;
	}
	var url="data_input_getTemplat.action";
	var json = {};
	//׼�����͵���̨�������͵��������

	json["templat_childclass_sn"]=class_name;//������SN
	json["templat_childclass_name"]=childclass_name;//����������
	$('#download_btn').linkbutton('disable');
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
					//$("#templat_download").attr("href",msg.returnList[1]);
					$("#templat_download").attr("href","/map_datainput_downloadfile.action?file_path="+msg.returnList[1]+"&file_name=���ݵ���ģ��.xls");
					$("#templat_download").html(msg.returnList[0]+' Excelģ��');
					$("#h_str1").show();
					$("#h_str2").show();
					$("#upload_div").show();
					$('#download_btn').linkbutton('enable');
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
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

function upload_excel(){
	console.log('file_name:'+$("input[name='upload']").val());
	
	var file_name = $("input[name='upload']").val();
	if(file_name==null||file_name==undefined||file_name==''){
		$.messager.show({title: "��ʾ",msg: "�ϴ����ļ�������Ϊ�գ�"});
		return;
	}else{
		//file_name.substring(file_name.lastIndexOf('\\')+1)//��ȡ�ļ���
		//file_name.substring(file_name.lastIndexOf('.')+1)//��ȡ�ļ���׺
		$('#upload_excel_name').val(file_name.substring(file_name.lastIndexOf('\\')+1));
		$('#upload_childclass_sn').val($('#childclass_sn').combobox('getValue'));
		$('#upload_excel_btn').linkbutton('disable');
		if(file_name.substring(file_name.lastIndexOf('.')+1)=='xls'||file_name.substring(file_name.lastIndexOf('.')+1)=='XLS'){
			fmSubmit("#queryfm","data_input_uploadTemplat.action",function(result){
				$('#upload_excel_btn').linkbutton('enable');
			});
		}else{
			$.messager.show({title: "��ʾ",msg: "�ϴ����ļ�ֻ����xls��׺��Excel�ļ���"});
			$('#upload_excel_btn').linkbutton('enable');
		}
	}
}

function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
		success: function(result){
			var result = eval("("+result+")");
			if(result.if_success){
				//
				
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