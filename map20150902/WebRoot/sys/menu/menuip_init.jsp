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
		<script type="text/javascript" src="/map/mapjs/map_usericon.js"></script>
		 
		<script type="text/javascript">
	 
			//init
			var query_url="menuip_query";
		 
			var select_rowData=null;
	 
			
			//user menu
			function doQueryUser(){
				var queryDate =$("#userfm").form2json();
				$("#userTab").datagrid("load",queryDate);
			}
			function addUser(){
				$("#user_dlg").dialog("open").dialog("setTitle","��IP");
				$("#user_fm").form("clear");
				
			}
			function editUser(){
				var row =  $("#userTab").datagrid("getSelected");
				if(row){
					$("#user_dlg").dialog("open").dialog("setTitle","����Ϣ�޸�");
					$("#user_fm").form("load",row);
			       	var a_arr = [];
			     	a_arr = row.menu_id.split(",");
			     	$('#menu_id').combotree('setValues', a_arr);
				}else{
					$.messager.alert("����","����ѡ��һ������Ϣ");
				}
			}
			function delUser(){
				var row =  $("#userTab").datagrid("getSelected");
				if(row){0.
				
					 $.messager.confirm('ȷ��','�Ƿ�ɾ������Ϣ',function(r){
           if (r){
               $.post("menuip_del",row, function(result) {
				       if (result.success) {
					        $.messager.show({title: "�ɹ�",msg: result.msg});
					        $("#userTab").datagrid("reload");
				       } else {
					        $.messager.show({title: "����",msg: result.msg}); 
				       }
			         }, "json");
           
           }}); 
				}else{
					$.messager.alert("����","����ѡ��һ������Ϣ");
				}
			}
 
 
			
			//dlg
			function doSaveUser(){
				fmSubmit("#user_fm","menuip_save",
		        function(result){
	                $("#userTab").datagrid("reload");
	                $("#user_dlg").dialog("close"); 
			   });
			}
			
		 
 
		 
		</script>
	</head>
	<body  class="easyui-layout">
		<!--�û����� ������-->
		<div id="user_toolbar" style="padding: 5px; display: none">
			<form id="userfm" style="margin: 0">
		 
				��IP:
				<input type="text" name="ip"  class="easyui-validatebox" style="width:200px"  validtype="ip"  id="ip"/>
				&nbsp;&nbsp;&nbsp;�˵�:
			 <select    name="menu_id"   class="easyui-combotree" style="width: 150px" 
							data-options="valueField:'value',textField:'text',url:'/menu_tree'"  multiple="true" onlyLeafCheck="true"  animate="true"></select>
				 
				
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doQueryUser()">��ѯ</a>
				<br>
			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addUser()">����</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delUser()">ɾ��</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"  onclick="editUser()">�޸�</a>			
			 	 </form>
		</div>
	 
		<div data-options="region:'center' ,title:'�˵���IP����',split:true" style="background:#fafafa;overflow:hidden;" >
		<table id="userTab" class="easyui-datagrid"

			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#user_toolbar',
     	pageSize:10,singleSelect:true,url:query_url,border:false " >
			<thead>
				<tr>
					<th data-options="field:'menu_name'" width="250">
						�˵�����
					</th>
					<th data-options="field:'ip'" width="150">
						��IP
					</th>
					<th data-options="field:'detail'" width="250">
						˵��
					</th>
					 
				 
			 
				</tr>
			</thead>
		</table>
		</div>
		 
 

		<!--�û����� ������-->
		 <div id="user_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
			 	{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){doSaveUser();}},
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	handler:function(){$('#user_dlg').dialog('close');}  }]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="user_fm" method="post">
				
				<input type="hidden"   id="sn" name="sn" value=""/>
			   <div class="form_item col1">
				   <label>�˵���</label>
				    <select  name="menu_id" id="menu_id" class="easyui-combotree" style="width: 205px" 
							data-options="required:true,valueField:'value',textField:'text',url:'/menu_tree'"  multiple="true" onlyLeafCheck="true"  animate="true"></select>
				
			 	  </div>
			  <div class="form_item col1">
				   <label>��IP��</label>
				   <input type="text" name="ip" class="easyui-validatebox"  validtype="ip" style="width:200px" 
				   data-options="required:true"   />
			  </div>
			
			  <div class="form_item col1">
				   <label>˵����</label>
				   <input type="text"  name="detail" class="easyui-validatebox" style="width:200px"  />
				 
			  </div>  
			
			 
			
			  </form>
		</div>
	 
	</body>
</html>
