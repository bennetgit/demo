package spring.demo.exercise.spi;

/**
 * Created by wangfacheng on 2018-01-30.
 */
public class MySpiTest implements ISpiTest {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
