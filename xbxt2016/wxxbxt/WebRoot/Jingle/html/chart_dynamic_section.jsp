<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_dynamic_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">ʵʱͼ</h1>
        <nav class="right">
            <a href="#" class="button" id="pause_dynamic_chart">��ͣ</a>
        </nav>
    </header>
    <article class="active">
        <div class="chartTitle">��˾��Ʒ��������</div>
        <canvas id="dynamic_line_canvas" width="400" height="400"></canvas>
        <div class="legend">
            <div data-icon="stop" style="color: #72caed">A��Ʒ</div>
            <div data-icon="stop" style="color: #a6d854">B��Ʒ</div>
        </div>
    </article>
</section>