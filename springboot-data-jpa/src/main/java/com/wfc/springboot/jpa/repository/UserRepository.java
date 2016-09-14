package com.wfc.springboot.jpa.repository;

import com.wfc.springboot.jpa.domain.TestUser;
import com.wfc.springboot.jpa.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by feng on 16/9/14.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();


}
