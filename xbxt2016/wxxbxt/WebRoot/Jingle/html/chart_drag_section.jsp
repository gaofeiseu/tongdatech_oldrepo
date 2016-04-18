<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_drag_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">���϶�ͼ��</h1>
    </header>
    <article class="active">
        <ul class="control-group" id="changeDragChartType" style="margin:10px 30px;">
            <li class="active" data-type="line">����ͼ</li>
            <li data-type="bar">��״ͼ</li>
        </ul>
        <div class="chartTitle">��˾��Ʒ��������</div>
        <canvas id="chart_drag_line_canvas"></canvas>
        <canvas id="chart_drag_bar_canvas" style="display: none;"></canvas>
        <div class="legend">
            <div data-icon="stop" style="color: #72caed">A��Ʒ</div>
            <div data-icon="stop" style="color: #a6d854">B��Ʒ</div>
        </div>
    </article>
</section>