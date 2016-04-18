window.JingleChart = JChart = {
    version : '0.1',
    animationOptions : {
        linear : function (t){
            return t;
        },
        easeInQuad: function (t) {
            return t*t;
        },
        easeOutQuad: function (t) {
            return -1 *t*(t-2);
        },
        easeInOutQuad: function (t) {
            if ((t/=1/2) < 1) return 1/2*t*t;
            return -1/2 * ((--t)*(t-2) - 1);
        },
        easeInCubic: function (t) {
            return t*t*t;
        },
        easeOutCubic: function (t) {
            return 1*((t=t/1-1)*t*t + 1);
        },
        easeInOutCubic: function (t) {
            if ((t/=1/2) < 1) return 1/2*t*t*t;
            return 1/2*((t-=2)*t*t + 2);
        },
        easeInQuart: function (t) {
            return t*t*t*t;
        },
        easeOutQuart: function (t) {
            return -1 * ((t=t/1-1)*t*t*t - 1);
        },
        easeInOutQuart: function (t) {
            if ((t/=1/2) < 1) return 1/2*t*t*t*t;
            return -1/2 * ((t-=2)*t*t*t - 2);
        },
        easeInQuint: function (t) {
            return 1*(t/=1)*t*t*t*t;
        },
        easeOutQuint: function (t) {
            return 1*((t=t/1-1)*t*t*t*t + 1);
        },
        easeInOutQuint: function (t) {
            if ((t/=1/2) < 1) return 1/2*t*t*t*t*t;
            return 1/2*((t-=2)*t*t*t*t + 2);
        },
        easeInSine: function (t) {
            return -1 * Math.cos(t/1 * (Math.PI/2)) + 1;
        },
        easeOutSine: function (t) {
            return 1 * Math.sin(t/1 * (Math.PI/2));
        },
        easeInOutSine: function (t) {
            return -1/2 * (Math.cos(Math.PI*t/1) - 1);
        },
        easeInExpo: function (t) {
            return (t==0) ? 1 : 1 * Math.pow(2, 10 * (t/1 - 1));
        },
        easeOutExpo: function (t) {
            return (t==1) ? 1 : 1 * (-Math.pow(2, -10 * t/1) + 1);
        },
        easeInOutExpo: function (t) {
            if (t==0) return 0;
            if (t==1) return 1;
            if ((t/=1/2) < 1) return 1/2 * Math.pow(2, 10 * (t - 1));
            return 1/2 * (-Math.pow(2, -10 * --t) + 2);
        },
        easeInCirc: function (t) {
            if (t>=1) return t;
            return -1 * (Math.sqrt(1 - (t/=1)*t) - 1);
        },
        easeOutCirc: function (t) {
            return 1 * Math.sqrt(1 - (t=t/1-1)*t);
        },
        easeInOutCirc: function (t) {
            if ((t/=1/2) < 1) return -1/2 * (Math.sqrt(1 - t*t) - 1);
            return 1/2 * (Math.sqrt(1 - (t-=2)*t) + 1);
        },
        easeInElastic: function (t) {
            var s=1.70158;var p=0;var a=1;
            if (t==0) return 0;  if ((t/=1)==1) return 1;  if (!p) p=1*.3;
            if (a < Math.abs(1)) { a=1; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (1/a);
            return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*1-s)*(2*Math.PI)/p ));
        },
        easeOutElastic: function (t) {
            var s=1.70158;var p=0;var a=1;
            if (t==0) return 0;  if ((t/=1)==1) return 1;  if (!p) p=1*.3;
            if (a < Math.abs(1)) { a=1; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (1/a);
            return a*Math.pow(2,-10*t) * Math.sin( (t*1-s)*(2*Math.PI)/p ) + 1;
        },
        easeInOutElastic: function (t) {
            var s=1.70158;var p=0;var a=1;
            if (t==0) return 0;  if ((t/=1/2)==2) return 1;  if (!p) p=1*(.3*1.5);
            if (a < Math.abs(1)) { a=1; var s=p/4; }
            else var s = p/(2*Math.PI) * Math.asin (1/a);
            if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*1-s)*(2*Math.PI)/p ));
            return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*1-s)*(2*Math.PI)/p )*.5 + 1;
        },
        easeInBack: function (t) {
            var s = 1.70158;
            return 1*(t/=1)*t*((s+1)*t - s);
        },
        easeOutBack: function (t) {
            var s = 1.70158;
            return 1*((t=t/1-1)*t*((s+1)*t + s) + 1);
        },
        easeInOutBack: function (t) {
            var s = 1.70158;
            if ((t/=1/2) < 1) return 1/2*(t*t*(((s*=(1.525))+1)*t - s));
            return 1/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2);
        },
        easeInBounce: function (t) {
            return 1 - JChart.animationOptions.easeOutBounce (1-t);
        },
        easeOutBounce: function (t) {
            if ((t/=1) < (1/2.75)) {
                return 1*(7.5625*t*t);
            } else if (t < (2/2.75)) {
                return 1*(7.5625*(t-=(1.5/2.75))*t + .75);
            } else if (t < (2.5/2.75)) {
                return 1*(7.5625*(t-=(2.25/2.75))*t + .9375);
            } else {
                return 1*(7.5625*(t-=(2.625/2.75))*t + .984375);
            }
        },
        easeInOutBounce: function (t) {
            if (t < 1/2) return JChart.animationOptions.easeInBounce (t*2) * .5;
            return JChart.animationOptions.easeOutBounce (t*2-1) * .5 + 1*.5;
        }
    },
    /**
     * ͨ�õļ�ʱ������
     */
    requestAnimFrame : (function(){
        return window.requestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function(callback) {
                window.setTimeout(callback, 1000 / 60);
            };
    })(),
    isNumber : function(n){
        return !isNaN(parseFloat(n)) && isFinite(n);
    },
    isEqual : function(number1, number2, digits){
        digits = digits == undefined? 10: digits; // Ĭ�Ͼ���Ϊ10
        return number1.toFixed(digits) === number2.toFixed(digits);
    },
    /**
     * ȡ��Ч�����ڵ�ֵ
     * @param valueToCap
     * @param maxValue
     * @param minValue
     * @return {*}
     */
    capValue : function(valueToCap, maxValue, minValue){
        var value;
        if(this.isNumber(maxValue) && valueToCap > maxValue) {
            return maxValue;
        }
        if(this.isNumber(minValue) && valueToCap < minValue ){
            return minValue;
        }
        return valueToCap;
    },
    getDecimalPlaces : function(num){
        if (num%1!=0){
            return num.toString().split(".")[1].length
        }
        else{
            return 0;
        }
    },
    extend : function(target){
        var args = Array.prototype.slice.call(arguments,1);
        this.each(args,function(v,i){
            extend(target,v);
        });
        function extend(target,source){
            for(var key in source){
                var o = source[key];
                if(o instanceof Array){
                    target[key] = extend([], o);
                }else if(o instanceof Object){
                    target[key] = extend({},o);
                }else{
                    target[key] = o;
                }
            }
            return target;
        }
        return target;

    },
    clone : function(obj){
        var o;
        if (typeof obj == "object") {
            if (obj === null) {
                o = null;
            } else {
                if (obj instanceof Array) {
                    o = [];
                    for (var i = 0, len = obj.length; i < len; i++) {
                        o.push(this.clone(obj[i]));
                    }
                } else {
                    o = {};
                    for (var j in obj) {
                        o[j] = this.clone(obj[j]);
                    }
                }
            }
        } else {
            o = obj;
        }
        return o;
    },
    //ֻ��array��Ч
    each : function(array,fn,context){
        for(var i = 0,len=array.length;i<len;i++){
            var result = fn.call(context,array[i],i,array);
            if(result === true){
                continue;
            }else if(result === false){
                break;
            }
        }
    },
    getOffset : function(el){
    	var box = el.getBoundingClientRect(), 
		doc = el.ownerDocument, 
		body = doc.body, 
		html = doc.documentElement, 
		clientTop = html.clientTop || body.clientTop || 0, 
		clientLeft = html.clientLeft || body.clientLeft || 0, 
		top = box.top + (self.pageYOffset || html.scrollTop || body.scrollTop ) - clientTop, 
		left = box.left + (self.pageXOffset || html.scrollLeft || body.scrollLeft) - clientLeft 
		return { 'top': top, 'left': left }; 
    },
    /**
     * ����ɫ����ת����RGB��ɫ
     * @param color
     * @return {*}
     */
    hex2Rgb : function(color,alpha){
        var r, g, b;
        // ����ΪRGBģʽʱ��������ת����ֱ�ӽ�ȡ�ַ�������
        if( /rgb/.test(color) ){
            var arr = color.match( /\d+/g );
            r = parseInt( arr[0] );
            g = parseInt( arr[1] );
            b = parseInt( arr[2] );
        }else if( /#/.test(color) ){// ����Ϊʮ������ʱ��Ҫ������ת��
            var len = color.length;
            if( len === 7 ){// �Ǽ�дģʽ #0066cc
                r = parseInt( color.slice(1, 3), 16 );
                g = parseInt( color.slice(3, 5), 16 );
                b = parseInt( color.slice(5), 16 );
            }else if( len === 4 ){ // ��дģʽ #06c
                r = parseInt( color.charAt(1) + val.charAt(1), 16 );
                g = parseInt( color.charAt(2) + val.charAt(2), 16 );
                b = parseInt( color.charAt(3) + val.charAt(3), 16 );
            }
        }
        else{
            return color;
        }
        return 'rgba('+r+','+g+','+ b + ','+(alpha?alpha:1)+')';
    },
    tmpl : (function(){
        //Javascript micro templating by John Resig - source at http://ejohn.org/blog/javascript-micro-templating/
        var cache = {};
        function tmpl(str, data){
            // Figure out if we're getting a template, or if we need to
            // load the template - and be sure to cache the result.
            var fn = !/\W/.test(str) ?
                cache[str] = cache[str] ||
                    tmpl(document.getElementById(str).innerHTML) :

                // Generate a reusable function that will serve as a template
                // generator (and which will be cached).
                new Function("obj",
                    "var p=[],print=function(){p.push.apply(p,arguments);};" +

                        // Introduce the data as local variables using with(){}
                        "with(obj){p.push('" +

                        // Convert the template into pure JavaScript
                        str
                            .replace(/[\r\t\n]/g, " ")
                            .split("<%").join("\t")
                            .replace(/((^|%>)[^\t]*)'/g, "$1\r")
                            .replace(/\t=(.*?)%>/g, "',$1,'")
                            .split("\t").join("');")
                            .split("%>").join("p.push('")
                            .split("\r").join("\\'")
                        + "');}return p.join('');");

            // Provide some basic currying to the user
            return data ? fn( data ) : fn;
        };
        return tmpl;
    })()
};


;(function(_){
    function Bar(data,cfg){
        _.Scale.apply(this);
        var barRanges = [];//��¼��״ͼ��ռ�ݵ�λ��
        this._type_ = 'bar';
        var _this = this;
        this.data = data;//���е�����
        this.chartData = null;//ͼ��ǰչʾ������
        //������
        _.extend(this.config,{
            //�Ƿ���ʾbar�ı߿�
            showBarBorder : true,
            //bar�߿���
            barBorderWidth : 2,
            //ÿ����bar֮��ļ��
            barSpacing : 1,
            //ÿ����bar֮��ļ��
            barSetSpacing : 5,
            //�Ƿ���Զ����ݽ����϶�
            datasetGesture : false,
            //ÿ����ʾ����������
            datasetShowNumber : 12
        });
        /**
         * ��canvas domԪ���ϵ��¼� �磺click��touch
         */
        this.bindEvents = function(){
            this.on('_tap',function(x,y){tapHandler(x,y,'tap.bar')});
            //this.on('_doubleTap',function(x,y){tapHandler(x,y,'doubleTap.bar')});
            this.on('_longTap',function(x,y){tapHandler(x,y,'longTap.bar')});
            if(this.config.datasetGesture){
                this.bindDataGestureEvent();
            }
        }
        /**
         * ��ʼ������Ԫ��ֵ
         */
        this.draw = function(noAnim){
            if(this.config.datasetGesture && this.data.labels.length > _this.config.datasetShowNumber){
                this.chartData = this.sliceData(this.data,0,this.data.labels.length,this.config.datasetShowNumber);
            }else{
                this.chartData = this.data;
            }
            this.mergeFont(['scaleFont','textFont']);
            this.initScale(true);
            if(noAnim){
                this.drawScale();
                this.drawBars(1);
            }else{
                this.doAnim(this.drawScale,this.drawBars);
            }
        }
        this.redraw = function(data){
            this.chartData = data;
            this.clear();
            this.initScale(true);
            this.drawScale();
            this.drawBars(1);
        }

        this.drawBars = function(animPc){
            if(animPc >= 1)barRanges = [];
            var ctx = _this.ctx,cfg = _this.config,scale = _this.scaleData;
            _.each(_this.chartData.datasets,function(set,i){
                if(!cfg.showBarBorder)borderColor = null;
                _.each(set.data,function(d,j){
                    var x = scale.x + cfg.barSetSpacing + scale.xHop*j + scale.barWidth*i + cfg.barSpacing*i + cfg.barBorderWidth* i,
                        y = scale.y,width = scale.barWidth,height = animPc*_this.calcOffset(d,scale.yScaleValue,scale.yHop)+(cfg.barBorderWidth/2),
                        color = set.color,borderColor,bgColor = _.hex2Rgb(color,0.6);
                    if(cfg.showBarBorder){
                        //�߿���ɫĬ����������ɫһ��
                        borderColor = set.borderColor || color;
                    }
                    ctx.rect(x,y,width,-height,bgColor,borderColor,cfg.barBorderWidth);
                    if(animPc >= 1){
                        barRanges.push([x,x+width,y,y-height,j,i]);
                    }
                    cfg.showText && _this.drawText(d,x+width/2,y-height-3,[j,i]);

                });
            })
        }

        function tapHandler(x,y,event){
            var p = isInBarRange(x,y);
            if(p){
                _this.trigger(event,[_this.chartData.datasets[p[5]].data[p[4]],p[4],p[5]]);
            }
        }

        function isInBarRange(x,y){
            var range;
            _.each(barRanges,function(r){
                if(x >= r[0] && x <= r[1] && y >= r[3] && y <= r[2]){
                    range = r;
                    return false;
                }
            });
            return range;
        }
        //��ʼ������
        if(cfg)this.initial(cfg);
    }
    _.Bar = Bar;
})(JChart)
/**
 * �򵥵�Canvas�����࣬ʹcanvas֧��������jquery����ʽ������֧��CanvasRenderingContext2D���еķ��������ṩһЩ���õĹ��߷���
 */
;(function(_){
    function Chain(el){
        //��Ҫ���ؽ���ķ�������Щ���������ܽ��к�������ʽ����
        var needReturnValueFn = ['isPointInPath','measureText','getImageData','createLinearGradient','createPattern','createRadialGradient','isPointInPath'];
        function Canvas(){
            this.el = el = (typeof el === 'string') ? document.getElementById(el) : el;
            this.ctx = el.getContext('2d');
            this.width = el.width;
            this.height = el.height;
            addProtoFunc(this.ctx);
        }

        //���canvasԭ��������prototype��
        function addProtoFunc(ctx){
            for(var fn in CanvasRenderingContext2D.prototype){
                if(Canvas.prototype[fn])continue;
                Canvas.prototype[fn] = function(fn){
                    return function(){
                        var args = Array.prototype.slice.call(arguments);
                        var result = ctx[fn].apply(ctx,args);
                        if(needReturnValueFn.indexOf(fn)>-1){
                            return result;
                        }
                        return this;
                    }
                }(fn);
            }
        }

        Canvas.prototype = {
            /**
             *  ����context������ֵ
             * @param name ������
             * @param value ����ֵ
             * @return this
             */
            set : function(name,value){
                if(typeof name == 'object'){
                    for(var p in name){
                        this.ctx[p] && (this.ctx[p] = name[p]);
                    }
                }else{
                    this.ctx[name] && (this.ctx[name] = value);
                }
                return this;
            },
            /**
             * ��ȡcontext������ֵ
             * @param name ������
             * @return value ����ֵ
             */
            get : function(name){
                return this.ctx[name];
            },
            /**
             * context���
             * @param color �����ɫ
             * @return this
             */
            fill : function (color) {
                color && this.set('fillStyle', color);
                this.ctx.fill();
                return this;
            },
            /**
             * context���
             * @param color �����ɫ
             * @return this
             */
            stroke : function (color,width) {
                if (color) {
                    this.set('strokeStyle', color);
                    width && this.set('lineWidth',width);
                }
                this.ctx.stroke();
                return this;
            },
            /**
             * ����ı������ı��м���\n��ʵ�ֻ���
             * @param text
             * @param x
             * @param y
             * @param style
             * @return {*}
             */
            fillText : function(text,x,y,style){
                this.ctx.save();
                if(style && typeof style == 'object'){
                    for(var p in style){
                        this.set(p,style[p]);
                    }
                }
                var texts = (text+'').split('\n');
                if(texts.length > 1){
                    var fontsize = this.getFontSize();
                    for(var i=0;i<texts.length;i++){
                        this.ctx.fillText(texts[i],x,y+i*fontsize);
                    }
                }else{
                    this.ctx.fillText(text,x,y);
                }
                this.ctx.restore();
                return this;
            },
            /**
             * �������
             * @param x
             * @param y
             * @param w
             * @param h
             * @return this
             */
            clear : function (x, y, w, h) {
                var c = this.el;
                x = x || 0;
                y = y || 0;
                w = w || this.el.width;
                h = h || this.el.height;
                this.ctx.clearRect(x, y, w, h);
                //android4.1+�¾��������clear��Ч����������������loop������
                c.style.opacity = 0.99;
                setTimeout(function() {
                    c.style.opacity = 1;
                }, 1);
                return this;
            },
            /**
             * �������ô�С
             * @param width ��
             * @param height ��
             * @return this
             */
            resize : function (width, height) {
                this.el.width = width;
                this.el.height = height;
                this.width = width;
                this.height = height;
                return this;
            },
            /**
             * ����
             */
            line : function(x,y,x1,y1,stroke,strokeWidth){
                this.beginPath().moveTo(x,y).lineTo(x1,y1);
                stroke && this.stroke(stroke,strokeWidth);
                return this;
            },
            /**
             * ������
             * @param x
             * @param y
             * @param w
             * @param h
             * @param fill �����ɫ
             * @param stroke �����ɫ
             * @param strokeWidth ��߿��
             * @return this
             */
            rect : function (x, y, w, h, fill, stroke,strokeWidth) {
                this.ctx.beginPath();
                this.ctx.rect(x, y, w, h);
                this.ctx.closePath();
                fill && this.fill(fill);
                stroke && this.stroke(stroke,strokeWidth);
                return this;
            },
            /**
             * ��Բ��
             * @param x
             * @param y
             * @param r �뾶
             * @param fill �����ɫ
             * @param stroke �����ɫ
             * @param strokeWidth ��߿��
             * @return this
             */
            circle : function (x, y, r, fill, stroke,strokeWidth) {
                this.beginPath();
                this.ctx.arc(x, y, r, 0, 2 * Math.PI, false);
                this.closePath();
                fill && this.fill(fill);
                stroke && this.stroke(stroke,strokeWidth);
                return this;
            },
            /**
             * ������
             * @param x
             * @param y
             * @param r �뾶
             * @param start ��ʼ�Ƕ�
             * @param end �����Ƕ�
             * @param fill �����ɫ
             * @param stroke �����ɫ
             * @param strokeWidth ��߿��
             * @reutrn this
             */
            sector : function(x,y,r,start,end,fill,stroke,strokeWidth){
                this.beginPath()
                    .arc(x,y,r,start, end, false)
                    .lineTo(x,y)
                    .closePath();
                fill && this.fill(fill);
                stroke && this.stroke(stroke,strokeWidth);
                return this;
            },
            /**
             * ��������
             * @param x
             * @param y
             * @param ir �ڰ뾶
             * @param or ��뾶
             * @param start ��ʼ�Ƕ�
             * @param end �����Ƕ�
             * @param fill �����ɫ
             * @param stroke �����ɫ
             * @param strokeWidth ��߿��
             * @reutrn this
             */
            dountSector : function(x,y,ir,or,start,end,fill,stroke,strokeWidth){
                this.beginPath()
                    .arc(x,y,or,start, end, false)
                    .arc(x,y,ir,end,start,true)
                    .closePath();
                fill && this.fill(fill);
                stroke && this.stroke(stroke,strokeWidth);
                return this;
            },
            /**
             * ����һ��ͼƬ
             * @param img
             * @return this
             */
            image : function (img) {
                var _self = this;
                var args = Array.prototype.slice.call(arguments);
                var cb = function () {
                    _self.ctx.drawImage.apply(_self.ctx, args);
                };

                if (typeof img === 'string') {
                    args[0] = new Image();
                    args[0].onload = cb;
                    args[0].src = img;
                } else {
                    cb();
                }
                return this;
            },
            /**
             * ��ȡcanvas��ǰ�������С
             * @return {Boolean}
             */
            getFontSize : function(){
                var size = this.ctx.font.match(/\d+(?=px)/i);
                if(size){
                    size = parseInt(size[0]);
                }
                return size;
            }
        }
        return new Canvas();
    }

    _.Canvas = Chain;
})(JChart || window);
;(function(_){
    var Chart = function(){
        //��ǰ������״̬
        this.isAnimating = false;
        this.config = {
            width : 0,
            height : 0,
            bgColor : '#fff',
            //���Ȼ��̶�
            drawScaleFirst : true,
            //�ı���������
            showText : true,
            textFont : {},
            //�Ƿ�������
            animation : true,
            //����֡��
            animationSteps : 10,
            //��������
            animationEasing : "linear"
        }
        this.defaultFont = {
            family : 'Arial',
            size : 16,
            style : 'normal',
            color : '#5b5b5b',
            textAlign : 'center',
            textBaseline : 'middle'
        }
        this.events = {};
        /**
         * ��ʼ������
         */
        this.initial = function(cfg){
            //�ϲ����ò���
            if(typeof cfg == 'string'){
                this.config.id = cfg;
            }else{
                _.extend(this.config,cfg);
            }
            this.ctx = _.Canvas(this.config.id);
            var canvas = this.ctx.el;
            this.config.width && (canvas.width = this.config.width);
            this.config.height && (canvas.height = this.config.height);
            if(this.config.fit){
                //todo �Զ�����߶ȿ��
                //todo ��� ת�� �¼�
            }
            this.width = canvas.width;
            this.height = canvas.height;
            //����canvas������ɫ
            canvas.style.background = this.config.bgColor;

            //High pixel density displays - multiply the size of the canvas height/width by the device pixel ratio, then scale.
            //����豸Ϊ����Ĥ������canvas�����豸���رȷŴ����أ�Ȼ���ٵȱ���С
            if (window.devicePixelRatio) {
                canvas.style.width = this.width + "px";
                canvas.style.height = this.height + "px";
                canvas.height = this.height * window.devicePixelRatio;
                canvas.width = this.width * window.devicePixelRatio;
                this.ctx.scale(window.devicePixelRatio, window.devicePixelRatio);
            }
            this.bindTouchEvents();
            this.bindEvents();
        };
        this.resize = function(w,h){

        },
        /**
         * ��ջ������������û����ı���
         */
        this.clear = function(){
            this.ctx.clear();
        };
        /**
         * ����ˢ��ͼ��
         */
        this.refresh = function(config){
            this.update(null,config,true);
        };
        /**
         * ��������
         * @param data
         * @param config
         */
        this.load = function(data){
            this.update(data,null,false);
        }
        /**
         * ����ͼ��
         * @param data
         * @param config
         * @param animation
         */
        this.update = function(data,config,animation){
            config && _.extend(this.config,config);
            data && (this.data = data);
            this.dataOffset = 0;
            this.clear();
            this.draw(!animation);
        }
        this.mergeFont = function(key){
            if(key instanceof Array){
                _.each(key,function(v){
                    this.mergeFont(v);
                },this);
            }else{
                var of = this.config[key];
                var f = _.extend({},this.defaultFont,of);
                f.font = f.style + " " + f.size+"px " + f.family;
                f.fillStyle = f.color;
                this.config[key] = f;
            }
        }
        /**
         * ��������
         * @param drawScale ���Ŷ��� ����
         * @param drawData  ����ʽ���� ����
         * @param callback  ִ�гɹ��ص�����
         */
        this.doAnim = function(drawScale,drawData,callback){
            this.isAnimating = true;
            var config = this.config,_this = this;
            // 1/����֡��
            var animFrameAmount = (config.animation)? 1/ _.capValue(config.animationSteps,1000,1) : 1,
            //����Ч��
                easingFunction = _.animationOptions[config.animationEasing],
            //���������
                percentAnimComplete =(config.animation)? 0 : 1,
                _this = this;

            if (typeof drawScale !== "function") drawScale = function(){};
            _.requestAnimFrame.call(window,animLoop);
            function animLoop(){
                percentAnimComplete += animFrameAmount;
                animateFrame();
                if (percentAnimComplete <= 1){
                    _.requestAnimFrame.call(window,animLoop);
                }else{
                    _this.isAnimating = false;
                    callback && callback.call(_this);
                    _this.trigger('animationComplete');
                }
            };
            function animateFrame(){
                _this.clear();
                var animPercent =(config.animation)? _.capValue(easingFunction(percentAnimComplete),1,0) : 1;
                drawData.call(_this,animPercent);
                if(_this.config.drawScaleFirst){
                    drawScale.call(_this);
                    drawData.call(_this,animPercent);
                }else{
                    drawData.call(_this,animPercent);
                    drawScale.call(_this);
                }
            };
        }
        /**
         * ���׵��¼���
         */
        this.on = function(event,callback){
            this.events[event] = callback;
        };
        /**
         * �����¼�����
         * @param event �¼�����
         * @param data ����(������ʽ)
         */
        this.trigger = function(event,data){
            var callback = this.events[event];
            if(callback){
                return callback.apply(this,data);
            }else{
                return null;
            }
        };
        this.drawText = function(text,x,y,args,style){
            this.ctx.set(this.config.textFont);
            style && this.ctx.set(style);
            args = args ? [text].concat(args) : [text];
            var t = this.trigger('renderText',args);
            t = (t == null)?text:t;
            this.ctx.fillText(t,x,y);
        };
        //��chart���tap longTap doubleTap�¼�
        this.bindTouchEvents = function(){
            var touch = {},touchTimeout,longTapDelay = 750, longTapTimeout,now, delta,offset,
	            hasTouch = 'ontouchstart' in window,
				START_EV = hasTouch ? 'touchstart' : 'mousedown',
				MOVE_EV = hasTouch ? 'touchmove' : 'mousemove',
				END_EV = hasTouch ? 'touchend' : 'mouseup',
				CANCEL_EV = hasTouch ? 'touchcancel' : 'mouseup',
	            _this = this;

            this.ctx.el.addEventListener(START_EV,touchstart);
            this.ctx.el.addEventListener(MOVE_EV,touchmove);
            this.ctx.el.addEventListener(END_EV,touchend);
            this.ctx.el.addEventListener(CANCEL_EV,cancelAll);

            function touchstart(e){
                now = Date.now();
                e = e.touches ? e.touches[0] : e;
                delta = now - (touch.last || now);
                touchTimeout && clearTimeout(touchTimeout);
                offset = _.getOffset(_this.ctx.el);
                touch.x1 = e.pageX - offset.left;
                touch.y1 = e.pageY - offset.top;
                if (delta > 0 && delta <= 250) touch.isDoubleTap = true;
                touch.last = now;
                longTapTimeout = setTimeout(longTap, longTapDelay);
            }
            function touchmove(e){
                if(!touch.last)return;
                var ev = e.touches ? e.touches[0] : e;
                touch.x2 = ev.pageX - offset.left;
                touch.y2 = ev.pageY - offset.top;
                if (Math.abs(touch.x1 - touch.x2) > 15){
                    e.preventDefault();
                    cancelAll();
                }
            }
            function touchend(e){
                cancelLongTap();
                if ('last' in touch){
                    //tap�¼�������/˫�����ᴥ����0�ӳ٣������ڲ�ʹ��doubleTap�Ļ�����ʹ�ã����Ҫͬʱʹ��tap��doubleTap����ʹ��singleTap
                    _this.trigger('_tap',[touch.x1,touch.y1]);
                    _this.trigger('tap',[touch.x1,touch.y1]);
                    if (touch.isDoubleTap) {
                        cancelAll();
                        _this.trigger('_doubleTap',[touch.x1,touch.y1]);
                        _this.trigger('doubleTap',[touch.x1,touch.y1]);
                    }else {
                        touchTimeout = setTimeout(function(){
                            touchTimeout = null;
                            _this.trigger('_singleTap',[touch.x1,touch.y1]);
                            _this.trigger('singleTap',[touch.x1,touch.y1]);
                            touch = {};
                        }, 250)

                    }
                };
            }

            function longTap() {
                longTapTimeout = null;
                if (touch.last) {
                    _this.trigger('_longTap',[touch.x1,touch.y1]);
                    _this.trigger('longTap',[touch.x1,touch.y1]);
                    touch = {};
                }
            }

            function cancelLongTap() {
                if (longTapTimeout) clearTimeout(longTapTimeout);
                longTapTimeout = null;
            }

            function cancelAll() {
                if (touchTimeout) clearTimeout(touchTimeout);
                if (longTapTimeout) clearTimeout(longTapTimeout);
                touchTimeout = longTapTimeout = null;
                touch = {};
            }
        }
    }
    _.Chart = Chart;
})(JChart);
;(function(_){
    function Line(data,cfg){
      	_.Scale.apply(this);
        var pointRanges = [];//��¼�ߵĽڵ�λ�� (for click �¼�)
        this._type_ = 'line';
        this.data = data;
        this.chartData = null;
        var _this = this;
        _.extend(this.config,{
            //ƽ������
            smooth : true,
            //�Ƿ���ʾ�ߵ����ӵ�
            showPoint : true,
            //����Բ��뾶
            pointRadius : 4,
            //���ӵ�ı߿���
            pointBorderWidth : 2,
            //���ӵ�ĵ����Χ(������ָ����)
            pointClickBounds : 20,
            //�����ߵĿ��
            lineWidth : 2,
            //�Ƿ����Ϊ���ͼ
            fill : true,
            //�Ƿ���Զ����ݽ����϶�
            datasetGesture : false,
            //ÿ����ʾ����������
            datasetShowNumber : 12
        });
        /**
         * ��canvas domԪ���ϵ��¼� �磺click��touch
         */
        this.bindEvents = function(){
            //this.ctx.canvas.addEventListener('click',tapHandler);
            this.on('_tap',tapHandler);
            if(this.config.datasetGesture){
                this.bindDataGestureEvent();
            }
        }
        /**
         * ��ʼ������Ԫ��ֵ
         */
        this.draw = function(noAnim){
            this.mergeFont(['textFont','scaleFont']);
            if(this.config.datasetGesture && this.data.labels.length > _this.config.datasetShowNumber){
                this.chartData = this.sliceData(this.data,0,this.data.labels.length,this.config.datasetShowNumber);
            }else{
                this.chartData = this.data;
            }
            _this.initScale(true);
            if(noAnim){
                this.drawScale();
                this.drawLines(1);
            }else{
                this.doAnim(this.drawScale,this.drawLines);
            }
        }
        this.redraw = function(data){
            this.chartData = data;
            this.clear();
            this.initScale(true);
            this.drawScale();
            this.drawLines(1);
        }
        this.drawLines = function(animPc){
            if(animPc >= 1)pointRanges = [];
            var ctx = _this.ctx,cfg = _this.config,dataset = _this.chartData.datasets,scale = _this.scaleData;
            _.each(dataset,function(set,i){
                //��������
                ctx.beginPath().moveTo(scale.x, yPos(i,0));
                _.each(set.data,function(d,j){
                    var pointX = xPos(j),pointY = yPos(i,j);
                    if (cfg.smooth){//����������
                        ctx.bezierCurveTo(xPos(j-0.5),yPos(i,j-1),xPos(j-0.5),pointY,pointX,pointY);
                    }else{
                        ctx.lineTo(pointX,pointY);
                    }
                    if(animPc >= 1){
                        pointRanges.push([pointX,pointY,j,i]);
                    }
                });
                ctx.stroke(set.color,cfg.lineWidth);
                //�������
                cfg.fill ? ctx.lineTo(scale.x + (scale.xHop*(set.data.length-1)),scale.y).lineTo(scale.x,scale.y).closePath()
                    .fill(set.fillColor?set.fillColor : _.hex2Rgb(set.color,0.6)) : ctx.closePath();

                //�����Լ������ı�
                _.each(set.data,function(d,k){
                    var x = xPos(k),y = yPos(i,k);
                    cfg.showPoint && _this.drawPoint(x,y,set);
                    cfg.showText && _this.drawText(d,x,y-3,[k,i]);
                });
            });

            function yPos(i,j){
                return scale.y - animPc*(_this.calcOffset(dataset[i].data[j],scale.yScaleValue,scale.yHop));
            }
            function xPos(i){
                return scale.x + (scale.xHop * i);
            }
        }
        function tapHandler(x,y){
            var p = isInPointRange(x,y);
            if(p){
                _this.trigger('tap.point',[_this.chartData.datasets[p[3]].data[p[2]],p[2],p[3]]);
            }
        }

        function isInPointRange(x,y){
            var point,pb = _this.config.pointClickBounds;
            _.each(pointRanges,function(p){
                if(x >= p[0] - pb && x <= p[0] + pb && y >= p[1]-pb && y <= p[1] + pb){
                    point = p;
                    return false;
                }
            });
            return point;
        }

        //��ʼ������
        if(cfg)this.initial(cfg);
    }
    _.Line = Line;
})(JChart)
;(function(_){
    function Pie(data,cfg){
        _.Chart.apply(this);
        var angleRanges;//��¼ÿ�����ε���ʼ�Ƕȣ���0��ʼ��
        var _this = this;
        this.data = data;
        var radius,totalData = 0,startAngle = 0,rotateAngle = 0,currentOutIndex = -1,origin = {};
        //����������
        _.extend(this.config,{
            //border
            showBorder : true,
            //border color
            borderColor : "#fff",
            //border width
            borderWidth : 2,
            //��ʼ�Ƕ�,Ĭ��Ϊ12���ӷ���
            startAngle : -Math.PI/2,
            //��ת���Σ�ʹ�����߶�Ӧ�ĽǶ�
            rotateAngle : Math.PI/2,
            //���ε�������
            pullOutDistance : 10,
            //�������Ĭ�ϴ������¼�����
            clickType : 'pullOut',// pullOut||rotate
            //����ͼ
            isDount : false,
            dountRadiusPercent :0.4,
            totalAngle : Math.PI*2,
            dountText : '',
            dountFont : {
                size : 20,
                style : 600,
                color : '#3498DB'
            }
        });
        /**
         * ����������ε���ʼ�Ƕ�
         * @param data
         */
        function calcAngel(){
            var angle = 0;
            angleRanges = [];
            _.each(_this.data,function(d,i){
                var start = angle;
                var percent = d.value/totalData;
                angle = angle + percent * _this.config.totalAngle;
                var end = angle;
                angleRanges.push([start,end,d,i,percent]);
            })
        }

        function animRotate(percent){
            drawPie(percent,'rotate');
        }

        /**
         *  ����ͼ
         * @param percent ��������
         */
        function drawPie (percent,type){
            _this.clear();
            percent = _this.config.animation ? percent : 1;
            _.each(angleRanges,function(a){
                drawSector(a,percent,type);
            });
            _this.config.isDount && _this.config.dountText && drawDountText();
        }

        /**
         * ����������ʵ�ĽǶ�
         */
        function calcSectorAngle(r,p,t){
            var start = r[0],end = r[1];
            if(t == 'rotate'){
                //��ת
                start = start + startAngle + rotateAngle*p;
                end = end + startAngle + rotateAngle*p;
            }else{
                //Ĭ�϶���
                start = start*p + startAngle;
                end = end*p + startAngle
            }
            return {
                start : start,
                end : end
            }
        }

        /**
         * ������
         * @param i
         * @param animPercent
         */
        function drawSector(r,p,t){
            var x = origin.x,y = origin.y,cfg = _this.config,
                index = r[3],angle = calcSectorAngle(r,p,t);

            if(index == currentOutIndex){
                var midAngle = (r[0] + r[1])/2+startAngle;
                x += Math.cos(midAngle) * cfg.pullOutDistance;
                y += Math.sin(midAngle) * cfg.pullOutDistance;
            }
            if(cfg.isDount){
                _this.ctx.dountSector(x,y,radius*cfg.dountRadiusPercent,radius,angle.start,angle.end,_this.data[index].color);
            }else{
                _this.ctx.sector(x,y,radius,angle.start,angle.end,_this.data[index].color);
            }
            cfg.showBorder && _this.ctx.stroke(cfg.borderColor,cfg.borderWidth);
            cfg.showText && drawText(x,y,radius,angle.start,angle.end,r);
        }

        function drawText(x,y,r,start,end,data){
            //�����ı�λ��
            var middAngle = (start+end)/ 2, dis = r/ 2,
                percent = data[4],d = data[2];
            if(_this.config.isDount){
                dis = r/2 + r*_this.config.dountRadiusPercent/2;
            }
            percent = (percent * 100).toFixed(1)+'%';
            var xaxis = Math.cos(middAngle) * dis + x, yaxis = Math.sin(middAngle) * dis + y;
            _this.drawText(percent,xaxis,yaxis,[d,data[3],data[4]]);
        }
        function drawDountText(){
            _this.ctx.fillText(_this.config.dountText,origin.x,origin.y,_this.config.dountFont);
        }
        /**
         * ��canvas domԪ���ϵ��¼� �磺click��touch
         */
        this.bindEvents = function(){
            this.on('_tap',function(x,y){tapHandler(x,y,'tap.pie')});
            //��ʱ�ر�doubleTap�¼�
            //this.on('_doubleTap',function(x,y){tapHandler(x,y,'doubleTap.pie')});
            this.on('_longTap',function(x,y){tapHandler(x,y,'longTap.pie')});
            //���һ��Ĭ�ϵ���¼�
            this.on('tap.pie',function(){return true;})
        }

        function tapHandler(x,y,event){
            var type = _this.config.clickType;
            var angle = isInSegment(x,y);
            if(angle){
                if(event == 'tap.pie'){//����һЩĬ����Ϊ
                    if(!_this.trigger(event,[angle[2],angle[3]]))return;
                    if(type == 'rotate'){
                        _this.rotate(angle[3]);
                    }else if(type == 'pullOut'){
                        _this.toggleSegment(angle[3]);
                    }
                }else{
                    _this.trigger(type,[angle[2],angle[3]]);
                }
            }
        }

        function isInSegment(offsetX,offsetY){
            var angle;
            var x = offsetX - origin.x;
            var y = offsetY - origin.y;
            //����Բ��ľ���
            var dfc = Math.sqrt( Math.pow( Math.abs(x), 2 ) + Math.pow( Math.abs(y), 2 ) );
            var isInPie = (dfc <= radius);
            if(isInPie && _this.config.isDount){//�ų�dountͼ������
                isInPie = (dfc >= radius*_this.config.dountRadiusPercent);
            }
            if(!isInPie)return;
            var clickAngle = Math.atan2(y, x)-startAngle;
            if ( clickAngle < 0 ) clickAngle += 2 * Math.PI;
            if(clickAngle > 2 * Math.PI) clickAngle -= 2 * Math.PI;

            _.each(angleRanges,function(a){
                if(clickAngle >= a[0] && clickAngle < a[1]){
                    angle = a;
                    return false;
                }
            });
            return angle;
        }

        /**
         * ����/�������ο�
         * @param i ��������
         */
        this.toggleSegment = function(i){
            if(i == currentOutIndex){
                this.pushIn();
            }else{
                this.pullOut(i);
            }
        }
        /**
         * �������е��������ο�
         */
        this.pushIn = function(){
            currentOutIndex = -1;
            drawPie(1);
            this.trigger('pushIn');
        }
        /**
         * ����ָ�������ο�
         * @param i ��������
         */
        this.pullOut = function(i){
            if ( currentOutIndex == i ) return;
            currentOutIndex = i;
            drawPie(1);
            this.trigger('pullOut',[_this.data[i],i,angleRanges[i][4]]);
        }
        /**
         * ��ת���ο������ָ��6���ӷ���
         * @param i ��������
         */
        this.rotate = function(i){
            if(_this.isAnimating)return;
            var middAngle = (angleRanges[i][0] + angleRanges[i][1]) / 2 + startAngle;
            var newRotateAngle = _this.config.rotateAngle-middAngle;
            if(_.isEqual(newRotateAngle,0))return;
            this.pushIn();
            rotateAngle = newRotateAngle;
            this.doAnim(null,animRotate,function(){
                startAngle += rotateAngle;
                _this.trigger('rotate',[_this.data[i],i,angleRanges[i][4]]);
            });
        }
        this.setDountText = function(text){
            _this.config.dountText = text;
            drawPie(1);
        }
        /**
         * ��ͼ
         */
        this.draw = function(noAnim){
            this.mergeFont(['textFont','dountFont']);
            calcOrigin();
            totalData = 0;
            currentOutIndex = -1;
            _.each(_this.data,function(d){
                totalData += d.value;
            });
            calcAngel();
            startAngle = _this.config.startAngle;
            if(noAnim){
                drawPie(1);
            }else{
                this.doAnim(null,drawPie);
            }
        }
        //����ԭ��λ�ü��뾶
        function calcOrigin(){
            if(_this.config.totalAngle == Math.PI){
                origin = {
                    x : _this.width/2,
                    y : _this.height - 20
                }
                radius = Math.min(origin.x,origin.y) - 10;
            }else{
                origin = {x:_this.width/2,y:_this.height/2};
                radius = Math.min(origin.x,origin.y) - 10;
            }
        }
        //��ʼ������
        if(cfg)this.initial(cfg);
    }
    _.Pie = Pie;
}(JChart));

  ;(function(_){
    function Polar(data,cfg){
    	_.Scale.apply(this);
        var _this = this;
        this.data = this.chartData = data;
  		//������
        _.extend(this.config,{
            drawScaleFirst : false,
            //�Ƿ���ʾ�̶��ı�����
            showScaleLabelBackdrop : true,
            //�̶ȱ�����ɫ
            scaleBackdropColor : "rgba(255,255,255,0.75)",
            //�̶�padding-top bottom
            scaleBackdropPaddingY : 2,
            //�̶�padding-left right
            scaleBackdropPaddingX : 2,
            //�Ƿ���ʾ�Ƕȷָ���
            showAngleLine : true,
            //�ָ�����ɫ
            angleLineColor : "rgba(0,0,0,.1)",
            showBorder : true,
            borderColor : '#fff',
            borderWidth : 1,
            textFont : {
                size : 16,
                color : '#666',
                textBaseline : 'middle'
            },
            //�ָ��߿��
            angleLineWidth : 1,
            //�Ƿ�����ת����
            animateRotate : true,
            //�Ƿ������Ŷ���
            animateScale : false
        });
        /**
         * ��canvas domԪ���ϵ��¼�
         */
        this.bindEvents = function(){
            this.on('_tap',tapHandler);
        }
        
        this.draw = function(noAnim){
            this.mergeFont(['scaleFont','textFont']);
        	this.initScale();
            if(noAnim){
                this.drawAllSegments(1);
                this.drawScale();
            }
        	this.doAnim(this.drawScale,this.drawAllSegments);
        }
        function tapHandler(x,y){
            var i = isInSegment(x,y);
            if(i>-1){
                this.trigger('tap.pie',[this.data[i],i]);
            }
        }
        this.calcDrawingSizes = function(){
            var maxSize = Math.min(this.width,this.height)/2,
                cfg = this.config,size = cfg.scaleFont.size,lh = size*2;

            maxSize -= Math.max(size*0.5,cfg.scaleLineWidth*0.5);
            if (cfg.showScaleLabelBackdrop){
                lh += (2 * cfg.scaleBackdropPaddingY);
                maxSize -= cfg.scaleBackdropPaddingY*1.5;
            }
            this.scaleData.yHeight = maxSize - 10;
            this.scaleData.yLabelHeight = lh;
        }
        
        this.drawScale = function(){
        	var cfg = this.config,scale = this.scaleData,x = this.width/2, y = this.height/2
                size = cfg.scaleFont.size,px = cfg.scaleBackdropPaddingX,py = cfg.scaleBackdropPaddingY;
            this.ctx.save().translate(x,y);

            //��ԲȦ
            for (var i=0; i<scale.yScaleValue.step; i++){
                var hop = scale.yHop * (i + 1);
                cfg.showGridLine && this.ctx.circle(0, 0, hop,false,cfg.gridLineColor,cfg.gridLineWidth);
                if (cfg.showScaleLabel){
                    var label =  scale.yScaleValue.labels[i];
                    if (cfg.showScaleLabelBackdrop){
                        var textWidth = this.ctx.measureText(label).width;
                        this.ctx.rect(
                            Math.round(- textWidth/2 - px),     //X
                            Math.round(- hop - size/2 - py),//Y
                            Math.round(textWidth + px*2), //Width
                            Math.round(size + py*2), //Height
                            cfg.scaleBackdropColor
                        );
                    }
                    this.ctx.fillText(label,0,-hop,cfg.scaleFont);
                }
            }
            //���Ƕȷָ���
            var len = this.data.labels.length,rotateAngle = (2*Math.PI)/len;
            this.ctx.rotate(-Math.PI/2-rotateAngle);
            _.each(this.data.labels,function(label,i){
                this.ctx.rotate(rotateAngle);
                if(cfg.showAngleLine){
                this.ctx.line(0,0,scale.yHeight,0,cfg.angleLineColor,cfg.angleLineWidth);
                }
                if(cfg.showLabel){
                    this.ctx.save().translate(scale.yHeight+10,0).rotate(Math.PI/2 - rotateAngle*i);
                    this.ctx.fillText(label,0,0,cfg.textFont);
                    this.ctx.restore();
                }
            },this);
            this.ctx.restore();
        }

        this.drawAllSegments = function(animPc){
            var startAngle = -Math.PI/2,angleStep = (Math.PI*2)/this.data.datasets.length,
               scaleAnim = 1,rotateAnim = 1,
                scale = this.scaleData,cfg = this.config,
                borderColor,borderWidth;
            if (cfg.animation) {
                cfg.animateScale && (scaleAnim = animPc);
                cfg.animateRotate && (rotateAnim = animPc);
            }
            if(cfg.showBorder){
                borderColor = cfg.borderColor;
                borderWidth = cfg.borderWidth;
            }
            _.each(this.data.datasets,function(d){
                var r = scaleAnim * this.calcOffset(d.value,scale.yScaleValue,scale.yHop);
                this.ctx.sector(this.width/2,this.height/2,r,startAngle, startAngle + rotateAnim*angleStep, _.hex2Rgb(d.color,.6),borderColor,borderWidth);
                startAngle += rotateAnim*angleStep;
            },this);
        }

        this.getValueBounds = function(data){
            var upperValue = Number.MIN_VALUE;
            var lowerValue = Number.MAX_VALUE;
            for (var i=0; i<data.length; i++){
                if (data[i].value > upperValue) {upperValue = data[i].value;}
                if (data[i].value < lowerValue) {lowerValue = data[i].value;}
            };
            var yh = this.scaleData.yHeight;
            var lh = this.scaleData.yLabelHeight;
            var maxSteps = Math.floor((yh/(lh*0.66)));
            var minSteps = Math.floor((yh/lh*0.5));
            return {
                maxValue : upperValue,
                minValue : lowerValue,
                maxSteps : maxSteps,
                minSteps : minSteps
            };
        }

        function isInSegment(x,y){
            var startAngle = -Math.PI/2,
                angleStep = (Math.PI*2)/this.data.length;
            var x = x-_this.width/ 2,y = y-_this.height/2;
            //����Բ��ľ���
            var dfc = Math.sqrt( Math.pow( Math.abs(x), 2 ) + Math.pow( Math.abs(y), 2 ) );
            var isInPie = (dfc <= _this.scaleData.yHeight);
            if(!isInPie)return -1;
            var clickAngle = Math.atan2(y, x)-startAngle;
            if ( clickAngle < 0 ) clickAngle = 2 * Math.PI + clickAngle;
            if(clickAngle > 2 * Math.PI) clickAngle = clickAngle - 2 * Math.PI;
            return Math.floor(clickAngle/angleStep);
        }

        //��ʼ������
        if(cfg)this.initial(cfg);
  
    }
    _.Polar = Polar;
  })(JChart);
;(function (_) {
    function Radar(data, cfg) {
        _.Scale.apply(this);
        var pointRanges = [];//��¼�ߵĽڵ�λ�� (for click �¼�)
        var _this = this;
        this.data = this.chartData = data;
        //������
        _.extend(this.config, {
            drawScaleFirst : false,
            //�Ƿ���ʾ�̶��ı�����
            scaleShowLabelBackdrop:true,
            //�̶ȱ�����ɫ
            scaleBackdropColor:"rgba(255,255,255,0.75)",
            //�̶�padding-top bottom
            scaleBackdropPaddingY:2,
            //�̶�padding-left right
            scaleBackdropPaddingX:2,
            //ͼ����״,���� diamond��Բ�� circle
            graphShape:'circle',
            //�Ƿ���ʾ�Ƕȷָ���
            showAngleLine:true,
            //�Ƕȷָ�����ɫ
            angleLineColor:"rgba(0,0,0,.1)",
            //�Ƕȷָ��߿��
            angleLineWidth:1,
            //�Ƿ���ʾ�ߵ����ӵ�
            showPoint:true,
            //����Բ��뾶
            pointRadius:3,
            //���ӵ�ı߿���
            pointBorderWidth:1,
            //���ӵ�ĵ����Χ(������ָ����)
            pointClickBounds:20,
            //�����ߵĿ��
            lineWidth:2,
            //�Ƿ����Ϊ���ͼ
            fill:true,
            showScaleLabel:true,
            showText : false,
            gridLineColor:'rgb(0,0,0,.5)'
        });

        /**
         * ��canvas domԪ���ϵ��¼� �磺click��touch
         */
        this.bindEvents = function () {
            this.on('_tap', tapHandler);
        }

        this.draw = function (noAnim) {
            this.mergeFont(['scaleFont','textFont']);
            this.initScale();
            if (noAnim) {
                this.drawAllDataPoints(1);
                this.drawScale();
            } else {
                this.doAnim(this.drawScale, this.drawAllDataPoints);
            }
        }

        function tapHandler(x, y) {
            var p = isInPointRange(x,y);
            if(p){
                _this.trigger('tap.point',[_this.data.datasets[p[3]].data[p[2]],p[2],p[3]]);
            }
        }

        this.calcDrawingSizes = function () {
            var maxSize = (Math.min(this.width, this.height) / 2),
                cfg = this.config,
                labelHeight = cfg.scaleFont.size * 2;
            var labelLength = 0;
            _.each(_this.data.labels, function (label) {
                this.ctx.set(cfg.textFont);
                var w = this.ctx.measureText(label).width;
                if (w > labelLength) labelLength = w;
            }, this);
            maxSize -= Math.max(labelLength, ((cfg.textFont.size/2) * 1.5));
            maxSize -= cfg.textFont.size;
            maxSize = _.capValue(maxSize, null, 0);
            this.scaleData.yHeight = maxSize;
            this.scaleData.yLabelHeight = labelHeight;
        }

        this.drawScale = function () {
            var ctx = this.ctx, cfg = this.config, scale = this.scaleData,scaleSize = cfg.scaleFont.size,textSize = cfg.textFont.size,
                dataLen = this.data.labels.length,px = cfg.scaleBackdropPaddingX,py = cfg.scaleBackdropPaddingY;
            //����ÿ�����ݵĽǶ�
            var rotationDegree = (2 * Math.PI) / dataLen;
            ctx.save().translate(this.width / 2, this.height / 2);
            //��ʾ�Ƕȷָ���
            if (cfg.showAngleLine) {
                var w = scale.yHeight - (scale.yHeight % scale.yHop);
                //��ÿ���Ƕȵķָ���
                for (var h = 0; h < dataLen; h++) {
                    ctx.rotate(rotationDegree).line(0,0,0,-w,cfg.angleLineColor,cfg.angleLineWidth);
                }
            }
            //���̶���
            for (var i = 0; i < scale.yScaleValue.step; i++) {
                var hop = scale.yHop * (i + 1);
                ctx.beginPath();
                if (cfg.showGridLine) {
                    ctx.set({strokeStyle : cfg.gridLineColor,lineWidth : cfg.gridLineWidth})
                    if (cfg.graphShape == 'diamond') {
                        ctx.moveTo(0, -hop);
                        for (var j = 0; j < dataLen; j++) {
                            ctx.rotate(rotationDegree).lineTo(0, -hop);
                        }
                    } else {
                        ctx.circle(0, 0, hop);
                    }
                    ctx.closePath().stroke();
                }
                //���̶�ֵ
                if (cfg.showScaleLabel) {
                    var label =  scale.yScaleValue.labels[i];
                    //��ʾ�̶�ֵ�ı���
                    if (cfg.showScaleLabelBackdrop){
                        var textWidth = this.ctx.measureText(label).width;
                        this.ctx.rect(
                            Math.round(-textWidth/2 - px),     //X
                            Math.round(-hop - scaleSize/2 - py),//Y
                            Math.round(textWidth + px*2), //Width
                            Math.round(scaleSize + py*2), //Height
                            cfg.scaleBackdropColor
                        );
                    }
                    this.ctx.fillText(label,0,-hop,cfg.scaleFont);
                }

            }

            //�����ı���ʽ
            this.ctx.set(cfg.textFont);
            //��ʾ���ݱ�ǩ�ı�
            for (var k = 0; k < dataLen; k++) {
                var opposite = Math.sin(rotationDegree * k) * (scale.yHeight + textSize);
                var adjacent = Math.cos(rotationDegree * k) * (scale.yHeight + textSize);
                var align;
                if (rotationDegree * k == Math.PI || rotationDegree * k == 0) {
                    align = 'center';
                } else if (rotationDegree * k > Math.PI) {
                    align = 'right';
                }else {
                    align = 'left';
                }
                this.ctx.fillText(this.data.labels[k], opposite, -adjacent,{textAlign:align});
            }
            ctx.restore();
        }

        this.drawAllDataPoints = function (animPc) {
            if (animPc >= 1)pointRanges = [];
            var dataLen = data.datasets[0].data.length,
                rotationDegree = (2 * Math.PI) / dataLen,
                scale = this.scaleData,
                ctx = this.ctx, cfg = this.config;
            ctx.save().translate(this.width / 2, this.height / 2);
            _.each(this.data.datasets, function (set, i) {
                ctx.beginPath().moveTo(0, getY(set.data[0]));
                //��������
                _.each(set.data, function (d, j) {
                    if (j == 0)return true;
                    ctx.rotate(rotationDegree).lineTo(0, getY(d));
                });
                ctx.closePath();

                cfg.fill && ctx.fill(set.fillColor||_.hex2Rgb(set.color,0.6));
                ctx.stroke(set.color,cfg.lineWidth);

                //�����ӵ�
                _.each(set.data,function(d,j){
                    var y = getY(d);
                    if (cfg.showPoint) {
                        ctx.rotate(rotationDegree).circle(0, y, cfg.pointRadius,set.pointColor,set.pointBorderColor,cfg.pointBorderWidth);
                    }
                    if(animPc >= 1){
                        var p = getPosition(y,j);
                        pointRanges.push([p[0],p[1],j,i]);
                    }
                });
                ctx.rotate(rotationDegree);

            }, this);
            ctx.restore();
            if(cfg.showText){
                drawText();
            }
            function getY(d){
                return -animPc * _this.calcOffset(d, scale.yScaleValue, scale.yHop);
            }
            function getPosition(radius,i){
                radius = Math.abs(radius);
                var x,y;
                var angel = -Math.PI/2 + i * rotationDegree;
                x = Math.cos(angel)*radius + _this.width/2;
                y = Math.sin(angel)*radius + _this.height/2;
                return [x,y];
            }
        }

        function drawText(){
            _.each(pointRanges,function(p){
                var y = p[1];
                if(y > _this.height/2){
                    y += 6;
                }
                _this.drawText(_this.data.datasets[p[3]].data[p[2]],p[0],y,[p[2],p[3]]);
            });

        }

        function isInPointRange(x,y){
            var point,pb = _this.config.pointClickBounds;
            _.each(pointRanges,function(p){
                if(x >= p[0] - pb && x <= p[0] + pb && y >= p[1]-pb && y <= p[1] + pb){
                    point = p;
                    return false;
                }
            });
            return point;
        }
        //��ʼ������
        if (cfg)this.initial(cfg);
    }
    _.Radar = Radar;
})(JChart);
;(function(_){
    /**
     * ������-�̶�ֵ
     * ������ʼ��XY���������
     * @constructor
     */
    function Scale(){
        var P_T = 5,//ͼ�����հ�
            P_R = 5,//ͼ���Ҳ�հ�
            P_Y = 20,//y�����հ�
            P_X = 10;//x���ı���x֮��ļ��
        _.Chart.apply(this);
        _.extend(this.config,{
            /**
             * @Object
             * Y��̶�ֵ��Ĭ��Ϊnull�����Զ����ɣ�Ҳ�����Լ�ָ��
             *   {
             *      step : 10,//�̶ȸ�������ѡ��
             *      stepValue : 10//ÿ�����̶���֮��Ĳ�ֵ����ѡ��
             *      start : 0//��ʼ�̶�ֵ,Ĭ��Ϊ0
             *   }
             */
            scale : null,
            //xy��̶��ߵ���ɫ
            scaleLineColor : "rgba(0,0,0,.3)",
            //�̶��߿��
            scaleLineWidth:1,
            //�Ƿ���ʾY��̶�ֵ
            showScaleLabel : true,
            //�Ƿ���ʾX��̶�ֵ
            showLabel : true,
            //�̶�ֵ��������
            scaleFont : {
                size:12,
                color : '#666'
            },
            textFont : {
                size : 14,
                textBaseline : 'bottom'
            },
            //�Ƿ���ʾ������
            showGridLine : true,
            //��������ɫ
            gridLineColor : "rgba(0,0,0,.1)",
            //�����߿��
            gridLineWidth : 1,
            //ˮƽ��{value : 50,color : #fff,width : 1}
            horizonLine : null

        });
        //����ƫ����-�Ѿ�ƫ��
        this.dataOffset = 0;
        this.scaleData = {
            x : 0,//Բ������
            y : 0,
            xHop : 0,//x����������
            yHop : 0,//y��ÿ���̶ȵĸ߶�
            xLength : 0,//x�᳤��
            yHeight : 0,//y��߶�
            yLabelHeight : 0,//y��̶��ı��߶�
            yScaleValue : null,//y��̶�ָ��
            labelRotate : 0,//x��label��ת�Ƕ�
            xLabelWidth : 0,//x��label���
            xLabelHeight : 0,//x��label���
            barWidth : 0//����ͼ���ӿ��
        }
        /**
         * ����X���ı���ȡ���ת�Ƕȼ�Y��߶�
         */
        this.calcDrawingSizes = function(){
            var maxSize = this.height,widestX = 0,scaleFontSize = this.config.scaleFont.size, xLabelWidth = 0,xLabelHeight = scaleFontSize,
                labelRotate = 0,dataLen = this.chartData.labels.length;
            //����X�ᣬ����������ݿ�ȳ����ܿ�ȣ���Ҫ��label������ת
            this.ctx.set(this.config.scaleFont);
            //�ҳ�����label
            _.each(this.chartData.labels,function(o){
                var w = this.ctx.measureText(o).width;
                widestX = (w > widestX)? w : widestX;
            },this);
            xLabelWidth = widestX;
            if (this.width/dataLen < widestX){
                labelRotate = 45;
                xLabelWidth = Math.cos(labelRotate*Math.PI/180) * widestX;
                xLabelHeight = Math.sin(labelRotate*Math.PI/180) * widestX ;
                if (this.width/dataLen < xLabelHeight){
                    labelRotate = 90;
                    xLabelWidth = scaleFontSize;
                    xLabelHeight = widestX;
                }
            }
            //��ȥx��label�ĸ߶�
            maxSize -= xLabelHeight;
            //��ȥx���ı���x��֮��ļ��
            maxSize -= P_X;
            //��Y�ᶥ����һ��հ�
            maxSize -= P_T;
            maxSize -= this.config.showText?scaleFontSize:0;
            //y��߶�
            this.scaleData.yHeight = maxSize;
            //y��̶ȸ߶�
            this.scaleData.yLabelHeight = scaleFontSize;
            //x���ı���ת�Ƕ�
            this.scaleData.labelRotate = labelRotate;
            //x���ı��Ŀ��
            this.scaleData.xLabelWidth = xLabelWidth;
            //x���ı��ĸ߶�
            this.scaleData.xLabelHeight = xLabelHeight;
        }

        /**
         * ����Y��̶ȵı߽缰�̶Ȳ���
         * @return {Object}
         */
        this.getValueBounds = function(dataset) {
            var upperValue = Number.MIN_VALUE;
            var lowerValue = Number.MAX_VALUE;
            _.each(dataset,function(o){
                _.each(o.data,function(obj){
                    if(obj > upperValue){upperValue = obj};
                    if (obj < lowerValue) { lowerValue = obj};
                });
            })
            var yh = this.scaleData.yHeight;
            var lh = this.scaleData.yLabelHeight;
            var maxSteps = Math.floor((yh/(lh*0.66)));
            var minSteps = Math.floor((yh/lh*0.5));

            return {
                maxValue : upperValue,
                minValue : lowerValue,
                maxSteps : maxSteps,
                minSteps : minSteps
            };
        }

        /**
         * ����Y��̶ȵĸ�������
         */
        this.calcYAxis = function(){
            var scale = this.config.scale;
            if (scale){
                scale.start = scale.start || 0;
                scale.labels = this.populateLabels(scale.step,scale.start,scale.stepValue);
            }else {
                var bounds = this.getValueBounds(this.chartData.datasets);
                scale = this.calcScale(this.scaleData.yHeight,bounds.maxSteps,bounds.minSteps,bounds.maxValue,bounds.minValue);
            }
            this.scaleData.yScaleValue = scale;
            this.scaleData.yHop = Math.floor(this.scaleData.yHeight/scale.step);
        }

        /**
         * ����X���ȣ�ÿ���������ȴ�С������ԭ��
         */
        this.calcXAxis = function(){
            var config = this.config,scale = this.scaleData,yLabelWidth = 0,xAxisLength,valueHop, x,y;
            if (config.showScaleLabel){
                //�ҳ�Y��̶ȵ����ֵ
                _.each(scale.yScaleValue.labels,function(o){
                    var w = this.ctx.measureText(o).width;
                    yLabelWidth = (w > yLabelWidth)? w : yLabelWidth;
                },this);
                yLabelWidth += P_Y;
            }
            //x��Ŀ��
            xAxisLength = this.width - yLabelWidth-P_R-(this.config.showText?this.config.textFont.size:0);

            if(this._type_ == 'bar'){//��������ͼ���ӿ�ȣ�����ͼx���ı�������ʾ����Ҫ���¼�����������
                valueHop = Math.floor(xAxisLength/this.chartData.labels.length);
                var len = this.chartData.datasets.length;
                scale.barWidth = (valueHop - config.gridLineWidth*2 - (config.barSetSpacing*2) - (config.barSpacing*len-1) - ((config.barBorderWidth/2)*len-1))/len;
            }else{
                valueHop = Math.floor(xAxisLength/(this.chartData.labels.length-1));
            }
            scale.x = yLabelWidth;
            scale.y = this.height - scale.xLabelHeight - P_X;
            scale.xWidth = xAxisLength;
            scale.xHop = valueHop;
        }

        this.drawScale = function(){
            var ctx = this.ctx,cfg = this.config,scale = this.scaleData,align;
            ctx.set({
                strokeStyle :  cfg.scaleLineColor,
                lineWidth : cfg.scaleLineWidth
            })
            //��X��
            ctx.line(scale.x-3, scale.y, scale.x+scale.xWidth, scale.y, true);
            //��Y��
            ctx.line(scale.x,scale.y+3, scale.x,scale.y-scale.yHeight, true);

            //��X��̶��ı�
            if (scale.labelRotate > 0){
                ctx.save();
                align = 'right';
            }else{
                align = 'center';
            }
            ctx.set({
                fillStyle : cfg.scaleFont.color,
                textAlign : align,
                textBaseline : 'hanging',
                strokeStyle : cfg.gridLineColor,
                lineWidth : cfg.gridLineWidth
            });
            _.each(this.chartData.labels,function(label,i){
                ctx.save();
                var cx = scale.x + i*scale.xHop,labelY = scale.y + P_X/ 2,
                    labelX = this._type_ == 'bar'?cx + scale.xHop/2 : cx;
                if (scale.labelRotate > 0){
                    ctx.translate(labelX,labelY).rotate(-(scale.labelRotate * (Math.PI/180))).fillText(label,0,0).restore();
                }else{
                    ctx.fillText(label, labelX,labelY);
                }
                //�������������
                if(cfg.showGridLine){
                    var x = (this._type_ == 'bar')?cx + scale.xHop : cx;
                    ctx.line(x, scale.y, x, scale.y-scale.yHeight,true);
                }
            },this);

            //������������
            ctx.set({textAlign:'right',textBaseline:'middle'});
            for (var j=0; j<scale.yScaleValue.step; j++){
                var y = scale.y - ((j+1) * scale.yHop);
                cfg.showGridLine && ctx.line(scale.x,y,scale.x + scale.xWidth,y, true);
                cfg.showScaleLabel && ctx.fillText(scale.yScaleValue.labels[j],scale.x-P_Y/2,y);
            }
            //����ˮƽ��
            if(cfg.horizonLine){
                var y = scale.y - this.calcOffset(cfg.horizonLine.value,scale.yScaleValue,scale.yHop);
                ctx.line(scale.x,y,scale.x + scale.xWidth,y, cfg.horizonLine.color || '#E74C3C',cfg.horizonLine.width||1);
            }

        }

        this.initScale = function(showX){
            this.calcDrawingSizes();
            this.calcYAxis();
            showX && this.calcXAxis();
        }
        this.drawPoint = function(x,y,d){
            //���ɫĬ��Ϊ��ɫ���߿���ɫĬ����������ɫһ��
            this.ctx.beginPath().circle(x,y,this.config.pointRadius,d.pointColor || '#fff',d.pointBorderColor || d.color,this.config.pointBorderWidth);
        }

		/**
         * ����������Ŀ̶�
         * @param drawingHeight
         * @param maxSteps
         * @param minSteps
         * @param maxValue
         * @param minValue
         */
        this.calcScale = function(drawingHeight,maxSteps,minSteps,maxValue,minValue){
            var min,max,range,stepValue,step,valueRange,rangeOrderOfMagnitude;

            valueRange = maxValue - minValue;

            rangeOrderOfMagnitude = calculateOrderOfMagnitude(valueRange);

            //min = Math.floor(minValue / (1 * Math.pow(10, rangeOrderOfMagnitude))) * Math.pow(10, rangeOrderOfMagnitude);
            min = 0;//�̶���ʼ��Ϊ0

            max = Math.ceil(maxValue / (1 * Math.pow(10, rangeOrderOfMagnitude))) * Math.pow(10, rangeOrderOfMagnitude);

            range = max - min;

            stepValue = Math.pow(10, rangeOrderOfMagnitude);

            step = Math.round(range / stepValue);

            //Compare number of steps to the max and min for that size graph, and add in half steps if need be.
            while(step < minSteps || step > maxSteps) {
                if (step < minSteps){
                    stepValue /= 2;
                    step = Math.round(range/stepValue);
                }
                else{
                    stepValue *=2;
                    step = Math.round(range/stepValue);
                }
            };
            var labels = this.populateLabels(step, min, stepValue);;
            return {
                step : step,
                stepValue : stepValue,
                start : min,
                labels : labels
            }
            function calculateOrderOfMagnitude(val){
                return Math.floor(Math.log(val) / Math.LN10);
            }
        }

        /**
         * ����̶�ֵ
         * @param labels
         * @param numberOfSteps
         * @param graphMin
         * @param stepValue
         */
        this.populateLabels = function (step, start, stepValue) {
            var labels = [];
            for (var i = 1; i < step + 1; i++) {
                if(!this.config.showScaleLabel){
                    labels.push('');
                    continue;
                }
                //С����λ����stepValue���С����һ��
                var value = (start + (stepValue * i)).toFixed(_.getDecimalPlaces(stepValue));
                var text = this.trigger('renderYLabel',[value]);
                text = text ? text : value;
                labels.push(text);
            }
            return labels;
        },
        this.calcOffset = function(val,scale,scaleHop){
            var outerValue = scale.step * scale.stepValue;
            var adjustedValue = val - scale.start;
            var scalingFactor = _.capValue(adjustedValue/outerValue,1,0);
            return (scaleHop*scale.step) * scalingFactor;
        },
        
        this.sliceData = function(data,offset,len,num){
            var newdata = _.clone(data);
            var min = offset,max = offset + num;
            if(max > len){
                min = len - num;
                max = len;
            }
            newdata.labels = newdata.labels.slice(min,max);
            _.each(newdata.datasets,function(d){
                d.data = d.data.slice(min,max)
            });
            return newdata;
        }
        this.bindDataGestureEvent = function(){
            var _this = this,
            	touchDistanceX,//��ָ����ƫ����
                startPosition,//������ʼλ�ü�¼
                currentOffset = 0,//��ǰһ�λ�����ƫ����
                dataNum = this.config.datasetShowNumber,//ÿ����ʾ����������
                gestureStarted,
                hasTouch = 'ontouchstart' in window,
				START_EV = hasTouch ? 'touchstart' : 'mousedown',
				MOVE_EV = hasTouch ? 'touchmove' : 'mousemove',
				END_EV = hasTouch ? 'touchend' : 'mouseup';

            this.ctx.el.addEventListener(START_EV,touchstart);
            this.ctx.el.addEventListener(MOVE_EV,touchmove);
            this.ctx.el.addEventListener(END_EV,touchend);

            function touchstart(e){
            	e = e.touches ? e.touches[0] : e;
                startPosition = {
                    x : e.pageX,
                    y : e.pageY
                }
                touchDistanceX = 0;
                gestureStarted = true;
            }
            function touchmove(e){
                if(!gestureStarted || !_this.config.datasetGesture)return;
                e = e.touches ? e.touches[0] : e;
                var x = e.pageX;
                var y = e.pageY;
                touchDistanceX = x - startPosition.x;
            	//ÿ����xHop������һ������
                var totalLen = _this.data.labels.length;//�����ܳ���
                var offset = _this.dataOffset - Math.floor(touchDistanceX/_this.scaleData.xHop);
                if(offset < 0 || offset == currentOffset||(offset+dataNum > totalLen))return;
                currentOffset = offset;
                console.log(offset);
                //����������ϵͳ���У����androidϵͳ��touchmove��bug
                setTimeout(function(){
                    _this.redraw(_this.sliceData(_this.data,offset,totalLen,dataNum));
                },0)
            }
            function touchend(event){
                gestureStarted = false;
                _this.dataOffset = currentOffset;
            }
        }
    }
    _.Scale = Scale;
})(JChart);