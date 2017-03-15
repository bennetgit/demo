package spring.demo.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import spring.demo.dto.request.PerformanceTestRequest;
import spring.demo.service.IPerformanceTestService;
import spring.demo.util.ExecutorFactory;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by facheng on 15.03.17.
 */

@Service
public class PerformanceTestServiceImpl implements IPerformanceTestService {

    @Override
    public void startTest(PerformanceTestRequest request) throws IOException {

        ThreadPoolExecutor executor = ExecutorFactory.generateExecutor(request.getThreadCount());
        for (int i = 0; i < request.getTaskCount(); i++) {
            executor.execute(() -> send(request));
        }

    }

    protected void send(PerformanceTestRequest request) {
        if (request == null) {
            return;
        }

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpUriRequest = new HttpPost(request.getUrl());
        HttpClientContext context = new HttpClientContext();
        httpUriRequest.setEntity(new StringEntity(request.getParams(), ContentType.APPLICATION_JSON));
        try {
            HttpResponse response = client.execute(httpUriRequest, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
