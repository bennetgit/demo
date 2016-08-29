package com.wfc.testcomm;

public class ClassLoaderTest {

    public static void main(String[] args) {

        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }

    class TestClassLoader {
    }

}
