package spring.demo.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import spring.demo.enums.DBEnum;

/**
 * Created by wangfacheng on 2017-11-10.
 */
public class JsonEnumDeserializer extends JsonDeserializer<Enum> {

    private static final Logger LOGGER = LogManager.getLogger(JsonEnumDeserializer.class);

    @Override
    public Enum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        try {
            String value = jsonParser.getText();
            if (value == null) {
                return null;
            }
            Object obj = jsonParser.getCurrentValue();
            Class enumClass = obj.getClass().getDeclaredField(jsonParser.getCurrentName()).getType();
            if (!enumClass.isEnum()) {
                return null;
            }
            if (DBEnum.class.isAssignableFrom(enumClass)) {
                for (Object enumObj : enumClass.getEnumConstants()) {
                    if (value.equals(String.valueOf(((DBEnum) enumObj).getConstant()))) {
                        return (Enum) enumObj;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Deserializer the field {} exception", jsonParser.getCurrentName(), e);
        }
        return null;
    }
}
