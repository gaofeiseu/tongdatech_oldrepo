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
			var url = "/report_query_table1.action";
			var data = {};
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
			$.messager.confirm('确认', '确认执行汇总程序？', function(r){
                if (r){
                	var url = "/report_run_rz.action";
        			var data = {};
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
            });
		}
		function getexcel(_type){
			var t = $("#time").datebox('getValue');
			if(t){
				var url = "/report_excelOne.action";
				var data = {};
				data["time"] = t;
				data["type"] = _type;
				$.ajax({
					type : "POST",
					cache : false,
					async : true,
					url : url,
					dataType : "json",
					data : data,
					success : function(result) {
						if(result.success){
							$("#zip_src").attr("href",result.backParam);
							$("#hiddendiv1").show();
							$.messager.show({title: "成功",msg: result.msg});
						}else{
							$.messager.show({title: "失败",msg: result.msg});
						}
					},
					error : function(msg) {
						//alert('error!!');
					}
				});
			}
		}
		function getexcel2(_type){
			var t = $("#time").datebox('getValue');
			if(t){
				var url = "/report_excelTwo.action";
				var data = {};
				data["time"] = t;
				data["type"] = _type;
				$.ajax({
					type : "POST",
					cache : false,
					async : true,
					url : url,
					dataType : "json",
					data : data,
					success : function(result) {
						if(result.success){
							$("#zip_src2").attr("href",result.backParam);
							$("#hiddendiv2").show();
							$.messager.show({title: "成功",msg: result.msg});
						}else{
							$.messager.show({title: "失败",msg: result.msg});
						}
					},
					error : function(msg) {
						//alert('error!!');
					}
				});
			}
		}
		</script>
	</head>
	<body>
		<!-- <input type="button" id="query_btn" value="查询按钮" onclick="query();" /> -->
		<input type="button" id="run_btn" value="执行日终" onclick="runsql();" />
		<input id="time" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
    	<script type="text/javascript">
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'.'+(m<10?('0'+m):m)+'.'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('.'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
    	</script>
		<input type="button" id="excel_btn" value="获取【电子地图走访平台使用情况统计表】报表（全省）" onclick="getexcel('1');" />
		<input type="button" id="excel_btn" value="获取【电子地图走访平台走访情况统计表】报表（全省）" onclick="getexcel2('1');" />
		<input type="button" id="excel_btn" value="获取【电子地图走访平台使用情况统计表】报表（市级）" onclick="getexcel('2');" />
		<input type="button" id="excel_btn" value="获取【电子地图走访平台走访情况统计表】报表（市级）" onclick="getexcel2('2');" />
		<div id="hiddendiv1" style="display:none;">
			【电子地图走访平台使用情况统计表】<a id="zip_src" href="#">Excel文件包</a>
		</div>
		<div id="hiddendiv2" style="display:none;">
			【电子地图走访平台走访情况统计表】<a id="zip_src2" href="#">Excel文件包</a>
		</div>
		<table id="result_table" class="gridtable">
		</table>
	</body>
</html>