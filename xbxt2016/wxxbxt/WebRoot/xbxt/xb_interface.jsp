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
		<title>xb_interface</title>
		<xl:resource></xl:resource>
		<style type="text/css">
		body { font-family: "Verdana", "Arial", "Helvetica", "sans-serif"; font-size: 12px; line-height: 180%; }
		td { font-size: 12px; line-height: 150%; }
		</style>
		<script type="text/javascript">
		var settings_query_url = "/xbxt_settings_query.action";
		var log_query_url = "/xbxt_log_query.action";
    	function paramsettings_add(){
    		$("#settings_dlg").dialog("open").dialog("setTitle","����");
			$("#settings_fm").form("clear");
    	}
    	function paramsettings_edit(){
    		var row =  $("#tb_paramsettings").datagrid("getSelected");
			if(row){
				$("#settings_dlg").dialog("open").dialog("setTitle","�޸�");
				$("#settings_fm").form("load",row);
				console.log(row);
			}else{
				$.messager.alert("����","��ѡ����Ҫ�༭���У�");
			}
    	}
    	function paramsettings_delete(){
    		var row =  $("#tb_paramsettings").datagrid("getSelected");
			if(row){
				$.messager.confirm('ȷ��','ȷ��ɾ�����Ϊ'+row.sn+'�����ݣ�',function(r){
					if (r){
						$.ajax({
	                		type : "POST",
	                		cache : false,
	                		async:true,
	                		url : "/xbxt_deleteInterfaceSettings.action",
	                		dataType : "json",
	                		data : {"sn":row.sn},
	                		success : function(msg) {
	                			$.messager.show({title: "��ʾ",msg: msg.msg});
	                			if(msg.success){
	                				$("#tb_paramsettings").datagrid("reload");
	                			}
	                		}
	                	});
       				}
       			}); 
			}else{
				$.messager.alert("����","����ѡ��һ���û�");
			}
    	}
    	function saveSettings(){
    		fmSubmit("#settings_fm","/xbxt_saveInterfaceSettings.action",
    			function(result){
          			$("#tb_paramsettings").datagrid("reload");
                    $("#settings_dlg").dialog("close");
        		}
    		);
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
        function log_query(){
        	var _time = $("#query_time").datebox('getValue');
        	if(_time&&_time!=''){
        		var _data = {};
          		_data["query_time"] = _time;
          		$("#tb_log").datagrid("load", _data);
        	}
        }
		function log_reload(){
			$("#tb_log").datagrid("reload");
        }
        function manual_run(){
        	$('#btn_manualrun').linkbutton('disable');
        	fmSubmit("#queryfm_manualrun","/xbxt_manual_run.action",
        		function(result){
        			$('#btn_manualrun').linkbutton('enable');
        		}
        	);
        	/* if($("#query_time").val()!=""&&$("#query_num").val()!=""){
        		var _d = {};
            	_d["query_time"] = $("#query_time").val();
            	_d["query_num"] = $("#query_num").val();
            	$.ajax({
            		type : "POST",
            		cache : false,
            		async : true,
            		url : "/xbxt_manual_run.action",
            		dataType : "json",
            		data : _d,
            		success : function(msg) {
            			if(msg.success){
            				$.messager.show({title: "�ɹ�",msg: msg.msg});
            			}else{
            				$.messager.show({title: "ʧ��",msg: msg.msg});
            			}
            		}
            	});
        	} */
        }
        /* function manual_copy(){
        	$('#btn_manualcopy').linkbutton('disable');
        	fmSubmit("#queryfm_manualrun","/xbxt_manual_copy.action",
        		function(result){
        			$('#btn_manualcopy').linkbutton('enable');
        		}
        	);
        } */
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="������־" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'center'">
						<div id="toolbar_log" style="padding:10px;display:none">
							<form id="queryfm_log" style="margin:0">
								ָ�����ڲ�ѯ��־��<input class="easyui-datebox" id="query_time" name="query_time" data-options="formatter:myformatter,parser:myparser" />
								<a href="javascript:log_query();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
								<a href="javascript:log_reload();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" >ˢ��</a>
							</form>
						</div>
						<table id="tb_log" class="easyui-datagrid" data-options="title:'����������', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar_log',
     						url:log_query_url,pageSize:50,singleSelect:true">
							<thead>
								<tr class="header">
									<th data-options="field:'sn',align:'center',width:50" >���</th>
									<th data-options="field:'log_type',align:'center',width:200" >��־����</th>
									<th data-options="field:'log_str',align:'center',width:600" >��־����</th>
									<th data-options="field:'log_date_str',align:'center',width:200" >��־ʱ��</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div title="�ֶ�ִ��" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'center'">
						<form id="queryfm_manualrun" style="margin:0">
						ִ������ʱ��(�ռ�����)(��ʼʱ��)��<input class="easyui-datebox" name="query_time" required data-options="formatter:myformatter,parser:myparser" value="" />
						<br/>
						ִ������ʱ��(�ռ�����)(����ʱ��)��<input class="easyui-datebox" name="query_time_ed" required data-options="formatter:myformatter,parser:myparser" value="" />
						<br/>
						<!-- ִ��������������ʼ����<input type="text" name="query_num_st" value="" data-options="required:true,validType:'numeric'" /> -->
						ִ������������<input type="text" name="query_num_ed" value="" data-options="required:true,validType:'numeric'" />
						<a href="javascript:manual_run();" id="btn_manualrun" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'" >ִ��</a>
						<!-- <a href="javascript:manual_copy();" id="btn_manualcopy" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'" >��ʼ���ӿڲ�ѯ�������gnxb_ord_peo_prtcin��</a> -->
						</form>
					</div>
				</div>
			</div>
			<div title="���ղ�������" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'center'">
						<div id="toolbar_paramsettings" style="padding:10px;display:none">
							<form id="queryfm_paramsettings" style="margin:0">
								<a href="javascript:paramsettings_add();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" >����</a>
								<a href="javascript:paramsettings_edit();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >�޸�</a>
								<a href="javascript:paramsettings_delete();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" >ɾ��</a>
							</form>
						</div>
						<table id="tb_paramsettings" class="easyui-datagrid" data-options="title:'���ղ���', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar_paramsettings',
     						url:settings_query_url,pageSize:50,singleSelect:true">
							<thead>
								<tr class="header">
									<th data-options="field:'sn',align:'center',width:50" >���</th>
									<th data-options="field:'param_name',align:'center',width:200" >������</th>
									<th data-options="field:'param_value',align:'center',width:300" >����ֵ</th>
									<th data-options="field:'param_flag_str',align:'center',width:100" >����״̬</th>
									<th data-options="field:'param_comments',align:'center',width:400" >������ע</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="settings_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{text:'����',plain:true,iconCls:'icon-ok',handler:function(){saveSettings();}},
				{text:'ȡ��',plain:true,iconCls:'icon-cancel',handler:function(){$('#settings_dlg').dialog('close');}}]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="settings_fm" method="post">
					<input type="hidden" name="sn" value="" />
					<div class="form_item col1">
				   		<label>��������</label>
				   		<input type="text" name="param_name" class="easyui-validatebox" style="width:200px" data-options="required:true" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>����ֵ��</label>
				   		<input type="text" name="param_value" class="easyui-validatebox" style="width:200px" data-options="required:true" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>������ע��</label>
				   		<input type="text" name="param_comments" class="easyui-validatebox" style="width:200px" data-options="required:true" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>����״̬��</label>
				   		<!-- <input type="text" name="param_flag" class="easyui-validatebox" style="width:200px" data-options="required:true" /> -->
				   		<select class="easyui-combobox" data-options="required:true" name="param_flag" style="width:200px">
							<option value="0">����</option>
							<option value="1" selected>����ʹ��</option>
						</select>
			  		</div>
			  </form>
		</div>
	</body>
</html>