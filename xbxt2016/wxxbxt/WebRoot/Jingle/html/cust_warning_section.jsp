<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="cust_warning_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">����</a>
        </nav>
        <h1 class="title">�ͻ�Ԥ��</h1>
    </header>
    <article class="active " id="cust_warning_list_article" data-scroll="true" style="padding: 10px;">
        <ul class="list">
        	<s:iterator value="bean.list_map" var="x">
            <li data-icon="next" data-selected="selected">
            	<a href="#MarketingRecord_section?custid=<s:property value="#x.bigcustno" />&custname=<s:property value="#x.bigcustname" />&connect_name=<s:property value="#x.bigcustname" />&connect_tel=<s:property value="#x.phone" />" data-target="section">
                	<div class="tag">·��<s:property value="#x.destination" /></div>
                	<strong><s:property value="#x.bigcustname" /></strong>
               		<p><s:property value="#x.big_addr" /></p>
                	<p>
                		<span class="label">���·�����:<s:property value="#x.lastmonth_sendnum" /></span>
                		<span class="label">�����·�����:<s:property value="#x.lastlastmonth_sendnum" /></span>
                	</p>
                	<p>
                		<span class="label">��������1��<s:property value="#x.condition_one" /></span>
                		<span class="label">��������2��<s:property value="#x.condition_two" /></span>
                	</p>
                	<p>
                		<span class="label">�绰��<s:property value="#x.phone" /></span>
                	</p>
                </a>
            </li>
            </s:iterator>
        </ul>
    </article>
</section>
