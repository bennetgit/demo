var _pathName = location.pathname;
$(window).load(function() {
    var obj = {};
    $("nav[class='layout-menu'] > a").each(function() {
        if(_pathName.indexOf($(this).attr("href")) > -1){
            obj = this;
            return;
        }
    });
    menuItemSelected(obj);
});
function menuItemSelected(obj) {
    if (!$(obj)[0]) {
        return;
    }
    if (!$(obj).hasClass("selected")) {
        $(obj).addClass("selected");
        $("nav[class='layout-menu'] > a").not(obj).each(function() {
            if ($(this).hasClass("selected")) {
                $(this).removeClass("selected");
            }
        });
    }
}