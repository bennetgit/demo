package spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spring.demo.dto.MenuDto;
import spring.demo.dto.response.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facheng on 10.03.17.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }


    @GetMapping("/menuList")
    @ResponseBody
    public ResponseInfo<MenuDto> initMenuList() {

        List<MenuDto> menus = new ArrayList<MenuDto>() {
            {

                MenuDto level1_sys = MenuDto.of("系统管理", "system");
                MenuDto level1_other = MenuDto.of("其他管理", "other");
                MenuDto level2_user = MenuDto.of("用户管理", "user");
                MenuDto level2_menu = MenuDto.of("菜单管理", "menu.list");
                MenuDto level2_other = MenuDto.of("菜单管理_2", "other.2");

                level1_sys.addSubMenu(level2_menu);
                level1_sys.addSubMenu(level2_user);
                level1_other.addSubMenu(level2_other);

                add(level1_other);
                add(level1_sys);


            }
        };

        return ResponseInfo.success(menus);
    }


}
