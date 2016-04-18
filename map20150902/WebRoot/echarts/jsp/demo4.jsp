<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- 饼图  -->
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
            text: '月星花园会员分布图',
            //subtext: '测试数据',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['高级钻石会员','高级VIP会员','中级VIP会员','初级VIP会员','普通客户']
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
                name:'客户类型',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'初级VIP会员'},
                    {value:310, name:'中级VIP会员'},
                    {value:234, name:'高级VIP会员'},
                    {value:135, name:'高级钻石会员'},
                    {value:1548, name:'普通客户'}
                ]
            }
        ]
    });
    
    var myChart2 = echarts.init(document.getElementById('main2'));
    myChart2.setOption({
        title : {
            text: '世纪锦城会员分布图',
            //subtext: '测试数据',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['高级钻石会员','高级VIP会员','中级VIP会员','初级VIP会员','普通客户']
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
                name:'客户类型',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                      {value:535, name:'初级VIP会员'},
                      {value:610, name:'中级VIP会员'},
                      {value:734, name:'高级VIP会员'},
                      {value:435, name:'高级钻石会员'},
                      {value:848, name:'普通客户'}
                  ]
            }
        ]
    });
    </script>
</body>