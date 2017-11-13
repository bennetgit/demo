package spring.demo.service;

import spring.demo.dto.MenuDto;
import spring.demo.dto.TreeNode;

import java.util.List;

/**
 * Created by feng on 17/10/29.
 */
public interface IMenuService {

    List<MenuDto> findAll();

    List<TreeNode> menuTree();

    MenuDto findByMenuId(Long id);

    void addMenu(MenuDto menuDto);

    void updateMenu(MenuDto menuDto);

    void deleteMenu(Long id);
}
