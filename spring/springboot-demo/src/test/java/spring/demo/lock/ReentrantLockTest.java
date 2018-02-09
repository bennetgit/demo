package spring.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangfacheng on 2018-02-05.
 */
public class ReenLockTest {

    private ReentrantLock lock = new ReentrantLock();
    private Condition test1 = lock.newCondition();

    class MyTask implements Runnable {

        @Override
        public void run() {

            lock.lock();

            try {

            } finally {
                lock.unlock();
            }

            System.out.println("current thread " + Thread.currentThread().getName());
        }
    }
}
