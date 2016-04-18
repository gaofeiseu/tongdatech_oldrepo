<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="/mapfiles/css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/map/mapjs/farbtastic12/farbtastic/farbtastic.css"/>
		<title>��ӱ�ע</title>
		<xl:resource></xl:resource>
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<!--<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>-->
		<script type="text/javascript" src="/map/mapjs/map_query_tools.js"></script>	
		<script type="text/javascript" src="/map/mapjs/map-query-1.0.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_circle_query.js"></script>
		<script type="text/javascript" src="/map/mapjs/textarea_class.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_markers_add.js" charset="GBK"></script>
		<script type="text/javascript" src="/map/mapjs/farbtastic12/farbtastic/farbtastic.js"></script>
		<script type="text/javascript">
			var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
			var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
			var map;// ȫ�ֵ�ͼ����
			window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
			var t_usertype = '${session.userInfo.user_type}';
			//console.log('t_usertype-------->'+t_usertype);
			var clickable=false;
		</script>
	</head>
	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');" class="easyui-layout">
		<div data-options="region:'west',collapsed:true,split:true" title="��ͼ��ѯ" style="width: 200px;">
		    <div class="easyui-layout" data-options="fit:true" >            
                <div data-options="region:'center',border:false ,split:true">
                    <ul class="easyui-tree" id="mapTree" data-options="
		            url:'map_query_tree',
		            method:'post',
		    		checkbox:true,
		    		onCheck:map_query
		            " 
		            style="margin: 20px 10px 10px 10px"></ul>
                </div>
                <div data-options="region:'south',border:false,split:true" style="height:100px">
		   
                    <div style="margin: 20px 10px 10px 15px ;width:170px;overflow: hidden">
                    <span id="sdfasdf" style="display:none;">
                      	<span id="queryString3Show">����ѡ��</span>
		                 	<INPUT type="hidden"  id="queryString3" value=""/>
                        <a href="###"   id="toquery" onclick="" title="asdfsdf"><img src="/weblib/img/magnifier.png">����</a>
		         	</span>
		         	<span id="asdfasdf">
		                <input id="queryString"  type="text" style="width:80px;" value="" >
		            </span>
                        <a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-filter'" onclick="map_query_1()">��ѯ</a>
		            </div>
		          
                </div>
                
                
                 <div data-options="region:'south',border:false,split:true" style="height:100px">
           
          
		          
                </div>
            </div>
		    
		</div>
		<div data-options="region:'center',split:true" title="��ͼ" style="background: #fafafa; overflow: hidden;">
	  
		 <div id="map_canvas"  style="float:right; width:100%; height:100%;  "></div>
		 <div class="map_markers_toolbar">
		 	
		 	<div class="map_markers_t1" title="�ָ��϶�" style="z-index:50;position:absolute;top:55px;right:325px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:0 0;" onclick="changeCssForClick(this.className,'1');"></div>
		 	<div class="map_markers_t2" title="��ӵ��ע" style="z-index:50;position:absolute;top:55px;right:260px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-65px 0;" onclick="changeCssForClick(this.className,'2');"></div>
		 	<div class="map_markers_t3" title="���Բ�α�ע" style="z-index:50;position:absolute;top:55px;right:195px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-130px 0;" onclick="changeCssForClick(this.className,'3');"></div>
		 	
		 	 <!-- 
		 	<div class="map_markers_t1" title="�ָ��϶�" style="z-index:50;position:absolute;top:55px;right:260px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:0 0;" onclick="changeCssForClick(this.className,'1');"></div>
		 	<div class="map_markers_t2" title="��ӵ��ע" style="z-index:50;position:absolute;top:55px;right:195px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-65px 0;" onclick="changeCssForClick(this.className,'2');"></div>
		 	 -->
		 	<div class="map_markers_t4" title="����߱�ע" style="z-index:50;position:absolute;top:55px;right:130px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-195px 0;" onclick="changeCssForClick(this.className,'4');"></div>
		 	<div class="map_markers_t5" title="������ע" style="z-index:50;position:absolute;top:55px;right:65px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-260px 0;" onclick="changeCssForClick(this.className,'5');"></div>
		 	<div class="map_markers_t6" title="����ı���" style="z-index:50;position:absolute;top:55px;right:0px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-325px 0;" onclick="changeCssForClick(this.className,'6');"></div>
		 </div>
		 <div id="icon_add_dlg" class="easyui-dialog" title="ͼ������"
			data-options="iconCls:'icon-mark-input',closed:true,modal:true"
			style="width: 600px; height: 80px; padding: 10px">
			<form name="icon_add_frm" id="icon_add_frm" method="post" enctype="multipart/form-data">
				<div>
					<input type="text" name="viewfile" id="icon_viewfile" class="data_input_viewfile" />
					<label for="data_input_fileupload" class="data_input_bottom">ѡ��ͼ��</label>
					<input type="file" name="upload" id="data_input_fileupload" onchange="document.getElementById('icon_viewfile').value=this.value;" class="data_input_file" />
					<input type="button" id="upload_addicon" value="����ͼ��" onclick="do_icon_add();" />
				</div>
			</form>
		</div>
		<!-- ������������ӦDlg   ST -->
		<div id="point_zishiying_dlg" class="easyui-dialog" title="���ע���"
			data-options="iconCls:'icon-add',buttons:'#point_zishiying_dlg_buttons',closed:true,modal:true"
			style="width: 450px; height: 400px; padding: 10px">
			<form name="point_zishiying_frm" id="point_zishiying_frm" method="post" enctype="multipart/form-data">
				<table id="hidden_table_for_point_zishiying" style="width:100%;">
					<tr><td></td></tr>
				</table>
				<input type="hidden" id="selecticon_num_11" value="" />
				<input type="hidden" id="selectclass_tablename1" value="" />
			</form>
		</div>
		<div id="point_zishiying_dlg_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#point_zishiying_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="addPointMarkerS();">ȷ��</a>
    	</div>		
		<!-- ������������ӦDlg   ED -->
		<!-- ������������ӦDlg   ST -->
		<div id="line_zishiying_dlg" class="easyui-dialog" title="�߱�ע���"
			data-options="iconCls:'icon-add',closed:true,modal:true"
			style="width: 450px; height: 400px; padding: 10px">
			<form name="line_zishiying_frm" id="line_zishiying_frm" method="post" enctype="multipart/form-data">
				<table id="hidden_table_for_line_zishiying" style="width:100%;">
					<tr><td></td></tr>
				</table>
				<input type="hidden" id="selecticon_num_22" value="" />
				<input type="hidden" id="selectclass_tablename2" value="" />
				<table style="width:100%;">
					<tr>
						<td align="center">
							<input type="button" id="submit_my_line_zishiying" value="����" onclick="addLineMarkerS();" />
						</td>
					</tr>
				</table>
			</form>
		</div>		
		<!-- ������������ӦDlg   ED -->
		<!-- ������������ӦDlg   ST -->
		<div id="area_zishiying_dlg" class="easyui-dialog" title="���ע���"
			data-options="iconCls:'icon-add',closed:true,modal:true"
			style="width: 450px; height: 400px; padding: 10px">
			<form name="area_zishiying_frm" id="area_zishiying_frm" method="post" enctype="multipart/form-data">
				<table id="hidden_table_for_area_zishiying" style="width:100%;">
					<tr><td></td></tr>
				</table>
				<input type="hidden" id="selecticon_num_33" value="" />
				<input type="hidden" id="selectclass_tablename3" value="" />
				<table style="width:100%;">
					<tr>
						<td align="center">
							<input type="button" id="submit_my_area_zishiying" value="����" onclick="addAreaMarkerS();" />
						</td>
					</tr>
				</table>
			</form>
		</div>		
		<!-- ������������ӦDlg   ED -->
		
		
    	
    	<!-- �ı���Ĳ���Dlg		ST --><!-- �ɵ� -->
    	
    	<div id="textarea_input_dlg" class="easyui-dialog" title="����ı���"
			data-options="iconCls:'icon-edit',buttons:'#textarea_input_dlg_buttons',closed:true,modal:true"
			style="width: 250px; height: 120px; padding: 10px">
			<div align="center">�������ݣ�<input type="text" id="textarea_input1" name="textarea_input1" value="" /></div>
			<input type="hidden" id="hidden_lat_1" name="hidden_lat_1" />
			<input type="hidden" id="hidden_lng_1" name="hidden_lng_1" />
			<input type="hidden" id="hidden_zoom_1" name="hidden_zoom_1" />
		</div>
		<div id="textarea_input_dlg_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#textarea_input_dlg').dialog('close');">ȡ��</a>
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="submit_textarea_input();">ȷ��</a>
    	</div>
    	
    	<!-- �ı���Ĳ���Dlg		ED -->
    	
    	<!-- �ı���Ĳ���Dlg		ST -->
    	
    	<div id="textarea_zishiying_dlg" class="easyui-dialog" title="�ı����ע���"
			data-options="iconCls:'icon-add',closed:true,modal:true"
			style="width: 450px; height: 400px; padding: 10px">
			<form name="textarea_zishiying_frm" id="textarea_zishiying_frm" method="post" enctype="multipart/form-data">
				��ǰ�����ı�������ͼ��Ϊ��<div id="hh_div1"></div>
				�ı�����ı���ʾ���ݣ�<input type="text" id="textarea_textcontent" name="textarea_textcontent" value="" /><font color="red">*������д</font>
				<table id="hidden_table_for_textarea_zishiying" style="width:100%;">
					<tr><td></td></tr>
				</table>
				<input type="hidden" id="selecticon_num_55" value="" />
				<input type="hidden" id="selectclass_tablename5" value="" />
				<table style="width:100%;">
					<tr>
						<td align="center">
							<input type="button" id="submit_my_textarea_zishiying" value="����" onclick="addTextAreaMarkerS();" />
						</td>
					</tr>
				</table>
			</form>
		</div>
    	
    	<!-- �ı���Ĳ���Dlg		ED -->
    	
    	<!-- Բ�α�ע����Dlg		ST -->
    	
    	<div id="circle_zishiying_dlg" class="easyui-dialog" title="Բ�α�ע���"
			data-options="iconCls:'icon-add',closed:true,modal:true"
			style="width: 450px; height: 400px; padding: 10px">
			<form name="circle_zishiying_frm" id="circle_zishiying_frm" method="post" enctype="multipart/form-data">
				<table id="hidden_table_for_circle_zishiying" style="width:100%;">
					<tr><td></td></tr>
				</table>
				<input type="hidden" id="selecticon_num_44" value="" />
				<input type="hidden" id="selectclass_tablename4" value="" />
				<table style="width:100%;">
					<tr>
						<td align="center">
							<input type="button" id="submit_my_circle_zishiying" value="����" onclick="addCircleMarkerS();" />
						</td>
					</tr>
				</table>
			</form>
		</div>	
    	
    	<!-- Բ�α�ע����Dlg		ED -->
    	
    	
			<!-- ���Ƶ�����-->
		<div id="copy_dialog" title="����" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-add',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
		<form action="" method="post" enctype="multipart/form-data"  id="copyform">
	
		 <table id='copy_table1' width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>
		 
 
		 </form>
		</div>	
		 
		<table id='copy_table2' style='width:100%;'>
		 	<tr></tr>
		 </table>
		 </form>
		</div>	
		 
		
					<!-- �޸ĵ�����-->
		<div id="modify_dialog" title="��Ϣ�޸�" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-edit',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
			<form action="" method="post" enctype="multipart/form-data"  id="modifyform">
 		<table id='modify_table1' width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>

 
		 </form>
		</div>	
	
		<!-- ��ϸ��Ϣ-->
		<div id="show_dialog" title="��ϸ��Ϣ" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-edit',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
			<form action="" method="post" enctype="multipart/form-data"  id="showform">
 		<table id='show_table1'  width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>


		 </form>
		</div>	
		
    </div>
	</body>
</html>