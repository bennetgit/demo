package spring.demo.dto.request;

import spring.demo.enums.ModuleType;

public class PrivilegeRequest extends BaseRequest {

    private String name;

    private String url;

    private Long id;

    private ModuleType module;

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
}
