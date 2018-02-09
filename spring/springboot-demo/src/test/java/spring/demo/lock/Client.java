package spring.demo.lock;

/**
 * Created by wangfacheng on 2018-02-05.
 */
public class Client {

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();

        Thread t1 = new Thread(() -> {
            try {
                lockTest.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        Thread t3 = new Thread(() -> {
            try {
                lockTest.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();

        Thread t2 = new Thread(() -> {
            lockTest.offer();
        });

        t2.start();

        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
