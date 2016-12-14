package com.springboot.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "user_index", type = "userIndexType")
public class UserIndex {
    @Id
    private Long id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;

    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int age;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
