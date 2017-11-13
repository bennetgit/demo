package spring.demo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
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

    public static final Role fromDto(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
    }

    public static final RoleDto fromDomain(Role role) {
        if (role == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        roleDto.setCreatedOn(LocalDateTime.fromDateFields(role.getCreatedOn()));
        if (role.getUpdatedOn() != null) {
            roleDto.setUpdatedOn(LocalDateTime.fromDateFields(role.getUpdatedOn()));
        }
        return roleDto;
    }

    public static final List<RoleDto> fromDomains(List<Role> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }

        return roles.stream().map(RoleParser::fromDomain).collect(Collectors.toList());
    }

}
