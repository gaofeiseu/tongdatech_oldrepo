<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_pie_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">Pie</h1>
    </header>
    <article class="active">
        <ul class="control-group" id="changePieType" style="margin:10px 30px;">
            <li class="active" data-type="pie">��ͼ</li>
            <li data-type="dount">����ͼ</li>
        </ul>
        <div class="chartTitle">��վ������Դ</div>
        <canvas id="pie_canvas"></canvas>
        <div id="pie_segment_info" class="arrow_container">
        </div>
    </article>
</section>