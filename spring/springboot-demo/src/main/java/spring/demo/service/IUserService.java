package spring.demo.service;

/**
 * Created by facheng on 16.03.17.
 */
public interface IUserService {

    void create(String name, Integer age);

    void deleteByName(String name);

    Long getAllUsers();

    void deleteAllUsers();
}
