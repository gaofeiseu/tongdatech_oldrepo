<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="cust_report_detail_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">返回</a>
        </nav>
        <h1 class="title">【<s:property value="bean.custname" />】报表明细</h1>
    </header>
    <article class="active " id="cust_report_detail_article" data-scroll="true" style="padding: 10px;">
        <ul class="list">
        	<s:iterator value="bean.list_map" var="x">
            <li data-selected="selected"><!--  data-icon="next" -->
                <div class="tag">发件数<s:property value="#x.send_num" /></div>
                <strong><s:property value="#x.destination" /></strong>
                <p>
                	<span class="label">资费：<s:property value="#x.fee" /></span>
                </p>
                <p>
                	<span class="label">邮件平均时限：<s:property value="#x.mail_aver_time" />天</span>
                	<span class="label">总平均时限：<s:property value="#x.total_aver_time" />天</span>
                </p>
            </li>
            </s:iterator>
        </ul>
    </article>
</section>
