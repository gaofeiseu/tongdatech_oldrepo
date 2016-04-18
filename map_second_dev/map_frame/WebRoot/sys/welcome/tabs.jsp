<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>

<link rel="stylesheet" type="text/css" href="/weblib/css/main.css" />

<s:property escape="false" value="@com.tongdatech.sys.util.EasyUIUtil@Reference()"/>
<style type="text/css">
.tabs-header, .tabs-tool {
    background-color: #EEF2FB;
}
</style>

<script type="text/javascript">
var main_url='${main_url}';
var main_title='${main_title}';

$(document).ready(function(){
  $("#tabs").tabs({
    border:false,
    fit:true,
    onSelect:function(title){
        //alert(title+' is selected');
    }
  });
 if(main_url!=''&&main_title!='')addtab(main_title,main_url);
});

function doOpen(menu){
  addtab(menu.menu_name,menu.menu_url);
}

function addtab(title,tab_url){
var len=$("#tabs").tabs("tabs").length;
var limit=20;
if(len>limit){
  alert("同时最多只能打开"+limit+"个标签,为了确保浏览器流畅运行请先关闭其他标签");
  return;
}
$("#tabs").tabs("add",{
    title:title,
    closable:true

});
var pp = $("#tabs").tabs("getSelected");
//var tab = pp.panel("options").tab;
$(pp).html('<iframe scrolling="yes" frameborder="0"  src="'+tab_url+'" style="width:100%;height:100%;"></iframe>');

}

</script>
</head>

<body>

<div id="tabs">

</div>


</body>
</html>
