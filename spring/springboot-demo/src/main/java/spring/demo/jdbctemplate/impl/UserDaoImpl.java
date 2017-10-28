package spring.demo.jdbctemplate.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spring.demo.jdbctemplate.IUserDao;

import javax.annotation.Resource;

/**
 * Created by facheng on 16.03.17.
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into _user(name, age) values(?, ?)", name, age);

    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from _user where name = ?", name);
    }

    @Override
    public Long getAllUsers() {
        return jdbcTemplate.queryForObject("select count(*) from _user", Long.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from _user");
    }
}
