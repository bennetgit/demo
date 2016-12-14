package com.springboot.elasticsearch.service;

import com.springboot.elasticsearch.domain.UserIndex;

public interface IUserService {
    void save(UserIndex user);
}
