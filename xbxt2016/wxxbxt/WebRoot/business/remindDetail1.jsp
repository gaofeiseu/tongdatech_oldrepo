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
<script type="text/javascript">

</script>
</head>
<body>
    <table id="dg" class="easyui-datagrid"
		data-options="title:'���ڵ��ڿͻ�',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=1&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="CUSTNAME" width="80">�ͻ�����</th>
                <th field="SJ" width="120">�ֻ�</th>
                <th field="DH" width="120">�绰</th>
                <th field="ACCOUNT_NO" width="150">�˺�</th>
                <th field="CHUXU_TYPE" width="100">��������</th>
                <th field="MONEY_FINAL" width="100">���</th>
                <th field="STATUS" width="80">״̬</th>
                <th field="OPEN_OFFICE_CODE" width="100">����������</th>
                <th field="TRADE_DATE" width="100">��������</th>
                <th field="DAOQI_DATE" width="100">��������</th>
                <th field="CUST_P_ID" width="100">����Ψһ��ʶ</th>
            </tr>
        </thead>
    </table>
</body>
</html>