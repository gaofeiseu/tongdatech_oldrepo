<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- ©��ͼ  -->
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
 		            text: '���ǻ�԰�ͻ��������',
 		            subtext: '��������'
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
 		            data : ['�ʼ�ҵ��','Ʊ��ҵ��','��ҵ����չ','�Ե�ҵ��','�߶��û�ҵ��']
 		        },
 		        calculable : true,
 		        series : [
 		            {
 		                name:'����Ŀ��',
 		                type:'funnel',
 		                x: '10%',
 		                width: '80%',
 		                itemStyle: {
 		                    normal: {
 		                        label: {
 		                            formatter: '{b}����Ŀ��'
 		                        },
 		                        labelLine: {
 		                            show : false
 		                        }
 		                    },
 		                    emphasis: {
 		                        label: {
 		                            position:'inside',
 		                            formatter: '{b}����Ŀ��: {c}%'
 		                        }
 		                    }
 		                },
 		                data:[
 		                    {value:60, name:'�߶��û�ҵ��'},
 		                    {value:40, name:'�Ե�ҵ��'},
 		                    {value:20, name:'��ҵ����չ'},
 		                    {value:80, name:'Ʊ��ҵ��'},
 		                    {value:100, name:'�ʼ�ҵ��'}
 		                ]
 		            },
 		            {
 		                name:'��ǰ�׶����',
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
 		                            formatter: '{b}��ǰ�׶���� : {c}%'
 		                        }
 		                    }
 		                },
 		                data:[
 		                    {value:30, name:'�߶��û�ҵ��'},
 		                    {value:10, name:'�Ե�ҵ��'},
 		                    {value:5, name:'��ҵ����չ'},
 		                    {value:50, name:'Ʊ��ҵ��'},
 		                    {value:80, name:'�ʼ�ҵ��'}
 		                ]
 		            }
 		        ]
 		    };
    myChart.setOption(option);
    </script>
</body>