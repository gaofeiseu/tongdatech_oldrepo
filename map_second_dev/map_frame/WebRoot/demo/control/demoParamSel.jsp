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


	</head>

	<body>
		<xl:wrap title="选择控件  /demo/control/demoParamSel.jsp">
		
			<fieldset>
              <legend>从系统参数中生成的控件</legend>
			<div class="form_item col2"><!--col2 3列布局  -->
				<label>s:控件</label>
				<s:select list="#application.ParamSys.DEMO" listKey="key" listValue="value" value="1" theme="simple" style="width: 150px"></s:select>
				<br>
				<br>
				<span>
				&lt;s:select list=&quot;#application.ParamSys.DEMO&quot; listKey=&quot;key&quot;listValue=&quot;value&quot; value=&quot;1&quot; theme=&quot;simple&quot;style=&quot;width: 150px&quot;&gt;&lt;/s:select&gt;</span>
				
		    </div>
		    <div class="form_item col2">
				<label>easyUI控件</label>
				
				<input type="text" value="1"  class="easyui-combobox" style="width: 150px"
				 data-options="valueField:'value',textField:'text',data:<s:property default="[]" value="#application.ParamJson.DEMO"/>"/>
				 <br>
				 <br>
				 <span>&lt;input type=&quot;text&quot; value=&quot;1&quot;  class=&quot;easyui-combobox&quot; style=&quot;width: 150px&quot;
				 data-options=&quot;valueField:'value',textField:'text',data:&lt;s:property default=&quot;[]&quot; value=&quot;#application.ParamJson.DEMO&quot;/&gt; &quot;/&gt;
				 </span>
			</div>
            
		    
			</fieldset>
			
			
		</xl:wrap>
	</body>
</html>
