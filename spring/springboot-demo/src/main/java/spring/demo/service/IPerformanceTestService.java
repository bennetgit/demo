package spring.demo.service;

import spring.demo.dto.request.PerformanceTestRequest;

import java.io.IOException;

/**
 * Created by facheng on 15.03.17.
 */
public interface IPerformanceTestService {

    void startTest(PerformanceTestRequest request) throws IOException;
}
