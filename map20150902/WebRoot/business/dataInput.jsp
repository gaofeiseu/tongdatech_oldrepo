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
		<script type="text/javascript" src="/business/js/dataInput.js" charset="GBK"></script>
		<script type="text/javascript">
			window.onload=function()
			{
				$('#mainclass_sn').combobox('select', '1');
				$('#user_type').combobox('select', '1');
			};
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body>
		<xl:wrap title="���ݵ���">
			<form id="queryfm" action="map_datainput_uploadTemplat.action" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>��ȡģ��</legend>
			
					<div class="form_item col2">
			    		<label>��������:</label>
						<input type="text" id="childclass_sn" name="childclass_sn" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',url:'/data_manage_getSN.action',panelHeight:'auto'"/>
					</div>
					<div class="form_item col1" style="text-align: center;">
						<a href="#" id="download_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-download'" onclick="getTemplat();">��ȡģ��</a>
					</div>
					<div class="form_item col1" style="text-align: center;display: none" id="h_str1" >
						<font color="red">����������������ض�Ӧ��ģ���ļ���</font>
					</div>
					<div class="form_item col1" style="text-align: center;display: none" id="h_str2" >
						<a id="templat_download" ></a>
					</div>
					</fieldset>
				</div>
				<div id="upload_div" style="display: none">
					<fieldset>
              			<legend>�ϴ���д�õ�Excel�ļ�</legend>
              			<div class="form_item col1" style="text-align: center;" >
							<font color="red">�뽫����������ͼ������ļ�ѡ��Ȼ�������水ť���ļ��ύ����������</font>
						</div>
						<div class="form_item col1" style="text-align: center;" >
							<xl:file name="upload"></xl:file>
							<input type="hidden" id="upload_excel_name" name="upload_excel_name" />
							<input type="hidden" id="upload_childclass_sn" name="upload_childclass_sn" />
						</div>
						<div class="form_item col1" style="text-align:center">
							<a href="#" id="upload_excel_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="upload_excel();">����</a>
						</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>