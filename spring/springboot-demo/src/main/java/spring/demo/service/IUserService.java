package spring.demo.service;

import org.apache.commons.lang3.tuple.Pair;
import spring.demo.dto.PageQuery;
import spring.demo.dto.TreeNode;
import spring.demo.dto.UserDto;
import spring.demo.util.PageResult;

import java.util.List;

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

    UserDto getUserById(Long id);

    void update(UserDto userDto);

    Pair<UserDto, List<TreeNode<Long>>> getUserAndRoleTree(Long userId);

    void updateUserRole(Long userId, List<Long> roleIds);
}
