package spring.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * Created by facheng on 17-11-27.
 */
public class ResourceBundleDto implements Serializable {
    private static final long serialVersionUID = 3604854548662430107L;

    private Long id;

    private String key;

    private String value;

    private Locale locale;

    private Date createdOn;

    private Date updatedOn;

    public static final ResourceBundleDto of(Long id, String key, String value, Locale locale, Date createdOn,
            Date updatedOn) {
        ResourceBundleDto result = new ResourceBundleDto();
        result.setId(id);
        result.setKey(key);
        result.setCreatedOn(createdOn);
        result.setUpdatedOn(updatedOn);
        result.setLocale(locale);
        result.setValue(value);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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
}
