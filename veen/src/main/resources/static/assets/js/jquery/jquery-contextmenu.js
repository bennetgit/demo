/**
 * Created by Hong on 2016/12/21.
 */
(function($){
    jQuery.menuIndex = 0;
    $.fn.showMenu = function(options) {
        var defaults = {title: "this is title.", datas: []};
        var opts = $.extend(defaults, options);
        var menuId = 0;
        $(this).click(function(){
            $.selectActive(this);
        });

        $(this).contextmenu(function(e){
            var this_ = this;
            $.selectActive(this);

            $("div[id^=menuId]").remove();

            //菜单按钮
            menuId = "menuId"+$.menuIndex;

            //组装数据
            var menu = $("<div class='menu' id='"+menuId+"'></div>")
            menu.append("<h1 class='title'>"+opts.title+"</h1>")
            menu.append("<ul></ul>");
            $(opts.datas).each(function(index){//绑定菜单项数据
                var li = $("<li>"+this.text+"</li>");
                var func = this.func;
                li.click(function(){
                    func(this_);//返回当前点击的HTML对象
                });
                menu.find("ul").append(li);
            });
            menu.css("top", e.clientY);
            menu.css("left", e.clientX);

            $("body").append(menu);//添加到网页

            menu.show();
            $.menuIndex++;
            return false;
        });
        $("body").click(function(){
            $("div[id^=menuId]").fadeOut(100, function(){
                $(this).remove();
            });
        }).contextmenu(function(){
            $("div[id^=menuId]").fadeOut(100, function(){
                $(this).remove();
            });
        });
        return menuId;
    }

    jQuery.selectActive = function(obj){
        $(".active").removeClass("active");
        $(obj).addClass("active");
    }
})(jQuery);