package spring.demo.controller;

import static spring.demo.dto.response.ResponseInfo.fail;
import static spring.demo.dto.response.ResponseInfo.success;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.demo.dto.MenuDto;
import spring.demo.dto.request.MenuRequest;
import spring.demo.dto.response.ResponseInfo;
import spring.demo.service.IMenuService;

/**
 * Created by wangfacheng on 2017-11-13.
 */
@RestController
@RequestMapping("/menus")
public class MenuController {

    private static final Logger LOGGER = LogManager.getLogger(MenuController.class);

    @Resource
    private IMenuService menuService;

    @GetMapping("/treeList")
    public ResponseInfo<MenuDto> getTreeList() {
        return success(menuService.menuTree());
    }

    @GetMapping("/{id}")
    public ResponseInfo<MenuDto> getMenuDetail(@PathVariable Long id) {
        return success(menuService.findByMenuId(id));
    }

    @PostMapping("")
    public ResponseInfo addMenu(@RequestBody MenuRequest request) {

        try {
            menuService.addMenu(MenuDto.from(request));
            return success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail(request);
        }
    }

    @PutMapping("/{id}")
    public ResponseInfo update(@RequestBody MenuRequest request) {

        try {
            menuService.updateMenu(MenuDto.from(request));
            return success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail(request);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseInfo delete(@PathVariable Long id) {
        try {
            menuService.deleteMenu(id);
            return success();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return fail();
        }
    }

}
