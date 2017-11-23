package spring.demo.persistence.common;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by facheng on 17-11-23.
 */
public class TimeRecordListener {

    @PrePersist
    public void recordCreationTime(Object entity) {
        if (entity instanceof TimeRecorder) {
            TimeRecorder toBeCreated = (TimeRecorder) entity;
            long now = System.currentTimeMillis();
            toBeCreated.recordCreationTime(now);
            toBeCreated.recordUpdateTime(now);
        }
    }

    @PreUpdate
    public void recordUpdateTime(Object entity) {
        if (entity instanceof TimeRecorder) {
            TimeRecorder toBeUpdated = (TimeRecorder) entity;
            toBeUpdated.recordUpdateTime(System.currentTimeMillis());
        }
    }
}
