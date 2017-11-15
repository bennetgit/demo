package spring.demo.persistence.primary.domain;

import java.time.Instant;
import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by facheng on 17-11-15.
 */

@Entity
@Table(name = "_privilege")
@SequenceGenerator(name = "seq_privilege", sequenceName = "seq_privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_privilege")
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "udpated_by")
    private User updatedBy;

    @ManyToOne

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String creatorInfo() {
        return getCreatedBy() == null ? StringUtils.EMPTY : getCreatedBy().getUsername();
    }

    public String updatorInfo() {
        return getUpdatedBy() == null ? StringUtils.EMPTY : getUpdatedBy().getUsername();
    }
}
