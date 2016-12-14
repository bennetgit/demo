package com.springboot.elasticsearch.dao;

import com.springboot.elasticsearch.domain.UserIndex;

public interface IUserRepository {

    void save(UserIndex user);
}
