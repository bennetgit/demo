package com.hong610.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.hong610.common.enums.types.ResourcesType;
import com.hong610.controller.base.BaseController;
import com.hong610.domain.Resources;
import com.hong610.domain.Role;
import com.hong610.common.result.Result;
import com.hong610.service.IResourcesService;
import com.hong610.service.IRoleResourcesService;
import com.hong610.service.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色
 * Created by Hong on 2016/12/16.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource(name = "RoleServiceImpl")
    IRoleService roleService;

    @Resource(name = "ResourcesServiceImpl")
    IResourcesService resourceService;

    @Resource(name = "RoleResourcesServiceImpl")
    IRoleResourcesService roleResourcesServiceImpl;

    /**
     * 角色资源管理
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'role_resources')")
    @RequestMapping(value = "/resources.json", method = {RequestMethod.POST})
    public Result<Object> resources(long roleId, String[] ids){
        Result<Object> result = new Result<Object>();
        boolean flag = roleResourcesServiceImpl.updateByRole(roleId, ids);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 进入角色资源管理
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'role_resources')")
    @RequestMapping(value = "/resources.html", method = {RequestMethod.GET})
    public String resources(Map<String, Object> map, long id) {
        List<Resources> topMenu = resourceService.findByType(ResourcesType.TOPMENU.getValue());
        List<Resources> oneMenu = resourceService.findByType(ResourcesType.ONEMENU.getValue());
        List<Resources> twoMenu = resourceService.findByType(ResourcesType.TWOMENU.getValue());
        List<Resources> links = resourceService.findByType(ResourcesType.LINK.getValue());
        List<Resources> roleResources = resourceService.findResourcesByRoleId(id);
        map.put("topMenu", topMenu);
        map.put("oneMenu", oneMenu);
        map.put("twoMenu", twoMenu);
        map.put("links", links);
        map.put("roleResources", roleResources);
        map.put("id", id);
        return "role/operate/resources";
    }

    /**
     * 角色列表
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'role_list')")
    @RequestMapping(value = "/list.html", method = {RequestMethod.GET})
    public String list(Map<String, Object> map, String name) {
        Page<Role> page = roleService.findPage(super.getPage(Role.class), name);
        map.put("page", page);
        map.put("name", name);
        return "role/list";
    }

    /**
     * 进入添加角色的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'role_add')")
    @RequestMapping(value = "/add.html", method = {RequestMethod.GET})
    public String add() {
        return "role/operate/add";
    }

    /**
     * 进入修改角色的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'role_edit')")
    @RequestMapping(value = "/edit.html", method = {RequestMethod.GET})
    public String edit(Map<String, Object> map, Long id) {
        Role role  = roleService.findById(id);
        map.put("role", role);
        return "role/operate/edit";
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'role_add')")
    @RequestMapping(value = "/add.json", method = {RequestMethod.POST})
    public Result<Object> add(Role role) {
        Result<Object> result = new Result<Object>();
        boolean flag = roleService.insert(role);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'role_edit')")
    @RequestMapping(value = "/edit.json", method = {RequestMethod.POST})
    public Result<Object> edit(Role role) {
        Result<Object> result = new Result<Object>();
        boolean flag = roleService.update(role);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'role_remove')")
    @RequestMapping(value = "/remove.json", method = {RequestMethod.POST})
    public Result<Object> remove(Long id) {
        Result<Object> result = new Result<Object>();
        boolean flag = roleService.remove(id);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 批量删除角色
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'role_removes')")
    @RequestMapping(value = "/removes.json", method = {RequestMethod.POST})
    public Result<Object> remove(String ids) {
        Result<Object> result = new Result<Object>();
        boolean flag = roleService.removes(ids);
        result.setSuccess(flag);
        return result;
    }

}
