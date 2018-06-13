package spring.demo.exercise.specialbean;

import org.springframework.beans.factory.DisposableBean;

/**
 * Created by facheng on 18-6-13.
 */
public class DisposableBeanTest implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("============== destroy= ============" + Thread.currentThread().getId());
    }
}
