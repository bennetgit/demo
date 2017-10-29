package spring.demo.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import spring.demo.dto.MenuDto;
import spring.demo.persistence.primary.domain.Menu;
import spring.demo.persistence.primary.jpa.IMenuRepository;
import spring.demo.service.IMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by feng on 17/10/29.
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Resource
    private IMenuRepository menuRepository;

    @Override
    public List<MenuDto> findAll() {
        List<Menu> menus = menuRepository.findAll();

        if (CollectionUtils.isEmpty(menus)){
            return Lists.newArrayList();
        }


        return null;
    }
}
