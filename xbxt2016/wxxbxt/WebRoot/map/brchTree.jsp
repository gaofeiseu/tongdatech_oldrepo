<%@ page language="java"  contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="com.tongdatech.sys.bean.Brch" %> 
<%@ page language="java" import="java.util.*" %> 
<html>
<head>

<title>机构选择</title>
<META HTTP-EQUIV="Expires" CONTENT="0">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
 
		<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>
 
 	<script type="text/javascript" src="/map/mapjs/map-query-1.0.js"></script>
		<script type="text/javascript" src="/map/mapjs/column-query.js"></script>
		
	 	<link rel="stylesheet" type="text/css" href="/map/css/css.css">
<script type="text/javascript">

function ishide(brchid){
	if($("[temp="+brchid+"]").is(":hidden")){
		if($("[temp="+brchid+"]").size()>0){
			$("[temp="+brchid+"]").show();
		}
	}else{
		if($("[temp="+brchid+"]").size()>0){
			var str1=$("[temp="+brchid+"]");
			str1.each(function(){
				if($("[temp="+$(this).attr("temp2")+"]").size()>0){
					var str2=$("[temp="+$(this).attr("temp2")+"]");
					str2.each(function(){
						if($("[temp="+$(this).attr("temp2")+"]").size()>0){
							var str3=$("[temp="+$(this).attr("temp2")+"]")
							str3.each(function(){
								$("[temp="+$(this).attr("temp2")+"]").hide();
							});
							str3.hide();
						}
					});
					str2.hide();
				}
			});
			str1.hide();
		}
	}
}
function isshow(obj,status){
	//1是展开状态0非展开状态
	if(status=='0'){
		obj.show();
		
	}else{
		obj.hide();
		
	}
}
function upd(){
	var roleNbr = '${role.roleNbr}';
	var brchidStr = checkStr("brchOid");
	
	if(brchidStr == ""){
		alert("请选择菜单");
		return;
	}
	
	var param = "&roleNbr="+roleNbr+"&brchidStr="+brchidStr;
	$.ajax({
		type: "post",
		url: "RoleAction.action?method=updatebrch",
		data: param,
		success: function(msg){
			if(msg=="操作成功"){
				alert(msg);
				window.close();
			}else{
				alert(msg);
			}
		},
		error: function(msg){
			alert("系统异常!");
		}
	});
}

function checkStr(inputName){
	var selected = document.getElementsByName(inputName);
	var str = "";
	if(selected.length>0){
		str = "'";
		for(var i=0;i<selected.length;i++){
			if(selected[i].checked==true){
				str += selected[i].value + "','";
			}
		}
		str = str.substring(0,str.length-2);
	}
	return str;
}

function check(id, lev,ownbrchid,this_name){
	var length = id.length;
	var allbrch = document.getElementsByName(this_name);//'brchOid'
	var thisObj = document.getElementById(id);
	var brch;
	var j=0;
	var num=0;
	if(thisObj.checked){
		for(var i = 0;i<allbrch.length;i++){
			 
			if(allbrch[i].attributes["value2"].nodeValue==id ){
				
				allbrch[i].checked = true;
				num++;
					for(var s=i;s<allbrch.length;s++){
						if(allbrch[i].value==allbrch[s].attributes["value2"].nodeValue){
							allbrch[s].checked=true;
							num++;
							for(var t=s;t<allbrch.length;t++){
								if(allbrch[s].value==allbrch[t].attributes["value2"].nodeValue){
									allbrch[t].checked=true;
									num++;
								}
							}
						}
					}
			}
		}
	 
	}else{
		for(var i = 0;i<allbrch.length;i++){
			if(allbrch[i].attributes["value2"].nodeValue==id){
				allbrch[i].checked = false;
					for(var s=i;s<allbrch.length;s++){
						if(allbrch[i].value==allbrch[s].attributes["value2"].nodeValue){
							allbrch[s].checked=false;
							for(var t=s;t<allbrch.length;t++){
								if(allbrch[s].value==allbrch[t].attributes["value2"].nodeValue){
									allbrch[t].checked=false;
								}
							}
						}
					}
			}
		}
	}

	if(num>440)
	{
		alert("查看机构选择过多");
		$('#userForm').reset();
		return;
	}
	
}
function sub(_type)
{
 	var obj_name =$("#obj_name").val();
 	var value_obj="childclass_"+obj_name+"_brch";
 	var name_obj="childclass_"+obj_name+"_brch_name";
 	var type_obj = "childclass_"+obj_name+"_brch_type";
 	var no_val="";
 	var name_val="";
 	var ck_no_val=[];
 	var ck_name_val=[];
 	var allbrch = document.getElementsByName('brchOid');//查看的部门
 	var allbrchQ = document.getElementsByName('brchOidQ');//被查看的部门
 	var chakan_num = 0;
 	var beichakan_num = 0;
 	for(var i=0;i<allbrch.length;i++){
 		if(allbrch[i].checked==true){
 			ck_no_val.push(allbrch[i].value);
 		 	ck_name_val.push(allbrch[i].attributes["value3"].nodeValue);
 			chakan_num++;
 		}
 	}
 	/* if(chakan_num>1){
 		alert('查看部门只能选择一个！');
 		return;
 	} */
 	for(var i = 0;i<allbrchQ.length;i++){
		if(allbrchQ[i].checked == true)
		{
			beichakan_num++;
		}
	}
 	if(chakan_num==0&&beichakan_num!=0){
 		alert('请选择一个查看部门，然后再进行提交动作！');
 		return;
 	}
 	if(chakan_num!=0&&beichakan_num==0){
 		alert('请选择一个被查看部门，然后再进行提交动作！');
 		return;
 	}
 	for(var i = 0;i<allbrchQ.length;i++){
		if((allbrchQ[i].checked == true)&&(chakan_num!=0))
		{
			for(var j=0;j<ck_no_val.length;j++){
				if(ck_no_val[j]!=allbrchQ[i].value.substring(0,allbrchQ[i].value.length-1)){
					if(no_val!="")
					{
						no_val+=","+ck_no_val[j]+"->"+allbrchQ[i].value.substring(0,allbrchQ[i].value.length-1);
						name_val+=","+ck_name_val[j]+"->"+allbrchQ[i].attributes["value3"].nodeValue;
					}
					else
					{
						no_val+=ck_no_val[j]+"->"+allbrchQ[i].value.substring(0,allbrchQ[i].value.length-1);
						name_val+=ck_name_val[j]+"->"+allbrchQ[i].attributes["value3"].nodeValue;
					}
				}
			}
		}
	}
 	
	window.opener.document.getElementById(value_obj).value=no_val;
	window.opener.document.getElementById(name_obj).value=name_val;
	if(_type=='1'){
		//覆盖
		window.opener.document.getElementById(type_obj).value='1';
	}else if(_type=='0'){
		//追加
		window.opener.document.getElementById(type_obj).value='0';
	}
    window.close();
}
</script>
</head>
<body class="body">
<form id="userForm" name="userForm" action="" method="post">
	<div style="height:500px;overflow:auto;overflow-x:hidden"><input type="hidden">
	<table width="880">
	<tbody>
	<tr>
	<td width="10"></td>
	<td width="400">
	<table width="100%" border="0" cellpadding="4" cellspacing="1" class="condit" align="center">
	<caption align="top">查看的部门</caption>
	<th colspan="8"> <strong></strong></th>
		<!-- count 从1开始   index从0开始 -->
							<%
							HttpSession sessions = request.getSession();
							List<Brch> resultList=new ArrayList<Brch>();
							String obj_name="";
							if(sessions!=null){
								resultList = (List<Brch>) sessions.getAttribute("brchTree");	
								obj_name=(String) sessions.getAttribute("obj_name");	
							}
							%>
						
							<%
							for(Brch b:resultList)
							{ 
								%>

			<tr temp="<%=b.getBrch_up()%>" temp2="<%=b.getBrch_no()%>"><td>
				<div align="left" style="font-size:12px;">
					<%=b.getBrch_mode()%>
					<input type="checkbox" name="brchOid" id="<%=b.getBrch_no()%>" 
					value="<%=b.getBrch_no()%>" value2="<%=b.getBrch_up()%>" value3="<%=b.getBrch_name()%>"  <%=b.getBrch_flag()%>
					onclick="check('<%=b.getBrch_no()%>','<%=b.getBrch_mode()%>','<%=b.getBrch_up()%>',this.name);">
					    &nbsp;<a href="javascript:ishide('<%=b.getBrch_no()%>');"><%=b.getBrch_name()%></a>
				</div>
			</td></tr>
	
      						<% 
							}
							%>
	</table>
	</td>
	<td width="30">--></td>
	<td width="400">
	<table width="100%" border="0" cellpadding="4" cellspacing="1" class="condit" align="center">
	<caption align="top">被查看的部门</caption>
	<th colspan="8"> <strong></strong></th>
		<!-- count 从1开始   index从0开始 -->
							<%
							for(Brch b:resultList)
							{ 
								%>

			<tr temp="<%=b.getBrch_up()%>Q" temp2="<%=b.getBrch_no()%>Q"><td>
				<div align="left" style="font-size:12px;">
					<%=b.getBrch_mode()%>
					<input type="checkbox" name="brchOidQ" id="<%=b.getBrch_no()%>Q" 
					value="<%=b.getBrch_no()%>Q" value2="<%=b.getBrch_up()%>Q" value3="<%=b.getBrch_name()%>"  <%=b.getBrch_flag()%>
					onclick="check('<%=b.getBrch_no()%>Q','<%=b.getBrch_mode()%>','<%=b.getBrch_up()%>Q',this.name);">
					    &nbsp;<a href="javascript:ishide('<%=b.getBrch_no()%>Q');"><%=b.getBrch_name()%></a>
				</div>
			</td></tr>
	
      						<% 
							}
							%>
	</table>
	</td>
	</tr>
	</tbody>
	</table>
	
	&nbsp;
	</div>
	<center>
	<input type="button" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="以覆盖方式修改" onClick="sub('1')" />
 	<input type="button" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="以追加方式修改" onClick="sub('0')" />
	<input type="button" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';"  value="关 闭" onClick="window.close();" />
	</center>
</form>
	<input type="hidden" id="obj_name" value="<%=obj_name%>">
</body>
</html>
