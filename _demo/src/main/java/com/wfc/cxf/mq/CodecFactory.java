package com.wfc.cxf.mq;

import java.io.IOException;

public interface CodecFactory {

    // 序列化
    byte[] serialize(Object obj) throws IOException;

    // 反序列化
    Object deSerialize(byte[] in) throws IOException;
}
