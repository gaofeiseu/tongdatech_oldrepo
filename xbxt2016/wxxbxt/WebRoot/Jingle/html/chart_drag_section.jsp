<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_drag_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">可拖动图表</h1>
    </header>
    <article class="active">
        <ul class="control-group" id="changeDragChartType" style="margin:10px 30px;">
            <li class="active" data-type="line">折线图</li>
            <li data-type="bar">柱状图</li>
        </ul>
        <div class="chartTitle">公司产品销售数据</div>
        <canvas id="chart_drag_line_canvas"></canvas>
        <canvas id="chart_drag_bar_canvas" style="display: none;"></canvas>
        <div class="legend">
            <div data-icon="stop" style="color: #72caed">A产品</div>
            <div data-icon="stop" style="color: #a6d854">B产品</div>
        </div>
    </article>
</section>