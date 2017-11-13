package spring.demo.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.annotation.PagerQueryParam;
import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.dto.TreeNode;
import spring.demo.dto.request.RoleRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IRoleService;
import spring.demo.util.PageResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spring.demo.dto.response.ResponseInfo.fail;
import static spring.demo.dto.response.ResponseInfo.success;

/**
 * Created by wangfacheng on 2017-11-13.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @PostMapping("/list")
    public ResponseInfo<PageResult<RoleDto>> lists(@PagerQueryParam PageQuery pageQuery,
            @RequestBody RoleRequest request) {

        PageResult<RoleDto> pageResult = roleService.lists(pageQuery, RoleDto.from(request));
        return success(pageResult);
    }

    @PostMapping("")
    public ResponseInfo add(@RequestBody RoleRequest request) {
        try {

            roleService.addRole(RoleDto.from(request));
            return success();
        } catch (Exception e) {
            return ResponseInfo.fail(request);
        }
    }

    @GetMapping("/{id}")
    public ResponseInfo<RoleDto> getRoleDetail(@PathVariable Long id) {
        return success(roleService.findRoleDetail(id));
    }

    @PutMapping("/{id}")
    public ResponseInfo update(@PathVariable Long id, @RequestBody RoleRequest request) {

        try {

            roleService.updateRole(id, RoleDto.from(request));
            return success();
        } catch (Exception e) {
            return fail(request);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseInfo deleteRole(@PathVariable Long id) {

        try {
            roleService.deleteRole(id);
            return success();
        } catch (Exception e) {
            return fail();
        }
    }

    @GetMapping("/{id}/privilege")
    public ResponseInfo<RoleDto> getRolePrivilege(@PathVariable Long id) {

        Pair<List<TreeNode>, List<TreeNode>> rolePrivileges = roleService.getRolePrivilege(id);
        Map<String, List<TreeNode>> content = new HashMap<>();
        content.put("roleMenus", rolePrivileges.getLeft());
        content.put("rolePrivileges", rolePrivileges.getRight());
        return success(content);
    }

    @PutMapping("/{id}/privilege")
    public ResponseInfo updatePrivilege(@PathVariable Long id, @RequestBody RoleRequest request) {

        try {
            roleService.updatePrivilege(id, request.getMenuIds());
            return success();
        } catch (Exception e) {
            return fail(request);
        }
    }

}
