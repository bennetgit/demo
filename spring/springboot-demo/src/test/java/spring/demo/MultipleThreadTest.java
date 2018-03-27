package spring.demo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by feng on 18/3/13.
 */
public class MultipleThreadTest {

    private static Integer mutex = 0;
//    private static Object monitor = new Object();
    private static Integer monitor = 0;

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(() -> {

            System.out.println("current thread " + Thread.currentThread().getName() + " is daemon?"
                    + Thread.currentThread().isDaemon() + " mutex's hashCode = " + mutex.hashCode());
            synchronized (monitor) {
                for (int i = 0; i < 1000; i++) {
                    mutex++;
                    // System.out.println("current thread " +
                    // Thread.currentThread().getName() + " mutex = " + mutex);
                }
            }
            countDownLatch.countDown();
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("current thread " + Thread.currentThread().getName() + " is daemon?"
                    + Thread.currentThread().isDaemon() + " mutex's hashCode = " + mutex.hashCode());
            synchronized (monitor) {
                for (int i = 0; i < 1000; i++) {
                    mutex++;
                    // System.out.println("current thread " +
                    // Thread.currentThread().getName() + " mutex = " + mutex);
                }
            }
            countDownLatch.countDown();
        });

        thread1.start();
        thread2.start();
        try {
            countDownLatch.await();
            System.out.println(mutex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
