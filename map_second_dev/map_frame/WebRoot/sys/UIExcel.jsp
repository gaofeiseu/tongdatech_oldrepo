<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
  <script type="text/javascript">
  	
  	var excelParam={};
  	function formatExcelItem(row){  
            var s = '<span style="font-weight:bold">' + row.text + '</span><br/>' +  
                    '<span style="color:#888">' + row.desc + '</span>';  
            return s;  
    }
    function pageExcel(){
        if(pageExcelParam.url=='null'||pageExcelParam.url.length==0){
        	alert("未配置EXCEL下载链接");
        	}else{
        	 $("#excel_line").form("reset");
    	     $("#excel_page").form("reset");
    	     $("#exceldlg").dialog("open");
    	     //uiPanelReposition($("#exceldlg"));
    	     excelParam=pageExcelParam;
    	    }
    }    
    function expexcel(selector,excel_url){
    	$("#excel_line").form("reset");
    	$("#excel_page").form("reset");
    	$("#exceldlg").dialog("open");
    	
    	//excelParam['queryParm']=$(selector).datagrid("getData").queryParams; 这种写法有问题
    	excelParam['queryParm']=$.param($(selector).datagrid("options").queryParams);
    	excelParam['total']=$(selector).datagrid("getPager").pagination("options").total;
    	excelParam['pageSize']=$(selector).datagrid("options").pageSize;
    	excelParam['pageNumber']=$(selector).datagrid("options").pageNumber;
    	excelParam['url']=excel_url;
    	excelParam['maxPage']=Math.ceil(excelParam.total/excelParam.pageSize)||1;
    }
    	
    function doexpexcel(){
   	var exceltype = $("input[name='exceltype']:checked").val();
   	var edition =$("#edition").combobox("getValue");
   	
   	var stnum=0;var ednum=0;
   	if(excelParam.total==0){$.messager.alert("错误","根据条件只能导出0条,请检查查询条件"); return false;}

   	
   	if(exceltype==2){//2导出当前
    
      stnum=excelParam.pageSize * (excelParam.pageNumber - 1)+1;
      ednum=excelParam.pageSize *  excelParam.pageNumber;   
     
	  }else if(exceltype==3){//3导出条数
	  	 
	  	 
	     if($("#excel_line").form("validate")==false){return false;}
	     stnum=Math.min($("#excel_line1").val(),$("#excel_line2").val());
	     ednum=Math.max($("#excel_line1").val(),$("#excel_line2").val());
	     ednum=Math.min(ednum,edition);
	     
	  }else if(exceltype==4){ //4导出页数
	  	
	
       if($("#excel_page").form("validate")==false){return false;}

	     stnum=excelParam.pageSize*(Math.min($("#excel_page1").val(),$("#excel_page2").val())-1)+1;
	     ednum=excelParam.pageSize*Math.max($("#excel_page1").val(),$("#excel_page2").val());
	     ednum=Math.min(Math.min(ednum,edition),excelParam.total);
	     
	  }else { //1 全部导出
	  	stnum=1;
	  	ednum=edition;
	  	
	  }
	  
	  	var excelfmurl=excelParam.url+"?excelEdition="+edition+"&stnum="+stnum+"&ednum="+ednum+"&"+excelParam.queryParm;
	    $('#excelfm').form('submit', {   
	    url:excelfmurl,   
	 
	    onSubmit: function(){   
	 	onloading();
	    $('#exceldlg').dialog('close');
	    },   
	    success:function(result){   
		
	       
	    }   
	    });  
 		removeload();

   	}
   	
   	
   	
   	
   	function onloading(){  
         $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");   
         $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});   
    }  
    function removeload(){  
        $(".datagrid-mask").remove();  
        $(".datagrid-mask-msg").remove();  
    }  
       

  </script>
	<!-- easyui excel 导出对话框  -->
	<div id="exceldlg" class="easyui-dialog" class="easyui-dialog" data-options="closed:true,resizable:true,modal:true,title:'导出EXCEL',
		    onOpen:uiPanelReposition,
				buttons:[
				{ text:'确认',plain:true,iconCls:'icon-ok',	    handler:function(){doexpexcel();}                   },
				{ text:'取消',plain:true,iconCls:'icon-cancel',	handler:function(){$('#exceldlg').dialog('close');}  }]"
				 style="width:360px;height:280px;">	
      <form id="excelfm" method="post"></form>
		
      <input type="hidden" id="range" />
      <input type="hidden" id="total" />
      
      <div class="form_item col1">
				    <label>EXCEL版本:</label>
            <input class="easyui-combobox"id="edition" name="edition" data-options="  
                data: [{id:'65500',text:'EXCEL2003版',desc:'2003版EXCEL通常以xls或XLS结尾。最大支持65535行数据，本系统支持1次导出数据范围为1-65500条',selected:true},
  		                 {id:'1048500',text:'EXCEL2007版',desc:'2007版EXCEL通常以xlsx或XLSX结尾。最大支持1048576行数据，本系统支持1次导出数据范围为1-1048500条'}],  
                valueField: 'id',  
                textField: 'text',  
                panelWidth: 200,  
                panelHeight: 'auto',  
                formatter: formatExcelItem 
                ">
      </div>  
      <div class="form_item col1">
      	    
				    <label><input type="radio"  name="exceltype" value="1" checked="checked"/>导出全部:</label>
				    导出符合条件的全部数据
			</div>
      <div class="form_item col1">
      	    
				    <label><input type="radio"  name="exceltype" value="2" />导出当前:</label>
				    导出符合条件的本页数据
			</div>
			<div class="form_item col1">
				    
      	    
				    <label><input type="radio"  name="exceltype" value="3" />导出条数:</label>
				    <form id="excel_line" method="post">		    
				    <input id="excel_line1" type="text"  maxlength="7" class="easyui-validatebox"  style="width:52px"  data-options="validType:['positive_int','between[1,excelParam.total]' ]"/>
          - <input id="excel_line2" type="text"  maxlength="7" class="easyui-validatebox"  style="width:52px"  data-options="validType:['positive_int','between[1,excelParam.total]' ]"/>条
            </form>
			</div>
			<div class="form_item col1">
				    
      	    
				    <label><input type="radio"  name="exceltype" value="4" />导出页数:</label>
				    <form id="excel_page" method="post">
				    <input id="excel_page1" type="text" maxlength="7"  class="easyui-validatebox"  style="width:52px"  data-options="validType:['positive_int','between[1,excelParam.maxPage]']"/>
          - <input id="excel_page2" type="text" maxlength="7"  class="easyui-validatebox"  style="width:52px"  data-options="validType:['positive_int','between[1,excelParam.maxPage]']"/>页
            </form>
			</div>
			<div class="form_item col1" style="color:#666">注意:超出EXCEL最大限制的行数将自动被剔除</div>

			
			
			

	</div>


