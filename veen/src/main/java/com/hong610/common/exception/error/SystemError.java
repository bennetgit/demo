package com.hong610.common.exception.error;

import com.hong610.common.exception.Error;

/**
 * 0x0000yyyy 系统异常
 * Created by Hong on 2016/12/4.
 */
public class SystemError extends Error {

    public static Error SERVICE_ERROR = new SystemError("0x00000000", "服务器开小差了，请稍后再试！");
    public static Error SERVICE_NULLPOINT_ERROR = new SystemError("0x00000001", "找不到对象！");

    public static Error USER_INVALID_SESSION_ERROR = new SystemError("0x00000010", "身份失效，请重新登录！");
    public static Error USER_NOTRESOURCES_ERROR = new SystemError("0x00000011", "访问权限受制，禁止访问！");
    public static Error USER_RELOGIN_ERROR = new SystemError("0x00000012", "重新登录！");
    public static Error USER_UNKNOWN_ERROR = new SystemError("0x00000013", "用户不存在！");
    public static Error USER_PASSWORD_ERROR = new SystemError("0x00000014", "用户名或者密码错误！");
    public static Error USER_CAPTCHA_ERROR = new SystemError("0x00000015", "验证码输入错误！");
    public static Error USER_DELETE_ERROR = new SystemError("0x00000016", "您的账户已经被删除了！");
    public static Error USER_DISABLED_ERROR = new SystemError("0x00000017", "您的账户已经被禁用了！");
    public static Error USER_ABNORMAL_ERROR = new SystemError("0x00000018", "账户出现异常，禁止登录！");
    public static Error USER_LOGIN_ADMIN_ERROR = new SystemError("0x00000019", "禁止登录！");

    public static Error PAGE_404 = new SystemError("0x00000100", "找不到当前访问的网页！");
    public static Error PAGE_405 = new SystemError("0x00000101", "当前访问姿势不对！");

    public static Error ERROE_EXCEPTION = new SystemError("0x00000500", "系统很忙，请稍后再试！");
    public static Error ERROE_DATABASE = new SystemError("0x00000501", "数据库错误，请稍后再试！");
    public static Error ERROR_PARAMETER = new SystemError("0x00000502", "参数错误，请检查参数完整性！");
    public static Error ERROR_NULLPOINT = new SystemError("0x00000503", "找不到对象！");


    public static Error ADMIN_ADD_USER_UNIQUE_ERROR = new SystemError("0x00010000", "该管理员已经存在，添加失败！");
    public static Error REMOVE_USER_ERROR = new SystemError("0x00010001", "删除用户失败！");
    public static Error ADMIN_EDIT_USER_STATUS_ERROR = new SystemError("0x00010002", "修改用户状态失败！");
    public static Error REMOVES_USER_ERROR = new SystemError("0x00010003", "批量删除用户失败！");
    public static Error REMOVE_NOUSER_ERROR = new SystemError("0x00010004", "请先选择用户！");
    public static Error EDIT_USER_ROLE_ERROR = new SystemError("0x00010100", "修改管理员信息失败！");

    public static Error ROLE_UNKNOWN_ERROR = new SystemError("0x00020100", "角色不存在！");
    public static Error ROLE_ADD_ERROR = new SystemError("0x00020101", "添加角色失败！");
    public static Error ROLE_UPDATE_ERROR = new SystemError("0x00020102", "修改角色失败！");
    public static Error ROLE_REMOVE_ERROR = new SystemError("0x00020103", "删除角色失败！");
    public static Error ROLE_REMOVES_NOUSER_ERROR = new SystemError("0x00020104", "请先选择角色！");
    public static Error ROLE_REMOVES_ERROR = new SystemError("0x00020105", "批量删除角色失败！");

    public static Error RESOURCES_REPEAT_ERROR = new SystemError("0x00030100", "资源已经存在！");
    public static Error RESOURCES_ADD_ERROR = new SystemError("0x00030101", "添加资源失败！");
    public static Error RESOURCES_REMOVE_ERROR = new SystemError("0x00030102", "删除资源失败！");
    public static Error RESOURCES_REMOVES_ERROR = new SystemError("0x00030103", "批量删除资源失败！");
    public static Error RESOURCES_UNKNOWN_ERROR = new SystemError("0x00030104", "资源不存在！");
    public static Error RESOURCES_REMOVE_DOWN_LIST_ERROR = new SystemError("0x00030105", "下面存在资源导致删除失败！");
    public static Error RESOURCES_ANTHORITY_REPEAT_ERROR = new SystemError("0x00030106", "权限名已经存在！");
    public static Error RESOURCES_EDIT_ERROR = new SystemError("0x00030107", "修改资源失败！");

    public static Error ROLERESOURCES_EDITBYROLE_NULL_ERROR = new SystemError("0x00040100", "请先选择资源！");
    public static Error ROLERESOURCES_EDITBYROLE_ERROR = new SystemError("0x00040101", "编辑角色资源失败！");

    public SystemError(String code, String message){
        super(code, message);
    }
    public SystemError(){ super(); }

}
