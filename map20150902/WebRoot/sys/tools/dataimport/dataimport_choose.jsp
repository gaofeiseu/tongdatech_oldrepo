<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@ page import="com.tongdatech.sys.pojo.*"%>
<%@ page import="com.tongdatech.tools.dataimport.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="xl" uri="/struts-xl"%>
<%         
           //方法1取出结果对象
           ValueStack vs=(ValueStack)request.getAttribute("struts.valueStack");
           PageUI pageui=(PageUI)vs.findValue("context.render");
%>
<<!DOCTYPE HTML>


<html>



<head>
<meta http-equiv=Content-Type content=text/html;charset=gbk>
<!-- 添加以防止IE8下报JSON未定义 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<xl:resource></xl:resource>

	</head>
	<script type="text/javascript">
     var jsonData=${jsonData};
     var nowValue;
     
	function doNext() {
	   if($("#fm").form("validate")){
	     $("#fm").submit();
	   }
		
	}
	function colFilter(data){
	   data=data.concat();  
	   var name=$(this).val();
	   if(name!=undefined){
	    data.push({"value":name,"name":name});
	   }
	   return data;
	}
	$.extend($.fn.validatebox.defaults.rules, { 
    	unique: {  
            validator: function(value,param){
            // var obj=$(this).parent("span").prev("input");
             var obj=$(param[0]);
             var rs=true;
             
             if($(obj).attr("col")==value) 
             	return true;
             var uqs=$(".unique");
             for(i=0;i<uqs.length;i++){
                var unique=uqs[i];
                if(obj[0]!=unique){
                   var val=$(unique).next("span").find(".combo-text").val();
                   if(val==value){
                    rs=false;
                    break;
                   }
                }         
              }
             return rs;
            },  
            message: "列选择重复"
        }
     });
</script>

	<body>
<xl:wrap title="%{context.config.title}">

            
			<br>
			<div style="text-align: center;">
			<form  id="fm" action="import_result" method="post">
			<input type="hidden" name="chainIndex" value="${chainIndex}">
		<table class="uicopy" style="width: 90%;margin: 0 auto;">
			 <tr class="title"><td colspan="<%=(pageui.getColumns()).size()+1%>">导入数据</td></tr>
	         <tr class="header">
	            <td>行号</td>
	           	
	            <%
	            int i=0;
	            for(String col:(List<String>)pageui.getColumns()){
	            %><td>
	           <!--   <a href="#" class="easyui-linkbutton" onclick="openDlg(this)"><%=col%></a> -->
	           
	            <input id="id<%=i%>" name="<%=i%>"  value="<%=col %>"  col="<%=col %>"  class="easyui-combobox unique"
	            data-options="valueField:'value',textField:'name',data:JSON.parse(JSON.stringify(jsonData)),loadFilter:colFilter,validType:'unique[\'#id<%=i%>\']'"/></td>
	            <%i++;}%>
	            
				
	         </tr>
	         <%
	         for(Map mp:(List<Map>)pageui.getRows()){
	         %>
	         <tr class="body">
	            <td><%=mp.get(ImportTool.ROW_NUM)%></td>
	            
	            <%
	            for(String col:(List<String>)pageui.getColumns()){
	            %><td class="col<%=col%>"><%=mp.get(col)%></td><%
	            }
	            %>
			 </tr>
	         <%   
	         }
	         %> 
	          
	       </table>
	       
	       </form>
	       <div class="form_item col1" style="text-align: center">
	       <a href="###" class="easyui-linkbutton"
										data-options="plain:true,iconCls:'icon-next'"
										onclick="doNext()">下一步</a>
		   </div>
           </div>
</xl:wrap>


	</body>
</html>
