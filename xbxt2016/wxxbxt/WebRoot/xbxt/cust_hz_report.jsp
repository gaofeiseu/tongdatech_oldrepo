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
		<title>cust_hz_report</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
    	var url;
		var query_url = "/xbxt_query_cust_hz_report.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		loadBigCustSearch(".BigCustCombo");
      		loadMgrSearch(".custmgrCombo");
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
      	function loadMgrSearch(selector){
        	if($(selector).is("input")){ grid=selector;
    		}else grid=$(selector).find("#search_area");
    		
    		$(grid).combogrid({  
    			panelWidth: 600,
    			mode: 'remote',
    			multiple:false,
                idField: 'mgr_no',  
                textField: 'mgr_name',
                url: '/xbxt_custmgr_search.action',
                columns: [[
					{field:'mgr_no',title:'�ͻ������',width:150},
                    {field:'mgr_name',title:'�ͻ�������',width:100},
                    {field:'department',title:'��������',width:250},
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
			}
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
        	var excelfmurl="/xbxt_expexcel_hz_report.action?"+$.param($("#tb").datagrid("options").queryParams);
        	$('#queryfm').form('submit', {   
        	    url:excelfmurl,   
        	    onSubmit: function(){  
        	    	
        	    },
        	    success:function(result){   
					        	       
        	    }   
			});  
        }
        function bigcustno_formatter(value,row,index){
        	return "<a href=\"/xbxt_init_khyyqkjb.action?bigcustno="+value+"&query_time="+$("#tb").datagrid("options").queryParams["query_time"]+"\">"+value+"</a>";//javascript:alert('������"+value+"')
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="�ͻ�����������ܱ�" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
						�ͻ����ƣ�<input type="text" name="cust_id" class="BigCustCombo" value=""  data-options="missingMessage:'��ѡ'"/>
						��ѯ���£�<input class="easyui-datebox" name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						�ͻ������ͻ�����ţ�<input type="text" id="cust_mgrname" style="width:250px" name="cust_mgrno" class="custmgrCombo" value="" />
						<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
						<a href="javascript:expExcel();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-excel'" >excel����</a>
						<br/>
						��ǰ��̯������<font color="red"><s:property value="bean.gdft"/></font>���ò��������ڡ�С������������á�ģ�����޸ġ�
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'�ͻ�����������ܱ�', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true">
					<thead>
						<tr class="header">
							<th data-options="field:'bigcustno',align:'center',width:150,formatter:bigcustno_formatter" >�ͻ���</th>
							<th data-options="field:'bigcustname',align:'center',width:150" >�ͻ�����</th>
							<th data-options="field:'mgrname',align:'center',width:100" >�����ͻ�����</th>
							<th data-options="field:'send_num',align:'center',width:100" >������</th>
							<th data-options="field:'standard_fee',align:'center',width:100" >��׼�ʷ�</th>
							<th data-options="field:'fee',align:'center',width:100" >ʵ���ʷ�</th>
							<th data-options="field:'discount_persent',align:'center',width:100" >�ۿ���(�ٷ�֮)</th>
							<th data-options="field:'real_direct_mail_cost',align:'center',width:100" >ֱ�ӳɱ�</th>
							<th data-options="field:'maoli',align:'center',width:100" >ë��</th>
							<th data-options="field:'last_month_send_num',align:'center',width:100" >���·�����</th>
							<th data-options="field:'direct_mail_cost',align:'center',width:100" >���˳ɱ�</th>
							<th data-options="field:'department',align:'center',width:250" >��������</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</body>
</html>