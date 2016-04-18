<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>地图碎片处理</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/maptile_jsp/js/map_tile.js"></script>
		<script type="text/javascript">
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body>
		<xl:wrap title="地图碎片处理">
			<form id="frm" action="map_tile_doop.action" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>操作区域</legend>
					<div class="form_item col1" align="center">
						<label>
							操作的文件夹:
						</label>
						<span>
							<input type="text" id="select_folder" name="select_folder" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center" id="hiddendiv1" style="display:none;">
						
					</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="subimit_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="subimit_frm();">确认</a>
					</div>
					</fieldset>
				</div>
			</form>
			<form id="frm2" action="map_tile_foop.action" style="margin: 40px auto;width:600px" method="post" enctype="multipart/form-data">
				<div>
					<fieldset>
              		<legend>操作区域</legend>
					<div class="form_item col1" align="center">
						<label>
							Type:
						</label>
						<span>
							<input type="text" id="select_Type" name="select_Type" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<label>
							Zoom:
						</label>
						<span>
							<input type="text" id="select_Zoom" name="select_Zoom" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<label>
							X:
						</label>
						<span>
							<input type="text" id="select_X" name="select_X" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<label>
							Y:
						</label>
						<span>
							<input type="text" id="select_Y" name="select_Y" />
						</span>
		    		</div>
		    		<div class="form_item col1" align="center">
						<a href="#" id="subimit_btn2" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="subimit_frm2();">确认</a>
					</div>
					</fieldset>
				</div>
			</form>
		</xl:wrap>
	</body>
</html>