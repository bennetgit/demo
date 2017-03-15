package spring.demo.dto.request;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by facheng on 15.03.17.
 */
public class PerformanceTestRequest extends BaseRequest {

    private String url;
    private RequestMethod method;
    private String params;

    private int threadCount;

    private int taskCount;

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
