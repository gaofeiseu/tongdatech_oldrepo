<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- ��ͼ  -->
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
</head>
<body>
    <div id="main" style="height:600px;width:600px;float:left;border:1px solid #ccc;padding:10px;"></div>
    <div id="main2" style="height:600px;width:600px;float:left;border:1px solid #ccc;padding:10px;"></div>
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
        title : {
            text: '���ǻ�԰��Ա�ֲ�ͼ',
            //subtext: '��������',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['�߼���ʯ��Ա','�߼�VIP��Ա','�м�VIP��Ա','����VIP��Ա','��ͨ�ͻ�']
        },
        toolbox: {
            show : true,
            orient : 'vertical',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'�ͻ�����',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'����VIP��Ա'},
                    {value:310, name:'�м�VIP��Ա'},
                    {value:234, name:'�߼�VIP��Ա'},
                    {value:135, name:'�߼���ʯ��Ա'},
                    {value:1548, name:'��ͨ�ͻ�'}
                ]
            }
        ]
    });
    
    var myChart2 = echarts.init(document.getElementById('main2'));
    myChart2.setOption({
        title : {
            text: '���ͽ��ǻ�Ա�ֲ�ͼ',
            //subtext: '��������',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['�߼���ʯ��Ա','�߼�VIP��Ա','�м�VIP��Ա','����VIP��Ա','��ͨ�ͻ�']
        },
        toolbox: {
            show : true,
            orient : 'vertical',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'�ͻ�����',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                      {value:535, name:'����VIP��Ա'},
                      {value:610, name:'�м�VIP��Ա'},
                      {value:734, name:'�߼�VIP��Ա'},
                      {value:435, name:'�߼���ʯ��Ա'},
                      {value:848, name:'��ͨ�ͻ�'}
                  ]
            }
        ]
    });
    </script>
</body>