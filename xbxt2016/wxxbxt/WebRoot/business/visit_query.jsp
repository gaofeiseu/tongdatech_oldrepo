<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>走访记录查询</title>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<META HTTP-EQUIV="imagetoolbar" CONTENT="no">
<xl:resource></xl:resource>
<style type="text/css">
body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
td { font-size: 12px; line-height: 150%; }
</style>
<script type="text/javascript" src="/business/js/picTool.js"></script>
<script language="javascript" type="text/javascript" src="/business/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
    var url;
	var query_url = "visit_query.action";
			var root="${session.userInfo.brch_no}"; 
      $(document).ready(function(){
	  //  loadBrchSearch(".brchsearch");
	 //		loadBrchSearch("#query_brch_no");
		 loadBrchCombo("#query_brch_no",root);
	 
	    });	
	
	function doquery() {		
		var fmdata = $("#queryfm").form2json();
		$("#data1").datagrid("load", fmdata);
	}

		function fmPicAction(value,row,index){
			if(row.img_id!=""&&row.img_id!=null)
			{
			  return "<a href=\"###\" onclick=\"showpic('"+row.img_id+"')\">查看</a>";
			 }
			 else
			 {
			   return "--";
			 }
			  
		}
		function showpic(sn){
		   		
		   		  		 var str="";

 

		str+="	 <div id='hiddenPic' style='left:10px; top:10px;'><img name='images1' src='/visit_downloadfile.action?sn="+sn+"' border='0'></div>";
 		   		

			    jQuery("#fm").html(str);	
			    $("#pic_dlg").dialog("open").dialog("setTitle","图片查看");			
		}


</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			 用户名：<input type="text" name="login_name" class="easyui-validatebox" style="width:100px"/>
			&nbsp;&nbsp;  
			时间：
			<input type="text" style="width:120px,height:30px" name="check_in_time_start"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
			
				&nbsp;--&nbsp; 
			<input type="text" style="width:120px,height:30px" name="check_in_time_end"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
			&nbsp;&nbsp; 
			走访类型：<select id="visit_type" class="easyui-combobox" name="visit_type" style="width:80px">
				<option value="1">跨赛走访</option>
				<option value="2">转型外拓</option>
				<option value="3">邮务类走访</option>
				<option  selected='selected' value="">全部</option>
			</select>
			&nbsp;&nbsp;
			<br>
			走访种类： 
			<input type="text"  name="visit_class" id="visit_class" class="easyui-combobox" style="width: 150px" 
				data-options="valueField:'value',textField:'text',data:${application.ParamJson.MOBILE_VISIT_CLASS}"/>
			
			&nbsp;&nbsp;所属机构:
			<input type="text" name="brch_no" id="query_brch_no"  />
			是否查看下级：
			<select id="flag" class="easyui-combobox" name="_flag" style="width:80px">
				<option value="Y">是</option>
				<option  selected='selected' value="N">否</option>
			</select>
			
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">查询</a>
				<br>
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#data1','visit_excel')">excel导出</a>
		
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'走访记录列表',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url,singleSelect:true",
     	>
		<thead>
			<tr class="header">
			 
				<th data-options="field:'login_name',align:'center',width:150" >用户名</th>
			
				<th data-options="field:'visit_type',align:'center',width:120" >走访类型</th>
				<th data-options="field:'visit_custom_name',align:'center',width:120" >走访客户名</th>
				<th data-options="field:'visit_content',align:'center',width:120" >走访内容</th>
				<th data-options="field:'visit_note',align:'center',width:120" >走访备注</th>
				<th data-options="field:'check_in_time',align:'center',width:150" >签到时间</th>
				<th data-options="field:'visit_class',align:'center',width:120" >走访种类</th>
				<th data-options="field:'action',align:'center',formatter:fmPicAction" width="100">
						照片
				</th>
				<th data-options="field:'check_in_lat',align:'center',width:150" >签到经度</th>
				<th data-options="field:'check_in_lng',align:'center',width:150" >签到纬度</th>
				
			</tr>
		</thead>
	</table>

	<!-- 编辑窗口 -->
	<div id="pic_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'放大',plain:true,iconCls:'icon-add',	    handler:function(){bigit();}},
				{ text:'缩小',plain:true,iconCls:'icon-remove',	    handler:function(){smallit();}}]"
	
				 style="width:550px;height:450px;padding:10px 10px;">	

		<form id="fm" method="post"">
		
		</form>
	</div>

	<div style="display:none">
		<!-- 引入excel 导出选项  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
