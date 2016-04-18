<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>提醒开始页面</title>
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
			<th>提醒类型</th>
			<th align="right">提醒信息</th>
		</tr>
		<tr>
			<td>定期到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=1&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_1">(加载中。。。)</a>笔定期存款到期</td>
		</tr>
		<tr>
			<td>一本通到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=2&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_2">(加载中。。。)</a>笔一本通存款到期</td>
		</tr>
		<tr>
			<td>保险到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=3&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_3">(加载中。。。)</a>笔保险到期</td>
		</tr>
		<tr>
			<td>理财到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=4&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_4">(加载中。。。)</a>笔理财到期</td>
		</tr>
		<tr>
			<td>储蓄式国债到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=5&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_5">(加载中。。。)</a>笔储蓄式国债到期</td>
		</tr>
		<tr>
			<td>凭证式国债到期客户提醒</td>
			<td align="right">最近半月内您有<a href="/remind_goDetail.action?query_type=6&user_root_brch=${session.userInfo.brch_no}" class="my_a" id="a_6">(加载中。。。)</a>笔凭证式国债到期</td>
		</tr>
	</table>
</body>
</html>