mainApp.controller("systemMenuListCtl", function ($scope, $http, mineTree, mineHttp, mineUtil, mineMessage) {
    var menuTree;
    $scope.ztreeSelected = function (event, treeId, treeNode) {
        $scope.messageStatus = null;
        var url = "menus/" + treeNode.id;
        mineHttp.send("GET", url, {}, function (data) {
            if (verifyData(data)) {
                $scope.menu = data.content;
            } else {
                $scope.menu = null;
            }
        });
    };
    $scope.buildTree = function (callback) {
        mineHttp.send("GET", "menus/treeList", {}, function (data) {
            var options = {
                callback: {
                    onClick: $scope.ztreeSelected
                }
            };
            menuTree = mineTree.build($("#menuTree"), data.content, options);

            if (angular.isFunction(callback)) {
                callback();
            }
        });
    };
    $scope.add = function (oneLevelFlag) {
        var params = null;
        if (!oneLevelFlag) {
            params = $scope.menu;
        }
        var modalInstance = mineUtil.modal("module/system/menu/menuAdd.html", "systemMenuAddController", params);
        modalInstance.result.then(function (selectedItem) {
        }, function () {
        });
    };
    $scope.edit = function () {
        var modalInstance = mineUtil.modal("module/system/menu/menuEdit.html", "systemMenuEditController", $scope.menu);
        modalInstance.result.then(function (selectedItem) {
        }, function () {
        });
    };
    $scope.drop = function () {
        mineUtil.confirm("确认删除吗？", function () {
            var url = "menus/" + $scope.menu.id;
            var parentNodeTemp = menuTree.getNodeByParam("id", $scope.menu.id).getParentNode();
            mineHttp.send("DELETE", url, {}, function (data) {

                if (!verifyData(data)) {
                    $scope.messageStatus = false;
                    $scope.message = data.message;
                    return;
                }
                $scope.menu = null;
                mineUtil.alert("删除成功");
                $scope.buildTree(function () {
                    if (parentNodeTemp != null) {
                        var parentNode = menuTree.getNodeByParam("id", parentNodeTemp.id);
                        menuTree.expandNode(parentNode);
                    }
                });
            });
        });
    };
    $scope.buildTree();
    mineMessage.subscribe("systemMenuTreeRefresh", function (event, nodeId) {
        $scope.buildTree(function () {
            var parentNode = menuTree.getNodeByParam("id", nodeId);
            menuTree.expandNode(parentNode);
        });
    });
});

mainApp.controller("systemMenuAddController", function ($scope, data, $uibModalInstance, mineHttp, mineMessage) {
    $scope.initPage = function () {
        $scope.menu = {};
        if (data == null) {
            $scope.title = "一级菜单";
        } else {
            $scope.title = "二级菜单";
            $scope.menu.parentId = data.id;
            $scope.menu.parentName = data.name;
        }
    };
    $scope.initPage();
    $scope.ok = function () {
        mineHttp.send("POST", "menus", {data: $scope.menu}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
            if ($scope.messageStatus) {
                var nodeId = data == null ? null : data.id;
                mineMessage.publish("systemMenuTreeRefresh", nodeId);
            }
            $scope.initPage();
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

mainApp.controller("systemMenuEditController", function ($scope, data, $uibModalInstance, mineHttp, mineMessage) {
    mineHttp.send("GET", "menus/" + data.id, {}, function (data) {
        if (verifyData(data)) {
            $scope.menu = data.content;
        } else {
            $scope.menu = null;
        }
    });
    $scope.ok = function () {
        mineHttp.send("PUT", "menus/" + $scope.menu.id, {data: $scope.menu}, function (data) {
            $scope.menu = data.content;
            $scope.messageStatus = verifyData(data);
            $scope.message = data.message;
            if ($scope.messageStatus) {
                mineMessage.publish("systemMenuTreeRefresh", data.content.parentId);
            }
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});


//-----------user ------------------------------------------------------------------

mainApp.controller("systemUserListCtl", function ($scope, $uibModal, mineHttp, mineGrid, mineUtil) {
//    mineHttp.constant("approveRole", function (data) {
//        $scope.approveRoles = data.content;
//    });
    $scope.selectedFlag = false;
    mineGrid.gridPageInit("gridOptions", $scope, {
        data: 'myData',
        showSelectionCheckbox: true,
        multiSelect: true,
        selectWithCheckboxOnly: true,
        requestMethod: "POST",
        requestUrl: fullPath("users/list"),
        columnDefs: [{field: 'username', displayName: '用户名'},
            {
                field: 'sex',
                displayName: '性别',
                cellTemplate: "<span class='mine-table-span'>{{row.entity.sexType}}</span>"
            },
            {field: 'mobile', displayName: '联系电话'},
            {field: 'createdOn', displayName: '创建时间'},
            {field: 'statusMessage', displayName: '状态'},
            {
                field: 'id',
                displayName: '操作',
                width: 200,
                sortable: false,
                cellTemplate: "<div><mine-action icon='fa fa-edit' action='edit(row.entity)' name='编辑'></mine-action>" +
                "<mine-action icon='fa fa-sticky-note-o' action='detail(row.entity)' name='查看'></mine-action>" +
                "<mine-action icon='fa fa-user-o' action='setRole(row.entity)' name='角色'></mine-action></div>"
            }

        ]
    });
    // init load datas
    $scope.gridPageQueryCallback = function (data) {
        return {data: data.content.rows, total: data.content.total};
    };
    $scope.gridPageSelectedItems = function (newValue, oldValue) {
        if (newValue != 0) {
            $scope.selectedFlag = true;
        } else {
            $scope.selectedFlag = false;
        }
    };
    $scope.query = function () {
        $scope.gridPageQuery({}, $scope.userQuery);
    };
    $scope.query();

    $scope.add = function () {
        var modalInstance = mineUtil.modal("module/system/user/userAdd.html", "systemUserAddController", {});
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.edit = function (user) {
        var modalInstance = mineUtil.modal("module/system/user/userEdit.html", "systemUserEditController", user);
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.detail = function (user) {
        var modalInstance = mineUtil.modal("module/system/user/userDetail.html", "systemUserDetailController", user);
        modalInstance.result.then(function () {
        }, function () {
        });
    };
    $scope.setRole = function (user) {
        var modalInstance = mineUtil.modal("module/system/user/userRole.html", "systemUserRoleController", user);
        modalInstance.result.then(function () {
        }, function () {
        });
    };
    $scope.enable = function (enabled) {
        var selectedItems = $scope.gridSelectedItems;
        if (selectedItems == null || selectedItems.length == 0) {
            return;
        }
        $scope.userSelect = {};
        $scope.userSelect.enabled = enabled;
        $scope.userSelect.userIds = new Array();
        for (var index in selectedItems) {
            $scope.userSelect.userIds.push(selectedItems[index].id);
        }
        mineHttp.send("PUT", "users/status", {data: $scope.userSelect}, function (result) {
            if (verifyData(result)) {
                $scope.query();
            }
        });
    }
});

mainApp.controller("systemUserSettingProfileCtl", function ($scope, mineHttp) {

    mineHttp.send("GET","users/current",{}, function(result){
        $scope.user = result.content;
    });

});

mainApp.controller("systemUserSettingUpdateCtl", function ($scope, mineHttp) {

    $scope.clearError=function(){
        $('#error_td_old').empty();
    };

    $scope.submit = function () {

        mineHttp.send("POST", "users/current/pw/check", {data: $scope.user}, function (result) {
            if (!result.content) {
                $('#error_td_old').empty().html(
                    "<span class='help-inline' style='color:#a94442;'>" +
                    "<i class='fa fa-exclamation-circle'/>" +
                    "<span class='message'>密码错误</span>" +
                    "</span>"
                );
            } else {
                mineHttp.send("PUT", "users/update/password", {data: $scope.user}, function (result) {
                    $scope.messageStatus = verifyData(result);
                    $scope.message = result.message;
                });
            }
        });


    };

});

mainApp.controller("systemUserAddController", function ($scope, $uibModalInstance, mineHttp) {

//    mineHttp.constant("approveRole", function (data) {
//        $scope.approveRoles = data.content;
//    });

    $scope.ok = function () {
        mineHttp.send("POST", "users", {data: $scope.user}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
            if ($scope.messageStatus) {
                $scope.user = null;
            }
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemUserEditController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.send("GET", "users/" + data.id, {}, function (result) {
        if (!verifyData(result)) {
            $scope.messageStatus = false;
            $scope.message = result.message;
        }
        $scope.user = result.content;
    });
    $scope.ok = function () {
        mineHttp.send("PUT", "users/", {data: $scope.user}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemUserDetailController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.send("GET", "users/" + data.id, {}, function (result) {
        $scope.message = result.message;
        $scope.user = result.content;
    });
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemUserRoleController", function ($scope, $uibModalInstance, mineHttp, mineTree, data) {
    var roleTree = {};
    mineHttp.send("GET", "users/" + data.id + "/role", {}, function (result) {
        $scope.message = result.message;
        $scope.user = result.content.user;
        var options = {check: {enable: true}};
        roleTree = mineTree.build($("#roleTree"), result.content.roleTree, options);
    });
    $scope.ok = function () {
        $scope.userRole = {};
        $scope.userRole.id = data.id;
        $scope.userRole.roleIds = new Array();
        var roleNodes = roleTree.getCheckedNodes(true);
        for (var index in roleNodes) {
            $scope.userRole.roleIds.push(roleNodes[index].id);
        }
        mineHttp.send("PUT", "users/" + data.id + "/role", {data: $scope.userRole}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

//-------------- role ------------------------------------------------------------------
mainApp.controller("systemRoleListCtl", function ($scope, $uibModal, mineHttp, mineGrid, mineUtil) {
    $scope.myData = [];
    mineGrid.gridPageInit("gridOptions", $scope, {
        data: 'myData',
        showSelectionCheckbox: true,
        multiSelect: true,
        selectWithCheckboxOnly: true,
        requestMethod: "POST",
        requestUrl: fullPath("roles/list"),
        columnDefs: [
            {field: 'name', displayName: '角色名'},
            {field: 'description', displayName: '描述'},
            {field: 'createdOn', displayName: '创建时间'},
            {
                field: 'id',
                displayName: '操作',
                width: 200,
                sortable: false,
                cellTemplate: "<div><mine-action icon='fa fa-edit' action='edit(row.entity)' name='编辑'></mine-action>" +
                "<mine-action icon='fa fa-sticky-note-o' action='detail(row.entity)' name='查看'></mine-action>" +
                "<mine-action icon='fa fa-street-view' action='privilege(row.entity)' name='权限'></mine-action></div>"
            }

        ]
    });
    // init load datas
    $scope.gridPageQuery();
    $scope.gridPageQueryCallback = function (data) {
        return {data: data.content.rows, total: data.content.total};
    };
    $scope.query = function () {
        $scope.gridPageQuery({}, $scope.roleQuery);
    };
    $scope.add = function () {
        var modalInstance = mineUtil.modal("module/system/role/roleAdd.html", "systemRoleAddController", {});
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.edit = function (role) {
        var modalInstance = mineUtil.modal("module/system/role/roleEdit.html", "systemRoleEditController", role);
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.detail = function (role) {
        var modalInstance = mineUtil.modal("module/system/role/roleDetail.html", "systemRoleDetailController", role);
        modalInstance.result.then(function () {
        }, function () {
        });
    };
    $scope.privilege = function (role) {
        var modalInstance = mineUtil.modal("module/system/role/rolePrivilege.html", "systemRolePrivilegeController", role);
        modalInstance.result.then(function () {
        }, function () {
        });
    }
});
mainApp.controller("systemRoleAddController", function ($scope, $uibModalInstance, mineHttp) {
    $scope.ok = function () {
        mineHttp.send("POST", "roles", {data: $scope.role}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
            if ($scope.messageStatus) {
                $scope.role = null;
            }
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

mainApp.controller("systemRoleEditController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.send("GET", "roles/" + data.id, {}, function (result) {
        if (!verifyData(result)) {
            $scope.messageStatus = false;
            $scope.message = result.message;
        }
        $scope.role = result.content;
    });
    $scope.ok = function () {
        mineHttp.send("PUT", "roles/" + data.id, {data: $scope.role}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemRoleDetailController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.send("GET", "roles/" + data.id, {}, function (result) {
        $scope.message = result.message;
        $scope.role = result.content;
    });
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemRolePrivilegeController", function ($scope, $uibModalInstance, mineHttp, data, mineTree) {
    mineHttp.send("GET", "roles/" + data.id, {}, function (result) {
        $scope.message = result.message;
        $scope.role = result.content;
    });
    var menuTree = {};
    var permitTree = {};
    $scope.buildTree = function (callback) {
        mineHttp.send("GET", "roles/" + data.id + "/privilege", {}, function (data) {
            var options = {check: {enable: true}};
            menuTree = mineTree.build($("#menuTree"), data.content.roleMenus, options);
            permitTree = mineTree.build($("#functionTree"), data.content.rolePrivileges, options);
        });
    };
    $scope.buildTree();
    $scope.ok = function () {
        $scope.rolePrivilege = {};
        $scope.rolePrivilege.id = data.id;
        $scope.rolePrivilege.menuIds = new Array();
        $scope.rolePrivilege.privilegeIds = new Array();
        var menuNodes = menuTree.getCheckedNodes(true);
        for (var index in menuNodes) {
            $scope.rolePrivilege.menuIds.push(menuNodes[index].id);
        }
        var permitNodes = permitTree.getCheckedNodes(true);
        for (var index in permitNodes) {
            if (isNaN(permitNodes[index].id)) {
                continue;
            }
            $scope.rolePrivilege.privilegeIds.push(permitNodes[index].id);
        }
        mineHttp.send("PUT", "roles/" + data.id + "/privilege", {data: $scope.rolePrivilege}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});


//-------------- privilege ------------------------------------------------------------------
mainApp.controller("systemPrivilegeListCtl", function ($scope, $uibModal, mineHttp, mineGrid, mineUtil) {
    $scope.myData = [];
    mineGrid.gridPageInit("gridOptions", $scope, {
        data: 'myData',
        showSelectionCheckbox: true,
        multiSelect: true,
        selectWithCheckboxOnly: true,
        requestMethod: "POST",
        requestUrl: fullPath("privileges/list"),
        columnDefs: [
            {field: 'name', displayName: '权限名'},
            {field: 'url', displayName: '权限路径url'},
            {field: 'requestMethod', displayName: '请求方法'},
            {field: 'moduleMessage', displayName: '所属模块'},
            {field: 'createdOn', displayName: '创建时间'},
            {field: 'createdBy', displayName: '创建者'},
            {
                field: 'id',
                displayName: '操作',
                width: 200,
                sortable: false,
                cellTemplate: "<div><mine-action icon='fa fa-edit' action='edit(row.entity)' name='编辑'></mine-action>" +
                "<mine-action icon='fa fa-sticky-note-o' action='detail(row.entity)' name='查看'></mine-action>"
            }

        ]
    });
    // init load datas
    $scope.gridPageQuery();
    $scope.gridPageQueryCallback = function (data) {
        return {data: data.content.rows, total: data.content.total};
    };
    $scope.query = function () {
        $scope.gridPageQuery({}, $scope.privilegeQuery);
    };
    $scope.add = function () {
        var modalInstance = mineUtil.modal("module/system/privilege/privilegeAdd.html", "systemPrivilegeAddController", {});
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.edit = function (privilege) {
        var modalInstance = mineUtil.modal("module/system/privilege/privilegeEdit.html", "systemPrivilegeEditController", privilege);
        modalInstance.result.then(function () {
        }, function () {
            $scope.query();
        });
    };
    $scope.detail = function (privilege) {
        var modalInstance = mineUtil.modal("module/system/privilege/privilegeDetail.html", "systemPrivilegeDetailController", privilege);
        modalInstance.result.then(function () {
        }, function () {
        });
    };
});
mainApp.controller("systemPrivilegeAddController", function ($scope, $uibModalInstance, mineHttp) {

    mineHttp.constant("privilegeModule", function (data) {
        $scope.modules = data.content;
    });

    mineHttp.constant("requestMethods", function (data) {
        $scope.requestMethods = data.content;
    });

    $scope.ok = function () {
        mineHttp.send("POST", "privileges", {data: $scope.privilege}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
            if ($scope.messageStatus) {
                $scope.privilege = null;
            }
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

mainApp.controller("systemPrivilegeEditController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.constant("privilegeModule", function (data) {
        $scope.modules = data.content;
    });

    mineHttp.constant("requestMethods", function (data) {
        $scope.requestMethods = data.content;
    });

    mineHttp.send("GET", "privileges/" + data.id, {}, function (result) {
        if (!verifyData(result)) {
            $scope.messageStatus = false;
            $scope.message = result.message;
        }
        $scope.privilege = result.content;
    });
    $scope.ok = function () {
        mineHttp.send("PUT", "privileges/" + data.id, {data: $scope.privilege}, function (result) {
            $scope.messageStatus = verifyData(result);
            $scope.message = result.message;
        });
    };
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
mainApp.controller("systemPrivilegeDetailController", function ($scope, $uibModalInstance, mineHttp, data) {
    mineHttp.send("GET", "privileges/" + data.id, {}, function (result) {
        $scope.message = result.message;
        $scope.privilege = result.content;
    });
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});