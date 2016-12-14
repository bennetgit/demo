package com.springboot.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.springboot.elasticsearch.domain.UserIndex;

public interface IElasticSearchBaseRepository extends ElasticsearchRepository<UserIndex, Long> {
}
