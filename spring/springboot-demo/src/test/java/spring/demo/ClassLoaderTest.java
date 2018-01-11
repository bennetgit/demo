package spring.demo;

/**
 * Created by wangfacheng on 2018-01-05.
 */
public class ClassLoaderTest {

    public static ClassLoaderTest1 test1 = new ClassLoaderTest1();

    static {
        System.out.println("ClassLoaderTest 代码块被执行");
    }

    public ClassLoaderTest() {
        System.out.println("ClassLoaderTest 构造函数被执行");
    }

}
