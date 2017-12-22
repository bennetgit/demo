package spring.demo.health;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;


/**
 * Created by faos on 16-10-27.
 */
public class LBSServerHealthIndicator extends AbstractHealthIndicator implements InitializingBean {

    private String name;

    private String url;
    private static final String LATEST_POSITION = "/truck/latest";


    public LBSServerHealthIndicator(String name, String url) {
        this.url = url;
        this.name = name;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (StringUtils.isEmpty(this.url)) {
            builder.up().withDetail("lbs", "unknown");
        } else {
            lbsHealthCheck(builder);
        }
    }

    private void lbsHealthCheck(Health.Builder builder){
        builder.up().withDetail("lbs", name);
        CloseableHttpClient client = null;
        try {
            client = createHttpClient();
            HttpPost post = new HttpPost(url+LATEST_POSITION);
            /*QueryLatestPosiRequest poRequest = new QueryLatestPosiRequest();
            List<LatestPosition> latestPoList = new ArrayList<LatestPosition>();
            for (Long truckId : request.getTruckIds()) {
                LatestPosition po = new LatestPosition();
                po.setTruck(String.valueOf(truckId));
                latestPoList.add(po);

            }
            poRequest.setLatestPositions(latestPoList);*/
            String request = "{\"latestPositions\":[],\"isGetAddr\":\"false\",\"isGetDistance\":\"false\",\"isGetDelay\":\"false\"}";
            post.setEntity(createEntity(request));
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            builder.withDetail("hello", statusCode);
        } catch (Exception e){
            builder.down(e);
        }finally {
            closeHttpClient(client);
        }
    }

    private static HttpEntity createEntity(String request) throws Exception {
        return new StringEntity(request, "application/json", "UTF-8");
    }
    private static CloseableHttpClient createHttpClient() {

        HttpClientBuilder builder = HttpClientBuilder.create().useSystemProperties();

        try {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5 * 1000)
                    .setSocketTimeout(60 * 1000)
                    .build();
            builder.setDefaultRequestConfig(requestConfig);
        } catch (Exception e) {
           // LOGGER.error("create httpclient exception", e);
        }
        return builder.build();
    }

    private static void closeHttpClient(CloseableHttpClient httpClient) {
        try {
            if(httpClient != null) {
                httpClient.close();
            }
        } catch(Exception e) {
            //LOGGER.error("httpclient close exception", e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(!StringUtils.isEmpty(this.url), "url for LBSServerHealthIndicator must be specified");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
}
