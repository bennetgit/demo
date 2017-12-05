
// directive('first', [ function(){
//     return {
//         scope: false, //默认值为 false 共享父作用域 值为true时共享父级作用域并创建指令自己的
//
//         controller: function($scope, $element, $attrs, $transclude) {}, //作用域  值为{}时创建全新的隔离作用域, 值为string时为控制器名称
//         restrict: 'AE', // E = Element, A = Attribute, C = Class, M = Comment
//         template: 'first name:{{name}}',//值为string、function 用于显示dom元素
//         templateUrl: 'xxx.html' //值为string function 以id为xxx.html为 调用文件显示
//         prioruty: 0 //指明指令的优先级，若在dom上有多个指令优先级高的先执行
//         replace: flase // 默认值为false 当为true是直接替换指令所在的标签
//         terminal: true //值为true时优先级低于此指令的其它指令无效
//         link:function // 值为函数 用来定义指令行为从传入的参数中获取元素并进行处理
//
//     };


mainApp.directive("mineAction", function () {
    return {
        restrict: 'E',
        scope: {
            name: "@",
            action: "&",
            icon: "@"
        },
        template: "<span class='mine-action'><a href='javascript:void(0);' ng-click='action()'><i ng-if='icon != null' ng-class='icon'></i>{{name}}</a></span>",
        replace: true
    }
});
mainApp.directive("mineValidator", function () {
    return {
        restrict: 'E',
        scope: {
            ok: "@",
            error: "@",
            valid: "=",
            when: "=",
            right: "="
        },
        template: "<span class='mine-validator'><span class='help-inline' style='color:#a94442;' ng-show='(when == null || when) && !valid'><i class='fa fa-exclamation-circle'></i><span class='message'>{{error}}</span></span>" +
        "<span ng-if='right' class='help-inline' style='color:green;' ng-show='(when == null || when) && valid'><i class='fa fa-check-circle-o'></i><span class='message'>{{ok}}</span></span></span>",
        replace: true
    }
});
mainApp.directive("mineLabel", function () {
    return {
        restrict: 'E',
        scope: {
            name: "@",
            required: "@"
        },
        template: "<label class='acme-label'>{{name}}<span ng-if='required == \"\"' class='acme-required'>*</span></label>",
        replace: true
    }
});
mainApp.directive("mineDate", function () {
    return {
        restrict: 'E',
        require: 'ngModel',
        template: "<div class='input-group date' style='width: 162px;'>" +
        "<input style='width: 142px;' type='text'class='form-control input-sm' />" +
        "<span class='input-group-addon' style='cursor:pointer;'><span class='glyphicon glyphicon-calendar'></span></span></div>",
        link: function (scope, element, attrs, ngModel) {
            var dateText = $(element).children("input");
            $(dateText).datetimepicker({format: 'Y-m-d H:i:s'});
            $(dateText).change(function () {
                ngModel.$setViewValue($(this).val());
                console.log($(this).val());
            });
            ngModel.$render = function () {
                $(dateText).val(ngModel.$viewValue);
            };
            $(element).find("span[class='input-group-addon']").click(function () {
                $(dateText).trigger("focus");
            });
        },
        replace: true
    }
});
mainApp.directive("mineUmeditor", function ($rootScope) {
    return {
        restrict: 'EA',
        require: 'ngModel',
        link: function (scope, ele, attrs, ngModel) {
            var ctrl = {
                initialized: false,
                editorInstance: null,
                placeholder: attrs['metaUmeditorPlaceholder'] || '',
                focus: false
            };

            var height = attrs["height"];
            if (typeof height == "undefined") {
                height = 200;
            }
            var setting = {initialFrameHeight: height};
            ctrl.init = function () {
                ctrl.createEditor();
                ngModel.$render = function () {
                    if (ctrl.initialized) {
                        ctrl.editorInstance.ready(function () {
                            ctrl.editorInstance.setContent(ngModel.$viewValue || '', false);
                            ctrl.checkPlaceholder();
                        });
                    }
                };
            };
            ctrl.createEditor = function () {
                if (!ctrl.initialized) {
                    umeditorInit($);
                    ctrl.editorInstance = UM.getEditor(attrs['id'], setting);
                    ctrl.initialized = true;
                    ctrl.initListener();
                }
            };
            //修改ngModel Value
            ctrl.updateModelView = function () {
                var modelContent = ctrl.editorInstance.getContent();
                ngModel.$setViewValue(modelContent);
                if (!scope.$root.$$phase) {
                    scope.$apply();
                }
            };
            //监听多个事件
            ctrl.initListener = function () {
                ctrl.editorInstance.addListener('contentChange', function () {
                    scope.$evalAsync(ctrl.updateModelView);
                });
                ctrl.editorInstance.addListener('fullscreenchanged', function (event, isFullScreen) {
                    if (!isFullScreen) {
                        ctrl.editorInstance.setHeight(height);
                        $("body").css("overflow-y", "auto");
                    }
                });
            };
            ctrl.checkPlaceholder = function () {
            };
            ctrl.init();
            $rootScope.$on('$locationChangeStart', function (event) {
                ctrl.editorInstance && ctrl.editorInstance.destroy();
            });
        }
    }
});