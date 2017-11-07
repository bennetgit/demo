package com.hong610.controller;

import com.hong610.common.enums.types.ResourcesType;
import com.hong610.controller.base.BaseController;
import com.hong610.domain.Resources;
import com.hong610.common.result.Result;
import com.hong610.service.IResourcesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资源
 * Created by Hong on 2016/12/21.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Resource(name = "ResourcesServiceImpl")
    IResourcesService resourceService;

    /**
     * 管理链接
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_manager_link')")
    @RequestMapping(value = "/manager.html", method = {RequestMethod.GET})
    public String manager(Map<String, Object> map, long id) {
        Resources resources = resourceService.findById(id);
        map.put("resources", resources);
        List<Resources> links = resourceService.findByTypeAndParentId(ResourcesType.LINK.getValue(), id);
        map.put("links", links);
        map.put("type", ResourcesType.LINK.getValue());
        return "resource/manager";
    }

    /**
     * 查看
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_view')")
    @RequestMapping(value = "/view.html", method = {RequestMethod.GET})
    public String view(Map<String, Object> map, long id) {
        Resources resources = resourceService.findById(id);
        Resources parentResources = resourceService.findById(resources.getParentId());
        map.put("resources", resources);
        map.put("parentResources", parentResources);
        map.put("type", ResourcesType.values());
        return "resource/view";
    }

    /**
     * 资源列表
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_list')")
    @RequestMapping(value = "/list.html", method = {RequestMethod.GET})
    public String list(Map<String, Object> map) {
        List<Resources> topMenu = resourceService.findByType(ResourcesType.TOPMENU.getValue());
        List<Resources> oneMenu = resourceService.findByType(ResourcesType.ONEMENU.getValue());
        List<Resources> twoMenu = resourceService.findByType(ResourcesType.TWOMENU.getValue());
        map.put("topMenu", topMenu);
        map.put("oneMenu", oneMenu);
        map.put("twoMenu", twoMenu);
        return "resource/list";
    }

    /**
     * 进入添加菜单的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_add')")
    @RequestMapping(value = "/addMenu.html", method = {RequestMethod.GET})
    public String addMenu(Map<String, Object> map, long parentId) {
        map.put("parentId", parentId);
        map.put("type", ResourcesType.ONEMENU.getValue());
        return "resource/operate/add";
    }

    /**
     * 进入添加二级菜单的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_add')")
    @RequestMapping(value = "/addChildMenu.html", method = {RequestMethod.GET})
    public String addChildMenu(Map<String, Object> map, long parentId) {
        map.put("parentId", parentId);
        map.put("type", ResourcesType.TWOMENU.getValue());
        return "resource/operate/add";
    }

    /**
     * 添加链接
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_add')")
    @RequestMapping(value = "/addLink.html", method = {RequestMethod.GET})
    public String addLink(Map<String, Object> map, long parentId) {
        map.put("parentId", parentId);
        map.put("type", ResourcesType.LINK.getValue());
        return "resource/operate/add";
    }

    /**
     * 添加资源
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_add')")
    @RequestMapping(value = "/add.json", method = {RequestMethod.POST})
    public Result<Object> add(Resources resources) {
        Result<Object> result = new Result<Object>();
        boolean flag = resourceService.insert(resources);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 进入修改资源的页面
     */
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_edit')")
    @RequestMapping(value = "/edit.html", method = {RequestMethod.GET})
    public String edit(Map<String, Object> map, Long id) {
        System.err.println(id);
        Resources resource = resourceService.findById(id);
        map.put("resource", resource);
        return "resource/operate/edit";
    }

    /**
     * 修改资源
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_edit')")
    @RequestMapping(value = "/edit.json", method = {RequestMethod.POST})
    public Result<Object> edit(Resources resources) {
        Result<Object> result = new Result<Object>();
        boolean flag = resourceService.update(resources);
        result.setSuccess(flag);
        return result;
    }

    /**
     * 删除资源
     */
    @ResponseBody
    @PreAuthorize("authenticated and hasPermission('permission', 'resource_remove')")
    @RequestMapping(value = "/remove.json", method = {RequestMethod.POST})
    public Result<Object> remove(Long id) {
        Result<Object> result = new Result<Object>();
        boolean flag = resourceService.remove(id);
        result.setSuccess(flag);
        return result;
    }

}
