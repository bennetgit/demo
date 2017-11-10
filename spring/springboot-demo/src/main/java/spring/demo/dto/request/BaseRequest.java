package spring.demo.dto.request;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import spring.demo.util.JsonDateTimeDeserializer;

public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -3226404196843843131L;

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private LocalDateTime requestTimeStart;

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    private LocalDateTime requestTimeEnd;

    public LocalDateTime getRequestTimeStart() {
        return requestTimeStart;
    }

    public void setRequestTimeStart(LocalDateTime requestTimeStart) {
        this.requestTimeStart = requestTimeStart;
    }

    public LocalDateTime getRequestTimeEnd() {
        return requestTimeEnd;
    }

    public void setRequestTimeEnd(LocalDateTime requestTimeEnd) {
        this.requestTimeEnd = requestTimeEnd;
    }

}
