package spring.demo.persistence.primary.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by facheng on 16.03.17.
 */

@Entity
@Table(name = "demo_user")
@SequenceGenerator(name = "seq_demo_user", sequenceName = "seq_demo_user")
public class DemoUser implements Serializable {

    private static final long serialVersionUID = 2832544526842891863L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_demo_user")
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    public DemoUser() {
    }

    public DemoUser(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
