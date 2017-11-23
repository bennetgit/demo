package spring.demo.persistence.primary.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import spring.demo.enums.ModuleType;
import spring.demo.enums.RequestMethod;
import spring.demo.persistence.common.TimeComponent;

/**
 * Created by facheng on 17-11-15.
 */

@Entity
@Table(name = "_privilege")
@SequenceGenerator(name = "seq_privilege", sequenceName = "seq_privilege")
public class Privilege extends TimeComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_privilege")
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column
    @Type(type = "spring.demo.enums.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "spring.demo.enums.RequestMethod") })
    private RequestMethod requestMethod;

    @Column
    @Type(type = "spring.demo.enums.DBEnumType", parameters = {
            @Parameter(name = "enumClass", value = "spring.demo.enums.ModuleType") })
    private ModuleType module;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "privilege_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ModuleType getModule() {
        return module;
    }

    public void setModule(ModuleType module) {
        this.module = module;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String requestTypeInfo() {
        return requestMethod == null ? StringUtils.EMPTY : requestMethod.name();
    }

    public String creatorInfo() {
        return getCreatedBy() == null ? StringUtils.EMPTY : getCreatedBy().getUsername();
    }

    public String updatedInfo() {
        return getUpdatedBy() == null ? StringUtils.EMPTY : getUpdatedBy().getUsername();
    }
}
