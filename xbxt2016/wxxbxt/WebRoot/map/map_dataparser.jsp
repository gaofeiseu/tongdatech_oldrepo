<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>��ע����ά��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/map_dataparser.js" charset="GBK"></script>
		<script type="text/javascript">
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body>
		<xl:wrap title="���ݽ���">
			<form id="uploadTXTfm" action="map_dataparser_uploadTXT.action" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>�ϴ�TXT�ļ�</legend>
              		<div class="form_item col1" align="center">
						<font color="red">��ʾ���ϴ���TXT�ļ��в�Ҫ�������������п��У�</font>
		    		</div>
					<div class="form_item col1" align="center">
						<label>
							TXT�ļ�:
						</label>
						<span>
							<xl:file name="upload" id="upload"></xl:file>
							<input type="hidden" name="file_name" id="file_name" />
							<input type="hidden" name="file_exe" id="file_exe" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center" id="hiddendiv1" style="display:none;">
						<a id="zip_src" href="#">�����õ�Excel�ļ���</a>
					</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="uploadTXT_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-upload'" onclick="upload_TXT();">�ϴ�TXT</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>