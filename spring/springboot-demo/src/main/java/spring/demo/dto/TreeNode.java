package spring.demo.dto;

import java.io.Serializable;

/**
 * Created by wangfacheng on 2017-11-13.
 */
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = -3913787668434420272L;

    private T id;

    private T pid;

    private String name;

    private boolean checked = false;

    private boolean isParent;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getPid() {
        return pid;
    }

    public void setPid(T pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }


}
