var num=[];
var tempSize=[];
var columnNum = [];


function inputTest(attrName, attrDesc, attrType,attrDown){
		var fals=true;
		for(i=0;i<num.length;i++){
			if(num[i]==attrName){
				generalInput(attrName, attrDesc, attrType,attrDown);
				fals=false;
				tempSize[i]+=1;
			}
		}
		if(fals){
			if(num.length<10){
				num.push(attrName);
				tempSize.push(1);
				generalInput(attrName, attrDesc, attrType,attrDown);
			}else{
				alert("属性个数不大于10个...");
			}
		}
	jQuery("#attrSearch").val("");
}

  function generalInput(attrName, attrDesc, attrType,attrDown){
    		//alert(columnNum.contains(attrName));
     	var len = jQuery("table#condition tr").length + 1;
     	var tr="";
     	dis_dm="disabled";
     	
     	if(attrDown!=''){
     		dis_dm="";
     	}
     
     	tr += "&nbsp;&nbsp;【<span title=\""+attrType+"\" class=\""+attrName+"\" id=\"desc"+len+"\">" + attrDesc + "</span>】&nbsp;&nbsp;";
    		tr += "<span id=\"sp"+len+"\">&nbsp;<select onchange=\"changeInput('"+len+"','"+attrType+"');\" name=\"types\" id=\"type"+len+"\" style=\"width: 80\"></select>&nbsp;&nbsp;";
			if(attrType=="2"||attrType=="3")
			{
				tr += "<input class=\"input\" id=\"colVal"+len+"\" type=\"text\" onkeyup='clearNoNum(this)' name=\"colVals\" style=\"width:150\"/>&nbsp;";
			}
			else
			{
    		tr += "<input class=\"input\" id=\"colVal"+len+"\" type=\"text\" name=\"colVals\" style=\"width:150\"/>&nbsp;";
			}
    		tr += "<a href=\"#\" onclick=\"delrule('trs"+len+"','"+attrName+"');\">删除</a></span>";
    		var tableName= document.getElementById("condition");
			var s = tableName.insertRow(-1);
			s.id = "trs"+len;
			var tdVal = s.insertCell(0);
			tdVal.innerHTML = tr;
			
			changeType(len, attrType);
			
     }

 
    function changeType(id,val){
			if(val=="1"){
				jQuery("#type"+id).append("<option value='='>等于</option>");
				jQuery("#type"+id).append("<option value='<>'>不等于</option>");
				jQuery("#type"+id).append("<option value='LIKE'>包含</option>");
				jQuery("#type"+id).append("<option value='IS NULL'>为空</option>");
				jQuery("#type"+id).append("<option value='IS NOT NULL'>不为空</option>");
				jQuery("#type"+id).append("<option value='NOT LIKE'>不包含</option>");
				
			} else if(val=="2" || val=="3"){
				jQuery("#type"+id).append("<option value='='>等于</option>");
				jQuery("#type"+id).append("<option value='>='>大于等于</option>");
				jQuery("#type"+id).append("<option value='>'>大于</option>");
				jQuery("#type"+id).append("<option value='<='>小于等于</option>");
				jQuery("#type"+id).append("<option value='<'>小于</option>");
				jQuery("#type"+id).append("<option value='IS NULL'>为空</option>");
				jQuery("#type"+id).append("<option value='IS NOT NULL'>不为空</option>");
				jQuery("#type"+id).append("<option value='BETWEEN'>介于</option>");
			} else if(val.toUpperCase()=="DATE"){
				jQuery("#type"+id).append("<option value='='>等于</option>");
				jQuery("#type"+id).append("<option value='<>'>不等于</option>");
				jQuery("#type"+id).append("<option value='>='>大于等于</option>");
				jQuery("#type"+id).append("<option value='>'>大于</option>");
				jQuery("#type"+id).append("<option value='<='>小于等于</option>");
				jQuery("#type"+id).append("<option value='<'>小于</option>");
				jQuery("#type"+id).append("<option value='IS NULL'>为空</option>");
				jQuery("#type"+id).append("<option value='IS NOT NULL'>不为空</option>");
				jQuery("#type"+id).append("<option value='BETWEEN'>介于</option>");
				jQuery("#colVal"+id).bind("click",function(e){
					calendar();
				});
			} else {
				jQuery("#type"+id).append("<option value='IN'>包含</option>");
				jQuery("#type"+id).append("<option value='NOT IN'>不包含</option>");
				jQuery("#type"+id).append("<option value='IS NULL'>为空</option>");
				jQuery("#type"+id).append("<option value='IS NOT NULL'>不为空</option>");
			}
     }
  	function changeInput(id, attrType){
    		jQuery("#colVal"+id).next("span").remove();
     	var val = jQuery("#type"+id).find("option:selected").val();
     	if(val.toUpperCase().indexOf("NULL") >= 0){
     		jQuery("#colVal"+id).val("");
     		jQuery("#colVal"+id).hide();
     	} else {
     		jQuery("#colVal"+id).show();
     	}
     	if(val.toUpperCase().indexOf("BETWEEN") >= 0){
     		jQuery("#colVal"+id).after("<span>和<input id=\"wcolVal"+id+"\" class=\"input\" type=\"text\" style=\"width:150;\"/>之间</span>&nbsp;");
     	}
     	if(attrType.toUpperCase()=="DATE"){
     		jQuery("#wcolVal"+id).bind("click",function(e){
					calendar();
				});
     	}
     }	

  	  function delrule(id,attrName){
         	jQuery("#"+id).remove();
         	for(i=0;i<num.length;i++){
		   			if(num[i]==attrName){
		   				if(tempSize[i] > 1){
		   					tempSize[i]-=1;
		   				}else{
		   					num.splice(i,1);
		   					tempSize.splice(i,1);
		   				}
		   			}
		   		}
	      	}	
	        function sub()
	        {
		     
	    	  var attrs=generalArray();
	    	  if(attrs==false)
	    	  {
		    	  return false;
		      }
	    	  
	    	  if(attrs.desc!=""&&attrs.sql!="")
	    	  {
	    		  window.opener.document.getElementById("sdfasdf").style.display="block";
	    		  window.opener.document.getElementById("queryString3Show").innerHTML=attrs.desc;
	    		  window.opener.document.getElementById("queryString3").value=attrs.sql;
					
	        	  window.close();
	    	  }
	    	  
		    }
  		function generalArray(){
        		columnNum = [];
       	 	var desc="";
		    	var sql="";
        		var attrs = {"desc":"", "sql":""};
		   		jQuery.each(jQuery("table#condition").find("tr").get(),function(index,th){
		   			var id = jQuery(this).attr("id").substring(3);
        			var res = genelSql(jQuery("#desc"+id).attr("class"), id);
        			if(res==false)
        				return false;
        			var desc1 = getDesc(id);
        		
		   			//attrs.segNum = index+1;
		   			//attrs.tableName = "tb_yx_indiv"; 
		   		//	attrs.attrName = jQuery("#desc"+id).attr("class");
		   			//attrs.attrDesc = jQuery("#desc"+id).text();
		   			attrs.desc = desc1;
		   			attrs.sql = res;

		   			if(desc!="")
		   			{
		   				desc+=desc1;
		   			}
		   			else
		   			{
		   				desc+="查询条件："+desc1+"";
			   		}
		   			
		   			if(sql!="")
		   			{
		   				sql+=" "+jQuery("#where").val()+" "+res;
		   			}
		   			else
		   			{
		   				sql+=res;
			   		}
		   			//columnNum.push(attrs);
        		});
        		attrs.desc=desc;
        		attrs.sql=sql;
        		return attrs;
        	}
  		function getDesc(id){
        		var tx = "";
        		var oper = jQuery("#type"+id).val();
        		if(jQuery("#code"+id).attr("checked") == true){
        			if(oper.toUpperCase().indexOf("IN") >= 0){
						//var txs = jQuery("#colVal"+id).val().split("$$");
						var txs = jQuery("#colVal"+id).find("option:selected").text().split("$$");
						var rvals = "";
						for(var i = 0; i < txs.length; i++){
							//rvals += "$$"+txs[i].split("-")[1];
							rvals += "$$"+txs[i].split("-")[0];
						}
						//tx = jQuery("#desc"+id).text() +" "+ jQuery("#type"+id).find("option:selected").text() +" " + rvals.substring(1);
						tx = jQuery("#desc"+id).text() +" "+ jQuery("#type"+id).find("option:selected").text() +" " + rvals.substring(2);
					} else if(oper.toUpperCase().indexOf("NULL") >= 0){
						tx = jQuery("#desc"+id).text() +" "+ jQuery("#type"+id).find("option:selected").text();
					}
				} else if(jQuery("#type"+id).val().toUpperCase().indexOf("BETWEEN") >= 0){
					tx = jQuery("#desc"+id).text() +" "+ jQuery("#type"+id).find("option:selected").text() +" "+ jQuery("#colVal"+id).val() +"和"+ jQuery("#wcolVal"+id).val()+"之间";
				} else {
					tx = jQuery("#desc"+id).text() +" "+ jQuery("#type"+id).find("option:selected").text() +" "+ jQuery("#colVal"+id).val();
				}
				return tx;
        	}
        	function genelSql(attrName,id){
        		var val = jQuery("#desc"+id).attr("title");
				var tx = jQuery("#colVal"+id).val();
				var oper = jQuery("#type"+id).val();
				if(oper.indexOf("=")>=0||oper.indexOf(">")>=0||oper.indexOf("<")>=0){
					if(tx=='') {
						alert('所有 = >= <= != 判断输入值不可为空');
						jQuery("#colVal"+id).focus().select();
						return false;
					}
				}
				if(oper.toUpperCase().indexOf("BETWEEN") >= 0){
					if(tx==''||jQuery("#wcolVal"+id).val()=='') {
						alert('所有介于输入值不可为空');
						jQuery("#colVal"+id).focus().select();
						return false;
					}
				}
				if(jQuery("#code"+id).attr("checked") == true){
					if(oper.toUpperCase().indexOf("IN") >= 0){
						var txs = tx.split("$$");
						var rvals = "";
						for(var i = 0; i < txs.length; i++){
							rvals += ",'"+txs[i].split("-")[0]+"'";
						}
						tx = oper + " (" + rvals.substring(1) + ") ";
					} else if(oper.toUpperCase().indexOf("NULL") >= 0){
						tx = oper;
					}
				} else {
					if(val=="1"){
						if(oper.toUpperCase().indexOf("LIKE") >= 0){
							tx = oper + " '%"+tx+"%' ";
						} else if(oper.toUpperCase().indexOf("NULL") >= 0){
							tx = oper;
						} else {
							tx = oper + " '"+tx+"' ";
						}
						if(oper.toUpperCase().indexOf("BETWEEN") >= 0){
							var ne = jQuery("#wcolVal"+id).val();
			            	tx += " AND " + " '"+ne+"' ";
						}
					} else if(val=="2" || val=="3"){
						if(oper.toUpperCase().indexOf("NULL") >= 0){
							tx = oper;
						} else {
							tx = oper + " "+tx+" ";
						}
						if(oper.toUpperCase().indexOf("BETWEEN") >= 0){
							var ne = jQuery("#wcolVal"+id).val();
			            	tx += " AND " +ne+" ";
						}
					} else if(val.toUpperCase()=="DATE"){
						if(oper.toUpperCase().indexOf("NULL") >= 0){
							tx = oper;
						} else {
							tx = oper + " TO_DATE('"+tx+"', 'yyyy-mm-dd') ";
						}
						if(oper.toUpperCase().indexOf("BETWEEN") >= 0){
							var ne = jQuery("#wcolVal"+id).val();
			            	tx += " AND " + " TO_DATE('"+ne+"', 'yyyy-mm-dd') ";
						}
					} else {
						if(oper.toUpperCase().indexOf("IN") >= 0){
							if(oper.toUpperCase().indexOf("NOT") >= 0)
								tx = " not like	"+"('%"+tx+"%')";
							else
								tx = " like	"+"('%"+tx+"%')";
						} else if(oper.toUpperCase().indexOf("NULL") >= 0){
							tx = oper;
						}
					}
				}
				return attrName.toUpperCase()+" "+tx;
        	}


