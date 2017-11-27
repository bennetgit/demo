package spring.demo.persistence.primary.domain;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import spring.demo.persistence.common.TimeComponent;
import spring.demo.persistence.common.TimeRecordListener;

/**
 * Created by facheng on 17-11-27.
 */

@EntityListeners({ TimeRecordListener.class })
@Entity
@Table
@SequenceGenerator(name = "seq_resource_bundle", sequenceName = "seq_resource_bundle")
public class ResourceBundle extends TimeComponent {

    @Id
    @GeneratedValue(generator = "seq_resource_bundle", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private Locale locale;

    public Long getId() {
        return id;
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
}
