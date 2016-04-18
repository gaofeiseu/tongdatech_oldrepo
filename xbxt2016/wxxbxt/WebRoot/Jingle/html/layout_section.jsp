<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="layout_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#"  data-icon="previous" data-target="back">����</a>
        </nav>
        <ul class="control-group" id="layout_header_ctrl">
            <li class="active">A</li>
            <li>B</li>
        </ul>
    </header>
    <nav class="header-secondary">����һ�������������������</nav>
    <footer>
        <a  href="#layout_book_article" data-target="article" data-icon="book" class="active">book</a>
        <a  href="#layout_pencil_article" data-target="article" data-icon="pencil">pencil</a>
        <a  href="#" id="layout-btn-getmore"  data-icon="ellipsis">more</a>
    </footer>
    <article class="active" id="layout_book_article">
        <ul class="list">
            <li data-icon="next" data-selected="selected">
                <a href="#about_section" data-target="section">about</a>
            </li>
            <li data-icon="next" data-selected="selected">book</li>
            <li data-icon="next" data-selected="selected">book</li>
            <li data-icon="next" data-selected="selected">book</li>
            <li data-icon="next" data-selected="selected">book</li>
        </ul>
    </article>
    <article id="layout_pencil_article">
        <ul class="list">
            <li data-icon="pencil" data-selected="selected">pencil</li>
            <li data-icon="pencil" data-selected="selected">pencil</li>
            <li data-icon="pencil" data-selected="selected">pencil</li>
            <li data-icon="pencil" data-selected="selected">pencil</li>
            <li data-icon="pencil" data-selected="selected">pencil</li>
        </ul>
    </article>
</section>