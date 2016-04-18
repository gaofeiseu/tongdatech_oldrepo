/**=============================================================================
 * FOR AJAX
 **=============================================================================*/
$.ajaxSetup({
traditional:true,//��ͳ��ʽ���л�����  ������a[]=1&&a[]=2
contentType:'application/x-www-form-urlencoded;  charset=UTF-8', //ȷ��ie�����Ĳ�����
complete:function(XMLHttpRequest,textStatus){
    //ͨ��XMLHttpRequestȡ����Ӧͷ��sessionstatus           
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
 * ��FORM��ֵת��ΪJSON
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

//������param1=x1&param2=x2&obj.name=name�Ĵ�ת��ΪJSON {param1:x1,param2:x2,obj:{name:name}}
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

//������param1=x1&param2=x2&obj.name=name�Ĵ�ת��ΪJSON {param1:x1,param2:x2,obj.name:name}}
function paramString2Param(serializedParams) {
	var obj = {};
	function evalThem(str) {
		var attributeName = str.split("=")[0];
		//attributeName=attributeName.replace(/\./g, "\\\\.");// obj.name ת��Ϊobj\\.name=
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
 * ��json�����ǰ׺
 * @param json json����
 * @param perfix ǰ׺
 * @returns
 */
$.Json2Perfix=function(json,perfix){
	var data=$.param(json);
	var data=data.replace(/(&?)(\w*)(=)/g, "$1"+perfix+"."+"$2$3");
	return paramString2Param(data);
};


/**
 * �޸�����easyui Panel �򿪾���ʱδ���� scroll�����
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
 * ͨ���첽form �ύ
 * @param fmSelector form ѡ����
 * @param urls       ִ��url 
 * @param callback   ִ�гɹ�ʱ�Ļص�����
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
					$.messager.show({title: "�ɹ�",msg: result.msg});
				} else {
					$.messager.show({title: "����",msg: result.msg});
				}
			}
		 });
		 }
 /**
 * form ���ù���
 * @param selector form ѡ����
 */
function resetform(selector){
		   $(selector).form("reset");
	   }
/**=============================================================================
 * FOR TREE
 * ���õ���
 **=============================================================================*/

/**
 * ���������
 * @param selector  jQuery ѡ����
 * @param root �����ڵ�
 * @param mode ��ģʽ
 * @param uiOnClickBrch ����¼�����
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
            onLoadSuccess:function(node,data){   //����date ��һ��array                                         
              if(data!=null&&data[0]!=null&&data[0].id==root){
              var dis=$(this).tree('find',root);    //���ҳ����ڵ��root����
              $(this).tree('expand',dis.target);    //չ�����ڵ�root
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
		onLoadSuccess:function(node,data){   //����date ��һ��array                                         
			if(data!=null&&data[0]!=null&&data[0].id==root){
				var dis=$(this).tree('find',root);    //���ҳ����ڵ��root����
				$(this).tree('expand',dis.target);    //չ�����ڵ�root
			}
		}
     });
}

/**
 * �������������
 * @param selector  jQuery ѡ����
 * @param treeRoot  �����ڵ�
 * @param expNode   չ�����ڵ�
 * value ʵ��ֵ
 * text  ��ʾ�ı�
 * e.g. <input value="1" text="һ"/>
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
//			 missingMessage:"��������ѡ��"
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
//			 missingMessage:"��������ѡ��"
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
 * ����������
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
                {field:'brch_name',title:'��������',width:150},
                {field:'brch_code',title:'8λ������',width:70},      
                {field:'area_name',title:'����',width:70},
                {field:'brch_path',title:'�㼶',width:310,
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
 * ����������
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
                {field:'area_name',title:'��������',width:150},
                {field:'area_no',title:'������',width:70},      
                {field:'gov_code',title:'��������',width:70},
                {field:'area_path',title:'�㼶',width:310,
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
 * ���ñ��
 **=============================================================================*/
/**
 * ִ�д�������ѯ
 * @param fmSelector   fromѡ����
 * @param dataSelector datagrid ѡ����
 * @param callback     �ص�����
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
        else $.messager.show({title: "����",msg: result.msg}); 
	},'json');
}
function messagerpost(urls,data,callback){
	$.post(urls,data,function(result){
        if (result.success){$.messager.show({title: "�ɹ�",msg: result.msg}); callback();}     
        else $.messager.show({title: "����",msg: result.msg}); 
	},'json');
}



/**=============================================================================
 * FOR SYSTEM
 * ͨ�õ���js
 **=============================================================================*/

/**
 * @param remain ʣ��ʱ��
 */
function sys_staylogin(remain){
	var clock;
//	function sys_loginCountDown(){
//		remain=remain-1;
//		if(remain<0){clearInterval(clock);}
//		else {
//		  $("#sys_staylogin_msg").html(""+remain+"����Զ��ǳ�");
//		};
//	}

	if(Number(remain)<=80){
        
       var dia=$("<div style=\"width:240px;height:150px;padding:10px 10px;\"><span id=\"sys_staylogin_msg\"></span></div>");
       dia.dialog({
        	closed:false,resizable:true,modal:true,title:"��ȫ�ǳ�",
        	onClose:function(){
        		$(dia).dialog("destroy");
        		clearInterval(clock);
        	},
			buttons:[
			{ text:'���ֵ�¼',plain:true,iconCls:'icon-ok',handler:function(){
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
			  $("#sys_staylogin_msg").html(""+remain+"����Զ��ǳ�");
			};
		}, 1000);
		
	}

}

function disableCopy(){
	    document.oncontextmenu=function(e){return false;};
		document.onselectstart=function(e){return false;};
		$("body").css("-moz-user-select","none");
}
