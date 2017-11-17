package spring.demo.dto.request;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class RoleRequest extends BaseRequest {

    private Long id;

    private String name;

    private String description;

    private List<Long> menuIds = Lists.newArrayList();

    private List<Long> privilegeIds = Lists.newArrayList();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public List<Long> getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(List<Long> privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}
