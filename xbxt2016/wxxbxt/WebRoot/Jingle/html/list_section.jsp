<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="list_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">����</a>
        </nav>
        <h1 class="title">list</h1>
    </header>
    <footer>
        <a  href="#list_article" data-target="article" data-icon="list" class="active">list</a>
        <a  href="#inset_list_article" data-target="article" data-icon="stack">inset list</a>
    </footer>
    <article class="active" id="list_article"  data-scroll="true">
        <ul class="list">
            <li class="divider">list</li>
            <li>
                <strong>Title</strong>
                <p>description</p>
            </li>
            <li class="divider">��icon�� list</li>
            <li data-icon="next">���м�ͷ</li>
            <li data-icon="airplane">���зɻ�</li>
            <li data-icon="checkbox-checked" class="active">yes</li>
            <li data-icon="checkbox-unchecked">no</li>
            <li class="divider">selected list</li>
            <li data-icon="checkbox-checked" data-selected="selected">����</li>
            <li class="divider">tag list</li>
            <li data-icon="next" data-selected="selected">
                <div class="grid">
                    <div class="col-1">
                        text
                    </div>
                    <div  class="label" >
                        100
                    </div>
                </div>

            </li>
            <li data-icon="next" data-selected="selected">
                <div class="tag">2013-05-22</div>
                <strong>Title</strong>
                <p>description</p>
            </li>
            <li data-icon="next" data-selected="selected">
                <div class="tag">2013-07-22</div>
                <strong>Title</strong>
                <p>description</p>
                <p><span class="label">java</span><span class="label">python</span></p>
            </li>
        </ul>
    </article>
    <article id="inset_list_article" data-scroll="true" style="padding: 10px;">
        <ul class="list inset">
            <li class="divider">list</li>
            <li>
                <strong>Title</strong>
                <p>description</p>
            </li>
            <li class="divider">��icon�� list</li>
            <li data-icon="next">���м�ͷ</li>
            <li data-icon="airplane">���зɻ�</li>
            <li data-icon="checkbox-checked" class="active">yes</li>
            <li data-icon="checkbox-unchecked">no</li>
            <li class="divider">selected list</li>
            <li data-icon="checkbox-checked" data-selected="selected">����</li>
            <li class="divider">tag list</li>
            <li data-icon="next" data-selected="selected">
                <div class="tag">2013-05-22</div>
                <strong>Title</strong>
                <p>description</p>
            </li>
            <li data-icon="next" data-selected="selected">
                <div class="tag">2013-07-22</div>
                <strong>Title</strong>
                <p>description</p>
                <p><span class="label">java</span><span class="label">python</span></p>
            </li>
        </ul>
    </article>
</section>