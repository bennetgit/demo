package spring.demo.persistence.common;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by feng on 17/11/18.
 */

@EntityListeners({ TimeRecordListener.class })
@MappedSuperclass
public class TimeComponent implements Serializable, TimeRecorder {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public void recordUpdateTime(Long updateTime) {
        this.updatedOn = new Date(updateTime);
    }

    @Override
    public void recordCreationTime(Long creationTime) {
        this.createdOn = new Date(creationTime);
        this.updatedOn = new Date(creationTime);
    }
}
