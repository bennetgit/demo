package spring.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by jay on 7/20/16.
 */
public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String objToJson(Object obj) {

        final OutputStream out = new ByteArrayOutputStream();
        try {
            mapper.writeValue(out, obj);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return String.valueOf(out);
    }

    public static Object jsonToObj(String str, Class clazz) {
        try {
            return mapper.readValue(str, clazz);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Object jsonToObj(String str, Class clazz, Class genericClazz) {

        if (genericClazz == null) {
            return JacksonUtils.jsonToObj(str, clazz);
        }

        JavaType javaType = getCollectionType(clazz, genericClazz);
        try {
            return mapper.readValue(str, javaType);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    private static JavaType getCollectionType(Class clazz, Class genericClazz) {
        return mapper.getTypeFactory().constructParametrizedType(clazz, clazz, genericClazz);
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

}
