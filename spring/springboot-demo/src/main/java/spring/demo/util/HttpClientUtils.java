package spring.demo.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

/**
 * Created by wangfacheng on 2018-07-17.
 */
public class HttpClientUtils {

    private static final int CONNECTION_TIME_OUT = 5000;
    private static final int SOCKET_TIME_OUT = 5000;
    private static final String DEFAULT_CHAR_SET = "UTF-8";

    public static String doDelete(String url, Map<String, Object> payload) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create().useSystemProperties();
        clientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(CONNECTION_TIME_OUT)
                .setSocketTimeout(SOCKET_TIME_OUT).build());
        CloseableHttpClient httpClient = null;
        String result = null;
        HttpDeleteWithBody httpDelete;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            httpClient = clientBuilder.build();
            httpDelete = new HttpDeleteWithBody(url);
            httpDelete.setHeader("Content-Type", "application/json; charset=UTF-8");
            if (MapUtils.isNotEmpty(payload)) {
                httpDelete.setEntity(new StringEntity(objectMapper.writeValueAsString(payload)));
            }
            HttpResponse response = httpClient.execute(httpDelete);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, DEFAULT_CHAR_SET);
                }
            }

        } catch (Exception e) {
        } finally {
            closeHttpClient(httpClient);
        }
        return result;
    }

    public static String doPut(String url, Map<String, String> headers, Map<String, Object> payload) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create().useSystemProperties();
        clientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(CONNECTION_TIME_OUT)
                .setSocketTimeout(SOCKET_TIME_OUT).build());
        CloseableHttpClient httpClient = null;
        String result = null;
        HttpPut httpPut;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            httpClient = clientBuilder.build();
            httpPut = new HttpPut(url);
            httpPut.setHeader("Content-Type", "application/json; charset=UTF-8");

            if (MapUtils.isNotEmpty(headers)) {
                headers.forEach((k, v) -> httpPut.setHeader(k, v));
            }

            if (MapUtils.isNotEmpty(payload)) {
                httpPut.setEntity(new StringEntity(objectMapper.writeValueAsString(payload), DEFAULT_CHAR_SET));
            }
            HttpResponse response = httpClient.execute(httpPut);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, DEFAULT_CHAR_SET);
                }
            }

        } catch (Exception e) {
        } finally {
            closeHttpClient(httpClient);
        }
        return result;
    }

    public static String doPost(String url, Map<String, String> headers, Map<String, Object> payload) {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create().useSystemProperties();
        clientBuilder.setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(CONNECTION_TIME_OUT)
                .setSocketTimeout(SOCKET_TIME_OUT).build());
        CloseableHttpClient httpClient = null;
        String result = null;
        HttpPost httpPost;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            httpClient = clientBuilder.build();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json; charset=UTF-8");

            if (MapUtils.isNotEmpty(headers)) {
                headers.forEach((k, v) -> httpPost.setHeader(k, v));
            }

            if (MapUtils.isNotEmpty(payload)) {
                httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(payload), DEFAULT_CHAR_SET));
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, DEFAULT_CHAR_SET);
                }
            }

        } catch (Exception e) {
        } finally {
            closeHttpClient(httpClient);
        }
        return result;
    }

    private static void closeHttpClient(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (Exception e) {
        }
    }
}
