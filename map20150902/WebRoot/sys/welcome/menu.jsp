<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>


<script src="/weblib/js/menu.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/weblib/css/main.css" />
<xl:resource></xl:resource>
<%-- <s:property escape="false" value="@com.tongdatech.sys.util.EasyUIUtil@Reference()"/> --%>

<style>

body {
	font-size:14px ;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;

}
#container {
	width: 182px;

}

IMG{
   width:182px ;
   height:5px;
   
}
.type {
	cursor: pointer;
	display: block;
	width: 152px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background-image: url(/weblib/img/menu/menu_bgs.gif);
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: left;
	margin: 0px;
	padding-left: 30px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.tree-node{
    padding:0  0 0 10px;
    line-height: 24px;
	color: #333333;
	background-image: url(/weblib/img/menu/menu_bg1.gif);
	background-repeat: no-repeat;
	height: 24px;

}
.tree-node-hover {
  background: #307DCA;
  color: white;/**#009641;*/
}
.tree-node-selected {
  background-image: url(/weblib/img/menu/menu_bg1.gif);
  color: #307DCA;
}
</style>
<script type="text/javascript">
var isTabs='${application.isTabs}';
function open(menu){
$.post("menu_menuLog",menu);
if(isTabs=="true"){
  self.parent.frames["main"].doOpen(menu);
 }else{
  self.parent.frames["main"].location.href=menu.menu_url;
 }
}
</script>
</head>
<body>

<div id="container">
<s:iterator value='menuList' id="menu" status="st">  

    <div class="type"><s:property value="#menu.menu_name"></s:property></div>
        <div class="content">
        <div><img src="/weblib/img/menu/menu_topline.gif" height="5px" /></div>
        
        <s:set value="#menu.menu_id" name="key1"/>
        <ul class="easyui-tree"  data-options='
        data:<s:property escape="false"  value="menuMaps[#key1]"/>,
        animate:true,
        onClick: function(node){
          if(node.obj.menu_url==undefined){
            $(this).tree("toggle", node.target);
          }else{
            open(node.obj);
          }
	    }
        '></ul>

        </div>
      

</s:iterator>
</div>
        <script type="text/javascript">
		var contents = document.getElementsByClassName("content");
		var toggles = document.getElementsByClassName("type");
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		//myAccordion.showThisHideOpen(contents[1]);
	</script>

</body>
</html>
