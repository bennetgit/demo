package spring.demo.controller;

import static spring.demo.dto.response.ResponseInfo.fail;
import static spring.demo.dto.response.ResponseInfo.success;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.annotation.PagerQueryParam;
import spring.demo.dto.PageQuery;
import spring.demo.dto.TreeNode;
import spring.demo.dto.UserDto;
import spring.demo.dto.request.UserRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IRoleService;
import spring.demo.service.IUserService;
import spring.demo.util.PageResult;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @PostMapping(value = "/list")
    public ResponseInfo<PageResult<UserDto>> list(@PagerQueryParam PageQuery pageQuery,
            @RequestBody UserRequest userQuery) {
        return success(userService.getUserListByPage(pageQuery, UserDto.from(userQuery)));
    }

    @PostMapping(value = "")
    public ResponseInfo<UserDto> add(@RequestBody UserRequest userRequest) {
        userService.create(UserDto.from(userRequest));
        return success(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseInfo<UserDto> getDetail(@PathVariable("id") Long id) {
        return success(userService.getUserById(id));
    }

    @PutMapping(value = "")
    public ResponseInfo<UserDto> update(@RequestBody UserRequest request) {
        try {
            userService.update(UserDto.from(request));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail();
        }

        return success();
    }

    @GetMapping(value = "/{id}/role")
    public ResponseInfo<UserDto> getUserRoleWithId(@PathVariable Long id) {
        try {

            Pair<UserDto, List<TreeNode<Long>>> userAndRoleTree = userService.getUserAndRoleTree(id);
            Map<String, Object> content = new HashMap<>();
            content.put("user", userAndRoleTree.getLeft());
            content.put("roleTree", userAndRoleTree.getRight());
            return success(content);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail();
        }
    }

    @PutMapping(value = "/{id}/role")
    public ResponseInfo<UserDto> updateUserRole(@PathVariable Long id, @RequestBody UserRequest request) {

        try {
            userService.updateUserRole(id, request.getRoleIds());
            return success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail();
        }
    }
}
