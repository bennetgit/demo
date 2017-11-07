/**
 * Created by Hong on 2016/12/3.
 */
(function($){
    $.extend({
        post: function(url, data, success, error, dataType){//扩展的POST
            $.ajax({
                url: url,
                type: "POST",
                data: data,
                dataType: dataType,
                success: function(data, status, xhr){
                    if(data.success){
                        success(data, status, xhr);
                    }else{
                        if(data.code == "0x00000010"){//身份失效，请重新登录！
                            window.open("/account/login.html", "_top");
                        }else if(data.code == "0x00000012"){//重新登录！
                            window.open("/account/login.html", "_top");
                        }else{
                            if(data.message == null)
                                layer.alert("Error.", {icon:5});
                            else
                                layer.alert(data.message, {icon:5});
                        }
                    }
                },
                error: function(xhr, status){
                    error($.parseJSON(xhr.responseText), xhr, status);
                }
            });
        },
        get: function(url, data, success, error, dataType){//扩展的GET
            $.ajax({
                url: url,
                type: "GET",
                data: data,
                dataType: dataType,
                success: function(data, status, xhr){
                    if(data.success){
                        success(data, status, xhr);
                    }else{
                        if(data.code == "0x00000010"){//身份失效，请重新登录！
                            window.open("/account/login.html", "_top");
                        }else if(data.code == "0x00000012"){//重新登录！
                            window.open("/account/login.html", "_top");
                        }else{
                            if(data.message == null)
                                layer.alert("Error.", {icon:5});
                            else
                                layer.alert(data.message, {icon:5});
                        }
                    }
                },
                error: function(xhr, status){
                    error($.parseJSON(xhr.responseText), xhr, status);
                }
            });
        }
    });
})(jQuery);