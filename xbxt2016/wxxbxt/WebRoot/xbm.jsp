<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
    	<title>С������</title>
    	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
   		<meta name="apple-mobile-web-app-capable" content="yes">
    	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    	<link rel="stylesheet" href="/Jingle/css/Jingle.css">
    	<link rel="stylesheet" href="/Jingle/css/app.css">
	</head>
	<body>
<div id="aside_container">
</div>

<div id="section_container">
    <section id="index_section" class="active">
        <header>
            <h1 class="title">С������</h1>
            <nav class="right">
                <a data-target="section" data-icon="info" href="#about_section"></a>
            </nav>
        </header>
        <article class="active" data-scroll="true">
            <div style="padding: 10px 0 20px;">
            <ul class="list inset demo-list">
            	<li data-icon="next" data-selected="selected">
                    <span class="icon calendar"></span>
                    <a href="#visit_log_section"  data-target="section"><!-- #MarketingLog_section -->
                        <strong>Ӫ����־</strong>
                        <p>�����Լ��߷��˶��ٿͻ�~</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bars"></span>
                    <a href="#cust_report_section"  data-target="section">
                        <strong>�ͻ�����</strong>
                        <p>�����ͻ��ı���~</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon search"></span>
                    <a href="#feequery_section"  data-target="section">
                        <strong>���۲�ѯ</strong>
                        <p>����ʼĵ�����Ҫ����Ǯ~</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bubble"></span>
                    <a href="#MarketingRecord_section"  data-target="section">
                        <strong>�ͻ��߷�</strong>
                        <p>�߷�ʱ�����Ǽ�¼~</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bell"></span>
                    <a href="#cust_warning_section"  data-target="section">
                        <strong>�ͻ�Ԥ��</strong>
                        <p>��Щ��ͻ���Ҫ�������~</p>
                    </a>
                </li>
            </ul>
            </div>
        </article>
    </section>
</div>
<!--<script type="text/javascript" src="js/lib/cordova.js"></script>-->
<!-- lib -->
<script type="text/javascript" src="/Jingle/js/lib/zepto.js"></script>
<script type="text/javascript" src="/Jingle/js/lib/iscroll.js"></script>
<script type="text/javascript" src="/Jingle/js/lib/template.min.js"></script>
<script type="text/javascript" src="/Jingle/js/lib/Jingle.debug.js"></script>
<script type="text/javascript" src="/Jingle/js/lib/zepto.touch2mouse.js"></script>
<script type="text/javascript" src="/Jingle/js/lib/JChart.debug.js"></script>
<!--- app --->
<script type="text/javascript" src="/Jingle/js/app/app.js"></script>
<!--<script src="http://192.168.2.153:8080/target/target-script-min.js#anonymous"></script>-->
</body>
</html>
<script type="text/ecmascript" src="/xbxt/js/md5.js"></script>
<script type="text/html" id="xbm_login_pop">
<div style="padding:10px 20px;">
	<div style="text-align:center;font-size:20px;color:#3498DB;font-weight:600;margin:5px 0;">��¼</div>
	<input type="text" id="login_pop_username" name="name" placeholder="�û���" value="">
	<input type="password" id="login_pop_password" name="password" placeholder="����" value="">
	<a href="#" id="login_pop_btn" class="button block" data-icon="checkmark">��¼</a>
</div>
</script>