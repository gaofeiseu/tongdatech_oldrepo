<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- 地图2级联动  -->
<head>
	<meta charset="UTF-8">
	<title>ECharts</title>
</head>
<body>
	<div id="main"
		style="height: 600px; border: 1px solid #ccc; padding: 10px;"></div>
	<script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
	<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('main'));
	var option = {};
	myChart.setOption(option);
</script>
</body>