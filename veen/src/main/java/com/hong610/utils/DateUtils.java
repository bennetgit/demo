package com.hong610.utils;

import org.apache.velocity.tools.config.DefaultKey;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hong on 2016/12/7.
 */
@DefaultKey("date")
public class DateUtils extends org.apache.velocity.tools.generic.DateTool {

    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String HHMMSS = "HH:mm:ss";
    public static final String HHMM = "HH:mm";

    /**与当前时间计算毫秒时间差**/
    public static final Long diffNow(Date val1){
        return diff(val1, new Date());
    }

    /**计算毫秒时间差**/
    public static final Long diff(Date val1, Date val2){
        return val1.getTime() - val2.getTime();
    }

    /**格式化时间**/
    public static final String formatNow(String format){
        return format(getSystemDate(), format);
    }

    /**格式化时间**/
    public synchronized static final String format(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }
}
