package spring.demo.persistence.common;

/**
 * Created by facheng on 17-11-23.
 */
public interface TimeRecorder {

    void recordUpdateTime(Long updateTime);

    void recordCreationTime(Long creationTime);
}
