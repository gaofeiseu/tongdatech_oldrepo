<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" type="text/css" href="/weblib/css/main.css" >
<link rel="stylesheet" type="text/css" href="/weblib/js/jquery-easyui-1.3.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="/weblib/js/jquery-easyui-1.3.5/themes/icon.css">
<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CNxl.js"></script>
<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/easyloader.js"></script>
<script type="text/javascript" src="/weblib/js/easyui-common.js"></script>
<script type="text/javascript" src="/weblib/js/validatebox_extend_rules.js"></script>



	</head>
	
	<script type="text/javascript">
     var jsonData=[{"id":"col1","text":"列1"},{"id":"col2","text":"列2"},{"id":"col3","text":"列3"},{"id":"col4","text":"列4"}];
	
	function colFilter(data){
	   data=data.concat();  
	   //var name=$(this).attr("comboname");
	   var name=$(this).val();
	   if(name!=undefined){
	    data.push({"id":name,"text":name});
	   }
	   return data;
	}
	$.extend($.fn.validatebox.defaults.rules, { 
    	unique: {  
            validator: function(value){
             var obj=$(this).parent("span").prev("input");
             var rs=true;
             
             if($(obj).attr("col")==value) 
             	return true;
             var uqs=$(".unique");
             for(i=0;i<uqs.length;i++){
                //var unique=$(uqs[x]).next("span").find(".combo-text")[0];
                var unique=uqs[i];
                if(obj[0]!=unique){
                   var val=$(unique).next("span").find(".combo-text").val();
                   if(val==value){
                    rs=false;
                    break;
                   }
                }          
              }
             return rs;
            },  
            message: "列选择重复"
        }
     });
</script>
		
	<body>
<table class="comWrapTab" height="100%">
<tr height="29px">
<td id="left_head"></td>
<td id="mid_head">
<ul>
<li id="l_title"></li>
<li id="m_title">测试导入</li>
<li id="r_title"></li>
</ul>
</td>
<td id="right_head"></td>
</tr>
<tr>
<td id="left_body"></td>
<td id="mid_body">
<div id="WrapDiv">


            
			<br>
		<table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="header">
	            <td>行号</td>
	            <td>
	            <input name="0"  value="A"  id="0"  class="easyui-combobox unique"
	            data-options="valueField:'id',textField:'text',data:JSON.parse(JSON.stringify(jsonData)),loadFilter:colFilter,validType:'unique'"/></td>
	         	<td>
	            <input name="1"  value="B"  id="1"  class="easyui-combobox unique"
	            data-options="valueField:'id',textField:'text',data:JSON.parse(JSON.stringify(jsonData)),loadFilter:colFilter,validType:'unique'"/></td>
	            <td>
	            <input name="2"  value="C"  id="2" class="easyui-combobox unique"
	            data-options="valueField:'id',textField:'text',data:JSON.parse(JSON.stringify(jsonData)),loadFilter:colFilter,validType:'unique'"/></td>
	         </tr>
	         
	       
</table>



	</body>
</html>

