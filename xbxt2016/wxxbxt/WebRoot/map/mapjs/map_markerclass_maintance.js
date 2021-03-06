/**
 * 
 * @author Mr.GaoFei
 * @Time 2014年7月22日9:43:24
 * 
 */

var l_url1 = "map_markerclassmaintance_loaddata1.action";
var l_url2 = "map_markerclassmaintance_loaddata2.action";
var delete_url1 = "map_markerclassmaintance_deletechildclass.action";
var delete_url2 = "map_markerclassmaintance_deleteclassproperty.action";

/**
 * 主类型ComboBox选择后，动态加载data1
 * @param newvalue
 */
function loadData1ForComboSelectChange(newvalue){
	var status=$('#status').combobox('getValue');
	var url2 = l_url1+'?parentclass_sn='+newvalue+'&childclass_status='+status+'&t_user_type='+t_usertype;
	$('#data1').datagrid({url:url2});
	$('#data2').datagrid('loadData',{total:0,rows:[]});
	rowDataForData1 = null;
}
/**
 * 
 * @param newvalue
 */
function loadData1ForComboSelectChange1(newvalue){
	var parentclass_sn=$('#childclass_parentclass_sn').combobox('getValue');
	var url2 = l_url1+'?parentclass_sn='+parentclass_sn+'&childclass_status='+newvalue+'&t_user_type='+t_usertype;
	$('#data1').datagrid({url:url2});
	$('#data2').datagrid('loadData',{total:0,rows:[]});
	rowDataForData1 = null;
}

/**
 * 打开子类型新增窗口
 */
function openChildClassAddDlg(){
	if($('#childclass_parentclass_sn').combobox('getValue')==''||$('#childclass_parentclass_sn').combobox('getValue')==null||$('#childclass_parentclass_sn').combobox('getValue')==undefined){
		$.messager.show({title: "提示",msg: '进行子类型新增操作前，请先选中需要进行新增的主类型！'});
	}else{
		$('#show_str1').text($('#childclass_parentclass_sn').combobox('getText'));
		$('#childclass_add_for_parentclassid').val($('#childclass_parentclass_sn').combobox('getValue'));
		$('#childclass_add_status').combobox('setValue','1');
		$('#childclass_add_dlg').dialog('open');
	}
}
/**
 * 打开子类型编辑窗口
 */
function openChildClassEditDlg(){
	if(rowDataForData1!=undefined&&rowDataForData1!=null){
		$('#show_str2').text($('#childclass_parentclass_sn').combobox('getText'));
		$('#childclasssn_edit').val(rowDataForData1.childclass_sn);
		$('#childclass_edit_classname').val(rowDataForData1.childclass_name);
//     	var childclass_brchno = [];
//     	childclass_brchno = rowDataForData1.childclass_brchno.split(",");
		$('#childclass_edit_brch').val(rowDataForData1.childclass_brchno);
		$('#childclass_edit_brch_name').val(rowDataForData1.childclass_brchstr);
		$('#childclass_edit_status').combobox('setValue',rowDataForData1.childclass_status);
		$('#childclass_edit_status').combobox('setText',rowDataForData1.childclass_status_str);
		$('#childclass_edit_dlg').dialog('open');
	}else{
		$.messager.show({title: "提示",msg: '进行子类型删除操作前，请先选中需要进行删除的子类型！'});
	}
}
/**
 * 子类型新增窗口的表单提交函数
 */
function submit_childclass_add_fm(){
	var url = "map_markerclassmaintance_addchildclass.action";
	if ($('#childclass_add_fm').form('validate') == false){
		return;
	}
//	var fmdata = $('#childclass_add_fm').form2json();
//	$.ajax({
//		url : url,
//		type : "POST",
//		cache : false,
//		data : fmdata,
//		success : function(result) {
//			var result = eval("(" + result + ")");
//			if (result.success) {
//				$('#childclass_add_dlg').dialog('close');
//				$('#data1').datagrid('reload');
//				$.messager.show({title: "成功",msg: result.msg});
//			} else {
//				$.messager.show({title: "失败",msg: result.msg});
//			}
//		}
//
//	});
	
	fmSubmit("#childclass_add_fm","map_markerclassmaintance_addchildclass.action",
	        function(result){
         
			if (result.success) {
				$('#childclass_add_dlg').dialog('close');
				$('#data1').datagrid('reload');
				$.messager.show({title: "成功",msg: result.msg});
			} else {
				$.messager.show({title: "失败",msg: result.msg});
			}
   });
}
/**
 * 子类型编辑窗口的表单提交函数
 */
function submit_childclass_edit_fm(){
	var url = "map_markerclassmaintance_editchildclass.action";
	if ($('#childclass_edit_fm').form('validate') == false){
		return;
	}
//	var fmdata = $('#childclass_edit_fm').form2json();
//	$.ajax({
//		url : url,
//		type : "POST",
//		cache : false,
//		data : fmdata,
//		success : function(result) {
//			var result = eval("(" + result + ")");
//			if (result.success) {
//				$('#childclass_edit_dlg').dialog('close');
//				$('#data1').datagrid('reload');
//				$.messager.show({title: "成功",msg: result.msg});
//			} else {
//				$.messager.show({title: "失败",msg: result.msg});
//			}
//		}
//
//	});
	fmSubmit("#childclass_edit_fm","map_markerclassmaintance_editchildclass.action",
	        function(result){
         
        	if (result.success) {
				$('#childclass_edit_dlg').dialog('close');
				$('#data1').datagrid('reload');
				$.messager.show({title: "成功",msg: result.msg});
			} else {
				$.messager.show({title: "失败",msg: result.msg});
			}
           
            
	    });
	
}
/**
 * 子类型的属性的新增窗口的表单提交函数
 */
function submit_classproperty_add_fm(){
	var url = "map_markerclassmaintance_submit_classproperty_add.action";
	if ($('#classproperty_add_fm').form('validate') == false){
		return;
	}
	var fmdata = $('#classproperty_add_fm').form2json();
	$.ajax({
		url : url,
		type : "POST",
		cache : false,
		data : fmdata,
		success : function(result) {
			var result = eval("(" + result + ")");
			if (result.success) {
				$('#classproperty_add_dlg').dialog('close');
				$('#data2').datagrid('reload');
				$.messager.show({title: "成功",msg: result.msg});
			} else {
				$.messager.show({title: "失败",msg: result.msg});
			}
		}
	});
}

var rowDataForData1;
/**
 * data1的行选中事件函数
 * @param rowIndex
 * @param rowData
 */
function initData1Params(rowIndex,rowData){
	rowDataForData1 = rowData;
	var url2 = l_url2+'?childclass_sn='+rowData.childclass_sn;
	$('#data2').datagrid({url:url2});
}
/**
 * 打开子类型属性的新增窗口
 */
function openClassPropertyAddDlg(){
	if(rowDataForData1!=undefined&&rowDataForData1!=null){
		$('#classproperty_add_sn').val(rowDataForData1.childclass_sn);
		$('#classproperty_add_status').combobox('setValue','1');
		$('#classproperty_add_dlg').dialog('open');
	}else{
		$.messager.show({title: "提示",msg: '进行子类型属性添加操作前，请先选中需要进行添加属性的子类型！'});
	}
}
/**
 * 子类型的删除操作函数
 */
function delete_childclass(){
	if(rowDataForData1!=undefined&&rowDataForData1!=null){
		$.messager.confirm("确认","确认删除ID为"+rowDataForData1.childclass_sn+"的子类型？",function (r){
	        if (r){
	        	var fmdata = {};
	        	fmdata["sn_for_deletechildclass"]=rowDataForData1.childclass_sn;
	        	$.ajax({
	        		url : delete_url1,
	        		type : "POST",
	        		cache : false,
	        		data : fmdata,
	        		success : function(result) {
	        			var result = eval("(" + result + ")");
	        			if (result.success) {
	        				rowDataForData1=null;
	        				$('#data1').datagrid('reload');
	        				$.messager.show({title: "成功",msg: result.msg});
	        			} else {
	        				rowDataForData1=null;
	        				$('#data1').datagrid('reload');
	        				$.messager.show({title: "失败",msg: result.msg});
	        			}
	        		}
	        	});
	        }else{
	        	return;
	        }
	    });
	}else{
		$.messager.show({title: "提示",msg: '进行子类型删除操作前，请先选中需要进行删除的子类型！'});
	}
}
/**
 * 子类型属性新增窗口的属性类型选择combobox选中改变事件
 * @param newvalue
 */
function changeForComboSelectChange(newvalue){
	if(newvalue=='1'){
		$('#classproperty_add_size').val('50');
		$('#show_str3').text('');
	}else if(newvalue=='2'){
		$('#classproperty_add_size').val('0');
		$('#show_str3').text('数值类型属性不需要指定长度！');
	}else if(newvalue=='3'){
		$('#classproperty_add_size').val('0');
		$('#show_str3').text('百分比类型属性不需要指定长度！');
	}else if(newvalue=='4'){
		$('#classproperty_add_size').val('0');
		$('#show_str3').text('图片类型属性不需要指定长度！');
	}else{
		$('#show_str3').text('');
	}
}

var rowDataForData2;
/**
 * data2的行选中事件函数
 * @param rowIndex
 * @param rowData
 */
function initData2Params(rowIndex,rowData){
	rowDataForData2 = rowData;
}
/**
 * 子类型属性的删除操作函数
 */
function delete_classproperty(){
	if(rowDataForData2!=undefined&&rowDataForData2!=null){
		$.messager.confirm("确认","确认删除ID为"+rowDataForData2.classproperty_id+"的子类型属性？",function (r){
	        if (r){
	        	var fmdata = {};
	        	fmdata["sn_for_deleteclassproperty"]=rowDataForData2.classproperty_id;
	        	$.ajax({
	        		url : delete_url2,
	        		type : "POST",
	        		cache : false,
	        		data : fmdata,
	        		success : function(result) {
	        			var result = eval("(" + result + ")");
	        			if (result.success) {
	        				rowDataForData2=null;
	        				$('#data2').datagrid('reload');
	        				$.messager.show({title: "成功",msg: result.msg});
	        			} else {
	        				rowDataForData2=null;
	        				$.messager.show({title: "失败",msg: result.msg});
	        			}
	        		}
	        	});
	        }else{
	        	return;
	        }
	    });
	}else{
		$.messager.show({title: "提示",msg: '进行子类型属性删除操作前，请先选中需要进行删除的子类型属性！'});
	}
}
/**
 * 打开子类型属性的编辑窗口
 */
function openClassPropertyEditDlg(){
	if(rowDataForData2!=undefined&&rowDataForData2!=null){
		$('#classproperty_edit_sn').val(rowDataForData2.classproperty_id);
		$('#classproperty_edit_name').val(rowDataForData2.classproperty_name);
		$('#classproperty_edit_status').combobox('setValue',rowDataForData2.classproperty_status);
		$('#classproperty_edit_status').combobox('setText',rowDataForData2.classproperty_status_str);
		$('#classproperty_edit_dlg').dialog('open');
	}else{
		$.messager.show({title: "提示",msg: '进行子类型属性修改操作前，请先选中需要进行修改的子类型属性！'});
	}
}
/**
 * 子类型属性的编辑窗口的表单提交函数
 */
function submit_classproperty_edit_fm(){
	var url3 = 'map_markerclassmaintance_editclassproperty.action';
	$.messager.confirm("确认","确认修改ID为"+$('#classproperty_edit_sn').val()+"的子类型属性？",function (r){
        if (r){
        	var fmdata = {};
        	fmdata["sn_for_editclassproperty"]=$('#classproperty_edit_sn').val();
        	fmdata["name_for_editclassproperty"]=$('#classproperty_edit_name').val();
        	fmdata["status_for_editclassproperty"]=$('#classproperty_edit_status').combobox('getValue');
        	$.ajax({
        		url : url3,
        		type : "POST",
        		cache : false,
        		data : fmdata,
        		success : function(result) {
        			var result = eval("(" + result + ")");
        			if (result.success) {
        				rowDataForData2=null;
        				$('#data2').datagrid('reload');
        				$('#classproperty_edit_dlg').dialog('close');
        				$.messager.show({title: "成功",msg: result.msg});
        			} else {
        				rowDataForData2=null;
        				$.messager.show({title: "失败",msg: result.msg});
        			}
        		}
        	});
        }else{
        	return;
        }
    });
}
