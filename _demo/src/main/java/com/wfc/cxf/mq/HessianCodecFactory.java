package com.wfc.cxf.mq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 下面是编码解码的实现类，用了hessian来实现
 * 
 * @author fcw
 *
 */
public class HessianCodecFactory implements CodecFactory {

    @Override
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = null;
        HessianOutput output = null;
        try {
            baos = new ByteArrayOutputStream(1024);
            output = new HessianOutput(baos);
            output.startCall();
            output.writeObject(obj);
            output.completeCall();
        } catch (IOException e) {
            throw e;
        } finally {
            if (output != null) {
                try {
                    baos.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return baos != null ? baos.toByteArray() : null;
    }

    @Override
    public Object deSerialize(byte[] in) throws IOException {
        Object obj = null;
        ByteArrayInputStream bais = null;
        HessianInput input = null;
        try {
            bais = new ByteArrayInputStream(in);
            input = new HessianInput(bais);
            input.startReply();
            obj = input.readObject();
            input.completeReply();
        } catch (final IOException ex) {
            throw ex;
        } catch (final Throwable e) {
        } finally {
            if (input != null) {
                try {
                    bais.close();
                } catch (final IOException ex) {
                }
            }
        }
        return obj;
    }

}
