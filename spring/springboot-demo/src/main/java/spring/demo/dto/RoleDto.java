package spring.demo.dto;

import java.io.Serializable;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class RoleDto implements Serializable {

    private Long id;

    private String name;

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
}
