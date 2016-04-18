<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="cust_report_detail_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">����</a>
        </nav>
        <h1 class="title">��<s:property value="bean.custname" />��������ϸ</h1>
    </header>
    <article class="active " id="cust_report_detail_article" data-scroll="true" style="padding: 10px;">
        <ul class="list">
        	<s:iterator value="bean.list_map" var="x">
            <li data-selected="selected"><!--  data-icon="next" -->
                <div class="tag">������<s:property value="#x.send_num" /></div>
                <strong><s:property value="#x.destination" /></strong>
                <p>
                	<span class="label">�ʷѣ�<s:property value="#x.fee" /></span>
                </p>
                <p>
                	<span class="label">�ʼ�ƽ��ʱ�ޣ�<s:property value="#x.mail_aver_time" />��</span>
                	<span class="label">��ƽ��ʱ�ޣ�<s:property value="#x.total_aver_time" />��</span>
                </p>
            </li>
            </s:iterator>
        </ul>
    </article>
</section>
