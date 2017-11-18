package spring.demo.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import spring.demo.enums.ModuleType;
import spring.demo.enums.RequestMethod;
import spring.demo.util.JsonEnumDeserializer;

public class PrivilegeRequest extends BaseRequest {

    private String name;

    private String url;

    private Long id;

    @JsonDeserialize(using = JsonEnumDeserializer.class)
    private ModuleType module;

    private RequestMethod requestMethod;

    public ModuleType getModule() {
        return module;
    }

    public void setModule(ModuleType module) {
        this.module = module;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }
}
