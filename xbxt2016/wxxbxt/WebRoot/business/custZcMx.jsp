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
		<script type="text/javascript" src="/map/mapjs/map_usericon.js"></script>
		 
		<script type="text/javascript">
	 
	
     	 $(document).ready(function(){
 			var cust_p_id="${cust_p_id}";

 			$('#data1').datagrid({  url:'/cust_zc_Dingqi.action?cust_p_id='+cust_p_id }); 
 			$('#data2').datagrid({  url:'/cust_zc_Yibentong.action?cust_p_id='+cust_p_id}); 
 			$('#data3').datagrid({  url:'/cust_zc_Huoqi.action?cust_p_id='+cust_p_id}); 
 			$('#data4').datagrid({  url:'/cust_zc_BaoXian.action?cust_p_id='+cust_p_id}); 
 			$('#data5').datagrid({  url:'/cust_zc_Licai.action?cust_p_id='+cust_p_id}); 
 			$('#data6').datagrid({  url:'/cust_zc_Cxguozhai.action?cust_p_id='+cust_p_id}); 
 			$('#data7').datagrid({  url:'/cust_zc_Pzguozhai.action?cust_p_id='+cust_p_id}); 
 		 
	    });	
		
		</script>
	</head>
	<body  class="easyui-layout">
	 
	<div id="toolbar" style="display:none">
			<form id="queryfm" style="margin:0">
			<input type="hidden" name="cust_p_id" value="${cust_p_id}">
			</form>
	</div>

 
	<div id="tt" class="easyui-tabs" style="background:#fafafa;" >
		<div title="定期明细" style="height:520px" >
		<table id="data1" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 
					<th data-options="field:'account_no',align:'center',width:150" >存单号</th>
					<th data-options="field:'money_final',align:'center',width:100" >金额</th>
					<th data-options="field:'chuxu_type',align:'center',width:200" >储种</th>
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'daoqi_date',align:'center',width:100" >到期日期</th>
			 
				</tr>
			</thead>
		</table>
		</div>
		<div title="一本通明细"  style="height:520px">
				<table id="data2" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 			
			 
				<th data-options="field:'account_no',align:'center',width:150" >存单号</th>
					<th data-options="field:'money',align:'center',width:100" >金额</th>
					<th data-options="field:'chuxu_type',align:'center',width:200" >储种</th>
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'daoqi_date',align:'center',width:100" >到期日期</th>
			 
			
				</tr>
			</thead>
		</table>
		</div>
		<div title="活期明细"  style="height:520px">
			<table id="data3" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 
					<th data-options="field:'account_no',align:'center',width:150" >账号</th>
					<th data-options="field:'money',align:'center',width:100" >金额</th>
					<th data-options="field:'weidu_sum',align:'center',width:100" >渠道维度总和</th>
					<th data-options="field:'dk_power',align:'center',width:100" >电费</th>
					<th data-options="field:'dk_tv',align:'center',width:100" >数字电视费</th>	
					<th data-options="field:'dk_water',align:'center',width:100" >水费	</th>	
					<th data-options="field:'dk_gas',align:'center',width:100" >燃气费</th>	
					<th data-options="field:'dk_med',align:'center',width:100" >医疗保险</th>
					<th data-options="field:'dk_telec',align:'center',width:100" >电信</th>	
					<th data-options="field:'dk_mphone',align:'center',width:100" >移动</th>
					<th data-options="field:'df',align:'center',width:100" >代发工资</th>	
					<th data-options="field:'netbank',align:'center',width:100" >网银</th>
				</tr>
			</thead>
		</table>
		</div>
		<div title="保险明细"  style="height:520px">
				<table id="data4" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 			
					<th data-options="field:'baoxian_no',align:'center',width:150" >保单号 </th>
					<th data-options="field:'baofei',align:'center',width:100" >合同保费</th>
					<th data-options="field:'type',align:'center',width:250" >险种</th>
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'pol_final_date',align:'center',width:100" >到期日期</th>
			 
			
				</tr>
			</thead>
		</table>
		</div>
		<div title="理财明细" style="height:520px">
				<table id="data5" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 			
				
					<th data-options="field:'contractno',align:'center',width:150" >合同号 </th>
					<th data-options="field:'money_final',align:'center',width:100" >金额</th>
					<th data-options="field:'product_name',align:'center',width:250" >产品</th>
				
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'enddate',align:'center',width:100" >到期日期</th>
			 
		
			
				</tr>
			</thead>
		</table>
		</div>
		<div title="储蓄式国债明细"  style="pading:10px;height:520px">
				<table id="data6" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 			
		 
			
				<th data-options="field:'account_no',align:'center',width:150" >储蓄托管账号</th>
					<th data-options="field:'money_final',align:'center',width:100" >金额</th>
						<th data-options="field:'product_name',align:'center',width:250" >产品</th>
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'end_date',align:'center',width:100" >到期日期</th>
			 
				</tr>
			</thead>
		</table>
		</div>
		<div title="凭证式国债明细"  style="height:520px;">
				<table id="data7" class="easyui-datagrid" 
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     	pageSize:10" >
			<thead>
				<tr> 			 
					<th data-options="field:'accountcode',align:'center',width:150" >国债账号</th>
					<th data-options="field:'money_final',align:'center',width:100" >金额</th>
						<th data-options="field:'product_name',align:'center',width:250" >产品</th>
					<th data-options="field:'trade_date',align:'center',width:100" >存入日期</th>
					<th data-options="field:'end_date',align:'center',width:100" >到期日期</th>
			 
				</tr>
			</thead>
		</table>
		</div>
		

	</div> 
 
	</body>
</html>
