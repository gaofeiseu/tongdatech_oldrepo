<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="grid_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">դ��ϵͳ</h1>
    </header>
    <article class="active" data-scroll="true">
        <div style="padding: 20px;">
            <p>դ��ϵͳ���õ���css3��flex-box��ʵ�ֵģ�չʾ�����ɡ�������col-1 - 5,�ȱ�������div,������ʹ��margin��padding</p>
            <h3>����</h3>
            <h4>1:1</h4>
            <div class="grid demo-grid">
                <div class="col-1">col-1</div>
                <div class="col-1">col-1</div>
            </div>
            <h4>1:1:1</h4>
            <div class="grid demo-grid">
                <div class="col-1">col-1</div>
                <div class="col-1">col-1</div>
                <div class="col-1">col-1</div>
            </div>
            <h4>1:2:3</h4>
            <div class="grid demo-grid">
                <div class="col-1">col-1</div>
                <div class="col-2">col-2</div>
                <div class="col-3">col-3</div>
            </div>
            <h4>��һ��Ϊ100px,�ڶ������ʣ�ಿ��</h4>
            <div class="grid demo-grid">
                <div class="col-0" style="width: 100px;">col-1</div>
                <div class="col-1">col-1</div>
            </div>
            <h3>����</h3>
            <h4>�߶�200px  1:2:3</h4>
            <div class="grid vertical demo-grid" style="height: 200px;">
                <div class="col-1">col-1</div>
                <div class="col-2">col-2</div>
                <div class="col-3">col-3</div>
            </div>
            <h4>����߶ȿ��divˮƽ��ֱ����</h4>
            <div class="grid middle" style="height: 100px;border:1px solid #ee3207">
                <div style="margin: auto;border: 1px solid #00364d;padding: 5px;">������ξ���</div>
            </div>
            <h4>�ۺϲ���</h4>

            <div class="grid middle demo-grid" style="height: 200px">
                <div class="col-1">
                    <div class="col-1">col-1</div>
                </div>
                <div class="col-2 grid vertical demo-grid">
                    <div class="col-1">col-1</div>
                    <div class="col-2">col-2</div>
                    <div class="col-3">col-3</div>
                </div>
            </div>
        </div>
    </article>
</section>