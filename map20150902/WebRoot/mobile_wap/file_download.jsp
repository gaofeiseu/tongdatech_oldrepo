<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title></title>
		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
    	<script type="text/javascript">
    	window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
    	var query_url ="/fileop_getData1.action";
    	function doquery(){
    		var fmdata=$('#queryfm').form2json();
    		$('#data1').datagrid("options").url=query_url;
    		$('#data1').datagrid("load",fmdata);
    	}
	    var root="${session.userInfo.brch_no}"; 
		$(document).ready(function(){
			loadBrchCombo("#ljhx_brchno_query",root);
			$('#hidden_root').val(root);
		});
   	 	</script>
	</head>
	<body>
		<div id="tb" style="padding:3px;display:none">
		 	<form id="queryfm" style="margin:0">
		 		�ļ����ͣ�<input type="text" id="file_class" name="file_class" value="1" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.M_FILE_TYPE}"/>
		 		<input type="hidden" id="hidden_root" name="hidden_root"></input>
		 		������ѯ��<input type="text" name="ljhx_brchno_query" id="ljhx_brchno_query" style="width: 150px" />
		 		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
		 	</form>
	 	</div>
     	<table id="data1" class="easyui-datagrid" data-options="title:'�ļ��б�',
     		fit:true,rownumbers:true,pagination:true,toolbar:'#tb',
     		pageSize:20,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'sn',align:'center'" width="60">SN</th>
					<th data-options="field:'my_filename',align:'center'" width="250">�ļ���</th>
					<th data-options="field:'file_exe',align:'center'" width="100">�ļ���׺��</th>
					<th data-options="field:'file_class_str',align:'center'" width="100">�ļ�����</th>
					<th data-options="field:'upload_user_name',align:'center'" width="100">�ϴ���</th>
					<th data-options="field:'file_msg',align:'center'" width="200">�ļ���ע</th>
					<th data-options="field:'input_time',align:'center'" width="120">�ϴ�ʱ��</th>
					<th data-options="field:'file_save_path',align:'center'" width="400">����</th>
				</tr>
			</thead>
		</table>
	</body>
</html>
