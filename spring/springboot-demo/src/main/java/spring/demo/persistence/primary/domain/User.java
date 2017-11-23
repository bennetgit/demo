package spring.demo.persistence.primary.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import spring.demo.enums.SexType;
import spring.demo.enums.UserStatus;
import spring.demo.persistence.common.TimeComponent;

/**
 * Created by facheng on 16.03.17.
 */

@Entity
@Table(name = "_user")
@SequenceGenerator(name = "seq_user", sequenceName = "seq_user")
public class User extends TimeComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String mobile;

    @Column
    private Boolean isAdmin;

    @Column
    @Type(type = "spring.demo.enums.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "spring.demo.enums.SexType") })
    private SexType sex;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Column
    @Type(type = "spring.demo.enums.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "spring.demo.enums.UserStatus") })
    private UserStatus status = UserStatus.ACTIVE;

    public User() {
    }

    public User(String name, String mobile) {
        this.username = name;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}