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
    <div id="main" style=" width:100%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div id="main1" style="width:100%;height:400px;border:1px solid #ccc;padding:10px;overflow: hidden"></div>
    <div>
    <!-- ECharts单文件引入 -->
    <script src="/echarts/js/echarts-plain-map.js" charset="UTF-8"></script>
    <script type="text/javascript">
 	// Step:3 echarts & zrender as a Global Interface by the echarts-plain.js.
    // Step:3 echarts和zrender被echarts-plain.js写入为全局接口
    var myChart = echarts.init(document.getElementById('main'));
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


    var myChart1 = echarts.init(document.getElementById('main1'));
    
    myChart1.showLoading({
        text : effect[effectIndex],
        effect : effect[effectIndex],
        textStyle : {
            fontSize : 20
        }
    });
    $(document).ready(function(){
    	getChartData();
	   });	
 
    
    function getChartData() {  
    
          //通过Ajax获取数据  
        $.ajax({  
            url : "/echarts_demo_zhanbi.action",  
			type : "POST",
			cache : false,
			async:true,
			dataType : "json",
			success : function(msg) {

		         
                	var legend=msg.data1.legend;
                	var xAxis=msg.data1.xAxis;
                	var series=msg.data1.series;



                	var legend1=msg.data2.legend;
                	var xAxis1=msg.data2.xAxis;
                	var series1=msg.data2.series;
                	  //定义图表options  
                    var options = {
                  
                            tooltip : {
                        trigger: 'axis'//触发模式   axis是垂直触发式，item是点触发模式
                    },
                    legend: {

                    
                        data:legend//标识种类名称
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
                    xAxis : xAxis,
                    yAxis : [
                        {
                            type : 'value',
                            splitArea : {show : true}
                        }
                    ],
                    series :  series
                };

                  myChart.hideLoading();  
                  myChart.setOption(options); //先把可选项注入myChart中  







                  var options1 = {
                          
                          tooltip : {
                      trigger: 'axis'//触发模式   axis是垂直触发式，item是点触发模式
                  },
                  legend: {

                  
                      data:legend1//标识种类名称
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
                  xAxis : xAxis1,
                  yAxis : [
                      {
                          type : 'value',
                          splitArea : {show : true}
                      }
                  ],
                  series :  series1
              };

                myChart1.hideLoading();  
                myChart1.setOption(options1); //先把可选项注入myChart中  
               
                    
              //    options.legend.data = result.legend;  
              //    options.xAxis[0].data = result.category;  
              //    options.series[0].data = result.series[0].data;  

                //  myChart.hideLoading();  
                 // myChart.setOption(options);  
                
            } 
        });  
    }  

 
    </script>
</body>