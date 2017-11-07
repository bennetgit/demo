package spring.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import spring.demo.util.JsonDateTimeSerializer;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 8123908834392229965L;
    private Long id;

    private String userName;

    private String password;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime updatedOn;

    private List<MenuDto> menus = new ArrayList<>();

    private List<RoleDto> roles = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String userName, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.id = id;
        this.userName = userName;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<MenuDto> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDto> menus) {
        this.menus = menus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
