<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����ʽ��ծ���ڿͻ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">

</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"
		data-options="title:'����ʽ��ծ���ڿͻ�',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=5&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="OPEN_OFFICE_CODE" width="100">���������</th>
                <th field="PRODUCT_NAME" width="100">��Ʒ</th>
                <th field="PSN_18" width="100">֤������</th>
                <th field="CUSTNAME" width="100">����</th>
                <th field="SJ" width="100">�ֻ�</th>
                <th field="DH" width="100">�绰</th>
                <th field="MONEY_FINAL" width="100">���</th>
                <th field="ACCOUNT_NO" width="100">�ͻ��˺�</th>
                <th field="TRADE_DATE" width="100">��������</th>
                <th field="ENDDATE" width="100">��������</th>
                <th field="CUST_P_ID" width="100">�ͻ�Ψһ��ʶ</th>
            </tr>
        </thead>
    </table>
</body>
</html>