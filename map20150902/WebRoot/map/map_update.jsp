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
		<script type="text/javascript" src="/map/mapjs/map_config.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapapi3.15.5.js"></script>
		<script type="text/javascript" src="/map/mapjs/mapmain.js"></script>
		<script type="text/javascript" src="/map/mapjs/map_update.js" charset="GBK"></script>
		<script type="text/javascript">
		 var localMapType = new LocalMapType(config.url,config.maxZoon,config.minZoon);
		 var localSaMap = new LocalSaMapType(config.url,config.maxZoon,config.minZoon);
		 var map;
		 /*
		 var download_url = "${application.ParamJson.MAP_DOWNLOAD_URL}";
		 var download_obj = eval('(' + download_url + ')');
		 
		 var sa_download_url = "${application.ParamJson.MAP_DOWNLOAD_SA_URL}";
		 var sa_download_obj = eval('(' + sa_download_url + ')');
		 
		 var sadl_download_url = "${application.ParamJson.MAP_DOWNLOAD_SADL_URL}";
		 var sadl_download_obj = eval('(' + sadl_download_url + ')');
		 */
		 
		 window.parent.window.document.getElementById("topFrame").contentWindow.MenuShow(1);
		 var t_user_lat = '${session.userInfo.lat}';
		 var t_user_lng = '${session.userInfo.lng}';
		</script>
	</head>
	<body onload="load_func();">
		<xl:wrap title="地图下载">
			<form id="queryfm" action="/map_update_dodownload.action"
			style="margin: 5px auto;width:600px">
			<fieldset>
              <legend>下载参数设置</legend>
            <div class="form_item col1" style="text-align: left;">
				<input type="radio" name="map_type" id="map_dl" value="1" checked="checked"  />道路地图<!-- onclick="change_downloadurl('1');" -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="map_type" id="map_sa" value="2"  />卫星地图<!-- onclick="change_downloadurl('2');" -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="map_type" id="map_sadl" value="3"  />卫星道路地图<!-- onclick="change_downloadurl('3');" -->
			</div>
			<div class="form_item col2">
				<label>起始经度：</label>
				<input type="text" name="st_lng" id="st_lng" class="easyui-validatebox" readonly/>
		    </div>
		    <div class="form_item col2">
				<label>起始纬度：</label>
				<input type="text" name="st_lat" id="st_lat" class="easyui-validatebox" readonly/>
			</div>
			<div class="form_item col2">
			    <label>结束经度：</label>
				<input type="text" name="ed_lng" id="ed_lng" class="easyui-validatebox" readonly/>
			</div>
			<div class="form_item col2">
				<label>结束纬度：</label>
				<input type="text" name="ed_lat" id="ed_lat" class="easyui-validatebox" readonly/>
			</div>
			<div class="form_item col2">
				<label>下载图层：</label>
				<input type="text" name="map_zoom" id="map_zoom" class="easyui-numberspinner" data-options="min:10,max:18,required:true" style="width:153px;"></input>
			</div>
			<!-- 
			<div class="form_item col2">
				<label>下载路径：</label>
				<input type="text" name="download_url" id="download_url" class="easyui-validatebox" readonly value="" />
			</div>
			-->
			<div class="form_item col1" style="text-align: left;">
				<font color="red">1、点击设置经纬度，在地图上选择2个点以涵盖所需下载地图的区域，系统将自动获取对应的起始结束经纬度。</font>
			</div>
			<div class="form_item col1" style="text-align: left;">
				<font color="red">2、选择需要下载的地图图层数。例如：选择15，则将下载1-15层的指定地区的地图。</font>
			</div>
			<div class="form_item col1" style="text-align: left;">
				<font color="red">3、点击开始下载注册下载任务，下载时可以关闭当前页面，但是请不要重复提交下载任务。</font>
			</div>
			<div id="loading_img" class="form_item col1" style="text-align: center;display:none;">
				<img src="/mapfiles/markers3/2897814_144722694127_1.gif" />
			</div>
			<div class="form_item col1" style="text-align: center;">
				<a href="#" class="easyui-linkbutton" id="setLatLng_btn" data-options="plain:true,iconCls:'icon-setLatLng'" onclick="startSetLatLng()">设置经纬度</a>
				<a href="#" class="easyui-linkbutton" id="down_btn" data-options="plain:true,iconCls:'icon-download'" onclick="dodownload()">开始下载</a>
			</div>
			</fieldset>
			</form>
			<div id="map_canvas"  style="z-index:9; width:600px; height:290px; margin: 5px auto;" ></div>
		</xl:wrap>
	</body>
</html>
