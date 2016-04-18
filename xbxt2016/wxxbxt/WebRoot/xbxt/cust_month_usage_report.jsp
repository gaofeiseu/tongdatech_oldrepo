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
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript" charset="UTF-8" src="/xbxt/js/echarts.min.js"></script>
		<script type="text/javascript" charset="UTF-8" src="/xbxt/js/china.js"></script>
		<script type="text/javascript">
    	var url;
		var query_url = "/xbxt_query.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		loadBigCustSearch(".BigCustCombo");
	    });	
		function doquery() {
			if($("#queryfm").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm").form2json();
				$("#tb").datagrid("load", fmdata);
				draw(fmdata);
			}
		}
		function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
        function draw(_fmdata){
        	$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_getTable.action",
        		dataType : "json",
        		data : _fmdata,
        		success : function(msg) {
        			try {
        				initChart1(msg);
        				init_myChart_line(msg);
        				initChart_sendnum(msg);
        				initChart_fee(msg);
        				initChart_aver(msg);
        				initChart_total_aver(msg);
        			}
        			catch (exception) {
        				console.log(exception);
        			}
        		}
        	});
        }
        function initChart_fee(msg){
        	var _aver_data = [];
        	var _double_aver = [];
        	$.each(msg.backParam,function(n,value){
        		var _aver_map = {};
        		if(value.destination.indexOf('内蒙')>-1){
        			_aver_map['name']='内蒙古';
        		}else if(value.destination.indexOf('黑龙')>-1){
        			_aver_map['name']='黑龙江';
        		}else{
        			_aver_map['name']=value.destination;
        		}
        		_aver_map['value']=value.fee;
        		_double_aver.push(value.fee);
        		_aver_data.push(_aver_map);
        	});
        	var min1 = findMIN(_double_aver);
        	var max1 = findMAX(_double_aver);
        	myChart_fee.setOption({
        	    title : {
        	        text: '实收资费',
        	        //subtext: '纯属虚构',
        	        left: 'center'
        	    },
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data:['资费']
        	    },
        	    visualMap: {
        	        min: min1,
        	        max: max1,
        	        left: 'left',
        	        top: 'bottom',
        	        text:['高','低'],           // 文本，默认为数值文本
        	        calculable : true,
        	        color: ['orangered','yellow','lightskyblue']
        	    },
        	    toolbox: {
        	        show: true,
        	        orient : 'vertical',
        	        left: 'right',
        	        top: 'center',
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    series : [
        	        {
        	            name: '资费',
        	            type: 'map',
        	            mapType: 'china',
        	            roam: false,
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:_aver_data
        	        }
        		]
        	});
        }
        function initChart_aver(msg){
        	var _aver_data = [];
        	var _double_aver = [];
        	$.each(msg.backParam,function(n,value){
        		var _aver_map = {};
        		if(value.destination.indexOf('内蒙')>-1){
        			_aver_map['name']='内蒙古';
        		}else if(value.destination.indexOf('黑龙')>-1){
        			_aver_map['name']='黑龙江';
        		}else{
        			_aver_map['name']=value.destination;
        		}
        		_aver_map['value']=value.mail_aver_time;
        		_double_aver.push(value.mail_aver_time);
        		_aver_data.push(_aver_map);
        	});
        	var min1 = findMIN(_double_aver);
        	var max1 = findMAX(_double_aver);
        	myChart_aver.setOption({
        	    title : {
        	        text: '邮件平均时限',
        	        //subtext: '纯属虚构',
        	        left: 'center'
        	    },
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data:['邮件平均时限']
        	    },
        	    visualMap: {
        	        min: min1,
        	        max: max1,
        	        left: 'left',
        	        top: 'bottom',
        	        text:['高','低'],           // 文本，默认为数值文本
        	        calculable : true,
        	        color: ['orangered','yellow','lightskyblue']
        	    },
        	    toolbox: {
        	        show: true,
        	        orient : 'vertical',
        	        left: 'right',
        	        top: 'center',
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    series : [
        	        {
        	            name: '邮件平均时限',
        	            type: 'map',
        	            mapType: 'china',
        	            roam: false,
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:_aver_data
        	        }
        		]
        	});
        }
        function initChart_total_aver(msg){
        	var _aver_data = [];
        	var _double_aver = [];
        	$.each(msg.backParam,function(n,value){
        		var _aver_map = {};
        		if(value.destination.indexOf('内蒙')>-1){
        			_aver_map['name']='内蒙古';
        		}else if(value.destination.indexOf('黑龙')>-1){
        			_aver_map['name']='黑龙江';
        		}else{
        			_aver_map['name']=value.destination;
        		}
        		_aver_map['value']=value.total_aver_time;
        		_double_aver.push(value.total_aver_time);
        		_aver_data.push(_aver_map);
        	});
        	var min1 = findMIN(_double_aver);
        	var max1 = findMAX(_double_aver);
        	myChart_total_aver.setOption({
        	    title : {
        	        text: '总平均时限',
        	        //subtext: '纯属虚构',
        	        left: 'center'
        	    },
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data:['总平均时限']
        	    },
        	    visualMap: {
        	        min: min1,
        	        max: max1,
        	        left: 'left',
        	        top: 'bottom',
        	        text:['高','低'],           // 文本，默认为数值文本
        	        calculable : true,
        	        color: ['orangered','yellow','lightskyblue']
        	    },
        	    toolbox: {
        	        show: true,
        	        orient : 'vertical',
        	        left: 'right',
        	        top: 'center',
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    series : [
        	        {
        	            name: '总平均时限',
        	            type: 'map',
        	            mapType: 'china',
        	            roam: false,
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:_aver_data
        	        }
        		]
        	});
        }
        function initChart_sendnum(msg){
        	var _aver_data = [];
        	var _double_aver = [];
        	$.each(msg.backParam,function(n,value){
        		var _aver_map = {};
        		if(value.destination.indexOf('内蒙')>-1){
        			_aver_map['name']='内蒙古';
        		}else if(value.destination.indexOf('黑龙')>-1){
        			_aver_map['name']='黑龙江';
        		}else{
        			_aver_map['name']=value.destination;
        		}
        		_aver_map['value']=value.send_num;
        		_double_aver.push(value.send_num);
        		_aver_data.push(_aver_map);
        	});
        	var min1 = findMIN(_double_aver);
        	var max1 = findMAX(_double_aver);
        	myChart_sendnum.setOption({
        	    title : {
        	        text: '发件量',
        	        //subtext: '纯属虚构',
        	        left: 'center'
        	    },
        	    tooltip : {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data:['发件量']
        	    },
        	    visualMap: {
        	        min: min1,
        	        max: max1,
        	        left: 'left',
        	        top: 'bottom',
        	        text:['高','低'],           // 文本，默认为数值文本
        	        calculable : true,
        	        color: ['orangered','yellow','lightskyblue']
        	    },
        	    toolbox: {
        	        show: true,
        	        orient : 'vertical',
        	        left: 'right',
        	        top: 'center',
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    series : [
        	        {
        	            name: '发件量',
        	            type: 'map',
        	            mapType: 'china',
        	            roam: false,
        	            itemStyle:{
        	                normal:{label:{show:true}},
        	                emphasis:{label:{show:true}}
        	            },
        	            data:_aver_data
        	        }
        		]
        	});
        }
        function init_myChart_line(msg){
        	if(msg.success){
        		var _a = [];
				var _d = [];
				var _e = [];
				for(var i=0;i<msg.backParam.length;i++){
					_a.push(msg.backParam[i].destination);
					_d.push(msg.backParam[i].mail_aver_time);
					_e.push(msg.backParam[i].total_aver_time);
				}
				var option = {
					    title: {
					        text: '各省邮件平均时限和总平均时限'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['邮件平均时限','总平均时限']
					    },
					    toolbox: {
		        	        /* 
		        	        show: true,
		        	        orient : 'vertical',
		        	        left: 'right',
		        	        top: 'center',
		        	        */
		        	        feature : {
		        	            mark : {show: true},
		        	            dataView : {show: true, readOnly: false},
		        	            restore : {show: true},
		        	            saveAsImage : {show: true}
		        	        },
		        	        show : true,
		        	        feature : {
		        	            mark : {show: true},
		        	            dataView : {show: true, readOnly: false},
		        	            magicType : {show: true, type: ['line', 'bar']},
		        	            restore : {show: true},
		        	            saveAsImage : {show: true}
		        	        }
		        	    },
					    /* grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    }, */
					    xAxis : [
				        			{
				        				type : 'category',
				        		 		//position: 'bottom',
				        				data : _a,
				        				//show : true,
				        		   		boundaryGap : false,//true,
				        		   		axisLabel:{  
			                         		interval:0 ,  
			                      			formatter:function(val){  
			                       				return val.split("").join("\n");  
			                    			}  
			                        	}
				        		  	}
				        		],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'邮件平均时限',
					            type:'line',
					            //stack: '总量',
					            //areaStyle: {normal: {}},
					            data:_d,
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        },
					        {
					            name:'总平均时限',
					            type:'line',
					            //stack: '总量',
					            //areaStyle: {normal: {}},
					            data:_e,
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        }
					    ]
					};
				myChart_line.setOption(option);
			}
        }
        function initChart1(msg){
        	if(msg.success){
				var _a = [];
				var _b = [];
				var _c = [];
				/* var _d = [];
				var _e = []; */
				for(var i=0;i<msg.backParam.length;i++){
					_a.push(msg.backParam[i].destination);
					_b.push(msg.backParam[i].send_num);
					_c.push(msg.backParam[i].fee);
					/* _d.push(msg.backParam[i].mail_aver_time);
					_e.push(msg.backParam[i].total_aver_time); */
				}
				var option = {
					title: {
					        text: '各省发件量和实际资费'
					    },
	        		tooltip : {
	        			trigger: 'axis',
	        			axisPointer : {// 坐标轴指示器，坐标轴触发有效
	        				type : 'shadow'// 默认为直线，可选为：'line' | 'shadow'
	        			}
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
	        			data:['发件量','资费']
	        		},
	        		grid: {
	        			left: '3%',
	        			right: '3%',
	        		        bottom: '3%',
	        		        containLabel: true
	        		    },
	        		xAxis : [
	        			{
	        				type : 'category',
	        		 		position: 'bottom',
	        				data : _a,
	        				show : true,
	        		   		boundaryGap : true,
	        		   		axisLabel:{  
                         		interval:0 ,  
                      			formatter:function(val){  
                       				return val.split("").join("\n");  
                    			}  
                        	}
	        		  	}
	        		],
	        		yAxis : [
	        			{
	        				type : 'value'
	        			}
	        		],
	        		series : [
	        			{
	        		    	name:'发件量',
	        		    	type:'bar',
	        		    	data:_b,
	        		    	markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            }
	        		 	},
	        		 	{
	        		 		name:'资费',
	        		  		type:'bar',
	        		   		data:_c,
	        		   		markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            }
	        		  	}
	        		]
	        	};
				myChart.setOption(option);
			}
        }
        
        function findMAX(_arr){
        	var _max = _arr[0];
        	for(var i=0;i<_arr.length;i++){
        		if(_max<_arr[i]){
        			_max=_arr[i];
        		}
        	}
        	return _max;
        }
        
        function findMIN(_arr){
        	var _min = _arr[0];
        	for(var i=0;i<_arr.length;i++){
        		if(_min>_arr[i]){
        			_min = _arr[i];
        		}
        	}
        	return _min;
        }
        
        function loadBigCustSearch(selector){
        	if($(selector).is("input")){ grid=selector;
    		}else grid=$(selector).find("#search_area");
    		
    		$(grid).combogrid({  
    			panelWidth: 600,
    			mode: 'remote',
    			multiple:false,
                idField: 'cust_no',  
                textField: 'cust_name',
                url: '/xbxt_cust_search.action',
                columns: [[
                    {field:'cust_no',title:'协议客户号',width:200},      
                    {field:'cust_name',title:'客户名称',width:250},
                    {field:'cust_brch',title:'客户归属部门',width:150}
                ]],  
                fitColumns: true,
                onLoadSuccess:function(data){
                	 if($(grid).attr("text")!=undefined){
                		 $(grid).combogrid("setText",$(grid).attr("text")); 
                	 }
                }
         	});
        }
        function expExcel(){
        	var excelfmurl="/xbxt_expexcel_cmur_report.action?"+$.param($("#tb").datagrid("options").queryParams);
        	$('#queryfm').form('submit', {   
        	    url:excelfmurl,   
        	    onSubmit: function(){  
        	    	
        	    },
        	    success:function(result){   
					        	       
        	    }   
			});  
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="客户月度用邮报告" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
						客户名称：<input type="text" name="cust_no" class="BigCustCombo" value=""  data-options="required:true,missingMessage:'必选'"/>
			 			<!-- 协议客户号：<input class="easyui-validatebox textbox" name="cust_no" style="width:150px" data-options="required:true" value="32020000834002" /> -->
						查询年月：<input class="easyui-datebox" required name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >查询</a>
						<a href="javascript:expExcel();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-excel'" >excel导出</a>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'客户月度用邮报告', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true">
					<thead>
						<tr class="header">
							<th data-options="field:'destination',align:'center',width:100" >路向</th>
							<th data-options="field:'send_num',align:'center',width:150" >发件量</th>
							<th data-options="field:'fee',align:'center',width:150" >实收资费</th>
							<th data-options="field:'mail_aver_time',align:'center',width:100" >邮件平均时限（天）</th>
							<th data-options="field:'total_aver_time',align:'center',width:100" >总平均时限（天）</th>
							<th data-options="field:'discount_persent',align:'center',width:100" >折扣率(百分之)</th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="发件量和实际资费柱状图" style="padding:10px">
				<div id="div_report" style="width: 100%;height: 500px;"></div>
			</div>
			<div title="邮件平均时限和总平均时限折线图" style="padding:10px">
				<div id="div_line_report" style="width: 100%;height: 500px;"></div>
			</div>
			<div title="地图-发件量" style="padding:10px">
				<div id="div_report_sendnum" style="width: 100%;height: 500px;"></div>
			</div>
			<div title="地图-资费" style="padding:10px">
				<div id="div_report_fee" style="width: 100%;height: 500px;"></div>
			</div>
			<div title="地图-邮件平均时限" style="padding:10px">
				<div id="div_report_aver" style="width: 100%;height: 500px;"></div>
			</div>
			<div title="地图-总平均时限" style="padding:10px">
				<div id="div_report_total_aver" style="width: 100%;height: 500px;"></div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('div_report'));
	var myChart_line = echarts.init(document.getElementById('div_line_report'));
	var myChart_sendnum = echarts.init(document.getElementById('div_report_sendnum'));
	var myChart_fee = echarts.init(document.getElementById('div_report_fee'));
	var myChart_aver = echarts.init(document.getElementById('div_report_aver'));
	var myChart_total_aver = echarts.init(document.getElementById('div_report_total_aver'));
</script>