<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
<%@ page import="com.tongdatech.tools.dataimport.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<%         
           //方法1取出结果对象
           ValueStack vs=(ValueStack)request.getAttribute("struts.valueStack");
           PageUI pageui=(PageUI)vs.findValue("context.render");
%>
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
	<script type="text/javascript">

	function doNext(selector) {
		$("#fm").submit();
	}
</script>

	<body>
<xl:wrap title="%{context.config.title}">

            
			<br>
			<div style="text-align: center;">
		<table class="uicopy" style="width: 90%;margin: 0 auto;">
			 <tr class="title"><td colspan="<%=(pageui.getColumns()).size()+1%>">导入数据</td></tr>
	         <tr class="header">
	            <td>行号</td>
	            <%
	            for(String col:(List<String>)pageui.getColumns()){
	            %><td><%=col %></td><%
	            }
	            %>
	            
				
	         </tr>
	         <%
	         for(Map mp:(List<Map>)pageui.getRows()){
	         %>
	         <tr class="body">
	            <td><%=mp.get(ImportTool.ROW_NUM)%></td>
	            
	            <%
	            for(String col:(List<String>)pageui.getColumns()){
	            %><td><%=mp.get(col)%></td><%
	            }
	            %>
			 </tr>
	         <%   
	         }
	         %>
	          
	       </table>
	       <div class="form_item col1" style="text-align: center">
	       <form action="import_result" method="post" id="fm" >
	        <input type="hidden" name="chainIndex" value="${chainIndex}">
	       </form>
	       <a href="###" class="easyui-linkbutton"
										data-options="plain:true,iconCls:'icon-next'"
										onclick="doNext()">下一步</a>
		   </div>
           </div>
</xl:wrap>
	</body>
</html>
