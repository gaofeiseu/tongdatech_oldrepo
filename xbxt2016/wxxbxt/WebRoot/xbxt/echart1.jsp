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
		<title>cust_month_usage_report</title>
		<xl:resource></xl:resource>
		<script src="/xbxt/js/echarts.min.js"></script>
		<script type="text/javascript">
    	
		</script>
	</head>
	<body>
		<div id="main" style="width: 600px;height:400px;"></div>
		<script type="text/javascript">
        // ����׼���õ�dom����ʼ��echartsʵ��
        var myChart = echarts.init(document.getElementById('main'));

        // ָ��ͼ��������������
        var option = {
            title: {
                text: 'ECharts ����ʾ��'
            },
            tooltip: {},
            legend: {
                data:['����']
            },
            xAxis: {
                data: ["����","��ë��","ѩ����","����","�߸�Ь","����"]
            },
            yAxis: {},
            series: [{
                name: '����',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // ʹ�ø�ָ�����������������ʾͼ��
        myChart.setOption(option);
    </script>
	</body>
</html>