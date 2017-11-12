package spring.demo.service;

import spring.demo.dto.PageQuery;
import spring.demo.dto.UserDto;
import spring.demo.util.PageResult;

/**
 * Created by facheng on 16.03.17.
 */
public interface IUserService {

    void create(UserDto userDto);

    void deleteByName(String name);

    Long getAllUsers();

    void deleteAllUsers();

    UserDto getUserByName(String username);

    PageResult<UserDto> getUserListByPage(PageQuery pageQuery, UserDto userQuery);
}
