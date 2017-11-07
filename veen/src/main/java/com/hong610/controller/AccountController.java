package com.hong610.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.common.enums.status.UserStatus;
import com.hong610.common.exception.AppException;
import com.hong610.common.exception.error.SystemError;
import com.hong610.controller.base.BaseController;
import com.hong610.domain.Role;
import com.hong610.domain.User;
import com.hong610.domain.vo.UserAdmin;
import com.hong610.common.result.Result;
import com.hong610.service.IRoleService;
import com.hong610.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * Created by Hong on 2016/12/7.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Resource(name = "UserServiceImpl")
    IUserService userService;

    @Resource(name = "RoleServiceImpl")
    IRoleService roleService;

    /**
     * 进入登录页面
     */
    @RequestMapping(value = "/login.html", method = {RequestMethod.GET})
    public String login(HashMap<String, Object> map) {
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            throw new AppException(SystemError.USER_RELOGIN_ERROR);
            //throw new AppException(SystemError.USER_NOTRESOURCES_ERROR);
        }
        map.put("login_error", session.getAttribute("login_error"));
        session.removeAttribute("login_error");
        return "account/login";
    }

    /**
     * 管理员列表
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'account_list')")
    @RequestMapping(value = "/list.html", method = {RequestMethod.GET})
    public String list(Map<String, Object> map, String nick) {
        Page<UserAdmin> page = userService.findAdminPage(super.getPage(UserAdmin.class), nick);
        map.put("page", page);
        map.put("nick", nick);
        return "account/list";
    }

    /**
     * 进入添加用户的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'account_add')")
    @RequestMapping(value = "/add.html", method = {RequestMethod.GET})
    public String add() {
        return "account/operate/add";
    }

    /**
     * 进入修改用户的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'account_edit')")
    @RequestMapping(value = "/edit.html", method = {RequestMethod.GET})
    public String edit(Map<String, Object> map, Long id) {
        User user = userService.findById(id);
        List<Role> myRole = roleService.findRoleByUser(id);
        List<Role> roles = roleService.findAll();
        map.put("user", user);
        map.put("myRole", myRole);
        map.put("roles", roles);
        return "account/operate/edit";
    }

    /**
     * 添加用户
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_add')")
    @RequestMapping(value = "/add.json", method = {RequestMethod.POST})
    public Result<Object> add(String nick, String userName, String password) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.insertAdmin(nick, userName, password);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 修改用户
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_edit')")
    @RequestMapping(value = "/edit.json", method = {RequestMethod.POST})
    public Result<Object> edit(User user, String roleIds) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.update(user, roleIds == null ? new String[0] : roleIds.split(","));
        result.setSuccess(flag);
        return result;
    }

    /**
     * 启用
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_start')")
    @RequestMapping(value = "/start.json", method = {RequestMethod.POST})
    public Result<Object> start(Long id) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.updateStatus(id, UserStatus.ENABLED.getValue());
        result.setSuccess(flag);
        return result;
    }

    /**
     * 禁用
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_stop')")
    @RequestMapping(value = "/stop.json", method = {RequestMethod.POST})
    public Result<Object> stop(Long id) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.updateStatus(id, UserStatus.DISABLED.getValue());
        result.setSuccess(flag);
        return result;
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_remove')")
    @RequestMapping(value = "/remove.json", method = {RequestMethod.POST})
    public Result<Object> remove(Long id) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.remove(id);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 批量删除用户
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'account_removes')")
    @RequestMapping(value = "/removes.json", method = {RequestMethod.POST})
    public Result<Object> remove(String ids) {
        Result<Object> result = new Result<Object>();
        boolean flag = userService.removes(ids);
        result.setSuccess(flag);
        return result;
    }
}
