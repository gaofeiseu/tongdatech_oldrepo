<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>报表获取</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
		function get_excel(){
			var time = $("#report_time").datebox('getValue');
			var type = $("#report_type").combobox('getValue');
			if(!time||time==""){
				$.messager.show({title: "注意",msg: "请选择报表日期之后再进行操作！"});
				return;
			}
			if(!type||type==""){
				$.messager.show({title: "注意",msg: "请选择报表类型之后再进行操作！"});
				return;
			}
			if(type=="1"){
				getexcel(time,"1");
			}else if(type=="2"){
				getexcel2(time,"1");
			}else if(type=="3"){
				getexcel(time,"2");
			}else if(type=="4"){
				getexcel2(time,"2");
			}
		}
		function getexcel(t,_type){
			if(t){
				var url = "/report_doExcelOne.action";
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
		function getexcel2(t,_type){
			if(t){
				var url = "/report_doExcelTwo.action";
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
		</script>
	</head>
	<body>
		<xl:wrap title="报表获取">
			<form id="frm" action="/" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>获取指定类型的报表</legend>
					<div class="form_item col2">
						<label>报表类型:</label>
						<input type="text" id="report_type" name="report_type" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',panelHeight:'auto',data:${application.ParamJson.REPORT_TYPE}"/>
		    		</div>
		    		<div class="form_item col2">
						<label>报表日期:</label>
						<input id="report_time" name="report_time" class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"></input>
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
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="getExcelBtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-download'" onclick="get_excel();">获取报表</a>
					</div>
					<div class="form_item col1" align="center" id="hiddendiv1" style="display:none;">
						<a id="zip_src" href="#">Excel文件包</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>