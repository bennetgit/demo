package com.veen.spring.demo.extension.model;

/**
 * Created by facheng on 18-5-4.
 */
public class People {

    private String id;

    private String name;

    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
