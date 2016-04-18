<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="menu_section">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">����</a>
        </nav>
        <h1 class="title">��߲˵���</h1>
    </header>
    <article class="active" id="menu_article"  data-scroll="true">
        <div class="scrollWrapper">
            <h1> ���˵���</h1>
            <div>
                <a class="button" href="#left_push_aside" data-target="menu">push</a>
                <a class="button" href="#left_overlay_aside" data-target="menu">overlay</a>
                <a class="button" href="#left_reveal_aside" data-target="menu">reveal</a>
                <a class="button" href="#show_close_aside" data-target="menu">show close</a>
            </div>
            <h1> �Ҳ�˵���</h1>
            <div>
                <a class="button" href="#right_push_aside" data-target="menu">push</a>
                <a class="button" href="#right_overlay_aside" data-target="menu">overlay</a>
                <a class="button" href="#right_reveal_aside" data-target="menu">reveal</a>
                <a class="button" href="#custom_width_aside" data-target="menu">�Զ�����</a>
            </div>

            <div>
                <ul class="list inset">
                    <li class="divider">aside�ϵ�����</li>
                    <li>Ĭ�Ͽ��Ϊ264px</li>
                    <li>data-position="left | right"</li>
                    <li>data-transition="push | overlay | reveal"</li>
                    <li>data-show-close="true | false"</li>
                    <li class="divider">push</li>
                    <li>�������ҳ��ͬʱ����</li>
                    <li class="divider">overlay</li>
                    <li>�������ҳ���ϲ㻮��</li>
                    <li class="divider">reveal</li>
                    <li>������ڵײ���ҳ�����Ϸ�����</li>
                </ul>
            </div>
        </div>
    </article>
</section>
