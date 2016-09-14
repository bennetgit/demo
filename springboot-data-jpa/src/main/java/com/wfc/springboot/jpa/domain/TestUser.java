package com.wfc.springboot.jpa.domain;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER)
@SequenceGenerator(name = "seq_test_user", sequenceName = "seq_test_user")
public class TestUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_test_user")
    private Long id;

    @Column
    private String name;

    @Column
    private int age;

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

    @Override
    public String toString() {
        return "id = " + id + ",name = " + name;
    }
}
