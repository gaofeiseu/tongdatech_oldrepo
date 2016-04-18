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
		<title>客户经理走访情况</title>
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
					{field:'mgr_no',title:'客户经理号',width:150},
                    {field:'mgr_name',title:'客户经理名',width:100},
                    {field:'department',title:'所属部门',width:250},
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
        		//return "<a href='javascript:void(0);' onclick='open_img_dlg(\""+value+"\");' >有图片</a>";
        		return "<a href='/xbxt_init_img_show.action?img_path="+value+"' target='_blank'>有图片</a>";
        	}else{
        		return "无图片";
        	}
        }
        function handlerWarningVisit(value,row,index){
        	if(value=='1'){
        		return "是";
        		//return "<a href='javascript:void(0);' onclick='alert("+row.sn+");' >是</a>";
        	}else{
        		return "否";
        	}
        }
        function open_img_dlg(value){
        	$("#img_dlg img").attr('src','/xbmobile_file/'+value);
        	$("#img_dlg").dialog("open").dialog("setTitle","图片浏览");
        }
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="客户经理走访情况" style="padding:10px">
				<div id="toolbar" style="padding:10px;display:none">
					<form id="queryfm" style="margin:0">
						客户经理或客户经理号：<input type="text" id="cust_mgrname" style="width:250px" name="cust_mgrno" class="custmgrCombo" value="" />
						&nbsp;&nbsp;查询年月：<input class="easyui-datebox" name="query_time" data-options="formatter:myformatter,parser:myparser" value="">
						&nbsp;&nbsp;<a href="javascript:doquery();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >查询</a>
						<br/>
					</form>
				</div>
				<table id="tb" class="easyui-datagrid" data-options="title:'客户经理走访情况', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     				url:query_url,pageSize:50,singleSelect:true">
					<thead>
						<tr class="header">
							<th data-options="field:'mgrid',align:'center',width:100" >客户经理号</th>
							<th data-options="field:'mgrname',align:'center',width:100" >客户经理名称</th>
							<th data-options="field:'custname',align:'center',width:100" >走访客户名</th>
							<th data-options="field:'connect_name',align:'center',width:100" >联系人</th>
							<th data-options="field:'connect_tel',align:'center',width:100" >联系电话</th>
							<th data-options="field:'custtype_text',align:'center',width:100" >客户类型</th>
							<th data-options="field:'comments',align:'center',width:100" >走访备注</th>
							<th data-options="field:'cust_feedback',align:'center',width:100" >客户反馈</th>
							<th data-options="field:'imgname',formatter:handlerImage,align:'center',width:100" >图片</th>
							<th data-options="field:'if_warning_visit',formatter:handlerWarningVisit,align:'center',width:100" >是否预警客户走访</th>
							<th data-options="field:'visit_time',align:'center',width:100" >走访时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div id="img_dlg" class="easyui-dialog" title="wp_comments_dlg" closed="true" data-options="iconCls:'icon-edit',buttons:'#img_buttons',closed:true" style="width:800px;height:500px;padding:10px">
        	<img src="" />
    	</div>
    	<div id="img_buttons">
    		<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="$('#img_dlg').dialog('close');">关闭</a>
    	</div>
	</body>
</html>