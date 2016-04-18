<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title>ɾ�����ݿ��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
			function requery(){
				$('#data1').datagrid({url:'/op_maintance_database_query.action?marker_class='+$('#c_marker_class').combobox('getValue')+'&dept_class='+$('#c_dept_class').combobox('getValue')});
			}
			function dodelete(){
				var select_row = $('#data1').datagrid('getSelected');
				if(select_row){
					$.messager.confirm('��ʾ','ȷ������ɾ��SNΪ'+select_row.sn+'������ô��',function(r){
					    if (r){
					    	$('#delete_btn').linkbutton('disable');
							var url="/op_maintance_database_dodelete.action";
							var json = {};
							json["s_sn"] = select_row.sn;
							json["s_marker_class"] = select_row.marker_class_num;
							json["s_dept_class"] = select_row.dept_class_num;
							json["s_class_name"] = select_row.class_name;
							json["s_class_tablename"] = select_row.class_tablename;
							$.ajax({
								type : "POST",
								cache : false,
								async:true,
								url : url,
								dataType : "json",
								data : json,
								success : function(msg) {
									try {
										if(msg.if_success){
											$.messager.show({title: "�ɹ�",msg: msg.msg});
										}else{
											$.messager.show({title: "ʧ��",msg: msg.msg});
										}
										$('#delete_btn').linkbutton('enable');
									}
									catch (exception) {
										alert(exception);
									}
								},
								error : function(msg) {
									alert('error!!');
								}
							});
					    }
					});
				}else{
					$.messager.show({title: "��ʾ",msg: "����ѡ����Ҫɾ�������ݣ�"});
				}
			}
		</script>
	</head>
	<body>
		<div id="tb" style="padding:3px;display:none">
		 	<form id="queryfm" style="margin:0">
		 		��ע���ͣ�<input type="text" value="1" id="c_marker_class" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.MARKER_TYPE},onChange:function(newValue){requery();}"/>
		 		�������ͣ�<input type="text" value="1" id="c_dept_class" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.USER_TYPE},onChange:function(newValue){requery();}"/>
		 		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		 		<a href="#" id="delete_btn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="dodelete();">ɾ��ָ���������͵ı�</a>
		 	</form>
	 	</div>
     	<table id="data1" class="easyui-datagrid" data-options="title:'ɾ����������ݿ��',
     		fit:true,rownumbers:true,pagination:true,toolbar:'#tb',pageSize:20,singleSelect:true,
     		url:'/op_maintance_database_query.action?marker_class=1&dept_class=1'">
			<thead>
				<tr>
					<th data-options="field:'sn',align:'center'" width="120">SN</th>
					<th data-options="field:'marker_class',align:'center'" width="150">��ע����</th>
					<th data-options="field:'dept_class',align:'center'" width="150">��������</th>
					<th data-options="field:'class_name',align:'center'" width="220">����������</th>
					<th data-options="field:'class_tablename',align:'center'" width="220">�����ͱ���</th>
				</tr>
			</thead>
		</table>
	</body>
</html>