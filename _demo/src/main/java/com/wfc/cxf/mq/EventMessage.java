package com.wfc.cxf.mq;

import java.io.Serializable;
import java.util.Arrays;

public class EventMessage implements Serializable {

    private static final long serialVersionUID = 2011109546057310070L;

    private String queueName;
    private String exchangeName;
    private byte[] eventData;

    public EventMessage(String queueName, String exchangeName, byte[] eventData) {
        super();
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.eventData = eventData;
    }

    public EventMessage() {
        super();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public byte[] getEventData() {
        return eventData;
    }

    public void setEventData(byte[] eventData) {
        this.eventData = eventData;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "EventMessage [queueName=" + queueName + ", exchangeName=" + exchangeName + ", eventData="
                + Arrays.toString(eventData) + "]";
    }

}
