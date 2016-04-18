/**=============================================================================
 * FOR AJAX
 **=============================================================================*/
$.ajaxSetup({
traditional:true,//传统方式序列化参数  而不是a[]=1&&a[]=2
contentType:'application/x-www-form-urlencoded;  charset=UTF-8', //确保ie下中文不乱码
complete:function(XMLHttpRequest,textStatus){
    //通过XMLHttpRequest取得响应头，sessionstatus           
    var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
    if(sessionstatus=="SESSION_TIME_OUT"){
         window.location.replace("/sys/GO.jsp");
    }
    }
});
$.fn.datagrid.defaults.pageList=[50,100,150,200];
$.fn.datagrid.defaults.pageSize=100; 
/**=============================================================================
 * FOR FORM
 * 将FORM中值转化为JSON
 **=============================================================================*/
$.fn.form2json = function() {
	var serializedParams = this.serialize();
	var obj = paramString2obj(serializedParams);
	return obj;
};
$.fn.form2param = function() {
	var serializedParams = this.serialize();
	var obj = paramString2Param(serializedParams);
	return obj;
};

//将形如param1=x1&param2=x2&obj.name=name的串转化为JSON {param1:x1,param2:x2,obj:{name:name}}
function paramString2obj(serializedParams) {
	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		var attributeValue = str.split("=")[1];
		if (!attributeValue) {
			return;
		}
		var array = attributeName.split(".");
		for ( var i = 1; i < array.length; i++) {
			var tmpArray = Array();
			tmpArray.push("obj");
			for ( var j = 0; j < i; j++) {
				tmpArray.push(array[j]);
			}

			var evalString = tmpArray.join(".");
			if (!eval(evalString)) {
				eval(evalString + "={};");
			}
		};
		eval("obj." + attributeName + "='" + decodeURI(attributeValue) + "';");
	}
	;
	var properties = serializedParams.split("&");
	for ( var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	}
	;
	return obj;
}

//将形如param1=x1&param2=x2&obj.name=name的串转化为JSON {param1:x1,param2:x2,obj.name:name}}
function paramString2Param(serializedParams) {
	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		//attributeName=attributeName.replace(/\./g, "\\\\.");// obj.name 转换为obj\\.name=
		var attributeValue = str.split("=")[1];
		if (!attributeValue) {
			return;
		}
		obj[attributeName]=decodeURI(attributeValue) ;
	}
	;
	var properties = serializedParams.split("&");
	for ( var i = 0; i < properties.length; i++) {
		evalThem(properties[i]);
	}
	;
	return obj;
}

/**
 * 给json对象加前缀
 * @param json json对象
 * @param perfix 前缀
 * @returns
 */
$.Json2Perfix=function(json,perfix){
	var data=$.param(json);
	var data=data.replace(/(&?)(\w*)(=)/g, "$1"+perfix+"."+"$2$3");
	return paramString2Param(data);
};


/**
 * 修复所有easyui Panel 打开居中时未考虑 scroll的情况
 * */
function uiPanelReposition(){
	obj=this;
	var height=$(obj).panel("options").height;
	var width=$(obj).panel("options").width;
	var top = ($(window).height() - height)/2; 
    var left = ($(window).width() - width)/2; 
    var scrollTop = $(document).scrollTop(); 
    var scrollLeft = $(document).scrollLeft(); 
    $(obj).panel("move",
    { left : left + scrollLeft,top : top + scrollTop });
   
}

/**
 * 通用异步form 提交
 * @param fmSelector form 选择器
 * @param urls       执行url 
 * @param callback   执行成功时的回调函数
 */
function fmSubmit(fmSelector,urls,callback){
    $(fmSelector).form("submit",{
    	url: urls,
			  onSubmit: function(){
				  return $(this).form("validate");
			  },
			 success: function(result){
				var result = eval("("+result+")");
				//alert(result);
				if (result.success){
					callback(result);	
					$.messager.show({title: "成功",msg: result.msg});
				} else {
					$.messager.show({title: "错误",msg: result.msg});
				}
			}
		 });
		 }
 /**
 * form 重置功能
 * @param selector form 选择器
 */
function resetform(selector){
		   $(selector).form("reset");
	   }
/**=============================================================================
 * FOR TREE
 * 常用的树
 **=============================================================================*/

/**
 * 常规机构树
 * @param selector  jQuery 选择器
 * @param root 树根节点
 * @param mode 树模式
 * @param uiOnClickBrch 点击事件函数
 * @see TreeAction brch
 */

function loadBrchTree(selector,root,mode,uiOnClickBrch){
	//using("tree", function(){
		$(selector).tree({   
            url: "tree_brch?node="+root+"&type=one&mode="+mode, 
            onBeforeExpand:function(node,param){
               $(this).tree("options").url = "tree_brch?node=" + node.id+"&mode="+mode;                 
            },
            onClick:function(node){                 
            	uiOnClickBrch(node);
            },
            onLoadSuccess:function(node,data){   //树的date 是一个array                                         
              if(data!=null&&data[0]!=null&&data[0].id==root){
              var dis=$(this).tree('find',root);    //查找出根节点的root对象
              $(this).tree('expand',dis.target);    //展开根节点root
              }
            }
     });
	//});
}

function loadXBBrchTree(selector,root,mode,uiOnClickBrch){
	$(selector).tree({   
		url: "/xbxt_brch?node="+root+"&type=one&mode="+mode, 
		onBeforeExpand:function(node,param){
			$(this).tree("options").url = "/xbxt_brch?node=" + node.id+"&mode="+mode;                 
		},
		onClick:function(node){                 
			uiOnClickBrch(node);
		},
		onLoadSuccess:function(node,data){   //树的date 是一个array                                         
			if(data!=null&&data[0]!=null&&data[0].id==root){
				var dis=$(this).tree('find',root);    //查找出根节点的root对象
				$(this).tree('expand',dis.target);    //展开根节点root
			}
		}
     });
}

/**
 * 常规机构下拉树
 * @param selector  jQuery 选择器
 * @param treeRoot  树根节点
 * @param expNode   展开至节点
 * value 实际值
 * text  显示文本
 * e.g. <input value="1" text="一"/>
 */
function loadBrchCombo(selector,root,expNode){
	//using("combotree", function(){
	$.each($(selector), function(i, item){  
        var obj=this;
        var url="tree_brch?node="+root+"&type=one";
        if(expNode!=undefined){
        	url ="tree_expandToBrch?node="+root+"&expnode="+expNode;
        }
		$(this).combotree(
//			{
//			 required : true,
//			 missingMessage:"机构必须选择"
//			}
		)
		 .combotree("tree").tree({
			url: url,
			onBeforeExpand : function(node, param) {
				$(this).tree("options").url = "tree_brch?node=" + node.id;  	
			},
			onLoadSuccess:function(node, data){
				//alert($(obj).combotree("getValue"));
				if(expNode==undefined&&$(obj).attr("text")!=undefined){
					text = $(obj).attr("text");
					$(obj).combotree("setText",text);
				}
				if(expNode!=undefined){
					var en= $(this).tree("find",Number(expNode));					
					$(this).tree("expandTo",en.target);
					//$(this).tree("select",en.target);
					$(obj).combotree("setValue",en.id);
				}
			}
	 		    
		 });

	   });	
	//});
}


function loadXBBrchCombo(selector,root,expNode){
	$.each($(selector), function(i, item){  
        var obj=this;
        var url="/xbxt_brch2?node="+root+"&type=one";
        if(expNode!=undefined){
        	url ="/xbxt_expandToBrch?node="+root+"&expnode="+expNode;
        }
		$(this).combotree(
//			{
//			 required : true,
//			 missingMessage:"机构必须选择"
//			}
		).combotree("tree").tree({
			url: url,
			onBeforeExpand : function(node, param) {
				$(this).tree("options").url = "/xbxt_brch2?node=" + node.id;  	
			},
			onLoadSuccess:function(node, data){
				//alert($(obj).combotree("getValue"));
				if(expNode==undefined&&$(obj).attr("text")!=undefined){
					text = $(obj).attr("text");
					$(obj).combotree("setText",text);
				}
				if(expNode!=undefined){
					var en= $(this).tree("find",Number(expNode));					
					$(this).tree("expandTo",en.target);
					//$(this).tree("select",en.target);
					$(obj).combotree("setValue",en.id);
				}
			}
		});
	});	
}



/**
 * 机构搜索框
 * @param selector
 * @param text
 */
function loadBrchSearch(selector){
	//using("combogrid", function(){
		//if(required==undefined)required=false;
		if($(selector).is("input")){ grid=selector;
		}else grid=$(selector).find("#search_brch");
		
		$(grid).combogrid({   
			panelWidth: 580,
			mode: 'remote',
			multiple:false,
			//required : required,
            idField: 'brch_no',  
            textField: 'brch_name',
            url: '/search_brch',
           // onChange:onChange,
            columns: [[
                {field:'brch_name',title:'部门名称',width:150},
                {field:'brch_code',title:'8位机构号',width:70},      
                {field:'area_name',title:'地区',width:70},
                {field:'brch_path',title:'层级',width:310,
                	formatter: function(value,row,index){
                		s='<div class="pathinfo" title="'+value+'">'+value+'</div>';
                		return s;
                	}	
                }
                
            ]],  
            fitColumns: true,
            onLoadSuccess:function(data){
            	 if($(grid).attr("text")!=undefined){
            		 $(grid).combogrid("setText",$(grid).attr("text")); 
            	 }
            }
     });
	
     
	//});
}


/**
 * 地区搜索框
 * @param selector
 * @param text
 */
function loadAreaSearch(selector){
	//using("combogrid", function(){
		if($(selector).is("input")){ grid=selector;
		}else grid=$(selector).find("#search_area");
		
		
		$(grid).combogrid({  
			panelWidth: 580,
			mode: 'remote',
			multiple:false,
            idField: 'area_no',  
            textField: 'area_name',
            url: '/search_area',
            columns: [[
                {field:'area_name',title:'地区名称',width:150},
                {field:'area_no',title:'地区号',width:70},      
                {field:'gov_code',title:'行政区划',width:70},
                {field:'area_path',title:'层级',width:310,
                	formatter: function(value,row,index){
                		s='<div class="pathinfo" title="'+value+'">'+value+'</div>';
                		return s;
                	}	
                }
                
            ]],  
            fitColumns: true,
            onLoadSuccess:function(data){
            	 if($(grid).attr("text")!=undefined){
            		 $(grid).combogrid("setText",$(grid).attr("text")); 
            	 }
            }
     });
	
     
	//});
}



/**=============================================================================
 * FOR DATAGRID
 * 常用表格
 **=============================================================================*/
/**
 * 执行带参数查询
 * @param fmSelector   from选择器
 * @param dataSelector datagrid 选择器
 * @param callback     回调函数
 */
function datagridquery(fmSelector,dataSelector,callback){
    var fmdata=$(fmSelector).form2json();
    $(dataSelector).datagrid("options").url=query_url;
    $(dataSelector).datagrid("load",fmdata);
    callback();
    }
function datagridpost(urls,data,callback){
	$.post(urls,data,function(result){
        if (result.success) callback();    
        else $.messager.show({title: "错误",msg: result.msg}); 
	},'json');
}
function messagerpost(urls,data,callback){
	$.post(urls,data,function(result){
        if (result.success){$.messager.show({title: "成功",msg: result.msg}); callback();}     
        else $.messager.show({title: "错误",msg: result.msg}); 
	},'json');
}



/**=============================================================================
 * FOR SYSTEM
 * 通用调用js
 **=============================================================================*/

/**
 * @param remain 剩余时间
 */
function sys_staylogin(remain){
	var clock;
//	function sys_loginCountDown(){
//		remain=remain-1;
//		if(remain<0){clearInterval(clock);}
//		else {
//		  $("#sys_staylogin_msg").html(""+remain+"秒后将自动登出");
//		};
//	}

	if(Number(remain)<=80){
        
       var dia=$("<div style=\"width:240px;height:150px;padding:10px 10px;\"><span id=\"sys_staylogin_msg\"></span></div>");
       dia.dialog({
        	closed:false,resizable:true,modal:true,title:"安全登出",
        	onClose:function(){
        		$(dia).dialog("destroy");
        		clearInterval(clock);
        	},
			buttons:[
			{ text:'保持登录',plain:true,iconCls:'icon-ok',handler:function(){
				$.post("wel_stay");
				$(dia).dialog("close");
			}}
            ]
        });
       
		clock=setInterval(function(){
			remain=remain-1;
			if(remain<0){
				clearInterval(clock);
				window.location.replace("/sys/GO.jsp");
			}else {
			  $("#sys_staylogin_msg").html(""+remain+"秒后将自动登出");
			};
		}, 1000);
		
	}

}

function disableCopy(){
	    document.oncontextmenu=function(e){return false;};
		document.onselectstart=function(e){return false;};
		$("body").css("-moz-user-select","none");
}
