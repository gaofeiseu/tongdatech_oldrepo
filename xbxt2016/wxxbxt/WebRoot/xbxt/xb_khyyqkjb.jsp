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
		<title>cust_month_usage_report</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
    	var url;
		var query_url = "/xbxt_query_khyyqkjb.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		loadBigCustSearch(".BigCustCombo");
	    });	
      	function loadBigCustSearch(selector){
        	if($(selector).is("input")){ grid=selector;
    		}else grid=$(selector).find("#search_area");
    		
    		$(grid).combogrid({  
    			panelWidth: 600,
    			mode: 'remote',
    			multiple:false,
                idField: 'cust_no',  
                textField: 'cust_name',
                url: '/xbxt_cust_search.action',
                columns: [[
                    {field:'cust_no',title:'Э��ͻ���',width:200},      
                    {field:'cust_name',title:'�ͻ�����',width:250},
                    {field:'cust_brch',title:'�ͻ���������',width:150}
                ]],  
                fitColumns: true,
                onLoadSuccess:function(data){
                	 if($(grid).attr("text")!=undefined){
                		 $(grid).combogrid("setText",$(grid).attr("text")); 
                	 }
                }
         	});
        }
		function doquery() {
			if($("#queryfm").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm").form2json();
				$("#tb").datagrid("load", fmdata);
				ajax_custinfo(fmdata);
			}
		}
		function ajax_custinfo(_fmdata){
			$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_ajax_custinfo.action",
        		dataType : "json",
        		data : _fmdata,
        		success : function(msg) {
        			try {
        				$("#hidden_msg").hide();
        				$("#hidden_msg").text("");
        				if(msg.backParam.bigcustname&&msg.backParam.bigcustname!=''&&msg.backParam.bigcustname!='undefined'&&msg.backParam.bigcustname!='null'){
        					$("#hidden_msg").show();
            				var _s = "�ͻ����ƣ�"+msg.backParam.bigcustname+" ��ҵ��"+msg.backParam.industry+" �󶨻�����"+msg.backParam.deptname+" �ͻ�����"+msg.backParam.mgrname;
            				$("#hidden_msg").text(_s);
        				}
        			}
        			catch (exception) {
        				console.log(exception);
        			}
        		}
        	});
		}
		function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        function myparser(s){
            if (!s) return new Date();
            var ss = (s.split('-'));
            var y = parseInt(ss[0],10);
            var m = parseInt(ss[1],10);
            var d = parseInt(ss[2],10);
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                return new Date(y,m-1,d);
            } else {
                return new Date();
            }
        }
        function expExcel(){
        	var excelfmurl="/xbxt_expexcel_khyyqkjb_report.action?"+$.param($("#tb").datagrid("options").queryParams);;//$.param($("#tb").datagrid("options").queryParams);
        	$('#queryfm').form('submit', {   
        	    url:excelfmurl,   
        	    onSubmit: function(){  
        	    	
        	    },
        	    success:function(result){   
					        	       
        	    }   
			});  
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="�ͻ�����������" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
						�ͻ����ƣ�<input type="text" id="cust_id" name="cust_id" class="BigCustCombo" value=""  data-options="required:true,missingMessage:'��ѡ'"/>
						��ѯ���£�<input class="easyui-datebox" required name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
						<a href="javascript:expExcel();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-excel'" >excel����</a>
						<br/>
						<div id="hidden_msg" style="display:none"></div>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'�ͻ��¶����ʱ���', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true">
					<thead>
						<tr class="header">
							<th data-options="field:'cust_id',align:'center',width:150" >�ͻ���</th>
							<th data-options="field:'destination',align:'center',width:150" >·��</th>
							<th data-options="field:'send_num',align:'center',width:100" >������</th>
							<th data-options="field:'standard_fee',align:'center',width:100" >��׼�ʷ�</th>
							<th data-options="field:'fee',align:'center',width:100" >ʵ���ʷ�</th>
							<th data-options="field:'mail_weight',align:'center',width:100" >�ʼ�����</th>
							<th data-options="field:'direct_mail_cost',align:'center',width:100" >�ʼ����˳ɱ�</th>
							<th data-options="field:'real_direct_mail_cost',align:'center',width:100" >�ʼ�ֱ�ӳɱ�</th>
							<th data-options="field:'discount_persent',align:'center',width:100" >�ۿ���(�ٷ�֮)</th>
							<th data-options="field:'mail_aver_time',align:'center',width:100" >�ʼ�ƽ��ʱ��</th>
							<th data-options="field:'total_aver_time',align:'center',width:100" >��ƽ��ʱ��</th>
							<th data-options="field:'last_month_send_num',align:'center',width:100" >���·�����</th>
							<th data-options="field:'maoli',align:'center',width:100" >ë��</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
	function abcc(){
		var v_param = {};
  		var if_load = false;
  		<s:if test="bean.bigcustno!=null">
  		v_param["cust_id"]="<s:property value="bean.bigcustno"/>";
  		if_load = true;
  		</s:if>
  		<s:if test="bean.query_time!=null">
  		v_param["query_time"]="<s:property value="bean.query_time"/>";
  		if_load = true;
  		</s:if>
  		if(if_load){
  			ajax_custinfo(v_param);
  			setTimeout(function () { 
  				$("#tb").datagrid("load", v_param);
  		    }, 500);
  		}
	}
	abcc();
</script>
			
