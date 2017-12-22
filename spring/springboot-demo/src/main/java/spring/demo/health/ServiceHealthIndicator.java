package spring.demo.health;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import spring.demo.util.JacksonUtils;

/**
 * Created by jay on 9/9/16.
 */
public class ServiceHealthIndicator extends AbstractHealthIndicator {

    private static final int MILLI_SEC_IN_ONE_SEC = 1000;
    private static final String HEADER_CONTENT_TYPE = "Content-type";
    private static final String DEFAULT_CHAR_SET = "UTF-8";
    private static final String APPLICATION_JSON = "application/json";
    private static final String PATH_SEPERATOR = "/";
    private static int TIMEOUT_SECOND = 3000;
    private static int RETRY = 1;
    private String name;
    private String url;
    private boolean production;

    private static final String HEALTH_CHECK_PATH = "/dependencyCheck";

    public ServiceHealthIndicator(String name, String url, boolean production) {
        this.name = name;
        this.url = url;
        this.production = production;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Health health = sendRequest(url + HEALTH_CHECK_PATH);
        builder.status(health.getStatus());
    }

    @Override
    public String getName() {
        return name;
    }

    protected Health sendRequest(String uri) throws IOException, URISyntaxException {
        HttpClient client = createClient();
        HttpGet get = new HttpGet(uri);
        get.setHeader("username", AbstractDependencyCheck.getUsername());
        get.setHeader("password", AbstractDependencyCheck.getUnencryptedPasswd());
        HttpResponse response = client.execute(get);

        try {
            validateResponse(response);
        } catch (Exception ex) {

        }
        ObjectMapper objectMapper = JacksonUtils.getObjectMapper();
        TypeReference<Health> type = new TypeReference<Health>() {
        };
        return (Health) objectMapper.readValue(EntityUtils.toString(response.getEntity(), DEFAULT_CHAR_SET), type);
    }

    protected HttpClient createClient() {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT_SECOND * MILLI_SEC_IN_ONE_SEC);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIMEOUT_SECOND * MILLI_SEC_IN_ONE_SEC);
        client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(RETRY, true));
        return client;
    }

    protected void validateResponse(HttpResponse response) throws HttpResponseException {
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            int code = response.getStatusLine().getStatusCode();
            String message = response.getStatusLine().getReasonPhrase();
            throw new HttpResponseException(code, message);
        }
    }

}
