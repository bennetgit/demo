/**
 * Created by Hong on 2016/12/15.
 */

/** 通用 **/
var common = {
    reloadCaptcha: function () {//刷新验证码
        $("#captcha").attr("src", "/captcha.jpg?v=" + new Date().getTime());
    },
    form: {
        submit: function (form, title) {
            common.post($(form).attr("src"), $(form).serialize(), title, function () {
                layer.alert(title, {icon: 6}, function () {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.$('.btn-refresh').click();
                    parent.layer.close(index);
                });
            });
        }
    },
    post: function (url, data, title, success) {
        $.post(url, data, function (data) {
            if (data.success) {
                success(data.value);
            } else {
                layer.alert(data.message, {icon: 5});
            }
        }, function (data) {
            layer.alert(data.message, {icon: 5});
        }, "JSON");
    }
}

/** 用户相关 **/
var account = {
    operate: {
        add: function (form) {//添加管理员
            common.form.submit(form, "添加管理员成功！");
        },
        edit: function (form) {//修改管理员
            common.form.submit(form, "修改管理员成功！");
        }
    },
    list: {
        add: function (title, url) {//打开添加管理员的弹窗
            layer_show(title, url);
        },
        edit: function (title, url) {//打开修改管理员的弹窗
            layer_show(title, url);
        },
        remove: function (id) {//是否删除管理员
            var index = layer.confirm("你确定要删除该管理员吗？", {btn: ["删除", "取消"]}, function () {
                common.post("/account/remove.json", {id: id}, "删除成功！", function () {
                    layer.close(index);
                    index = layer.alert("删除成功！", {icon: 6, closeBtn: 0}, function () {
                        $('.btn-refresh').click();
                        layer.close(index);
                    });
                });
            })
        },
        removes: function () {//批量删除管理员
            var ids = new Array();
            $.each($("input[name=id]:checked"), function () {
                ids.push(this.value);
            });
            if (ids.length == 0) {
                layer.alert("请先选择用户！", {icon: 5});
                return;
            }
            var index = layer.confirm("你确定要删除这些管理员吗？", {btn: ["删除", "取消"]}, function () {
                common.post("/account/removes.json", {ids: ids.toString()}, "删除成功！", function () {
                    layer.close(index);
                    index = layer.alert("删除成功！", {icon: 6, closeBtn: 0}, function () {
                        $('.btn-refresh').click();
                        layer.close(index);
                    });
                });
            })
        },
        accountStart: function (id) {//启用
            common.post("/account/start.json", {id: id}, "启用成功！", function () {
                var index = layer.alert("启用成功！", {icon: 6, closeBtn: 0}, function () {
                    $('.btn-refresh').click();
                    layer.close(index);
                });
            });
        },
        accountStop: function (id) {//禁用
            common.post("/account/stop.json", {id: id}, "禁用成功！", function () {
                var index = layer.alert("禁用成功！", {icon: 6, closeBtn: 0}, function () {
                    $('.btn-refresh').click();
                    layer.close(index);
                });
            });
        }
    }
};

/** 角色相关 **/
var role = {
    operate: {
        add: function (form) {//添加角色
            common.form.submit(form, "添加角色成功！");
        },
        edit: function (form) {//修改角色
            common.form.submit(form, "修改角色成功！");
        },
        resources: function (form) {//角色权限管理
            common.form.submit(form, "角色资源更改成功！！");
        }
    },
    list: {
        add: function (title, url) {//打开添加管理员的弹窗
            layer_show(title, url);
        },
        edit: function (title, url) {//打开修改管理员的弹窗
            layer_show(title, url);
        },
        resources: function (title, url) {//打开资源管理的弹窗
            layer_show(title, url);
        },
        remove: function (id) {//是否删除管理员
            var index = layer.confirm("你确定要删除该角色吗？", {btn: ["删除", "取消"]}, function () {
                common.post("/role/remove.json", {id: id}, "删除成功！", function () {
                    layer.close(index);
                    index = layer.alert("删除成功！", {icon: 6, closeBtn: 0}, function () {
                        $('.btn-refresh').click();
                        layer.close(index);
                    });
                });
            })
        },
        removes: function () {//批量删除管理员
            var ids = new Array();
            $.each($("input[name=id]:checked"), function () {
                ids.push(this.value);
            });
            if (ids.length == 0) {
                layer.alert("请先选择角色！", {icon: 5});
                return;
            }
            var index = layer.confirm("你确定要删除这些角色吗？", {btn: ["删除", "取消"]}, function () {
                common.post("/role/removes.json", {ids: ids.toString()}, "删除成功！", function () {
                    layer.close(index);
                    index = layer.alert("删除成功！", {icon: 6, closeBtn: 0}, function () {
                        $('.btn-refresh').click();
                        layer.close(index);
                    });
                });
            })
        }
    }
};

/**资源相关**/
var resource = {
    operate: {
        add: function (form) {//添加资源
            common.form.submit(form, "添加资源成功！");
        },
        edit: function (form) {//修改资源
            common.form.submit(form, "修改资源成功！");
        }
    },
    list: {
        addChildMenu: function (title, url) {//打开添加子菜单的弹窗
            layer_show(title, url);
        },
        addMenu: function (title, url) {//打开添加菜单的弹窗
            layer_show(title, url);
        },
        edit: function (title, url) {//打开修改资源的弹窗
            layer_show(title, url);
        },
        remove: function (id) {//删除资源
            var index = layer.confirm("你确定要删除该资源吗？", {btn: ["删除", "取消"]}, function () {
                common.post("/resource/remove.json", {id: id}, "删除成功！", function () {
                    layer.close(index);
                    index = layer.alert("删除成功！", {icon: 6, closeBtn: 0}, function () {
                        $('.btn-refresh').click();
                        layer.close(index);
                    });
                });
            })
        },
        view: function (id) {//查看
            window.open("/resource/view.html?id=" + id, "iframe_view");
        },
        manager: function (id) {//点击进入管理旗下链接
            window.open("/resource/manager.html?id=" + id, "iframe_manager");
        },
        addLink: function(title, url){//打开添加链接的窗口
            layer_show(title, url);
        }
    }
}