<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<xl:resource></xl:resource>


<script type="text/javascript">
    var url;
	var query_url = "ljiadd_query.action";
	
	function loadParam() {
		url = "ljiadd_load.action";
		$.post(url,function(result){
        if (result.success){$.messager.show({title: "成功",msg: result.msg});}     
        else $.messager.show({title: "错误",msg: result.msg,
        showType:'fade'}); 
	    },"json");

	}

	function addParam() {
		$("#dlg").dialog("open").dialog("setTitle", "新增");
		$("#fm").form("reset");
		url = "ljiadd_addParam";
	}

	function editParam() {
		var rows = $("#data1").datagrid("getSelections");
		var row =  $("#data1").datagrid("getSelected");

		if (rows.length == 0) {
		    $.messager.alert("警告","请选择要修改的数据");
			return;
		}
		if (rows) {
			if (rows.length > 1) {
			    $.messager.alert("警告","每次只能修改一条记录");
				return;
			}
			$("#dlg").dialog("open").dialog("setTitle", "编辑");
			$("#fm").form("load", row);
			url = "ljiadd_editParam";
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
            $.messager.show({title: "成功",msg: result.msg,
            showType:'fade'});
        }else $.messager.show({title: "错误",msg: result.msg,
        showType:'fade'}); 
	    },"json");

	}
	function deleteParam() {
		url = "ljiadd_deleteParam.action";
		var rows = $("#data1").datagrid("getSelections");

		if (rows.length > 0) {
			$.messager.confirm("确认", "是否要删除?", function(r) {
				if (r) {
					var sns = [];
					for ( var i = 0; i < rows.length; i++) {
						sns.push(rows[i].sn);
					}
					var datas = sns.toString();
					$.post(url, "sns=" + datas, function(result) {
						if (result.success) {
							$("#data1").datagrid("reload");
							$.messager.show({title: "成功",msg: result.msg,
							showType:'fade'});
						} else {
							$.messager.show({title: "错误",msg: result.msg,
							showType:'fade'}); 
						}
					}, "json");
				}
			});
		} else {
			$.messager.alert("警告","请选择要删除的数据");
			return;
		}
	}
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'" onclick="addParam()">增加</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="editParam()">修改</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'"
				onclick="deleteParam()">删除</a>
			 

		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'增删改查',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:20,collapsible:true,
     	url:query_url"
     	>
		<thead>
			<tr class="header">
				<th data-options="checkbox:'true'"></th>
				<th data-options="field:'colint1',align:'center',width:100" >colint1:</th>
				<th data-options="field:'colint2',align:'center',width:100" >colint2:</th>
				<th data-options="field:'colstr1',align:'center',width:120" >colstr1:</th>
				<th data-options="field:'colstr2',align:'center',width:120" >colstr2:</th>
				<th data-options="field:'coldate1',align:'center',width:150" >coldate1:</th>
				<th data-options="field:'coldate2',align:'center',width:150" >coldate2:</th>
			</tr>
		</thead>
	</table>

	<!-- 编辑窗口 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){save_data();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#dlg').dialog('close');}}]"
				 style="width:600px;height:200px;padding:10px 10px;">	

		<form id="fm" method="post">
			<input type="hidden" name="sn" id="sn" value=""/>
			<div class="form_item col2">
				<label>colint1:</label> <input type="text" name="colint1"
					class="easyui-validatebox" style="width:100px" 
					data-options="required:true,validType:'between[1,10000]'"/>
			</div>
			<div class="form_item col2">
				<label>colint2:</label> <input type="text" name="colint2"
					class="easyui-validatebox" style="width:100px" 
					data-options="required:true,validType:'between[1,10000]'"/>
			</div>
			<div class="form_item col2">
				<label>colstr1:</label> <input type="text" name="colstr1"
					class="easyui-validatebox" style="width:100px" 
					data-options="required:true,validType:'alphanum'"/>
			</div>
			<div class="form_item col2">
				<label>colstr2:</label> <input type="text" name="colstr2"
					class="easyui-validatebox" style="width:100px" 
					data-options="required:true,validType:'alphanum'"/>
			</div>
			<div class="form_item col2">
				<label>coldate1:</label><input type="text" name="coldate1" 
				class="easyui-datebox" data-options="editable:false" />
			</div>
			<div class="form_item col2">
				<label>coldate2:</label><input type="text" name="coldate2" 
				class="easyui-datebox" data-options="editable:false" />
			</div>
		</form>
	</div>

	<div style="display:none">
		<!-- 引入excel 导出选项  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
