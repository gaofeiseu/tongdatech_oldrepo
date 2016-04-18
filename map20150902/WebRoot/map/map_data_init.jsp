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
			<script type="text/javascript" src="/map/mapjs/map_data_manage.js"></script>
			<script type="text/javascript" src="/map/mapjs/map_data_manage_gf.js"></script>
		<script type="text/javascript">
			//init
		  
   		   $(document).ready(function(){
	
	 	   });	
			
  
		</script>
	</head>
	<body  class="easyui-layout">
		<!--�û����� ������-->
		<div id="user_toolbar" style="padding: 5px; display: none">
			<form id="userfm" style="margin: 0">
				�������:
				<input type="text"  name="marker_class" id="marker_class" class="easyui-combobox" style="width: 150px" 
						data-options="required:true,missingMessage:'��ѡ',valueField:'value',textField:'text',data:${application.ParamJson.MARKER_TYPE},onChange:getselect"/>
				С��:
				<input type="text"  name="SN" id="sn" class="easyui-combobox" style="width: 150px" 
						data-options="required:true,missingMessage:'��ѡ',valueField:'value',textField:'text',onChange:getColumn"/>
								
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doQuery()">��ѯ</a>
				<br>
			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="addData()">����</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delData()">ɾ��</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-edit'"  onclick="editData()">�޸�</a>			
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expportExcel();">excel����</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-tip'" onclick="setMarkers();">����������</a>

			</form>
		</div>
 
		<div data-options="region:'center' ,title:'��ͼ���ݹ���',split:true" style="background:#fafafa;overflow:hidden;" >
		<table id="dataTab" class="easyui-datagrid" 
			data-options="fit:true,rownumbers:true,pagination:true,toolbar:'#user_toolbar',
     	pageSize:10,url:'map_data_doquery',  border:false " >
	     <thead>
	     </thead>
		</table>
		</div>
 


		<!--�û����� ������-->
		 <div id="user_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){doSave();}},
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	handler:function(){$('#user_dlg').dialog('close');}  }]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="user_fm" method="post" enctype="multipart/form-data" >
				
			 
			  </form>
		</div>




		
		<!-- Gf  st -->
		<div id="addMarkers_point_dlg" class="easyui-dialog" title="����������������" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_point_buttons',closed:true" style="width:550px;height:470px;padding:10px">
        	<form id="addoredit_usericon_fm" method="post" >
        		<fieldset>
              		<legend><div id="show_str1"></div></legend>
              		<div id="show_str2"></div>
            	</fieldset>
            	<input type="hidden" id="hidden_user_icon" name="hidden_user_icon" value="" />
            	<fieldset>
            		<legend><font color='red'>Ŀǰѡ�е��û���ʶ��</font></legend>
            		<div id="show_str3">��</div>
            	</fieldset>
        	</form>
    	</div>
    	<div id="addMarkers_point_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_point_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_addMarkers_point_fm();">ȷ��</a>
    	</div>

		<div id="confirm_usericon_dlg" class="easyui-dialog" title="ȷ���ύ��������" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_usericon_buttons',closed:true" style="width:400px;height:150px;padding:10px">
        	<div id="show_str4">
        	</div>
    	</div>
    	<div id="confirm_usericon_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_usericon_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitUserIcon();">ȷ��</a>
    	</div>



		<div id="addMarkers_line_dlg" class="easyui-dialog" title="����������������" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_line_buttons',closed:true" style="width:360px;height:400px;padding:10px">
        	<form id="addMarkers_line_fm" method="post" >
        		<div><font size='3'><b>��·��ɫѡ��</b></font></div>
        		<div><hr></div>
        		<div>��ɫ:<input type="text" id="select_my_line_color" name="select_my_line_color" value="#FF0000" /></div>
        		<div><div id="linecolorpicker"></div></div>
        		<div>͸����<input id="line_strokeOpacity" name="line_strokeOpacity" value="1.0" /><font color="red">0.0-1.0(1.0��ʾ��͸��)</font></div>
        		<div>��ϸ��<input id="line_strokeWeight" name="line_strokeWeight" value="2" /><font color="red">��СΪ2(Խ��Խ��)</font></div>
        	</form>
    	</div>
    	<div id="addMarkers_line_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_line_dlg').dialog('close');">ȡ��</a>
    		<a href="#" id="line_color_confirm" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="line_color_confirm();">ȷ��</a>
    	</div>

		
		<div id="confirm_line_dlg" class="easyui-dialog" title="ȷ���ύ��������������" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_line_buttons',closed:true" style="width:400px;height:150px;padding:10px">
			<input type="hidden" id="line_markers" />
        	<div id="show_str5">
        	</div>
    	</div>
    	<div id="confirm_line_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_line_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitLineMarker();">ȷ��</a>
    	</div>


		<div id="addMarkers_area_dlg" class="easyui-dialog" title="����������������" closed="true" data-options="iconCls:'icon-add',buttons:'#addMarkers_area_buttons',closed:true" style="width:400px;height:400px;padding:10px">
        	<form id="addMarkers_area_fm" method="post" >
        		<div><font size='3'><b>����ɫѡ��</b></font></div>
        		<div><hr></div>
        		<div>��ɫ:<input type="text" id="select_my_area_color" name="select_my_area_color" value="#FF0000" /></div>
        		<div><div id="areacolorpicker"></div></div>
        		<div>͸����<input id="area_strokeOpacity" name="area_strokeOpacity" value="1.0" /><font color="red">0.0-1.0(1.0��ʾ��͸��)</font></div>
				<div>��ϸ��<input id="area_strokeWeight" name="area_strokeWeight" value="2" /><font color="red">��СΪ2(Խ��Խ��)</font></div>
        	</form>
    	</div>
    	<div id="addMarkers_area_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#addMarkers_area_dlg').dialog('close');">ȡ��</a>
    		<a href="#" id="area_color_confirm" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="area_color_confirm();">ȷ��</a>
    	</div>

		
		<div id="confirm_area_dlg" class="easyui-dialog" title="ȷ���ύ��������������" closed="true" data-options="iconCls:'icon-edit',buttons:'#confirm_area_buttons',closed:true" style="width:400px;height:150px;padding:10px">
			<input type="hidden" id="area_markers" />
        	<div id="show_str6">
        	</div>
    	</div>
    	<div id="confirm_area_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#confirm_area_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submitAreaMarker();">ȷ��</a>
    	</div>

		<!-- Gf  ed -->



    	
    	<div style="display:none">
      	<!-- ����excel ����ѡ��  -->
      		<jsp:include page="/sys/UIExcel.jsp" >
      		</jsp:include>
      	</div>
	</body>
</html>
