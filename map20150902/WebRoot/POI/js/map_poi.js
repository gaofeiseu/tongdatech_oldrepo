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
						document.getElementById("show_msg").innerHTML="查询结果共"+msg.list_map.length+"条";
						$("#my_table").append("<tr><th>名称</th><th>百度纬度</th><th>百度经度</th><th>地址信息</th><th>UID</th></tr>");
						for(var i=0;i<msg.list_map.length;i++){
							var trHTML = "<tr><td>"+msg.list_map[i].name+"</td><td>"
								+msg.list_map[i].lat+"</td><td>"
								+msg.list_map[i].lng+"</td><td>"
								+msg.list_map[i].address+"</td><td>"
								+msg.list_map[i].uid+"</td></tr>";
							$("#my_table").append(trHTML);
						}
					}else{
						$.messager.show({title: "失败",msg: msg.msg});
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
		$.messager.show({title: "失败",msg: "请填写城市名和查询关键字后再进行查询！"});
	}
}