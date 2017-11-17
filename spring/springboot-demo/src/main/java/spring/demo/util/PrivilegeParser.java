package spring.demo.util;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.PrivilegeDto;
import spring.demo.persistence.primary.domain.Privilege;

/**
 * Created by facheng on 17-11-15.
 */
public final class PrivilegeParser {

    private PrivilegeParser() {
    }

    public static final PrivilegeDto toSimplePrivilegeDto(Privilege privilege) {
        return PrivilegeDto.of(privilege.getId(), privilege.getName(), privilege.getUrl());
    }

    public static final PrivilegeDto fromDomain(Privilege privilege) {
        PrivilegeDto dto = PrivilegeDto.of(privilege.getId(), privilege.getName(), privilege.getUrl(),
                privilege.creatorInfo(), privilege.updatedInfo(), privilege.getModule());
        dto.setCreatedOn(LocalDateTime.fromDateFields(privilege.getCreatedOn()));
        dto.setUpdatedOn(LocalDateTime.fromDateFields(privilege.getUpdatedOn()));
        return dto;

    }

    public static final List<PrivilegeDto> fromDomains(List<Privilege> privileges) {

        if (CollectionUtils.isEmpty(privileges)) {
            return Lists.newArrayList();
        }

        return privileges.stream().map(PrivilegeParser::fromDomain).collect(Collectors.toList());
    }

    public static final Privilege fromDto(PrivilegeDto privilegeDto) {
        if (privilegeDto == null) {
            return null;
        }

        Privilege privilege = new Privilege();
        privilege.setName(privilegeDto.getName());
        privilege.setUrl(privilegeDto.getUrl());
        privilege.setModule(privilegeDto.getModule());
        return privilege;
    }
}
