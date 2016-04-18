<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<!-- Öù×´ºÍÕÛÏß  -->
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
    <xl:resource></xl:resource>
</head>
<body>
    <div id="main" style="height:100%;border:1px solid #ccc;padding:10px;"></div>
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
 	
    var url="echarts_demo_loaddata.action";
	var json = {};
	json["echarts_type"]="3";
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
					var obj = eval('(' + msg.returnString + ')');
					myChart.setOption(obj);
				}else{
					$.messager.show({title: "´íÎó",msg: msg.msg});
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

    </script>
</body>