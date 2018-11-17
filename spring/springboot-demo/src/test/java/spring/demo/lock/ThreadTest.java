package spring.demo.lock;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * Created by wangfacheng on 2018-04-24.
 */
public class ThreadTest {

    volatile Integer test_volatile = 1;

    public ThreadTest() {
        System.out.println("xxx");
    }

    @Test
    public void interruptTest() throws InterruptedException {

        test_volatile++;

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread testThread = new Thread(() -> {
            try {
                while (true) {
                }
            } finally {
                countDownLatch.countDown();
            }
        });
        //
        // Thread thread2 = new Thread(() -> {
        // int i = 0;
        // while (true) {
        // System.out.println("------> " + i++);
        // }
        // });

        // thread2.start();
        testThread.start();
        System.out.println(
                "testThread interrupt --> " + testThread.isInterrupted() + " is alived -->" + testThread.isAlive());

        Thread.sleep(5000);

        testThread.interrupt();
        // thread2.interrupt();

        System.out.println(
                "testThread interrupt --> " + testThread.isInterrupted() + " is alived -->" + testThread.isAlive());

        try {
            countDownLatch.await();
            System.out.println(
                    "testThread interrupt --> " + testThread.isInterrupted() + " is alived -->" + testThread.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class Task implements Runnable {
        @Override
        public void run() {

        }
    }
}
