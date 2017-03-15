package spring.demo.service.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import spring.demo.constant.Constants;
import spring.demo.dto.request.PerformanceTestRequest;
import spring.demo.service.IPerformanceTestService;
import spring.demo.util.ExecutorFactory;
import spring.demo.websocket.MyWebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by facheng on 15.03.17.
 */

@Service
public class PerformanceTestServiceImpl implements IPerformanceTestService {

    @Override
    public void startTest(PerformanceTestRequest request) throws IOException {

        AtomicInteger atomicInteger = new AtomicInteger(0);

        long start = System.currentTimeMillis();

        ThreadPoolExecutor executor = ExecutorFactory.generateExecutor(request.getThreadCount());

        List<Future<HttpResponse>> futureList = new ArrayList<>();

        for (int i = 0; i < request.getTaskCount(); i++) {
            futureList.add(executor.submit(() -> send(request, atomicInteger)));
        }
        try {
            HttpResponse httpResponse;
            for (Future<HttpResponse> future : futureList) {
                httpResponse = future.get();
                MyWebSocket.sendInfo("执行次数" + atomicInteger.incrementAndGet() + ";status = "
                        + httpResponse.getStatusLine().getStatusCode() + ";response : "
                        + EntityUtils.toString(httpResponse.getEntity(), Constants.DEFAULT_CHAR_SET));
            }

            MyWebSocket.sendInfo("cost time " + (System.currentTimeMillis() - start) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    protected HttpResponse send(PerformanceTestRequest request, AtomicInteger atomicInteger) {
        if (request == null) {
            return null;
        }
        HttpResponse response = null;
        try {

            HttpClient client = HttpClientBuilder.create().build();

            HttpPost httpUriRequest = new HttpPost(request.getUrl());
            HttpClientContext context = new HttpClientContext();
            httpUriRequest.setEntity(new StringEntity(request.getParams(), ContentType.APPLICATION_JSON));

            response = client.execute(httpUriRequest, context);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
