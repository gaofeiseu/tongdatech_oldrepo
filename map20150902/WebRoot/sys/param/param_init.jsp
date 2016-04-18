<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>��������</title>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">
    var url;
	var query_url = "param_query.action";
	var queryParams = {flag:1};
	
	function doquery() {		
		var fmdata = $("#queryfm").form2json();
		$("#data1").datagrid("load", fmdata);
	}



	function loadParam() {
		url = "param_load.action";
		$.post(url,function(result){
        if (result.success){$.messager.show({title: "�ɹ�",msg: result.msg});}     
        else $.messager.show({title: "����",msg: result.msg}); 
	    },"json");

	}

	function addParam() {
		$("#dlg").dialog("open").dialog("setTitle", "����");
		$("#fm").form("reset");
		url = "param_addParam";
	}

	function editParam() {
		var rows = $("#data1").datagrid("getSelections");
		var row =  $("#data1").datagrid("getSelected");

		if (rows.length == 0) {
		    $.messager.alert("����","��ѡ��Ҫ�޸ĵ�����");
			return;
		}
		if (rows) {
			if (rows.length > 1) {
			    $.messager.alert("����","ÿ��ֻ���޸�һ����¼");
				return;
			}
			$("#dlg").dialog("open").dialog("setTitle", "�༭");
			$("#fm").form("load", row);
			url = "param_editParam";
		}
	}

	function save_data() {
		if ($("#fm").form("validate") == false)
			return;
		var datafm = $("#fm").form2json();
		$.post(url,datafm,function(result){
        if (result.success){
            $("#dlg").dialog("close");
			$("#data1").datagrid("reload");
            $.messager.show({title: "�ɹ�",msg: result.msg});
        }else $.messager.show({title: "����",msg: result.msg}); 
	    },"json");

	}
	function deleteParam() {
		url = "param_deleteParam.action";
		var rows = $("#data1").datagrid("getSelections");

		if (rows.length > 0) {
			$.messager.confirm("ȷ��", "�Ƿ�Ҫɾ��?", function(r) {
				if (r) {
					var sns = [];
					for ( var i = 0; i < rows.length; i++) {
						sns.push(rows[i].sn);
					}
					var datas = sns.toString();
					$.post(url, "sns=" + datas, function(result) {
						if (result.success) {
							$("#data1").datagrid("reload");
							$.messager.show({title: "�ɹ�",msg: result.msg});
						} else {
							$.messager.show({title: "����",msg: result.msg}); 
						}
					}, "json");
				}
			});
		} else {
			$.messager.alert("����","��ѡ��Ҫɾ��������");
			return;
		}
	}
	function showInfo(){
	    window.location.replace("/sys/param/sysParam.html");
	}
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			 �������ͣ�<input type="text" name="type" class="easyui-validatebox" style="width:100px"/>
			&nbsp;&nbsp; 
			�������ƣ�<input type="text" name="text" class="easyui-validatebox" style="width:100px"/> 
			&nbsp;&nbsp;
			������־��<select id="flag" class="easyui-combobox" name="flag" style="width:80px">
				<option selected='selected' value="1">����</option>
				<option value="2">ȫ��</option>
				<option value="0">��ɾ��</option>
			</select>
			&nbsp;&nbsp;
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
			<br><hr/>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'" onclick="addParam()">����</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="editParam()">�޸�</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'"
				onclick="deleteParam()">ɾ��</a>
			 

			<span class="line">|</span>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-reload'" onclick="loadParam()">������Ч</a>
			<span class="line">|</span>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-tip'" onclick="showInfo()">ʹ�÷�ʽ</a>
			

		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'��������',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:20,
     	url:query_url,queryParams:queryParams",
     	>
		<thead>
			<tr class="header">
				<th data-options="field:'ck',align:'center',checkbox:'true'"></th>
				<th data-options="field:'sn',align:'center',width:100" >�������</th>
				<th data-options="field:'type',align:'center',width:150" >��������</th>
				<th data-options="field:'value',align:'center',width:150" >����ֵ</th>
				<th data-options="field:'text',align:'center',width:150" >��������</th>
				<th data-options="field:'order_id',align:'center',width:120" >��������</th>
			</tr>
		</thead>
	</table>

	<!-- �༭���� -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){save_data();}                   },
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	handler:function(){$('#dlg').dialog('close');}  }]"
				 style="width:550px;height:200px;padding:10px 10px;">	

		<form id="fm" method="post"">
			<input type="hidden" name="sn" id="sn" value=""/>
			<div class="form_item col1">
				<label>�������ͣ�</label> <input type="text" name="type"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>����ֵ��</label> <input type="text" name="value"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>�������ƣ�</label> <input type="text" name="text"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>������־��</label> 
					<select class="easyui-combobox" name="flag" style="width:100px">
					<option value="0">ɾ��</option>
					<option value="1" selected>����</option>
					</select>
			</div>
			<div class="form_item col2">
				<label>��������</label> <input type="text" name="order_id"
					class="easyui-validatebox" data-options="validType:'number'"
					style="width:100px" />
			</div>
		</form>
	</div>

	<div style="display:none">
		<!-- ����excel ����ѡ��  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
