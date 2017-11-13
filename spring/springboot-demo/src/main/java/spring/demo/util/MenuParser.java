package spring.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.MenuDto;
import spring.demo.persistence.primary.domain.Menu;

/**
 * Created by feng on 17/10/29.
 */
public final class MenuParser {
    private MenuParser() {
    }

    public static final MenuDto fromDomain(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuDto menuDto = MenuDto.of(menu.getId(), menu.getName(), menu.getUrl(), menu.parentName(),
                menu.getParentId());
        menuDto.setSequence(menu.getSequence());
        return menuDto;
    }

    public static final Menu fromDto(MenuDto menuDto) {
        if (menuDto == null) {
            return null;
        }

        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setUrl(menuDto.getUrl());
        menu.setSequence(menuDto.getSequence() == null ? 0 : menuDto.getSequence());
        return menu;
    }

    public static final List<MenuDto> toTreeMenus(List<Menu> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }

        Map<Long, MenuDto> tempTreeMenuMap = new HashMap<>();

        MenuDto tempMenu;
        for (Menu menu : menus) {
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

        return new ArrayList<>(tempTreeMenuMap.values());
    }

}
