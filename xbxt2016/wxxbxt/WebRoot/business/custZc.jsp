<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>�ͻ��ʲ���ѯ</title>
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
<script language="javascript" type="text/javascript" src="/business/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    var url;
	var query_url = "cust_zc_query.action";
			var root="${session.userInfo.brch_no}"; 
 
	
	function doquery() {		
		var fmdata = $("#queryfm").form2json();
		$("#data1").datagrid("load", fmdata);
	}
	
	function openDilog(){
		var row =  $("#data1").datagrid("getSelections");

		if(row.length>0){
		
		    var cust_ids="";
		    for(var i=0;i<row.length;i++)
		    {
			    cust_ids+=","+row[i].cust_p_id;
			    if(row[i].feiying_tag=="��Ӫ�ͻ�")
			    {
			    	  $.messager.alert("����","��Ӫҵ�ͻ����ܽ��пͻ��������");
				 	  return;
				}
			}
		   	$("#cust_p_id").val(cust_ids);
		    $("#dlg").dialog("open").dialog("setTitle","�ͻ��������");
	  }else{
	  	  $.messager.alert("����","����ѡ��һ��");
	  }
		
	}
	function fmAction(value,row,index)
	{
		  return "<a href=\"###\" onclick=\"toQuery('"+row.cust_p_id+"')\">"+row.pass_no+"</a>";
	
	}
	function doSave()
	{
		fmSubmit("#fm","cust_zc_save",
		        function(result){
                $("#data1").datagrid("reload");
                $("#dlg").dialog("close");
             
        });
	}
	function clearNoNum(obj)
	{
	    //�Ȱѷ����ֵĶ��滻�����������ֺ�.
	    obj.value = obj.value.replace(/[^\d.]/g,"");
	    //���뱣֤��һ��Ϊ���ֶ�����.
	    obj.value = obj.value.replace(/^\./g,"");
	    //��ֻ֤�г���һ��.��û�ж��.
	    obj.value = obj.value.replace(/\.{2,}/g,".");
	    //��֤.ֻ����һ�Σ������ܳ�����������
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}

	  function timeFormatter(date){
	        return date.getFullYear()+""+(date.getMonth()+1);
	    }


	function toQuery(cust_p_id){//��������ѡ��ҳ�� 
	 
		   	var iWidth=800;                          //�������ڵĿ��;
		   	var iHeight=550;                         //�������ڵĸ߶�;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;//��ô��ڵĴ�ֱλ��   
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //��ô��ڵ�ˮƽλ��    
		    var url ="/cust_zc_mx_init.action?cust_p_id="+cust_p_id;
		   		 
		    var oWin=window.open (url,'newwindow','height=550,width=800,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=yes, status=no')
			oWin.document.title="you title";
	 }
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			
		
			 ��&nbsp;&nbsp;�䣺<input type="text" name="age_min" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="age_max" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			 ��&nbsp;&nbsp;�ڣ�<input type="text" name="dingqi_value_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="dingqi_value_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			��&nbsp;&nbsp;�ڣ�<input type="text" name="huoqi_value_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="huoqi_value_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			<br>
			��&nbsp;&nbsp;�գ�<input type="text" name="baofei_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="baofei_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			��&nbsp;&nbsp;�ƣ�<input type="text" name="licai_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="licai_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			��&nbsp;&nbsp;ծ��<input type="text" name="guozhai_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="guozhai_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;
			���ʲ���<input type="text" name="zichan_all_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="zichan_all_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;
		
				<br/>
			��&nbsp;&nbsp;��<select id="sex" class="easyui-combobox" name="sex" style="width:120px">
				<option value="">ȫ��</option>
				<option value="��">��</option>
				<option value="Ů">Ů</option>
			</select>
			&nbsp;&nbsp;
			
			���֤��<input type="text" name="pass_no" class="easyui-validatebox" style="width:120px"/>
			&nbsp;&nbsp;
			�ͻ�������<input type="text" name="custname" class="easyui-validatebox" style="width:120px"/>
			&nbsp;&nbsp; 
					<br>  
			�������ڣ�
			<input type="text" name="jiesuan_date" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyyMM'})" readonly/>
			&nbsp;&nbsp; 
			�ͻ�����
			 <input type="text"  name="manager_name"  class="easyui-combobox" style="width: 120px"
							data-options="valueField:'user_name',textField:'user_name',url:'/cust_zc_queryManager.action'" />
			&nbsp;&nbsp; 
			����ά�Ȼ��ܣ�<input type="text" name="qudao_weidu_sum_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="qudao_weidu_sum_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp; 
			��Ӫ�ͻ���
			<input type="checkbox" name="feiying_tag"  id="feiying_tag" value="1" />
					
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">��ѯ</a>
				<br>
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#data1','cust_zc_excel')">excel����</a>
			
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openDilog();">�ͻ��������</a>
		
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'�ͻ��ʲ��б�',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url"
     	>
		<thead>
			<tr class="header">
				 <th data-options="field:'cust_p_id',align:'center',checkbox:true" ></th>
				<th data-options="field:'custname',align:'center',width:100" >�ͻ�����</th>
				<th data-options="field:'manager_name',align:'center',width:100" >�ͻ�����</th>
				<th data-options="field:'zichan_grade',align:'center',width:120" >�ʲ��ȼ�</th>
				<th data-options="field:'action',align:'center',formatter:fmAction,width:150" >���֤</th>
				<th data-options="field:'sj',align:'center',width:100" >�ֻ�</th>
				<th data-options="field:'dh',align:'center',width:100" >����</th>
				<th data-options="field:'dingqi_value',align:'center',width:100" >����</th>
				<th data-options="field:'huoqi_value',align:'center',width:100" >����</th>
				<th data-options="field:'licai',align:'center',width:100" >���</th>
				<th data-options="field:'baofei',align:'center',width:100" >����</th>
					<th data-options="field:'guozhai',align:'center',width:100" >��ծ</th>
				<th data-options="field:'zichan_all',align:'center',width:100" >���ʲ�</th>
			
				<th data-options="field:'sex',align:'center',width:60" >�Ա�</th>
				<th data-options="field:'age',align:'center',width:60" >����</th>
				<th data-options="field:'qudao_weidu_sum',align:'center',width:100" >����ά���ܺ�</th>
				<th data-options="field:'dk_power',align:'center',width:100" >���۵��</th>
				<th data-options="field:'dk_tv',align:'center',width:100" >�������ֵ��ӷ�</th>
				<th data-options="field:'dk_water',align:'center',width:100" >����ˮ��</th>
				<th data-options="field:'dk_gas',align:'center',width:100" >����ȼ����</th>
				<th data-options="field:'dk_med',align:'center',width:100" >����ҽ�Ʊ���</th>
				<th data-options="field:'dk_telec',align:'center',width:100" >���۵���ͨѶ��</th>
				<th data-options="field:'dk_mphone',align:'center',width:100" >�����ƶ��ֻ���</th>
				<th data-options="field:'df',align:'center',width:100" >��������</th>
				<th data-options="field:'netbank',align:'center',width:100" >����</th>
					<th data-options="field:'open_office_code',align:'center',width:100" >���������</th>
				<th data-options="field:'brch_name',align:'center',width:120" >��������</th>
				<th data-options="field:'jiesuan_date',align:'center',width:80" >ͳ������</th>
					<th data-options="field:'feiying_tag',align:'center',width:100" >��Ӫ��ʶ</th>
			

			</tr>
		</thead>
	</table>

	<!-- �༭���� -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){doSave();}},
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	    handler:function(){$('#dlg').dialog('close');}}]"
				 style="width:300px;height:200px;padding:10px 10px;">	

		<form id="fm" method="post"">
				<div class="form_item col1">
				   <label>Ӫ������</label>
			      <input type="text"  name="manager_name" id="manager_name" class="easyui-combobox" style="width: 150px" 
							data-options="required:true,missingMessage:'��ѡ',valueField:'user_name',textField:'user_name',url:'/cust_zc_queryManager.action'" />
					 <input type="hidden" id="cust_p_id" name="cust_p_id"  />
				
			  </div>  
		</form>
	</div>

	<div style="display:none">
		<!-- ����excel ����ѡ��  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
