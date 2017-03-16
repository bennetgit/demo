package spring.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.jdbctemplate.IUserDao;
import spring.demo.service.IUserService;

import javax.annotation.Resource;

/**
 * Created by facheng on 16.03.17.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Transactional
    @Override
    public void create(String name, Integer age) {
        userDao.create(name, age);
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
}
