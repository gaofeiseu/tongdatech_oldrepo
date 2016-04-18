<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>文件上传</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
			var user_name = '${session.userInfo.user_name}';
			var user_id = '${session.userInfo.user_id}';
			var mobile_img_url = "${application.ParamJson.M_FILE_URL}";
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
		<script type="text/javascript" src="/mobile_wap/js/file_upload.js" charset="GBK"></script>
	</head>
	<body>
		<xl:wrap title="文件上传">
			<form id="uploadFilefm" action="/fileop_fileupload.action" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
					<!-- 
              		<legend></legend>
              		 -->
              		<div class="form_item col1" align="center">
              			<label>
							文件类型:
						</label>
						<span>
							<input type="text" id="file_class" name="file_class" value="1" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.M_FILE_TYPE}"/>
		    			</span>
		    		</div>
					<div class="form_item col1" align="center">
						<label>
							文件:
						</label>
						<span>
							<xl:file name="upload" id="upload"></xl:file>
							<input type="hidden" name="file_name" id="file_name" />
							<input type="hidden" name="file_exe" id="file_exe" />
							<input type="hidden" name="user_name" id="user_name" />
							<input type="hidden" name="user_id" id="user_id" />
							<input type="hidden" name="file_save_path" id="file_save_path" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
              			<label>
							文件备注:
						</label>
						<span>
							<input type="text" id="file_msg" name="file_msg" style="width: 150px" />
		    			</span>
		    		</div>
		    		<div class="form_item col1" align="center" id="hiddendiv1" style="display:none;">
						
					</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="uploadFile_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-upload'" onclick="upload_file();">上传</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>