package com.wfc.cxf.mq;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

import com.wfc.cxf.mq.core.DefaultEventController;

/**
 * 将数据转换为EventMessage
 * 
 * @author fcw
 *
 */
public class DefaultEventTemplate implements EventTemplate {

    private AmqpTemplate eventAmqpTemplate;
    private CodecFactory defaultCodecFactory;

    public DefaultEventTemplate() {
        super();
    }

    private DefaultEventController eec;

    public DefaultEventTemplate(AmqpTemplate eopAmqpTemplate, CodecFactory defaultCodecFactory,
            DefaultEventController eec) {
        this.eventAmqpTemplate = eopAmqpTemplate;
        this.defaultCodecFactory = defaultCodecFactory;
        this.eec = eec;
    }

    public DefaultEventTemplate(AmqpTemplate eventAmqpTemplate, CodecFactory defaultCodecFactory) {
        super();
        this.eventAmqpTemplate = eventAmqpTemplate;
        this.defaultCodecFactory = defaultCodecFactory;
    }

    @Override
    public void send(String queueName, String exchangeName, Object eventContent) {
        this.send(queueName, exchangeName, eventContent, defaultCodecFactory);
    }

    @Override
    public void send(String queueName, String exchangeName, Object eventContent, CodecFactory codecFactory) {
        if (StringUtils.isEmpty(queueName) || StringUtils.isEmpty(exchangeName)) {
            throw new RuntimeException("错误");
        }

        // if (!eec.beBinded(exchangeName, queueName))
        // eec.declareBinding(exchangeName, queueName);

        byte[] eventContentBytes = null;
        if (codecFactory == null) {
            if (eventContent == null) {
            } else {
                throw new RuntimeException("错误");
            }
        } else {
            try {
                eventContentBytes = codecFactory.serialize(eventContent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 构造成Message
        EventMessage msg = new EventMessage(queueName, exchangeName, eventContentBytes);
        try {
            eventAmqpTemplate.convertAndSend(exchangeName, queueName, msg);
        } catch (AmqpException e) {
            throw new RuntimeException(e);
        }
    }

}
