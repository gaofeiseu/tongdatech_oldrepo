function ljhx_reloadscreen(){
	if(phone_markerpath_array.length>0&&phone_markerpath_array!=null&&phone_markerpath_array!=undefined){
		for(var i in phone_markerpath_array){
			phone_markerpath_array[i].setMap(null);
		}
	}
}
var phone_markerpath_array=[];

function query_ljhx_data(){
	var st_time = '';
	var ed_time = '';
	var query_role_type = '';
	var query_loginname = '';
	var if_success = false;
	var iconurl="/mapfiles/markers/purple_point.png";
	
	var s_s=''; 
	$('input[name="ljhx_radiobutton"]:checked').each(function(){
		s_s+=$(this).val()+',';
	});
	if(s_s.length>0){
		s_s = s_s.substring(0,s_s.length-1);
	}
	if(s_s==''){
		alert('��û��ѡ���κ����ݣ�');
		return;
	}
	var arr_s_s = [];
	arr_s_s = s_s.split(',', 10);//����������ѡ���˸�checkbox��value
	for(var i = 0;i<arr_s_s.length;i++){
		if(arr_s_s[i]=='1'){
			st_time = $('#ljhx_query_timeST').datebox('getValue');//   7/6/2014 11:08:25
			ed_time = $('#ljhx_query_timeED').datebox('getValue');
			
			if(st_time==''||st_time==undefined||st_time==null||
					ed_time==''||ed_time==undefined||ed_time==null){
				alert('��ʱ���ѯʱ����ʼʱ��ͽ���ʱ�������Ϊ�գ�');
				return;
			}else{
				if(Number(st_time)>Number(ed_time)){
					alert('��ʼʱ�䲻������ڽ���ʱ�䣡');
					return;
				}else{
					if_success=true;
				}
			}
		}else if(arr_s_s[i]=='2'){
			query_role_type = $('#ljhx_selectbutton').combobox('getValue');
			//console.log('query_role_type----------->'+query_role_type);
			if(query_role_type==''||query_role_type==undefined||query_role_type==null){
				alert('��δѡ����Ҫ��ѯ�Ŀͻ����ͣ�');
				return;
			}
			if_success=true;
		}else if(arr_s_s[i]=='3'){
			query_loginname = $('#ljhx_loginname_query').val();
			if(query_loginname==''||query_loginname==undefined||query_loginname==null){
				alert('����¼����ѯʱ����ѯ�ĵ�¼������Ϊ�գ�');
				return;
			}else{
				if_success=true;
			}
		}else{
			
		}
	}

	if(if_success){
		var url="/map_echopath_query.action";
		var json = {};
		//׼�����͵���̨�������͵��������
		json["ljhx_query_type"]=s_s;//·�����ԵĲ�ѯ����
		
		for(var i = 0;i<arr_s_s.length;i++){
			if(arr_s_s[i]=='1'){
				json["ljhx_time_st"]=st_time;//ʱ���ѯ�Ŀ�ʼʱ��
				json["ljhx_time_ed"]=ed_time;//ʱ���ѯ�Ľ���ʱ��
			}else if(arr_s_s[i]=='2'){
				json["ljhx_query_role_type"] = query_role_type;//�������Ͳ�ѯ
			}else if(arr_s_s[i]=='3'){
				json["ljhx_query_loginname"] = query_loginname;
			}else{
				return;
			}
		}
		
		$.ajax({
			type : "POST",
			cache : false,
			async:false,
			url : url,
			dataType : "json",
			data : json,
			success : function(msg) {
				try {
					if(msg.if_success){
						var pathpoint=[];
						pathpoint=msg.list_map;
						var lat_arr = [];
						var lon_arr = [];
						for(var o = 0; o < pathpoint.length; o++){
							if(pathpoint[o].lat!=undefined&&pathpoint[o].lon!=undefined){
								lat_arr.push(pathpoint[o].lat);
								lon_arr.push(pathpoint[o].lon);
							}
							var phonepoint = new google.maps.LatLng(pathpoint[o].lat,pathpoint[o].lon);
							//console.log('user_icon----->'+pathpoint[o].user_icon);
							//console.log('lat----->'+pathpoint[o].lat+'lon----->'+pathpoint[o].lon);
							//console.log('123');
							//console.log('��������������ַ��'+pathpoint[o].address);
							//console.log('�������������û�����'+pathpoint[o].user_name);
							//console.log('������������ʱ�䣺'+pathpoint[o].record_time);
							var s_title = '��ַ��'+pathpoint[o].address+'\n'+'�û�����'+pathpoint[o].user_name+'\n'+'ʱ�䣺'+trimTimeFunc(pathpoint[o].record_time);
							//(pathpoint[o].record_time==''||pathpoint[o].record_time==undefined||pathpoint[o].record_time==null)?(''):(trimTimeFunc(pathpoint[o].record_time))
							//console.log('·������ title��'+s_title);
							var marker = new google.maps.Marker({
						        position: phonepoint,
						        map: map,
						        draggable:false,
						        icon:(pathpoint[o].user_icon==''||pathpoint[o].user_icon==null||pathpoint[o].user_icon==undefined)?(iconurl):(pathpoint[o].user_icon),
						        title:s_title
							});
							//console.log('321');
							phone_markerpath_array.push(marker);
						}
						//console.log('lat:'+my_lat+' lon:'+my_lon);
						var ii = findRightPoint(my_lat,my_lon,lat_arr,lon_arr);
						var myLatlng = new google.maps.LatLng(pathpoint[ii].lat,pathpoint[ii].lon);
						map.setCenter(myLatlng);
						$("#echo_query_msg").text("��ѯ�ɹ�������"+pathpoint.length+"��·���㡣");
					}else{
						alert(msg.msg);
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
}

function findRightPoint(o_lat,o_lon,lat_arr,lon_arr){
	var radio_length_arr = [];
	for ( var i = 0; i < lat_arr.length; i++) {
		radio_length_arr.push((lat_arr[i]-o_lat)*(lat_arr[i]-o_lat)+(lon_arr[i]-o_lon)*(lon_arr[i]-o_lon));
	}
	var ii = findMinRadio(radio_length_arr);
	return ii;
}

function findMinRadio(radio_arr){
	Array.prototype.min = function(){
		return Math.min.apply({},this);
	};
	var min_n = radio_arr.min();
	var n = 0;
	for ( var i = 0; i < radio_arr.length; i++) {
		if(radio_arr[i]==min_n){
			n = i;
			//console.log(radio_arr[i]+"=="+min_n);
		}else if(radio_arr[i]>min_n){
			//console.log(radio_arr[i]+">"+min_n);
		}else{
			//console.log(radio_arr[i]+"<"+min_n);
		}
	}
	return n;
}

function trimTimeFunc(mark_time){
	//20140728172412
	if(mark_time==''||mark_time==undefined||mark_time==null){
		return "��";
	}else{
		try {
			return mark_time.substring(0,4)+'��'+mark_time.substring(4,6)+'��'+mark_time.substring(6,8)+'��'+mark_time.substring(8,10)+'ʱ'+mark_time.substring(10,12)+'��'+mark_time.substring(12,14)+'��';
		} catch (e) {
			return "��";
		}
	}
}
