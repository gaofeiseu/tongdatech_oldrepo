<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>POI</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<link href="/POI/tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
		<script type="text/javascript" src="/POI/tablecloth/tablecloth.js"></script>
		<script type="text/javascript" src="/POI/js/map_poi.js"></script>
	</head>
	<body>
		<div>
			城市：<input type="text" id="city" />
			查询关键字：<input type="text" id="query_str" />
			<button id="sub_bt" onclick="getData();">--查  询--</button>
			<label id="show_msg"></label>
		</div>
		<table id="my_table" cellspacing="0" cellpadding="0">
			
		</table>
	</body>
</html>