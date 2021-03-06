package spring.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.MenuDto;
import spring.demo.dto.UserDto;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.domain.Role;
import spring.demo.persistence.primary.domain.User;
import spring.demo.util.helper.PasswordHelper;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class UserParser {

    public static User fromDto(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getMobile());
        user.setPassword(PasswordHelper.password(userDto.getPassword()));
        user.setSex(userDto.getSexType());
        user.setAdmin(userDto.getAdmin());
        return user;
    }

    public static UserDto fromDomain(User user) {

        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setCreatedOnStart(LocalDateTime.fromDateFields(user.getCreatedOn()));
        if (user.getUpdatedOn() != null) {
            userDto.setUpdatedOn(LocalDateTime.fromDateFields(user.getUpdatedOn()));
        }
        userDto.setPassword(user.getPassword());
        userDto.setAdmin(user.getAdmin());
        List<MenuDto> menus = new ArrayList<>();
        userDto.setMenus(menus);


        List<Role> roles = user.getRoles();
        if (roles == null) {
            return userDto;
        }

        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        userDto.withRoleIds(roleIds);

        Map<Long, MenuDto> tempTreeMenuMap = new HashMap<>();

        MenuDto tempMenu;
        for (Role role : roles) {

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

        menus.addAll(tempTreeMenuMap.values().stream().sorted((m1, m2) -> m1.getSequence() - m2.getSequence())
                .collect(Collectors.toList()));

        return userDto;
    }

    public static final List<UserDto> toSimpleUserDto(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }

        return users.stream().map(UserParser::toSimpleUserDto).collect(Collectors.toList());
    }

    public static final UserDto toSimpleUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto result = UserDto.of(user.getId(), user.getUsername(), user.getMobile(), user.getSex(),
                LocalDateTime.fromDateFields(user.getCreatedOn()));
        result.setAdmin(user.getAdmin());
        result.setStatus(user.getStatus());
        return result;
    }
}
