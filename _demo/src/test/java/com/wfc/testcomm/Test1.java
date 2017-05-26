package com.wfc.testcomm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;

public class Test1 {

    private static final Logger LOGG = org.slf4j.LoggerFactory.getLogger(Test1.class);

    @Ignore
    @Test
    public void TT() {
        // System.out.println(MessageFormat.format("{0}nihoa{0}", 111));
        StringBuffer str = new StringBuffer();
        System.out.println(str.toString());
    }

    @Ignore
    @Test
    public void fileter() {

        List<String> strs = new ArrayList<String>();
        String s1 = new String("1");
        strs.add(s1);
        strs.add("6");
        strs.add("5");
        strs.add("4");
        strs.add("3");
        strs.add("2");
        // String[] ss = null;
        // strs = null;
        // strs = null;{ "1", "1", "7" }
        // filterOwnerTruck(strs);

        s1 = "7";
        List<User> user = new ArrayList<User>();
        User u1 = new User("hah", 12);
        User u2 = new User("jhhhh", 11);
        user.add(u1);
        user.add(u2);
        u1.setName("nihao ");

        System.out.println(user);
        // System.out.println(s1);

    }

    @Ignore
    @Test
    public void testTET() {
        System.out.println(T1.class.getSuperclass());
        System.out.println(T.class.getSuperclass());
        System.out.println(T.class.getSuperclass());

        List<String> strList = new ArrayList<String>();
        String[] testArr = { "1", "2" };
        strList = Arrays.asList(testArr);
        strList.addAll(Arrays.asList(testArr));
        strList.add("3");
        System.out.println(Arrays.toString(testArr));

        // Collections.addAll(strList, testArr);

    }

    @Ignore
    @Test
    public void testBigDecimal() {
        BigDecimal va1 = new BigDecimal("0.0");
        BigDecimal va2 = null;
        System.out.print(va1.add(va2));
    }

    @Ignore
    @Test
    public void testLongEqual() {
        List<Long> longList = new ArrayList<Long>();
        longList.add(1l);
        longList.add(3720l);
        // System.out.println(longList.contains(3720L));
        Long tt = 1l;
        Test13 tt1 = (Test13) null;

        System.out.println(tt1);
    }

    @Ignore
    @Test
    public void testUUID() {

        Map<String, T1> testMap = new HashMap<String, T1>();
        testMap.put("1", new T1());
        System.out.println(testMap);
        T1 val = testMap.get("1");
        val.val = "2";
        System.out.println(testMap);
    }

    @Test
    public void testModulo() {
        String date_pattern = "yyyy-MM-DD HH:mm:ss.Szz";

        LocalDateTime time = LocalDateTime.now();

        System.out.println(time.equals(LocalDateTime.fromDateFields(time.toDate())));
        // int i = 10;
        // int[] arrs = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // for (int arr : arrs) {
        // System.out.println(arr % i);
        // }
    }

    enum Test13 {

    }

    interface T {
    }

    class T1 {

        String val;
        Long val2;

        public String toString() {
            return "{" + val + ":" + val2 + "}";
        }
    }

    class T2 {
    }

    private void filterOwnerTruck(List<String> truckList) {
        // filter all owner truck
        if (truckList != null && !truckList.isEmpty()) {
            // Iterator<String> iterator = truckList.iterator();
            // while (iterator.hasNext()) {
            // if (iterator.next().equals("1")) {
            // iterator.remove();
            // }
            // }
            for (final Iterator<String> iterator = truckList.iterator(); iterator.hasNext();) {
                if (iterator.next().equals("1")) {
                    iterator.remove();
                }
            }
            System.out.println(truckList.toString());
        }
    }

    private Set<String> getRecipients(String[] otmsSettigEmails, List<String> superUserEamils) {
        Set<String> temps = new HashSet<String>();
        if (otmsSettigEmails != null) {
            for (String str : otmsSettigEmails) {
                temps.add(str);
            }
        }

        if (superUserEamils != null) {
            temps.addAll(superUserEamils);
        }

        return temps;
    }
}
