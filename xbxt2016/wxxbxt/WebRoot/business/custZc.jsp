<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
<head>
<title>客户资产查询</title>
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
			    if(row[i].feiying_tag=="非营客户")
			    {
			    	  $.messager.alert("错误","非营业客户不能进行客户经理关联");
				 	  return;
				}
			}
		   	$("#cust_p_id").val(cust_ids);
		    $("#dlg").dialog("open").dialog("setTitle","客户经理关联");
	  }else{
	  	  $.messager.alert("错误","请先选择一行");
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
	    //先把非数字的都替换掉，除了数字和.
	    obj.value = obj.value.replace(/[^\d.]/g,"");
	    //必须保证第一个为数字而不是.
	    obj.value = obj.value.replace(/^\./g,"");
	    //保证只有出现一个.而没有多个.
	    obj.value = obj.value.replace(/\.{2,}/g,".");
	    //保证.只出现一次，而不能出现两次以上
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	}

	  function timeFormatter(date){
	        return date.getFullYear()+""+(date.getMonth()+1);
	    }


	function toQuery(cust_p_id){//弹出条件选择页面 
	 
		   	var iWidth=800;                          //弹出窗口的宽度;
		   	var iHeight=550;                         //弹出窗口的高度;
		    var iTop = (window.screen.availHeight-30-iHeight)/2;//获得窗口的垂直位置   
		    var iLeft = (window.screen.availWidth-10-iWidth)/2;       //获得窗口的水平位置    
		    var url ="/cust_zc_mx_init.action?cust_p_id="+cust_p_id;
		   		 
		    var oWin=window.open (url,'newwindow','height=550,width=800,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=yes, status=no')
			oWin.document.title="you title";
	 }
</script>
</head>

<body>
	<div id="toolbar" style="padding:10px;display:none">
		<form id="queryfm" style="margin:0">
			
		
			 年&nbsp;&nbsp;龄：<input type="text" name="age_min" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="age_max" class="easyui-validatebox" style="width:60px" onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			 定&nbsp;&nbsp;期：<input type="text" name="dingqi_value_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="dingqi_value_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			活&nbsp;&nbsp;期：<input type="text" name="huoqi_value_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="huoqi_value_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			<br>
			保&nbsp;&nbsp;险：<input type="text" name="baofei_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="baofei_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			理&nbsp;&nbsp;财：<input type="text" name="licai_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="licai_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;  
			国&nbsp;&nbsp;债：<input type="text" name="guozhai_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="guozhai_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;
			总资产：<input type="text" name="zichan_all_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="zichan_all_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp;
		
				<br/>
			性&nbsp;&nbsp;别：<select id="sex" class="easyui-combobox" name="sex" style="width:120px">
				<option value="">全部</option>
				<option value="男">男</option>
				<option value="女">女</option>
			</select>
			&nbsp;&nbsp;
			
			身份证：<input type="text" name="pass_no" class="easyui-validatebox" style="width:120px"/>
			&nbsp;&nbsp;
			客户姓名：<input type="text" name="custname" class="easyui-validatebox" style="width:120px"/>
			&nbsp;&nbsp; 
					<br>  
			结算日期：
			<input type="text" name="jiesuan_date" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyyMM'})" readonly/>
			&nbsp;&nbsp; 
			客户经理：
			 <input type="text"  name="manager_name"  class="easyui-combobox" style="width: 120px"
							data-options="valueField:'user_name',textField:'user_name',url:'/cust_zc_queryManager.action'" />
			&nbsp;&nbsp; 
			渠道维度汇总：<input type="text" name="qudao_weidu_sum_min" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;--&nbsp;<input type="text" name="qudao_weidu_sum_max" class="easyui-validatebox" style="width:60px"  onkeyup="clearNoNum(this)"/>
			&nbsp;&nbsp; 
			非营客户：
			<input type="checkbox" name="feiying_tag"  id="feiying_tag" value="1" />
					
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="doquery()">查询</a>
				<br>
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#data1','cust_zc_excel')">excel导出</a>
			
			<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="openDilog();">客户经理关联</a>
		
		</form>
	</div>
	<table id="data1" class="easyui-datagrid"
		data-options="title:'客户资产列表',
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',pageSize:50,
     	url:query_url"
     	>
		<thead>
			<tr class="header">
				 <th data-options="field:'cust_p_id',align:'center',checkbox:true" ></th>
				<th data-options="field:'custname',align:'center',width:100" >客户姓名</th>
				<th data-options="field:'manager_name',align:'center',width:100" >客户经理</th>
				<th data-options="field:'zichan_grade',align:'center',width:120" >资产等级</th>
				<th data-options="field:'action',align:'center',formatter:fmAction,width:150" >身份证</th>
				<th data-options="field:'sj',align:'center',width:100" >手机</th>
				<th data-options="field:'dh',align:'center',width:100" >座机</th>
				<th data-options="field:'dingqi_value',align:'center',width:100" >定期</th>
				<th data-options="field:'huoqi_value',align:'center',width:100" >活期</th>
				<th data-options="field:'licai',align:'center',width:100" >理财</th>
				<th data-options="field:'baofei',align:'center',width:100" >保险</th>
					<th data-options="field:'guozhai',align:'center',width:100" >国债</th>
				<th data-options="field:'zichan_all',align:'center',width:100" >总资产</th>
			
				<th data-options="field:'sex',align:'center',width:60" >性别</th>
				<th data-options="field:'age',align:'center',width:60" >年龄</th>
				<th data-options="field:'qudao_weidu_sum',align:'center',width:100" >渠道维度总和</th>
				<th data-options="field:'dk_power',align:'center',width:100" >代扣电费</th>
				<th data-options="field:'dk_tv',align:'center',width:100" >代扣数字电视费</th>
				<th data-options="field:'dk_water',align:'center',width:100" >代扣水费</th>
				<th data-options="field:'dk_gas',align:'center',width:100" >代扣燃气费</th>
				<th data-options="field:'dk_med',align:'center',width:100" >代扣医疗保险</th>
				<th data-options="field:'dk_telec',align:'center',width:100" >代扣电信通讯费</th>
				<th data-options="field:'dk_mphone',align:'center',width:100" >代扣移动手机费</th>
				<th data-options="field:'df',align:'center',width:100" >代发工资</th>
				<th data-options="field:'netbank',align:'center',width:100" >网银</th>
					<th data-options="field:'open_office_code',align:'center',width:100" >网点机构号</th>
				<th data-options="field:'brch_name',align:'center',width:120" >网点名称</th>
				<th data-options="field:'jiesuan_date',align:'center',width:80" >统计年月</th>
					<th data-options="field:'feiying_tag',align:'center',width:100" >非营标识</th>
			

			</tr>
		</thead>
	</table>

	<!-- 编辑窗口 -->
	<div id="dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'保存',plain:true,iconCls:'icon-ok',	    handler:function(){doSave();}},
				{ text:'取消',plain:true,iconCls:'icon-cancel',	    handler:function(){$('#dlg').dialog('close');}}]"
				 style="width:300px;height:200px;padding:10px 10px;">	

		<form id="fm" method="post"">
				<div class="form_item col1">
				   <label>营销经理：</label>
			      <input type="text"  name="manager_name" id="manager_name" class="easyui-combobox" style="width: 150px" 
							data-options="required:true,missingMessage:'必选',valueField:'user_name',textField:'user_name',url:'/cust_zc_queryManager.action'" />
					 <input type="hidden" id="cust_p_id" name="cust_p_id"  />
				
			  </div>  
		</form>
	</div>

	<div style="display:none">
		<!-- 引入excel 导出选项  -->
		<jsp:include page="/sys/UIExcel.jsp">
		</jsp:include>
	</div>
</body>
</html>
