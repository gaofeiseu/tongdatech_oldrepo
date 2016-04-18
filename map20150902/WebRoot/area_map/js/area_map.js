function submit_insert_frm(){
	var insert_clob = $("#insert_clob").val();
	if(insert_clob){
		var url="/areamap_insertCLOB.action";
		var json = {};
		json["insert_clob"]=insert_clob;
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
				}
				catch (exception) {
					
				}
			},
			error : function(msg) {
				
			}
		});
	}else{
		$.messager.show({title: "失败",msg: "请先填写CLOB的内容！"});
	}
}