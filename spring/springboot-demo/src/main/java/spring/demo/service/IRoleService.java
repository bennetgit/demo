package spring.demo.service;

import java.util.List;

import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.util.PageResult;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public interface IRoleService {

    List<RoleDto> findRolesWithUserId(Long userId);

    PageResult<RoleDto> lists(PageQuery pageQuery, RoleDto dto);
}
