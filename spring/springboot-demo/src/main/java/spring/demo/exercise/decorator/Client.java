package spring.demo.exercise.decorator;

import redis.clients.util.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by feng on 18/4/7.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        LowerCaseInputStream inputStream = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(
                "/Users/feng/sources/myself/demo/spring/springboot-demo/src/main/java/spring/demo/exercise/decorator/test.text")));
        int c;
        while ((c = inputStream.read()) >= 0) {
            System.out.print((char) c);
        }

        inputStream.close();
    }
}
