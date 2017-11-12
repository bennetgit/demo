package spring.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import spring.demo.dto.request.UserRequest;
import spring.demo.enums.SexType;
import spring.demo.util.JsonDateTimeSerializer;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 8123908834392229965L;

    public static final String P_USERNAME = "username";
    public static final String P_CREATED_ON = "createdOn";
    public static final String P_ID = "id";

    private Long id;

    private String username;

    private String password;

    private String mobile;

    private SexType sexType;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOnStart;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOnEnd;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime updatedOn;

    private List<MenuDto> menus = new ArrayList<>();

    private List<RoleDto> roles = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String username, LocalDateTime createdOnStart, LocalDateTime updatedOn) {
        this.id = id;
        this.username = username;
        this.createdOnStart = createdOnStart;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedOnStart() {
        return createdOnStart;
    }

    public void setCreatedOnStart(LocalDateTime createdOnStart) {
        this.createdOnStart = createdOnStart;
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

    public LocalDateTime getCreatedOnEnd() {
        return createdOnEnd;
    }

    public void setCreatedOnEnd(LocalDateTime createdOnEnd) {
        this.createdOnEnd = createdOnEnd;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public static final UserDto of(Long id, String userName, String mobile, SexType sexType, LocalDateTime createdOn) {

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(userName);
        userDto.setCreatedOn(createdOn);
        userDto.setMobile(mobile);
        userDto.setSexType(sexType);
        return userDto;
    }

    public static final UserDto from(UserRequest request) {

        if (request == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setMobile(request.getMobile());
        userDto.setSexType(request.getSex());
        userDto.setUsername(request.getUsername());
        userDto.setPassword(request.getPassword());

        return userDto;
    }
}
