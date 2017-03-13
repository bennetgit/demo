package spring.demo.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.LocalDateTime;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import spring.demo.constant.Constants;

public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private SimpleDateFormat FORMAT = new SimpleDateFormat(Constants.FULL_DATE_PATTERN);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        String dateStr = jsonParser.getText();
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        try {
            return LocalDateTime.fromDateFields(FORMAT.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
