document.addEventListener('deviceready', onDeviceReady, false);
function onDeviceReady(){
    navigator.splashscreen.hide();
    //ע����˰�ť
    document.addEventListener("backbutton", function (e) {
        if(J.hasMenuOpen){
            J.Menu.hide();
        }else if(J.hasPopupOpen){
            J.closePopup();
        }else{
            var sectionId = $('section.active').attr('id');
            if(sectionId == 'index_section'){
                J.confirm('��ʾ','�Ƿ��˳�����',function(){
                    navigator.app.exitApp();
                });
            }else{
                window.history.go(-1);
            }
        }
    }, false);
}
var App = (function(){
    var pages = {};
    var run = function(){
        $.each(pages,function(k,v){
            var sectionId = '#'+k+'_section';
            $('body').delegate(sectionId,'pageinit',function(){
                v.init && v.init.call(v);
            });
            $('body').delegate(sectionId,'pageshow',function(e,isBack){
                //ҳ����ص�ʱ�򶼻�ִ��
                v.show && v.show.call(v);
                //����ʱ��ִ��
                if(!isBack && v.load){
                    v.load.call(v);
                }
            });
        });
		J.Transition.add('flip','slideLeftOut','flipOut','slideRightOut','flipIn');
        Jingle.launch({
            showWelcome : true,
            welcomeSlideChange : function(i){
                switch(i){
                    case 0 :
                        J.anim('#welcome_jingle','welcome_jinlge',1000);
                        break;
                    case 1 :
                        $('#r_head,#r_body,#r_hand_left,#r_hand_right,#r_foot_left,#r_foot_right').hide()
                        J.anim($('#r_head').show(),'r_head',500,function(){
                            J.anim($('#r_body').show(),'r_body',1200,function(){
                                J.anim($('#r_hand_left').show(),'r_hand_l',500);
                                J.anim($('#r_hand_right').show(),'r_hand_r',500,function(){
                                    J.anim($('#r_foot_left').show(),'r_foot_l',500);
                                    J.anim($('#r_foot_right').show(),'r_foot_r',500,function(){
                                        J.anim('#welcome_robot','welcome_robot',2000);
                                    });
                                });
                            });
                        });
                        break;
                    case 2 :
                        $('#w_box_1,#w_box_2,#w_box_3,#w_box_4').hide()
                        J.anim($('#w_box_1').show(),'box_l',500,function(){
                            J.anim($('#w_box_2').show(),'box_r',500,function(){
                                J.anim($('#w_box_3').show(),'box_l',500,function(){
                                    J.anim($('#w_box_4').show(),'box_r',500);
                                });
                            });
                        });
                        break;
                }
            },
            showPageLoading : true,
            remotePage : {
                '#about_section' : '/xbm_init_about_section.action',
                '#MarketingRecord_section' : '/xbm_init_MarketingRecord_section.action',
                '#feequery_section' : '/xbm_init_feequery_section.action',
                '#visit_log_section' : '/xbm_init_visit_log_section.action',
                '#cust_warning_section' : '/xbm_init_cust_warning_section.action',
                '#cust_report_section' : '/xbm_init_cust_report_section.action',
                '#cust_report_detail_section' : '/xbm_init_cust_report_detail_section.action'
            }
        });
       
    };
    var page = function(id,factory){
        return ((id && factory)?_addPage:_getPage).call(this,id,factory);
    }
    var _addPage = function(id,factory){
        pages[id] = new factory();
    };
    var _getPage = function(id){
        return pages[id];
    }
    //��̬����chart canvas�ĸ߶ȣ���ȣ��������ն˽���
    var calcChartOffset = function(){
        return {
            height : $(document).height() - 44 - 30 -60,
            width : $(document).width()
        }

    }
    return {
        run : run,
        page : page,
        calcChartOffset : calcChartOffset
    }
}());
App.page('index',function(){
    this.init = function(){
        $('#btn_show_welcome').on('tap', J.Welcome.show);
        $('#login_pop_btn').tap(function(){
        	var _username = $('#login_pop_username').val();
        	var _password = $('#login_pop_password').val();
        	if(_username&&_username!=''&&_password&&_password!=''){
        		_password = hex_md5(_password);
        		$.ajax({
            		type : "POST",
            		cache : false,
            		async:true,
            		url : "/xbmlogin_checkPassword.action",
            		dataType : "json",
            		data : {'username':_username,'password':_password},
            		success : function(result) {
            			try {
            				if(result.success){
            					J.closePopup();
            		        	J.showToast(result.msg,'success',3000);//TODO
            				}else{
            					J.showToast(result.msg,'error',3000);
            				}
            			}
            			catch (exception) {
            				//console.log(exception);
            			}
            		}
            	});
        	}
        	//J.settings.serverIP
        })
    }
})
App.page('calendar',function(){
    this.init = function(){
        new J.Calendar('#calendar_demo',{
            onRenderDay : function(day,date){
                if(day == 5){
                    return '<div>'+day+'</div><div style="font-size: .8em;color: red">����</div>'
                }
                return day;
            },
            onSelect:function(date){
                alert(date);
            }
        });
        $('#btn_popup_calendar').tap(function(){
            J.popup({
                html : '<div id="popup_calendar"></div>',
                pos : 'center',
                backgroundOpacity : 0.4,
                showCloseBtn : false,
                onShow : function(){
                    new J.Calendar('#popup_calendar',{
                        date : new Date(2013,7,1),
                        onSelect:function(date){
                            $('#btn_popup_calendar').text(date);
                            J.closePopup();
                        }
                    });
                }
            });
        });
    }
});
App.page('refresh',function(){
    this.init = function(){
        J.Refresh({
            selector : '#down_refresh_article',
            type : 'pullDown',
            pullText : '���������ô...',
            releaseText : 'ʲôʱ�����Ը����֣�',
            refreshTip : '���һ�������ˣ�<span style="color:#e222a5">ɧ��</span>',
            callback : function(){
                var scroll = this;
                setTimeout(function () {
                    $('#down_refresh_article ul.list li').text('�Ź���������Ҿͳ�����~');
                    scroll.refresh();
                    J.showToast('���³ɹ�','success');
                }, 2000);
            }
        });
//    ���Լ�ĵ��÷�ʽ
        J.Refresh( '#up_refresh_article','pullUp', function(){
            var scroll = this;
            setTimeout(function () {
                var html = '';
                for(var i=0;i<10;i++){
                    html += '<li style="color:#E74C3C">���Ǳ���������...</li>'
                }
                $('#up_refresh_article ul.list').append(html);
                scroll.refresh();
                J.showToast('���سɹ�','success');
            }, 2000);
        })
    }
});
App.page('scroll',function(){
    this.init = function(){
        $('#h_scroll_article').on('articleshow',function(){
            J.Scroll('#h_scroll_demo',{hScroll:true,hScrollbar : false});
        })
    }
});
App.page('menu',function(){
    this.init = function(){
        $.get('html/custom_aside.html',function(aside){
            $('#aside_container').append(aside);
        })
    }
});
App.page('layout',function(){
    this.init = function(){
        $('#layout_header_ctrl').on('change',function(event,el){
            J.alert('��ʾ','�����'+$(el).text());
        })
        $('#layout-btn-getmore').tap(function(){
            J.popup({
                html: '<div style="height: 100px;line-height: 100px;font-size: 20px;font-weight: 600;text-align: center;">����չʾ���๦��</div>',
                pos : 'bottom-second',
                showCloseBtn : false
            });
        })
    }
});
App.page('popup',function(){
    this.init = function(){
        $('#btn_alert').tap(function(){
            J.alert('��ʾ','����һ��Alert');
        })
        $('#btn_confirm').tap(function(){
            J.confirm('��ʾ','����һ��Confirm��',function(){J.showToast('��ѡ���ˡ�ȷ����')},function(){J.showToast('��ѡ���ˡ�ȡ����')});
        })
        $('#btn_loading').tap(function(){
            J.showMask();
        })
        $('#btn_center').tap(function(){
            J.popup({
                html: '<div style="height: 100px;text-align: center;font-size: 20px;font-weight: 600;margin-top: 10px;color:#E74C3C ">���������ĵ������</div>',
                pos : 'center'
            })
        })
        $('#btn_from_tpl').tap(function(){
            /*J.popup({
                tplId : 'tpl_popup_login',
                pos : 'center'
            });*/
        	J.Router.goTo('#about_section');//TODO
            //$('#about_qq').text('123456789');
        })
        $('#btn_t_top').tap(function(){
            J.popup({
                html: '����һ�����Զ����ĵ�����',
                pos : 'top',
                showCloseBtn : false
            })
        })
        $('#btn_t_ts').tap(function(){
            J.popup({
                html: '����һ����header֮�µĵ�����',
                pos : 'top-second',
                showCloseBtn : false
            })
        })
        $('#btn_t_bottom').tap(function(){
            J.popup({
                html: '����һ�����Եײ�������',
                pos : 'bottom',
                showCloseBtn : false
            })
        })
        $('#btn_t_bs').tap(function(){
            J.popup({
                html: '����һ����footer֮�ϵĵ�����',
                pos : 'bottom-second',
                showCloseBtn : false
            })
        })
        $('#btn_popover').tap(function(){
            J.popover('<ul class="list"><li style="color:#000;">Hello Jingle</li><li style="color:#000;">��ã�Jingle</li></ul>',{top:'50px',left:'10%',right:'10%'},'top');
        });
        $('#btn_actionsheet').tap(function(){
            J.Popup.actionsheet([{
                text : '����QQ����',
                handler : function(){
                    J.showToast('����QQ���ѣ�');
                }
            },{
                text : '����MSN����',
                handler : function(){
                    J.showToast('����MSN���ѣ�');
                }
            }
            ]);
        });
    }
});
App.page('slider',function(){
    this.init = function(){
        var slider;
        $('#slider_section article').on('articleshow',function(){
            slider = new J.Slider({
                selector : '#slider_test',
                onBeforeSlide : function(){
                    return true;
                },
                onAfterSlide : function(i){
                    //alert(i);
                }
            });
        });
        $('#slider_prev').tap(function(){slider.prev()});
        $('#slider_next').tap(function(){slider.next()});
    }
});
App.page('toast',function(){
    this.init = function(){
        $('#btn_t_default').tap(function(){
            J.showToast('����Ĭ�ϵ�Toast,Ĭ��3s��Сʱ');
        })
        $('#btn_t_success').tap(function(){
            J.showToast('��ϲ��success,5s����ʧ','success',5000);
        })
        $('#btn_t_error').tap(function(){
            J.showToast('��Ǹ��error','error');
        })
        $('#btn_t_info').tap(function(){
            J.showToast('��ʾ��info','info');
        })
        $('#btn_t_top').tap(function(){
            J.showToast('������50������','toast top');
        })
    }
});
App.page('chart_line',function(){
    var line,$chart;
    this.init = function(){
        //��������canvas��С
        $chart = $('#line_canvas');
        var wh = App.calcChartOffset();
        $chart.attr('width',wh.width).attr('height',wh.height-30);

        renderLine();
        $('#changeLineType').on('change',function(e,el){
            updateChart(el.data('type'));
        })
    }

    function renderLine(){
        var data = {
            labels : ["һ��","����","����","����","����","����","����",'����','����','ʮ��','ʮһ��','ʮ����'],
            datasets : [
                {
                    name : 'A��Ʒ',
                    color : "#72caed",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [65,59,90,81,56,55,40,20,13,20,11,60]
                },
                {
                    name : 'B��Ʒ',
                    color : "#a6d854",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [28,48,40,19,96,27,100,40,40,70,11,89]
                }
            ]
        }
        line = new JChart.Line(data,{
            id : 'line_canvas',
            smooth : false,
            fill : false
        });
        line.on('tap.point',function(d,i,j){
            J.alert(data.labels[i],d);
        });
        line.draw();
    }
    function updateChart(type){
        if(type == 'smooth'){
            line.refresh({
                smooth : true,
                fill : false
            });
        }else if(type == 'area'){
            line.refresh({
                smooth : true,
                fill : true
            });
        }else{
            line.refresh({
                smooth : false,
                fill : false
            });
        }

    }
});
App.page('chart_bar',function(){
    var $chart;
    this.init = function(){
        //��������canvas��С
        $chart = $('#bar_canvas');
        var wh = App.calcChartOffset();
        $chart.attr('width',wh.width).attr('height',wh.height);

        var data = {
            labels : ["һ��","����","����","����","����","����","����",'����','����','ʮ��','ʮһ��','ʮ����'],
            datasets : [
                {
                    name : 'A��Ʒ',
                    color : "#72caed",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [65,59,90,81,56,55,40,20,13,20,11,60]
                },
                {
                    name : 'B��Ʒ',
                    color : "#a6d854",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [28,48,40,19,96,27,100,40,40,70,11,89]
                }
            ]
        }
        var bar = new JChart.Bar(data,{
            id : 'bar_canvas'
        });
        bar.on('tap.bar',function(d,i,j){
            J.alert(data.labels[i],d);
        });
        bar.on('longTap.bar',function(d,i,j){
            J.alert('��ʾ',d+' = ��ס750ms����ִ���ʾ');
        });
        bar.draw();
    }
});
App.page('chart_pie',function(){
    var pie,$chart;
    this.init = function(){
        //��������canvas��С
        $chart = $('#pie_canvas');
        var wh = App.calcChartOffset();
        $chart.attr('width',wh.width).attr('height',wh.height-100);
        renderPie();
        $('#changePieType').on('change',function(e,el){
            updateChart(el.data('type'));
        })
    }

    function renderPie(){
        var pie_data = [
            {
                name : 'ֱ�ӷ���',
                value: 335,
                color:"#F38630"
            },{
                name : '���˹��',
                value : 234,
                color : "#E0E4CC"
            },{
                name : '��Ƶ���',
                value : 135,
                color : "#72caed"
            },{
                name : '��������',
                value : 1400,
                color : "#a6d854"
            }
        ];
        pie = new JChart.Pie(pie_data,{
            id : 'pie_canvas',
            clickType : 'rotate'
        });
        pie.on('rotate',function(d,i,j){
            $('#pie_segment_info').html(d.name + '    '+ d.value).show();
        });
        pie.draw();
    }
    function updateChart(type){
        $('#pie_segment_info').hide();
        if(type == 'pie'){
            pie.refresh({
                isDount : false
            });
        }else if(type == 'dount'){
            pie.refresh({
                isDount : true,
                dountText : '������Դ'
            });
        }

    }
});
App.page('chart_drag',function(){
    var $lineChart,$barChart;
    this.init = function(){
        //��������canvas��С
        $lineChart = $('#chart_drag_line_canvas');
        $barChart = $('#chart_drag_bar_canvas');
        var wh = App.calcChartOffset();
        $lineChart.attr('width',wh.width).attr('height',wh.height-30);
        $barChart.attr('width',wh.width).attr('height',wh.height-30);
        var data = {
            labels : ["2012","����","����","����","����","����","����",'����','����','ʮ��','ʮһ��','ʮ����','2013',"����","����","����","����","����","����",'����','����','ʮ��','ʮһ��','ʮ����','2014','һ��','����'],
            datasets : [
                {
                    name : 'A��Ʒ',
                    color : "#72caed",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [65,59,90,81,56,55,40,20,13,20,11,60,65,59,90,81,56,55,40,20,11,20,10,60,11,60,65]
                },
                {
                    name : 'B��Ʒ',
                    color : "#a6d854",
                    pointColor : "#95A5A6",
                    pointBorderColor : "#fff",
                    data : [28,48,40,19,96,27,100,40,40,70,11,89,28,48,40,19,96,27,100,40,40,70,10,89,28,48,40]
                }
            ]
        }
        $('#changeDragChartType').on('change',function(e,el){
            renderChart(el.data('type'),data);
        })
        renderChart('line',data);
    }
    var renderChart = function(type,data){
        if(type == 'line'){
            $lineChart.show();
            $barChart.hide();
            new JChart.Line(data,{
                id : 'chart_drag_line_canvas',
                datasetGesture : true,
                datasetOffsetNumber : 10
            }).draw(true);
        }else{
            $lineChart.hide();
            $barChart.show();
            new JChart.Bar(data,{
                id : 'chart_drag_bar_canvas',
                datasetGesture : true,
                datasetOffsetNumber : 10
            }).draw(true);
        }
    }
});
App.page('chart_dynamic',function(){
    var pause = false,$chart;
    var datasets = [[65,59,90,81,56,55,40,20,3,20,10,60],[28,48,40,19,96,27,100,40,40,70,10,89]];
    var data = {
        labels : ["һ��","����","����","����","����","����","����",'����','����','ʮ��','ʮһ��','ʮ����'],
        datasets : [
            {
                name : 'A��Ʒ',
                color : "#72caed",
                pointColor : "#95A5A6",
                pointBorderColor : "#fff",
                data : datasets[0]
            },
            {
                name : 'B��Ʒ',
                color : "#a6d854",
                pointColor : "#95A5A6",
                pointBorderColor : "#fff",
                data : datasets[1]
            }
        ]
    }

    this.init = function(){
        //��������canvas��С
        $chart = $('#dynamic_line_canvas');
        var wh = App.calcChartOffset();
        $chart.attr('width',wh.width).attr('height',wh.height-30);
        var line = new JChart.Line(data,{
            id : 'dynamic_line_canvas'
        });
        line.draw();
        refreshChart(line);
        $('#pause_dynamic_chart').on('tap',function(){
            pause = !pause;
            $(this).text(pause?'����':'��ͣ');
        })
    }

    function refreshChart(chart){
        setTimeout(function(){
            if(!pause){
                datasets[0].shift();
                datasets[0].push(Math.floor(Math.random()*100));
                datasets[1].shift();
                datasets[1].push(Math.floor(Math.random()*100));
                chart.load(data);
            }
            refreshChart(chart);
        },1000);
    }
});
App.page('form',function(){
    this.init = function(){
        alert('init');
        $('#checkbox_1').on('change',function(){
            alert($(this).data('checkbox'));
        })
    }
})
$(function(){
    App.run();
})