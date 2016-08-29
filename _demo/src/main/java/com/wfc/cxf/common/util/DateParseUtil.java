package com.wfc.cxf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.LocalDateTime;

public class DateParseUtil {

    public static LocalDateTime strDate2LocaleTime(String timeStr) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(PatternUtil.TIME_PATTERN);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateFormat.parse(timeStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return LocalDateTime.fromCalendarFields(c);
    }
}
