package spring.demo;

/**
 * Created by wangfacheng on 2018-01-05.
 */
public class ClassLoaderTest1 {

    static {
        System.out.println("ClassLoaderTest1 代码块被执行");
    }

    public ClassLoaderTest1() {
        System.out.println("ClassLoaderTest1 构造函数被执行");
    }

}
