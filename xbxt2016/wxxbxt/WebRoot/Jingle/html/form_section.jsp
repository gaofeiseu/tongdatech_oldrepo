<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="form_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">Back</a>
        </nav>
        <h1 class="title">Form</h1>
    </header>
    <footer>
        <a  href="#form_module_article" class="active" data-target="article" data-icon="stack">���</a>
        <a  href="#form_article" data-target="article" data-icon="list" >��</a>
    </footer>
    <article data-scroll="true" id="form_article" >
        <div class="indented">
            <!-- Input -->
            <form>
                <input type="date">
                <input type="time">
                <input type="month">
                <input type="week">
                <a class="button block" data-icon="cogs">����</a>
            </form>

            <!-- Input group -->
            <form class="input-group">
                <input type="text" placeholder="��ʵ����">
                <input type="email" placeholder="��������">
                <input type="text" placeholder="�û���">
            </form>

            <!-- Input group with labels-->
            <form class="input-group">
                <div class="input-row">
                    <label>����</label>
                    <input type="text" placeholder="����">
                </div>
                <div class="input-row">
                    <label>����</label>
                    <input type="email" placeholder="example@gmail.com">
                </div>
                <div class="input-row">
                    <label>�û���</label>
                    <input id="username_form" type="text" placeholder="��ĸ�����֡��»���">
                </div>
                <div class="input-row">
                    <label>�Ա�</label>
                    <div class="toggle active" data-on="��" data-off="Ů" name="sex"></div>
                </div>
            </form>
        </div>
    </article>
    <article data-scroll="true" class="active " id="form_module_article" >
        <div>
        <ul class="list">
            <li class="divider">checkbox</li>
            <li>
                <div data-checkbox="unchecked" id="checkbox_1">������͹�����</div>
                <div data-checkbox="checked" id="checkbox_2">������͹�����</div>
            </li>
            <li class="divider">toggle</li>
            <li>
                <div class="demo-btn-group">
                    <div style="text-align: left">Ĭ�ϣ�</div>
                    <div class="toggle active" >
                    </div>
                    <div class="toggle" >
                    </div>
                    <div style="text-align: left">�Զ������� data-on="����" data-off="�ر�"</div>
                    <div style="text-align: left">
                        <label>���ԣ�</label>
                        <div class="toggle active" data-on="����" data-off="�ر�"></div>
                    </div>
                    <div style="text-align: left">
                        <label>���ԣ�</label>
                        <div class="toggle" data-on="��" data-off="��"></div>
                    </div>

                </div>
            </li>
            <li class="divider">range</li>
            <li>
                <div class="demo-btn-group">
                    <div style="text-align: left">Ĭ�ϣ�</div>
                    <div>
                        <input type="range" min=4 max=10 value="7"/>
                    </div>
                    <div style="text-align: left">�Ҳ���ʾֵ��</div>
                    <div  data-rangeinput="right">
                        <input type="range" min=4 max=10 value="7"/>
                    </div>
                    <div style="text-align: left">�����ʾֵ��</div>
                    <div  data-rangeinput="left">
                        <input type="range" min=4 max=10 value="8"/>
                    </div>
                </div>
            </li>
            <li class="divider">progress</li>
            <li>
                <div class="demo-btn-group">
                    <div style="text-align: left">Ĭ�ϣ�</div>
                    <div data-progress="40"></div>
                    <div style="text-align: left">�Զ������֣�</div>
                    <div data-progress="80" data-title="�����"></div>
                </div>
            </li>
        </ul>
        </div>
    </article>
</section>