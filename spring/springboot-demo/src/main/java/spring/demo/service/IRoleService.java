package spring.demo.service;

import spring.demo.dto.RoleDto;

import java.util.List;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public interface IRoleService {

    List<RoleDto> findRolesWithUserId(Long userId);

}
