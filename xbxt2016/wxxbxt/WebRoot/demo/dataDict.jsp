<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
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

	var url = "dataDict_list.action";

	function doquery() {
		
		var fmdata = $('#queryfm').form2json();

		$('#data1').datagrid("load", fmdata);
	}
	function reset() {
		window.location.href = "/demo/dataDict.jsp";
	}

</script>
</head>

<body >
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			������<input type="text" name="table_name" class="easyui-validatebox" style="width:150px"/>
			&nbsp;&nbsp; 
			 <a href="###"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
			<a href="javascript:reset();" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-redo'">����</a>
			<a href="###" 
			   class="easyui-linkbutton" 
			   data-options="plain:true,iconCls:'icon-excel'" 
			   onclick="expexcel('#data1','dataDict_excel')">����Excel</a>
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'���ݴʵ�',
     	fit:true,rownumbers:true,pagination:true,nowrap:false,striped:true,toolbar:'#toolbar',pageSize:20,url:url"
     	     	style="width: 90%;margin: 0 auto;" >
		<thead>
			<tr class="header">
				<th data-options="field:'ck',align:'center',checkbox:'true'"></th>
				<th data-options="field:'table_name',align:'center'" width="180">����</th>
				<th data-options="field:'column_name',align:'center'" width="180">�ֶ���</th>
				<th data-options="field:'data_type',align:'center'" width="180">�ֶ�����</th>
				<th data-options="field:'data_default',align:'center'" width="120">Ĭ��ֵ</th>
				<th data-options="field:'nullable',align:'center'" width="80">�ܷ�Ϊ��</th>
				<th data-options="field:'col_comments',align:'center'" width="180">�ֶα�ע</th>
			</tr>
		</thead>
	</table>
	
	<div style="display:none">
		<!-- ����excel ����ѡ��  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
