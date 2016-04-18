<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="slider_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">slider</h1>
        <nav class="right">
            <a href="#" class="button" data-icon="previous" id="slider_prev"></a>
            <a href="#" class="button" data-icon="next" id="slider_next"></a>
        </nav>
    </header>
    <article class="active">
        <div id="slider_test" class="slider">
            <div>
                <div style="text-align: center"><img src="image/1.jpg"></div>
                <div style="text-align: center"><img src="image/2.jpg"></div>
                <div style="text-align: center"><img src="image/3.jpg"></div>
                <div style="text-align: center;"><img src="image/4.jpg"></div>
            </div>
        </div>
    </article>
</section>