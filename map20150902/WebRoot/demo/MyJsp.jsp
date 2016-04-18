<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="/weblib/css/main.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
    <xl:wrap title="首页">
    This is my JSP page. <br>
			<div class="datagrid-pager pagination">
				<table cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td>
								<select class="pagination-page-list">
									<option>
										10
									</option>
									<option>
										20
									</option>
									<option>
										30
									</option>
									<option>
										40
									</option>
									<option>
										50
									</option>
								</select>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)" class="l-btn l-btn-plain" group=""
									id=""><span class="l-btn-left"><span
										class="l-btn-text"><span
											class="l-btn-empty pagination-first">&nbsp;</span>
									</span>
								</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)" class="l-btn l-btn-plain" group=""
									id=""><span class="l-btn-left"><span
										class="l-btn-text"><span
											class="l-btn-empty pagination-prev">&nbsp;</span>
									</span>
								</span>
								</a>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<span style="padding-left: 6px;">第</span>
							</td>
							<td>
								<input type="text" size="2" value="1" class="pagination-num">
							</td>
							<td>
								<span style="padding-right: 6px;">共11页</span>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)" class="l-btn l-btn-plain" group=""
									id=""><span class="l-btn-left"><span
										class="l-btn-text"><span
											class="l-btn-empty pagination-next">&nbsp;</span>
									</span>
								</span>
								</a>
							</td>
							<td>
								<a href="javascript:void(0)" class="l-btn l-btn-plain" group=""
									id=""><span class="l-btn-left"><span
										class="l-btn-text"><span
											class="l-btn-empty pagination-last">&nbsp;</span>
									</span>
								</span>
								</a>
							</td>
							<td>
								<div class="pagination-btn-separator"></div>
							</td>
							<td>
								<a href="javascript:void(0)" class="l-btn l-btn-plain" group=""
									id=""><span class="l-btn-left"><span
										class="l-btn-text"><span
											class="l-btn-empty pagination-load">&nbsp;</span>
									</span>
								</span>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="pagination-info">
					显示181到200,共201记录
				</div>
				<div style="clear: both;"></div>
			</div>
		</xl:wrap>
  </body>
</html>
