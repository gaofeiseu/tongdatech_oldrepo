<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>fire base</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script src='https://cdn.firebase.com/js/client/1.1.1/firebase.js'></script>
		<script src='/firebase/js/fire_base.js'></script>
		<script type="text/javascript">
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body>
		<xl:wrap title="fire base">
			<form id="frm_add" action="/" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>Add</legend>
					<div class="form_item col1" align="center">
						<label>
							Key:
						</label>
						<span>
							<input type="text" id="add_key" name="add_key" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<label>
							Value:
						</label>
						<span>
							<input type="text" id="add_value" name="add_value" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="add_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="doadd();">确认</a>
					</div>
					</fieldset>
				</div>
			</form>
			<form id="frm_edit" action="/" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>Edit</legend>
					<div class="form_item col1" align="center">
						<label>
							Key:
						</label>
						<span>
							<input type="text" id="edit_key" name="edit_key" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<label>
							Value:
						</label>
						<span>
							<input type="text" id="edit_value" name="edit_value" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="edit_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="doedit();">确认</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>