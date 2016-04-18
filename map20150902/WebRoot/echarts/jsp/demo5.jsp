<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html>
<!-- 仪表盘  -->
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
</head>
<body>
    <div id="main" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main2" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main3" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main4" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main5" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main6" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main7" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <div id="main8" style="height:300px;float:left;width:300px;border:1px solid #ccc;padding:10px;"></div>
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    var timeTicket;
    var option = {
    		title : {
                text: '月星花园客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart.setOption(option);
    clearInterval(timeTicket);
    timeTicket = setInterval(function (){
        option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart.setOption(option, true);
    },2000);
    
    
    var myChart2 = echarts.init(document.getElementById('main2'));
    var timeTicket2;
    var option2 = {
    		title : {
                text: '天润城客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart2.setOption(option2);
    clearInterval(timeTicket2);
    timeTicket2 = setInterval(function (){
        option2.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart2.setOption(option2, true);
    },2000);
    
    var myChart3 = echarts.init(document.getElementById('main3'));
    var timeTicket3;
    var option3 = {
    		title : {
                text: '世纪锦城客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart3.setOption(option3);
    clearInterval(timeTicket3);
    timeTicket3 = setInterval(function (){
        option3.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart3.setOption(option3, true);
    },2000);
    
    var myChart4 = echarts.init(document.getElementById('main4'));
    var timeTicket4;
    var option4 = {
    		title : {
                text: '盛大花园客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart4.setOption(option4);
    clearInterval(timeTicket4);
    timeTicket4 = setInterval(function (){
        option4.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart4.setOption(option4, true);
    },2000);
    
    var myChart5 = echarts.init(document.getElementById('main5'));
    var timeTicket5;
    var option5 = {
    		title : {
                text: '江岸锦城客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart5.setOption(option5);
    clearInterval(timeTicket5);
    timeTicket5 = setInterval(function (){
        option5.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart5.setOption(option5, true);
    },2000);
    
    var myChart6 = echarts.init(document.getElementById('main6'));
    var timeTicket6;
    var option6 = {
    		title : {
                text: '万科金域客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart6.setOption(option6);
    clearInterval(timeTicket6);
    timeTicket6 = setInterval(function (){
        option6.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart6.setOption(option6, true);
    },2000);
    
    var myChart7 = echarts.init(document.getElementById('main7'));
    var timeTicket7;
    var option7 = {
    		title : {
                text: '荣耀九龙客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart7.setOption(option7);
    clearInterval(timeTicket7);
    timeTicket7 = setInterval(function (){
        option7.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart7.setOption(option7, true);
    },2000);
    
    var myChart8 = echarts.init(document.getElementById('main8'));
    var timeTicket8;
    var option8 = {
    		title : {
                text: '凤凰小镇客户渗透率',
                //subtext: '测试数据',
                x:'center'
            },
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                show : true,
                orient : 'vertical',
                feature : {
                    mark : {show: true},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'业务指标',
                    type:'gauge',
                    detail : {formatter:'{value}%'},
                    data:[{value: 50, name: '渗透率'}]
                }
            ]
        };
    myChart8.setOption(option8);
    clearInterval(timeTicket8);
    timeTicket8 = setInterval(function (){
        option8.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
        myChart8.setOption(option8, true);
    },2000);
    </script>
</body>