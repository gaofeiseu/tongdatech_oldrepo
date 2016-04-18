<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>


		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
		 function doquery(){	    
		    if($("#queryfm").form("validate")){//对前台easyui控制值进行校验
		      $("#queryfm").submit();
		    }
		 }
		</script>

	</head>

	<body>
		<xl:wrap title="普通分页 /demo/pagination/demoPage_init.jsp">
			<form id="queryfm" action="/demopage_query" 
			style="margin: 50px auto;width:600px">
			<fieldset>
              <legend>查询条件</legend>
			<div class="form_item col2"><!--col2 2列布局  -->
				<label>colint1:</label>
				<input type="text" name="colint1" class="easyui-validatebox"
					data-options="validType:'number'" />
		    </div>
		    <div class="form_item col2">
				<label>colint2:</label>
				<input type="text" name="colint2" class="easyui-validatebox"
					data-options="validType:'number'" />
			</div>
			<div class="form_item col2">
			    <label>colstr1:</label>
				<input type="text" name="colstr1" />
			</div>
			
			<div class="form_item col2">
				<label>colstr2:</label>
				<input type="text" name="colstr2" />
			</div>
			<div class="form_item col2">
				<label>coldate1:</label>
				<input type="text" name="coldate1" class="easyui-datebox"
					data-options="editable:false" />
			</div>
			<div class="form_item col2"><!--col1 1列布局  -->
				<label>coldate2:</label>
				<input type="text" name="coldate2" class="easyui-datetimebox"
					data-options="editable:false" />
			</div>
			<div class="form_item col2">
				<label>title:</label>
				<input type="text" name="title" />
			</div>
			<div class="form_item col1" style="text-align: center;">
				<input type="hidden" name="back_url" value="/demopage_init" />
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">查询</a>
				
			</div>
			</fieldset>
			</form>
			
		</xl:wrap>
	</body>
</html>
