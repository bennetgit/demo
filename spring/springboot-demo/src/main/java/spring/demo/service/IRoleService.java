package spring.demo.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import spring.demo.dto.MenuDto;
import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.dto.TreeNode;
import spring.demo.util.PageResult;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public interface IRoleService {

    List<RoleDto> findRolesWithUserId(Long userId);

    PageResult<RoleDto> lists(PageQuery pageQuery, RoleDto dto);

    void addRole(RoleDto roleDto);

    RoleDto findRoleDetail(Long roleId);

    void updateRole(Long id, RoleDto roleDto);

    void deleteRole(Long id);

    Pair<List<TreeNode>, List<TreeNode>> getRolePrivilege(Long id);

    void updatePrivilege(RoleDto roleDto);
}
