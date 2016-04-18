function getNum(a){
	var a_id = a.id;
	var query_type = a_id.substring(a_id.length-1,a_id.length);
	var url="/remind_getNum.action";
	var json = {};
	json["query_type"]=query_type;
	json["user_root_brch"]=user_root_brch;
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
					$("#"+a_id).text(msg.msg);
				}else{
					$.messager.show({title: "Ê§°Ü",msg: msg.msg});
				}
			}
			catch (exception) {
				
			}
		},
		error : function(msg) {
			
		}
	});
}

