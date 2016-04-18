/**
 * @authod Mr.GaoFei
 */

var checked_arr = [];
var show_str = "";
function setMarkers(){
	checked_arr = new Array();
	checked_arr = $('#dataTab').datagrid('getSelections');
	if(checked_arr==null||checked_arr==undefined||checked_arr.length==0){
		$.messager.show({title: "��ʾ",msg: "��ѡ������һ���ٽ��������������õı�ע��"});
		return;
	}
	show_str = "";
	for(var i=0;i<checked_arr.length;i++){
		show_str += checked_arr[i].sn + ",";
	}
	//console.log('׼������ɾ������SN:'+show_str+'   �����ͣ�'+$('#marker_class').combobox('getValue')+'   �����ͣ�'+$('#sn').combobox('getValue'));
	
	if($('#marker_class').combobox('getValue')=='1'){
		var ss1 = $('#sn').combobox('getText')+'(��ţ�'+show_str.substring(0, show_str.length-1)+')';
		$("#show_str1").text(ss1);
		getMyIconHtml();
		$('#addMarkers_point_dlg').dialog('open');
	}
	else if($('#marker_class').combobox('getValue')=='2'){
		//console.log('�ߵ������������ã�');
		$('#addMarkers_line_dlg').dialog('open');
		$('#linecolorpicker').farbtastic('#select_my_line_color');
	}
	else if($('#marker_class').combobox('getValue')=='3'){
		//console.log('��������������ã�');
		$('#addMarkers_area_dlg').dialog('open');
		$('#areacolorpicker').farbtastic('#select_my_area_color');
	}
}

function area_color_confirm(){
	var a_color = $("#select_my_area_color").val();
	var a_strokeOpacity = $("#area_strokeOpacity").val();
	var a_strokeWeight = $("#area_strokeWeight").val();
	if(isNaN(a_strokeOpacity)){
		a_strokeOpacity=1.0;
	}else{
		if(a_strokeOpacity>1.0){
			a_strokeOpacity=1.0;
		}
	}
	if(isNaN(a_strokeWeight)){
		a_strokeWeight=2;
	}else{
		if(a_strokeWeight<2){
			a_strokeWeight=2;
		}
	}
	var h_area_color = a_color+"||"+a_strokeOpacity+"||"+a_strokeWeight;
	$('#area_markers').val(h_area_color);
	var ss = "ȷ�Ͻ�"+$('#sn').combobox('getText')+'(��ţ�'+show_str.substring(0, show_str.length-1)+')'+"�ı�ʶ����Ϊ��ɫ"+a_color+" ͸����"+a_strokeOpacity+" ��ϸ��"+a_strokeWeight+"��";
	$('#show_str6').html(ss);
	$('#confirm_area_dlg').dialog('open');
}

function submitAreaMarker(){
	var mainclass_sn = $('#marker_class').combobox('getValue');
	var childclass_sn = $('#sn').combobox('getValue');
	var area_marker = $('#area_markers').val();
	//console.log('������������޸ĵ�������SN��'+mainclass_sn+" ������SN��"+childclass_sn+"  ѡ���е�SN��"+show_str+"  ͼƬURL��"+area_marker);
	
	var url="map_echopath_setAreaMarkers.action";
	var json = {};
	json["a_mainclass_sn"] = mainclass_sn;
	json["a_childclass_sn"] = childclass_sn;
	json["a_area_marker"] = area_marker;
	json["a_selected_sns"] = show_str;
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				if(msg.if_success){
					$.messager.show({title: "�ɹ�",msg: msg.msg});
					$('#addMarkers_area_dlg').dialog('close');
					$('#confirm_area_dlg').dialog('close');
					$("#dataTab").datagrid("reload");
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
					$('#confirm_area_dlg').dialog('close');
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function line_color_confirm(){
	var l_color = $("#select_my_line_color").val();
	var l_strokeOpacity = $("#line_strokeOpacity").val();
	var l_strokeWeight = $("#line_strokeWeight").val();
	if(isNaN(l_strokeOpacity)){
		l_strokeOpacity=1.0;
	}else{
		if(l_strokeOpacity>1.0){
			l_strokeOpacity=1.0;
		}
	}
	if(isNaN(l_strokeWeight)){
		l_strokeWeight=2;
	}else{
		if(l_strokeWeight<2){
			l_strokeWeight=2;
		}
	}
	
	var h_line_color = l_color+"||"+l_strokeOpacity+"||"+l_strokeWeight;
	$('#line_markers').val(h_line_color);
	var ss = "ȷ�Ͻ�"+$('#sn').combobox('getText')+'(��ţ�'+show_str.substring(0, show_str.length-1)+')'+"�ı�ʶ����Ϊ��ɫ"+l_color+" ͸����"+l_strokeOpacity+" ��ϸ��"+l_strokeWeight+"��";
	$('#show_str5').html(ss);
	$('#confirm_line_dlg').dialog('open');
	
}

function submitLineMarker(){
	var mainclass_sn = $('#marker_class').combobox('getValue');
	var childclass_sn = $('#sn').combobox('getValue');
	var line_marker = $('#line_markers').val();
	//console.log('���������ߵ��޸ĵ�������SN��'+mainclass_sn+" ������SN��"+childclass_sn+"  ѡ���е�SN��"+show_str+"  ͼƬURL��"+line_marker);
	
	var url="map_echopath_setLineMarkers.action";
	var json = {};
	json["l_mainclass_sn"] = mainclass_sn;
	json["l_childclass_sn"] = childclass_sn;
	json["l_line_marker"] = line_marker;
	json["l_selected_sns"] = show_str;
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				if(msg.if_success){
					$.messager.show({title: "�ɹ�",msg: msg.msg});
					$('#addMarkers_line_dlg').dialog('close');
					$('#confirm_line_dlg').dialog('close');
					$("#dataTab").datagrid("reload");
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
					$('#confirm_line_dlg').dialog('close');
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function getMyIconHtml(){
	var url="user_getMyIconSelectHtml.action";
	var json = {};
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				if(msg.if_success){
					$("#show_str2").html(msg.returnHtml);
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function submitUserIcon(){
	var mainclass_sn = $('#marker_class').combobox('getValue');
	var childclass_sn = $('#sn').combobox('getValue');
	var img_url = $('#hidden_user_icon').val();
	//console.log('���������޸ĵ�������SN��'+mainclass_sn+" ������SN��"+childclass_sn+"  ѡ���е�SN��"+show_str+"  ͼƬURL��"+img_url);
	
	var url="map_echopath_seticons.action";
	var json = {};
	json["u_mainclass_sn"] = mainclass_sn;
	json["u_childclass_sn"] = childclass_sn;
	json["u_img_url"] = img_url;
	json["u_selected_sns"] = show_str;
	$.ajax({
		type : "POST",
		cache : false,
		async:true,
		url : url,
		dataType : "json",
		data : json,
		success : function(msg) {
			try {
				if(msg.if_success){
					$.messager.show({title: "�ɹ�",msg: msg.msg});
					$('#addMarkers_point_dlg').dialog('close');
					$('#confirm_usericon_dlg').dialog('close');
					$("#dataTab").datagrid("reload");
				}else{
					$.messager.show({title: "ʧ��",msg: msg.msg});
					$('#confirm_usericon_dlg').dialog('close');
					return;
				}
			}
			catch (exception) {
				alert(exception);
			}
		},
		error : function(msg) {
			alert('error!!');
		}
	});
}

function setMarkerIconToMySelect(this_src){
	//this.src----http://localhost:7001/mapfiles/markers/0blue-ball.png 
	var a_1=[];
	a_1 = this_src.split("/mapfiles/");
	this_src = /mapfiles/+a_1[1];
	//console.log("this_src:"+this_src);
	$('#hidden_user_icon').val(this_src);
	var img_html = "<img src='"+this_src+"' />";
	$("#show_str3").html(img_html);
}

function submit_addMarkers_point_fm(){
	var ss = "ȷ�Ͻ�"+$('#sn').combobox('getText')+'(��ţ�'+show_str.substring(0, show_str.length-1)+')'+"�ı�ʶ����Ϊ"+"<img src='"+$('#hidden_user_icon').val()+"' />��";
	$('#show_str4').html(ss);
	$('#confirm_usericon_dlg').dialog('open');
}