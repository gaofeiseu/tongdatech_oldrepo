<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!-- struts2 自带的ognl迭代标签在2.1中的特定情况下会非常慢  故此处提供2种写法 --> 

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
		<xl:wrap title="文件列表 /demo/file/demoDownloadList.jsp">

            

			
	
	       <!-- 方法2 -->
	       <table class="uicopy" style="width: 90%;margin: 0 auto;">
	         <tr class="title"><td colspan="4">文件列表</td></tr>
	         <tr class="header">
	            <td>唯一文件名</td>
	            <td>显示文件名</td>
				<td>文件类型</td>
				<td>下载</td>
			
	       </tr>
	       <s:iterator value='pageui.rows' id="obj" status="st">  
                <tr class="body">
                <td>${obj['file_path']}</td>
	            <td>${obj['file_name']}</td>
				<td>${obj['file_type']}</td>
				<td><a href="demoDownload_down?file_path=${obj['file_path']}&file_name=${obj['file_name']}">下载</a></td>
			 </tr>
			
           </s:iterator>
           <tr ><td colspan="4"> <xl:pagination value="pagination" buttons="back" ></xl:pagination></td></tr>
           </table>
  
          
      <div style="display:none">
      <!-- 引入excel 导出选项  -->
      <jsp:include page="/sys/UIExcel.jsp" >
      </jsp:include>
      </div>
          
          
		   
		</xl:wrap>
	</body>
</html>
