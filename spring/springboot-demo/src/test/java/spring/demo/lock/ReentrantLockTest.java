package spring.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangfacheng on 2018-02-05.
 */
public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();

    private Condition notStart = lock.newCondition();

    private volatile boolean start;

    public String take() throws InterruptedException {

        lock.lock();
        try {

            while (!start) {
                System.out.println();
                System.out.println(
                        "not start ---------> " + Thread.currentThread().getName() + "  isLocked " + lock.isLocked());
                System.out.println("--------start await----------" + Thread.currentThread().getName());
                notStart.await();
                System.out.println("--------end await----------" + Thread.currentThread().getName());
            }
            return "hello world";
        } finally {
            lock.unlock();
        }
    }

    public void offer() {
        lock.lock();
        try {
            while (!start) {
                start = true;
                System.out.println("--------start ----------" + Thread.currentThread().getName());
                System.out.println("--------start signalAll----------" + Thread.currentThread().getName());
                notStart.signalAll();
                System.out.println("--------end signalAll----------" + Thread.currentThread().getName());
            }

        } finally {
            lock.unlock();
        }
    }
}
