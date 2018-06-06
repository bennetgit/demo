package spring.demo.lock;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * Created by wangfacheng on 2018-04-08.
 */
public class LockTest {

    @Test
    public void testLock() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            System.out.println("t1 start request String.class monitor object");
            synchronized (String.class) {
                System.out.println("t1 lock String, sleep 10s");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start request String.class monitor object");
            synchronized (String.class) {
                System.out.println("t2 request lock String.class monitor success");
            }

            countDownLatch.countDown();
        });

        t1.start();
        t2.start();

        countDownLatch.await();
    }

    public synchronized static void test(){
        System.out.println("xxx");
    }
}
