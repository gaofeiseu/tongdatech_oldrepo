<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!-- struts2 �Դ���ognl������ǩ��2.1�е��ض�����»�ǳ���  �ʴ˴��ṩ2��д�� --> 

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

    </script>

	</head>

	<body>
		<xl:wrap title="�ļ��б� /demo/file/demoDownloadList.jsp">

            

			
	
	       <!-- ����2 -->
	       <table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="title"><td colspan="4">�ļ��б�</td></tr>
	         <tr class="header">
	            <td>Ψһ�ļ���</td>
	            <td>��ʾ�ļ���</td>
				<td>�ļ�����</td>
				<td>����</td>
			
	       </tr>
	       <s:iterator value='pageui.rows' id="obj" status="st">  
                <tr class="body">
                <td>${obj['file_path']}</td>
	            <td>${obj['file_name']}</td>
				<td>${obj['file_type']}</td>
				<td><a href="demoDownload_down?file_path=${obj['file_path']}&file_name=${obj['file_name']}">����</a></td>
			 </tr>
			
           </s:iterator>
           <tr ><td colspan="4"> <xl:pagination value="pagination" buttons="back" ></xl:pagination></td></tr>
           </table>
  
          
      <div style="display:none">
      <!-- ����excel ����ѡ��  -->
      <jsp:include page="/sys/UIExcel.jsp" >
      </jsp:include>
      </div>
          
          
		   
		</xl:wrap>
	</body>
</html>
