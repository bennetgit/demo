package spring.demo.util;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.util.CollectionUtils;

import spring.demo.dto.MenuDto;
import spring.demo.dto.UserDto;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.domain.User;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public final class UserParser {

    private UserParser() {
    }

    public static final UserDto fromDomain(User user) {

        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setCreatedOn(LocalDateTime.fromDateFields(user.getCreatedOn()));
        userDto.setUpdatedOn(LocalDateTime.fromDateFields(user.getUpdatedOn()));

        List<MenuDto> menus = new ArrayList<>();
        userDto.setMenus(menus);

        if (CollectionUtils.isEmpty(user.getRoles())) {
            return userDto;
        }

        for (Role role : user.getRoles()) {
            for (Menu menu : role.getMenus()) {
                menus.add(MenuParser.fromDomain(menu));
            }
        }

        return userDto;
    }
}
