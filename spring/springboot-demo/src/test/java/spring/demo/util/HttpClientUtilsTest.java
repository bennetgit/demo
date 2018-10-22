package spring.demo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Created by wangfacheng on 2018-10-10.
 */
public class HttpClientUtilsTest {

    @Test
    public void pickupTest() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader("/home/facheng/backup/test.txt"))) {

            StringBuilder requestPayload = new StringBuilder("");

            fileReader.lines().forEach(line -> requestPayload.append(line));

            String url = "http://192.168.3.194:8088/ws/openapi/1.0/pair/quickpickup";
            Map<String, String> headers = new HashMap<String, String>() {
                {
                    put("apiCaller", "WMS");
                    put("userAccount", "amy.standsr4@otms.com");
                    put("currentTime", "1538966065249");
                    put("signKey", "5d8f579c6619b40d7ec8d5d6e1d59fb7ab728a8cc7e9404cc502e137544eeae9");
                }
            };
            System.out.println(HttpClientUtils.doPost(url, headers, requestPayload.toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
