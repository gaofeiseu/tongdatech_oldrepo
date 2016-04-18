/*
 * Jingle v0.4 Copyright (c) 2013 shixy, http://shixy.github.io/Jingle/
 * Released under MIT license
 * walker.shixy@gmail.com
 */
var Jingle = J = {
    version : '0.41',
    $ : window.Zepto,
    //��������
    settings : {
        //single  ��ҳ�湤��  muti  ��ҳ�湤��
        appType : 'single',
        //pageĬ�϶���Ч��
        transitionType : 'slide',
        //�Զ��嶯��ʱ��Ĭ�϶���ʱ��(��pageת������ʱ��)
        transitionTime : 250,
        //�Զ��嶯��ʱ��Ĭ�϶�������(��pageת����������)
        transitionTimingFunc : 'ease-in',
        //toast ����ʱ��,Ĭ��Ϊ3s
        toastDuration : 3000,
        //�Ƿ���ʾ��ӭ����
        showWelcome : false,
        //��ӭ���濨Ƭ�л�ʱ��ִ�к���������������˧��ը��Ļ�ӭ����
        welcomeSlideChange : null,
        //����pageģ��ʱ���Ƿ���ʾ����
        showPageLoading : false,
        //pageģ��Ĭ�ϵ����λ�ã���Ҫ���ڿ���hybridӦ�ã�ʵ��page���Զ�װ��
        basePagePath : '/Jingle/html/',
        basePageSuffix : '.jsp',
        //pageģ���Զ��·��{#id: href,#id: href}
        remotePage:{},
        serverIP:'http://198.10.1.142:7001/'
    },
    //�ֻ�����ƽ��
    mode : window.innerWidth < 800 ? "phone" : "tablet",
    hasTouch : 'ontouchstart' in window,
    //�Ƿ��������
    launchCompleted : false,
    //�Ƿ��д򿪵Ĳ�߲˵�
    hasMenuOpen : false,
    //�Ƿ��д򿪵ĵ�����
    hasPopupOpen : false,
    isWebApp : location.protocol == 'http:',
    /**
     * ����Jingle
     * @param opts {object}
     */
    launch : function(opts){
        $.extend(this.settings,opts);
//        var hasShowWelcome = window.localStorage.getItem('hasShowWelcome');
//        if(!hasShowWelcome && this.settings.showWelcome){
//            this.Welcome.show();
//        }
        this.LoginPop.show();
        this.Element.init();
        this.Element.initControlGroup();
        this.Router.init();
        this.Menu.init();
    }
};
J.LoginPop = (function(){
	var show = function(){
		J.popup({
	        tplId : 'xbm_login_pop',
	        pos : 'center',
	        showCloseBtn : false,
	        clickMask2Close : false
	    });
	};
	var close = function(){
		J.Popup.close();
	};
	return {show:show,close:close};
})();
/**
 * ��ʼ��ҳ�����Ԫ��
 */
J.Element = (function($){
    var SELECTOR  = {
        'icon' : '[data-icon]',
        'scroll' : '[data-scroll="true"]',
        'toggle' : '.toggle',
        'range' : '[data-rangeinput]',
        'progress' : '[data-progress]',
        'count' : '[data-count]',
        'checkbox' : '[data-checkbox]'
    }
    /**
     * ��ʼ�����������
     * @param {String} ��Ԫ�ص�cssѡ����
     * @param {Object} ��Ԫ�ػ��߸�Ԫ�ص�zeptoʵ��
     */
    var init = function(selector){
        if(!selector){
            //iscroll ������Ԫ�ؿɼ�������²��ܳ�ʼ��
            $(document).on('articleshow','article',function(){
                J.Element.scroll(this);
            });
        };
        var $el = $(selector || 'body');
        if($el.length == 0)return;

        $.map(_getMatchElements($el,SELECTOR.icon),_init_icon);
        $.map(_getMatchElements($el,SELECTOR.toggle),_init_toggle);
        $.map(_getMatchElements($el,SELECTOR.range),_init_range);
        $.map(_getMatchElements($el,SELECTOR.progress),_init_progress);
        $.map(_getMatchElements($el,SELECTOR.count),_init_badge);
        $.map(_getMatchElements($el,SELECTOR.checkbox),_init_checkbox);
        $el = null;
    }

    /**
     * ��ʼ����ť��(���¼�)
     */
    var initControlGroup = function(){
        $(document).on('tap','ul.control-group li',function(){
            var $this = $(this);
            if($this.hasClass('active'))return;
            $this.addClass('active').siblings('.active').removeClass('active').parent().trigger('change',[$this]);
        });
    }
    /**
     * �������Ӽ�����
     */
    var _getMatchElements = function($el,selector){
        return $el.find(selector).add($el.filter(selector));
    }
    /**
     * ��ʼ��iscroll�����������iscroll���
     */
    var initScroll = function(selector){
        $.map(_getMatchElements($(selector),SELECTOR.scroll),function(el){J.Scroll(el);});
    }
    /**
     * ����icon���
     */
    var _init_icon = function(el){
        var $el = $(el),$icon=$el.children('i.icon'),icon = $el.data('icon');
        if($icon.length > 0){//�Ѿ���ʼ�����͸���icon
            $icon.attr('class','icon '+icon);
        }else{
            $el.prepend('<i class="icon '+icon+'"></i>');
        }
    }
    /**
     * ����toggle�л����
     */
    var _init_toggle = function(el){
        var $el = $(el);
        if($el.find('div.toggle-handle').length>0){//�Ѿ���ʼ��
            return;
        }
        var name = $el.attr('name');
        //��������򣬷����ȡֵ
        if(name){
            $el.append('<input style="display: none;" name="'+name+'" value="'+$el.hasClass('active')+'"/>');
        }
        $el.append('<div class="toggle-handle"></div>');
        $el.tap(function(){
            var $t = $(this),v = !$t.hasClass('active');
            $t.toggleClass('active').trigger('toggle',[v]);//����toggle�¼�
            $t.find('input').val(v);
        })
    }
    /**
     * ����range�������
     */
    var _init_range = function(el){
        var $el = $(el),$input;
        var $range = $('input[type="range"]',el);
        var align = $el.data('rangeinput');
        var input = $('<input type="number" name="test" value="'+$range.val()+'"/>');
        if(align == 'left'){
            $input = input.prependTo($el);
        }else{
            $input = input.appendTo($el);
        }
        var max = parseInt($range.attr('max'),10);
        var min = parseInt($range.attr('min'),10);
        $range.change(function(){
            $input.val($range.val());
        });
        $input.on('input',function(){
            var value = parseInt($input.val(),10);
            value = value>max?max:(value<min?min:value);
            $range.val(value);
            $input.val(value);
        })
    }
    /**
     * ����progress���
     */
    var _init_progress = function(el){
        var $el = $(el),$bar;
        var progress = parseFloat($el.data('progress'))+'%';
        var title = $el.data('title') || '';
        $bar = $el.find('div.bar');
        if($bar.length == 0){
            $bar = $('<div class="bar"></div>').appendTo($el);
        }
        $bar.width(progress).text(title+progress);
        if(progress == '100%'){
            $bar.css('border-radius','10px');
        }
    }
    /**
     * ����badge���
     */
    var _init_badge = function(el){
        var $el = $(el),$count = $el.find('span.count'),count = parseInt($el.data('count')),
            orient = $el.data('orient'), className = (orient == 'left')?'left':'';
        if($count.length>0){
            $count.text(count).show();//��������
        }else{
            $count = $('<span class="count '+className+'">'+count+'</span>').appendTo($el);
        }
        if(count == 0){
            $count.hide();
        }
    }

    var _init_checkbox = function(el){
        var $el = $(el);
        var value = $el.data('checkbox');
        if($el.find('i.icon').length>0){
            return;
        }
        $el.prepend('<i class="icon checkbox-'+value+'"></i>');
        $el.on('tap',function(){
            var status = ($el.data('checkbox') == 'checked') ? 'unchecked':'checked';
            $el.data('checkbox',status).find('i.icon').attr('class','icon checkbox-'+status);
            //�Զ���change�¼�
            $el.trigger('change');
        });

    }

    return {
        init : init,
        initControlGroup : initControlGroup,
        icon : _init_icon,
        toggle : _init_toggle,
        progress : _init_progress,
        range : _init_range,
        badge : _init_badge,
        scroll : initScroll
    }
})(J.$);
/**
 * ��߲˵�
 */
J.Menu = (function($){
    var $asideContainer,$sectionContainer,$sectionMask;
    var init = function(){
        $asideContainer = $('#aside_container');
        $sectionContainer = $('#section_container');
        $sectionMask = $('<div id="section_container_mask"></div>').appendTo('#section_container');
        //��Ӹ��ֹر��¼�
        $sectionMask.on('tap',hideMenu);
        $asideContainer.on('swipeRight','aside',function(){
            if($(this).data('position') == 'right'){
                hideMenu();
            }
        });
        $asideContainer.on('swipeLeft','aside',function(){
            if($(this).data('position') != 'right'){
                hideMenu();
            }
        });
        $asideContainer.on('tap','.aside-close',hideMenu);
    }
    /**
     * �򿪲�߲˵�
     * @param selector cssѡ������elementʵ��
     */
    var showMenu = function(selector){
        var $aside = $(selector).addClass('active'),
            transition = $aside.data('transition'),// push overlay  reveal
            position = $aside.data('position') || 'left',
            showClose = $aside.data('show-close'),
            width = $aside.width(),
            translateX = position == 'left'?width+'px':'-'+width+'px';
        if(showClose && $aside.find('div.aside-close').length == 0){
            $aside.append('<div class="aside-close icon close"></div>');
        }

        //aside�п�����Ҫscroll���
        J.Element.scroll($aside);

        if(transition == 'overlay'){
            J.anim($aside,{translateX : '0%'});
        }else if(transition == 'reveal'){
            J.anim($sectionContainer,{translateX : translateX});
        }else{//Ĭ��Ϊpush
            J.anim($aside,{translateX : '0%'});
            J.anim($sectionContainer,{translateX : translateX});
        }
        $('#section_container_mask').show();
        J.hasMenuOpen = true;
    }
    /**
     * �رղ�߲˵�
     * @param duration {int} ��������ʱ��
     * @param callback ������ϻص�����
     */
    var hideMenu = function(duration,callback){
        var $aside = $('#aside_container aside.active'),
            transition = $aside.data('transition'),// push overlay  reveal
            position = $aside.data('position') || 'left',
            translateX = position == 'left'?'-100%':'100%';

        var _finishTransition = function(){
            $aside.removeClass('active');
            J.hasMenuOpen = false;
            callback && callback.call(this);
        };

        if(transition == 'overlay'){
            J.anim($aside,{translateX : translateX},duration,_finishTransition);
        }else if(transition == 'reveal'){
            J.anim($sectionContainer,{translateX : '0'},duration,_finishTransition);
        }else{//Ĭ��Ϊpush
            J.anim($aside,{translateX : translateX},duration);
            J.anim($sectionContainer,{translateX : '0'},duration,_finishTransition);
        }

        $('#section_container_mask').hide();
    }
    return {
        init : init,
        show : showMenu,
        hide : hideMenu
    }
})(J.$);
/**
 * section ҳ��Զ�̼���
 */
J.Page = (function($){
    var _formatHash = function(hash){
        return hash.indexOf('#') == 0 ? hash.substr(1) : hash;
    }
    /**
     * ����sectionģ��
     * @param {string} hash��Ϣ
     * @param {string} url����
     */
    var loadSectionTpl = function(hash,callback){
        var param = {},query,replaceSection = false;
        if($.type(hash) == 'object'){
            param = hash.param;
            query = hash.query;
            hash = hash.tag;
        }
        var q = $(hash).data('query');
        //�Ѿ�������ֱ����ת����Ӧ��ҳ��
//        if($(hash).length == 1){
//            if(q == query){
//                callback();
//                return;
//            }else{
//                replaceSection = true;
//            }
//        }
        replaceSection = true;//�����ػ��棬ÿ�ζ��ӷ���������
        
        var id = _formatHash(hash);
        //��ǰdom�в����ڣ���Ҫ�ӷ���˼���
        var url = J.settings.remotePage[hash];
        //���remotePage���Ƿ�������,û�����Զ���basePagePath��װ��ģ��
        url || (url = J.settings.basePagePath+id+J.settings.basePageSuffix);
        J.settings.showPageLoading && J.showMask();
        loadContent(url,param,function(html){
            J.settings.showPageLoading && J.hideMask();
            //��ӵ�dom����
            $(hash).remove();
            var $h = $(html);
            $('#section_container').append($h);
            if(replaceSection){
                $h.addClass('active');
            }
            //����pageload�¼�
            $h.trigger('pageload').data('query',query);
            //�������
            J.Element.init(hash);
            callback();
            $h = null;
        });
    }
    var loadSectionRemote = function(url,section){
        var param = J.Util.parseHash(window.location.hash).param;
        loadContent(url,param,function(html){
            $(section).html(html);
            J.Element.init(section);
        });
    }
    /**
     * �����ĵ�Ƭ��
     * @param url
     */
    var loadContent = function(url,param,callback){
        return $.ajax({
                url : url,
                timeout : 20000,
                data : param,
                success : function(html){
                    callback && callback(html);
                }
            });
    }
    return {
        load : loadSectionTpl,
        loadSection : loadSectionRemote,
        loadContent : loadContent
    }
})(J.$);
/**
 * ·�ɿ�����
 */
J.Router = (function($){
        var _history = [];
    /**
     * ��ʼ��events��state
     */
    var init = function(){
        $(window).on('popstate', _popstateHandler);
        //��ֹ��data-target����href��'#'��ͷ�ĵ�aԪ�ص�Ĭ����Ϊ
        $(document).on('click','a',function(e){
            var target = $(this).data('target'),
                href = $(this).attr('href');
            if(!href ||  href.match(/^#/) || target){
                e.preventDefault();
                return false;
            }
        });
        $(document).on('tap','a[data-target]',_targetHandler);
        _initIndex();
    }

    //����appҳ���ʼ��
    var _initIndex = function(){
        var targetHash = location.hash;
        //ȡҳ���е�һ��section��Ϊapp����ʼҳ
        var $section = $('#section_container section').first();
        var indexHash = '#'+$section.attr('id');
        _add2History(indexHash,true);
        if(targetHash != '' && targetHash != indexHash){
            _showSection(targetHash);//��ת��ָ����ҳ��
        }else{
            $section.trigger('pageinit').trigger('pageshow').data('init',true).find('article.active').trigger('articleshow');
        }
    }

    /**
     * ����������ĺ����¼�
     * ǰ���¼���������
     * @private
     */
    var _popstateHandler = function(e){
        if(e.state && e.state.hash){
            var hash = e.state.hash;
            if(_history[1] && hash === _history[1].hash){//������ʷ��¼��֤���Ǻ����¼�
                J.hasMenuOpen && J.Menu.hide();//�رյ�ǰҳ��Ĳ˵�
                J.hasPopupOpen && J.Popup.close();//�رյ�ǰҳ��ĵ�������
                back();
            }else{//������Ϊ�ǷǷ����˻���ǰ��
                return;
            }
        }else{
            return;
        }

    }
    var _targetHandler = function(){
        var _this = $(this),
            target = _this.attr('data-target'),
            href = _this.attr('href');

        switch(target){
            case 'section' :
                if(J.settings.appType == 'single'){
                    _showSection(href);
                }
                break;
            case 'article' :
                _showArticle(href,_this);
                break;
            case 'menu' :
                _toggleMenu(href);
                break;
            case 'back' :
                window.history.go(-1);
                break;
        }
    }

    /**
     * ��ת����ҳ��
     * @param hash ��page��'#id'
     */
    var _showSection  = function(hash){
        if(J.hasMenuOpen){//�رղ˵�����ת��
            J.Menu.hide(200,function(){
                _showSection(hash);
            });
            return;
        }
        //��ȡhash��Ϣ
        var hashObj = J.Util.parseHash(hash);
        var current = _history[0];
        //ͬһ��ҳ��,�����¼���
        if(current.hash === hashObj.hash){
            return;
        }
        //����ģ��
        J.Page.load(hashObj,function(){
            var sameSection = (current.tag == hashObj.tag);
           if(sameSection){//��ͬҳ�棬��������¼�
               $(current.tag).trigger('pageshow').find('article.active').trigger('articlehide');
           }else{//��ͬ��Ƭҳ��ת����
               _changePage(current.tag,hashObj.tag);
           }
            _add2History(hash,sameSection);
        });
    }
    /**
     * ����
     */
    var back = function(){
        if(J.settings.appType == 'single'){
            _changePage(_history.shift().tag,_history[0].tag,true)
        }
    }
    var _changePage = function(current,target,isBack){
        J.Transition.run(current,target,isBack);
    }
    /**
     * ������ʼ�¼
     */
    var _add2History = function(hash,noState){
       var hashObj = J.Util.parseHash(hash);
        if(noState){//������������ʷ��¼
            _history.shift(hashObj);
            window.history.replaceState(hashObj,'',hash);
        }else{
            window.history.pushState(hashObj,'',hash);
        }
        _history.unshift(hashObj);
    }

    /**
     * ����href��Ӧ��article
     * @param href #id
     * @param el ��ǰê��
     */
    var _showArticle = function(href,el){
        var article = $(href);
        if(article.hasClass('active'))return;
        el.addClass('active').siblings('.active').removeClass('active');
        var activeArticle = article.addClass('active').siblings('.active').removeClass('active');
        article.trigger('articleshow');
        activeArticle.trigger('articlehide');
    }

    var _toggleMenu = function(hash){
        J.hasMenuOpen?J.Menu.hide():J.Menu.show(hash);
    }

    return {
        init : init,
        goTo : _showSection,
        showArticle : _showArticle,
        back : back
    }
})(J.$);
/**
 * ��zeptojs��ajax���з�װ��ʵ�����߷���
 * �Ƽ������ݵ�ajax������ñ�����������������ʹ��zeptojs�Լ���ajax
 * @Deprecated ��J.Cache����
 */
J.Service = (function($){
    var UNPOST_KEY = 'JINGLE_POST_DATA',
        GET_KEY_PREFIX = 'JINGLE_GET_';
    var ajax = function(options){
        if(options.type == 'post'){
            _doPost(options);
        }else{
            _doGet(options);
        }
    }

    var _doPost = function(options){
        if(J.offline){//����ģʽ�������ݴ浽���أ�����ʱ�����ύ
            _setUnPostData(options.url,options.data);
            options.success('�����Ѵ�������');
        }else{//����ģʽ��ֱ���ύ
            $.ajax(options);
        }
    }
    var _doGet = function(options){
        var key = options.url +JSON.stringify(options.data);
        if(J.offline){//����ģʽ��ֱ�Ӵӱ��ض�ȡ
            var result = _getCache(key);
            if(result){
                options.success(result.data,key,result.cacheTime);
            }else{//δ���������
                options.success(result);
            }
        }else{//����ģʽ�������ݱ��浽����
            var callback = options.success;
            options.success = function(result){
                _saveData2local(key,result);
                callback(result,key);
            }
            $.ajax(options);
        }
    }

    /**
     * ��ȡ�����ѻ��������
     * @private
     */
    var _getCache = function(key){
         return JSON.parse(window.localStorage.getItem(GET_KEY_PREFIX+key));
    }
    /**
     * �������ݵ�����
     * @private
     */
    var _saveData2local = function(key,result){
        var data = {
            data : result,
            cacheTime : new Date()
        }
        window.localStorage.setItem(GET_KEY_PREFIX+key,JSON.stringify(data));
    }

    /**
     * ��post�����ݱ���������
     * @param url
     * @param result
     * @private
     */
    var _setUnPostData = function(url,result){
        var data = getUnPostData();
        data = data || {};
        data[url] = {
            data : result,
            createdTime : new Date()
        }
        window.localStorage.setItem(UNPOST_KEY,JSON.stringify(data));
    }
    /**
     *  ��ȡ��δͬ����post����
     * @param url  û�оͷ�������δͬ��������
     */
    var getUnPostData = function(url){
        var data = JSON.parse(window.localStorage.getItem(UNPOST_KEY));
        return (data && url ) ? data[url] : data;
    }
    /**
     * �Ƴ�δͬ��������
     * @param url û�о��Ƴ�����δͬ��������
     */
    var removeUnPostData = function(url){
        if(url){
            var data = getUnPostData();
            delete data[url];
            window.localStorage.setItem(UNPOST_KEY,JSON.stringify(data));
        }else{
            window.localStorage.removeItem(UNPOST_KEY);
        }
    }

    /**
     * ͬ�����ػ����post����
     * @param url
     */
    var syncPostData = function(url,success,error){
        var unPostData = getUnPostData(url).data;
        $.ajax({
            url : url,
            contentType:'application/json',
            data : unPostData,
            type : 'post',
            success : function(){
                success(url);
            },
            error : function(){
                error(url);
            }
        })
    }
    /**
     * ͬ�����е�����
     * @param callback
     */
    var syncAllPostData = function(success,error){
        var unPostData = getUnPostData();
        for(var url in unPostData){
            syncPostData(url,success,error);
        }
        removeUnPostData();
    }

    //copy from zepto
    function parseArguments(url, data, success, dataType) {
        var hasData = !$.isFunction(data)
        return {
            url:      url,
            data:     hasData  ? data : undefined,
            success:  !hasData ? data : $.isFunction(success) ? success : undefined,
            dataType: hasData  ? dataType || success : success
        }
    }

    var get = function(url, data, success, dataType){
        return ajax(parseArguments.apply(null, arguments))
    }

    var post = function(url, data, success, dataType){
        var options = parseArguments.apply(null, arguments)
        options.type = 'POST'
        return ajax(options)
    }

    var getJSON = function(url, data, success){
        var options = parseArguments.apply(null, arguments);
        options.dataType = 'json'
        return ajax(options)
    }
    var clear = function(){
        var storage = window.localStorage;
        var keys = [];
        for(var i = 0; i< storage.length; i++){
            var key = storage.key(i);
            key.indexOf(GET_KEY_PREFIX) == 0 && keys.push(key);
        }
        for(var i = 0; i < keys.length; i++){
            storage.removeItem(keys[i]);
        }
        storage.removeItem(UNPOST_KEY);
    }
    return {
        ajax : ajax,
        get : get,
        post : post,
        getJSON : getJSON,
        getUnPostData : getUnPostData,
        removeUnPostData : removeUnPostData,
        syncPostData : syncPostData,
        syncAllPostData : syncAllPostData,
        getCacheData : _getCache,
        saveCacheData : _saveData2local,
        clear : clear
    }
})(J.$);
/**
 * �ṩһЩ�򵥵�ģ�壬��artTemplate����Ⱦ
 */
J.Template = (function($){
    /**
     * ����ģ��
     * @param el  selector
     * @param title  ��ʾ�ı�
     * @param icon   ͼ��
     */
    var background = function(el,title,icon){
        var markup = '<div class="back-mask"><div class="icon '+icon+'"></div><div>'+title+'</div></div>';
        $(el).html(markup);
    }

    /**
     * �޼�¼����ģ��
     * @param el
     */
    var no_result = function(el){
        background(el,'û���ҵ��������','drawer');
    }
    /**
     * ���صȴ�����ģ��
     * @param el
     */
    var loading = function(el){
        background(el,'������...','cloud-download');
    }

    /**
     * ����artTemplateģ������Ⱦҳ��
     * @param containerSelector Ŀ������
     * @param templateId  artTemplateģ��ID
     * @param data ģ������
     * @param type replace|add ��Ⱦ�õ��ĵ�Ƭ�����滻������ӵ�Ŀ��������
     */
    var render = function(containerSelector,templateId,data,type){
        var el =  $(containerSelector),
            type = type || 'replace';//replace  add
        if($.type(data) == 'array' && data.length == 0 ){
            no_result(el);
        }else{
            var html = template(templateId,data);
            if(type == 'replace'){
                el.html(html);
            }else{
                el.append(html);
            }
            J.Element.init(html);
        }
    }
    return {
        render : render,
        background : background,
        loading : loading,
        no_result : no_result
    }
})(J.$);

/**
 *  ��Ϣ���
 */
J.Toast = (function($){
    var toast_type = 'toast',_toast,timer,
        //����ģ��
        TEMPLATE = {
            toast : '<a href="#">{value}</a>',
            success : '<a href="#"><i class="icon checkmark-circle"></i>{value}</a>',
            error : '<a href="#"><i class="icon cancel-circle"></i>{value}</a></div>',
            info : '<a href="#"><i class="icon info-2"></i>{value}</a>'
        }

    var _init = function(){
        //ȫ��ֻ��һ��ʵ��
        $('body').append('<div id="jingle_toast"></div>');
        _toast = $('#jingle_toast');
        _subscribeCloseTag();
    }

    /**
     * �ر���Ϣ��ʾ
     */
    var hide = function(){
        J.anim(_toast,'scaleOut',function(){
            _toast.hide();
           _toast.empty();
        });
    }
    /**
     * ��ʾ��Ϣ��ʾ
     * @param type ����  toast|success|error|info  �ո� + class name ����ʵ���Զ�����ʽ
     * @param text ��������
     * @param duration ����ʱ�� Ϊ0���Զ��ر�,Ĭ��Ϊ3000ms
     */
    var show = function(type,text,duration){
        if(timer) clearTimeout(timer);
        var classname = type.split(/\s/);
        toast_type = classname[0];
        _toast.attr('class',type).html(TEMPLATE[toast_type].replace('{value}',text)).show();
        J.anim(_toast,'scaleIn');
        if(duration !== 0){//Ϊ0 ���Զ��ر�
            timer = setTimeout(hide,duration || J.settings.toastDuration);
        }
    }
    var _subscribeCloseTag = function(){
        _toast.on('tap','[data-target="close"]',function(){
            hide();
        })
    }
    _init();
    return {
        show : show,
        hide : hide
    }
})(J.$);
/**
 * pageת������
 * ���Զ���css����
 */
J.Transition = (function($){
    var isBack,$current,$target,transitionName,
        animationClass = {
        //[[currentOut,targetIn],[currentOut,targetIn]]
        slide : [['slideLeftOut','slideLeftIn'],['slideRightOut','slideRightIn']],
        cover : [['','slideLeftIn'],['slideRightOut','']],
        slideUp : [['','slideUpIn'],['slideDownOut','']],
        slideDown : [['','slideDownIn'],['slideUpOut','']],
        popup : [['','scaleIn'],['scaleOut','']]
        };

    var _doTransition = function(){
        //���� beforepagehide �¼�
        $current.trigger('beforepagehide',[isBack]);
        //���� beforepageshow �¼�
        $target.trigger('beforepageshow',[isBack]);
        var c_class = transitionName[0]||'empty' ,t_class = transitionName[1]||'empty';
        $current.bind('webkitAnimationEnd.jingle', _finishTransition).addClass('anim '+ c_class);
        $target.addClass('anim animating '+ t_class);
    }
    var _finishTransition = function() {
        $current.off('webkitAnimationEnd.jingle');
        $target.off('webkitAnimationEnd.jingle');
        //reset class
        $current.attr('class','');
        $target.attr('class','active');
        //add custom events
        !$target.data('init') && $target.trigger('pageinit').data('init',true);
        !$current.data('init') && $current.trigger('pageinit').data('init',true);
        //����pagehide�¼�
        $current.trigger('pagehide',[isBack]);
        //����pageshow�¼�
        $target.trigger('pageshow',[isBack]);

        $current.find('article.active').trigger('articlehide');
        $target.find('article.active').trigger('articleshow');
        $current = $target = null;//�ͷ�
    }

    /**
     * ִ��ת����������������ȡ����Ŀ��page�϶�������(����ʱȡ���ڵ�ǰpage)
     * @param current ��ǰpage
     * @param target  Ŀ��page
     * @param back  �Ƿ�Ϊ����
     */
    var run = function(current,target,back){
        //�رռ���
        $(':focus').trigger('blur');
        isBack = back;
        $current = $(current);
        $target = $(target);
        var type = isBack?$current.attr('data-transition'):$target.attr('data-transition');
        type = type|| J.settings.transitionType;
        //����ʱȡ�෴�Ķ���Ч����
        transitionName  = isBack ? animationClass[type][1] : animationClass[type][0];
        _doTransition();
    }

    /**
     * ����Զ���ת������Ч��
     * @param name  ��������
     * @param currentOut ��������µ�ǰҳ����ȥ�Ķ���class
     * @param targetIn   ���������Ŀ��ҳ�����Ķ���class
     * @param backCurrentOut ��������µ�ǰҳ����ȥ�Ķ���class
     * @param backCurrentIn ���������Ŀ��ҳ�����Ķ���class
     */
    var addAnimation = function(name,currentOut,targetIn,backCurrentOut,backCurrentIn){
        if(animationClass[name]){
            console.error('��ת�������Ѿ����ڣ��������Զ���Ķ�������(���Ʋ����ظ�)');
            return;
        }
        animationClass[name] = [[currentOut,targetIn],[backCurrentOut,backCurrentIn]];
    }
    return {
        run : run,
        add : addAnimation
    }

})(J.$);
/**
 * ���ù�����
 */
J.Util = (function($){
    var parseHash = function(hash){
        var tag,query,param={};
        var arr = hash.split('?');
        tag = arr[0];
        if(arr.length>1){
            var seg,s;
            query = arr[1];
            seg = query.split('&');
            for(var i=0;i<seg.length;i++){
                if(!seg[i])continue;
                s = seg[i].split('=');
                param[s[0]] = s[1];
            }
        }
        return {
            hash : hash,
            tag : tag,
            query : query,
            param : param
        }
    }

    /**
     * ��ʽ��date
     * @param date
     * @param format
     */
    var formatDate = function(date,format){
        var o =
        {
            "M+" : date.getMonth()+1, //month
            "d+" : date.getDate(),    //day
            "h+" : date.getHours(),   //hour
            "m+" : date.getMinutes(), //minute
            "s+" : date.getSeconds(), //second
            "q+" : Math.floor((date.getMonth()+3)/3),  //quarter
            "S" : date.getMilliseconds() //millisecond
        }
        if(/(y+)/.test(format))
            format=format.replace(RegExp.$1,(date.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(format))
                format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        return format;
    }

    return {
        parseHash : parseHash,
        formatDate : formatDate
    }

})(J.$);
/**
 * ��ӭ���棬�����������ŵ�ը��Ļ�ӭ����Ŷ
 * @module Welcome
 */
J.Welcome = (function($){
    /**
     * ��ʾ��ӭ����
     */
    var showWelcome = function(){
        $.ajax({
            url : J.settings.basePagePath+'welcome_page.jsp',
            timeout : 5000,
            async : false,
            success : function(html){
                //��ӵ�dom����
                $('body').append(html);
                new J.Slider({
                    selector : '#jingle_welcome',
                    onAfterSlide  : J.settings.welcomeSlideChange
                });
            }
        })
    }
    /**
     * �رջ�ӭ����
     */
    var hideWelcome = function(){
        J.anim('#jingle_welcome','slideLeftOut',function(){
            $(this).remove();
            window.localStorage.setItem('hasShowWelcome',true);
        })
    }

    return {
        show : showWelcome,
        hide : hideWelcome
    }
})(J.$);

(function($){
    /*
     * alias func
     * ��һЩ���÷�����д��
     ** /
    /**
     * ����zepto�Ķ�������,�ò�����Ϊ��ѡ
     */
    J.anim  =  function(el,animName,duration,ease,callback){
        var d, e,c;
        var len = arguments.length;
        for(var i = 2;i<len;i++){
            var a = arguments[i];
            var t = $.type(a);
            t == 'number'?(d=a):(t=='string'?(e=a):(t=='function')?(c=a):null);
        }
        $(el).animate(animName,d|| J.settings.transitionTime,e||J.settings.transitionTimingFunc,c);
    }
    /**
     * ��ʾloading��
     * @param text
     */
    J.showMask = function(text){
        J.Popup.loading(text);
    }
    /**
     * �ر�loading��
     */
    J.hideMask = function(){
        J.Popup.close(true);
    }
    /**
     *  ��ʾ��Ϣ
     * @param text
     * @param type toast|success|error|info
     * @param duration ����ʱ�䣬Ϊ0���Զ��ر�
     */
    J.showToast = function(text,type,duration){
        type = type || 'toast';
        J.Toast.show(type,text,duration);
    }
    /**
     * �ر���Ϣ��ʾ
     */
    J.hideToast = function(){
        J.Toast.hide();
    }
    J.alert = function(title,content){
        J.Popup.alert(title,content);
    }
    J.confirm = function(title,content,okCall,cancelCall){
        J.Popup.confirm(title,content,okCall,cancelCall);
    }
    /**
     * ��������
     * @param options
     */
    J.popup = function(options){
        J.Popup.show(options);
    }
    /**
     * �رմ���
     */
    J.closePopup = function(){
        J.Popup.close();
    }
    /**
     * ����ͷ�ĵ�����
     * @param html [��ѡ]
     * @param pos [��ѡ]  λ��
     * @param arrowDirection [��ѡ] ��ͷ����
     * @param onShow [��ѡ] ��ʾ֮ǰִ��
     */
    J.popover = function(html,pos,arrowDirection,onShow){
        J.Popup.popover(html,pos,arrowDirection,onShow);
    }
    /**
     * �Զ���Ⱦģ�岢��䵽ҳ��
     * @param containerSelector ����������
     * @param templateId ģ��ID
     * @param data ����Դ
     * @param type [��ѡ] add|replace
     */
    J.tmpl = function(containerSelector,templateId,data,type){
        J.Template.render(containerSelector,templateId,data,type);
    }
})(J.$);
/**
 * ���������
 */
J.Popup = (function($){
    var _popup,_mask,transition,clickMask2close,
        POSITION = {
            'top':{
                top:0,
                left:0,
                right:0
            },
            'top-second':{
                top:'44px',
                left:0,
                right:0
            },
            'center':{
                top:'50%',
                left:'5%',
                right:'5%',
                'border-radius' : '3px'
            },
            'bottom' : {
                bottom:0,
                left:0,
                right:0
            },
            'bottom-second':{
                bottom : '51px',
                left:0,
                right:0
            }
        },
        ANIM = {
            top : ['slideDownIn','slideUpOut'],
            bottom : ['slideUpIn','slideDownOut'],
            defaultAnim : ['bounceIn','bounceOut']
        },
        TEMPLATE = {
            alert : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a data-target="closePopup" data-icon="checkmark">{ok}</a></div>',
            confirm : '<div class="popup-title">{title}</div><div class="popup-content">{content}</div><div id="popup_btn_container"><a class="cancel" data-icon="close">{cancel}</a><a data-icon="checkmark">{ok}</a></div>',
            loading : '<i class="icon spinner"></i><p>{title}</p>'
        };

    /**
     * ȫ��ֻ��һ��popupʵ��
     * @private
     */
    var _init = function(){
        $('body').append('<div id="jingle_popup"></div><div id="jingle_popup_mask"></div>');
        _mask = $('#jingle_popup_mask');
        _popup = $('#jingle_popup');
        _subscribeEvents();
    }

    var show = function(options){
        var settings = {
            height : undefined,//�߶�
            width : undefined,//���
            opacity : 0.3,//͸����
            url : null,//Զ�̼���url
            tplId : null,//����ģ��ID
            tplData : null,//ģ�����ݣ����tplIdʹ��
            html : '',//popup����
            pos : 'center',//λ�� {@String top|top-second|center|bottom|bottom-second}   {@object  css��ʽ}
            clickMask2Close : true,// �Ƿ���������ֹر�popup
            showCloseBtn : true,// �Ƿ���ʾ�رհ�ť
            arrowDirection : undefined,//popover�ļ�ͷָ��
            animation : true,//�Ƿ���ʾ����
            timingFunc : 'linear',
            duration : 200,//����ִ��ʱ��
            onShow : undefined //@event ��popup���ݼ�����ϣ�������ʼǰ����
        }
        $.extend(settings,options);
        clickMask2close = settings.clickMask2Close;
        _mask.css('opacity',settings.opacity);
        //rest position and class
        _popup.attr({'style':'','class':''});
        settings.width && _popup.width(settings.width);
        settings.height && _popup.height(settings.height);
        var pos_type = $.type(settings.pos);
        if(pos_type == 'object'){// style
            _popup.css(settings.pos);
            transition = ANIM['defaultAnim'];
        }else if(pos_type == 'string'){
            if(POSITION[settings.pos]){ //�Ѿ�Ĭ�ϵ���ʽ
                _popup.css(POSITION[settings.pos])
                var trans_key = settings.pos.indexOf('top')>-1?'top':(settings.pos.indexOf('bottom')>-1?'bottom':'defaultAnim');
                transition = ANIM[trans_key];
            }else{// pos Ϊ class
                _popup.addClass(settings.pos);
                transition = ANIM['defaultAnim'];
            }
        }else{
            console.error('����Ĳ�����');
            return;
        }
        _mask.show();
        var html;
        if(settings.html){
            html = settings.html;
        }else if(settings.url){//Զ�̼���
            html = J.Page.loadContent(settings.url);
        }else if(settings.tplId){//����ģ��
            html = template(settings.tplId,settings.tplData)
        }

        //�Ƿ���ʾ�رհ�ť
        if(settings.showCloseBtn){
            html += '<div id="tag_close_popup" data-target="closePopup" class="icon cancel-circle"></div>';
        }
        //popover ��ͷ����
        if(settings.arrowDirection){
            _popup.addClass('arrow '+settings.arrowDirection);
            _popup.css('padding','8px');
            if(settings.arrowDirection=='top'||settings.arrowDirection=='bottom'){
                transition = ANIM[settings.arrowDirection];
            }
        }

        _popup.html(html).show();
        J.Element.init(_popup);
        //ִ��onShow�¼������Զ�̬�������
        settings.onShow && settings.onShow.call(_popup);

        //��ʾ��ȡ�����߶ȣ���������ֱ����
        if(settings.pos == 'center'){
            var height = _popup.height();
            _popup.css('margin-top','-'+height/2+'px')
        }
        if(settings.animation){
            J.anim(_popup,transition[0],settings.duration,settings.timingFunc);
        }
        J.hasPopupOpen = true;
    }

    /**
     * �رյ�����
     * @param noTransition �����رգ��޶���
     */
    var hide = function(noTransition){
        _mask.hide();
        if(transition && !noTransition){
            J.anim(_popup,transition[1],200,function(){
                _popup.hide().empty();
                J.hasPopupOpen = false;
            });
        }else{
            _popup.hide().empty();
            J.hasPopupOpen = false;
        }

    }
    var _subscribeEvents = function(){
        _mask.on('tap',function(){
            clickMask2close &&  hide();
        });
        _popup.on('tap','[data-target="closePopup"]',function(){hide();});
    }

    /**
     * alert���
     * @param title ����
     * @param content ����
     */
    var alert = function(title,content,btnName){
        var markup = TEMPLATE.alert.replace('{title}',title).replace('{content}',content).replace('{ok}',btnName || 'ȷ��');
        show({
            html : markup,
            pos : 'center',
            clickMask2Close : false,
            showCloseBtn : false
        });
    }

    /**
     * confirm ���
     * @param title ����
     * @param content ����
     * @param okCall ȷ����ťhandler
     * @param cancelCall ȡ����ťhandler
     */
    var confirm = function(title,content,okCall,cancelCall){
        var markup = TEMPLATE.confirm.replace('{title}',title).replace('{content}',content).replace('{cancel}','ȡ��').replace('{ok}','ȷ��');
        show({
            html : markup,
            pos : 'center',
            clickMask2Close : false,
            showCloseBtn : false
        });
        $('#popup_btn_container [data-icon="checkmark"]').tap(function(){
            hide();
            okCall.call(this);
        });
        $('#popup_btn_container [data-icon="close"]').tap(function(){
            hide();
            cancelCall.call(this);
        });
    }

    /**
     * ����ͷ�ĵ�����
     * @param html ����������
     * @param pos λ��
     * @param arrow_direction ��ͷ����
     * @param onShow onShow�¼�
     */
    var popover = function(html,pos,arrow_direction,onShow){
        show({
            html : html,
            pos : pos,
            showCloseBtn : false,
            arrowDirection : arrow_direction,
            onShow : onShow
        });
    }

    /**
     * loading���
     * @param text �ı���Ĭ��Ϊ��������...��
     */
    var loading = function(text){
        var markup = TEMPLATE.loading.replace('{title}',text||'������...');
        show({
            html : markup,
            pos : 'loading',
            opacity :.1,
            animation : true,
            clickMask2Close : false
        });
    }

    /**
     * actionsheet���
     * @param buttons ��ť����
     * [{color:'red',text:'btn',handler:function(){}},{color:'red',text:'btn',handler:function(){}}]
     */
    var actionsheet = function(buttons){
        var markup = '<div class="actionsheet">';
        $.each(buttons,function(i,n){
            markup += '<button style="background-color: '+ n.color +' !important;">'+ n.text +'</button>';
        });
        markup += '<button class="alizarin">ȡ��</button>';
        markup += '</div>';
        show({
            html : markup,
            pos : 'bottom',
            showCloseBtn : false,
            onShow : function(){
                $(this).find('button').each(function(i,button){
                    $(button).on('tap',function(){
                        if(buttons[i] && buttons[i].handler){
                            buttons[i].handler.call(button);
                        }
                        hide();
                    });
                });
            }
        });
    }

    _init();

    return {
        show : show,
        close : hide,
        alert : alert,
        confirm : confirm,
        popover : popover,
        loading : loading,
        actionsheet : actionsheet
    }
})(J.$);
/**
 * �������
 * ��zepto��tap�¼���ע����һ����ʱ������ʵ�ֵ��̬
 */
J.Selected = (function($){
    var DELAY = 100,SELECTOR='[data-selected]';
    var _trigger = $.fn.trigger;
    $.fn.trigger = function (event) {
        var $this = $(this), args = arguments, classname;
        if (event === 'tap' || event.type === 'tap') {
            var match = $this.closest(SELECTOR).get(0);
            if(match){
                match = $(match);
                classname = match.data('selected');
                match.addClass(classname);
                setTimeout(function () {
                    match.removeClass(classname);
                    _trigger.apply($this, args);
                    $this = match = null;
                }, DELAY);
                return this;
            }
        }
        _trigger.apply($this, args);
        return this;
    }
})(J.$);
/**
 * ���ݻ���
 * todo  �����ݽ��м���
 */
J.Cache = (function($){
    var UNPOST_KEY = '_J_P_',
        GET_KEY_PREFIX = '_J_';

    /**
     * ����ӷ���˻�ȡ������
     * @param key
     * @param value
     */
    var save = function(key,value){
        var data = {
            data : value,
            cacheTime : new Date()
        }
        window.localStorage.setItem(GET_KEY_PREFIX+key,JSON.stringify(data));
    }
    /**
     * ��ȡ�����ѻ��������
     */
    var get = function(key){
        return JSON.parse(window.localStorage.getItem(GET_KEY_PREFIX+key));
    }

    /**
     * ���汾�ش��ύ������˵�����(���߲���)
     * @param url
     * @param result
     */
    var savePost = function(url,result){
        var data = getPost();
        data = data || {};
        data[url] = {
            data : result,
            createdTime : new Date()
        }
        window.localStorage.setItem(UNPOST_KEY,JSON.stringify(data));
    }

    /**
     *  ��ȡ������δ�ύ������˵Ļ�������
     * @param url  û�оͷ�������δͬ��������
     */
    var getPost = function(url){
        var data = JSON.parse(window.localStorage.getItem(UNPOST_KEY));
        return (data && url ) ? data[url] : data;
    }
    /**
     * �Ƴ�δ�ύ�Ĵ��ύ������˵Ļ�������
     * @param url û�о��Ƴ�����δ�ύ������
     */
    var removePost = function(url){
        if(url){
            var data = getPost();
            delete data[url];
            window.localStorage.setItem(UNPOST_KEY,JSON.stringify(data));
        }else{
            window.localStorage.removeItem(UNPOST_KEY);
        }
    }
    /**
     * ͬ������δ�ύ�����ݵ������
     * * @param url û�о�ͬ������δ�ύ������
     */
    var syncPost = function(url,success,error){
        var dataLen,index = 0;
        if($.type(url) == 'string'){
            dataLen = 1;
            sync(url);
        }else{
            var postData = getPost();
            if(!postData)return;
            dataLen = postData.length;
            for(var url in postData){
                sync(url);
            }
        }
        function sync(url){
            var data = getPost(url).data;
            $.ajax({
                url : url,
                contentType:'application/json',
                data : data,
                type : 'post',
                success : function(){
                    index++;
                    removePost(url);
                    if(index == dataLen)success(url);
                },
                error : function(){
                    error(url);
                }
            })
        }
    }

    /**
     * ��ձ��ػ���
     */
    var clear = function(){
        var storage = window.localStorage;
        for(var key in storage){
            if(key.indexOf(GET_KEY_PREFIX) == 0){
                storage.removeItem(key);
            }
        }
        storage.removeItem(UNPOST_KEY);
    }


    return {
        get : get,
        save : save,
        getPost : getPost,
        savePost : savePost,
        removePost : removePost,
        syncPost : syncPost,
        clear : clear
    }

})(J.$);
;(function($){
    /**
     * �������
     * @param selector selector
     * @param options ���ò���
     */
    var calendar = function(selector,options){
        var defaults = {
                months : ["01��", "02��", "03��", "04��", "05��", "06��",
                    "07��", "08��", "09��", "10��", "11��", "12��"],
                days : ["��", "һ", "��", "��", "��", "��", "��"],
                swipeable : true,//�Ƿ��ͨ����ָ����
                date : new Date(),//������ǰ����
                onRenderDay : undefined,//��Ⱦ��Ԫ��ʱ���¼�
                onSelect : undefined //ѡ������ʱ���¼�
            },
            _this = this,
            $el = $(selector),
            $yearText,
            $monthText,
            $calendarBody,
            currentDate,currentYear,currentMonth;

        var _init = function(){
            _this.settings = $.extend({},defaults,options);
            currentYear = _this.settings.date.getFullYear();
            currentMonth = _this.settings.date.getMonth();
            currentDate = new Date(currentYear,currentMonth,_this.settings.date.getDate());
            _render();
            _subscribeEvents();
        }

        /**
         * ��ȡ�·ݵ�һ�������ڼ�[0-6]
         */
        var _fisrtDayOfMonth = function(date){
            return ( new Date(date.getFullYear(), date.getMonth(), 1) ).getDay();
        }
        /**
         * ��ȡ�·�������[1-31]
         */
        var _daysInMonth = function(date){
            return ( new Date(date.getFullYear(),date.getMonth()+1,0) ).getDate();
        }

        /**
         * ��Ⱦ����
         * @private
         */
        var _render = function(){
            var html = '';
            html += '<div class="jingle-calendar">';
            html += _renderNav(currentYear,currentMonth);
            html += _renderHead();
            html += '<div class="jingle-calendar-body">';
            html += _renderBody(currentDate);
            html += '</div></div>'
            $el.html(html);
            var $span = $el.find('span');
            $yearText = $span.eq(0);
            $monthText = $span.eq(1);
            $calendarBody = $el.find('.jingle-calendar-body');
        }

        var _renderNav = function(year,month){
            var html = '<div class="jingle-calendar-nav">';
            html += '<div> <i class="icon previous" data-year='+year+'></i><span>'+year+'</span><i class="icon next" data-year='+year+'></i></div>';
            html += '<div ><i class="icon previous" data-month='+month+'></i> <span>'+_this.settings.months[month]+'</span><i class="icon next" data-month='+month+'></i></div>';
            html += '</div>';
            return html;
        }

        var _renderHead = function(){
            var html = '<table><thead><tr>';
            for(var i = 0; i < 7; i++){
                html += '<th>'+_this.settings.days[i]+'</th>';
            }
            html += '</tr></thead></table>'
            return html;
        }

        var _renderBody = function(date){
            var firstDay = _fisrtDayOfMonth(date),
                days = _daysInMonth(date),
                rows = Math.ceil((firstDay+days) / 7),
                beginDate,
                html = '';
            currentYear = date.getFullYear();
            currentMonth = date.getMonth();
            beginDate = new Date(currentYear,currentMonth,1-firstDay);//������ʼ������
            html += '<table><tbody>';
            for(var i = 0; i < rows; i++){
                html += '<tr>';
                for(var j = 0; j < 7; j++){
                    html += _renderDay(beginDate,currentMonth);
                    beginDate.setDate(beginDate.getDate() + 1);
                }
                html += '</tr>';
            }
            html += '</tbody></table>';
            return html;
        }
        var _renderDay = function(date,month){
            var otherMonth = (date.getMonth() !== month);
            var dateStr = _this.format(date);
            var classname = (_this.format(_this.settings.date) == dateStr) ? 'active':'';
            var dayStr = date.getDate();
            if(_this.settings.onRenderDay){
                dayStr = _this.settings.onRenderDay.call(null,dayStr,dateStr);
            }
            return otherMonth ? '<td>&nbsp;</td>' : '<td data-selected="selected" class="'+classname+ '" data-date= '+dateStr+'>'+dayStr+'</td>';
        }

        var _subscribeEvents = function(){
            var $target,$ctarget;
            $el.on('tap',function(e){
                $target = $(e.target);
                if($target.is('[data-year].next')){
                    //��һ��
                    currentDate.setFullYear(currentDate.getFullYear()+1);
                    _this.refresh(currentDate);
                }else if($target.is('[data-year].previous')){
                    //ǰһ��
                    currentDate.setFullYear(currentDate.getFullYear()-1);
                    _this.refresh(currentDate);
                }else if($target.is('[data-month].next')){
                    //��һ��
                    currentDate.setMonth(currentDate.getMonth()+1);
                    _this.refresh(currentDate);
                }else if($target.is('[data-month].previous')){
                    //ǰһ��
                    currentDate.setMonth(currentDate.getMonth()-1);
                    _this.refresh(currentDate);
                }
                $ctarget = $target.closest('td');
                if(!$target.is('td') && $ctarget.length > 0){
                    $target = $ctarget;
                }
                if($target.is('td')){
                    var dateStr = $target.data('date');
                    if(dateStr && _this.settings.onSelect){
                        _this.settings.onSelect.call(_this,dateStr)
                    }
                }

            });
            $el.on('swipeLeft',function(){
                currentDate.setMonth(currentDate.getMonth()+1);
                _this.refresh(currentDate);
            });
            $el.on('swipeRight',function(){
                currentDate.setMonth(currentDate.getMonth()-1);
                _this.refresh(currentDate);
            })
        }

        /**
         * ˢ������Ϊָ������
         * @param date ָ������
         */
        this.refresh = function(date){
            var oldDate = new Date(currentYear,currentMonth,1),
                newDate = new Date(date.getFullYear(),date.getMonth(),1),
                transition = undefined,$table;

            if(oldDate.getTime() == newDate.getTime())return;
            transition = oldDate<newDate ? 'slideLeftRound' : 'slideRightRound';

            $yearText.text(date.getFullYear());
            $monthText.text(this.settings.months[date.getMonth()]);
            var newbody = _renderBody(date);
            J.anim($calendarBody,transition,function(){
                $calendarBody.html(newbody);
            });

        }
        _init();
    }
    /**
     * �ַ���ת��Ϊ���ڶ���ֻ֧��yyyy-MM-dd �� yyyy/MM/dd
     * @param date
     * @return {*}
     */
    calendar.prototype.parse = function(date){
        var dateRE = /^(\d{4})(?:\-|\/)(\d{1,2})(?:\-|\/)(\d{1,2})$/;
        return dateRE.test(date) ? new Date(parseInt(RegExp.$1, 10), parseInt(RegExp.$2, 10) - 1, parseInt(RegExp.$3, 10)) : null;
    }
    /**
     * ��ʽ������  yyyy-MM-dd
     * @return {String}
     */
    calendar.prototype.format = function(date){
        var y  = date.getFullYear(),m = date.getMonth()+1,d = date.getDate();
        m = (m<10)?('0'+m):m;
        d = (d<10)?('0'+d):d;
        return y + '-' + m + '-' + d;
    }
    J.Calendar = calendar;
})(J.$);
/**
 *  ���ݶ������(iscroll)
 */
;(function($){
    var scrollCache = {},index = 1;
    J.Scroll = function(selector,opts){
        var scroll,scrollId,$el = $(selector),
            options = {
               hScroll : false,
               bounce : false,
               lockDirection : true,
               useTransform: true,
               useTransition: false,
               checkDOMChanges: false,
               onBeforeScrollStart: function (e) {
                    var target = e.target;
                    while (target.nodeType != 1) target = target.parentNode;
                    if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
                        e.preventDefault();
                }
            };
        scrollId = $el.data('_jscroll_');
        //�������ʹ��Ƶ��������������ʡ����
        if(scrollId && scrollCache[scrollId]){
            scroll = scrollCache[scrollId];
            $.extend(scroll.scroller.options,opts)
            scroll.scroller.refresh();
            return scroll;
        }else{
            scrollId = '_jscroll_'+index++;
            $el.data('_jscroll_',scrollId);
            $.extend(options,opts);
            scroller = new iScroll($el[0],options);
            return scrollCache[scrollId] = {
                scroller : scroller,
                destroy : function(){
                    scroller.destroy();
                    delete scrollCache[scrollId];
                }
            };
        };
    }
})(J.$);


/**
 * �õ�Ƭ���
 */
;(function($){
    function slider(selector,showDots){
        var afterSlide = function(){},
            beforeSlide = function(){return true},
            gestureStarted = false,
            index = 0,
            speed = 200,
            wrapper,
            dots,
            container,
            slides,
            slideNum,
            slideWidth,
            deltaX,
            autoPlay
            interval = 0;
        var _this = this;

        if($.isPlainObject(selector)){
            wrapper = $(selector.selector);
            showDots = selector.showDots;
            beforeSlide = selector.onBeforeSlide || beforeSlide;
            afterSlide = selector.onAfterSlide || afterSlide;
            autoPlay = selector.autoPlay;
            interval = selector.interval || 3000;
        }else{
            wrapper = $(selector);
        }
        /**
         * ��ʼ��������С
         */
        var _init = function() {
            container = wrapper.children().first();
            slides = container.children();
            slideNum = slides.length;
            slideWidth = wrapper.offset().width;
            container.css('width',slideNum * slideWidth);
            slides.css({
                    'width':slideWidth,
                    'float':'left'
            }).show();
            if(showDots == undefined)showDots = true;
            showDots && _initDots();
            _slide(0, 0);
            afterSlide(0);
            autoPlay && _autoPlay();
        };

        var _autoPlay = function(){
            setTimeout(function(){
                if(index == slideNum - 1){
                    _slide(0);
                }else{
                    _this.next();
                }
                _autoPlay();
            },interval);
        }

        var _initDots = function(){
            dots = wrapper.find('.dots');
            if(dots.length>0){
                dots.show();
            }else{
                var dotsWidth = slideNum*30+20+2;
                var html = '<div class="dots"><ul>';
                for(var i=0;i<slideNum;i++){
                    html +='<li index="'+i+'"';
                    if(i == 0){
                        html += 'class="active"';
                    }
                    html += '><a href="#"></a></li>';
                }
                html += '</ul></div>';
                wrapper.append(html);
                dots = wrapper.find('.dots');
                dots.children().css('width',dotsWidth+'px');
                dots.find('li').on('tap',function(){
                    var index = $(this).attr('index');
                    _slide(parseInt(index), speed);
                })
            }
        }

        /**
         * ������ָ����Ƭ
         * @param i
         * @param duration
         * @private
         */
        var _slide = function(i, duration) {
            duration = duration || speed;
            container.css({
                '-webkit-transition-duration':duration + 'ms',
                '-webkit-transform':'translate3D(' + -(i * slideWidth) + 'px,0,0)'
            });
            if(index != i){
                index = i;
                if(dots) $(dots.find('li').get(index)).addClass('active').siblings().removeClass('active');
                afterSlide(index);
            }
        };

        /**
         * �󶨻����¼�
         */
        var _bindEvents = function() {
            container.on('touchstart',_touchStart,false);
            container.on('touchmove',_touchMove,false);
            container.on('touchend',_touchEnd,false);
        };

        var  _touchStart = function(event) {
            var e = event.touches[0];
            start = {
                pageX: e.pageX,
                pageY: e.pageY,
                time: Number(new Date())
            };
            isScrolling = undefined;
            deltaX = 0;
            container[0].style.webkitTransitionDuration = 0;
            gestureStarted = true;
        };

        var _touchMove = function(event) {
            if(!gestureStarted)return;
            var e = event.touches[0];
            deltaX = e.pageX - start.pageX;
            if ( typeof isScrolling == 'undefined') {
                //����X��Y���ƫ�����ж��û�����ͼ�����һ����������»���
                isScrolling = Math.abs(deltaX) < Math.abs(e.pageY - start.pageY)
            }
            if (!isScrolling) {
                event.preventDefault();
                //�ж��Ƿ�ﵽ�˱߽缴��һ���һ������һ����
                var isPastBounds = !index && deltaX > 0 || index == slideNum - 1 && deltaX < 0;
                if(isPastBounds)return;
                var pos = (deltaX - index * slideWidth);
                container[0].style.webkitTransform = 'translate3D('+pos+'px,0,0)';
            }
        };

        var _touchEnd = function(e) {
            //�ж��Ƿ���ת����һ����Ƭ
            //����ʱ��С��250ms���߻���X��ľ��������Ļ��ȵ�1/3����ֱ����ת����һ����Ƭ
            var isValidSlide = (Number(new Date()) - start.time < 250 && Math.abs(deltaX) > 20) || Math.abs(deltaX) > slideWidth/3;
                //�ж��Ƿ�ﵽ�˱߽缴��һ���һ������һ����
            var isPastBounds = !index && deltaX > 0 || index == slideNum - 1 && deltaX < 0;
            if (!isScrolling) {
                if(beforeSlide(index,deltaX)){
                    _slide( index + ( isValidSlide && !isPastBounds ? (deltaX < 0 ? 1 : -1) : 0 ), speed );
                }else{
                    _slide(index);
                }
            }
            gestureStarted = false;
        };


        _init();
        _bindEvents();

        this.refresh = function(){
            container.attr('style','');
            _init();
        };

        this.prev = function() {
            if (index) _slide(index-1, speed);
        };

        this.next = function() {
            if(index < slideNum-1){
                _slide(index+1, speed);
            }
        };
        this.index = function(i) {
            _slide(i);
        };
    }
    J.Slider = slider;
})(J.$);
/**
 * ����/�������
 */
;(function($){
    var refreshCache = {},index = 1;
    function Refresh(selector,type,callback){
        var iscroll, scroller,refreshEl,iconEl,labelEl,topOffset,isPullDown,
            options = {
                selector : undefined,
                type : 'pullDown',//pullDown|pullUp Ĭ��ΪpullDown
                minPullHeight : 10,//�������������ֵ�������Żᷭת
                pullText: "����ˢ��...",
                releaseText: "�ɿ�����ˢ��...",
                refreshText: "ˢ����...",
                refreshTip : false,//����ʱ��ʾ���ı������磺������ʱ��:2013-....
                onPullIcon : 'arrow-down-2',
                onReleaseIcon  : 'icon-reverse',
                onRefreshIcon : 'spinner',
                callback : undefined
            };
        //װ������
        if(typeof selector === 'object'){
            $.extend(options,selector);
        }else{
            options.selector = selector;
            options.type = type;
            options.callback = callback;
            if(type === 'pullUp'){
                $.extend(options,{
                    pullText: "�������ظ���...",
                    releaseText: "�ɿ�����������...",
                    refreshText: "������...",
                    onPullIcon : 'arrow-up-3'
                })
            }
        }
        isPullDown = options.type === 'pullDown' ? true : false;

        /**
         * ��ʼ��dom�ڵ�
         * @param opts
         * @private
         */
        var _init = function(opts){
            scroller = $(opts.selector).children()[0];
            var refreshTpl = '<div class="refresh-container"><span class="refresh-icon icon '+opts.onPullIcon
                +'"></span><span class="refresh-label">'
                +opts.pullText+'</span>'
                +(opts.refreshTip?'<div class="refresh-tip">'+opts.refreshTip+'</div>':'')+'</div>';
            if(isPullDown){
                refreshEl = $(refreshTpl).prependTo(scroller);
            }else{
                refreshEl = $(refreshTpl).appendTo(scroller);
            }
            topOffset = refreshEl.height();
            iconEl = refreshEl.find('.refresh-icon');
            labelEl = refreshEl.find('.refresh-label');
        }

        /**
         * ����iscroll��������󶨻����¼�
         * @param opts
         * @private
         */
        var _excuteScroll = function(opts){
            return J.Scroll(opts.selector,{
                    topOffset:isPullDown?topOffset:0,
                    bounce : true,
                    onScrollMove : function(){
                        if (this.y > opts.minPullHeight && isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                            iconEl.addClass(opts.onReleaseIcon);
                            labelEl.html(opts.releaseText);
                            this.minScrollY = 0;
                        } else if (this.y < opts.minPullHeight && isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                            iconEl.removeClass(opts.onReleaseIcon);
                            labelEl.html(opts.pullText);
                            this.minScrollY = -topOffset;
                        }else if (this.y < (this.maxScrollY - opts.minPullHeight) && !isPullDown && !iconEl.hasClass(opts.onReleaseIcon)) {
                            iconEl.addClass(opts.onReleaseIcon);
                            labelEl.html(opts.releaseText);
                            this.maxScrollY = this.maxScrollY;
                        } else if (this.y > (this.maxScrollY + opts.minPullHeight) && !isPullDown && iconEl.hasClass(opts.onReleaseIcon)) {
                            iconEl.removeClass(opts.onReleaseIcon);
                            labelEl.html(opts.pullText);
                            this.maxScrollY = topOffset;
                        }
                    },
                    onScrollEnd : function(){
                        if(iconEl.hasClass(opts.onReleaseIcon)){
                            iconEl.removeClass(opts.onReleaseIcon).removeClass(opts.onPullIcon).addClass(opts.onRefreshIcon);
                            labelEl.html(opts.refreshText);
                            var _this = this;
                            setTimeout(function(){//�����chrome��onRefresh��ʱ���ı��޷����ĵ����⡣��ֵ����⣡
                                opts.callback.call(_this);
                            },1);

                        }
                    },
                    onRefresh: function () {
                        iconEl.removeClass(opts.onRefreshIcon).addClass(opts.onPullIcon);
                        labelEl.html(opts.pullText);
                    }
                });
        }

        //run
        _init(options);
        iscroll = _excuteScroll(options);
        return iscroll;

    }

    /**
     * ˢ�����
     * @param selector selector
     * @param type ���� pullDown(����) pullUp(����)
     * @param callback �ص�����
     */
    J.Refresh = function(selector,type,callback){
        var el,jRefreshId;
        if(selector.selector){
            el = $(selector.selector)
        }else{
            el = $(selector);
        }
        jRefreshId = el.data('_jrefresh_');
        //�������������ܻ�ʹ�õıȽ�Ƶ�����ʻ���������ʡ����,��ɷ�ֹ�ظ���
        if(jRefreshId && refreshCache[jRefreshId]){
            return refreshCache[jRefreshId];
        }else{
            jRefreshId = '_jrefresh_'+index++;
            el.data('_jrefresh_',jRefreshId);
            var refresh = new Refresh(selector,type,callback);
            return refreshCache[jRefreshId] = {
                scroller : refresh.scroller,
                destroy : function(){
                    delete refreshCache[jRefreshId];
                    refresh.scroller.destroy();
                    $('.refresh-container',selector).remove();
                }
            };
        }
    }
})(J.$);