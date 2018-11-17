package spring.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangfacheng on 2018-10-22.
 */
public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }

    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;

            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {

        try (FileOutputStream outputStream = new FileOutputStream("spring/demo/$Proxy0.class")) {
            outputStream.flush();
        } catch (Exception e) {

        }

        File file = new File("$Proxy0.class");
        System.out.println(file.getAbsoluteFile());

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);// 配置是否将动态生成的字节码保存

        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();

    }
}
