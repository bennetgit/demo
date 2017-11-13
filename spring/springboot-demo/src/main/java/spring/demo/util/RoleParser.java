package spring.demo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.RoleDto;
import spring.demo.persistence.primary.domain.Role;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public final class RoleParser {

    private RoleParser() {
    }

    public static final RoleDto fromDomain(Role role) {
        if (role == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }

    public static final List<RoleDto> fromDomains(List<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }

        return roles.stream().map(RoleParser::fromDomain).collect(Collectors.toList());
    }

}
