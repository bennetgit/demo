package spring.demo.service.impl;

import static spring.demo.util.RoleParser.fromDomains;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.PageQuery;
import spring.demo.dto.RoleDto;
import spring.demo.persistence.primary.domain.Role;
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

    @Resource
    private IRoleRepository roleRepository;

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
                predicates.add(cb.equal(root.get("name"), dto.getName()));
            }

            if (StringUtil.isNotBlank(dto.getDescription())) {
                predicates.add(cb.equal(root.get("description"), dto.getDescription()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        }, pageQuery.sortPageDefault("id"));

        return PageResult.of(roles.getTotalElements(), RoleParser.fromDomains(roles.getContent()));
    }
}
