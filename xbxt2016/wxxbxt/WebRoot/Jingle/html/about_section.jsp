<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="about_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#"  data-icon="arrow-down-left-2" data-target="back">����</a>
        </nav>
        <h1 class="title">С��ϵͳ����</h1>
    </header>
    <article class="active indented">
        <div style="line-height: 2em;margin-bottom: 20px;">
        	3��3����ȫ����ЭίԱ���й��������Ź�˾�ܾ����������ȫ����Эʮ�����Ĵλ��鿪Ļ����Ӳ�������ίԱפ�صı����������������ʱ�ʾ֣�����������ԱҪ�ø߶ȵ��������θС���Ч����֯��ʵ�����ǵķ������飬Ϊ�����ṩ���ʡ���ݡ���ȫ�������������й���������120����֮�ʣ������չʾ�й������ľ����ò��ʱ����ɡ�
        </div>
        <ul class="list inset" style="margin: 10px 0;">
            <li data-icon="barcode">
                <s:property value="bean.mgr.mgrid"/>
            </li>
            <li data-icon="user">
            	<s:property value="bean.mgr.mgrname"/>
            </li>
            <li data-icon="tree" id="about_qq">
            	<s:property value="bean.mgr.department"/>
            </li>
        </ul>
        <div class="grid">
            <a href="javascript:window.open('http://58.214.25.2:9001/','_system')" class="button col-1">
             	����С��ϵͳ
            </a>
        </div>
    </article>
</section>