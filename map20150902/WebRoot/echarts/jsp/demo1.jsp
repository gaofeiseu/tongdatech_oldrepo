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
    <!-- ΪECharts׼��һ���߱���С����ߣ���Dom -->
    <div>
    <div id="main" style=" float: left;width:45%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div id="mainMap" style="width:45%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div>
    <!-- ECharts���ļ����� -->
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
 	// Step:3 echarts & zrender as a Global Interface by the echarts-plain.js.
    // Step:3 echarts��zrender��echarts-plain.jsд��Ϊȫ�ֽӿ�
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
            trigger: 'axis'//����ģʽ   axis�Ǵ�ֱ����ʽ��item�ǵ㴥��ģʽ
        },
        legend: {
        	//padding:[20,20,20,20],--------����ľ�������--�ֱ�����������
        	//x:'left',------ˮƽ����λ��
        	//y:'top',------��ֱ����λ��
        	//orient:'vertical',------'horizontal' | 'vertical'
        	//backgroundColor:'rgba(100,200,100,200)',//-----ͼ��������ɫ��Ĭ��͸��
        	//itemGap:50,//--------����item֮��ļ������λpx��Ĭ��Ϊ10
            data:['������','��ˮ��']//��ʶ��������
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
                data : ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��']
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
                name:'������',
                type:'bar',
                data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
            },
            {
                name:'��ˮ��',
                type:'bar',
                data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            }
        ]
    });
    
    // --- ��ͼ ---
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
                name: '�й�',
                type: 'map',
                mapType: 'china',
                selectedMode : 'multiple',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[
                    {name:'�㶫',selected:true}
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