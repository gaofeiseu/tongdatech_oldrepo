<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="scroll_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">scroll</h1>
    </header>
    <footer>
        <a href="#v_scroll_article" class="active" data-target="article" data-icon="arrow-up-2">�������</a>
        <a href="#h_scroll_article" data-target="article" data-icon="arrow-left-2">�������</a>
    </footer>
    <article class="active" id="v_scroll_article" data-scroll="true">
        <div class="scrollWrapper">
            <ul class="list">
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
                <li>�ö�����</li>
            </ul>
        </div>
    </article>
    <article id="h_scroll_article">
        <div style="margin:20px 10px;">
            <h1 style="margin:10px 0;">���һ�������</h1>
            <div style="overflow: hidden;" id="h_scroll_demo">
                <ul class="control-group" style="width: 700px;border-radius: 0;">
                    <li class="active"><a href="#">Hello</a></li>
                    <li><a href="#">Jingle</a></li>
                    <li><a href="#" >html5</a></li>
                    <li><a href="#" >html5</a></li>
                    <li><a href="#" >html5</a></li>
                    <li><a href="#" >html5</a></li>
                    <li><a href="#" >html5</a></li>
                </ul>
            </div>
        </div>
    </article>
</section>