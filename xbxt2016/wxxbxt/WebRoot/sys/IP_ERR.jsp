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

  <xl:wrap title="Ȩ�޲���">
  <div id="msg">
  <h1>Ȩ�޲���</h1>
  <dl>
    <dt>��ǰIP��ַ����ʹ�ñ�����</dt>
    <dd>����Ӱ��ϵͳ����ʹ������ϵϵͳ����Ա</dd>
    <dd>${sessionScope.errNo}</dd>
  </dl>
  </div>
  </xl:wrap>

</body>
</html>
