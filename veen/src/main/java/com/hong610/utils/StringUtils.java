package com.hong610.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.velocity.tools.config.DefaultKey;

/**
 * 字符串工具类
 * Created by Hong on 2016/12/4.
 */
@DefaultKey("StringUtils")
public class StringUtils {

    public static boolean isEmpty(Object obj){
        return (obj == null || obj.toString().length()==0);
    }
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
    public static boolean isNull(Object obj){
        return (obj == null);
    }
    public static boolean isNotNull(Object obj){
        return !isNull(obj);
    }
    public static long time(){
        return System.currentTimeMillis();
    }

    /**
     * 对象转JSON
     */
    public static String parseJSON(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * 获取错误信息
     */
    public static String getErrorMessage(String errJson){
        try{
            JSONObject json = JSON.parseObject(errJson);
            return json.getString("message");
        }catch (Exception e){
            return errJson;
        }
    }

    public static void main(String[] args) {

    }
}
