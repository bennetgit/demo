// global variable
var optionYesNo = [{key: "1", value: "是"}, {key: "0", value: "否"}];
var staticSource = {loadImage: "/img/loading.gif"};
var bootstrapPopoverTemplate = '<div style="min-width:200px;" class="popover" role="tooltip">'+
    '<div class="arrow"></div><h4 class="popover-title text-warning"></h4><div class="popover-content"></div></div>';
$(document).ready(function() {
    // if support NProgress
    if(typeof NProgress == "object"){
        NProgress.start();
        $(window).load(function() {
            NProgress.done();
        });
    }
});

(function ($) {

    /** base on bootstarp **/
    $.fn.bindPopover=function(options){
      var defaults={animation:true,content:"content",title:"title",template:bootstrapPopoverTemplate,trigger:"manual"};
      var opts = $.extend(defaults, options);
      $(this).popover(opts);
    }

    $.fn.showPopover=function(showTime){
        var obj = this;
        if(typeof showTime != "number"){
          showTime=1000;
        }
        $(obj).popover("show");
        setTimeout(function(){ $(obj).popover("hide");},showTime)
    }

    /** 自定义 select选项卡 插件 **/
    $.fn.mineSelect = function (jsonOption, options) {
        if (typeof(jsonOption) != "object" || jsonOption.length == 0) {
            return;
        }
        var defaults = {
            allowEmpty: true
        };
        var opts = $.extend(defaults, options);
        var selectBody = "";
        $.each(jsonOption, function (index, value) {
            if (index == 0 && opts.allowEmpty) {
                selectBody += "<option></option>";
            }
            selectBody += "<option value='" + value.key + "'>" + value.value + "</option>";
        });

        $(this).empty().append(selectBody);
    };
    $.fn.mineAjaxSelect = function (url, options) {
        var selectObj = this;
        var defaults = {
            url: url,
            type: "POST",
            async: false,
            traditional: true,
            dataType: "json",
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                window.location.reload();
            },
            success: function (data) {
                if (typeof data == "object") {
                    var selectBody = "";
                    $.each(data, function (index, value) {
                        selectBody += "<option value='" + value.key + "'>" + value.value + "</option>";
                    });
                    $(selectObj).empty().append(selectBody);
                }
            }
        };

        $.ajax($.extend(defaults, options));
    };
    /** 自定义 表单值设定 插件 **/
    $.fn.mineFormSet = function (obj) {
        if (typeof(obj) != "object") {
            return;
        }
        for (var key in obj) {
            var htmlObj = $(this).find("[name='" + key + "']");
            if (typeof (htmlObj) == "object") {
                if ($(htmlObj).is("input")) {
                    $(htmlObj).val(obj[key]);
                } else if ($(htmlObj).is("textarea")) {
                    $(htmlObj).text(obj[key]);
                } else if ($(htmlObj).is("select")) {
                    $(htmlObj).val(obj[key]);
                } else {
                    $(htmlObj).html(obj[key]);
                }
            }
        }
    };
    /** 自定义 消息显示 插件 **/
    $.fn.mineMessage = function (messages) {
        if (typeof(messages) != "object" || messages.length == 0) {
            return;
        }
        $(this).empty();
        if (messages[0].type == "info") {
            $(this).append('<p mine="message" class="alert alert-success"></p>')
        } else {
            $(this).append('<p mine="message" class="alert alert-danger"></p>')
        }
        var messageP = $(this).children("p[mine='message']");
        for (var i = 0; i < messages.length; i++) {
            $(messageP).append(messages[i].message + "<br/>")
        }
    };
    /** 自定义 Ajax异步请求 插件 **/
    $.mineAjax = function (options, shadeArea) {
        var defaults = {
            type: "POST",
            async: true,
            traditional: true,
            dataType: "json",
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("unknown exception");
            }
        };
        var shadeOpt = {};
        if (typeof(shadeArea) == "object") {
            if (typeof ($(shadeArea).children("div[mine='overlay']")) == "object") {
                var body = '<div mine="overlay">';
                body += '<div mine="shadeDiv"></div>';
                body += '<div mine="loadDiv" style="position:absolute;width:100%;text-align:center;top: 50%;margin:0 auto;z-index:9999;">';
                body += '<img src="' + staticSource.loadImage + '"/></div></div>';
                $(shadeArea).append(body);
                $(shadeArea).children("div[mine='overlay']").css({
                    "display": "none", "position": "absolute",
                    "top": "0px", "left": "0px", "width": "100%", "height": "100%", "z-index": 9997
                });
                $(shadeArea).children("div[mine='overlay']").children("div[mine='shadeDiv']").css({
                    "background": "#000",
                    "filter": "alpha(opacity=0)",
                    "opacity": 0,
                    "position": "absolute",
                    "top": "0px",
                    "left": "0px",
                    "width": "100%",
                    "height": "100%",
                    "z-index": 9998
                });

            }
            ;
            var showOverLay = function () {
                $(shadeArea).children("div[mine='overlay']").show();
            };
            var hideOverLay = function () {
                $(shadeArea).children("div[mine='overlay']").hide();
            };
            shadeOpt = {beforeSend: showOverLay, complete: hideOverLay};
        }
        $.ajax($.extend(defaults, options, shadeOpt));
    };
    /** 自定义 序列化form表单对象 插件 **/
    $.fn.mineSerializeObject = function () {
        var obj = {};
        var array = this.serializeArray();
        $.each(array, function () {
            if (obj[this.name]) {
                if (!obj[this.name].push) {
                    obj[this.name] = this.value;
                } else {
                    obj[this.name].push(this.value);
                }
            } else {
                obj[this.name] = this.value;
            }
        });
        return obj;
    };
    $.mineDate = function (format, hours) {
        var nowDate = new Date();
        var fmt = "yyyy-MM-dd hh:mm:ss";
        if (typeof format == "string") {
            fmt = format;
        }

        if (typeof hours == "number") {
            nowDate.setHours(nowDate.getHours() + hours);
        }
        return nowDate.format(fmt);
    }
    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    // 例子：
    // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
    Date.prototype.format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);
