<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/mapfiles/css/default.css" rel="stylesheet" type="text/css" />
		<title>·������</title>
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>
		<script type="text/javascript">
		var my_lat = "${session.userInfo.lat}";
		var my_lon = "${session.userInfo.lng}";
		</script>
		<script type="text/javascript" src="/map/mapjs/map_echopath.js" charset="GBK"></script>
		<script type="text/javascript">
			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
			var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
			var map;// ȫ�ֵ�ͼ����
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		</script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');" class="easyui-layout">
		<div data-options="region:'west',split:true" title="·�����Բ�ѯ" style="width: 183px;">
			<fieldset>
              	<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="1" checked="checked" /> ʱ���ѯ��
              	</legend>
              	<div>��ʼʱ�䣺</div>
              	<div><input class="easyui-datetimebox" id="ljhx_query_timeST" ></input></div>
              	<div>����ʱ�䣺</div>
              	<div><input class="easyui-datetimebox" id="ljhx_query_timeED" ></input></div>
			</fieldset>
			<fieldset>
				<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="2" /> ��ɫ���Ͳ�ѯ��
              	</legend>
              	<div><input type="text" id="ljhx_selectbutton" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.USER_TYPE}"/></div>
			</fieldset>
			<fieldset>
				<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="3" /> ��¼����ѯ��
              	</legend>
              	<div><input class="easyui-validatebox textbox" id="ljhx_loginname_query" style="width: 150px"/></div>
			</fieldset>
            <input type="hidden" id="hidden_user_icon" name="hidden_user_icon" value="" />
            <div align="center">
            	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="ljhx_reloadscreen();">���ý���</a>
            	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="query_ljhx_data();">ȷ��</a>
            </div>
            <div align="center"><font color='red'><div id="echo_query_msg"></div></font></div>
		</div>
		<div data-options="region:'center',split:true" title="·�����Ե�ͼ" style="background: #fafafa; overflow: hidden;">
			<div id="map_canvas"  style="z-index:9;float:right; width:100%; height:100%;"></div>
		</div>
	</body>
</html>