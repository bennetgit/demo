package spring.demo.service.impl;

import static spring.demo.util.RoleParser.fromDomains;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.dto.TreeNode;
import spring.demo.exception.RoleOperateException;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.jpa.IMenuRepository;
import spring.demo.persistence.primary.jpa.IRoleRepository;
import spring.demo.service.IRoleService;
import spring.demo.util.PageResult;
import spring.demo.util.RoleParser;
import spring.demo.util.StringUtil;

/**
 * Created by wangfacheng on 2017-11-13.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    @Resource
    private IRoleRepository roleRepository;

    @Resource
    private IMenuRepository menuRepository;

    @Override
    @Transactional
    public List<RoleDto> findRolesWithUserId(Long userId) {
        return fromDomains(roleRepository.findRolesWithUserId(userId));
    }

    @Override
    public PageResult<RoleDto> lists(PageQuery pageQuery, RoleDto dto) {

        Page<Role> roles = roleRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtil.isNotBlank(dto.getName())) {
                predicates.add(cb.like(root.get("name"), StringUtil.wildcard(dto.getName())));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        }, pageQuery.sortPageDefault("id"));

        return PageResult.of(roles.getTotalElements(), RoleParser.fromDomains(roles.getContent()));
    }

    @Override
    @Transactional
    public void addRole(RoleDto roleDto) {
        if (roleDto == null) {
            throw new RoleOperateException("role dto cannot be empty");
        }

        Role role = RoleParser.fromDto(roleDto);

        roleRepository.save(role);
    }

    @Override
    public RoleDto findRoleDetail(Long roleId) {
        return RoleParser.fromDomain(roleRepository.findOne(roleId));
    }

    @Override
    @Transactional
    public void updateRole(Long id, RoleDto roleDto) {

        Role role;
        if (id == null || roleDto == null || (role = roleRepository.findOne(id)) == null) {
            throw new RoleOperateException();
        }

        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());
        role.setUpdatedOn(Date.from(Instant.now()));
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role;
        if (id == null || (role = roleRepository.findOne(id)) == null) {
            throw new RoleOperateException("cannot find role " + id);
        }

        roleRepository.delete(role);
    }

    @Override
    @Transactional
    public Pair<List<TreeNode>, List<TreeNode>> getRolePrivilege(Long id) {

        Role role;
        if (id == null || (role = roleRepository.findOne(id)) == null) {
            throw new RoleOperateException("cannot find role" + id);
        }

        Map<Long, Menu> roleMenuMap = role.getMenus().stream()
                .collect(Collectors.toMap(Menu::getId, Function.<Menu> identity()));

        List<TreeNode> roleMenus = new ArrayList<>();
        List<Menu> menus = menuRepository.findAll();
        if (!CollectionUtils.isEmpty(menus)) {
            roleMenus
                    .addAll(menus.stream().map(m -> convertMenu(m, m.getParentId(), roleMenuMap.containsKey(m.getId())))
                            .collect(Collectors.toList()));
        }

        return new ImmutablePair<>(roleMenus, Lists.newArrayList());
    }

    @Override
    @Transactional
    public void updatePrivilege(Long id, List<Long> menuIds) {

        Role role;
        if (id == null || (role = roleRepository.findOne(id)) == null) {
            throw new RoleOperateException("cannot find role " + id);
        }

        List<Menu> menus = menuRepository.findMenuWithIds(menuIds);
        role.setMenus(menus);

    }

    private TreeNode convertMenu(Menu menu, Long pid, boolean isChecked) {
        TreeNode treeNode = new TreeNode<>();
        treeNode.setName(menu.getName());
        treeNode.setChecked(isChecked);
        treeNode.setPid(pid);
        treeNode.setId(menu.getId());
        return treeNode;
    }
}
