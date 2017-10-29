mainApp.service("mineHttp", function ($http, Upload) {
    this.send = function (method, url, params, callback) {
        var setting = {
            method: method,
            url: fullPath(url),
        };
        $.extend(setting, params);
        $http(setting).then(function successCallback(response) {
            callback(response.data);
        }, function errorCallback(response) {
            alert("request error")
        });
    };
    this.upload = function (url, data, callback) {
        Upload.upload({
            url: fullPath(url),
            data: data,
            file: data.file
        }).progress(function (evt) {
            //进度条
            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
            console.log('progess:' + progressPercentage + '%' + evt.config.file.name);
        }).success(function (data, status, headers, config) {
            callback(data);
        }).error(function (data, status, headers, config) {
            alert("上传失败");
        });
    };
    this.constant = function (type, callback) {
        var url = "admin/constant/" + type;
        this.send("GET", url, {}, function (data) {
            callback(data)
        });
    }

});
mainApp.service("mineUtil", function ($uibModal) {
    this.confirm = function (message, callback) {
        var confirmModal = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: fullPath('comm/confirm.htm'),
            size: 'sm',
            controller: 'confirmController',
            resolve: {
                data: function () {
                    return message;
                }
            }
        });
        confirmModal.result.then(function () {
            callback();
        }, function () {
        });
    };
    this.alert = function (message) {
        var alertModal = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: fullPath('comm/alert.htm'),
            size: 'sm',
            controller: 'alertController',
            resolve: {
                data: function () {
                    return message;
                }
            }
        });
        alertModal.result.then(function () {
        }, function () {
        });
    };
    this.modal = function (templateUrl, controller, data, size) {
        var myModal = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: fullPath(templateUrl),
            size: size,
            controller: controller,
            resolve: {
                data: function () {
                    return data;
                }
            }
        });
        myModal.rendered.then(function () {//模态窗口打开之后执行的函数
            $(".modal-dialog").draggable({
                handle: ".modal-header"   // 只能点击头部拖动
            });
        });
        return myModal;
    };
});