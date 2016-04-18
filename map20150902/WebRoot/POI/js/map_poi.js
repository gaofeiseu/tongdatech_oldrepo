function getData(){
	var city = $("#city").val();
	var query_str = $("#query_str").val();
	if(city&&query_str){
		$("#my_table").empty();
		document.getElementById("show_msg").innerHTML = "";
		$("#sub_bt").attr("disabled","true");
		var url="/poi_getData.action";
		var json = {};
		json["city"]=city;
		json["query_str"]=query_str;
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
						document.getElementById("show_msg").innerHTML="��ѯ�����"+msg.list_map.length+"��";
						$("#my_table").append("<tr><th>����</th><th>�ٶ�γ��</th><th>�ٶȾ���</th><th>��ַ��Ϣ</th><th>UID</th></tr>");
						for(var i=0;i<msg.list_map.length;i++){
							var trHTML = "<tr><td>"+msg.list_map[i].name+"</td><td>"
								+msg.list_map[i].lat+"</td><td>"
								+msg.list_map[i].lng+"</td><td>"
								+msg.list_map[i].address+"</td><td>"
								+msg.list_map[i].uid+"</td></tr>";
							$("#my_table").append(trHTML);
						}
					}else{
						$.messager.show({title: "ʧ��",msg: msg.msg});
					}
					$("#sub_bt").removeAttr("disabled");
				}
				catch (exception) {
					
				}
			},
			error : function(msg) {
				
			}
		});
	}else{
		$.messager.show({title: "ʧ��",msg: "����д�������Ͳ�ѯ�ؼ��ֺ��ٽ��в�ѯ��"});
	}
}