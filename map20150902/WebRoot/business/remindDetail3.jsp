<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>���յ��ڿͻ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">

</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"
		data-options="title:'���յ��ڿͻ�',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=3&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="OPEN_OFFICE_CODE" width="100">���������</th>
                <th field="BAOXIAN_NO" width="100">������</th>
                <th field="BXGS" width="100">���չ�˾</th>
                <th field="TYPE" width="100">����</th>
                <th field="PASS_NO" width="100">֤������</th>
                <th field="CUSTNAME" width="100">����</th>
                <th field="phone" width="100">�ֻ�</th>
                <th field="tel" width="100">�绰</th>
                <th field="address" width="100">��ַ</th>
                <th field="BAOFEI" width="100">���</th>
                <th field="ACCOUNT_ORG" width="100">�ͻ��˺�</th>
                <th field="SHUHUI_TAG" width="100">��ر�ʶ</th>
                <th field="POL_STAT_FLAG" width="100">״̬</th>
                <th field="TRADE_DATE" width="100">��������</th>
                <th field="POL_FINAL_DATE" width="100">��������</th>
                <th field="CUST_P_ID" width="100">�ͻ�Ψһ��ʶ</th>
            </tr>
        </thead>
    </table>
</body>
</html>