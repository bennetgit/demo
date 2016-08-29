package com.wfc.cxf.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;

public class HttpUtils {

    public static String doGet(String url, Map<String, String> headers) {

        HttpClient httpClient = HttpClients.createDefault();
        HttpUriRequest request = new HttpGet(url);
        HttpResponse response = null;
        String result = "";
        try {
            if (headers != null && !headers.isEmpty()) {
                for (String key : headers.keySet()) {
                    request.setHeader(key, headers.get(key));
                }
            }
            response = httpClient.execute(request);
            result = getResultInString(result, request, response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getResultInString(String result, HttpUriRequest httpUriRequest, HttpResponse httpResponse)
            throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        StringBuffer sb = new StringBuffer();
        byte[] buffer = new byte[1024];
        if (entity != null) {
            InputStream inputStream = entity.getContent();
            try {
                int bytesRead = 0;
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                while ((bytesRead = bis.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, bytesRead);
                    sb.append(chunk);
                }
                result = sb.toString();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (RuntimeException runtimeException) {
                // connection immediately.
                httpUriRequest.abort();
                runtimeException.printStackTrace();
            } finally {
                // Closing the input stream will trigger connection release
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                }
            }
        }
        return result;
    }

    public static String getRealIp() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
        
    }

}
