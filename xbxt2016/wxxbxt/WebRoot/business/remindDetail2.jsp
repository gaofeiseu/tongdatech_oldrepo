<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>一本通到期客户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">

</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"
		data-options="title:'一本通到期客户',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=2&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="CUSTNAME" width="100">客户姓名</th>
                <th field="SJ" width="100">手机</th>
                <th field="DH" width="100">电话</th>
                <th field="ACCOUNT_NO" width="100">账号</th>
                <th field="CHUXU_TYPE" width="100">定期类型</th>
                <th field="MONEY" width="100">金额</th>
                <th field="STATUS" width="100">状态</th>
                <th field="OPEN_OFFICE_CODE" width="100">所属机构号</th>
                <th field="TRADE_DATE" width="100">交易日期</th>
                <th field="DAOQI_DATE" width="100">到期日期</th>
                <th field="CUST_P_ID" width="80">个人唯一标识</th>
            </tr>
        </thead>
    </table>
</body>
</html>