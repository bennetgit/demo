package spring.demo.util;

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
}
