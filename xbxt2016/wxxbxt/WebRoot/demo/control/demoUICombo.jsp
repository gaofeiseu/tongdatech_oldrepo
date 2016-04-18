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
	     loadBrchCombo(".BrchCombo",root);
	     loadBrchCombo("#BrchCombo2",root,"98413");
	     });
		</script>

	</head>

	<body>
		<xl:wrap title="下拉控件 /demo/control/demoUICombo.jsp">
		
			<fieldset>
              <legend>机构相关 </legend>
			<div class="form_item col3"><!--col2 3列布局  -->
				<label>机构选择:</label>
				<input type="text" name="brch_no" class="BrchCombo" value="${session.userInfo.brch_no}"
				data-options="required : true" />
		    </div>
		    <div class="form_item col3">
				<label>树展开至</label>
				<input type="text" name="brch_no" id="BrchCombo2" value="98413" data-options="required : true"/>
			</div>
            <div class="form_item col3"><!--col2 3列布局  -->
				<label>带默认值:</label>
				<input type="text" name="brch_no" class="BrchCombo" text="默认值" value="2"/>
		    </div>
		    
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
