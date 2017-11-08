package spring.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UserParser {

    public static UserDto fromDomain(User user) {

        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUsername());
        userDto.setCreatedOn(LocalDateTime.fromDateFields(user.getCreatedOn()));
        userDto.setUpdatedOn(LocalDateTime.fromDateFields(user.getUpdatedOn()));
        userDto.setPassword(user.getPassword());
        List<MenuDto> menus = new ArrayList<>();
        userDto.setMenus(menus);

        if (CollectionUtils.isEmpty(user.getRoles())) {
            return userDto;
        }

        Map<Long, MenuDto> tempTreeMenuMap = new HashMap<>();

        MenuDto tempMenu;
        for (Role role : user.getRoles()) {

            for (Menu menu : role.getMenus()) {
                tempMenu = MenuParser.fromDomain(menu);
                if (menu.isRoot()) {
                    if (tempTreeMenuMap.containsKey(menu.getId())) {
                        continue;
                    }
                    tempTreeMenuMap.put(menu.getId(), tempMenu);
                    continue;
                }

                if (tempTreeMenuMap.containsKey(menu.getParentId())) {
                    tempTreeMenuMap.get(menu.getParentId()).addSubMenu(tempMenu);
                } else {
                    tempTreeMenuMap.put(menu.getId(), MenuParser.fromDomain(menu.getParent()));
                    continue;
                }

            }
        }

        menus.addAll(tempTreeMenuMap.values());

        return userDto;
    }
}
