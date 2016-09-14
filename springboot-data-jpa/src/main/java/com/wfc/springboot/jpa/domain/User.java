package com.wfc.springboot.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by feng on 16/9/14.
 */
@Entity
@DiscriminatorValue("10")
public class User extends TestUser {

    @Column
    private String sex;

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return super.toString() + ",sex = " + sex;
    }
}
