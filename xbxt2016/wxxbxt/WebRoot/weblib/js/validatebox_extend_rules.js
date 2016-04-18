    $.extend($.fn.validatebox.defaults.rules, { 
    	AjaxCheck: {  
            validator: function(value){         
             return false;
            },  
            message: 'AjaxCheck' 
        },
        Ajax:{
        	message:'��У����ʾ',
        	validator:function(value,param){
        	var postdate={};
        	postdate[param[1]]=value;
        	//if(param[2])postdate[param[2]]=$("#"+param[2]).val();
        	for(x in param){
        		if(x > 1){
        			if(param[x])postdate[param[x]]=getUIValue("#"+param[x]);
        		}
        	}
        	
        	var result=$.ajax({url:param[0],dataType:"json",data:postdate,async:false,cache:false,type:"post"}).responseText;
        	var json=eval("("+result+")");
        	this.message=json.msg;
        	return json.success;
        	}
        	
        },
        between: {  
            validator: function(value,param){
            if(isNaN(param[0]))param[0]=$(param[0]).val()||0;	
            if(isNaN(param[1]))param[1]=$(param[1]).val()||0;	
 
             return (value>=param[0]&&value<=param[1]);
            },  
            message: '�������{0}��{1}֮��'
        },
        bigThan: {
                validator: function(value,param){	    
                 return (value>=param[0]);
                },  
                message: '������ڵ���{0}'
        },
        lessThan: {
            validator: function(value,param){	    
                return (value<=param[0]);
               },  
               message: '����С�ڵ���{0}'
       },

        alpha:{     
            validator:function(value,param){     
                if (value){  
                    var reg =/^[a-zA-z\u00A1-\uFFFF]*$/;  
                    return reg.test(value);     
                } else {     
                    return true;     
                }     
            },     
            message:'ֻ��������ĸ.'     
        },     
        alphanum:{     
            validator:function(value,param){     
                if (value){ 
                    var reg = /^([a-zA-Z0-9])*$/;  
                    return reg.test(value);     
                } else {     
                    return true;     
                }     
            },     
            message:'ֻ��������ĸ������.'     
        },     
        positive_int:{     
            validator:function(value,param){     
                if (value){  
                    var reg =/^[0-9]*[1-9][0-9]*$/; 
                    return reg.test(value);     
                } else {     
                    return true;     
                }     
            },     
            message:'ֻ������������.'     
        },     
        numeric:{     
            validator:function(value){     
                if (value){
                   var reg = /^[0-9]*(\.[0-9]+)?$/;
                    return reg.test(value);     
                } else {     
                    return true;     
                }     
            },     
            message:'ֻ����������.'     
        },     
        chinese:{     
            validator:function(value,param){     
            if (value){  
                 var reg =/[^\u4E00-\u9FA5]/; 
                 return reg.test(value);     
            } else {     
                return true;     
            }     
        },     
        message:'ֻ����������'     
    }     
                 
    });  
      
    $.extend($.fn.validatebox.defaults.rules, {  
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '�����뺺��'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '�������벻����'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ���벻��ȷ'
    },

    mobile: {
        validator: function (value, param) {
            //return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(value);
            return /^13\d{9}|14[57]\d{8}|15[012356789]\d{8}|18[01256789]\d{8}$/.test(value);


        },
        message: '�ֻ����벻��ȷ'
    },
    /**
     ƥ���ʽ��
    11λ�ֻ�����
    3-4λ���ţ�7-8λֱ�����룬1��4λ�ֻ���
     �磺12345678901��1234-12345678-1234
    **/
    phone:{
        validator: function (value, param) {
        return /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/.test(value);
        },
        message: '�绰���벻��ȷ'
        
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '��¼����ֻ�����֡�Ӣ����ĸ�����ּ��»��ߡ�'
    },
    time:{
    	validator: function (value, param) {
    	return /^(([0-1]{1}[0-9]{1})|(2[0-3]{1})):[0-5]{1}[0-9]{1}$/.test(value);
    	},
        message: 'ʱ���ʽ����Ϊhh:mm'
    },
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '��������ĸ��������ɣ�����6λ'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '����������ַ���һ��'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '����������'
    },
    idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'��������ȷ�����֤����'
    },
    ip: {
            validator: function (value, param) {
                return /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/.test(value) ;
            },
            message:'��������ȷ��ip��ַ'
        }
        
    
});

 
/* ��������ĸ��������ɣ�����6λ */
var safePassword = function (value) {
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
};

var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
};

var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};

var getUIValue=function (Selector){
	var c = $(Selector);
	var cc = ['combobox','combotree','combogrid','datetimebox','datebox','combo'];
        var rs;
		for(var i=0; i<cc.length; i++){
			var type = cc[i];
			if (c.hasClass(type+'-f')){
				if (c[type]('options').multiple){
					rs=c[type]('getValues');
				} else {
					rs=c[type]('getValue');
				}
				return rs;
			}
		}
		rs=c.val();
		return rs;
	
};