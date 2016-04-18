<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>


		<title></title>
 
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<xl:resource></xl:resource>
	 
 

 	 	<link rel="stylesheet" type="text/css" href="/map/css/css.css">
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_query_tools.js"></script>	
		<script type="text/javascript" src="/map/mapjs/textarea_class.js"></script>
		<script type="text/javascript" src="/map/mapjs/map-query-1.0.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_circle_query.js"></script>	
 		<script type="text/javascript" src="/map/mapjs/map_markers_add.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_toolbardiv.js"></script>
		<script type="text/javascript">
		var my_lat = "${session.userInfo.lat}";
		var my_lon = "${session.userInfo.lng}";
		</script>
		
		<script type="text/javascript">
		var marker_center_lat='${session.userInfo.lat}';
		var marker_center_lng='${session.userInfo.lng}';

		var url = config.url;
		var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
		var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
		var map;

 		window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
 		var clickable=true;
 
	
		</script>
		<!-- 
		<script type="text/javascript" src="/mobile_wap/js/checkin_show.js" charset="GBK"></script>
		 -->
	</head>

	<body onload="initialize('map_canvas',config.maxZoon,'${session.userInfo.lat}','${session.userInfo.lng}');"  class="easyui-layout">
     
		<div data-options="region:'west',split:true" title="地图查询" style="width: 200px;">
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
                      	<span id="queryString3Show">条件选择：</span>
		                 	<INPUT type="hidden"  id="queryString3" value=""/>
                        <a href="###"   id="toquery" onclick="" title="asdfsdf"><img src="/weblib/img/magnifier.png">设置</a>
		         	</span>
		         	<span id="asdfasdf">
		                <input id="queryString"  type="text" style="width:80px;" value="" >
		            </span>
                        <a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-filter'" onclick="map_query_1()">查询</a>
		            </div>
		          
                </div>
               
            </div> 
		    
		</div>
		
	
		<div data-options="region:'center',split:true" title="地图" style="background: #fafafa; overflow: hidden;">
	  	<div class="map_markers_toolbar">
			<div class="map_markers_t3" title="圆形查询" style="z-index:50;position:absolute;top:55px;right:6px;width:65px;height:46px;background-image:url(/mapfiles/markers3/bg_drawing_tool.png);background-position:-130px 0;" onclick="changeCSS('map_markers_t3');drawCircle();"></div>
			<div class="map_markers_t1" title="测距" style="z-index:50;position:absolute;top:150px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/getDistance2-64-b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'1');"></div>
			<div class="map_markers_t2" title="测面积" style="z-index:50;position:absolute;top:214px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/getDistance-64-b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'2');"></div>
			<div class="map_markers_t4" title="显示文本框" style="z-index:50;position:absolute;top:278px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/fdce244eba_64_b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'4');"></div>
			<!-- 
			<div class="map_markers_t5" title="截图" style="z-index:50;position:absolute;top:342px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/save_screen-64-b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'5');"></div>
			<div class="map_markers_t6" title="查看经纬度" style="z-index:50;position:absolute;top:416px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/ee11ee1_64-b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'6');"></div>
			 -->
			<div class="map_markers_t6" title="查看经纬度" style="z-index:50;position:absolute;top:342px;right:6px;width:64px;height:64px;background-image:url(/mapfiles/markers3/ee11ee1_64-b.png);background-position:0 0;" onclick="changeCssForClick(this.className,'6');"></div>
		</div>
		 <div id="map_canvas"  style=" width:100%; height:100%;  "></div>
		
	
		</div>
		<!-- 
		<div data-options="region:'east',split:true,collapsed:true" title="签到查询" style="width: 183px;">
			<fieldset>
              	<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="1" checked="checked" /> 时间查询：
              	</legend>
              	<div>开始时间：</div>
              	<div><input class="easyui-datetimebox" id="ljhx_query_timeST" name="ljhx_query_timeST" ></input></div>
              	<div>结束时间：</div>
              	<div><input class="easyui-datetimebox" id="ljhx_query_timeED" name="ljhx_query_timeED" ></input></div>
			</fieldset>
			<fieldset>
				<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="2" /> 类型查询：
              	</legend>
              	<div><input type="text" id="ljhx_selectbutton" name="ljhx_selectbutton" class="easyui-combobox" style="width: 150px" data-options="valueField:'value',textField:'text',data:${application.ParamJson.MOBILE_VISIT_TYPE}"/></div>
			</fieldset>
			<fieldset>
				<legend>
              		<input type="checkbox" name="ljhx_radiobutton" value="3" /> 登录名查询：
              	</legend>
              	<div><input class="easyui-validatebox textbox" id="ljhx_loginname_query" name="ljhx_loginname_query" style="width: 150px"/></div>
			</fieldset>
            <input type="hidden" id="hidden_user_icon" name="hidden_user_icon" value="" />
            <div align="center">
            	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="ljhx_reloadscreen();">重置界面</a>
            	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" onclick="query_ljhx_data();">确认</a>
            </div>
            <div align="center"><font color='red'><div id="echo_query_msg"></div></font></div>
		</div>
		 -->
			<!-- 复制弹出层-->
		<div id="copy_dialog" title="复制" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-add',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
		<form action="" method="post" enctype="multipart/form-data"  id="copyform">
	
		 <table id='copy_table1' width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>
		 
 
		 </form>
		</div>	
			<!-- 修改弹出层-->
		<div id="modify_dialog" title="信息修改" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-edit',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
			<form action="" method="post" enctype="multipart/form-data"  id="modifyform">
 		<table id='modify_table1' width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>

 
		 </form>
		</div>	
	
		<!-- 详细信息-->
		<div id="show_dialog" title="详细信息" class="easyui-dialog panel-body panel-body-noborder window-body" data-options="iconCls:'icon-edit',closed:true,modal:true" title="" style="overflow: auto; width: 600px; height: 450px;">
			<form action="" method="post" enctype="multipart/form-data"  id="showform">
 		<table id='show_table1'  width="100%" border="0" align="center" cellpadding="4" cellspacing="1"  style="background-color: #d3d3d3; font-size: 12px; margin-top: 10px;">
		 	<tr style=" background-color: #ffffff;"></tr>
		 </table>


		 </form>
		</div>	

	 
	 
		
	</body>
</html>
