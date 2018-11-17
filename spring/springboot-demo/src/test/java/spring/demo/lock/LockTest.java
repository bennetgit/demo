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

    int index;

    @Test
    public void syncLockTest(){

        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(()->access(countDownLatch)).start();

        new Thread(()->access(countDownLatch)).start();

        new Thread(()->access(countDownLatch)).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("index ->" + index);

    }

    private synchronized void access(CountDownLatch countDownLatch) {
        while (true) {
            System.out.println("current thread " + Thread.currentThread().getName() + "current index = " + index);
            if (index >= 1000) {
                countDownLatch.countDown();
                return;
            }
            index++;
        }
    }
}
