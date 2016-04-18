<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<title></title>

<meta http-equiv=Content-Type content=text/html;charset=gbk>
<link href="/weblib/css/main.css" rel="stylesheet" type="text/css">

</head>
<body>

  <xl:wrap title="数据库异常">
  <div id="msg">
  <h1>数据库异常</h1>
  <dl>
    <dt>数据库内部抛出异常</dt>
    <dd>如若影响系统正常使用请联系系统管理员</dd>
    <dd>${sessionScope.errNo}</dd>
  </dl>
  </div>
  </xl:wrap>

</body>
</html>
