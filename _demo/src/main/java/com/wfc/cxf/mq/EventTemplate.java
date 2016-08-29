package com.wfc.cxf.mq;

/**
 * 新增一个接口专门用来实现发送功能
 * 
 * @author fcw
 *
 */
public interface EventTemplate {
    void send(String queueName, String exchangeName, Object eventContent);

    void send(String queueName, String exchangeName, Object eventContent, CodecFactory codecFactory);

}
