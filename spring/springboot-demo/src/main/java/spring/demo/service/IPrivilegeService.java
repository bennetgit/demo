package spring.demo.service;

import spring.demo.dto.PageQuery;
import spring.demo.dto.PrivilegeDto;
import spring.demo.util.PageResult;

/**
 * Created by facheng on 17-11-15.
 */
public interface IPrivilegeService {

    PageResult<PrivilegeDto> lists(PageQuery pageQuery, PrivilegeDto dto);

    void addPrivilege(PrivilegeDto privilegeDto, Long currentUserId);

    PrivilegeDto findPrivilegeDetail(Long privilegeId);

    void updatePrivilege(Long id, PrivilegeDto privilegeDto, Long currentUserId);

    void deletePrivilege(Long id);
}
