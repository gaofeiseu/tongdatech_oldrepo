<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
<%@ page import="com.tongdatech.sys.demo.bean.*"%>
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
		<xl:wrap title="菜单角色表 /demo/role/menurole.jsp">
			<br>
			<div style="text-align: center;">
			<br>
	       <table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="title"><td colspan="<s:property value="pageui.colInfo.size+2"/>">菜单角色表</td></tr>
	         <tr class="header">
	         <td>菜单</td>
	         <td>菜单层级路径</td>
	         <s:iterator value='pageui.colInfo' id="cols" status="lis"> 
			 <td><s:property value="#cols['role_name']"/></td>  
			 </s:iterator> 
	       </tr>
	       
	       <s:iterator value='pageui.rows' id="obj" status="st">  
	       <tr class="body">
	            <td style="text-align: left;">${obj['菜单']}</td>
				<td style="text-align: left;">${obj['菜单层级路径']}</td>               
                <s:iterator value='pageui.colInfo' id="cols" status="lis"> 
				<td><s:property value="#obj[#cols['role_name']]"/></td>
				</s:iterator> 
           </tr>
           </s:iterator>
           
           <tr ><td colspan="<s:property value="pageui.colInfo.size+2"/>"> <xl:pagination value="pagination" buttons="back,excel" excel_url="menurole_excel"></xl:pagination></td></tr>
           </table>
	     </div>
	     
	       <div>
      <!-- 引入excel 导出选项  -->
      <jsp:include page="/sys/UIExcel.jsp" >
      </jsp:include>
      </div>
      
		</xl:wrap>
	</body>
	
	
	
	
</html>
