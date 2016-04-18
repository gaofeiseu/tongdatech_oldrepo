<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
<%@ page import="com.tongdatech.sys.demo.bean.*"%>
<%@ page import="com.tongdatech.sys.bean.*"%>
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
		<xl:wrap title="菜单地区表/demo/role/menuarea.jsp">
			<br>
			<div style="text-align: center;">
			<br>
	       <table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="title"><td colspan="<s:property value="pageui.colInfo.size+2"/>">菜单地区表 
	         <a href="/demo/menuarea_query.action">(${userInfo.area_name})
					</a></td></tr>
	         <tr class="header">
	         <td>菜单</td>
	         <td>菜单层级路径</td>
	         <s:iterator value='pageui.colInfo' id="cols" status="lis"> 
			 <td><a href="menuarea_test.action?name=${cols['area_name']}&&no=${cols['area_no']}"><s:property value="#cols['area_name']"/></a></td>  
			 </s:iterator> 
	       </tr>
	       
	       <s:iterator value='pageui.rows' id="obj" status="st">  
	       <tr class="body">
	            <td style="text-align: left;">${obj['menu_name']}</td>
				<td style="text-align: left;">${obj['menu_path']}</td>               
                <s:iterator value='pageui.colInfo' id="cols" status="lis"> 
				<td><s:property value="#obj[#cols['area_name']]"/></td>
				</s:iterator> 
           </tr>
           </s:iterator>
           
           <tr ><td colspan="<s:property value="pageui.colInfo.size+2"/>"> <xl:pagination value="pagination" buttons="back,excel" excel_url="menuarea_excel"></xl:pagination></td></tr>
           </table>
	     </div>
	     
	       <div>
      <!-- 引入excel 导出选项  -->
      <jsp:include page="/sys/UIExcel.jsp" >
      </jsp:include>
      </div>
      
		</xl:wrap>
		<s:debug></s:debug>
	</body>

	
</html>
