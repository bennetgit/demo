package spring.demo.dto;

import java.io.Serializable;

import spring.demo.dto.request.PrivilegeRequest;

/**
 * Created by facheng on 17-11-15.
 */
public class PrivilegeDto implements Serializable {
    private static final long serialVersionUID = -92880268882930291L;

    private Long id;

    private String name;

    private String url;

    private String createdBy;

    private String updatedBy;

    public PrivilegeDto() {
    }

    PrivilegeDto(Long id, String name, String url, String createdBy, String updatedBy) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public PrivilegeDto(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static final PrivilegeDto of(Long id, String name, String url, String createdBy, String updatedBy) {
        return new PrivilegeDto(id, name, url, createdBy, updatedBy);
    }

    public static final PrivilegeDto from(PrivilegeRequest request) {
        if (request == null) {
            return null;
        }
        return new PrivilegeDto(request.getId(), request.getName(), request.getUrl());
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "PrivilegeDto{" + "id=" + id + ", name='" + name + '\'' + ", url='" + url + '\'' + '}';
    }
}
