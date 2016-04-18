<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_line_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">Line</h1>
    </header>
    <article class="active">
        <ul class="control-group" id="changeLineType" style="margin:10px 30px;">
            <li class="active" data-type="normal">����ͼ</li>
            <li data-type="smooth">ƽ����ͼ</li>
            <li data-type="area">���ͼ</li>
        </ul>
        <div>
            <div class="chartTitle">��˾��Ʒ��������</div>
            <canvas id="line_canvas"></canvas>
            <div class="legend">
                <div data-icon="stop" style="color: #72caed">A��Ʒ</div>
                <div data-icon="stop" style="color: #a6d854">B��Ʒ</div>
            </div>
        </div>
    </article>
</section>