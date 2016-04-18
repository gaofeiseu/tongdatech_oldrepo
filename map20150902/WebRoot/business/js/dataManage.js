
			function doQuery(){
			   if($('#sn').combobox('getValue')=="")
        	    {
        	    	  $.messager.show({title: "信息",msg: "请先选择数据类型再进行查询"});
        	    	  return; 
        	    } 
				var cols = [];
				
			 
			  
			    for(var i=0;i<colMap.length;i++)
			    {
			    	if((colMap[i].column_name).toLowerCase()!="post_flag")
			    	{
				    	if((colMap[i].column_name).toLowerCase()=="sn")
				    	{
				    		 var col = { field: (colMap[i].column_name).toLowerCase(),checkbox:true};
				    	}
				    	else if((colMap[i].column_name).toLowerCase()=="lat"||(colMap[i].column_name).toLowerCase()=="lng")
				    	{
				    		 var col = { field: (colMap[i].column_name).toLowerCase(), title: colMap[i].comments,width:160};
				    	}
				    	else
				    	{
				     	 var col = { field: (colMap[i].column_name).toLowerCase(), title: colMap[i].comments};
				    	}
	             	 	 cols.push(col);
			    	}
			    } 
			    var url="/data_manage_doquery.action?SN="+$('#sn').combobox('getValue')+"&query_property="+$('#queryString3').val();
        	 
			    $('#dataTab').datagrid({      
			    	                 
			            
			    	  dataType: 'json',      
			    	  url: url,      
			    	  columns: [cols],     
			    	  rownumbers:true,
			    	  pagination: true,      
			    	  pageSize: 50,                //每页记录数      
			    	  pageList: [50, 100, 150 ,200], //分页记录数数组      
			    	  onLoadSuccess: function (data, param) {      
			    	                      
			    	  }      
			    });      
			   
			}
		 
			
			function getselect()
			{
			 	  $('#sn').combobox('clear');
			      $('#sn').combobox('reload', "/data_manage_getSN.action");   
			}
			var colMap;
			function getColumn(newValue,oldValue)
			{
				jQuery("#sdfasdf").hide();
				document.getElementById("queryString3").value="";
				document.getElementById("queryString3Show").innerHTML="条件选择：";
				var json = {};
				json["SN"]=newValue; 
				var url="/data_manage_queryColumn.action";
				$.ajax({
					type : "POST",
					cache : false,
					async:true,
					url : url,
					dataType : "json",
					data : json,
					success : function(msg) {
			 			colMap=msg.colMaps;
			 			var str="";
			 		  	for(var i=0;i<colMap.length;i++)
			  			{
			  				 if(colMap[i].column_name!="SN"&&colMap[i].column_name!="POST_FLAG"&&colMap[i].column_name!="MC_MARKERS")
			            	 {	 
			            	 
			            		 if(colMap[i].data_type=="4")
								{
									str+="	 <div class=\"form_item col1\">";
									str+="		   <label></label>";
									str+= "<img id='preview' name='"+(colMap[i].column_name).toLowerCase()+"' alt='' width='220px' height='100px'  src='' />";
									str+="  </div>";
									str+="	 <div class=\"form_item col1\">";
									str+="		   <label>"+colMap[i].comments+"：</label>";
									str+= "  <input id='"+colMap[i].column_name+"' type='file' name='pic' style='width:100px'  onchange='change(this)' accept='image/*' />" ;
									str+="	<input id='filename' type='hidden' name='filename'/>";
									str+="  </div>";
								}
								else
								{
				            	 	 str+="	 <div class=\"form_item col1\">";
									 str+="		   <label>"+colMap[i].comments+"：</label>";
									 str+="	   <input type=\"text\" id=\""+colMap[i].column_name+"\"name=\""+(colMap[i].column_name).toLowerCase()+"\" ";
									 if(colMap[i].nullable=="2")
									 {
										 str+=" data-options=\"required:true\" ";
									 }
									 if(colMap[i].data_type=="2")
									 {
									 	 str+=" onkeyup=\"clearNoNum(this)\" "; 
									 }
									 str+=" maxlength=\""+colMap[i].data_length+"\" class=\"easyui-validatebox\" style=\"width:200px\" />";
									 str+="  </div>";
								 }
							 }
							 else{
							 	 str+="	   <input type=\"hidden\" id=\""+colMap[i].column_name+"\"name=\""+(colMap[i].column_name).toLowerCase()+"\" value=\"\">";
								
							 }
					    } 
			 		  	
			 			 str+="	   <input type=\"hidden\" id=\"query_string\" name=\"query_string\" value=\"\">";
							
					    jQuery("#user_fm").html(str);
					} 
				});
			}
			
			function delData(){ 
				
				var row =  $("#dataTab").datagrid("getSelections");
				if(row){
					 $.messager.confirm('确定','是否删这'+row.length+'条数据',function(r){
       		    if (r){
       		    var sn="";
       		    for(var i=0;i<row.length;i++)
       		    {
       		    	if(sn=="")
       		    		sn=row[i].sn;
       		    	else
       		    		sn+=","+row[i].sn;
       		    	 
       		    }
       		    
               $.post("/map_query_deleteData.action?query_string="+colMap[0].table_name+"&SN="+sn,row, function(result) {
				       if (result.if_success) {
					        $.messager.show({title: "成功",msg: result.msg});
					        $("#dataTab").datagrid("reload");
				       } else {
					        $.messager.show({title: "错误",msg: result.msg}); 
				       }
			         }, "json");
           
       		    }}); 
				}else{
					$.messager.alert("错误","请先选择一条数据");
				}
				
			}
			
			function editData(){
			 	if($('#sn').combobox('getValue')=="")
	        	{
	        	    	  $.messager.show({title: "信息",msg: "请先选择小类"});
	        	    	  return; 
	        	} 
				var row =  $("#dataTab").datagrid("getSelections");
				if(row.length==1){
					for(var i=0;i<colMap.length;i++)
		  			{
						if(colMap[i].data_type=="4")
						{
							 jQuery("#preview").attr("src", eval("row[0]."+(colMap[i].column_name).toLowerCase()));
							
						}
		  			}
					 $("#user_dlg").dialog("open").dialog("setTitle","数据修改");
			   		  $("#user_fm").form("load",row[0]);
		 
				}else{
					$.messager.alert("错误","请选择一行数据");
				}
			}
	
			function addData()
			{
				  if($('#sn').combobox('getValue')=="")
	        	  {
	        	    	  $.messager.show({title: "信息",msg: "请先选择小类"});
	        	    	  return; 
	        	  } 
				  jQuery("#preview").attr("src","");
				  $("#user_fm").form("clear");
				  $("#user_dlg").dialog("open").dialog("setTitle","数据新增");
				
			}
			

			
			function doSave(){ 
				var tempstring="";
				var string1="";
				var string2="";
				var filecolumn="";
				var sn=jQuery("#SN").val();
				
				if(sn==""||sn==null)
				{
					for(var i=0;i<colMap.length;i++)
					{
						if(colMap[i].nullable=="2"&&colMap[i].column_name!="SN"&&colMap[i].column_name!="POST_FLAG"&&colMap[i].column_name!="MC_MARKERS")
						{
							if(jQuery("#"+colMap[i].column_name).val().length==0)
							{
								$.messager.show({title: "信息",msg: colMap[i].comments+"不能为空"}); 
									
								return;
							}
								
						}
						if(colMap[i].data_type!="4")
						{
							  if(string1=="")
							  {
								if(colMap[i].column_name=="SN")
				           	 	{
					           	 	string1+=""+colMap[i].column_name;
									string2+="SEQ_"+colMap[i].table_name+".nextval"; 
				           	 	}
								else if(colMap[i].column_name=="STATUS_FLAG")
								{
									string1+=""+colMap[i].column_name;
									string2+="'1'"; 
								}
								 else
								 {
									string1+=""+colMap[i].column_name;
									string2+="'"+jQuery("#"+colMap[i].column_name).val()+"'";
								 }
							  }
							  else
							  {
								if(colMap[i].column_name=="SN")
				           	 	{
					           	 	string1+=","+colMap[i].column_name;
									string2+=",SEQ_"+colMap[i].table_name+".nextval"; 
				           	 	}
								else if(colMap[i].column_name=="STATUS_FLAG")
								{
									string1+=","+colMap[i].column_name;
									string2+=",'1'"; 
								}
								 else
								 {
									string1+=","+colMap[i].column_name;
									string2+=",'"+jQuery("#"+colMap[i].column_name).val()+"'";
								 }
							}
						}
						else
						{
							filecolumn=colMap[i].column_name;
						}
					}
				}
				else if(sn!=""&&sn!=null)
				{
					for(var i=0;i<colMap.length;i++)
					{
					 
						if(colMap[i].nullable=="2"&&colMap[i].column_name!="SN"&&colMap[i].column_name!="POST_FLAG"&&colMap[i].column_name!="MC_MARKERS")
						{
							if(colMap[i].nullable=="2")
							{
								if(jQuery("#"+colMap[i].column_name).val().length==0)
								{
									$.messager.show({title: "信息",msg: colMap[i].comments+"不能为空"}); 
									return;
								}
								
							}
						}
						if(colMap[i].data_type!="4")
						{
							if(colMap[i].column_name!="SN")
							{
								string2+=","+colMap[i].column_name+"='"+jQuery("#"+colMap[i].column_name).val()+"'"; 
							 }
						}
						else
						{
							filecolumn=colMap[i].column_name;
						}
					}
				}
				var query_string ="^"+colMap[0].table_name+"^"+string1+"^"+string2+"^";
				jQuery("#query_string").val(query_string);
				var SN =sn;
				$("#user_fm").attr("action","");
				var urls="/data_manage_doSave.action?SN="+sn+"&query_operator="+filecolumn;
			 		$("#user_fm").form("submit",{
			    	url: urls,
					onSubmit: function(){
						return $(this).form("validate");
					},
					success: function(result){
						var result=eval("("+result+")");
						if (result.if_success){
						    
             			    $("#user_dlg").dialog("close");
							if(SN=="")
							{ 
								$.messager.show({title: "信息",msg: result.msg}); 
							}
							else
							{
								$.messager.show({title: "信息",msg: result.msg}); 
							}  
							
							 $("#dataTab").datagrid("reload");
							
						} else {
							 
							$.messager.show({title: "信息",msg: result.msg}); 
							return;
						}
					}
				}); 
          
			}
			
			
function expportExcel()
{
	var sn=$('#sn').combobox('getValue');
	 
	if(sn=="")
	{
	       $.messager.show({title: "信息",msg: "请先选择数据类型"});
	        return; 
	} 
	//expexcel('#dataTab','/data_manage_excel.action');
	
	$("#excel_line").form("reset");
	$("#excel_page").form("reset");
	$("#exceldlg").dialog("open");
	
	//excelParam['queryParm']=$(selector).datagrid("getData").queryParams; 这种写法有问题
	excelParam['queryParm']="SN="+sn+""+"&query_property="+$('#queryString3').val();
	excelParam['total']=$('#dataTab').datagrid("getPager").pagination("options").total;
	excelParam['pageSize']=$('#dataTab').datagrid("options").pageSize;
	excelParam['pageNumber']=$('#dataTab').datagrid("options").pageNumber;
	excelParam['url']='/data_manage_excel.action';
	excelParam['maxPage']=Math.ceil(excelParam.total/excelParam.pageSize)||1;
}
			
			 

function change(obj) {
    var pic = document.getElementById("preview");
    var file = obj;
    document.getElementById("filename").value=file.value;
    
    var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
    // gif在IE浏览器暂时无法显示
    if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
        alert("文件必须为图片！"); return;
    }
    // IE浏览器
    if (document.all) {

        file.select();
        var reallocalpath = document.selection.createRange().text;
        var ie6 = /msie 6/i.test(navigator.userAgent);
        // IE6浏览器设置img的src为本地路径可以直接显示图片
        if (ie6) pic.src = reallocalpath;
        else {
            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
            pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
        }
    }else{
        html5Reader(file);
    }
}

function html5Reader(file){
    var file = file.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e){
        var pic = document.getElementById("preview");
        pic.src=this.result;
    };
}

 
function clearNoNum(obj)
{
    //先把非数字的都替换掉，除了数字和.
    obj.value = obj.value.replace(/[^\d.]/g,"");
    //必须保证第一个为数字而不是.
    obj.value = obj.value.replace(/^\./g,"");
    //保证只有出现一个.而没有多个.
    obj.value = obj.value.replace(/\.{2,}/g,".");
    //保证.只出现一次，而不能出现两次以上
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}

 