package com.hong610.common.helper;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网页工具
 * Created by Hong on 2016/11/28.
 */
public class WebHelper {

    /**
     * 得到Agent
     */
    public static String getAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 获取客户端真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            }

        }
        if ((ip != null) && (ip.length() > 15) && (ip.indexOf(",") > 0)) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
