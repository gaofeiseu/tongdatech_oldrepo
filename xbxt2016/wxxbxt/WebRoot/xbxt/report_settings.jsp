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
		var query_url = "/xbxt_query2.action";
		var query_url2 = "/xbxt_query5.action";
		var query_url3 = "/xbxt_query3.action";
		var query_url4 = "/xbxt_query4.action";
		
		var query_url7 = "/xbxt_query7.action";
		
		var root="${session.userInfo.brch_no}";
		var __cust_id = "";
		var __cust_name = "";
      	$(document).ready(function(){
      		loadXBBrchTree(".brchTree","21000100","",click);
      		loadXBBrchTree(".brchTree2","21000100","",click2);
      		loadXBBrchTree(".brchTree3","21000100","",click3);
      		loadXBBrchCombo(".xbBrchCombo","113080");
      		loadMgrSearch(".custmgrCombo");
      		getFTParam();
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
      	function click(node){
      		//_brch_code = node.id;
      		doquery(node.id);
   		}
      	function click2(node){
      		//_brch_code = ;
      		$("#tb2").datagrid("load", {"brch_code":node.id});
   		}
      	function click3(node){
      		//_brch_code = ;
      		$("#tb7").datagrid("load", {"brch_code":node.id});
   		}
      	function doquery(_id){
      		var _data = {};
      		_data["brch_code"] = _id;
			$("#tb").datagrid("load", _data);
      	}
      	function doEdit(){
      		var row =  $("#tb").datagrid("getSelected");
			if(row){
				$("#edit_dlg").dialog("open").dialog("setTitle","�޸ģ�"+row.v_jdjsmgm+"��");
				$("#edit_fm").form("load",row);
			}else{
				$.messager.alert("����","��ѡ����Ҫ�༭���У�");
			}
      	}
      	function EditRealFee(){
      		var row =  $("#tb2").datagrid("getSelections");
			if(row&&row.length>0){
				var _province = "";
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_province += row[i].province;
					_sn += row[i].param_sn;
					if(i!=(row.length-1)){
						_province += ",";
						_sn += ",";
					}
				}
				$("#edit_dlg4").dialog("open").dialog("setTitle","�޸ģ�"+_province+"��");
				if(row.length==1){
					$("#edit_fm4").form("load",row[0]);
				}else{
					$("#edit_fm4_sn").val(_sn);
				}
			}else{
				$.messager.alert("����","��ѡ����Ҫ�༭���У�");
			}
      	}
      	function EditStandardFee(){
      		var row =  $("#tb7").datagrid("getSelections");
			if(row&&row.length>0){
				var _province = "";
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_province += row[i].province;
					_sn += row[i].param_sn;
					if(i!=(row.length-1)){
						_province += ",";
						_sn += ",";
					}
				}
				$("#edit_dlg7").dialog("open").dialog("setTitle","�޸ģ�"+_province+"��");
				if(row.length==1){
					$("#edit_fm7").form("load",row[0]);
				}else{
					$("#edit_fm7_sn").val(_sn);
				}
			}else{
				$.messager.alert("����","��ѡ����Ҫ�༭���У�");
			}
      	}
      	
      	function doEdit2(){
      		var row =  $("#tb4").datagrid("getSelections");
			if(row&&row.length>0){
				var _province = "";
				var _sn = "";
				for(var i=0;i<row.length;i++){
					_province += row[i].province;
					_sn += row[i].sn;
					if(i!=(row.length-1)){
						_province += ",";
						_sn += ",";
					}
				}
				$("#edit_dlg2").dialog("open").dialog("setTitle","�޸ģ�"+_province+"��");
				if(row.length==1){
					$("#edit_fm2").form("load",row[0]);
				}else{
					$("#edit_fm2_sn").val(_sn);
				}
			}else{
				$.messager.alert("����","��ѡ����Ҫ�༭���У�");
			}
      	}
      	function doSaveChange(){
      		fmSubmit("#edit_fm","/xbxt_editSettings.action",
				function(result){
      				$("#tb").datagrid("reload");
                	$("#edit_dlg").dialog("close");
    		});
      	}
      	function doSaveChange4(){
      		fmSubmit("#edit_fm4","/xbxt_editSettings4.action",
				function(result){
      				$("#tb2").datagrid("reload");
                	$("#edit_dlg4").dialog("close");
    		});
      	}
      	function doSaveChange7(){
      		fmSubmit("#edit_fm7","/xbxt_editSettings7.action",
    			function(result){
          			$("#tb7").datagrid("reload");
                    $("#edit_dlg7").dialog("close");
        	});
      	}
      	function doSaveChange2(){
      		fmSubmit("#edit_fm2","/xbxt_editSettings2.action",
				function(result){
      				$("#tb4").datagrid("reload");
                	$("#edit_dlg2").dialog("close");
    		});
      	}
      	function doquery2(){
      		var _data = {};
      		_data["bigcust_type"] = $("bigcust_type").val();
      		$("#tb3").datagrid("load", _data);
      	}
      	function load_othertable(rowIndex, rowData){
      		if(rowData&&rowData.sskhdm){
      			var _data = {};
          		_data["cust_no"] = rowData.sskhdm;
          		__cust_id = rowData.sskhdm;
          		__cust_name = rowData.jjrdwmc;
          		$("#tb4").datagrid("load", _data);
      		}
      	}
      	function dobuquan(){
      		var _data = $('#tb4').datagrid('getData');
      		if(_data&&Number(_data.total)<31){
      			if(__cust_id&&__cust_id!=""){
      				var _p = "";
      				if(_data.total!=0){
      					for(var i=0;i<_data.rows.length;i++){
          					_p += _data.rows[i].province+",";
          				}
      				}
      				$.ajax({
                		type : "POST",
                		cache : false,
                		async:true,
                		url : "/xbxt_dobuquan.action",
                		dataType : "json",
                		data : {"cust_id":__cust_id,"provinces_str":_p,"cust_name":__cust_name},
                		success : function(msg) {
                			$.messager.show({title: "��ʾ",msg: msg.msg});
                			if(msg.success){
                				$("#tb3").datagrid("reload");
                				$("#tb4").datagrid("reload");
                			}
                		}
                	});
      			}else{
      				$.messager.show({title: "��ʾ",msg: "�ͻ��Ż�ȡʧ�ܣ������¼��غ����ԣ�"});
      			}
      		}else{
      			$.messager.show({title: "��ʾ",msg: "�Ѿ�����������31��ʡ�е����ݣ�����Ҫ���в�ȫ��"});
      		}
      	}
      	function doQueryForTB3(){
      		if($("#queryfm3").form('enableValidation').form('validate')){
				var fmdata = $("#queryfm3").form2json();
				$("#tb3").datagrid("load", fmdata);
			}
      	}
      	function changeBigcustType(newValue, oldValue){
      		var fmdata = {};
      		fmdata["bigcust_type"] = newValue;
			$("#tb3").datagrid("load", fmdata);
      	}
      	function setcustinfo(){
      		var row =  $("#tb3").datagrid("getSelected");
			if(row){
				$("#custinfo_fm").form("clear");
				$("#custinfo_fm").form("load",row);
				$.ajax({
            		type : "POST",
            		cache : false,
            		async : true,
            		url : "/xbxt_getinfo_forsetcustinfo.action",
            		dataType : "json",
            		data : {"bigcustno":row.sskhdm},
            		success : function(msg) {
            			if(msg.success){
            				//$("#tb3").datagrid("reload");
            				//$("#tb4").datagrid("reload");
            				$("#custinfo_industry").val(msg.backParam.industry);
            				$("#custinfo_dept").combotree("setValue",msg.backParam.dept_no);
            				$("#custinfo_dept").combotree("setText",msg.backParam.dept_name);
            				//$.messager.show({title: "��ʾ",msg: "dept_no:"+$("#custinfo_dept").combotree("getValue")});
            			}
            		}
            	});
				$("#custinfo_dlg").dialog("open").dialog("setTitle","�ͻ������޸�");
		     	//$("#user_brch_no").combogrid("setText",$("#user_brch_name").val());
		     	//$("#user_type").combogrid("setText",$("#user_type_name").val());
			}else{
				$.messager.show({title: "��ʾ",msg: "����ѡ��һ����ͻ�"});
			}
      	}
      	function setcustmgrinfo(){
      		var row =  $("#tb3").datagrid("getSelected");
			if(row){
				$("#custmgr_fm").form("clear");
				$("#custmgr_fm").form("load",row);
				$.ajax({
            		type : "POST",
            		cache : false,
            		async : true,
            		url : "/xbxt_getinfo_forsetcustmgrinfo.action",
            		dataType : "json",
            		data : {"bigcustno":row.sskhdm},
            		success : function(msg) {
            			if(msg.success){
            				$("#cust_mgrname").combogrid("setValue",msg.backParam.mgrid);
            				$("#cust_mgrname").combogrid("setText",msg.backParam.mgrname);
            			}
            		}
            	});
				$("#custmgr_dlg").dialog("open").dialog("setTitle","�ͻ���Ӧ�ͻ������޸�");
			}else{
				$.messager.show({title: "��ʾ",msg: "����ѡ��һ����ͻ�"});
			} 
      	}
      	function save_custinfo(){
      		fmSubmit("#custinfo_fm","/xbxt_savecustinfo",function(result){
            	if(result.success){
            		$('#custinfo_dlg').dialog('close');
            		$("#tb3").datagrid("reload");
    				$("#tb4").datagrid("reload");
            	}
			});
      	}
      	function save_custmgrinfo(){
      		fmSubmit("#custmgr_fm","/xbxt_savecustmgrinfo",function(result){
            	if(result.success){
            		$('#custmgr_dlg').dialog('close');
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
      	
      	function getFTParam(){
      		$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_get_FTParam.action",
        		dataType : "json",
        		data : {},
        		success : function(result) {
        			if(result.success){
        				$("#queryfm_ft input[name='ftparam']").val(result.msg);
        			}
        		}
        	});
      	}
      	function set_ftparam(){
      		$.ajax({
        		type : "POST",
        		cache : false,
        		async:true,
        		url : "/xbxt_set_FTParam.action",
        		dataType : "json",
        		data : {'ftparam':$("#queryfm_ft input[name='ftparam']").val()},
        		success : function(result) {
        			$.messager.show({title: "��ʾ",msg: result.msg});
        		}
        	});
      	}
		</script>
	</head>
	<body>
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="���˳ɱ�������" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west',split:true,title:'ѡ����'" style="width:300px;">
						<div class="brchTree"></div>
					</div>
					<div data-options="region:'center',title:'������'">
						<div id="toolbar" style="padding:10px;display:none">
							<form id="queryfm" style="margin:0">
								<a href="javascript:doEdit();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >�޸�</a>
							</form>
						</div>
						<table id="tb" class="easyui-datagrid" data-options="title:'DS_GNXB_JSCB_BJ', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar',
     						url:query_url,pageSize:50,singleSelect:true">
							<thead>
								<tr class="header">
									<th data-options="field:'v_jdjsmgm',align:'center',width:250" >�Ĵ��ʡ��</th>
									<th data-options="field:'hcys',align:'center',width:100" >������</th>
									<th data-options="field:'qcys',align:'center',width:100" >��������</th>
									<th data-options="field:'nbcl',align:'center',width:100" >�ڲ�����</th>
									<th data-options="field:'jz',align:'center',width:100" >��ת</th>
									<th data-options="field:'td',align:'center',width:100" >Ͷ��</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div title="ֱ�ӳɱ���������" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west',split:true,title:'ѡ����'" style="width:300px;">
						<div class="brchTree2"></div>
					</div>
					<div data-options="region:'center',title:'������'">
						<div id="toolbar2" style="padding:10px;display:none">
							<form id="queryfm2" style="margin:0">
								<a href="javascript:EditRealFee();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >�޸ĵ���ϵ��a�͹̶�����b</a>
							</form>
						</div>
						<table id="tb2" class="easyui-datagrid" data-options="title:'�ʷ���Ϣ', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar2',
     						url:query_url2,pageSize:50"><!-- ,singleSelect:true -->
							<thead>
								<tr class="header">
									<th data-options="field:'province',align:'center',width:250" >�Ĵ��ʡ��</th>
									<th data-options="field:'real_fee',align:'center',width:150" >ʵ���ʷ�</th>
									<th data-options="field:'param_a',align:'center',width:150" >���ڲ���a</th>
									<th data-options="field:'param_b',align:'center',width:150" >�̶�����b</th>
									<th data-options="field:'province_aver_time',align:'center',width:150" >��ƽ��ʱ�ޣ����£�</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div title="�ֻ��˱��۲�ѯ��������" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west',split:true,title:'ѡ����'" style="width:300px;">
						<div class="brchTree3"></div>
					</div>
					<div data-options="region:'center',title:'������'">
						<div id="toolbar7" style="padding:10px;display:none">
							<form id="queryfm7" style="margin:0">
								<a href="javascript:EditStandardFee();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >���ڱ�׼�ʷѺ��ۿۺ��ʷ�</a>
							</form>
						</div>
						<table id="tb7" class="easyui-datagrid" data-options="title:'�ʷ���Ϣ', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar7',
     						url:query_url7,pageSize:50"><!-- ,singleSelect:true -->
							<thead>
								<tr class="header">
									<th data-options="field:'province',align:'center',width:250" >�Ĵ��ʡ��</th>
									<th data-options="field:'standard_fee',align:'center',width:200" >��׼�ʷ�</th>
									<th data-options="field:'discount_fee',align:'center',width:200" >�ۿۺ��ʷ�</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div title="��̯��������" style="padding:10px">
				<form id="queryfm_ft" style="margin:0">
					<input type="text" name="ftparam" value="" data-options="required:true,validType:'numeric'" />
					<a href="javascript:set_ftparam();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >�޸ķ�̯����</a>
				</form>
			</div>
			<div title="��ͬ��������" style="padding:10px">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west',split:true,title:'ѡ����'" style="width:480px;">
						<div id="toolbar3" style="padding:10px;display:none">
							<form id="queryfm3" style="margin:0">
								<input type="text" id="bigcust_type" name="bigcust_type" value="1" class="easyui-combobox" style="width: 150px" data-options="onChange:changeBigcustType,valueField:'value',textField:'text',data:<s:property default="[]" value="#application.ParamJson.XB_BIGCUST_TYPE"/> " />
								&nbsp;&nbsp;�ͻ�����ͻ��ţ�<input type="text" id="bigcustnameorbigcustno" name="bigcustnameorbigcustno" class="BigCustCombo" value="" /><!-- <input class="easyui-validatebox textbox" id="bigcustnameorbigcustno" name="bigcustnameorbigcustno" style="width:150px" data-options="required:true" value="" /> -->
								<br/><a href="javascript:doQueryForTB3();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" >��ѯ</a>
							</form>
						</div>
						<table id="tb3" class="easyui-datagrid" data-options="title:'���д�ͻ�', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar3',
     						url:query_url3,pageSize:50,singleSelect:true,onClickRow:load_othertable">
							<thead>
								<tr class="header">
									<th data-options="field:'sskhdm',align:'center',width:150" >��ͻ���</th>
									<th data-options="field:'jjrdwmc',align:'center',width:200" >��ͻ�����</th>
									<th data-options="field:'flag_str',align:'center',width:50" >״̬</th>
								</tr>
							</thead>
						</table>
					</div>
					<div data-options="region:'center',title:'������'">
						<div id="toolbar4" style="padding:10px;display:none">
							<form id="queryfm4" style="margin:0">
								<a href="javascript:dobuquan();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'" >���ݲ�ȫ</a>
								<a href="javascript:doEdit2();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" >�޸ķ��ã����Զ�ѡ��</a>
								<a href="javascript:setcustinfo();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'" >���ÿͻ�����</a>
								<a href="javascript:setcustmgrinfo();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-filter'" >�ͻ�����</a>
							</form>
						</div>
						<table id="tb4" class="easyui-datagrid" data-options="title:'GNXB_HTJE', fit:true,rownumbers:true,pagination:true,toolbar:'#toolbar4',
     						url:query_url4,pageSize:50"><!-- ,singleSelect:true -->
							<thead>
								<tr class="header">
									<th data-options="field:'sn',align:'center',width:50" >���</th>
									<th data-options="field:'province',align:'center',width:100" >ʡ��</th>
									<th data-options="field:'fee',align:'center',width:100" >����</th>
									<th data-options="field:'cust_name',align:'center',width:200" >�ͻ�����</th>
									<th data-options="field:'bind_dept',align:'center',width:100" >�󶨻���</th>
									<th data-options="field:'industry',align:'center',width:100" >��ҵ</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="edit_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{text:'����',plain:true,iconCls:'icon-ok',handler:function(){doSaveChange();}},
				{text:'ȡ��',plain:true,iconCls:'icon-cancel',handler:function(){$('#edit_dlg').dialog('close');}}]"
				style="width:400px;height:300px;padding:10px 10px;">
				<form id="edit_fm" method="post">
					<input type="hidden" name="brch_code" value="" />
					<input type="hidden" name="v_jdjsmgm" value="" />
					<div class="form_item col1">
				   		<label>�����䣺</label>
				   		<input type="text" name="hcys" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>�������䣺</label>
				   		<input type="text" name="qcys" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>�ڲ�����</label>
				   		<input type="text" name="nbcl" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>��ת��</label>
				   		<input type="text" name="jz" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>Ͷ�ݣ�</label>
				   		<input type="text" name="td" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  </form>
		</div>
		<div id="edit_dlg2" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{text:'����',plain:true,iconCls:'icon-ok',handler:function(){doSaveChange2();}},
				{text:'ȡ��',plain:true,iconCls:'icon-cancel',handler:function(){$('#edit_dlg2').dialog('close');}}]"
				style="width:400px;height:150px;padding:10px 10px;">
				<form id="edit_fm2" method="post">
					<input type="hidden" id="edit_fm2_sn" name="param_value" value="" />
					<input type="hidden" name="cust_name" value="" />
					<input type="hidden" name="cust_id" value="" />
					<div class="form_item col1">
				   		<label>���ã�</label>
				   		<input type="text" name="fee" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  </form>
		</div>
		<div id="edit_dlg4" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{text:'����',plain:true,iconCls:'icon-ok',handler:function(){doSaveChange4();}},
				{text:'ȡ��',plain:true,iconCls:'icon-cancel',handler:function(){$('#edit_dlg4').dialog('close');}}]"
				style="width:400px;height:200px;padding:10px 10px;">
				<form id="edit_fm4" method="post">
					<input type="hidden" id="edit_fm4_sn" name="param_sn" value="" />
					<div class="form_item col1">
				   		<label>����ϵ��a��</label>
				   		<input type="text" name="param_a" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>�̶�����b��</label>
				   		<input type="text" name="param_b" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  </form>
		</div>
		<div id="edit_dlg7" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[{text:'����',plain:true,iconCls:'icon-ok',handler:function(){doSaveChange7();}},
				{text:'ȡ��',plain:true,iconCls:'icon-cancel',handler:function(){$('#edit_dlg7').dialog('close');}}]"
				style="width:400px;height:200px;padding:10px 10px;">
				<form id="edit_fm7" method="post">
					<input type="hidden" id="edit_fm7_sn" name="param_sn" value="" />
					<div class="form_item col1">
				   		<label>��׼�ʷѣ�</label>
				   		<input type="text" name="standard_fee" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  		<div class="form_item col1">
				   		<label>�ۿۺ��ʷѣ�</label>
				   		<input type="text" name="discount_fee" class="easyui-validatebox" style="width:200px" data-options="required:true,validType:'numeric'" />
			  		</div>
			  </form>
		</div>
		<div id="custinfo_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){save_custinfo();}},
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	handler:function(){$('#custinfo_dlg').dialog('close');}  }]"
				style="width:600px;height:400px;padding:10px 10px;">
			<form id="custinfo_fm" method="post">
				<div class="form_item col1">
				   	<label>��ͻ��ţ�</label>
				   	<input type="text" name="sskhdm" class="easyui-validatebox" style="width:350px" data-options="required:true" readonly />
			  	</div>
			  	<div class="form_item col1">
				   	<label>��ͻ����ƣ�</label>
				   	<input type="text" name="jjrdwmc" class="easyui-validatebox" style="width:350px" data-options="required:true" readonly />
			  	</div>
			  	<div class="form_item col1">
					<label>��ҵ��</label>
					<input type="text" name="bigcusthy" id="custinfo_industry" class="easyui-validatebox" style="width:350px" data-options="required:true" />
			 	</div>
			 	<div class="form_item col1">
					<label>�󶨻�����</label>
					<input type="text" class="xbBrchCombo" id="custinfo_dept" name="bigcustbrch" style="width:350px" data-options="required:true"/>
			 	</div>  
			</form>
		</div>
		<div id="custmgr_dlg" class="easyui-dialog"  data-options="closed:true,resizable:true,modal:true,
				buttons:[
				{ text:'����',plain:true,iconCls:'icon-ok',	    handler:function(){save_custmgrinfo();}},
				{ text:'ȡ��',plain:true,iconCls:'icon-cancel',	handler:function(){$('#custmgr_dlg').dialog('close');}  }]"
				style="width:500px;height:150px;padding:10px 10px;">
			<form id="custmgr_fm" method="post">
				<input type="hidden" name="sskhdm" />
			  	�ͻ������ͻ�����ţ�<input type="text" id="cust_mgrname" style="width:250px" name="cust_mgrno" class="custmgrCombo" value="" data-options="required:true,missingMessage:'��ѡ'"/>
			</form>
		</div>
	</body>
</html>