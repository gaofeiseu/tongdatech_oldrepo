<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>һ��ͨ���ڿͻ�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<xl:resource></xl:resource>
<script type="text/javascript">

</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"
		data-options="title:'һ��ͨ���ڿͻ�',
     	fit:true,rownumbers:true,pagination:true,pageSize:50,singleSelect:true" url="/remind_getDetail.action?query_type=2&user_root_brch=${session.userInfo.brch_no}">
        <thead>
            <tr>
                <th field="CUSTNAME" width="100">�ͻ�����</th>
                <th field="SJ" width="100">�ֻ�</th>
                <th field="DH" width="100">�绰</th>
                <th field="ACCOUNT_NO" width="100">�˺�</th>
                <th field="CHUXU_TYPE" width="100">��������</th>
                <th field="MONEY" width="100">���</th>
                <th field="STATUS" width="100">״̬</th>
                <th field="OPEN_OFFICE_CODE" width="100">����������</th>
                <th field="TRADE_DATE" width="100">��������</th>
                <th field="DAOQI_DATE" width="100">��������</th>
                <th field="CUST_P_ID" width="80">����Ψһ��ʶ</th>
            </tr>
        </thead>
    </table>
</body>
</html>