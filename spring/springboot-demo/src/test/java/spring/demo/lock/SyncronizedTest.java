package spring.demo.lock;

/**
 * Created by wangfacheng on 2018-02-08.
 */
public class SyncronizedTest {

    public static void main(String[] args) {
        try {
            synchronized ("1") {
                "1".wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
