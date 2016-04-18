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
		<script type="text/javascript" src="/xiechu/xiechu.js"></script>
		<script type="text/javascript">
		 var root="${session.userInfo.brch_no}";
		 $(document).ready(function(){
		 loadBrchSearch(".BrchCombo");
	     loadBrchSearch("#BrchCombo2");
	     loadAreaSearch(".AreaCombo");
	     loadMgrSearch(".MgrCombo",$(".BrchCombo").val());
		 });
	     
		</script>

	</head>

	<body>
		<xl:wrap title="搜索控件 /demo/control/demoUICombogrid.jsp">
		
			<fieldset>
              <legend>机构相关 </legend>
			<div class="form_item col3"><!--col2 3列布局  -->
				<label>机构选择:</label>
				<input type="text" name="brch_no" class="BrchCombo" id="brch" value="${session.userInfo.brch_no}"
				data-options="required:true"
				/>
		    </div>
		    <div class="form_item col3">
				<label>机构选择:</label>
				<input type="text" name="brch_no" class="BrchCombo" value="98413"
				data-options="onChange:function(newValue, oldValue){alert(newValue+'->'+oldValue);}"
				/>
			</div>
            <div class="form_item col3"><!--col2 3列布局  -->
				<label>带默认值:</label>
				<input type="text" name="brch_no" id="BrchCombo2" text="默认值" value="2"/>
				<!--必须设置id text才有用 -->
		    </div>
		    
			</fieldset>
			
			<fieldset>
				<legend>地区相关 </legend>
				<div class="form_item col3">
				<label>地区选择:</label>
				<input type="text" name="area_no" class="AreaCombo" value="${session.userInfo.area_name}"/>
				</div>
			</fieldset>
			
			<fieldset>
				<legend>客户经理相关 </legend>
				<div class="form_item col3">
				<label>客户经理选择:</label>
				<input type="text" name="mgrid" class="MgrCombo" />
				</div>
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
