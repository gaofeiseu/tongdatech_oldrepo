<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>DB</title>
		<xl:resource></xl:resource>
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
		<script type="text/javascript">
		function query(){
			var url = "/poi_query.action";
			var data = {};
			data["query_str"] = $("#query_str").val();
			data["password"] = $("#password").val();
			$.ajax({
				type : "POST",
				cache : false,
				async : true,
				url : url,
				dataType : "json",
				data : data,
				success : function(result) {
					if(result.success){
						if(result.backParam.length!=0){
							var s = "";
							s += "<tr>";
							for(var a in result.backParam[0]){
								s += "<th>"+a+"</th>";
							}
							s += "</tr>";
							for(var i=0;i<result.backParam.length;i++){
								s += "<tr>";
								for(var b in result.backParam[i]){
									s += "<td>"+result.backParam[i][b]+"</td>";
								}
								s += "</tr>";
							}
							$("#result_table").html(s);
						}
					}else{
						alert(result.msg);
					}
				},
				error : function(msg) {
					alert('error!!');
				}
			});
		}
		function runsql(){
			var url = "/poi_runsql.action";
			var data = {};
			data["query_str"] = $("#query_str").val();
			data["password"] = $("#password").val();
			$.ajax({
				type : "POST",
				cache : false,
				async : true,
				url : url,
				dataType : "json",
				data : data,
				success : function(result) {
					if(result.success){
						alert(result.msg);
					}else{
						alert(result.msg);
					}
				},
				error : function(msg) {
					alert('error!!');
				}
			});
		}
		</script>
	</head>
	<body>
		<textarea id="query_str" rows="4" cols="100"></textarea>
		<input type="button" id="query_btn" value="查询按钮" onclick="query();" />
		<input type="button" id="run_btn" value="执行按钮" onclick="runsql();" />
		<input type="password" id="password" />
		<table id="result_table" class="gridtable">
		</table>
	</body>
</html>