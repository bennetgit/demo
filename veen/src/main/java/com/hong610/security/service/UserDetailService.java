package com.hong610.security.service;

import com.alibaba.fastjson.JSON;
import com.hong610.common.enums.Delete;
import com.hong610.common.enums.status.UserStatus;
import com.hong610.common.enums.types.UserType;
import com.hong610.common.exception.error.SystemError;
import com.hong610.common.helper.HttpHelper;
import com.hong610.domain.Login;
import com.hong610.domain.Resources;
import com.hong610.domain.Role;
import com.hong610.domain.User;
import com.hong610.security.entity.UserDetail;
import com.hong610.service.ILoginService;
import com.hong610.service.IResourcesService;
import com.hong610.service.IRoleService;
import com.hong610.service.IUserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SpringSecurity User Service Created by Hong on 2016/12/7.
 */
@Service("UserDetailService")
public class UserDetailService implements UserDetailsService {

    @Resource(name = "UserServiceImpl")
    IUserService userService;

    @Resource(name = "RoleServiceImpl")
    IRoleService roleService;

    @Resource(name = "ResourcesServiceImpl")
    IResourcesService resourcesService;

    @Resource(name = "LoginServiceImpl")
    ILoginService loginService;

    @Override
    public UserDetail loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByName(userName);
        if (user == null) {
            HttpHelper.getHttpServletRequest().getSession().setAttribute("login_error",
                    SystemError.USER_UNKNOWN_ERROR.getMessage());
            throw new UsernameNotFoundException(JSON.toJSONString(SystemError.USER_UNKNOWN_ERROR));
        }
        if (user.getIsDelete() == Delete.DELETE.getValue()) {// 删除
            HttpHelper.getHttpServletRequest().getSession().setAttribute("login_error",
                    SystemError.USER_DELETE_ERROR.getMessage());
            throw new UsernameNotFoundException(JSON.toJSONString(SystemError.USER_DELETE_ERROR));
        }
        if (user.getAdmin() != UserType.ADMIN.getValue()) {// 除了管理员都不能登录
            HttpHelper.getHttpServletRequest().getSession().setAttribute("login_error",
                    SystemError.USER_LOGIN_ADMIN_ERROR.getMessage());
            throw new UsernameNotFoundException(JSON.toJSONString(SystemError.USER_LOGIN_ADMIN_ERROR));
        }
        if (user.getStatus() == UserStatus.DISABLED.getValue()) {// 禁用
            HttpHelper.getHttpServletRequest().getSession().setAttribute("login_error",
                    SystemError.USER_DISABLED_ERROR.getMessage());
            throw new UsernameNotFoundException(JSON.toJSONString(SystemError.USER_DISABLED_ERROR));
        }
        if (user.getStatus() != UserStatus.ENABLED.getValue()) {// 异常状态
            HttpHelper.getHttpServletRequest().getSession().setAttribute("login_error",
                    SystemError.USER_ABNORMAL_ERROR.getMessage());
            throw new UsernameNotFoundException(JSON.toJSONString(SystemError.USER_ABNORMAL_ERROR));
        }
        HttpHelper.getHttpServletRequest().getSession().setAttribute("User", user);

        // 获取登录信息
        Login login = loginService.findNewestLogin(user.getId());
        if (login == null)
            login = new Login();
        // 查询登录次数
        long count = loginService.findLoginCount(user.getId());
        login.setLoginCount(++count);
        HttpHelper.getHttpServletRequest().getSession().setAttribute("Login", login);

        List<Role> roles = roleService.findRoleByUser(user.getId());
        List<Resources> resources = resourcesService.findResourcesByUser(user.getId());
        return new UserDetail(user, roles, resources);
    }

}
