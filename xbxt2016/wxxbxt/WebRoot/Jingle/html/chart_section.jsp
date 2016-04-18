<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="chart_section" data-transition="flip">
    <header>
        <nav class="left">
            <a href="#"  data-icon="arrow-down-left-2" data-target="back">����</a>
        </nav>
        <h1 class="title">Jingle Chart</h1>
    </header>
    <article class="active indented">
        <div style="line-height: 2em;margin-bottom: 20px;">
            Jingle Chart��һ���ƶ���ר��ͼ�����(������...)�����https://github.com/shixy/JChart
        </div>
        <ul class="list inset demo-list">
            <li data-icon="next" data-selected="selected">
                <span class="icon stats"></span>
                <a href="#chart_line_section"   data-target="section">
                    <strong>����ͼ</strong>
                    <p>����ͼ��ƽ������ͼ�����ͼ</p>
                </a>
            </li>
            <li data-icon="next" data-selected="selected">
                <span class="icon bars-2"></span>
                <a href="#chart_bar_section"data-target="section">
                    <strong>��״ͼ</strong>
                    <p>���������״ͼ(δ������������ͼ���ѻ�ͼ)</p>
                </a>
            </li>
            <li data-icon="next" data-selected="selected">
                <span class="icon pie"></span>
                <a href="#chart_pie_section"data-target="section">
                    <strong>��ͼ</strong>
                    <p>��ͼ������ͼ</p>
                </a>
            </li>
            <li data-icon="next" data-selected="selected">
                <span class="icon stats"></span>
                <a href="#chart_drag_section"data-target="section">
                    <strong>�������϶�������ͼ</strong>
                    <p>����ͼ����״ͼ��֧��</p>
                </a>
            </li>
            <li data-icon="next" data-selected="selected">
                <span class="icon bars-2"></span>
                <a href="#chart_dynamic_section"data-target="section">
                    <strong>��̬(ʵʱ)ͼ��</strong>
                    <p>����ͼ����״ͼ��֧��</p>
                </a>
            </li>
        </ul>
    </article>
</section>