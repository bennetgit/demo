package spring.demo.util;

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

}
