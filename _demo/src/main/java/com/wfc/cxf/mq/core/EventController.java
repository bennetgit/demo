package com.wfc.cxf.mq.core;

import java.util.Map;

import com.wfc.cxf.mq.EventTemplate;
import com.wfc.cxf.mq.consumer.EventProcesser;

/**
 * 管理控制和rabbitmq的通信
 * 
 * @author fcw
 *
 */
public interface EventController {

    /**
     * 控制器启动方法
     */
    void start();

    /**
     * 获取发送模版
     */
    EventTemplate getEopEventTemplate();

    /**
     * 绑定消费程序到对应的exchange和queue
     */
    EventController add(String queueName, String exchangeName, EventProcesser eventProcesser);

    /* in map, the key is queue name, but value is exchange name */
    EventController add(Map<String, String> bindings, EventProcesser eventProcesser);
}
