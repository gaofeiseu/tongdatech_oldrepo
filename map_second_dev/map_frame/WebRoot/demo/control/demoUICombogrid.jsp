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
		<xl:wrap title="�����ؼ� /demo/control/demoUICombogrid.jsp">
		
			<fieldset>
              <legend>������� </legend>
			<div class="form_item col3"><!--col2 3�в���  -->
				<label>����ѡ��:</label>
				<input type="text" name="brch_no" class="BrchCombo" id="brch" value="${session.userInfo.brch_no}"
				data-options="required:true"
				/>
		    </div>
		    <div class="form_item col3">
				<label>����ѡ��:</label>
				<input type="text" name="brch_no" class="BrchCombo" value="98413"
				data-options="onChange:function(newValue, oldValue){alert(newValue+'->'+oldValue);}"
				/>
			</div>
            <div class="form_item col3"><!--col2 3�в���  -->
				<label>��Ĭ��ֵ:</label>
				<input type="text" name="brch_no" id="BrchCombo2" text="Ĭ��ֵ" value="2"/>
				<!--��������id text������ -->
		    </div>
		    
			</fieldset>
			
			<fieldset>
				<legend>������� </legend>
				<div class="form_item col3">
				<label>����ѡ��:</label>
				<input type="text" name="area_no" class="AreaCombo" value="${session.userInfo.area_name}"/>
				</div>
			</fieldset>
			
			<fieldset>
				<legend>�ͻ�������� </legend>
				<div class="form_item col3">
				<label>�ͻ�����ѡ��:</label>
				<input type="text" name="mgrid" class="MgrCombo" />
				</div>
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
