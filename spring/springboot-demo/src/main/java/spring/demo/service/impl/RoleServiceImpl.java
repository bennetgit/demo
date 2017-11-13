package spring.demo.service.impl;

import static spring.demo.util.RoleParser.fromDomails;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.RoleDto;
import spring.demo.persistence.primary.jpa.IRoleRepository;
import spring.demo.service.IRoleService;

/**
 * Created by wangfacheng on 2017-11-13.
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleRepository roleRepository;

    @Override
    @Transactional
    public List<RoleDto> findRolesWithUserId(Long userId) {
        return fromDomails(roleRepository.findRolesWithUserId(userId));
    }
}
