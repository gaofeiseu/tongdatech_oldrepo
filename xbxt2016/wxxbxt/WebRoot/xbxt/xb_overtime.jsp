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
		<title>overtime_page</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
    	var query_url1 = "/xbxt_query_overtime1.action";
		var query_url2 = "/xbxt_query_overtime2.action";//    /xbxt_query_overtime2.action
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		loadBigCustSearch(".BigCustCombo");
	    });
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
        function prehandle1(){
        	var row =  $("#tb1").datagrid("getSelections");
			if(row&&row.length>0){
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_sn += row[i].mailno;
					if(i!=(row.length-1)){
						_sn += ",";
					}
				}
				$("#hidden_div1").show();
				$("#hidden_div2").show();
				$("#pop_dlg_frm input[name='handle_comments']").removeAttr("readonly");
				if(row.length==1){
					var _data = {};
	      			_data['mailno'] = row[0].mailno;
	      			$.ajax({
	            		type : "POST",
	            		cache : false,
	            		async:true,
	            		url : "/xbxt_query_overtime_mail_detail.action",
	            		dataType : "json",
	            		data : _data,
	            		success : function(result) {
	            			if(result.success){
	            				$("#pop_dlg_frm input[name='mailnos']").val(_sn);
	            				$("#pop_dlg_frm").form("load",result.backParam);
	            				$("#pop_dlg").dialog("open").dialog("setTitle","修改");
	            			}else{
	            				$.messager.show({title: "失败",msg: result.msg});
	            			}
	            		}
	            	});
				}else{
					$("#pop_dlg_frm2 input[name='mailnos']").val(_sn);
					$("#pop_dlg2").dialog("open").dialog("setTitle","修改");
				}
			}else{
				$.messager.alert("错误","请选中需要编辑的行！");
			}
		}
        function prehandle2(){
        	var row =  $("#tb2").datagrid("getSelections");
			if(row&&row.length>0){
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_sn += row[i].mailno;
					if(i!=(row.length-1)){
						_sn += ",";
					}
				}
				$("#hidden_div1").hide();
				$("#hidden_div2").hide();
				$("#pop_dlg_frm input[name='handle_comments']").attr("readonly","readonly");
				if(row.length==1){
					var _data = {};
	      			_data['mailno'] = row[0].mailno;
	      			$.ajax({
	            		type : "POST",
	            		cache : false,
	            		async:true,
	            		url : "/xbxt_query_overtime_mail_detail.action",
	            		dataType : "json",
	            		data : _data,
	            		success : function(result) {
	            			if(result.success){
	            				$("#pop_dlg_frm input[name='mailnos']").val(_sn);
	            				$("#pop_dlg_frm").form("load",result.backParam);
	            				$("#pop_dlg").dialog("open").dialog("setTitle","修改");
	            			}else{
	            				$.messager.show({title: "失败",msg: result.msg});
	            			}
	            		}
	            	});
				}else{
					$("#pop_dlg_frm2 input[name='mailnos']").val(_sn);
					$("#pop_dlg2").dialog("open").dialog("setTitle","修改");
				}
			}else{
				$.messager.alert("错误","请选中需要编辑的行！");
			}
		}
        function save_handle_comments(){
        	if($("#pop_dlg_frm").form('validate')){
        		var _data = {};
      			_data['mailnos'] = $("#pop_dlg_frm input[name='mailnos']").val();
      			_data['handle_comments'] = $("#pop_dlg_frm input[name='handle_comments']").val();
      			$.ajax({
            		type : "POST",
            		cache : false,
            		async:true,
            		url : "/xbxt_save_handle_comments.action",
            		dataType : "json",
            		data : _data,
            		success : function(msg) {
            			if(msg.success){
            				$("#tb1").datagrid("reload");
            				$("#pop_dlg").dialog('close');
            			}else{
            				$.messager.show({title: "失败",msg: msg.msg});
            			}
            		}
            	});
        	}
        }
        function save_handle_comments2(){
        	if($("#pop_dlg_frm2").form('validate')){
        		var _data = {};
      			_data['mailnos'] = $("#pop_dlg_frm2 input[name='mailnos']").val();
      			_data['handle_comments'] = $("#pop_dlg_frm2 input[name='handle_comments']").val();
      			$.ajax({
            		type : "POST",
            		cache : false,
            		async:true,
            		url : "/xbxt_save_handle_comments.action",
            		dataType : "json",
            		data : _data,
            		success : function(msg) {
            			if(msg.success){
            				$("#tb1").datagrid("reload");
            				$("#pop_dlg2").dialog('close');
            			}else{
            				$.messager.show({title: "失败",msg: msg.msg});
            			}
            		}
            	});
        	}
        }
        function doquery() {
			if($("#queryfm1").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm1").form2json();
				$("#tb1").datagrid("load", fmdata);
			}
		}
        function doquery2() {
			if($("#queryfm2").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm2").form2json();
				$("#tb2").datagrid("load", fmdata);
			}
		}
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="超时邮件处理模块" style="padding:10px">
				<div id="toolbar1" style="padding:10px;display:none">
					<form id="queryfm1" style="margin:0">
						客户名称：<input type="text" name="cust_id" class="BigCustCombo" value="" />
						收寄时间：<input class="easyui-datebox" name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						目的地：<input class="easyui-combobox" name="area" data-options="valueField:'id',textField:'text',url:'/xbxt_getAreaComboData.action'">
						收寄局：<input class="easyui-combobox" name="sjj" data-options="valueField:'id',textField:'text',data: [{
								text: '无锡局',
								id: '21402800'
							},{
								text: '江阴局',
								id: '21446500'
							},{
								text: '宜兴局',
								id: '21426600'
							}]">
							<br/>
						<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >查询</a>
						<a href="javascript:prehandle1();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" >处理</a>
					</form>
				</div>
				<table id="tb1" class="easyui-datagrid" data-options="title:'超时邮件处理模块（本功能只查询前5000条，以提高查询效率）', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar1',
     				url:query_url1,pageSize:50"><!-- ,singleSelect:true -->
					<thead>
						<tr class="header">
							<th data-options="field:'mailno',align:'center',width:130" >邮件号</th>
							<th data-options="field:'destination',align:'center',width:300" >目的地</th>
							<th data-options="field:'sj_time',align:'center',width:150" >收寄时间</th>
							<th data-options="field:'custname',align:'center',width:180" >客户名称</th>
							<th data-options="field:'custno',align:'center',width:150" >客户代码</th>
							<th data-options="field:'if_handle',align:'center',width:100" >处理</th>
							<th data-options="field:'sjj',align:'center',width:100" >收寄局</th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="超时处理查询" style="padding:10px">
				<div id="toolbar2" style="padding:10px;display:none">
					<form id="queryfm2" style="margin:0">
						客户名称：<input type="text" name="cust_id" class="BigCustCombo" value="" />
						查询时间：<input class="easyui-datebox" name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						目的地：<input class="easyui-combobox" name="area" data-options="valueField:'id',textField:'text',url:'/xbxt_getAreaComboData.action'">
						收寄局：<input class="easyui-combobox" name="sjj" data-options="valueField:'id',textField:'text',data: [{
								text: '无锡局',
								id: '21402800'
							},{
								text: '江阴局',
								id: '21446500'
							},{
								text: '宜兴局',
								id: '21426600'
							}]">
							<br/>
						<a href="javascript:doquery2();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >查询</a>
						<a href="javascript:prehandle2();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" >查看详细信息</a>
					</form>
				</div>
				<table id="tb2" class="easyui-datagrid" data-options="title:'超时处理查询', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar2',
     				url:query_url2,pageSize:50,singleSelect:true"><!--  -->
					<thead>
						<tr class="header">
							<th data-options="field:'mailno',align:'center',width:100" >邮件号</th>
							<th data-options="field:'destination',align:'center',width:100" >目的地</th>
							<th data-options="field:'sj_time',align:'center',width:150" >收寄时间</th>
							<th data-options="field:'tt_time',align:'center',width:150" >妥投时间</th>
							<th data-options="field:'custname',align:'center',width:100" >客户名称</th>
							<th data-options="field:'custno',align:'center',width:100" >客户代码</th>
							<th data-options="field:'if_handle',align:'center',width:100" >处理</th>
							<th data-options="field:'sjj',align:'center',width:100" >收寄局</th>
							<th data-options="field:'handle_time',align:'center',width:160" >处理时间</th>
							<th data-options="field:'handle_comments',align:'center',width:100" >处理情况</th>
							<th data-options="field:'handle_user',align:'center',width:100" >处理人</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="pop_dlg" class="easyui-dialog" title="pop_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#pop_dlg_buttons',closed:true" style="width:450px;height:400px;padding:10px">
        	<form id="pop_dlg_frm" style="margin:0">
        		<input type="hidden" name="mailnos" />
        		<table>
        			<tr><td>邮件号码：</td><td><input type="text" name="mailno" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收寄日期：</td><td><input type="text" name="sjrq" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收寄时间：</td><td><input type="text" name="sjsj" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>寄件人姓名：</td><td><input type="text" name="sender_name" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>寄件人地址：</td><td><input type="text" name="sender_address" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>寄件人邮政编码：</td><td><input type="text" name="sender_postcode" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>寄件人单位名称：</td><td><input type="text" name="sender_dwmc" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>寄件人电话号码：</td><td><input type="text" name="sender_phone" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>客户号：</td><td><input type="text" name="custno" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收件人姓名：</td><td><input type="text" name="receiver_name" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收件人地址：</td><td><input type="text" name="receiver_address" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收件人邮编：</td><td><input type="text" name="receiver_postcode" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收件人单位名称：</td><td><input type="text" name="receiver_dwmc" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>收件人电话号码：</td><td><input type="text" name="receiver_phone" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>妥投日期：</td><td><input type="text" name="ttrq" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>妥投时间：</td><td><input type="text" name="ttsj" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>地区类型：</td><td><input type="text" name="area_type" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>目的地：</td><td><input type="text" name="destination" class="easyui-validatebox" style="width:200px" readonly /></td></tr>
        			<tr><td>包件处理结果：</td><td><input type="text" name="handle_comments" class="easyui-validatebox" style="width:200px"  data-options="required:true" /></td></tr>
        		</table>
			</form>
    	</div>
    	<div id="pop_dlg_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#pop_dlg').dialog('close');">取消</a>
    		<a href="#" id="hidden_div1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="save_handle_comments();">确认</a>
    	</div>
    	
    	<div id="pop_dlg2" class="easyui-dialog" title="pop_dlg2" closed="true" data-options="iconCls:'icon-edit',buttons:'#pop_dlg_buttons2',closed:true" style="width:450px;height:180px;padding:10px">
        	<form id="pop_dlg_frm2" style="margin:0">
        		<input type="hidden" name="mailnos" />
        		<table>
        			<tr><td>包件处理结果：</td><td><input type="text" name="handle_comments" class="easyui-validatebox" style="width:200px"  data-options="required:true" /></td></tr>
        		</table>
			</form>
    	</div>
    	<div id="pop_dlg_buttons2">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#pop_dlg2').dialog('close');">取消</a>
    		<a href="#" id="hidden_div2" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="save_handle_comments2();">确认</a>
    	</div>
	</body>
</html>
<script type="text/javascript">



</script>