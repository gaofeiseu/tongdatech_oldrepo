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
    <dt>�������</dt>
    <dd>���´����ԭ������ǵ�ַ����,����URL��ַ,�����·��ʡ�</dd>
    <dd>����Ӱ��ϵͳ����ʹ������ϵϵͳ����Ա</dd>
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
