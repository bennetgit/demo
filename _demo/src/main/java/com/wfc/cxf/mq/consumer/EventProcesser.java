package com.wfc.cxf.mq.consumer;

public interface EventProcesser {

    void process(Object o);
}
