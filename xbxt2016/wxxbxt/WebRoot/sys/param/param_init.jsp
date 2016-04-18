<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>参数管理</title>
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
        if (result.success){$.messager.show({title: "成功",msg: result.msg});}     
        else $.messager.show({title: "错误",msg: result.msg}); 
	    },"json");

	}

	function addParam() {
		$("#dlg").dialog("open").dialog("setTitle", "新增");
		$("#fm").form("reset");
		url = "param_addParam";
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
            $.messager.show({title: "成功",msg: result.msg});
        }else $.messager.show({title: "错误",msg: result.msg}); 
	    },"json");

	}
	function deleteParam() {
		url = "param_deleteParam.action";
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
							$.messager.show({title: "成功",msg: result.msg});
						} else {
							$.messager.show({title: "错误",msg: result.msg}); 
						}
					}, "json");
				}
			});
		} else {
			$.messager.alert("警告","请选择要删除的数据");
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
			 参数类型：<input type="text" name="type" class="easyui-validatebox" style="width:100px"/>
			&nbsp;&nbsp; 
			参数名称：<input type="text" name="text" class="easyui-validatebox" style="width:100px"/> 
			&nbsp;&nbsp;
			参数标志：<select id="flag" class="easyui-combobox" name="flag" style="width:80px">
				<option selected='selected' value="1">正常</option>
				<option value="2">全部</option>
				<option value="0">已删除</option>
			</select>
			&nbsp;&nbsp;
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">查询</a>
			<br><hr/>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'" onclick="addParam()">增加</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'" onclick="editParam()">修改</a>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-remove'"
				onclick="deleteParam()">删除</a>
			 

			<span class="line">|</span>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-reload'" onclick="loadParam()">参数生效</a>
			<span class="line">|</span>
			<a href="###" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-tip'" onclick="showInfo()">使用方式</a>
			

		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'参数管理',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:20,
     	url:query_url,queryParams:queryParams",
     	>
		<thead>
			<tr class="header">
				<th data-options="field:'ck',align:'center',checkbox:'true'"></th>
				<th data-options="field:'sn',align:'center',width:100" >参数序号</th>
				<th data-options="field:'type',align:'center',width:150" >参数类型</th>
				<th data-options="field:'value',align:'center',width:150" >参数值</th>
				<th data-options="field:'text',align:'center',width:150" >参数名称</th>
				<th data-options="field:'order_id',align:'center',width:120" >参数排序</th>
			</tr>
		</thead>
	</table>

	<!-- 编辑窗口 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){save_data();}                   },
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#dlg').dialog('close');}  }]"
				 style="width:550px;height:200px;padding:10px 10px;">	

		<form id="fm" method="post"">
			<input type="hidden" name="sn" id="sn" value=""/>
			<div class="form_item col1">
				<label>参数类型：</label> <input type="text" name="type"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>参数值：</label> <input type="text" name="value"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>参数名称：</label> <input type="text" name="text"
					class="easyui-validatebox" style="width:100px" />
			</div>
			<div class="form_item col2">
				<label>参数标志：</label> 
					<select class="easyui-combobox" name="flag" style="width:100px">
					<option value="0">删除</option>
					<option value="1" selected>正常</option>
					</select>
			</div>
			<div class="form_item col2">
				<label>参数排序：</label> <input type="text" name="order_id"
					class="easyui-validatebox" data-options="validType:'number'"
					style="width:100px" />
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
