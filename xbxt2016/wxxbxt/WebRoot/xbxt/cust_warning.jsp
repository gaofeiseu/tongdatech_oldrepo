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
		<title>cust_warning</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
    	var url;
		var query_url = "/xbxt_cust_warning_query.action";
		var query_warningwritepaper_url = "/xbxt_cust_warning_querywritepaper.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		query_param();
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
                    {field:'cust_no',title:'Э��ͻ���',width:200},      
                    {field:'cust_name',title:'�ͻ�����',width:250},
                    {field:'cust_brch',title:'�ͻ���������',width:150}
                ]],  
                fitColumns: true,
                onLoadSuccess:function(data){
                	 if($(grid).attr("text")!=undefined){
                		 $(grid).combogrid("setText",$(grid).attr("text")); 
                	 }
                }
         	});
        }
		function doquery() {
			if($("#queryfm").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm").form2json();
				$("#tb").datagrid("load", fmdata);
			}
		}
		function query_param(){
			$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_find_cust_warning_param.action",
        		dataType : "json",
        		data : {},
        		success : function(msg) {
        			try {
        				if(msg.success){
        					var a = msg.msg.split('_');
        					$("#param_sn").val(a[0]);
        					$("#param_a").val(a[1]);
        					$("#param_b").val(a[2]);
        				}
        			}
        			catch (exception) {
        				console.log(exception);
        			}
        		}
        	});
		}
		function update_param(){
			$('#update_btn').linkbutton('disable');
			fmSubmit("#warning_param_setting_form","/xbxt_update_cust_warning_param.action",
				function(result){
					query_param();
					$('#update_btn').linkbutton('enable');
	    	});
		}
		function add_writepage(){
			var rows =  $("#tb").datagrid("getSelections");
			if(rows&&rows.length>0){
				$.messager.confirm('ȷ��','ȷ����ָ���ͻ���·����ӵ�Ԥ������������Ӻ�ÿͻ���·�򽫲��ٱ�Ԥ��',function(r){
       				if (r){
       					var bigcustno_arr = [];
       					var destination_arr = [];
           				for(var i=0;i<rows.length;i++){
           					bigcustno_arr.push(rows[i].bigcustno);
           					destination_arr.push(rows[i].destination);
           				}
           				$.ajax({
           	        		type : "POST",
           	        		cache : false,
           	        		async:true,
           	        		url : "/xbxt_addinto_writepage.action",
           	        		dataType : "json",
           	        		data : {"bigcustno_arr":bigcustno_arr,"destination_arr":destination_arr},
           	        		success : function(result) {
           	        			try {
           	        				if(result.success){
           	        					$.messager.show({title: "�ɹ�",msg: result.msg});
           	        					$("#tb").datagrid("reload");
           	        					$("#tb_query_warningwritepaper").datagrid("reload");
           	        				}else{
           	        					$.messager.show({title: "ʧ��",msg: result.msg});
           	        				}
           	        			}
           	        			catch (exception) {
           	        				console.log(exception);
           	        			}
           	        		}
           	        	});
       				}
       			}); 
			}else{
				$.messager.alert("����","����ѡ����Ҫ��ӵ�Ԥ����������·��");
			}
		}
		function remove_writepage(){
			var rows =  $("#tb_query_warningwritepaper").datagrid("getSelections");
			if(rows&&rows.length>0){
				$.messager.confirm('ȷ��','ȷ����ָ���ͻ���·����ӵ�Ԥ������������Ӻ�ÿͻ���·�򽫲��ٱ�Ԥ��',function(r){
       				if (r){
       					var sn_arr = [];
           				for(var i=0;i<rows.length;i++){
           					sn_arr.push(rows[i].sn);
           				}
           				$.ajax({
           	        		type : "POST",
           	        		cache : false,
           	        		async:true,
           	        		url : "/xbxt_removefrom_writepage.action",
           	        		dataType : "json",
           	        		data : {"sn_arr":sn_arr},
           	        		success : function(result) {
           	        			try {
           	        				if(result.success){
           	        					$.messager.show({title: "�ɹ�",msg: result.msg});
           	        					$("#tb").datagrid("reload");
           	        					$("#tb_query_warningwritepaper").datagrid("reload");
           	        				}else{
           	        					$.messager.show({title: "ʧ��",msg: result.msg});
           	        				}
           	        			}
           	        			catch (exception) {
           	        				console.log(exception);
           	        			}
           	        		}
           	        	});
       				}
       			}); 
			}else{
				$.messager.alert("����","����ѡ����Ҫ�Ƴ����������С�");
			}
		}
		function open_writepage_comments_dlg(){
			var rows =  $("#tb_query_warningwritepaper").datagrid("getSelections");
			if(rows&&rows.length>0){
				$("#wp_comments_frm textarea[name='comments']").val("");
				if(rows.length==1){
					$("#wp_comments_dlg").dialog("open").dialog("setTitle","�޸Ļ����ñ�ע");
					$("#wp_comments_frm textarea[name='comments']").val(rows[0].comments);
					$("#wp_comments_frm input[name='sns']").val(rows[0].sn);
				}else{
					var sns = "";
					for(var i=0;i<rows.length;i++){
						if(i==0){
							sns += rows[i].sn;
						}else{
							sns += "," + rows[i].sn;
						}
					}
					$("#wp_comments_dlg").dialog("open").dialog("setTitle","�޸Ļ����ñ�ע");
					$("#wp_comments_frm input[name='sns']").val(sns);
				}
			}else{
				$.messager.alert("����","����ѡ����Ҫ�޸Ļ���ӱ�ע���С�");
			}
		}
		function add_writepage_comments(){
			var comments = $("#wp_comments_frm textarea[name='comments']").val().replace(/(^\s*)|(\s*$)/g, "");
			if(comments!=""){
				var sns = "";
				sns = $("#wp_comments_frm input[name='sns']").val();
				if(sns!=""){
					$.ajax({
			        	type : "POST",
			        	cache : false,
			        	async:true,
			        	url : "/xbxt_update_writepage_comments.action",
			        	dataType : "json",
			        	data : {"sns":sns,"comments":comments},
			        	success : function(result) {
			        		try {
			        			if(result.success){
			        				$.messager.show({title: "�ɹ�",msg: result.msg});
			        				$("#tb_query_warningwritepaper").datagrid("reload");
			        				$('#wp_comments_dlg').dialog('close');
			        			}else{
			        				$.messager.show({title: "ʧ��",msg: result.msg});
			        			}
			        		}
			        		catch (exception) {
			        			console.log(exception);
			        		}
			        	}
			        });
				}else{
					$.messager.alert("����","�޷����в�����");
				}
			}else{
				$("#wp_comments_frm textarea[name='comments']").val("");
			}
		}
		function expExcel(){
        	var excelfmurl="/xbxt_expexcel_custwarning_report.action?"+$.param($("#tb").datagrid("options").queryParams);;//$.param($("#tb").datagrid("options").queryParams);
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
			<div title="Ԥ���ͻ��б�" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
					�ͻ����ƻ��ͻ��ţ�<input type="text" name="bigcustnameorbigcustno" class="BigCustCombo" value=""  data-options="required:true,missingMessage:'��ѡ'"/><!-- <input class="easyui-validatebox textbox" name="bigcustnameorbigcustno" data-options="required:true,validType:'length[2,30]'"> -->
					<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
					�ж�����һ��(����������-��������)>a  �ж���������(����������-��������)/����������>b
					<a href="javascript:add_writepage();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'" >��ӵ��ͻ�Ԥ��������</a>
					<a href="javascript:expExcel();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-excel'" >excel����</a>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'Ԥ���ͻ��б�', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50"><!-- ,singleSelect:true -->
					<thead>
						<tr class="header">
							<th data-options="field:'bigcustno',align:'center',width:150" >��ͻ���</th>
							<th data-options="field:'bigcustname',align:'center',width:150" >�ͻ���</th>
							<th data-options="field:'destination',align:'center',width:100" >·��</th>
							<th data-options="field:'lastmonth_sendnum',align:'center',width:100" >���·�����</th>
							<th data-options="field:'lastlastmonth_sendnum',align:'center',width:100" >�����·�����</th>
							<th data-options="field:'condition_one',align:'center',width:150" >�ж�����һ</th>
							<th data-options="field:'condition_two',align:'center',width:150" >�ж�������</th>
							<th data-options="field:'mgrname',align:'center',width:100" >�����ͻ�����</th>
							<th data-options="field:'mgrid',align:'center',width:100" >�����ͻ������</th>
						</tr>
					</thead>
				</table>
			</div>
			<div title="Ԥ����������" style="padding:10px">
				<form id="warning_param_setting_form">
					<input type="hidden" id="param_sn" name="param_sn" />
					<table cellpadding="5">
            			<tr>
                			<td>��ʽ(����������-��������)>a������</td>
                			<td><input type="text" id="param_a" name="param_a" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" /></td>
            			</tr>
            			<tr>
                			<td>��ʽ(����������-��������)/����������>b������</td>
                			<td><input type="text" id="param_b" name="param_b" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" /></td>
            			</tr>
            			<tr>
            				<td></td>
            				<td><a href="javascript:update_param();" id="update_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" >ȷ���ύ</a></td>
            			</tr>
					</table>
				</form>
			</div>
			<div title="Ԥ���ͻ�������" style="padding:10px">
				<div id="toolbar_warningwritepaper" style="padding:10px;display:none">
					<form id="queryfm_warningwritepaper" style="margin:0">
						<a href="javascript:remove_writepage();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'" >�Ƴ�������</a>
						<a href="javascript:open_writepage_comments_dlg();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'" >��ӱ�ע</a>
					</form>
				</div>
				<table id="tb_query_warningwritepaper" class="easyui-datagrid" data-options="title:'Ԥ���ͻ�����������������ϵĿͻ������Ԥ��ʱ�������ڲ��ᱻԤ����', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar_warningwritepaper',
     				url:query_warningwritepaper_url,pageSize:50"><!-- ,singleSelect:true -->
					<thead>
						<tr class="header">
							<th data-options="field:'bigcustno',align:'center',width:150" >��ͻ���</th>
							<th data-options="field:'bigcustname',align:'center',width:150" >�ͻ���</th>
							<th data-options="field:'destination',align:'center',width:100" >·��</th>
							<th data-options="field:'op_name',align:'center',width:100" >�����˺�</th>
							<th data-options="field:'year',align:'center',width:120" >��Чʱ�䣨�꣩</th>
							<th data-options="field:'month',align:'center',width:120" >��Чʱ�䣨�£�</th>
							<th data-options="field:'op_time',align:'center',width:100" >����ʱ��</th>
							<th data-options="field:'comments',align:'center',width:300" >��ע</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="wp_comments_dlg" class="easyui-dialog" title="wp_comments_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#wp_comments_buttons',closed:true" style="width:500px;height:400px;padding:10px">
        	<form id="wp_comments_frm" style="margin:0">
        		<input type="hidden" name="sns" />
				<textarea name="comments" rows="18" cols="63" style="resize: none;"></textarea>
				�����ܿ��Զ������в�����������С��~
			</form>
    	</div>
    	<div id="wp_comments_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#wp_comments_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="add_writepage_comments();">ȷ��</a>
    	</div>
	</body>
</html>