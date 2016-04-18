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

		function onDrop(target,source,point){
	
//		    alert(tNode.text+"-"+source.text+"-"+point);
		    var tNode=$(this).tree("getNode",target);
		    var data ={menu_id:source.id,menu_parent:tNode.id,treePoint:point};
		    $.post("menu_dragTree",data, function(result) {
				if (result.success) {
					$.messager.show({title: "成功",msg: result.msg});
				} else {
					$.messager.show({title: "错误",msg: result.msg}); 
					$("#menuTree").tree("reload");
				}
				
			}, "json");
		}
		function onClick(node){
		   $("#fm").form("clear");
		   $("#fm").form("load", node.obj);
		}
		function onLoadSuccess(node, data){
		    var menu_id = $("#menu_id").val();
		    if(menu_id!=""&&menu_id!="0"){
		     expand(menu_id);
            }
		}
		
		
		function expand(val){
		    if(val==undefined||val==null)val=$("#menuSearch").combobox("getValue");
		    var tar=$("#menuTree").tree("find",val);
		    if(tar!=undefined){
		      $("#menuTree").tree("expandTo",tar.target);
		      $("#menuTree").tree("select",tar.target);
		    }
		    
		}
		//buttons
		function toggleDnd(){
		    var flag=$("#menuTree").tree("options").dnd;
		    if(!flag){
		       $("#menuTree").tree("enableDnd");
		       $("#toggleDnd").find(".l-btn-text").html("关闭拖动编辑");
		    }else{
		       $("#menuTree").tree("disableDnd");
		       $("#toggleDnd").find(".l-btn-text").html("打开拖动编辑");
		    }
		}
		function areaAuth(){
		    var menu_id = $("#menu_id").val();
		    if(menu_id==""||menu_id=="0"){$.messager.alert("警告","请先选择一个菜单");return;}
		    $("#dlg").dialog("open");
		    $("#area_tree").tree({
		       url:"menu_treeArea?menu_id="+menu_id,
		       checkbox:true,
		       //cascadeCheck:false,
		       onContextMenu:function(e,node){
                e.preventDefault();
                $(this).tree("select",node.target);
                $("#mm").menu("show",{
                    left: e.pageX,
                    top: e.pageY
                });
               }    
		    });
		}
		function addMenu(){
		    $("#fm").form("clear");
		}
		function saveMenu(){		   
	        //@see /weblib/js/easyui-common.js
	        
		    fmSubmit("#fm","menu_menuSave",
		        function(result){
		        $("#menuTree").tree("reload");
		        $("#menu_id").val(result.backParam);
		        });
		}
		
		//tree menu
		function checkall(){
		   var node = $("#area_tree").tree("getSelected");
		   $("#area_tree").tree("check",node.target);
		   if($("#area_tree").tree("isLeaf",node.target))return;
		   
		   var children = $("#area_tree").tree("getChildren",node.target);
		   for(i in children){
		       $("#area_tree").tree("check",children[i].target);
		   }
		}
		function uncheckall(){
		   var node = $("#area_tree").tree("getSelected");
		   $("#area_tree").tree("uncheck",node.target);
		   if($("#area_tree").tree("isLeaf",node.target))return;
		   
		   var children = $("#area_tree").tree("getChildren",node.target);
		   for(i in children){
		       $("#area_tree").tree("uncheck",children[i].target);
		   }
		}
		
		function menuarea()
		{
			window.location.href="/demo/menuarea_query.action";
		}
		
		function menurole()
		{
			window.location.href="/demo/menurole_query.action";
		}
		
		function areaSave(){
		    var menu_id = $("#menu_id").val();
		    var nodes   = $("#area_tree").tree("getChecked");
		    var areas = [];
		    for(i in nodes){    
		       areas.push(nodes[i].id);
		    }
		    var data={"menu_id":menu_id,"areas":areas};
		    $.post("menu_areaSave",data, function(result) {
						if (result.success) {
							$.messager.show({title: "成功",msg: result.msg});
						} else {
							$.messager.show({title: "错误",msg: result.msg}); 
						}
						$("#dlg").dialog("close");
			}, "json");
		}
		</script>

	</head>

	<body class="easyui-layout">
     
		<div data-options="region:'west',split:true" title="菜单树" style="width: 300px;">
		    <div class="easyui-layout" data-options="fit:true" >            
                <div data-options="region:'center',border:false">
                    <ul class="easyui-tree" id="menuTree" data-options="
		            url:'menu_tree',
		            method:'post',
		            onDrop:onDrop,
		            onClick:onClick,
		            onLoadSuccess:onLoadSuccess" 
		            style="margin: 20px 10px 10px 10px"></ul>
                </div>
                <div data-options="region:'south',border:false,split:true" style="height:70px">
                    <div style="margin: 20px 10px 10px 20px">
		                c<input id="menuSearch" class="easyui-combobox" data-options="
                        valueField: 'menu_id',
                        textField: 'menu_name',
                        url: 'search_menu',
                        mode:'remote',
                        onSelect:expand">
                        <a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-filter'" onclick="expand()">展开树</a>
		            </div>
                </div>
            </div>
		    
		</div>
		<div data-options="region:'center'" title="菜单管理" style="background: #fafafa; overflow: hidden;">

			<div  style="padding: 10px;">



				<a href="###" class="easyui-linkbutton" id="toggleDnd"
					data-options="plain:true,iconCls:'icon-unlock'"
					onclick="toggleDnd()">打开拖动编辑</a>
				<span class="line">|</span>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"
					onclick="areaAuth()">地区权限</a>
				<span class="line">|</span>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'"
					onclick="addMenu()">新增菜单</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-save'"
					onclick="saveMenu()">保存菜单</a>
				<span class="line">|</span>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-table1'"
					onclick="menuarea()">菜单地区表</a>  
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-table2'"
					onclick="menurole()">菜单角色表</a>
				    
			    <hr>
			    <form id="fm">
			        <input type="hidden" name="menu_id" id="menu_id"/>
			        <div class="form_item col1">
				        <label>菜单名称:</label>
				        <input type="text" name="menu_name" class="easyui-validatebox" data-options="required:true,delay:500,validType:'Ajax[\'menu_nameCheck\',\'menu_name\',\'menu_id\']'" style="width:250px"/>
			            
			        </div>
			        <div class="form_item col1">
				        <label>菜单链接:</label>
				        <input type="text" name="menu_url" class="easyui-validatebox" style="width:250px"/>
			        </div> 
			        <div class="form_item col1">
				        <label>菜单样式:</label>
				        <input type="text" name="menu_style" class="easyui-validatebox" style="width:250px"/>
			        </div> 
			         <div class="form_item col1">
				        <label>是否需要IP绑定:</label>
				     		<input type="text"  name="ip_flag" id="ip_flag" class="easyui-combobox" style="width: 150px" 
								data-options="required:true,missingMessage:'必选',valueField:'value',textField:'text',data:${application.ParamJson.BOOLEAN_FLAG}"/>
					
			        </div> 
			    </form>
				
				



			</div>




		</div>
		<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,title:'地区权限配置',
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){areaSave();}                   },
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#dlg').dialog('close');}  }]"
				 style="width:400px;height:300px;padding:10px 10px;">
				 <span style="color:#999">*右击树节点全选\全不选 </span>
				 <div id="area_tree"></div>
	    </div>
	    <div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="checkall()">全选</div>
        <div onclick="uncheckall()">全不选</div>
        </div>
	</body>
</html>
