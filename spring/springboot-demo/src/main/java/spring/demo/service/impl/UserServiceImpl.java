package spring.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.demo.dto.UserDto;
import spring.demo.jdbctemplate.IUserDao;
import spring.demo.persistence.primary.domain.User;
import spring.demo.persistence.primary.jpa.IUserRepository;
import spring.demo.service.IUserService;
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
    public void create(String name, Integer age) {
        User user = new User(name, age);
        user.setPassword("12312");
        userRepository.save(user);
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

}
