package com.springboot.elasticsearch.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.elasticsearch.dao.IElasticSearchBaseRepository;
import com.springboot.elasticsearch.dao.IUserRepository;
import com.springboot.elasticsearch.domain.UserIndex;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private IElasticSearchBaseRepository elasticSearchBaseRepository;


    @Override
    public void save(UserIndex user) {
        elasticSearchBaseRepository.save(user);
    }
}
