<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>�߷ü�¼��ѯ</title>
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
			  return "<a href=\"###\" onclick=\"showpic('"+row.img_id+"')\">�鿴</a>";
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
			    $("#pic_dlg").dialog("open").dialog("setTitle","ͼƬ�鿴");			
		}


</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			 �û�����<input type="text" name="login_name" class="easyui-validatebox" style="width:100px"/>
			&nbsp;&nbsp;  
			ʱ�䣺
			<input type="text" style="width:120px,height:30px" name="check_in_time_start"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
			
				&nbsp;--&nbsp; 
			<input type="text" style="width:120px,height:30px" name="check_in_time_end"  class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
			&nbsp;&nbsp; 
			�߷����ͣ�<select id="visit_type" class="easyui-combobox" name="visit_type" style="width:80px">
				<option value="1">�����߷�</option>
				<option value="2">ת������</option>
				<option value="3">�������߷�</option>
				<option  selected='selected' value="">ȫ��</option>
			</select>
			&nbsp;&nbsp;
			<br>
			�߷����ࣺ 
			<input type="text"  name="visit_class" id="visit_class" class="easyui-combobox" style="width: 150px" 
				data-options="valueField:'value',textField:'text',data:${application.ParamJson.MOBILE_VISIT_CLASS}"/>
			
			&nbsp;&nbsp;��������:
			<input type="text" name="brch_no" id="query_brch_no"  />
			�Ƿ�鿴�¼���
			<select id="flag" class="easyui-combobox" name="_flag" style="width:80px">
				<option value="Y">��</option>
				<option  selected='selected' value="N">��</option>
			</select>
			
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
				<br>
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#data1','visit_excel')">excel����</a>
		
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'�߷ü�¼�б�',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url,singleSelect:true",
     	>
		<thead>
			<tr class="header">
			 
				<th data-options="field:'login_name',align:'center',width:150" >�û���</th>
			
				<th data-options="field:'visit_type',align:'center',width:120" >�߷�����</th>
				<th data-options="field:'visit_custom_name',align:'center',width:120" >�߷ÿͻ���</th>
				<th data-options="field:'visit_content',align:'center',width:120" >�߷�����</th>
				<th data-options="field:'visit_note',align:'center',width:120" >�߷ñ�ע</th>
				<th data-options="field:'check_in_time',align:'center',width:150" >ǩ��ʱ��</th>
				<th data-options="field:'visit_class',align:'center',width:120" >�߷�����</th>
				<th data-options="field:'action',align:'center',formatter:fmPicAction" width="100">
						��Ƭ
				</th>
				<th data-options="field:'check_in_lat',align:'center',width:150" >ǩ������</th>
				<th data-options="field:'check_in_lng',align:'center',width:150" >ǩ��γ��</th>
				
			</tr>
		</thead>
	</table>

	<!-- �༭���� -->
	<div id="pic_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'�Ŵ�',plain:true,iconCls:'icon-add',	    handler:function(){bigit();}},
				{ text:'��С',plain:true,iconCls:'icon-remove',	    handler:function(){smallit();}}]"
	
				 style="width:550px;height:450px;padding:10px 10px;">	

		<form id="fm" method="post"">
		
		</form>
	</div>

	<div style="display:none">
		<!-- ����excel ����ѡ��  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
