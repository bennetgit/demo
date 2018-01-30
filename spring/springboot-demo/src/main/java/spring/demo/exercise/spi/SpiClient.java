package spring.demo.exercise.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by wangfacheng on 2018-01-30.
 */
public class SpiClient {

    public static void main(String[] args) {

        ServiceLoader<ISpiTest> services = ServiceLoader.load(ISpiTest.class);
        Iterator<ISpiTest> iterator = services.iterator();
        ISpiTest spiTest;

        while (iterator.hasNext()) {
            spiTest = iterator.next();
            spiTest.sayHello();
        }
    }
}
