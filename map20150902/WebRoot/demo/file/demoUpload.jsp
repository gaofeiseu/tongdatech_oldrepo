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


	</head>
	<script type="text/javascript">
	function doSave(){
	  $("#fm").submit();
	}
	</script>

	<body>
		<xl:wrap title=" �ļ��ϴ�  /demo/file/demoUpload.jsp">


			<form id="fm" action="/demoUpload_doUp" method="post" enctype="multipart/form-data" >
				<fieldset>
					<legend>  
						demoUpload_�ļ��ϴ�  
					</legend>
					<s:fielderror cssStyle="color:red" />
					<div class="form_item col1">
						<label>
							�ϴ��ļ�1:
						</label>
						<span>
						<xl:file name="upload"></xl:file>
					</span>
					</div>
					<div class="form_item col1">
						<label>
							�ϴ��ļ�2:
						</label>
						<xl:file name="upload" id="myid" height="40px"></xl:file>
					</div>
					<div class="form_item col1">
						<label>
							�ϴ��ļ�3:
						</label>
						<xl:file name="upload" id="myid" height="30px" width="200px" accept="image/*" ></xl:file>
						
					</div>
					<div class="form_item col1" style="text-align:center">
					<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="doSave()">����</a>
					
					</div>
				</fieldset>
			</form>
		</xl:wrap>
	</body>
</html>
