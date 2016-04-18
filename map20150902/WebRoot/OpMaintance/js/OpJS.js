function dosubmit(){
	var server_url = $('#server_url').val();
	var max_zoom = $('#max_zoom').val();
	var min_zoom = $('#min_zoom').val();
	var isValid = $('#fm').form('validate');
	if(!isValid){
		return;
	}
	if((server_url==''||server_url==null||server_url==undefined)
			&&(max_zoom==''||max_zoom==null||max_zoom==undefined)
			&&(min_zoom==''||min_zoom==null||min_zoom==undefined)){
		$.messager.show({title: "提醒",msg: "参数都是空就不要提交了(⊙o⊙)…"});
		return;
	}
	var url="op_maintance_js_dosubmit.action";
	var json = {};
	json["server_url"] = server_url;
	json["max_zoom"] = max_zoom;
	json["min_zoom"] = min_zoom;
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
				}else{
					$.messager.show({title: "失败",msg: msg.msg});
				}
				$('#sub_btn').linkbutton('disable');
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