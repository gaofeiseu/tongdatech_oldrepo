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
		<link rel="stylesheet" type="text/css" href="/map/mapjs/farbtastic12/farbtastic/farbtastic.css"/>
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/farbtastic12/farbtastic/farbtastic.js"></script>
		<script type="text/javascript" src="/business/js/dataManage.js"></script>
		<!--<script type="text/javascript" src="/map/mapjs/map_data_manage_gf.js"></script>  -->	
		<script type="text/javascript">
			//init
		  
   		   $(document).ready(function(){
	
	 	   });	
   		function toQuery(){//弹出条件选择页面 
   		   if($('#sn').combobox('getValue')=="")
	   	   {
	   	    	  $.messager.show({title: "信息",msg: "请先选择数据类型再进行查询"});
	   	    	  return; 
	   	   } 
   		   else
   		   {
	   		    var iWidth=600;                          //弹出窗口的宽度;
	   		    var iHeight=350;                         //弹出窗口的高度;
	   		    var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置   
	   		    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //获得窗口的水平位置    
	   			var attribution ="dialogLeft:"+iLeft+"px;dialogTop:"+iTop+"px;"+"toolbar:no;location:no;directories:no;status:no;menubar:no;Scroll:yes;resizable:yes;dialogheight:350px;dialogwidth:600px";
	   			var url ="/data_manage_toQueryColumn.action?SN="+$('#sn').combobox('getValue');
	   		 
				window.open (url,'newwindow','height=350,width=600,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
				
   		   }
   		}
  
		</script>
	</head>
	<body  class="easyui-layout">
		<!--用户配置 工具栏-->
		<div id="user_toolbar" style="padding: 5px; display: none">
			<form id="userfm" style="margin: 0">
		 	数据类型：
				<input type="text"  name="SN" id="sn" class="easyui-combobox" style="width: 150px" 
						data-options="required:true,missingMessage:'必选',valueField:'value',url:'/data_manage_getSN.action',textField:'text',onChange:getColumn"/>
							
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'" onclick="toQuery()">设置查询条件</a>	
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doQuery()">查询</a>
				<br>
				     <span id="sdfasdf" style="display:none;margin-top:20px;">
                      	<span id="queryString3Show">所选条件：</span>
		                 	<INPUT type="hidden"  id="queryString3" name="query_property" value=""/>
                       </span>
		         	 
				<br>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addData()">增加</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delData()">删除</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"  onclick="editData()">修改</a>			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expportExcel();">excel导出</a>
		
			</form>
		</div>
 
		<div data-options="region:'center' ,title:'数据管理',split:true" style="background:#fafafa;overflow:hidden;" >
		<table id="dataTab" class="easyui-datagrid" 
			data-options="fit:true,rownumbers:true,pagination:true,toolbar:'#user_toolbar',
     	pageSize:10,url:'data_manage_doquery',  border:false " >
	     <thead>
	     </thead>
		</table>
		</div>
 


		<!--用户配置 弹出框-->
		 <div id="user_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){doSave();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#user_dlg').dialog('close');}  }]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="user_fm" method="post" enctype="multipart/form-data" >
				
			 
			  </form>
		</div>




		
		<!-- Gf  st -->
		<div id="addMarkers_point_dlg" class="easyui-dialog" title="点类型特征量设置" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_point_buttons',closed:true" style="width:550px;height:470px;padding:10px">
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
    	<div id="addMarkers_point_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_point_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_addMarkers_point_fm();">确认</a>
    	</div>

		<div id="confirm_usericon_dlg" class="easyui-dialog" title="确认提交特征量？" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_usericon_buttons',closed:true" style="width:400px;height:150px;padding:10px">
        	<div id="show_str4">
        	</div>
    	</div>
    	<div id="confirm_usericon_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_usericon_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitUserIcon();">确认</a>
    	</div>



		<div id="addMarkers_line_dlg" class="easyui-dialog" title="线类型特征量设置" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_line_buttons',closed:true" style="width:360px;height:400px;padding:10px">
        	<form id="addMarkers_line_fm" method="post" >
        		<div><font size='3'><b>线路颜色选择</b></font></div>
        		<div><hr></div>
        		<div>颜色:<input type="text" id="select_my_line_color" name="select_my_line_color" value="#FF0000" /></div>
        		<div><div id="linecolorpicker"></div></div>
        		<div>透明度<input id="line_strokeOpacity" name="line_strokeOpacity" value="1.0" /><font color="red">0.0-1.0(1.0表示不透明)</font></div>
        		<div>粗细度<input id="line_strokeWeight" name="line_strokeWeight" value="2" /><font color="red">最小为2(越大越粗)</font></div>
        	</form>
    	</div>
    	<div id="addMarkers_line_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_line_dlg').dialog('close');">取消</a>
    		<a href="#" id="line_color_confirm" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="line_color_confirm();">确认</a>
    	</div>

		
		<div id="confirm_line_dlg" class="easyui-dialog" title="确认提交线类型特征量？" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_line_buttons',closed:true" style="width:400px;height:150px;padding:10px">
			<input type="hidden" id="line_markers" />
        	<div id="show_str5">
        	</div>
    	</div>
    	<div id="confirm_line_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_line_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitLineMarker();">确认</a>
    	</div>


		<div id="addMarkers_area_dlg" class="easyui-dialog" title="面类型特征量设置" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_area_buttons',closed:true" style="width:240px;height:360px;padding:10px">
        	<form id="addMarkers_area_fm" method="post" >
        		<div><font size='3'><b>面颜色选择</b></font></div>
        		<div><hr></div>
        		<div>颜色:<input type="text" id="select_my_area_color" name="select_my_area_color" value="#FF0000" /></div>
        		<div><div id="areacolorpicker"></div></div>
        	</form>
    	</div>
    	<div id="addMarkers_area_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_area_dlg').dialog('close');">取消</a>
    		<a href="#" id="area_color_confirm" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="area_color_confirm();">确认</a>
    	</div>

		
		<div id="confirm_area_dlg" class="easyui-dialog" title="确认提交线类型特征量？" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_area_buttons',closed:true" style="width:400px;height:150px;padding:10px">
			<input type="hidden" id="area_markers" />
        	<div id="show_str6">
        	</div>
    	</div>
    	<div id="confirm_area_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_area_dlg').dialog('close');">取消</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitAreaMarker();">确认</a>
    	</div>

		<!-- Gf  ed -->



    	
    	<div style="display:none">
      	<!-- 引入excel 导出选项  -->
      		<jsp:include page="/sys/UIExcel.jsp" >
      		</jsp:include>
      	</div>
	</body>
</html>
