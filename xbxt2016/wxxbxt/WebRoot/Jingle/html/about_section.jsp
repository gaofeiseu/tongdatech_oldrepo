<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="about_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#"  data-icon="arrow-down-left-2" data-target="back">返回</a>
        </nav>
        <h1 class="title">小包系统介绍</h1>
    </header>
    <article class="active indented">
        <div style="line-height: 2em;margin-bottom: 20px;">
        	3月3日晚，全国政协委员、中国邮政集团公司总经理李国华在全国政协十二届四次会议开幕会后视察了设在委员驻地的北京邮政两会服务临时邮局，勉励工作人员要用高度的政治责任感、高效的组织落实、高涨的服务热情，为两会提供优质、便捷、安全的邮政服务，在中国邮政开办120周年之际，向社会展示中国邮政的精神风貌和时代风采。
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
             	访问小包系统
            </a>
        </div>
    </article>
</section>