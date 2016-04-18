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
		
		<script type="text/javascript">
	
	var root="${session.userInfo.brch_no}";
	var area_no = "${session.userInfo.area_no}";
	var query_url = "userinfo_userinfo_query.action";
	var add_url = "userinfo_userinfo_add.action";
	var delete_url = "userinfo_userinfo_delete.action";
	var edit_url = "userinfo_userinfo_edit.action";
	var power_url = "userinfo_userinfo_power.action";
	var getUserID_url = "userinfo_userinfo_getUserIDFromUserName.action";
	var getBrchName_url = "userinfo_userinfo_getBrchNameFromBrchNo.action";
	var sele_user_id;
	var sele_username;
	var sele_nickname;
	var sele_brchno;
	var sele_brchname;
	var my_old_username;
	var sele_power_brch_name;
	var sele_power_role_name;
	var sele_power_brch_no;
	var sele_power_role_id;
	
	
	function query_user_info() {
		var tmp_username = $('#user_name').val();
		var tmp_nickname = $('#user_nick_name').val();
		var tmp_brchno = $('#user_brch_no').val();
		$('#data1').datagrid("options").url = query_url;
		$('#data1').datagrid("load", {
    		'user_name':tmp_username,
    		'user_nick_name':tmp_nickname,
    		'brch_no':tmp_brchno
    		});
	}
	
	
	
    function power_submit(){
    	var brch_sn = $('#BrchCombo').combotree('getValue');
    	var cb;
    	$("input[checked='true']").each(function(){
    		cb+=$(this).val()+"#";
    	});
    	alert('brch_sn:'+brch_sn+'\n'+'power:'+cb+'\n');
    }
    
    function query_power_info(){
    	$("#data2").datagrid("options").url="userinfo_loadPowerDetailForUser.action";
    	$("#data2").datagrid("load",{
    		'user_name':sele_username});
    }
    
    function loadPowerForUser(rowData){
    	
    	sele_username=rowData.user_name;
    	sele_nickname=rowData.user_nick_name;
    	sele_brchname = rowData.user_brch_name;
    	sele_brchno = getBrchNoFromBrchName(sele_brchname);
    	sele_power_brch_name = '';
    	sele_power_role_name = '';
    	$("#data2").datagrid("options").url="userinfo_loadPowerDetailForUser.action";
    	$("#data2").datagrid("load",{
    		'user_name':rowData.user_name});
    }
    function loadPowerForUserWithSeleUserName(ss){
    	$("#data2").datagrid("options").url="userinfo_loadPowerDetailForUser.action";
    	$("#data2").datagrid("load",{
    		'user_name':ss});
    }
    function getSelePowerDetail(rowData){
    	sele_power_brch_name = rowData.brch_name;
    	sele_power_role_name = rowData.role_name;
    }
    
    $.extend($.fn.validatebox.defaults.rules, {
        /*��֤�������������Ƿ���ͬ*/
        equalTo: {
            validator:function(value,param){
                return $(param[0]).val() == value;
            },
            message:'�ֶβ�ƥ��'
        },
        checkUniqe:{
        	validator:function(value,param){
        	var postdate={};
        	postdate[param[1]]=value;
        	if(param[2])postdate[param[2]]=$("#"+param[2]).val();
        	var result=$.ajax({url:param[0],dataType:"json",data:postdate,async:false,cache:false,type:"post"}).responseText;
        	var json=eval("("+result+")");
        	this.message=json.msg;
        	return json.success;
        	},
        	message:'���û����Ѿ���ռ��'
        },
        checkUniqeForUserEdit:{
        	validator:function(value,param){
        		var postdate={};
        		postdate[param[1]]=value;
        		if(param[2]){
        			postdate[param[2]]=$("#"+param[2]).val();
        		}
        		if(value==my_old_username){
        			return true;
        		}else{
        			var result=$.ajax({url:param[0],dataType:"json",data:postdate,async:false,cache:false,type:"post"}).responseText;
                	var json=eval("("+result+")");
                	this.message=json.msg;
                	return json.success;
        		}
        	},
        	message:'���û����Ѿ���ռ��'
        }
        
    });
	
    loadBrchCombo("#brch_tree",root);
    loadBrchCombo("#brch_tree1",root);
    
    
    function show_msg(){
    	var brch_no = $('#brch_tree').combotree('getValue');
    	var add_username = $('#add_username').val();
    	var add_nickname = $('add_nickname').val();
    	var add_password = $('#add_password').val();
    	if(typeof(add_nick)=='undefined'){
    		add_nickname="";
    	}
    	alert(
    			'add_username:'+add_username+'\n'
    			+'add_nickname:'+add_nickname+'\n'
    			+'add_password:'+add_password+'\n'
    			+'brch_no:'+brch_no
    			);
    }
    
    function initPowerDlgRoleSele(){
    	var url="userinfo_initPowerDlgRoleSele.action";
		var json={};
		json["testParam"]="a1a1";
		$.ajax({
			type : "POST",
			cache : false,
			async:true,
			url : url,
					
			dataType : "json",
			data : json,
			success : function(msg) {
				try {
					var obj = msg;
					obj.sort(function(a,b){return a.role_id-b.role_id;});
					$("#Add_role_sele option").each(function(){
						if($(this).val() != -1){
							$(this).remove(); 
						} 
					});
					for(var i=0;i<obj.length;i++){
						$("#Add_role_sele").append("<option value='"+obj[i].role_id+"'>"+obj[i].role_name+"</option>");
					}
				} catch (exception) {
					alert(exception);
				}
			},
			error : function(msg) {
				alert('error!!');
			}
		});
    }
    function initPowerDlgRoleSeleForEdit(){
    	var url="userinfo_initPowerDlgRoleSele.action";
		var json={};
		json["testParam"]="a1a1";
		$.ajax({
			type : "POST",
			cache : false,
			async:false,
			url : url,
					
			dataType : "json",
			data : json,
			success : function(msg) {
				try {
					var obj = msg;
					obj.sort(function(a,b){return a.role_id-b.role_id;});
					$("#edit_role_sele option").each(function(){
						if($(this).val() != -1){
							$(this).remove();
						} 
					});
					for(var i=0;i<obj.length;i++){
						$("#edit_role_sele").append("<option value='"+obj[i].role_id+"'>"+obj[i].role_name+"</option>");
					}
				} catch (exception) {
					alert(exception);
				}
			},
			error : function(msg) {
				alert('error!!');
			}
		});
    }
   	function initAddPowerDlg(){
   		
   		if(typeof(sele_username)!='undefined'&&sele_username!=''){
   			$('#user_power_add_dlg').dialog('open');
   	    	loadBrchCombo("#brch_tree2",sele_brchno);
   	    	$('#brch_tree2').combotree('setValue','');
   			$('#brch_tree2').combotree('setText','');
   	   		initPowerDlgRoleSele();
		}else{
			$.messager.alert('��ʾ','����ѡ����Ҫ���Ȩ�޵��û����ٽ���Ȩ����Ӳ�����');
			return;
		}
   	}
    
    function add_user_power(){
    	if(!$("form[id='user_power_add_frm']").form("validate")){
			return;
		}
    	
    	if(typeof(sele_username)!='undefined'){
    		$.messager.confirm('ȷ�����Ȩ�ޣ�','ȷ�Ͻ���Ȩ����Ӳ�����',function(r){
        	    if (r){
        	    	sele_user_id = getUserIDFromUserName(sele_username);
        	    	var brch_no = $('#brch_tree2').combotree('getValue');
        	    	var role_id = $('#Add_role_sele').val();
        	    	/*
        	    	alert(
        	    			'area_no:'+area_no+'\n'+
        	    			'brch_no:'+brch_no+'\n'+
        	    			'role_id:'+role_id+'\n'+
        	    			'sele_user_id:'+sele_user_id+'\n'
        	    			);
        	    	*/
        	    	$('#user_power_add_frm').form('submit', {
        	    	    url:'userinfo_userpower_add.action',
        	    	    onSubmit: function(param){
        	    	        // do some check
        	    	        // return false to prevent submit;
        	    	    	param.user_id = sele_user_id;
        	    	    	param.brch_no = brch_no;
        	    	    	param.role_id = role_id;
        	    	    	param.area_no = area_no;
        	    	    },
        	    	    success:function(data){
        	    	    	var json=eval("("+data+")");
        	    	    	if(json.success){
        	    	    		query_power_info();
        	    	    		$.messager.alert('�û�Ȩ�������ɹ�',json.msg);
        	    	    	}else{
        	    	    		$.messager.alert('�û�Ȩ������ʧ��',json.msg);
        	    	    	}
        	    	    }
        	    	});
        	    }else{
        	    	return;
        	    }
        	});
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫ���Ȩ�޵��û����ٽ���Ȩ����Ӳ�����');
    		return;
    	}
    }
    
    function add_user(){
    	//user_add_fm
    	
    	var brch_no = $('#brch_tree').combotree('getValue');
    	var add_username = $('#add_username').val();
    	var add_nickname = $('#add_nickname').val();
    	var add_password = $('#add_password').val();
    	/*
    	$('#user_add_fm').form('load',{
    		'brch_no':brch_no,
    		'username':add_username,
    		'nickname':add_nickname,
    		'password':add_password});
    	*/
    	if(!$("form[id='user_add_fm']").form("validate")){
			return;
		}
    	
    	var fmdata = $('#user_add_fm').form2json();
    	$('#user_add_fm').form('submit', {
    	    url:add_url,
    	    onSubmit: function(param){
    	        // do some check
    	        // return false to prevent submit;
    	    	param.brch_no = brch_no;
    	    	param.user_name = add_username;
    	    	param.user_nick_name = add_nickname;
    	    	param.password = add_password;
    	    },
    	    success:function(data){
    	    	var json=eval("("+data+")");
    	    	if(json.success){
    	    		query_user_info();
    	    		$.messager.alert('�û������ɹ�',json.msg);
    	    	}else{
    	    		$.messager.alert('�û�����ʧ��',json.msg);
    	    	}
    	    }
    	});
    }
    function getRoleIDFromRoleName(ss){
    	var postdate={};
    	postdate['role_name']=ss;//sele_username
    	var result=$.ajax(
    			{
    				url:'userinfo_getRoleIDFromRoleName.action',
    				dataType:"json",
    				data:postdate,
    				async:false,
    				cache:false,
    				type:"post"
    				}
    			).responseText;
    	var json=eval("("+result+")");
    	//sele_user_id=json.msg;
    	return json.msg;
    }
    function getUserIDFromUserName(ss){
    	var postdate={};
    	postdate['user_name']=sele_username;//sele_username
    	var result=$.ajax(
    			{
    				url:getUserID_url,
    				dataType:"json",
    				data:postdate,
    				async:false,
    				cache:false,
    				type:"post"
    				}
    			).responseText;
    	var json=eval("("+result+")");
    	//sele_user_id=json.msg;
    	return json.msg;
    }
    function getBrchNameFromBrchNo(ss){
    	var postdate={};
    	postdate['brch_no']=ss;//sele_brchno
    	var result=$.ajax(
    			{
    				url:getBrchName_url,
    				dataType:"json",
    				data:postdate,
    				async:false,
    				cache:false,
    				type:"post"
    				}
    			).responseText;
    	var json=eval("("+result+")");
    	//sele_brchname=json.msg;
    	return json.msg;
    }
    function getBrchNoFromBrchName(ss){
    	var postdate={};
    	postdate['brch_name']=ss;
    	var result=$.ajax(
    			{
    				url:'userinfo_userinfo_getBrchNoFromBrchName.action',
    				dataType:"json",
    				data:postdate,
    				async:false,
    				cache:false,
    				type:"post"
    				}
    			).responseText;
    	var json=eval("("+result+")");
    	return json.msg;
    }
    function submit_edit_user(){
    	if(!$("form[id='user_edit_fm']").form("validate")){
			return;
		}
    	var edit_brch_no = $('#brch_tree1').combotree('getValue');
    	var edit_username = $('#edit_username').val();
    	var edit_nickname = $('#edit_nickname').val();
    	var edit_password = $('#edit_password').val();
    	
    	if(edit_brch_no==old_brchno_for_user_edit){
    		$('#user_edit_fm').form('submit', {
	    	    url:edit_url,
	    	    onSubmit: function(param){
	    	        // do some check
	    	        // return false to prevent submit;
	    	    	param.brch_no = edit_brch_no;
	    	    	param.user_name = edit_username;
	    	    	param.user_nick_name = edit_nickname;
	    	    	param.password = edit_password;
	    	    	param.user_id = sele_user_id;
	    	    	param.status = "0";
	    	    },
	    	    success:function(data){
	    	    	var json=eval("("+data+")");
	    	    	if(json.success){
	    	    		query_user_info();
	    	    		$.messager.alert('�û��޸ĳɹ�',json.msg);
	    	    	}else{
	    	    		$.messager.alert('�û��޸�ʧ��',json.msg);
	    	    	}
	    	    }
	    	});
    	}else{
    		$.messager.confirm('ȷ�϶��û��������Ž����޸ģ�','ȷ�϶��û��������Ž����޸ģ����޸��û��������Ž���������ʷ��ɫȨ�޶�ʧ����',function(r){
        	    if (r){
        	    	$('#user_edit_fm').form('submit', {
        	    	    url:edit_url,
        	    	    onSubmit: function(param){
        	    	        // do some check
        	    	        // return false to prevent submit;
        	    	    	param.brch_no = edit_brch_no;
        	    	    	param.user_name = edit_username;
        	    	    	param.user_nick_name = edit_nickname;
        	    	    	param.password = edit_password;
        	    	    	param.user_id = sele_user_id;
        	    	    	param.status = "1";
        	    	    },
        	    	    success:function(data){
        	    	    	var json=eval("("+data+")");
        	    	    	if(json.success){
        	    	    		query_user_info();
        	    	    		$.messager.alert('�û��޸ĳɹ�',json.msg);
        	    	    	}else{
        	    	    		$.messager.alert('�û��޸�ʧ��',json.msg);
        	    	    	}
        	    	    }
        	    	});
        	    }else{
        	    	return;
        	    }
        	});
    	}
    }
    var old_brchno_for_user_edit;
    var old_brchname_for_user_edit;
    function edit_user(){
    	if(typeof(sele_username)!='undefined'){
    		sele_user_id = getUserIDFromUserName(sele_username);
    		sele_brchname = getBrchNameFromBrchNo(sele_brchno);
    		/*
    		alert(
        			'user_id:'+sele_user_id+'\n'+
        			'user_name:'+sele_username+'\n'+
        			'nick_name:'+sele_nickname+'\n'+
        			'brch_no:'+sele_brchno+'\n'+
        			'brch_name:'+sele_brchname+'\n'
        			);
    		*/
    		old_brchno_for_user_edit='';
    		old_brchname_for_user_edit='';
    		$('#user_edit_dlg').dialog('open');
    		$('#edit_username').val(sele_username);
    		$('#edit_nickname').val(sele_nickname);
    		old_brchno_for_user_edit=sele_brchno;
    		old_brchname_for_user_edit=sele_brchname;
    		$('#brch_tree1').combotree('setValue',sele_brchno);
    		$('#brch_tree1').combotree('setText',sele_brchname);
    		my_old_username=sele_username;
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫ�޸ĵ��û����ٽ����޸Ĳ�����');
    		return;
    	}
    }
    
    function initeditpower(){
    	if(typeof(sele_username)!='undefined'&&sele_username!=''
			&&typeof(sele_power_brch_name)!='undefined'&&sele_power_brch_name!=''
			&&typeof(sele_power_role_name)!='undefined'&&sele_power_role_name!=''){
    		loadBrchCombo("#brch_tree3",sele_brchno);
    		initPowerDlgRoleSeleForEdit();
    		$('#user_power_edit_dlg').dialog('open');
    		sele_power_brch_no = getBrchNoFromBrchName(sele_power_brch_name);
    		$('#brch_tree3').combotree('setValue',sele_power_brch_no);
    		$('#brch_tree3').combotree('setText',sele_power_brch_name);
    		sele_power_role_id = getRoleIDFromRoleName(sele_power_role_name);
    		//alert(w);
    		/*
    		$("#edit_role_sele option").each(function(){
    			$("#edit_role_sele option[text='"+sele_power_role_name+"']").attr("selected", "selected");
    		});
    		
    		*/
    		$("#edit_role_sele option").each(function(){
				if($(this).val() == sele_power_role_id){
					$(this).attr("selected", true);
				}
			});
    		
    		//$("#edit_role_sele").val('"+w+"');
    		//document.getElementById("#edit_role_sele").options[1].setAttribute("selected", true);
    		//$("#edit_role_sele").find("option").eq(w).attr("selected","selected");
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫ�޸ĵ��û����ٽ����޸Ĳ�����');
    		return;
    	}
    }
    function edit_user_power(){///////////////////////////////////////////////
    	if(!$("form[id='user_power_edit_dlg']").form("validate")){
			return;
		}
    	if(typeof(sele_username)!='undefined'&&sele_username!=''
			&&typeof(sele_power_brch_name)!='undefined'&&sele_power_brch_name!=''
			&&typeof(sele_power_role_name)!='undefined'&&sele_power_role_name!=''){
    		$.messager.confirm('ȷ���޸�Ȩ�ޣ�','ȷ�Ͻ���Ȩ���޸Ĳ�����',function(r){
        	    if (r){
        	    	
        	    	var brch_no = $('#brch_tree3').combotree('getValue');
        	    	var role_id = $('#edit_role_sele').val();
        	    	var user_name = sele_username;
        	    	
        	    	
        	    	$('#user_power_edit_frm').form('submit', {
        	    	    url:'userinfo_userpower_edit.action',
        	    	    onSubmit: function(param){
        	    	        // do some check
        	    	        // return false to prevent submit;
        	    	        param.power_edit_brch_no = brch_no;
        	    	        param.power_edit_role_id = role_id;
        	    	        param.power_edit_user_name = user_name;
        	    	        param.area_no = area_no;
        	    	        
        	    	        param.old_power_brch_no = sele_power_brch_no;
        	    	        param.old_power_role_id = sele_power_role_id;
        	    	    },
        	    	    success:function(data){
        	    	    	var json=eval("("+data+")");
        	    	    	if(json.success){
        	    	    		query_power_info();
        	    	    		$.messager.alert('�û�Ȩ���޸ĳɹ�',json.msg);
        	    	    	}else{
        	    	    		$.messager.alert('�û�Ȩ���޸�ʧ��',json.msg);
        	    	    	}
        	    	    }
        	    	});
        	    	
        	    }else{
        	    	return;
        	    }
        	});
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫ�޸�Ȩ�޵��û����ٽ���Ȩ���޸Ĳ�����');
    		return;
    	}
    }
    function deletepower(){
    	//sele_power_brch_name
    	//sele_power_role_name
    	if(typeof(sele_username)!='undefined'&&sele_username!=''
    			&&typeof(sele_power_brch_name)!='undefined'&&sele_power_brch_name!=''
    			&&typeof(sele_power_role_name)!='undefined'&&sele_power_role_name!=''){
    		$.messager.confirm(
    				'ȷ��ɾ��Ȩ�ޣ�','ȷ��ɾ���û���'+sele_username+'��Ȩ�ޣ� <br>'
    				+'[������'+sele_power_brch_name+',��ɫ��'+sele_power_role_name+']��',function(r){
        	    if (r){
        	  		
        	    	var postdate={};
        	    	postdate['delete_power_username']=sele_username;
        	    	postdate['delete_power_brchname']=sele_power_brch_name;
        	    	postdate['delete_power_rolename']=sele_power_role_name;
        	    	postdate['area_no']=area_no;
        	    	var result=$.ajax(
        	    			{
        	    				url:'userinfo_userpower_delete.action',
        	    				dataType:"json",
        	    				data:postdate,
        	    				async:false,
        	    				cache:false,
        	    				type:"post"
        	    				}
        	    			).responseText;
        	    	var json=eval("("+result+")");
        	    	if(json.success){
        	    		query_power_info();
        	    		$.messager.alert('�û�ɾ���ɹ�',json.msg);
        	    	}else{
        	    		$.messager.alert('�û�ɾ��ʧ��',json.msg);
        	    	}
        	    	
        	    }else{
        	    	return;
        	    }
        	});
    	}else{
    		$.messager.alert('��ʾ','����ѡ����Ҫɾ��Ȩ�޵��û���Ȩ�ޣ��ٽ���ɾ��������');
    		return;
    	}
    }
    
    function delete_user(){
    	if(typeof(sele_username)!='undefined'){
    		$.messager.confirm('ȷ��ɾ����','ȷ��ɾ���û�'+sele_username+'����ɾ���û��������û���ɫȨ��һ����ɾ������',function(r){
        	    if (r){
        	    	var postdate={};
        	    	postdate['delete_user_name']=sele_username;
        	    	var result=$.ajax(
        	    			{
        	    				url:delete_url,
        	    				dataType:"json",
        	    				data:postdate,
        	    				async:false,
        	    				cache:false,
        	    				type:"post"
        	    				}
        	    			).responseText;
        	    	var json=eval("("+result+")");
        	    	if(json.success){
        	    		query_user_info();
        	    		sele_username = '';
        	    		query_power_info();
        	    		$.messager.alert('�û�ɾ���ɹ�',json.msg);
        	    	}else{
        	    		$.messager.alert('�û�ɾ��ʧ��',json.msg);
        	    	}
        	    }else{
        	    	return;
        	    }
        	});
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫɾ�����û����ٽ���ɾ��������');
    		return;
    	}
    	
    }
    function initMainBrchDlg(){
    	
    	if(typeof(sele_username)!='undefined'&&sele_username!=''){
    		$.messager.confirm('ȷ���޸��������ţ�','ȷ���޸��û�['+sele_username+']������������',function(r){
        	    if (r){
        	    	$('#user_main_brch_dlg').dialog('open');
        	    	var url="userinfo_initMainBrchSele.action";
        			var json={};
        			json["user_name"]=sele_username;
        			$.ajax({
        				type : "POST",
        				cache : false,
        				async:false,
        				url : url,
        						
        				dataType : "json",
        				data : json,
        				success : function(msg) {
        					try {
        						var obj = msg;
        						obj.sort(function(a,b){return a.brch_no-b.brch_no;});
        						$("#main_brch_brchsele option").each(function(){
        							if($(this).val() != -1){
        								$(this).remove();
        							} 
        						});
        						for(var i=0;i<obj.length;i++){
        							$("#main_brch_brchsele").append("<option value='"+obj[i].brch_no+"'>"+obj[i].brch_name+"</option>");
        						}
        					} catch (exception) {
        						alert(exception);
        					}
        				},
        				error : function(msg) {
        					alert('error!!');
        				}
        			});	
        	    }else{
        	    	return;
        	    }
        	});
    	}else{
    		$.messager.alert('��ʾ','��ѡ����Ҫ�޸��������ŵ��û����ٽ��в�����');
    		return;
    	}
    }
    function submitMainBrch(){
    	var b = $("#main_brch_brchsele").find("option:selected").text();
    	var bn = $("#main_brch_brchsele").val();
    	$.messager.confirm('ȷ���޸��������ţ�','ȷ���޸��û�['+sele_username+']������������',function(r){
    	    if (r){
    	    	alert(b);
    	    	alert(bn);
    	    }else{
    	    	return;
    	    }
    	});
    }
    function outputExcel(a,b,mm,nn){
    	var c = b+".action?login_brch_no="+root+"&excelFileName="+mm+"&excelTitleName="+nn;
    	expexcel(a,c);
    }
</script>
	</head>
	<body  class="easyui-layout">
		<div id="user_toolbar" style="padding: 3px; display: none">
			<form id="userfm" style="margin: 0">
				&nbsp;&nbsp;&nbsp;�û���:
				<input type="text" name="user_name" id="user_name"/>
				&nbsp;&nbsp;&nbsp;�û��ǳ�:
				<input type="text" name="user_nick_name" id="user_nick_name"/>
				&nbsp;&nbsp;&nbsp;������:
				<input type="text" name="user_brch_no" id="user_brch_no" class="easyui-validatebox"
					data-options="validType:'number'" />
				<br>
				&nbsp;
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="$('#user_add_dlg').dialog('open')">����</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="delete_user()">ɾ��</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="edit_user()">�޸�</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-search'" onclick="query_user_info()">��ѯ</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="outputExcel('#data1','userinfo_excel','��Ա����','��Ա��Ϣ��')">excel����</a>
				<!-- 
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-tip'" onclick="alert('�ù��ܻ��ڿ�����')">������������</a>
				-->
			</form>
		</div>
		<div id="power_toolbar" style="padding: 3px; display: none">
			<form id="powerfrm" style="margin: 0">
				&nbsp;
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="initAddPowerDlg()">����</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-remove'"
					onclick="deletepower()">ɾ��</a>
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="initeditpower()">�޸�</a>
				<!-- 
				<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-excel'" onclick="expexcel('#data2','userinfo_excel')">excel����</a>
				 -->
			</form>
		</div>
		<div data-options="region:'center' ,title:'�û�����ҳ��',split:true" style="height:350px;background:#fafafa;overflow:hidden;" >
		<table id="data1" class="easyui-datagrid"
			style="height:30%;"
			data-options="
     	fit:true,rownumbers:true,pagination:true,toolbar:'#user_toolbar',
     	pageSize:20,singleSelect:true,url:query_url,
     	onClickRow:function(rowIndex, rowData)
     	{loadPowerForUser(rowData);},">
			<thead>
				<tr>
					<th data-options="field:'user_name'" width="180">
						�û���
					</th>
					<th data-options="field:'user_nick_name'" width="180">
						�û��ǳ�
					</th>
					<th data-options="field:'user_brch_name',align:'center'" width="300">
						������
					</th>
				</tr>
			</thead>
		</table>
		</div>
		<div data-options="region:'south',title:'Ȩ������ҳ��',split:true" style="height:250px;background:#fafafa;overflow:hidden;">
		<table id="data2" class="easyui-datagrid"
			style="height:30%;"
			data-options="
     	fit:true,rownumbers:true,pagination:true,
     	toolbar:'#power_toolbar',
     	pageSize:20,singleSelect:true,
     	onClickRow:function(rowIndex, rowData)
     	{getSelePowerDetail(rowData);},">
			<thead>
				<tr>
					<th data-options="field:'brch_name',align:'center'" width="520">
						��������
					</th>
					<th data-options="field:'role_name',align:'center'" width="160">
						��ɫID
					</th>
				</tr>
			</thead>
		</table>
		</div>
		<div style="display: none">
			<!-- ����excel ����ѡ��  -->
			<jsp:include page="/sys/UIExcel.jsp">
			</jsp:include>
		</div>
		<div id="user_add_dlg" class="easyui-dialog" title="�û�����" closed="true" data-options="iconCls:'icon-add'" style="width:245px;height:250px;padding:10px">
        	<form id="user_add_fm" method="post" novalidate>
            	<div class="fitem">
               	 	<label>�û���:&nbsp;&nbsp;&nbsp;</label>
                	<input name="add_username" id="add_username" validType="checkUniqe['userinfo_checkUserNameUnique.action','check_user_name']" class="easyui-validatebox" required="true" invalidMessage="���û����Ѿ���ռ��" />
            	</div>
            	<div class="fitem">
                	<label>�û��ǳ�:</label>
                	<input name="add_nickname" id="add_nickname" class="easyui-validatebox">
            	</div>
            	<div class="fitem">
                	<label>����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                	<input type="password" name="add_password" id="add_password" validType="length[4,32]" class="easyui-validatebox" required="true" value=""/>
            	</div>
            	<div class="fitem">
                	<label>ȷ������:</label>
                	<input type="password" name="add_repassword" id="add_repassword" validType="equalTo['#add_password']" class="easyui-validatebox" required="true" />
            	</div>
            	<div class="fitem">
					<label>����ѡ��:</label>
					<input type="text" name="brch_tree" id="brch_tree" class="BrchCombo"/>
		    	</div>
		    	<div>
    				<img alt="" src="/weblib/js/jquery-easyui-1.3.5/themes/icons/tip.png"/>
            		<font face="����"><font color="#FF6600"><b>�û������������Ľ�ɫȨ�޷�Χ�������ѡ��</b></font></font>
    			</div>
		    	<div class="fitem">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'"	onclick="$('#user_add_dlg').dialog('close');">ȡ��</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="add_user();">ȷ��</a>
		    	</div>
        	</form>
    	</div>
    	<div id="user_edit_dlg" class="easyui-dialog" title="�û��޸�" closed="true" data-options="iconCls:'icon-ok'" style="width:245px;height:185px;padding:10px">
    		<form id="user_edit_fm" method="post" novalidate>
            	<div class="fitem">
               	 	<label>�û���:&nbsp;&nbsp;&nbsp;</label>
                	<input name="edit_username" id="edit_username" validType="checkUniqeForUserEdit['userinfo_checkUserNameUnique.action','check_user_name']" class="easyui-validatebox" required="true" />
            	</div>
            	<div class="fitem">
                	<label>�û��ǳ�:</label>
                	<input name="edit_nickname" id="edit_nickname" class="easyui-validatebox">
            	</div>
            	<div class="fitem">
                	<label>����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                	<input type="password" name="edit_password" id="edit_password" validType="length[4,32]" class="easyui-validatebox" required="true" value=""/>
            	</div>
            	<div class="fitem">
                	<label>ȷ������:</label>
                	<input type="password" name="edit_repassword" id="edit_repassword" validType="equalTo['#edit_repassword']" class="easyui-validatebox" required="true"  invalidMessage="�����������벻һ��"/>
            	</div>
            	<div class="fitem">
					<label>����ѡ��:</label>
					<input type="text" name="brch_tree1" id="brch_tree1" class="BrchCombo" disabled="true"/>
		    	</div>
		    	<div class="fitem">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'"	onclick="$('#user_edit_dlg').dialog('close');">ȡ��</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="submit_edit_user();">�޸�</a>
		    	</div>
        	</form>
    	</div>
    	<div id="user_power_add_dlg" class="easyui-dialog" title="Ȩ������" closed="true" data-options="iconCls:'icon-add'" style="width:500px;height:120px;padding:10px">
    		<form id="user_power_add_frm" method="post" novalidate>
            	<div class="fitem">
					<label>����ѡ��:</label>
					<input type="text" name="brch_tree2" id="brch_tree2" class="BrchCombo"/>
		    		<label>��ɫѡ��:</label>
		    		<select name="Add_role_sele" id="Add_role_sele" STYLE="width: 160px">
					<option value='100001' selected='true'>��ѡ���ɫ...</option>
				</select>
		    	</div>
		    	<div class="fitem">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'"	onclick="$('#user_power_add_dlg').dialog('close');">ȡ��</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-add'" onclick="add_user_power();">����</a>
		    	</div>
        	</form>
    	</div>
    	<div id="user_power_edit_dlg" class="easyui-dialog" title="Ȩ���޸�" closed="true" data-options="iconCls:'icon-add'" style="width:500px;height:120px;padding:10px">
    		<form id="user_power_edit_frm" method="post" novalidate>
            	<div class="fitem">
					<label>����ѡ��:</label>
					<input type="text" name="brch_tree3" id="brch_tree3" class="BrchCombo"/>
		    		<label>��ɫѡ��:</label>
		    		<select name="edit_role_sele" id="edit_role_sele" STYLE="width: 160px">
					</select>
		    	</div>
		    	<div class="fitem">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'"	onclick="$('#user_power_edit_dlg').dialog('close');">ȡ��</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="edit_user_power();">�޸�</a>
		    	</div>
        	</form>
    	</div>
    	<div id="user_main_brch_dlg" class="easyui-dialog" title="������������" closed="true" data-options="iconCls:'icon-tip'" style="width:370px;height:130px;padding:10px">
    		<form id="user_main_brch_frm" method="post" novalidate>
    			<div>
    				<img alt="" src="/weblib/js/jquery-easyui-1.3.5/themes/icons/tip.png"/>
            		<font face="����"><font color="#FF6600"><b>�������Ž����������Ȩ�޵Ĺ����������ѡ��</b></font></font>
    			</div>
    			<div>
    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<label>��������ѡ��:</label>
		    		<select name="main_brch_brchsele" id="main_brch_brchsele" STYLE="width: 160px">
					</select>
    			</div>
		    	<div class="fitem">
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cancel'"	onclick="alert('������������-ȡ��');">ȡ��</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    		<a href="###" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-ok'" onclick="submitMainBrch()">ȷ��</a>
		    	</div>
        	</form>
    	</div>
    	<div style="display:none">
      	<!-- ����excel ����ѡ��  -->
      		<jsp:include page="/sys/UIExcel.jsp" >
      		</jsp:include>
      	</div>
	</body>
</html>
