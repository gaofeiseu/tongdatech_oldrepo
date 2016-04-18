<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>My JSP 'role_init.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<xl:resource></xl:resource>
  </head>
  
  <script type="text/javascript">
  	var url;
  	var query_url= "role_query.action";
  	function doquery(){
  		//url = "role_query.action";	
  		var fmdata = $('#frm').form2json();
  		//$('#data1').datagrid("options").url=url;
  		$('#data1').datagrid("load", fmdata);
  	}
  	
  	function addrole(){
  		$('#dialog1').dialog('open').dialog('setTitle','新增');
  		$('#editfm').form('clear');
  		url = "role_add.action";
  	}
  	
  	function editrole(){
  		var rows = $('#data1').datagrid('getSelections');
  		if(rows.length == 0){
  			alert("请选择需要修改的记录!");
  			return ;
  		}
  		var row = $('#data1').datagrid('getSelected');
  		if(rows){
  			if(rows.length>1){
  				alert("请逐条修改");
  				return ;
  			}
  			$('#dialog1').dialog('open').dialog('setTitle','编辑');
  			$('#editfm').form('load',row);
  			url = "role_edit.action";
  		}
  	}
  	
  	function removerole(){
  		url = "role_remove.action";
  		var rows = $('#data1').datagrid('getSelections');
  		//alert(rows.toString());
  		if(rows.length == 0){
  			alert("请选择需要删除的记录!");
  			return ;
  		}else{
  			$.messager.confirm('确认','确定要删除选中记录吗?',function(r){
  				if(r){
  					var sns = [];
  					for(var i=0;i<rows.length;i++){
  						sns.push(rows[i].role_id);
  					}
  					var datas = sns.toString();
  					
  					$.post(url,"sns=" + datas,function(result){
  						if (result.success) {
							$('#data1').datagrid('reload');
							alert(result.msg);
						} else {
							alert(result.msg);
						}
  					},'json');
  					
  				}
  			});
  		}
  	}
  	
  	function save_data(){
  		if($('#editfm').form('validate') == false) return ;
  		var fmdata = $('#editfm').form2json();
  		$.ajax({
  			url : url,
  			type :"POST",
  			cache : false,
  			data : fmdata,
  			success : function(result) {
				var result = eval("(" + result + ")");
				if (result.success) {
					$('#dialog1').dialog('close'); // close the dialog
					$('#data1').datagrid('reload'); // reload the user data
					alert(result.msg);
				} else {
					alert(result.msg);
				}
			}
  			
  		});
  	}
  	
  	
  	function menurole(){
  	    var rows = $('#data1').datagrid('getSelections');
  		if(rows.length == 0){
  			alert("请选择需要修改的记录!");
  			return ;
  		}else if(rows.length>1){
  			alert("请逐条赋权!");
  			return ;
  		}
  		var row = $('#data1').datagrid('getSelected');
  		
  	    $("#menuTree").tree({
            url:'menu_treeChecked?role_id='+row.role_id,
		    method:'post',
		    checkbox:true,
		    cascadeCheck:false
  	    });
  		$('#dialog2').dialog('open');
  		
  	}
  	
  	function savemenu(){
  		var nodes=$('#menuTree').tree('getChecked');
  		var s = '';
  		
  		var row = $('#data1').datagrid('getSelected');
  		//alert(row.role_id);
  		
  		
  		for(var i=0;i<nodes.length;i++){
  		//alert(nodes[i].id);
  			var node1 = $('#menuTree').tree('getParent',nodes[i].target);
  			if(node1 == null) continue;
  			if(!node1.checked){
  				alert(node1.text+"未选中!");
  				return ;
  			}
  		}
  		
  		for(var j=0;j<nodes.length;j++){
  				if(s!='') s+=',';
  				s+=nodes[j].id;
  		}
  		//alert(s);
  		url = "role_savemenu.action?role_id="+row.role_id;
  		$.post(url,"sns="+s,function(result){
  			var result = eval("(" + result + ")");
  			if(result.success){
  				$('#dialog2').dialog('close');
  				alert(result.msg);
  			}else{
  				alert(result.msg);
  			}
  		});
  	}
  	
  	
  </script>
  
  <body>

    <div id="toolbar" style="padding:5px;display:none">
	    	<form id="frm" style="margin:0">&nbsp; 
	  			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onClick="addrole()">增加</a>
	  			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onClick="editrole()">编辑</a>
	  			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onClick="removerole()">删除</a>
	  			<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onClick="menurole()">模块赋权</a>
	  		</form>
	    
    </div>
    
    <table id="data1" class="easyui-datagrid" data-options="title:'角色管理',fit:true,rownumbers:true,
    url:query_url,toolbar:'#toolbar'">
    			<thead>
    				<tr>
    					<th data-options="field:'role_id',width:120">角色ID</th>
    					<th data-options="field:'role_name',width:120">角色名称</th>
    					<th data-options="field:'order_id',width:120" >角色排序</th>
    					<th data-options="field:'role_flag',width:120" >角色状态</th>
    					<th data-options="field:'role_img',width:200" >角色图像</th>
    				</tr>
    			</thead>
    </table>
    
    <!-- 新增、编辑窗口 -->
    <div id="dialog1" class="easyui-dialog" style="width:400px;height:300px" data-options="buttons:'#dialog-buttons',closed:true">
    	<form id="editfm" method="post" style="padding: 20px 10px">
    		<input type="hidden" name="role_id" />
    		
    		<div class="form_item col1">
    			<label>角色名称：</label>
    			<input type="text" name="role_name" class="easyui-validatebox" data-options="required:true" style="width:180px" />
    		</div>
    		
    		<div class="form_item col1">
    			<label>角色排序：</label>
    			<input type="text" name="order_id" class="easyui-validatebox" style="width:180px"/>
    		</div>
    		
    		<div class="form_item col1">
    			<label>角色图像：</label>
    			<input type="text" name="role_img" class="easyui-validatebox" style="width:180px"/>
    		</div>
    		
    		<div class="form_item col1">
    			<label>角色状态：</label>
    			<select name="role_flag" class="easyui-combobox" style="width:184px">
    				<option value="1" selected>正常</option>
    				<option value="0">删除</option>
    			</select>
    		</div>
    	</form>
    </div>
    
    <div id="dialog-buttons">
    	<a href="#" id="save" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onClick="save_data()">保存</a>
    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onClick="javascript:$('#dialog1').dialog('close')">取消</a>
    </div>

	<!-- 模块赋权 -->    
    <div id="dialog2" class="easyui-dialog" style="width:400px;height:400px" data-options="title:'模块树',closed:true,buttons:'#menu-btns'" >
        <div id="menuTree" style="padding: 20px 10px"></div>
    </div>
    <div id="menu-btns">
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onClick="savemenu()" >确定</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onClick="javascript:$('#dialog2').dialog('close')" >取消</a>
	</div>
   	 	
  </body>
</html>
