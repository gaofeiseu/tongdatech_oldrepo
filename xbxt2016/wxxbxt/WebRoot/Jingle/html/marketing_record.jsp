<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="MarketingRecord_section" >
    <header>
        <nav class="left">
            <a href="#" data-icon="previous" data-target="back">����</a>
        </nav>
        <h1 class="title">�ͻ��߷�</h1>
    </header>
    <article data-scroll="true" class="active " id="article_mr" >
        <div class="indented">
            <!-- Input group with labels-->
            <form class="input-group" id="frm_mr_necessary" method="post" action="/xbm_marketing_record.action" data-ajax="false" enctype="multipart/form-data" target="hidden_frame">
            	<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
                <div class="input-row">
                    <label><font color="red">�ͻ�����</font></label>
                    <input type="text" name="mgrname" value="<s:property value="bean.mgr.mgrname"/>" readonly />
                    <input type="hidden" name="mgrid" value="<s:property value="bean.mgr.mgrid"/>" />
                    <input type="hidden" name="custid" value="<s:property value="bean.custid"/>" />
                </div>
                <div class="input-row">
                    <label><font color="red">�߷ÿͻ�</font></label>
                    <input type="text" name="custname" value="<s:property value="bean.custname"/>" />
                </div>
                <div class="input-row">
                    <label><font color="red">��ϵ������</font></label>
                    <input type="text" name="connect_name" value="<s:property value="bean.connect_name"/>" />
                </div>
                <div class="input-row">
                    <label><font color="red">��ϵ���ֻ�</font></label>
                    <input type="tel" name="connect_tel" value="<s:property value="bean.connect_tel"/>" />
                </div>
                <div class="input-row">
                    <label><font color="red">�ͻ�����</font></label>
                    <select style="width:180px;" name="custtype">
                    	<s:iterator value="bean.list_custtype" var="x">
                    	<option value="<s:property value="#x.param_value" />"><s:property value="#x.param_text" /></option>
                    	</s:iterator>
					</select>
                </div>
                <div class="input-row">
                    <label>����</label>
                    <input type="file" accept="image/png,image/gif,image/jpeg" id="mr_files" name="files" />
                </div>
                <div class="input-row">
                    <label>��ע</label>
                    <textarea rows="3" name="comments" ></textarea>
                </div>
                <div class="input-row">
                    <label>�ͻ�����</label>
                    <textarea rows="3" name="cust_feedback" ></textarea>
                </div>
                <div id="mr_div_save">
                	<a class="button block" data-icon="checkmark" id="mr_btn_save">����</a>
                </div>
            </form>
        </div>
    </article>
</section>
<script type="text/javascript">
$("#mr_btn_save").tap(function(){
	var mgrname = $("#frm_mr_necessary input[name='mgrname']").val();
	var mgrid = $("#frm_mr_necessary input[name='mgrid']").val();
	var custname = $("#frm_mr_necessary input[name='custname']").val();
	var connect_name = $("#frm_mr_necessary input[name='connect_name']").val();
	var connect_tel = $("#frm_mr_necessary input[name='connect_tel']").val();
	var custtype = $("#frm_mr_necessary select[name='custtype']").val();
	//console.log("mgrname:"+mgrname+"  "+"mgrid:"+mgrid+"  "+"custname:"+custname+"  "+"connect_name:"+connect_name+"  "+"connect_tel:"+connect_tel+"  "+"custtype:"+custtype+"  ");
	if(mgrname&&mgrid&&custname&&connect_name&&connect_tel&&custtype&&mgrname!=''&&mgrid!=''&&custname!=''&&connect_name!=''&&connect_tel!=''&&custtype!=''){
		$("#frm_mr_necessary").submit();
		$("#mr_div_save").hide();
	}else{
		J.showToast("��д�������ύ��~","error",3000);
	}
	//console.log($("#frm_mr_necessary").serialize());
});

function mr_callback(msg)
{
	J.showToast(msg,"success",2000);
	$("#mr_div_save").show();
	$("#mr_files").val("");
	J.Router.back();
}
</script>