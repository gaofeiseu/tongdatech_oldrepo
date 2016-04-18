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
		<title>�ͻ������߷����</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
    	var url;
		var query_url = "/xbxt_query_mgr_visit_show.action";
		var root="${session.userInfo.brch_no}";
      	$(document).ready(function(){
      		loadMgrSearch(".custmgrCombo");
	    });	
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
        function handlerImage(value,row,index){
        	if(value&&value!=''&&typeof value !=='undefined'&&value!=='null'){
        		//return "<a href='javascript:void(0);' onclick='open_img_dlg(\""+value+"\");' >��ͼƬ</a>";
        		return "<a href='/xbxt_init_img_show.action?img_path="+value+"' target='_blank'>��ͼƬ</a>";
        	}else{
        		return "��ͼƬ";
        	}
        }
        function handlerWarningVisit(value,row,index){
        	if(value=='1'){
        		return "��";
        		//return "<a href='javascript:void(0);' onclick='alert("+row.sn+");' >��</a>";
        	}else{
        		return "��";
        	}
        }
        function open_img_dlg(value){
        	$("#img_dlg img").attr('src','/xbmobile_file/'+value);
        	$("#img_dlg").dialog("open").dialog("setTitle","ͼƬ���");
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="�ͻ������߷����" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
						�ͻ������ͻ�����ţ�<input type="text" id="cust_mgrname" style="width:250px" name="cust_mgrno" class="custmgrCombo" value="" />
						&nbsp;&nbsp;��ѯ���£�<input class="easyui-datebox" name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						&nbsp;&nbsp;<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
						<br/>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'�ͻ������߷����', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true">
					<thead>
						<tr class="header">
							<th data-options="field:'mgrid',align:'center',width:100" >�ͻ������</th>
							<th data-options="field:'mgrname',align:'center',width:100" >�ͻ���������</th>
							<th data-options="field:'custname',align:'center',width:100" >�߷ÿͻ���</th>
							<th data-options="field:'connect_name',align:'center',width:100" >��ϵ��</th>
							<th data-options="field:'connect_tel',align:'center',width:100" >��ϵ�绰</th>
							<th data-options="field:'custtype_text',align:'center',width:100" >�ͻ�����</th>
							<th data-options="field:'comments',align:'center',width:100" >�߷ñ�ע</th>
							<th data-options="field:'cust_feedback',align:'center',width:100" >�ͻ�����</th>
							<th data-options="field:'imgname',formatter:handlerImage,align:'center',width:100" >ͼƬ</th>
							<th data-options="field:'if_warning_visit',formatter:handlerWarningVisit,align:'center',width:100" >�Ƿ�Ԥ���ͻ��߷�</th>
							<th data-options="field:'visit_time',align:'center',width:100" >�߷�ʱ��</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="img_dlg" class="easyui-dialog" title="wp_comments_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#img_buttons',closed:true" style="width:800px;height:500px;padding:10px">
        	<img src="" />
    	</div>
    	<div id="img_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#img_dlg').dialog('close');">�ر�</a>
    	</div>
	</body>
</html>