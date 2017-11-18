package spring.demo.dto;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import spring.demo.dto.common.BaseDto;
import spring.demo.dto.request.PrivilegeRequest;
import spring.demo.enums.ModuleType;
import spring.demo.enums.RequestMethod;
import spring.demo.util.JsonDateTimeSerializer;
import spring.demo.util.JsonEnumSerializer;
import spring.demo.util.MyCacheUtils;

/**
 * Created by facheng on 17-11-15.
 */
public class PrivilegeDto extends BaseDto<String> {
    private static final long serialVersionUID = -92880268882930291L;

    private Long id;

    private String name;

    private String url;

    private String createdBy;

    private String updatedBy;

    @JsonSerialize(using = JsonEnumSerializer.class)
    private ModuleType module;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private LocalDateTime updatedOn;

    private Long currentUserId;

    private RequestMethod requestMethod;

    public PrivilegeDto() {
    }

    PrivilegeDto(Long id, String name, String url, String createdBy, String updatedBy, ModuleType module) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.module = module;
    }

    public PrivilegeDto(Long id, String name, String url, ModuleType module) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.module = module;
    }

    public static final PrivilegeDto of(Long id, String name, String url) {
        return new PrivilegeDto(id, name, url, null, null, null);
    }

    public static final PrivilegeDto of(Long id, String name, String url, String createdBy, String updatedBy,
            ModuleType module) {
        return new PrivilegeDto(id, name, url, createdBy, updatedBy, module);
    }

    public static final PrivilegeDto from(PrivilegeRequest request) {
        if (request == null) {
            return null;
        }
        return new PrivilegeDto(request.getId(), request.getName(), request.getUrl(), request.getModule())
                .withRequestType(request.getRequestMethod());
    }

    public static final PrivilegeDto getInstance() {
        return new PrivilegeDto();
    }

    public final PrivilegeDto withId(Long id) {
        this.setId(id);
        return this;
    }

    public final PrivilegeDto withCurrentId(Long currentId) {
        this.setCurrentUserId(currentId);
        return this;
    }

    public final PrivilegeDto withRequestType(RequestMethod requestMethod) {
        this.setRequestMethod(requestMethod);
        return this;
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

    public ModuleType getModule() {
        return module;
    }

    public void setModule(ModuleType module) {
        this.module = module;
    }

    public String getModuleMessage() {
        return module == null ? StringUtils.EMPTY : module.getMessage();
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

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "PrivilegeDto{" + "id=" + id + ", name='" + name + '\'' + ", url='" + url + '\'' + '}';
    }

    @Override
    public String cacheKey() {
        return MyCacheUtils.getPrivilegeCacheKey(url,
                StringUtils.defaultString(String.valueOf(requestMethod), StringUtils.EMPTY));
    }
}
