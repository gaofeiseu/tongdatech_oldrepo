<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="feequery_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">����</a>
        </nav>
        <h1 class="title">���۲�ѯ</h1>
    </header>
    <article class="active " id="feequery_list_article" data-scroll="true" style="padding: 10px;">
        <ul class="list">
        	<s:iterator value="bean.list_map" var="x">
            <li data-selected="selected"><!--  data-icon="next" -->
                <div class="tag"><s:property value="#x.area" /></div>
                <strong><s:property value="#x.province" /></strong>
                <p>
                	<span class="label">��׼�ʷѣ�<s:property value="#x.standard_fee" /></span>
                	<span class="label">�ۿۺ��ʷѣ�<s:property value="#x.discount_fee" /></span>
                </p>
                <p>
                	<s:if test="#x.city_aver_time==''||#x.city_aver_time=='null'">
                	<span class="label">�ϸ���ʡ�ں�����ƽ��ʱ�ޣ�<s:property value="#x.center_aver_time" />��</span>
                	</s:if>
                	<s:else>
                	<span class="label">�ϸ���ʡ������ƽ��ʱ�ޣ�<s:property value="#x.city_aver_time" />��</span>
                	</s:else>
                </p>
            </li>
            </s:iterator>
        </ul>
    </article>
</section>
