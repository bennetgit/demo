package spring.demo.service.impl;

import org.springframework.stereotype.Service;
import spring.demo.dao.IUserDao;
import spring.demo.service.IUserService;

import javax.annotation.Resource;

/**
 * Created by facheng on 16.03.17.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public void create(String name, Integer age) {
        userDao.create(name, age);
    }

    @Override
    public void deleteByName(String name) {
        userDao.deleteByName(name);
    }

    @Override
    public Long getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAllUsers();
    }
}
