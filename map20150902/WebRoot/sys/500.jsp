<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta http-equiv=Content-Type content=text/html;charset=gbk>
<link href="/weblib/css/main.css" rel="stylesheet" type="text/css">

</head>
<body>

<table class="comWrapTab" height="100%">
<tr height="29px">
<td id="left_head"></td>
<td id="mid_head">
<ul>
<li id="l_title"></li>
<li id="m_title">500</li>
<li id="r_title"></li>
</ul>
</td>
<td id="right_head"></td>
</tr>
<tr>
<td id="left_body"></td>
<td id="mid_body">
<div id="WrapDiv">
  <div id="msg">
  <h1>500</h1>
  <dl>
    <dt>请求出错</dt>
    <dd>导致错误的原因可能是地址错误,请检查URL地址,再重新访问。</dd>
    <dd>如若影响系统正常使用请联系系统管理员</dd>
    <dd>${sessionScope.errNo}</dd>
  </dl>
  </div>
  </div>
</td>
<td id="right_body"></td>
</tr>
<tr height="17px">
<td id="left_buttom"></td>
<td id="mid_buttom"></td>
<td id="right_buttom"></td>
</tr>
</table>

</body>
</html>
