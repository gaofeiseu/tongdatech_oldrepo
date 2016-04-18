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
		<title>total_aver_time</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<link rel="stylesheet" type="text/css" href="/xbxt/js/table_css.css">
		<script type="text/javascript">
    	var url;
		var query_time_url = "/xbxt_query_warningtime.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		
	    });
      	
      	Date.prototype.Format = function (fmt) { //
      	    var o = {
      	        "M+": this.getMonth() + 1, //月份 
      	        "d+": this.getDate(), //日 
      	        "h+": this.getHours(), //小时 
      	        "m+": this.getMinutes(), //分 
      	        "s+": this.getSeconds(), //秒 
      	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
      	        "S": this.getMilliseconds() //毫秒 
      	    };
      	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
      	    for (var k in o){
      	    	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
      	    }
      	    return fmt;
      	}
      	
      	function getLastMonthFirstDay(){
      		var d = new Date();
      		return (new Date(d.setMonth(d.getMonth()-1))).Format("yyyy-MM-dd");
      	}
      	
      	function inittable(){
      		var _query_time = $('#query_time').datebox('getValue');
      		if(_query_time&&_query_time!=''){//
      			
      		}else{
      			_query_time = getLastMonthFirstDay();
      		}
      		//console.log("inittable:"+_query_time);
      		var _data = {};
  			_data['query_time'] = _query_time;
  			$("#table_div").html("");
  			$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_getTotalAverTime.action",
        		dataType : "json",
        		data : _data,
        		success : function(msg) {
        			if(msg.success){
        				var _a = "<table class=\"gridtable\">";
            			_a += "<tr><th>区域</th><th>省份</th><th>类型</th><th>平均时限（天）</th><th>预警时限设定（天）</th></tr>";
            			var _b = msg.backParam;
            			var _c = [];
            			for(var i=0;i<_b.list_area.length;i++){
            				var _d = 0;
            				for(var j=0;j<_b.list_tat.length;j++){
            					if(_b.list_area[i]==_b.list_tat[j].area){
            						_d += 1;
            					}
            				}
            				_c.push(_d);
            			}
            			var _f = 0;
            			for(var m=0;m<_b.list_tat.length;m++){
            				for(var n=0;n<4;n++){
            					_a += "<tr>";
                				_a += getFirstCol((m*4+n+1),_b.list_area,_c);
                				if(n==0){
                					_a += "<td rowspan=\"4\">"+_b.list_tat[m].province+"</td>";
                					_a += "<td>全省平均时限</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].province_aver_time)+"</td>";
                					_a += "<td></td>";
                				}else if(n==1){
                					/* _a += "<td rowspan=\"4\">"+_b.list_tat[m].province+"</td>"; */
                					_a += "<td>省会核心区平均时限</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].center_aver_time)+"</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].center_time)+"</td>";
                				}else if(n==2){
                					_a += "<td>省内市区平均时限</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].city_aver_time)+"</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].city_time)+"</td>";
                				}else if(n==3){
                					_a += "<td>省内农村平均时限</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].countryside_aver_time)+"</td>";
                					_a += "<td>"+fun_str(_b.list_tat[m].town_time)+"</td>";
                				}
                				_a += "</tr>";
                				console.log(_b.list_tat[m]);
            				}
            			}
            			_a += "</table>";
            			$("#table_div").html(_a);
        			}
        		}
        	});
      	}
      	
      	function fun_str(_s){
      		if(_s&&_s!='null'&&_s!=undefined){
      			return _s;//(parseInt(_s)/24).toFixed(1);
      		}else{
      			return "";
      		}
      	}
      	
      	function getFirstCol(_i,_str_arr,_num_arr){
      		var _a = 0;
      		var _b = 0;
      		var _c = "";
      		for(var i=0;i<_num_arr.length;i++){
      			_b = _a;
      			_a += _num_arr[i]*4;
      			if(i==0){
      				if(_i<=_a){
      					if(_i%_a==1){
      						_c = "<td rowspan=\""+_num_arr[i]*4+"\">"+_str_arr[i]+"</td>";
      					}else{
      						_c = "";
      					}
      				}else{
      					continue;
      				}
      			}else{
      				if((_i>_b)&&(_i<=_a)){
      					if((_i-_b)%(_num_arr[i]*4)==1){
      						_c = "<td rowspan=\""+_num_arr[i]*4+"\">"+_str_arr[i]+"</td>";
      					}else{
      						_c = "";
      					}
      				}else{
      					continue;
      				}
      			}
      		}
      		return _c;
      	}
      	function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);//y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
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
        function expExcel(){
        	var excelfmurl="/xbxt_expexcel_totalavertime_report.action?query_time="+$("#query_time").val();//$.param($("#tb").datagrid("options").queryParams);
        	$('#queryfm').form('submit', {   
        	    url:excelfmurl,   
        	    onSubmit: function(){  
        	    	
        	    },
        	    success:function(result){   
					        	       
        	    }   
			});  
        }
        function doedit(){
        	var row =  $("#tb").datagrid("getSelections");
			if(row&&row.length>0){
				var _province = "";
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_province += row[i].destination;
					_sn += row[i].sn;
					if(i!=(row.length-1)){
						_province += ",";
						_sn += ",";
					}
				}
				$("#time_settings_dlg").dialog("open").dialog("setTitle","修改（"+_province+"）");
				if(row.length==1){
					$("#timeid").val(_sn);
					$("#time_settings_frm").form("load",row[0]);
				}else{
					$("#timeid").val(_sn);
					$("#time_settings_frm input[name='center_time']").val("");
					$("#time_settings_frm input[name='city_time']").val("");
					$("#time_settings_frm input[name='town_time']").val("");
				}
			}else{
				$.messager.alert("错误","请选中需要编辑的行！");
			}
		}
        function save_time_settings(){
        	if($("#time_settings_frm").form('validate')){
        		var _data = {};
      			_data['sns'] = $("#time_settings_frm input[name='timeid']").val();
      			_data['center_time'] = $("#time_settings_frm input[name='center_time']").val();
      			_data['city_time'] = $("#time_settings_frm input[name='city_time']").val();
      			_data['town_time'] = $("#time_settings_frm input[name='town_time']").val();
      			$.ajax({
            		type : "POST",
            		cache : false,
            		async:true,
            		url : "/xbxt_save_time_settings.action",
            		dataType : "json",
            		data : _data,
            		success : function(msg) {
            			if(msg.success){
            				inittable();
            				$("#tb").datagrid("reload");
            				$('#time_settings_dlg').dialog('close');
            			}else{
            				$.messager.show({title: "失败",msg: msg.msg});
            			}
            		}
            	});
        	}
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="总平均时限情况表" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'center',title:'总平均时限情况表'">
						<form id="queryfm" style="margin:0">
							查询年月：<input class="easyui-datebox" required id="query_time" name="query_time" data-options="formatter:myformatter,parser:myparser" >
							<a href="javascript:inittable();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >查询</a>
							<a href="javascript:expExcel();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-excel'" >excel导出</a>
						</form>
						<div id="table_div"></div>
					</div>
				</div>
			</div>
			<div title="预警时限设定" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm_setting" style="margin:0">
						<a href="javascript:doedit();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >修改时限设定</a>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'预警时限设定', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_time_url,pageSize:50"><!-- ,singleSelect:true -->
					<thead>
						<tr class="header">
							<th data-options="field:'destination',align:'center',width:250" >省份</th>
							<th data-options="field:'center_time',align:'center',width:250" >省会核心区预警时限</th>
							<th data-options="field:'city_time',align:'center',width:250" >省内市区预警时限</th>
							<th data-options="field:'town_time',align:'center',width:250" >省内农村预警时限</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="time_settings_dlg" class="easyui-dialog" title="time_settings_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#time_settings_buttons',closed:true" style="width:370px;height:210px;padding:10px">
        	<form id="time_settings_frm" style="margin:0">
        		<input type="hidden" id="timeid" name="timeid" />
				省会核心区预警时限<input type="text" name="center_time" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
				<br/>
				省内市区预警时限&nbsp;&nbsp;&nbsp;<input type="text" name="city_time" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
				<br/>
				省内农村预警时限&nbsp;&nbsp;&nbsp;<input type="text" name="town_time" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			</form>
    	</div>
    	<div id="time_settings_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#time_settings_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="save_time_settings();">确认</a>
    	</div>
	</body>
</html>
<script type="text/javascript">
setTimeout(function () { 
	inittable();
}, 500);
</script>