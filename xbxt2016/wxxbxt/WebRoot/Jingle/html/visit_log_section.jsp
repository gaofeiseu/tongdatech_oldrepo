<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="visit_log_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">返回</a>
        </nav>
        <h1 class="title">我的走访日志</h1>
    </header>
    <article class="active " id="visit_log_list_article" data-scroll="true" style="padding: 10px;">
        <ul class="list">
        	<s:iterator value="bean.list_map" var="x">
            <li data-selected="selected"><!-- data-icon="next"  -->
                <div class="tag"><s:property value="#x.visit_time" /></div>
                <strong><s:property value="#x.custname" /></strong>
                <p>
                	<span class="label"><s:property value="#x.param_text" /></span>
                	<span class="label"><s:property value="#x.img_str" /></span>
                </p>
                <p><span class="label">联系方式：<s:property value="#x.connect_tel" /></span></p>
            </li>
            </s:iterator>
        </ul>
    </article>
</section>
