package spring.demo.health;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zouyue on 17-7-19.
 */
public class HealthCheckClient {

    private int connectionTimeout = 10000;
    private int socketTimeout = 10000;
    private int retryCount = 3;

    private ObjectMapper objectMapper;

    public Health check(String host, int port, String uri, String username, String password) throws IOException {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        CloseableHttpClient client = null;
        try {
            client = createClient();

            HttpGet request = new HttpGet(uri);
            request.setHeader("username", username);
            request.setHeader("password", password);

            HttpResponse response = client.execute(new HttpHost(host, port), request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new HttpResponseException(response.getStatusLine().getStatusCode(),
                        response.getStatusLine().getReasonPhrase());
            }

            return objectMapper.readValue(response.getEntity().getContent(), Health.class);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private CloseableHttpClient createClient() {
        return HttpClientBuilder.create().useSystemProperties()
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(connectionTimeout)
                        .setSocketTimeout(socketTimeout).build())
                .setRetryHandler(new StandardHttpRequestRetryHandler(retryCount, true)).build();
    }
}