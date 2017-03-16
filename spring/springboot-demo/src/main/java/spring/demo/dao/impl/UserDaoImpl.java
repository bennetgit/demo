package spring.demo.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spring.demo.dao.IUserDao;

import javax.annotation.Resource;

/**
 * Created by facheng on 16.03.17.
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into admin_user(name, age) values(?, ?)", name, age);

    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from admin_user where name = ?", name);
    }

    @Override
    public Long getAllUsers() {
        return jdbcTemplate.queryForObject("select count(*) from admin_user", Long.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from admin_user");
    }
}
