<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_bar_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">Bar</h1>
    </header>
    <article class="active">
        <div class="chartTitle">公司产品销售汇总</div>
        <canvas id="bar_canvas"></canvas>
        <div class="legend">
            <div data-icon="stop" style="color: #72caed">A产品</div>
            <div data-icon="stop" style="color: #a6d854">B产品</div>
        </div>
    </article>
</section>