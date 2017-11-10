package spring.demo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import spring.demo.enums.DBEnum;

/**
 * Created by wangfacheng on 2017-11-10.
 */
public class JsonEnumSerializer extends JsonSerializer<Enum> {
    @Override
    public void serialize(Enum anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {

        if (anEnum == null) {
            return;
        }

        if (anEnum instanceof DBEnum) {
            jsonGenerator.writeNumber(((DBEnum) anEnum).getConstant());
        }

    }
}
