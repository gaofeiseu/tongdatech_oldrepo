<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>

		<meta http-equiv=Content-Type content=text/html;charset=gbk>
		<xl:resource></xl:resource>

	</head>
	<body>

		<xl:wrap title="提示">

			<div id="msg">
				<h1>
					<s:if test="jspMsg.success==true">成&nbsp;功</s:if>
					<s:else>失&nbsp;败</s:else>
				</h1>
				<p>
					<s:property value='jspMsg.msg' />
				</p>

                <br>
     
				<s:iterator value='jspMsg.btns' id="us">
     
    			&nbsp;&nbsp;
				<a href="${us.url}"  class="easyui-linkbutton"  
				data-options="iconCls:'${us.iconCls}'" >
		
				<s:property value='#us.name' /> </a>
				&nbsp;&nbsp;
    
    			</s:iterator>

			</div>
		</xl:wrap>

	</body>
</html>
