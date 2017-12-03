package spring.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import spring.demo.dto.MenuDto;
import spring.demo.dto.TreeNode;
import spring.demo.exception.MenuOperateException;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.jpa.IMenuRepository;
import spring.demo.service.IMenuService;
import spring.demo.util.MenuParser;

/**
 * Created by feng on 17/10/29.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    private static final Logger LOGGER = LogManager.getLogger(MenuServiceImpl.class);

    @Resource
    private IMenuRepository menuRepository;

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus = menuRepository.findAll();

        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }

        return MenuParser.toTreeMenus(menus);
    }

    @Override
    public List<TreeNode> menuTree() {

        List<Menu> menus = menuRepository.findAll();
        if (CollectionUtils.isEmpty(menus)) {
            return Lists.newArrayList();
        }

        menus.sort((m1, m2) -> m1.getSequence() - m2.getSequence());
        return menus.stream().map(this::convert).collect(Collectors.toList());
    }

    private TreeNode convert(Menu menu) {
        if (menu == null) {
            return null;
        }

        TreeNode treeNode = new TreeNode();
        treeNode.setId(menu.getId());
        treeNode.setPid(menu.getParentId());
        treeNode.setName(menu.getName());
        return treeNode;
    }

    @Override
    public MenuDto findByMenuId(Long id) {

        MenuDto menuDto;
        if (id == null || (menuDto = MenuParser.fromDomain(menuRepository.findOne(id))) == null) {
            throw new MenuOperateException("cannot find menu " + id);
        }

        return menuDto;
    }

    @Override
    @Transactional
    public void addMenu(MenuDto menuDto) {
        LOGGER.info("start add menu {}", menuDto);
        if (menuDto == null) {
            throw new MenuOperateException("cannot find menu " + menuDto);
        }

        Menu menu = MenuParser.fromDto(menuDto);
        if (menuDto.getPid() != null) {
            menu.setParent(menuRepository.findOne(menuDto.getPid()));
        }
        menuRepository.save(menu);
    }

    @Override
    @Transactional
    public void updateMenu(MenuDto menuDto) {

        LOGGER.info("start update menu {}", menuDto);

        Menu menu;
        if (menuDto == null || menuDto.getId() == null || (menu = menuRepository.findOne(menuDto.getId())) == null) {
            throw new MenuOperateException();
        }

        menu.setUrl(menuDto.getUrl());
        menu.setName(menuDto.getName());
        menu.setSequence(menuDto.getSequence());
    }

    @Override
    @Transactional
    public void deleteMenu(Long id) {

        Menu menu;

        if (id == null || (menu = menuRepository.findOne(id)) == null) {
            throw new MenuOperateException("cannot find menu " + id);
        }

        menuRepository.delete(menu);
    }
}
