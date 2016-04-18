<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>��������ά��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
		<script type="text/javascript">
			var t_usertype = '${session.userInfo.user_type}';
			console.log('t_usertype-------->'+t_usertype);
		</script>
		<script type="text/javascript" src="/business/js/dataClassManage.js"></script>
		<script type="text/javascript">
			function doquery() {		
				var fmdata = $("#first_class_frm").form2json();
				$("#data1").datagrid("load", fmdata);
			}
			window.onload=function()
			{
			
		
			};
			
			$.extend($.fn.validatebox.defaults.rules, {
			checkTableName: {
			validator: function(value, param){
			
			return checkTableName(value);
			
			},
			message: '{0}'
			}
			}); 
		</script>
	</head>
	<body  class="easyui-layout">
		<div id="first_class_toolbar" style="padding: 3px; display: none">
			<form id="first_class_frm" style="margin: 0">
				&nbsp;&nbsp;���ƣ�
				<input type="text" id="childclass_name" name="childclass_name" class="easyui-validatebox" style="width:100px"/>
				&nbsp;&nbsp;״̬��
				<input type="text"  id="status"  name="childclass_status" class="easyui-combobox" style="width: 150px" 
								data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
				&nbsp;&nbsp;
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openChildClassAddDlg();">����</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"	onclick="delete_childclass();">ɾ��</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="openChildClassEditDlg();">�޸�</a>
				
			</form>
		</div>
		
		<div id="second_class_toolbar" style="padding: 3px; display: none">
			<form id="second_class_frm" style="margin: 0">
				&nbsp;
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openClassPropertyAddDlg();">����������������</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'" onclick="delete_classproperty();">������������ɾ��</a>
			<!-- 	<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="openClassPropertyEditDlg();">�������������޸�</a>
			 -->
			</form>
		</div>
		
		<div data-options="region:'north' ,title:'��������ҳ��',split:true" style="height:350px;background:#fafafa;overflow:hidden;" >
		<table id="data1" class="easyui-datagrid"
			style="height:30%;"
			data-options="
     	fit:true,rownumbers:true,toolbar:'#first_class_toolbar',pagination:true,pageSize:20,url:'/data_class_loaddata1.action',
     	singleSelect:true,onClickRow:function(rowIndex, rowData)
     	{initData1Params(rowIndex,rowData);},">
			<thead>
				<tr>
					<th data-options="field:'childclass_sn',align:'center'" width="180">
						��������ID
					</th>
					<th data-options="field:'childclass_name',align:'center'" width="280">
						������������
					</th>
					<th data-options="field:'table_name',align:'center'" width="280">
						�������ͱ���
					</th>
					<th data-options="field:'childclass_brchstr',align:'center'" width="150">
						��������
					</th>
				 
				</tr>
			</thead>
		</table>
		</div>
		
		<div data-options="region:'center',title:'������������ҳ��'" style="height:250px;background:#fafafa;overflow:hidden;">
		<table id="data2" class="easyui-datagrid"
			style="height:30%;"
			data-options="
     	fit:true,rownumbers:true,pagination:true,pageSize:20,url:'',
     	toolbar:'#second_class_toolbar',
     	singleSelect:true,
     	onClickRow:function(rowIndex, rowData)
     	{initData2Params(rowIndex,rowData);},">
			<thead>
				<tr>
					<th data-options="field:'classproperty_id',align:'center'" width="80">
						����ID
					</th>
					<th data-options="field:'classproperty_parentclass_id',align:'center'" width="80">
						����ID
					</th>
					<th data-options="field:'classproperty_parentclass_tn',align:'center'" width="150">
						����
					</th>
					<th data-options="field:'classproperty_parentclass_cn',align:'center'" width="150">
						��������
					</th>
					<th data-options="field:'classproperty_name',align:'center'" width="100">
						��������
					</th>
					<th data-options="field:'classproperty_type',formatter:function(value,row,index){if (value){return row.classproperty_type_str;} else {return '��������';}},align:'center'" width="100">
						��������
					</th>
					<th data-options="field:'classproperty_ifnull',formatter:function(value,row,index){if (value){return row.classproperty_ifnull_str;} else {return '��������';}},align:'center'" width="120">
						�Ƿ�ɿ�
					</th>
					<th data-options="field:'classproperty_size',formatter:function(value,row,index){if (value){if(value=='NULL'||value=='null'){return '-';} else {return value;}} else {return '��������';}},align:'center'" width="120">
						���Գ���
					</th>
					<th data-options="field:'classproperty_status',formatter:function(value,row,index){if (value){return row.classproperty_status_str;} else {return '��������';}},align:'center'" width="120">
						��ǰ״̬
					</th>
				</tr>
			</thead>
		</table>
		</div>
		
		<div id="childclass_add_dlg" class="easyui-dialog" title="������������" closed="true" data-options="iconCls:'icon-add',buttons:'#childclass_add_buttons',closed:true" style="width:350px;height:300px;padding:10px">
        	<form id="childclass_add_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str1"></div></legend>
              		<input type="hidden" name="childclass_add_for_parentclassid" id="childclass_add_for_parentclassid"></input>
            		<div class="form_item col1">
               	 		<label>������������:</label>
                		<input name="childclass_add_classname" id="childclass_add_classname" class="easyui-validatebox" style="width: 150px" required="true" />
            		</div>
 					<div class="form_item col1">
               	 		<label>�������ͱ���:</label>
                		<input name="table_name" type="text" id="childclass_add_tablename" class="easyui-validatebox" style="width: 150px" required="true"  onblur="checkTableName(this.value)" />
            		</div>
            		<div class="form_item col1">
                		<label>��������״̬:</label>
						<input type="text" id="childclass_add_status" name="childclass_add_status" class="easyui-combobox" style="width: 150px" required="true"  data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="childclass_add_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#childclass_add_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_childclass_add_fm();">ȷ��</a>
    	</div>
		
		<div id="childclass_edit_dlg" class="easyui-dialog" title="���������޸�" closed="true" data-options="iconCls:'icon-edit',buttons:'#childclass_edit_buttons',closed:true" style="width:350px;height:300px;padding:10px">
        	<form id="childclass_edit_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str2"></div></legend>
              		<input type="hidden" name="childclasssn_edit" id="childclasssn_edit"></input>
            		<div class="form_item col1">
               	 		<label>������������:</label>
                		<input name="childclass_edit_classname" id="childclass_edit_classname" class="easyui-validatebox" style="width: 150px" required="true"  />
            		</div>
             
            		<div class="form_item col1">
                		<label>��������״̬:</label>
						<input type="text" id="childclass_edit_status" name="childclass_edit_status" class="easyui-combobox" style="width: 150px" required="true"  data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="childclass_edit_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#childclass_edit_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_childclass_edit_fm();">ȷ��</a>
    	</div>
		
		<div id="classproperty_add_dlg" class="easyui-dialog" title="���������������" closed="true" data-options="iconCls:'icon-edit',buttons:'#classproperty_add_buttons',closed:true" style="width:350px;height:350px;padding:10px">
        	<form id="classproperty_add_fm" method="post" >
        		<fieldset>
              		<legend>���������������</legend>
              		<input type="hidden" name="classproperty_add_sn" id="classproperty_add_sn"></input>
        
              		<div class="form_item col1">
               	 		<label>��������:</label>
                		<input name="classproperty_add_name" id="classproperty_add_name" class="easyui-validatebox" style="width: 150px" required="true"  />
            		</div>
            		<div class="form_item col1">
               	 		<label>����:</label>
                		<input name="column_name"  type="text"  id="classproperty_add_column_name" class="easyui-validatebox" style="width: 150px" required="true"  onblur="checkColName(this.value)" />
            		</div>
            		<div class="form_item col1">
               	 		<label>��������:</label>
                		<input type="text" id="classproperty_add_type" name="classproperty_add_type" class="easyui-combobox" style="width: 150px" required="true"  data-options="valueField:'value',textField:'text',data:${application.ParamJson.CLASS_COLUMNTYPE},onChange:function(newvalue,oldvalue){changeForComboSelectChange(newvalue);}"/>
            		</div>
            		<div class="form_item col1">
               	 		<label>�����Ƿ�Ϊ��:</label>
                		<input type="text" id="classproperty_add_ifnull" name="classproperty_add_ifnull" class="easyui-combobox" style="width: 150px" required="true"  data-options="valueField:'value',textField:'text',data:${application.ParamJson.CLASS_NULLABLE}"/>
            		</div>
            		<div class="form_item col1">
               	 		<label>���Գ���:</label>
                		<input name="classproperty_add_size" id="classproperty_add_size" class="easyui-validatebox"  style="width: 150px" required="true" data-options="required:true,validType:'number'" />
            		</div>
            		<div class="form_item col1">
               	 		<label>����״̬:</label>
                		<input type="text" id="classproperty_add_status" name="classproperty_add_status" class="easyui-combobox" style="width: 150px" required="true"  data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            		<font color='red'>�������͵ĵ�һ���ֶ���չʾ�ֶΣ��벻ҪΪ�գ�</font>
            		<br/>
            		<font color='red'>ÿһ�����������벻Ҫ���ö���1����ͼƬ���ԣ�</font>
            		<br/>
            		<font color='red'><div id="show_str3"></div></font>
            	</fieldset>
        	</form>
    	</div>
    	<div id="classproperty_add_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#classproperty_add_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_classproperty_add_fm();">ȷ��</a>
    	</div>
		
		<div id="classproperty_edit_dlg" class="easyui-dialog" title="�������������޸�" closed="true" data-options="iconCls:'icon-edit',buttons:'#classproperty_edit_buttons',closed:true" style="width:350px;height:240px;padding:10px">
        	<form id="classproperty_edit_fm" method="post" >
        		<fieldset>
              		<legend>�������������޸�</legend>
              		<input type="hidden" name="classproperty_edit_sn" id="classproperty_edit_sn"></input>
            		<div class="form_item col1">
               	 		<label>��������:</label>
                		<input name="classproperty_edit_name" id="classproperty_edit_name" class="easyui-validatebox" data-options="required:true" />
            		</div>
            		<div class="form_item col1">
               	 		<label>����״̬:</label>
                		<input type="text" id="classproperty_edit_status" name="classproperty_edit_status" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="classproperty_edit_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#classproperty_edit_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_classproperty_edit_fm();">ȷ��</a>
    	</div>
	</body>
</html>