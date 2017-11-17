package spring.demo.persistence.primary.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import spring.demo.enums.SexType;
import spring.demo.enums.UserStatus;
import spring.demo.persistence.common.BaseDomain;

/**
 * Created by facheng on 16.03.17.
 */

@Entity
@Table(name = "_user")
@SequenceGenerator(name = "seq_user", sequenceName = "seq_user")
public class User extends BaseDomain {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = Date.from(Instant.now());

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn = Date.from(Instant.now());

    @Column
    @Type(type = "spring.demo.enums.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "spring.demo.enums.UserStatus") })
    private UserStatus status = UserStatus.INACTIVE;

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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
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