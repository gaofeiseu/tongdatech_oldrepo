<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���ѿ�ʼҳ��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<link href="/business/tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="/business/tablecloth/tablecloth.js"></script>
<script type="text/javascript" src="/business/js/remind.js"></script>
<script type="text/javascript">
var user_root_brch="${session.userInfo.brch_no}";
$(document).ready(function () {
	$(function(){
		$("a.my_a").each(function(){
			getNum(this);
		});
	});
});
</script>
</head>
<body>
	<table cellspacing="0" cellpadding="0">
		<tr>
			<th>��������</th>
			<th align="right">������Ϣ</th>
		</tr>
		<tr>
			<td>���ڵ��ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=1&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_1">(�����С�����)</a>�ʶ��ڴ���</td>
		</tr>
		<tr>
			<td>һ��ͨ���ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=2&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_2">(�����С�����)</a>��һ��ͨ����</td>
		</tr>
		<tr>
			<td>���յ��ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=3&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_3">(�����С�����)</a>�ʱ��յ���</td>
		</tr>
		<tr>
			<td>��Ƶ��ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=4&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_4">(�����С�����)</a>����Ƶ���</td>
		</tr>
		<tr>
			<td>����ʽ��ծ���ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=5&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_5">(�����С�����)</a>�ʴ���ʽ��ծ����</td>
		</tr>
		<tr>
			<td>ƾ֤ʽ��ծ���ڿͻ�����</td>
			<td align="right">�������������<a href="/remind_goDetail.action?query_type=6&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_6">(�����С�����)</a>��ƾ֤ʽ��ծ����</td>
		</tr>
	</table>
</body>
</html>