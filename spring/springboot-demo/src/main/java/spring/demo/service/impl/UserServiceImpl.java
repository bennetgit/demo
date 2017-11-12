package spring.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.PageQuery;
import spring.demo.dto.UserDto;
import spring.demo.jdbctemplate.IUserDao;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IUserService;
import spring.demo.util.PageResult;
import spring.demo.util.StringUtil;
import spring.demo.util.UserParser;

/**
 * Created by facheng on 16.03.17.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void create(UserDto userDto) {
        userRepository.save(UserParser.fromDto(userDto));
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }

    @Override
    @Transactional
    public UserDto getUserByName(String username) {
        return UserParser.fromDomain(userRepository.findUser(username));
    }

    @Override
    public PageResult<UserDto> getUserListByPage(PageQuery pageQuery, UserDto userQuery) {
        Page<User> users = userRepository.findAll((root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtil.isNotBlank(userQuery.getUsername())) {
                list.add(cb.equal(root.get(UserDto.P_USERNAME), userQuery.getUsername()));
            }
            if (!Objects.isNull(userQuery.getCreatedOnStart())) {
                list.add(cb.greaterThanOrEqualTo(root.get(UserDto.P_CREATED_ON), userQuery.getCreatedOnStart()));
            }
            if (!Objects.isNull(userQuery.getCreatedOnEnd())) {
                list.add(cb.lessThanOrEqualTo(root.get(UserDto.P_CREATED_ON), userQuery.getCreatedOnEnd()));
            }
            return cb.and(list.toArray(new Predicate[0]));
        }, pageQuery.sortPageDefault(UserDto.P_ID));

        return PageResult.of(users.getTotalElements(), UserParser.toSimpleUserDto(users.getContent()));
    }

}
