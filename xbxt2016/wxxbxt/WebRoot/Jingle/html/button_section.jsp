<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="button_section"  data-transition="popup">
    <header>
        <nav class="left">
            <a href="#" class="button" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">Buttons</h1>
    </header>
    <article class="active" id="button_article" data-scroll="true">

        <div class="indented">
            <div>
                <a href="#" class="button" data-icon="home">button</a>
                <a href="#" class="button" data-icon="home right">button</a>
                <a href="#" class="button small alizarin">small button</a>
                <a href="#" class="button light small">light button</a>
                <a href="#" class="button disabled">button</a>
            </div>
            <div>
                <a href="#" class="button" data-icon="checkmark" data-count="7" data-orient="left">count left</a>
                <a href="#" class="button" data-icon="checkmark" data-count="7">icon+count</a>
            </div>
            <div class="demo-btn-group">
                <a href="#" class="button block">block button</a>
                <a href="#" class="button block nephritis">button</a>
                <a href="#" class="button block alizarin">button</a>
            </div>
            <div class="demo-btn-group">
                <div style="text-align: left">默认(只有文字)：</div>
                <ul class="control-group">
                    <li class="active"><a href="#">Hello</a></li>
                    <li><a href="#">Jingle</a></li>
                    <li><a href="#" >html5</a></li>
                </ul>
                <div style="text-align: left">只有图标：</div>
                <ul class="control-group">
                    <li data-icon="home"></li>
                    <li class="active" data-icon="bell"></li>
                    <li data-icon="spinner"></li>
                </ul>
                <div style="text-align: left">文字+图标(左右)：</div>
                <ul class="control-group">
                    <li class="active"><a href="#" data-icon="home">Hello</a></li>
                    <li><a href="#" data-icon="bell">Jingle</a></li>
                    <li><a href="#" data-icon="spinner">html5</a></li>
                </ul>
                <div style="text-align: left">文字+图标(上下)：</div>
                <ul class="control-group">
                    <li class="active" data-icon="home"><a href="#">Hello</a></li>
                    <li data-icon="bell"><a href="#">Jingle</a></li>
                    <li data-icon="spinner"><a href="#" >html5</a></li>
                </ul>
            </div>
        </div>

    </article>
</section>