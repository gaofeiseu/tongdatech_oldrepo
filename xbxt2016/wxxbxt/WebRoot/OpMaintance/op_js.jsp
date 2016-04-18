<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<title>�޸�ϵͳ�����ļ�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/OpMaintance/js/OpJS.js" charset="GBK"></script>
	</head>
	<body>
		<div>
			<form id="fm" action="/" style="margin: 50px auto; width: 600px">
				<fieldset>
					<legend>
						��ͼ�����޸�
					</legend>
					<div class="form_item col1" style="text-align: center;">
						<label>
							����URL:
						</label>
						<input type="text" id="url_combobox" name="url_combobox" value="" class="easyui-combobox" 
							style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.C_SERVER_URL},
							onChange:function(newvalue){$('#server_url').val(decodeURIComponent(newvalue));}"/>
					</div>
					<div class="form_item col1" style="text-align: center;">
						<label>
							URL��
						</label>
						<input type="text" id="server_url" name="server_url" class="easyui-validatebox" />
					</div>
					<div class="form_item col1" style="text-align: center;">
						<label>
							��������
						</label>
						<input type="text" id="max_zoom" name="max_zoom" class="easyui-validatebox" data-options="validType:'number'" />
					</div>
					<div class="form_item col1" style="text-align: center;">
						<label>
							��С������
						</label>
						<input type="text" id="min_zoom" name="min_zoom" class="easyui-validatebox" data-options="validType:'number'" />
					</div>
					<div class="form_item col1" style="text-align: center;">
						<a href="#" id="sub_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="dosubmit()">�ύ</a>
					</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>