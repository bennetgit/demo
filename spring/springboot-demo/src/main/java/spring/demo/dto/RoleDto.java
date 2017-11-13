package spring.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import spring.demo.dto.request.RoleRequest;
import spring.demo.util.JsonDateTimeSerializer;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class RoleDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime updatedOn;

    private List<Long> menuIds = new ArrayList<>();

    public RoleDto() {
    }

    RoleDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public static final RoleDto from(RoleRequest request) {
        if (request == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto(request.getId(), request.getName(), request.getDescription());
        roleDto.setMenuIds(request.getMenuIds());
        return roleDto;
    }
}
