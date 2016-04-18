<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- 漏斗图  -->
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
</head>
<body>
    <div id="main" style="height:550px;border:1px solid #ccc;padding:10px;"></div>
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
 	var option = {
 		    color : [
 		            'rgba(255, 69, 0, 0.5)',
 		            'rgba(255, 150, 0, 0.5)',
 		            'rgba(255, 200, 0, 0.5)',
 		            'rgba(155, 200, 50, 0.5)',
 		            'rgba(55, 200, 100, 0.5)'
 		        ],
 		        title : {
 		            text: '月星花园客户覆盖情况',
 		            subtext: '测试数据'
 		        },
 		        tooltip : {
 		            trigger: 'item',
 		            formatter: "{a} <br/>{b} : {c}%"
 		        },
 		        toolbox: {
 		            show : true,
 		            feature : {
 		                mark : {show: true},
 		                dataView : {show: true, readOnly: false},
 		                restore : {show: true},
 		                saveAsImage : {show: true}
 		            }
 		        },
 		        legend: {
 		            data : ['邮件业务','票务业务','新业务扩展','试点业务','高端用户业务']
 		        },
 		        calculable : true,
 		        series : [
 		            {
 		                name:'最终目标',
 		                type:'funnel',
 		                x: '10%',
 		                width: '80%',
 		                itemStyle: {
 		                    normal: {
 		                        label: {
 		                            formatter: '{b}最终目标'
 		                        },
 		                        labelLine: {
 		                            show : false
 		                        }
 		                    },
 		                    emphasis: {
 		                        label: {
 		                            position:'inside',
 		                            formatter: '{b}最终目标: {c}%'
 		                        }
 		                    }
 		                },
 		                data:[
 		                    {value:60, name:'高端用户业务'},
 		                    {value:40, name:'试点业务'},
 		                    {value:20, name:'新业务扩展'},
 		                    {value:80, name:'票务业务'},
 		                    {value:100, name:'邮件业务'}
 		                ]
 		            },
 		            {
 		                name:'当前阶段情况',
 		                type:'funnel',
 		                x: '10%',
 		                width: '80%',
 		                maxSize: '80%',
 		                itemStyle: {
 		                    normal: {
 		                        borderColor: '#fff',
 		                        borderWidth: 2,
 		                        label: {
 		                            position: 'inside',
 		                            formatter: '{c}%',
 		                            textStyle: {
 		                                color: '#fff'
 		                            }
 		                        }
 		                    },
 		                    emphasis: {
 		                        label: {
 		                            position:'inside',
 		                            formatter: '{b}当前阶段情况 : {c}%'
 		                        }
 		                    }
 		                },
 		                data:[
 		                    {value:30, name:'高端用户业务'},
 		                    {value:10, name:'试点业务'},
 		                    {value:5, name:'新业务扩展'},
 		                    {value:50, name:'票务业务'},
 		                    {value:80, name:'邮件业务'}
 		                ]
 		            }
 		        ]
 		    };
    myChart.setOption(option);
    </script>
</body>