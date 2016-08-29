package com.wfc.cxf.http;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class IpRemoteAddress {
    private WebServiceContext wsContext;

    /**
     * 获取请求来的ips
     * 
     * @param request
     * @return
     */
    public String getIpAddr() {
        String ipAddress = null;
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest request = (HttpServletRequest) (mc.get(mc.SERVLET_REQUEST));
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            // 这里主要是获取本机的ip,可有可无
            if (ipAddress.equals("127.0.0.1") || ipAddress.endsWith("0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        // 或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // return
        // ipAddress!=null&&!"".equals(ipAddress)?ipAddress.split(",")[0]:null;
        return ipAddress;
    }

    public static void main(String[] args) {
        // 得到IP，输出PC-201309011313/122.206.73.83
        InetAddress ia;
        try {
            ia = InetAddress.getLocalHost();
            System.out.println(ia);
            getLocalMac(ia);
        } catch (UnknownHostException | SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getLocalMac(InetAddress ia) throws SocketException {
        // TODO Auto-generated method stub
        // 获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.println("mac数组长度：" + mac.length);
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // 字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            System.out.println("每8位:" + str);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }
        System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
    }
}
