<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>保险到期客户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">

</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"
		data-options="title:'保险到期客户',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=3&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="OPEN_OFFICE_CODE" width="100">网点机构号</th>
                <th field="BAOXIAN_NO" width="100">保单号</th>
                <th field="BXGS" width="100">保险公司</th>
                <th field="TYPE" width="100">种类</th>
                <th field="PASS_NO" width="100">证件号码</th>
                <th field="CUSTNAME" width="100">姓名</th>
                <th field="phone" width="100">手机</th>
                <th field="tel" width="100">电话</th>
                <th field="address" width="100">地址</th>
                <th field="BAOFEI" width="100">金额</th>
                <th field="ACCOUNT_ORG" width="100">客户账号</th>
                <th field="SHUHUI_TAG" width="100">赎回标识</th>
                <th field="POL_STAT_FLAG" width="100">状态</th>
                <th field="TRADE_DATE" width="100">购买日期</th>
                <th field="POL_FINAL_DATE" width="100">到期日期</th>
                <th field="CUST_P_ID" width="100">客户唯一标识</th>
            </tr>
        </thead>
    </table>
</body>
</html>