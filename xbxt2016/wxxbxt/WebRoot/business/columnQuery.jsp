<%@ page language="java" import="java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="gb2312"%>

<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=gb2312" />
	 
		<title>Map</title>
	 
 
		<script type="text/javascript" src="/weblib/js/jquery-easyui-1.3.5/jquery.min.js"></script>
 
 
		<script type="text/javascript" src="/business/js/column-query.js"></script>
		
	 	<link rel="stylesheet" type="text/css" href="/map/css/css.css">
		
		<script type="text/javascript">
	

		</script>
	</head>
		<body  >
		<div id="content">
	 		
			<table width="98%" border="0" align="center" cellpadding="4" cellspacing="1" class="condit">
					<th colspan="6"><span style="width:40%"> <strong ><span>查询条件填写</span></strong></span><span style="width: 60%;text-align: right"><font color="red">（注意：此处添加查询条件时，条件是<select id="where"><option value="and">且</option><option value="or">或</option>  </select>的关系）</font></span></th>
			
				<tr class="title">
					<td width="20%" nowrap valign="top">
						<table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" class="dataTable" style="margin-top: 0px;">
							
			
							<!-- count 从1开始   index从0开始 -->
							<%
							HttpSession sessions = request.getSession();
							List<Map> resultList = new ArrayList<Map>();
							if(sessions!=null){
								resultList = (List<Map>) sessions.getAttribute("resultList");	
							}
							
							for(int i=0;i<resultList.size();i++)
							{
								if(i%2==0)
								{
									if(i%4==0)
									{
								%>
									<tr class="tr1">
									<%}
									else{%>
									<tr class="tr2">
								
								<%}
								}
								%>
									<td align="left" nowrap width="50%">
										<span class="attitems">
										<a href="#" onclick="inputTest('<%=resultList.get(i).get("class_columnname") %>','<%=resultList.get(i).get("class_comments") %>','<%=resultList.get(i).get("class_columntype") %>','');">
										<span id="<%=resultList.get(i).get("class_columnname") %>"><%=resultList.get(i).get("class_comments") %></span>
										</a>
										</span>&nbsp;
									</td>
									<%if(i%2==0)
								{ %>
										<%if(i==resultList.size()-1) {%>
								<td align="left" nowrap width="50%"></td>
								<% }
								}
								%>
								<%if(i%2!=0)
								{ %>
							
									</tr>
								
							<% }
							}
							%>
						
						
						</table>
						<table id="tab_spcl" width="100%" border="0" align="center" cellpadding="4" cellspacing="1" class="dataTable" style="margin-top: 0px;"></table>
					</td>
					<td width="80%" nowrap valign="top">
						<table id="condition" width="100%" border="0" align="center" cellpadding="4" cellspacing="1" class="dataTable" style="margin-top: 0px;"></table>
					</td>
				</tr>
			</table>
			
			<table width="98%" border="0" align="center" cellpadding="4" cellspacing="1" class="condit">
				<tr class="title">
				
					<td width="10%" nowrap align="center">
						<input type="button" id="show" onclick="sub();" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="确定"/>&nbsp;
					 	<input type="button" id="seldata" onclick="window.close();" class="button" onMouseOver="this.className='button00';" onMouseOut="this.className='button';" value="关闭"/>&nbsp; 
				</td>
				</tr>
				 
			</table>
			
		</div>
		</body>
</html>