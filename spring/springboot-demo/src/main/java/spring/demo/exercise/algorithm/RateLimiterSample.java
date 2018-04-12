package spring.demo.exercise.algorithm;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by wangfacheng on 2018-04-12.
 */
public class RateLimiterSample {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);

        for (int i = 0; i < 10; i++) {
            System.out.println("xxxxxxxxxxx  "+i);
            System.out.println("等待时间" + rateLimiter.acquire());
            new Thread(new MockRequest(i)).start();
        }
    }

    private static class MockRequest implements Runnable {
        private int id;

        public MockRequest(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(id);
        }
    }
}
