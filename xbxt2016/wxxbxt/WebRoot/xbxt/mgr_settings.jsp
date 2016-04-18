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
		var query_url = "/xbxt_mgr_settings_query.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		
	    });	
		function mgr_query() {
			//alert($("input[name='if_onlyinitmgr']:radio:checked").val());
			if($("#queryfm").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm").form2json();
				$("#tb").datagrid("load", fmdata);
			}
		}
		function mgr_init_mobile(){
			var row =  $("#tb").datagrid("getSelected");
			if(row){
				console.log(row);
				$("#mgr_settings_frm input[name='mgrid']").val(row.mgrid);
				if(row.if_init=='δ��ʼ'){
					$("#mgr_settings_dlg").dialog("open").dialog("setTitle","��ʼ����½��Ϣ");
				}else{
					$("#mgr_settings_dlg").dialog("open").dialog("setTitle","�޸ĵ�½��Ϣ");
					$("#mgr_settings_frm input[name='mgr_login_str']").val(row.mgr_login_str);
				}
			}else{
				$.messager.alert("����","����ѡ��һ�У��ٽ��в�����");
			}
		}
		function merger_mgr_settings(){
			var mgrid = $("#mgr_settings_frm input[name='mgrid']").val();
			var login_str = $("#mgr_settings_frm input[name='mgr_login_str']").val();
			var login_pwd = $("#mgr_settings_frm input[name='mgr_login_pwd']").val();
			if(login_str&&login_str!=''&&login_pwd&&login_pwd!=''){
				$.ajax({
		        	type : "POST",
		        	cache : false,
		        	async:true,
		        	url : "/xbxt_merger_mobileinfo_formgr.action",
		        	dataType : "json",
		        	data : {"cust_no":mgrid,"login_str":login_str,"login_pwd":login_pwd},
		        	success : function(result) {
		        		try {
		        			if(result.success){
		        				$.messager.show({title: "�ɹ�",msg: result.msg});
		        				$("#tb").datagrid("reload");
		        				$('#mgr_settings_dlg').dialog('close');
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
				$.messager.alert("����","��½�ַ��͵�½���붼����Ϊ�գ�");
			}
		}
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="�ͻ������ֻ��˵�½��Ϣ����" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
					�ͻ���������ͻ�����ţ�<input class="easyui-validatebox textbox" name="mgrnameormgrno" value="" data-options="validType:'length[1,30]'">
					<input type="radio" name="if_onlyinitmgr" value="0" checked="checked" />ȫ���ͻ�����
					<input type="radio" name="if_onlyinitmgr" value="1" />�ѳ�ʼ���Ŀͻ�����
					<a href="javascript:mgr_query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >����</a>
					<a href="javascript:mgr_init_mobile();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help'" >��ʼ���ͻ������ƶ����˺�</a>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'�ͻ������ֻ��˵�½��Ϣ����', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true"><!-- ,singleSelect:true -->
					<thead>
						<tr class="header">
							<th data-options="field:'mgrid',align:'center',width:150" >�ͻ������</th>
							<th data-options="field:'mgrname',align:'center',width:150" >�ͻ���������</th>
							<th data-options="field:'department',align:'center',width:300" >����</th>
							<th data-options="field:'if_init',align:'center',width:150" >�Ƿ��Ѿ���ʼ����½</th>
							<th data-options="field:'mgr_login_str',align:'center',width:150" >���ڵ�½���ַ���</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- <div title="Ԥ����������" style="padding:10px">
				12345
			</div> -->
		</div>
		<div id="mgr_settings_dlg" class="easyui-dialog" title="mgr_settings_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#mgr_settings_buttons',closed:true" style="width:300px;height:160px;padding:10px">
        	<form id="mgr_settings_frm" style="margin:0">
        		<input type="hidden" name="mgrid" />
				��½�ַ�<input type="text" name="mgr_login_str" style="width:200px" />
				<br/>
				��½����<input type="password" name="mgr_login_pwd" value="1" style="width:200px" />
			</form>
    	</div>
    	<div id="mgr_settings_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#mgr_settings_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="merger_mgr_settings();">ȷ��</a>
    	</div>
	</body>
</html>