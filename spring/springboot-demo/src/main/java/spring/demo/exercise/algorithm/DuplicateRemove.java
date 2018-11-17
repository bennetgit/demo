package spring.demo.exercise.algorithm;

import spring.demo.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by facheng on 18-5-30.
 */
public class DuplicateRemove {

    public static void main(String[] args) {

        List<String> sLists = new ArrayList<String>() {

            {
                add("");
                add("1");
                add("2");
                add("3");
                add("");
            }
        };

        System.out.println(sLists.stream().filter(s -> StringUtil.isNotBlank(s)).collect(Collectors.toList()));
    }
}
