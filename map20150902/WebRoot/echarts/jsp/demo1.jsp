<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>ECharts</title>
    <xl:resource></xl:resource>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div>
    <div id="main" style=" float: left;width:45%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div id="mainMap" style="width:45%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div>
    <!-- ECharts单文件引入 -->
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
 	// Step:3 echarts & zrender as a Global Interface by the echarts-plain.js.
    // Step:3 echarts和zrender被echarts-plain.js写入为全局接口
    var myChart = echarts.init(document.getElementById('main'));
 	
 	
 	
    var loadingTicket;
    var effectIndex = -1;
    var effect = ['spin' , 'bar' , 'ring' , 'whirling' , 'dynamicLine' , 'bubble'];
    effectIndex = ++effectIndex % effect.length;
    myChart.showLoading({
        text : effect[effectIndex],
        effect : effect[effectIndex],
        textStyle : {
            fontSize : 20
        }
    });
 	
    myChart.setOption({
        tooltip : {
            trigger: 'axis'//触发模式   axis是垂直触发式，item是点触发模式
        },
        legend: {
        	//padding:[20,20,20,20],--------标题的距离设置--分别是上右下左
        	//x:'left',------水平安放位置
        	//y:'top',------垂直安放位置
        	//orient:'vertical',------'horizontal' | 'vertical'
        	//backgroundColor:'rgba(100,200,100,200)',//-----图例背景颜色，默认透明
        	//itemGap:50,//--------各个item之间的间隔，单位px，默认为10
            data:['蒸发量','降水量']//标识种类名称
        },
        toolbox: {
            show : true,
            orient : 'vertical',//'horizontal' | 'vertical'
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitArea : {show : true}
          
            }
        ],
        series : [
            {
                name:'蒸发量',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name:'降水量',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            }
        ]
    });
    
    // --- 地图 ---
    var myChart2 = echarts.init(document.getElementById('mainMap'));
    
    myChart2.showLoading({
        text : effect[effectIndex],
        effect : effect[effectIndex],
        textStyle : {
            fontSize : 20
        }
    });
    
    myChart2.setOption({
        tooltip : {
            trigger: 'item',
            formatter: '{b}'
        },
        series : [
            {
                name: '中国',
                type: 'map',
                mapType: 'china',
                selectedMode : 'multiple',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[
                    {name:'广东',selected:true}
                ]
            }
        ]
    });

    
    clearTimeout(loadingTicket);
    loadingTicket = setTimeout(function (){
        myChart.hideLoading();
        myChart.setOption(option);
    },2200);
    loadingTicket = setTimeout(function (){
        myChart2.hideLoading();
        myChart2.setOption(option);
    },2200);
    
    </script>
</body>