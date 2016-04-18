<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'brch_init.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<xl:resource></xl:resource>
  </head>
  
  
  <script type="text/javascript">
  	var url;
  	var root="${session.userInfo.brch_no}"; 
	$(document).ready(function(){
	loadBrchTree(".brchTree",root,"",click); //@see /weblib/js/easyui-common.js
	loadAreaSearch(".AreaCombo");//@see /weblib/js/easyui-common.js
	loadBrchCombo(".BrchCombo",root);
	});
	     
	function click(node){
		 //alert("onclick==>"+node.id);
		 $("#frm").form("clear");
		 url = "brch_queryOne.action";
		 $.post(url,{brch_no:node.id},function(result){
		  $("#frm").form("clear");
		 	$("#frm").form("load",result);
		// 	$(".AreaCombo").combogrid('setValue',result.area_no);
		 //	$(".AreaCombo").combogrid('setText',result.area_name);
		 	$(".BrchCombo").combotree('setValue',result.brch_up);
		 		$(".BrchCombo").combotree('setValue',result.brch_up_name);
		 },"json");
	}
	
	function addBrch(){
		$("#frm").form("clear");
		$("#brch_flag_id").combobox('setValue','1');
	}
	
	function saveBrch(){
		var brch_no = $("input[name='brch_no']").val();
		var brch_flag = $("input[name='brch_flag']").val();
		if(brch_flag == "1"){
			doSave();
		}else{//��������־Ϊ0(ɾ��)\2(����),��ʾ
			url = "brch_isChild.action";
			$.post(url,{brch_no:brch_no},function(result){
				if(result.success){
					alert("�ò��Ŵ����¼�����,�޷�ɾ��������!");
					return;
				}else{
					doSave();
				}
			},"json");
		}
	}
	
	function doSave(){
		//@see /weblib/js/easyui-common.js
		url="brch_brchSave.action";
		fmSubmit("#frm",url,function(result){
			//ˢ�»�����
			var tar=$(".brchTree").tree("getRoot");
			$(".brchTree").tree("reload",tar.target);
			//ˢ��combotree
			var ctree=$(".BrchCombo").combotree("tree");	
			var tar1 = ctree.tree("getRoot");
			ctree.tree("reload",tar1.target);
			
			//$("#brch_no").val(result.backParam);
		});
	}
	
	function editBrch(){
		url = "/brch_brchEdit.action";
		var brch_no = $("#brch_no").val();
		var brch_name = $("input[name='brch_name']").val();
		if(brch_no==""||brch_name==""){
			$.messager.alert("������ʾ", "����ѡ����Ҫ�޸ĵĲ��ţ��ٽ����޸Ĳ�����","info");
			return;
		}
		$.messager.confirm("������ʾ", "��ȷ��Ҫ��brch_noΪ��"+brch_no+"�� brch_nameΪ��"+brch_name+"���Ĳ�����Ϣ�����޸���", function (data) {
            if (data) {  
            	var brch_code = $("input[name='brch_code']").val();
            	$("input[name='brch_code']").val(brch_code+"###");
            	$('#editBrchBtn').linkbutton('disable');
            	$("#frm").form("submit",{
                	url: url,
            		onSubmit: function(){
            			return $(this).form("validate");
            		},
            		success: function(result){
            			var result = eval("("+result+")");
            			if (result.success){
            				$('#frm').form("clear");
            				$.messager.show({title: "�ɹ�",msg: result.msg+"��������ˢ�²�������"});
            			} else {
            				$.messager.show({title: "����",msg: result.msg});
            			}
            			$('#editBrchBtn').linkbutton('enable');
            		}
            	});
            }  
            else {  
                return; 
            }  
        });
	}
	     
  </script>
  
  <body>
    
			<div id="lay1" class="easyui-layout" style="width:100%;height:600px;">
				<div data-options="region:'west',title:'������',split:true" style="width:300px">
					<div class="col3">
		  				<div class="brchTree"></div>
					</div>
				</div>
				
				<div data-options="region:'center',title:'��������',split:true" style="background: #fafafa; overflow: hidden;">
				
					<div  style="padding: 10px;">
						<a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addBrch()">����</a>
						<a href="###" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" onclick="saveBrch()">����</a>
						<a href="###" id="editBrchBtn" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editBrch();">�༭</a>
						<hr>
						<form id="frm" method="post">
						<input type="hidden" name="brch_no" id="brch_no"/>
							<div class="form_item col1">
								<label>���źţ�</label>
								<input type="text" name="brch_code" class="easyui-validatebox"  style="width:250px" data-options="required:true,delay:500,validType:'Ajax[\'brch_idCheck\',\'brch_code\',\'brch_no\']'"/>
								<!--   -->
							</div>
							<div class="form_item col1">
								<label>�������ƣ�</label>
								<input type="text" name="brch_name" class="easyui-validatebox" style="width:250px" data-options="required:true,missingMessage:'�������Ʋ���Ϊ��'"/>
							</div>
							<input type="hidden" name="brch_up_name" value="">
							<div class="form_item col1">
								<label>�ϼ��������ƣ�</label>
								<input type="text" name="brch_up" class="BrchCombo"   value=""  data-options="required : true"/>
							</div>
							<input type="hidden" name="area_name"/>
							<div class="form_item col1">
								<label>����������</label>
								<input type="text" name="area_no" class="AreaCombo" value=""  data-options="required:true,missingMessage:'��ѡ'"/>
							</div>
							<div class="form_item col1">
								<label>����ģʽ��</label>
								<input type="text"  name="brch_mode"  class="easyui-combobox" style="width: 150px" 
								data-options="required:true,missingMessage:'��ѡ',valueField:'value',textField:'text',data:${application.ParamJson.BRCH_MODE}"/>
							</div>
							<div class="form_item col1"> 
								<label>������ʶ��</label>
								<input type="text"  name="brch_flag" id="brch_flag_id" class="easyui-combobox" style="width: 150px" 
								data-options="valueField:'value',textField:'text',data:${application.ParamJson.BRCH_FLAG_SHOW}"/>
							</div>
							<div class="form_item col1">
								<label>��������</label>
								<input type="text" name="order_id" class="easyui-validatebox" style="width:250px"/>
							</div>
						</form>
					
					</div>
				
					
				</div>
			</div>    	
    	
 
  </body>
</html>
