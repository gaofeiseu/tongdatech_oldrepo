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
			var query_url="user_query";
			var root="${session.userInfo.brch_no}"; 
      $(document).ready(function(){
	  //  loadBrchSearch(".brchsearch");
	 //		loadBrchSearch("#query_brch_no");
		 loadBrchCombo("#query_brch_no",root);
			loadBrchCombo(".BrchCombo",root);
	    });	
			var select_rowData=null;
			//datagrid
			function loadAuthForUser(rowIndex, rowData){
				select_rowData = rowData;
				$("#rolesTab").datagrid("options").url="user_roles?user_id="+rowData.user_id;
				$("#rolesTab").datagrid("load");
			}
			function fmUserAction(value,row,index){
			  return '<a href="###" onclick="resetPWD({user_id:\''+row.user_id+'\',user_name:\''+row.user_name+'\'})">重置密码</a>';
			}
			function resetPWD(user){
				$.post("user_resetPwd",user,function(result){
        if (result.success){
            $.messager.show({title: "成功",msg: result.msg});
        }else $.messager.show({title: "错误",msg: result.msg}); 
	      },"json");
			}
			
			
			//user menu
			function doQueryUser(){
				var queryDate =$("#userfm").form2json();
				$("#userTab").datagrid("load",queryDate);
			}
			function addUser(){
				$("#user_dlg").dialog("open").dialog("setTitle","用户新增");
				$("#user_fm").form("clear");
				
			}
			function editUser(){
				var row =  $("#userTab").datagrid("getSelected");
				if(row){
					$("#user_dlg").dialog("open").dialog("setTitle","用户修改");
					$("#user_fm").form("load",row);
			     	$("#user_brch_no").combogrid("setText",$("#user_brch_name").val());
			     	$("#user_type").combogrid("setText",$("#user_type_name").val());
			     	var a_arr = [];
			     	a_arr = row.user_type.split(",");
			     	$('#user_type').combobox('setValues', a_arr);
				}else{
					$.messager.alert("错误","请先选择一个用户");
				}
			}
			function delUser(){
				var row =  $("#userTab").datagrid("getSelected");
				if(row){
					 $.messager.confirm('确定','是否删用户'+row.user_name,function(r){
           if (r){
               $.post("user_delUser",row, function(result) {
				       if (result.success) {
					        $.messager.show({title: "成功",msg: result.msg});
					        $("#userTab").datagrid("reload");
				       } else {
					        $.messager.show({title: "错误",msg: result.msg}); 
				       }
			         }, "json");
           
           }}); 
				}else{
					$.messager.alert("错误","请先选择一个用户");
				}
			}
			
			function addRoles(){
				
				  var rows =  $("#rolesTab").datagrid("getRows");
				  var user_id=0;
				  if(rows&&rows.length>0){
				  	user_id=rows[0].user_id;
				  }else{
				  	var row =  $("#userTab").datagrid("getSelected");
				  	if(row)user_id=row.user_id;
				  }
				  
				  if(user_id==0){
				  	  $.messager.alert("错误","请先选择一个用户");
				  }else{
				  		$("#roles_fm").form("clear");
				      $("#roles_dlg").dialog("open").dialog("setTitle","角色权限新增");
				      $("#role_user_id").val(user_id);
				  }
			
			}
			function editRoles(){
				var row =  $("#rolesTab").datagrid("getSelected");
				if(row){
				    $("#roles_dlg").dialog("open").dialog("setTitle","角色权限修改");
			      $("#roles_fm").form("load",$.Json2Perfix(row,"role"));		      
			      $("#role_brch_no").combogrid("setText",$("#role_brch_name").val());

			  }else{
			  	  $.messager.alert("错误","请先选择一行");
			  }
				
			}
			function delRoles(){
				var row =  $("#rolesTab").datagrid("getSelected");
				if(row){
					 $.messager.confirm('确定','是否删该权限配置',function(r){
           if (r){
               $.post("user_delRoles",$.Json2Perfix(row,"role"), function(result) {
				       if (result.success) {
					        $.messager.show({title: "成功",msg: result.msg});
					        $("#rolesTab").datagrid("reload");
				       } else {
					        $.messager.show({title: "错误",msg: result.msg}); 
				       }
			         }, "json");
           
           }}); 
				}else{
					$.messager.alert("错误","请先选择一行配置");
				}
			}
			
			
			//dlg
			function doSaveUser(){
				fmSubmit("#user_fm","user_saveUser",
		        function(result){
                $("#userTab").datagrid("reload");
                $("#user_dlg").dialog("close");
                if(result.backParam.isAdd){
                	  $("#roles_fm").form("reset");
				            $("#roles_dlg").dialog("open").dialog("setTitle","角色权限新增");
                    $("#role_user_id").val(result.backParam.user_id);
                }
              
               
                
		        });
			}
			
			function doSaveRoles(){
				fmSubmit("#roles_fm","user_saveRoles",
		        function(result){
		        	  $("#rolesTab").datagrid("options").url="user_roles?user_id="+result.backParam;
                $("#rolesTab").datagrid("load");
                $("#roles_dlg").dialog("close");
		        });
			}
			
			function tosetCenter(){//弹出
			    var iWidth=600;                          //弹出窗口的宽度;
			    var iHeight=350;                         //弹出窗口的高度;
			    var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置   
			    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //获得窗口的水平位置    
				var attribution ="dialogLeft:"+iLeft+"px;dialogTop:"+iTop+"px;"+"toolbar:no;location:no;directories:no;status:no;menubar:no;Scroll:yes;resizable:yes;dialogheight:350px;dialogwidth:600px";
				var url ="/map/map_set_center.jsp";
			//	var ret=window.showModalDialog(url,window,attribution,true);

			window.open ('/map/map_set_center.jsp','newwindow','height=350,width=600,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
				
			//	if (ret == undefined) {  
			//   	 ret = window.returnValue;  
			//	}
			//	if(ret!="undefine")
			//	{
		//		var retval=ret.split("|");
			//		if(retval[0]=="yes")
			//		{
			//		document.getElementById("setlat").value=retval[1];
			//		document.getElementById("setlng").value=retval[2];
			//		}
			//	}
			}
		</script>
	</head>
	<body  class="easyui-layout">
		<!--用户配置 工具栏-->
		<div id="user_toolbar" style="padding: 5px; display: none">
			<form id="userfm" style="margin: 0">
				用户名:
				<input type="text" name="user_name" id="user_name" />
				&nbsp;&nbsp;&nbsp;用户昵称:
				<input type="text" name="nick_name" id="nick_name"/>
				&nbsp;&nbsp;&nbsp;所属机构:
				<input type="text" name="brch_no" id="query_brch_no"  />
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doQueryUser()">查询</a>
				<br>
			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addUser()">增加</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delUser()">删除</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"  onclick="editUser()">修改</a>			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#userTab','user_excel')">excel导出</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-tip'" onclick="doOpenUserIconDlg();">设置用户标识</a>
			</form>
		</div>
		<!--权限配置 工具栏-->
		<div id="auth_toolbar" style="padding: 3px; display: none">
			<form id="powerfrm" style="margin: 0">
				&nbsp;
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addRoles()">增加</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delRoles()">删除</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'" onclick="editRoles()">修改</a>

			</form>
		</div>
		<div data-options="region:'center' ,title:'用户配置页面',split:true" style="background:#fafafa;overflow:hidden;" >
		<table id="userTab" class="easyui-datagrid"

			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#user_toolbar',
     	pageSize:10,singleSelect:true,url:query_url,border:false,
     	onClickRow:loadAuthForUser" >
			<thead>
				<tr>
					<th data-options="field:'user_id'" width="80">
						系统ID
					</th>
					<th data-options="field:'user_name'" width="100">
						用户名
					</th>
					<th data-options="field:'nick_name'" width="100">
						用户昵称
					</th>
					<th data-options="field:'user_icon',align:'center',formatter:function(value,row,index){if (value==''||value==null){return '未标识';} else {return value;}}" width="220">
						用户标识
					</th>
					<th data-options="field:'lng',align:'center'" width="180">
						中心经度
					</th>
					<th data-options="field:'lat',align:'center'" width="180">
						中心纬度
					</th>
					<!-- 
					<th data-options="field:'user_type_name',align:'center'" width="180">
						用户类型
					</th>
			 -->
					<th data-options="field:'brch_name',align:'center'" width="180">
						所属机构
					</th>
					<th data-options="field:'action',align:'center',formatter:fmUserAction" width="100">
						操作
					</th>
				</tr>
			</thead>
		</table>
		</div>
		<div data-options="region:'south',title:'权限配置页面',split:true" style="height:275px;background:#fafafa;overflow:hidden;">
		<table id="rolesTab" class="easyui-datagrid"
			data-options="
     	fit:true,rownumbers:true,pagination:true,
     	toolbar:'#auth_toolbar',border:false,
     	pageSize:20,singleSelect:true"
     	>
			<thead>
				<tr>
					<th data-options="field:'user_id',align:'center'" width="160">
						用户ID
					</th>
		 
					<th data-options="field:'role_name',align:'center'" width="160">
						对应角色
					</th>
				</tr>
			</thead>
		</table>
		</div>

		<div id="addoredit_usericon_dlg" class="easyui-dialog" title="用户标识" closed="true" data-options="iconCls:'icon-add',buttons:'#addoredit_usericon_buttons',closed:true" style="width:500px;height:470px;padding:10px">
        	<form id="addoredit_usericon_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str1"></div></legend>
              		<div id="show_str2"></div>
            	</fieldset>
            	<input type="hidden" id="hidden_user_icon" name="hidden_user_icon" value="" />
            	<fieldset>
            		<legend><font color='red'>目前选中的用户标识：</font></legend>
            		<div id="show_str3">无</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="addoredit_usericon_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addoredit_usericon_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="doAddOrEditUserIcon();">确认</a>
    	</div>
    	
    	<div id="confirm_usericon_dlg" class="easyui-dialog" title="确认提交用户标识？" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_usericon_buttons',closed:true" style="width:400px;height:150px;padding:10px">
        	<div id="show_str4">
        	</div>
    	</div>
    	<div id="confirm_usericon_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_usericon_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitUserIcon();">确认</a>
    	</div>


		<!--用户配置 弹出框-->
		 <div id="user_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'设置中心点',plain:true,iconCls:'icon-add',	    handler:function(){tosetCenter();}},
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){doSaveUser();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#user_dlg').dialog('close');}  }]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="user_fm" method="post">
				
				<input type="hidden"   id="user_id" name="user_id" value="0"/>
				<div class="form_item col1">
				   <label>用户名：</label>
				   <input type="text" name="user_name" class="easyui-validatebox" style="width:200px" 
				   data-options="required:true,delay:500,validType:'Ajax[\'user_nameCheck\',\'user_name\',\'user_id\']'" />
			  </div>
			  <div class="form_item col1">
				   <label>用户昵称：</label>
				   <input type="text" name="nick_name" class="easyui-validatebox" style="width:200px" 
				   data-options="required:true" />
			  </div>
			
			  <div class="form_item col1">
				   <label>所属机构：</label>
				   <input type="text" class="BrchCombo" name="brch_no"  id="user_brch_no" style="width:200px" data-options="required:true"/>
				   <input type="hidden" id="user_brch_name" name="brch_name"  />
			  </div>  
			  <!-- 
			   <div class="form_item col1">
				   <label>用户类型：</label>
				   	<input type="text"  name="user_type" id="user_type" class="easyui-combobox" style="width: 150px" 
							multiple="true"	data-options="required:true,missingMessage:'必选',valueField:'value',textField:'text',url:'/param_getUserType'" />
					 
			 	   <input type="hidden" id="user_type_name" name="user_type_name"  />
			  </div>
			   -->
			    <div class="form_item col1">
				   <label>中心经度：</label>
				   <input type="text" id="setlng" name="lng" class="easyui-validatebox" style="width:200px" />
			  </div>
			  <div class="form_item col1">
				   <label>中心纬度：</label>
				   <input type="text" id="setlat"  name="lat" class="easyui-validatebox" style="width:200px" />
			  </div>
			
			  </form>
		</div>
		<!--权限配置 弹出框-->
    <div id="roles_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){doSaveRoles();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#roles_dlg').dialog('close');}  }]"
				style="width:400px;height:300px;padding:10px 10px;">
		
				<form id="roles_fm" method="post">
				<input type="hidden"    name="role.sn" />
			  
			  <div class="form_item col1">
				   <label>用户ID：</label>
				   <input type="text" id="role_user_id"  name="role.user_id"  value="0" style="width:200px" readonly="readonly" />
				   
			  </div>
			  <!-- 
			  <div class="form_item col1">
				   <label>角色机构：</label>
				   <input type="text" class="BrchCombo" name="role.brch_no" id="role_brch_no"  style="width:200px" data-options="required:true"/>
				   <input type="hidden"id="role_brch_name" name="role.brch_name"/>
				   
			  </div> -->
			  <div class="form_item col1">
				   <label>对应角色：</label>
				   <input type="text"  name="role.role_id"  class="easyui-combobox" style="width:200px" 
				   data-options="required:true,url:'role_combo?role_flag=1',valueField:'role_id',textField:'role_name'"/>
			  </div>
			  </form>
		</div>

    	
    	<div style="display:none">
      	<!-- 引入excel 导出选项  -->
      		<jsp:include page="/sys/UIExcel.jsp" >
      		</jsp:include>
      	</div>
	</body>
</html>
