<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<section id="cust_report_section" data-transition="slideUp">
    <header>
        <nav class="left">
            <a href="#" data-icon="undo" data-target="back">返回</a>
        </nav>
        <h1 class="title">客户报表</h1>
    </header>
    <article class="active " id="cust_report_list_article" data-scroll="true" style="padding: 10px;">
        <div class="indented">
        	<form class="input-group" id="cust_report_form"><!--  action="/xbm_init_cust_report_section.action" -->
        		<div class="input-row">
                    <label><font color="red">客户名</font></label>
                    <input type="text" id="cust_report_custname" name="custname" placeholder="客户名或大客户号,最少3个字">
                </div>
                <div class="input-row">
                    <label><font color="red">查询年月</font></label>
                    <input type="month" id="cust_report_month" name="month" width="100" />
                </div>
                <div>
                	<a href="#" class="button block" id="find_cust">查找客户</a>
                </div>
            </form>
        </div>
        <div id="cust_report_list_div" style="display: none;">
        	<ul class="list inset" id="cust_report_list_ul">
        		
        	</ul>
        </div>
    </article>
</section>
<script type="text/javascript">
$("#find_cust").tap(function(){
	var custname = $("#cust_report_custname").val();
	var year_month = $("#cust_report_month").val();
	if(custname&&custname!=''&&year_month&&year_month!=''){
		if(custname.length>2){
			var year = year_month.split('-')[0];
			var month = year_month.split('-')[1];
			$.ajax({
				type : "POST",
				cache : false,
				async:true,
				url : "/xbm_ajax_find_cust.action",
				dataType : "json",
				data : {"custname":custname,"year":year,"month":month},
				success : function(result) {
					if(result.success){
						$("#cust_report_list_div").show();
						$("#cust_report_list_ul").html("");
						var html = "";
						for(var i=0;i<result.backParam.length;i++){
							html += "<li data-icon=\"next\" data-selected=\"selected\">";
							html += "<a href=\"#cust_report_detail_section?custname="+result.backParam[i].bigcustname+"&custid="+result.backParam[i].bigcustno+"&year="+result.backParam[i].year+"&month="+result.backParam[i].month+"\" data-target=\"section\">";
							html += "<div class=\"tag\">"+result.backParam[i].bigcustno+"</div>";
							html += "<strong>"+result.backParam[i].bigcustname+"</strong>";
							html += "<p>"+result.backParam[i].big_addr+"</p>";
							if(result.backParam[i].phone&&result.backParam[i].phone!=''&&result.backParam[i].phone!='null'){
								html += "<p><span class=\"label\">电话"+result.backParam[i].phone+"</span></p>";
							}
							html += "</a>";
							html += "</li>";
						}
						$("#cust_report_list_ul").html(html);
					}
				}
			});
		}
	}else{
		J.showToast('请输入客户名或客户号以及查询年月，再进行查询','error',2000);
	}
});
</script>