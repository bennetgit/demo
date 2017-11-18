package spring.demo.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;
import spring.demo.dto.MenuDto;
import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.dto.TreeNode;
import spring.demo.service.common.CachedService;
import spring.demo.util.PageResult;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public interface IRoleService extends CachedService<RoleDto, Set<String>> {

    List<RoleDto> findRolesWithUserId(Long userId);

    PageResult<RoleDto> lists(PageQuery pageQuery, RoleDto dto);

    void addRole(RoleDto roleDto);

    RoleDto findRoleDetail(Long roleId);

    void updateRole(Long id, RoleDto roleDto);

    void deleteRole(Long id);

    Pair<List<TreeNode>, List<TreeNode>> getRolePrivilege(Long id);

    void updatePrivilege(RoleDto roleDto);
}
