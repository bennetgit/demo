package spring.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.annotation.PagerQueryParam;
import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.dto.request.RoleRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IRoleService;
import spring.demo.util.PageResult;

/**
 * Created by wangfacheng on 2017-11-13.
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @PostMapping()
    public ResponseInfo<PageResult<RoleDto>> lists(@PagerQueryParam PageQuery pageQuery,
            @RequestBody RoleRequest request) {

        PageResult<RoleDto> pageResult = roleService.lists(pageQuery, RoleDto.from(request));
        return ResponseInfo.success(pageResult);
    }
}
