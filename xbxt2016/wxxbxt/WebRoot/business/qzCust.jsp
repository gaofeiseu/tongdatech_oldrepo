<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>����Ǳ�ڿͻ���ѯ</title>
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
	    //�Ȱѷ����ֵĶ��滻�����������ֺ�.
	    obj.value = obj.value.replace(/[^\d.]/g,"");
	    //���뱣֤��һ��Ϊ���ֶ�����.
	    obj.value = obj.value.replace(/^\./g,"");
	    //��ֻ֤�г���һ��.��û�ж��.
	    obj.value = obj.value.replace(/\.{2,}/g,".");
	    //��֤.ֻ����һ�Σ������ܳ�����������
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}
 
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			
		
			 ���䣺<input type="text" name="age_min" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="age_max" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			��ַ��
		 
			<input type="text" name="dz" class="easyui-validatebox" style="width:200px"  />
		
			
			�Ա�<select id="sex" class="easyui-combobox" name="sex" style="width:120px">
				<option value="">ȫ��</option>
				<option value="��">��</option>
				<option value="Ů">Ů</option>
			</select>
			&nbsp;&nbsp; 
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
				<br>
	
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'Ǳ�ڿͻ��б�',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url" >
		<thead>
			<tr class="header">
			 
				<th data-options="field:'name',align:'center',width:120" >����</th>
				<th data-options="field:'age',align:'center',width:120" >����</th>
				<th data-options="field:'sex',align:'center',width:120" >�Ա�</th>
				<th data-options="field:'dz',align:'center',width:120" >��ַ</th>
				<th data-options="field:'sj',align:'center',width:120" >�ֻ�</th>
				<th data-options="field:'dh',align:'center',width:120" >�绰</th>
		

			</tr>
		</thead>
	</table>

 
</body>
</html>
