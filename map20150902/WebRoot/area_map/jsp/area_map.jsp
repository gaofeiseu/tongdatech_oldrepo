<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>�����ͼ</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/area_map/js/area_map.js"></script>
		<script type="text/javascript">
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body>
		<xl:wrap title="�����ͼ">
			<form id="insert_frm" action="areamap_insertCLOB.action" style="margin: 40px auto;width:600px" method="post" >
				<div>
					<fieldset>
              		<legend>¼��CLOB</legend>
					<div class="form_item col1" align="center">
						<label>
							CLOB:
						</label>
						<span>
							<input type="text" id="insert_clob" name="insert_clob" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="subimit_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_insert_frm();">����</a>
					</div>
					</fieldset>
				</div>
			</form>
			<form id="select_frm" action="areamap_selectCLOB.action" style="margin: 40px auto;width:600px" method="post" >
				<div>
					<fieldset>
              		<legend>��ȡCLOB</legend>
					<div class="form_item col1" align="center">
						<label>
							CLOB:
						</label>
						<span>
							<input type="text" id="select_clob" name="select_clob" readonly />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="subimit_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_select_frm();">��ѯ</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>