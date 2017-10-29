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

        MenuDto menuDto = new MenuDto();
        menuDto.of(menu.getName(), menu.getUrl(), menu.parentName(), menu.getParentId());
        return menuDto;
    }

}
