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
		<xl:wrap title="�����ؼ� /demo/control/demoUICombo.jsp">
		
			<fieldset>
              <legend>������� </legend>
			<div class="form_item col3"><!--col2 3�в���  -->
				<label>����ѡ��:</label>
				<input type="text" name="brch_no" class="BrchCombo" value="${session.userInfo.brch_no}"
				data-options="required : true" />
		    </div>
		    <div class="form_item col3">
				<label>��չ����</label>
				<input type="text" name="brch_no" id="BrchCombo2" value="98413" data-options="required : true"/>
			</div>
            <div class="form_item col3"><!--col2 3�в���  -->
				<label>��Ĭ��ֵ:</label>
				<input type="text" name="brch_no" class="BrchCombo" text="Ĭ��ֵ" value="2"/>
		    </div>
		    
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
