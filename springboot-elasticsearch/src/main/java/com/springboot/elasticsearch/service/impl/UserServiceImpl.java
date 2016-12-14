package com.springboot.elasticsearch.service.impl;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.springboot.elasticsearch.dao.IUserRepository;
import com.springboot.elasticsearch.domain.UserIndex;
import com.springboot.elasticsearch.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserRepository userRepository;

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(UserIndex user) {

//        elasticsearchTemplate.deleteIndex("order_index");
        userRepository.save(user);
        System.out.print("save success " + user);
    }
}
