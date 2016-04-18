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
		 $(document).ready(function(){
	     loadBrchTree(".brchTree",root); //@see /weblib/js/easyui-common.js
	     loadBrchTree("#brchTreeMode",root,"all");
	     loadBrchTree("#brchClick",root,"",clickTwo);
	     });
	     
	     function clickOne(node){
	       alert("one==>"+node.text);
	     }
	      function clickTwo(node){
	       alert("two==>"+node.text);
	     }
		</script>

	</head>

	<body>
		<xl:wrap title="树控件/demo/control/demoUITree.jsp">
		<fieldset>
              <legend>异步机构树 </legend>
		<div class="col3">
		  <div class="brchTree"></div>
		</div>
		<div class="col3">
		  <div id="brchClick"></div>
		</div>
		<div class="col3">
		  <div id="brchTreeMode"></div>
		</div>
		</fieldset>	
		</xl:wrap>
	</body>
</html>
