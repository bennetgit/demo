package spring.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import spring.demo.dto.request.MenuRequest;

/**
 * Created by feng on 17/10/28.
 */
public class MenuDto implements Serializable {
    private static final long serialVersionUID = 6158592312145459913L;

    private Long id;

    private String url;

    private String name;

    private String parentName;

    private Long pid;

    private List<MenuDto> subMenus = new ArrayList<>();

    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuDto> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuDto> subMenus) {
        this.subMenus = subMenus;
    }

    public void addSubMenu(MenuDto subMenu) {
        this.subMenus.add(subMenu);
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public static final MenuDto of(Long id, String name, String url, String pName, Long pid) {
        MenuDto menu = new MenuDto();
        menu.setId(id);
        menu.setName(name);
        menu.setUrl(url);
        menu.setParentName(pName);
        menu.setPid(pid);
        return menu;
    }

    public static final MenuDto from(MenuRequest request) {
        MenuDto menu = new MenuDto();
        menu.setId(request.getId());
        menu.setName(request.getName());
        menu.setUrl(request.getUrl());
        menu.setParentName(request.getParentName());
        menu.setPid(request.getParentId());
        menu.setSequence(request.getSequence() == null ? 0 : request.getSequence());
        return menu;
    }

    @Override
    public String toString() {
        return "MenuDto{" + "id=" + id + ", url='" + url + '\'' + ", name='" + name + '\'' + '}';
    }
}
