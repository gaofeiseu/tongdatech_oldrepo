<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
<%@ page import="com.tongdatech.sys.demo.bean.*"%>
<%@ page import="com.tongdatech.sys.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!-- struts2 �Դ���ognl������ǩ��2.1�е��ض�����»�ǳ���  �ʴ˴��ṩ2��д�� --> 
<%         
           //����1ȡ���������
           ValueStack vs=(ValueStack)request.getAttribute("struts.valueStack");
           PageUI pageui=(PageUI)vs.findValue("pageui");
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
    <script type="text/javascript">
    function	pagePrint(){
     alert('��ӡԤ���ӿ�');
    } 
    </script>

	</head>

	<body>
		<xl:wrap title="��ͨ��ҳ  /demo/pagination/demoPage_list.jsp">

            
			<br>
			<div style="text-align: center;">
			
			<p>ognl������ǩ��struts2.1�е��ض�����»�ǳ���,2.3��δ������������, �Է���һ���ṩ2��д�� </P>
			<p>title:${title}</p>
			<br>
			
			<!-- ����1 -->
			<table class="uicopy" style="width: 90%;margin: 0 auto;">
			 <tr class="title"><td colspan="7">JSPд��</td></tr>
	         <tr class="header">
	            <td>sn</td>
				<td>colint1</td>
				<td>colint2</td>
				<td>colstr1</td>
				<td>colstr2</td>
				<td>coldate1</td>
				<td>coldate2</td>
	         </tr>
	         <%
	         String[] types={"HTML"};
	         for(DemoObj obj:(List<DemoObj>)pageui.getRows()){
	         %>
	         <tr class="body">
	            <td><%=VulnerabilitiesCheck.encode(obj.getSn(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColint1(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColint2(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColstr1(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColint2(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColdate1(),types)%></td>
				<td><%=VulnerabilitiesCheck.encode(obj.getColdate2(),types)%></td>
			 </tr>
	         <%   
	         }
	         %>
	          <tr ><td colspan="7"> <xl:pagination value="pagination" ></xl:pagination></td></tr>
	       </table>
	       
	       
	       <br>
	       <!-- ����2 -->
	       <table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="title"><td colspan="7">OGNLд��</td></tr>
	         <tr class="header">
	            <td>sn</td>
				<td>colint1</td>
				<td>colint2</td>
				<td>colstr1</td>
				<td>colstr2</td>
				<td>coldate1</td>
				<td>coldate2</td>
	       </tr>
	       <s:iterator value='pageui.rows' id="obj" status="st">  
                <tr class="body">
	            <td>${obj.sn}</td>
				<td>${obj.colint1}</td>
				<td>${obj.colint2}</td>
				<td>${obj.colstr1}</td>
				<td>${obj.colint2}</td>
				<td><s:date name="#obj.coldate1" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td><s:date name="#obj.coldate2" format="yyyy-MM-dd HH:mm:ss" /></td>
			 </tr>
			
           </s:iterator>
           <tr ><td colspan="7"> <xl:pagination value="pagination" buttons="back,excel,print" excel_url="demopage_excel"></xl:pagination></td></tr>
           </table>
  
          

      <!-- ����excel ����ѡ��  -->
      <jsp:include page="/sys/UIExcel.jsp" >
      </jsp:include>
    
          
          
		   </div>
		</xl:wrap>
	</body>
</html>
