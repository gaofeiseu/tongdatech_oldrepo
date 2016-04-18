<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<!-- author Mr.GaoFei -->
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>标注类型维护</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<xl:resource></xl:resource>
		<script type="text/javascript">
			var t_usertype = '${session.userInfo.user_type}';
			//console.log('t_usertype-------->'+t_usertype);
		</script>
		<script type="text/javascript" src="/map/mapjs/map_markerclass_maintance.js"></script>
		<script type="text/javascript">
		
	 	var root="${session.userInfo.brch_no}"; 
			window.onload=function()
			{
				$('#childclass_parentclass_sn').combobox('select', '1');
				$('#status').combobox('select', '1');
				
			};

		      $(document).ready(function(){
		     
			 	 
		      });		
			/**
			 * 打开子类型复制窗口
			 */
			function openCopyDlg(){
				var row =  $("#data1").datagrid("getSelected");
				if(row){
					$("#childclass_copy_dlg").dialog("open").dialog("setTitle","子类型复制");
					$("#childclass_copy_fm").form("load",row);
		 
				}else{
					$.messager.alert("错误","请先选择一个子类型");
				}
			}
			function copysave()
			{
				fmSubmit("#childclass_copy_fm","map_markerclassmaintance_copychildclass.action",
				        function(result){
		                $("#data1").datagrid("reload");
		                $("#childclass_copy_dlg").dialog("close");
		          
		         });
			}
			function toQuery(obj_name){//弹出条件选择页面 
			    var iWidth=750;                          //弹出窗口的宽度;
			    var iHeight=550;                         //弹出窗口的高度;
			    var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置   
			    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //获得窗口的水平位置    
			    var checkBrch= $("#childclass_"+obj_name+"_brch").val();
			 	var url ="/map_markerclassmaintance_brchTree.action?obj_name="+obj_name+"&checkBrch="+checkBrch;
				 
				window.open (url,'newwindow','height=550,width=950,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
				
//			 
			}
						
		</script>
	</head>
	<body  class="easyui-layout">
		<div id="first_class_toolbar" style="padding: 3px; display: none">
			<form id="first_class_frm" style="margin: 0">
				&nbsp;
				<input type="text" id="childclass_parentclass_sn" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.MARKER_TYPE},onChange:function(newvalue,oldvalue){loadData1ForComboSelectChange(newvalue);}"/>
					
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openChildClassAddDlg();">增加</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"	onclick="delete_childclass();">删除</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="openChildClassEditDlg();">修改</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cut'" onclick="openCopyDlg();">复制</a>	
					
						<input type="text"  id="status"  class="easyui-combobox" style="width: 150px" 
								data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS},onChange:function(newvalue,oldvalue){loadData1ForComboSelectChange1(newvalue);}"/>
				
			</form>
		</div>
		
		<div id="second_class_toolbar" style="padding: 3px; display: none">
			<form id="second_class_frm" style="margin: 0">
				&nbsp;
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openClassPropertyAddDlg();">子类型属性新增</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'" onclick="delete_classproperty();">子类型属性删除</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="openClassPropertyEditDlg();">子类型属性修改</a>
			</form>
		</div>
		
		<div data-options="region:'north' ,title:'子类型页面',split:true" style="height:350px;background:#fafafa;overflow:hidden;" >
		<table id="data1" class="easyui-datagrid"
			style="height:30%;"
			data-options="
     	fit:true,rownumbers:true,toolbar:'#first_class_toolbar',pagination:true,pageSize:20,url:'',
     	singleSelect:true,onClickRow:function(rowIndex, rowData)
     	{initData1Params(rowIndex,rowData);},">
			<thead>
				<tr>
					<th data-options="field:'childclass_sn',align:'center'" width="180">
						子类型ID
					</th>
					<th data-options="field:'childclass_name',align:'center'" width="280">
						子类型名称
					</th>
					<th data-options="field:'childclass_add_brch',formatter:function(value,row,index){if (value){return row.childclass_add_brch;} else {return '错误数据';}},align:'center'" width="150">
						创建部门
					</th>
				 	<th data-options="field:'childclass_brchno',formatter:function(value,row,index){return row.childclass_brchstr;},align:'center'" width="550">
						可查看部门
					</th>
				</tr>
			</thead>
		</table>
		</div>
		
		<div data-options="region:'center',title:'子类型属性页面'" style="height:250px;background:#fafafa;overflow:hidden;">
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
						子类型属性ID
					</th>
					<th data-options="field:'classproperty_parentclass_id',align:'center'" width="80">
						所属子类型ID
					</th>
					<th data-options="field:'classproperty_parentclass_tn',align:'center'" width="150">
						子类型表名
					</th>
					<th data-options="field:'classproperty_parentclass_cn',align:'center'" width="150">
						子类型属性列名
					</th>
					<th data-options="field:'classproperty_name',align:'center'" width="100">
						子类型属性名称
					</th>
					<th data-options="field:'classproperty_type',formatter:function(value,row,index){if (value){return row.classproperty_type_str;} else {return '错误数据';}},align:'center'" width="100">
						子类型属性类型
					</th>
					<th data-options="field:'classproperty_ifnull',formatter:function(value,row,index){if (value){return row.classproperty_ifnull_str;} else {return '错误数据';}},align:'center'" width="120">
						子类型属性是否可空
					</th>
					<th data-options="field:'classproperty_size',formatter:function(value,row,index){if (value){if(value=='NULL'||value=='null'){return '-';} else {return value;}} else {return '错误数据';}},align:'center'" width="120">
						子类型属性字符长度
					</th>
					<th data-options="field:'classproperty_status',formatter:function(value,row,index){if (value){return row.classproperty_status_str;} else {return '错误数据';}},align:'center'" width="120">
						子类型当前状态
					</th>
				</tr>
			</thead>
		</table>
		</div>
		<div id="childclass_copy_dlg" class="easyui-dialog" title="子类型复制" closed="true" data-options="iconCls:'icon-edit',closed:true,
			buttons:[
			 	{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){copysave();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#childclass_copy_dlg').dialog('close');}  }]" style="width:450px;height:300px;padding:10px">
        	<form id="childclass_copy_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str2"></div></legend>
              		<input type="hidden" name="childclass_sn" id="childclass_sn_copy"></input>
            		<div class="form_item col1">
               	 		<label>新子类型名称:</label>
                		<input name="childclass_name" id="childclass_copy_classname" class="easyui-validatebox" required="true" />
            		</div>
           
           
            	</fieldset>
        	</form>
    	</div>
		<div id="childclass_add_dlg" class="easyui-dialog" title="子类型新增" closed="true" data-options="iconCls:'icon-add',buttons:'#childclass_add_buttons',closed:true" style="width:450px;height:300px;padding:10px">
        	<form id="childclass_add_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str1"></div></legend>
              		<input type="hidden" name="childclass_add_for_parentclassid" id="childclass_add_for_parentclassid"></input>
            		<div class="form_item col1">
               	 		<label>子类型名称:</label>
                		<input name="childclass_add_classname" id="childclass_add_classname" class="easyui-validatebox" required="true" />
            		</div>
            		<div class="form_item col1">
            			<label>可查看部门:</label>
             			<input type="hidden" id="childclass_add_brch" name="childclass_add_brch" value="">
             			<input type="hidden" id="childclass_add_brch_type" name="childclass_add_brch_type" value="">
						<input type="text" id="childclass_add_brch_name"  name="childclass_add_brch_name"   value=""   readonly  />
						<a href="#" onclick="toQuery('add')">+选择+</a>
            		</div>
            		<div class="form_item col1">
                		<label>子类型状态:</label>
						<input type="text" id="childclass_add_status" name="childclass_add_status" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="childclass_add_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#childclass_add_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_childclass_add_fm();">确认</a>
    	</div>
		
		<div id="childclass_edit_dlg" class="easyui-dialog" title="子类型修改" closed="true" data-options="iconCls:'icon-edit',buttons:'#childclass_edit_buttons',closed:true" style="width:450px;height:300px;padding:10px">
        	<form id="childclass_edit_fm" method="post" >
        		<fieldset>
              		<legend><!-- <div id="show_str2"></div> --></legend>
              		<input type="hidden" name="childclasssn_edit" id="childclasssn_edit"></input>
            		<div class="form_item col1">
               	 		<label>子类型名称:</label>
                		<input name="childclass_edit_classname" id="childclass_edit_classname" class="easyui-validatebox" required="true" />
            		</div>
            		<div class="form_item col1">
            		  	<label>可查看部门:</label>
             
						<input type="hidden" id="childclass_edit_brch"  name="childclass_edit_brch"    />
						<input type="hidden" id="childclass_edit_brch_type" name="childclass_edit_brch_type" value="">
						<input type="text" id="childclass_edit_brch_name"  name="childclass_edit_brch_name"        readonly />
						<a href="#" onclick="toQuery('edit')">+选择+</a>
						
            		</div>
            		<div class="form_item col1">
                		<label>子类型状态:</label>
						<input type="text" id="childclass_edit_status" name="childclass_edit_status" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="childclass_edit_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#childclass_edit_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_childclass_edit_fm();">确认</a>
    	</div>
		
		<div id="classproperty_add_dlg" class="easyui-dialog" title="子类型属性添加" closed="true" data-options="iconCls:'icon-edit',buttons:'#classproperty_add_buttons',closed:true" style="width:350px;height:350px;padding:10px">
        	<form id="classproperty_add_fm" method="post" >
        		<fieldset>
              		<legend>子类型属性添加</legend>
              		<input type="hidden" name="classproperty_add_sn" id="classproperty_add_sn"></input>
            		<div class="form_item col1">
               	 		<label>属性名称:</label>
                		<input name="classproperty_add_name" id="classproperty_add_name" class="easyui-validatebox" data-options="required:true" />
            		</div>
            		<div class="form_item col1">
               	 		<label>属性类型:</label>
                		<input type="text" id="classproperty_add_type" name="classproperty_add_type" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.CLASS_COLUMNTYPE},onChange:function(newvalue,oldvalue){changeForComboSelectChange(newvalue);}"/>
            		</div>
            		<div class="form_item col1">
               	 		<label>属性是否为空:</label>
                		<input type="text" id="classproperty_add_ifnull" name="classproperty_add_ifnull" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.CLASS_NULLABLE}"/>
            		</div>
            		<div class="form_item col1">
               	 		<label>属性长度:</label>
                		<input name="classproperty_add_size" id="classproperty_add_size" class="easyui-validatebox" data-options="required:true,validType:'number'" />
            		</div>
            		<div class="form_item col1">
               	 		<label>属性状态:</label>
                		<input type="text" id="classproperty_add_status" name="classproperty_add_status" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            		<font color='red'>子类型的第一个字段是展示字段，请不要为空！</font>
            		<br/>
            		<font color='red'>每一个子类型请不要设置多于1个的图片属性！</font>
            		<br/>
            		<font color='red'><div id="show_str3"></div></font>
            	</fieldset>
        	</form>
    	</div>
    	<div id="classproperty_add_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#classproperty_add_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_classproperty_add_fm();">确认</a>
    	</div>
		
		<div id="classproperty_edit_dlg" class="easyui-dialog" title="子类型属性修改" closed="true" data-options="iconCls:'icon-edit',buttons:'#classproperty_edit_buttons',closed:true" style="width:350px;height:240px;padding:10px">
        	<form id="classproperty_edit_fm" method="post" >
        		<fieldset>
              		<legend>子类型属性修改</legend>
              		<input type="hidden" name="classproperty_edit_sn" id="classproperty_edit_sn"></input>
            		<div class="form_item col1">
               	 		<label>属性名称:</label>
                		<input name="classproperty_edit_name" id="classproperty_edit_name" class="easyui-validatebox" data-options="required:true" />
            		</div>
            		<div class="form_item col1">
               	 		<label>属性状态:</label>
                		<input type="text" id="classproperty_edit_status" name="classproperty_edit_status" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.FIRST_CLASS_STATUS}"/>
            		</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="classproperty_edit_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#classproperty_edit_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_classproperty_edit_fm();">确认</a>
    	</div>
	</body>
</html>