package spring.demo.service;

import spring.demo.dto.MenuDto;

import java.util.List;

/**
 * Created by feng on 17/10/29.
 */
public interface IMenuService {

    List<MenuDto> findAll();
}
