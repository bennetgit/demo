package spring.demo.persistence.primary.domain;

import com.google.common.base.Strings;
import spring.demo.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Created by feng on 17/10/28.
 */

@Entity
@Table(name = "_menu")
@SequenceGenerator(name = "seq_menu", sequenceName = "seq_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menu")
    private Long id;

    @Column
    private String name;

    @Column(insertable = false, updatable = false)
    private Long parentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @Column
    private String url;

    @Column
    private Integer sequence = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Menu> children;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = Date.from(Instant.now());

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn = Date.from(Instant.now());

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String parentName() {
        return getParent() != null ? getParent().getName() : StringUtils.EMPTY;
    }
}
