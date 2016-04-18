<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>网点潜在客户查询</title>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<xl:resource></xl:resource>
<style type="text/css">
body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
td { font-size: 12px; line-height: 150%; }
</style>
<script language="javascript" type="text/javascript" src="/business/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var url;
	var query_url = "qzCust_query.action"; 
	function doquery() {		
		var fmdata = $("#queryfm").form2json();
		$("#data1").datagrid("load", fmdata);
	}
	
	function clearNoNum(obj)
	{
	    //先把非数字的都替换掉，除了数字和.
	    obj.value = obj.value.replace(/[^\d.]/g,"");
	    //必须保证第一个为数字而不是.
	    obj.value = obj.value.replace(/^\./g,"");
	    //保证只有出现一个.而没有多个.
	    obj.value = obj.value.replace(/\.{2,}/g,".");
	    //保证.只出现一次，而不能出现两次以上
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
 
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			
		
			 年龄：<input type="text" name="age_min" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="age_max" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			地址：
		 
			<input type="text" name="dz" class="easyui-validatebox" style="width:200px"  />
		
			
			性别：<select id="sex" class="easyui-combobox" name="sex" style="width:120px">
				<option value="">全部</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
			&nbsp;&nbsp; 
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">查询</a>
				<br>
	
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'潜在客户列表',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url" >
		<thead>
			<tr class="header">
			 
				<th data-options="field:'name',align:'center',width:120" >姓名</th>
				<th data-options="field:'age',align:'center',width:120" >年龄</th>
				<th data-options="field:'sex',align:'center',width:120" >性别</th>
				<th data-options="field:'dz',align:'center',width:120" >地址</th>
				<th data-options="field:'sj',align:'center',width:120" >手机</th>
				<th data-options="field:'dh',align:'center',width:120" >电话</th>
		

			</tr>
		</thead>
	</table>

 
</body>
</html>
