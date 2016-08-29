package com.wfc.cxf.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LambdaTest {

    public static void main(String[] args) {
//        List<String> ll = new ArrayList<String>();
//        ll.add("1");
//        ll.forEach(o -> {
//            System.out.println(o);
//        });
//        AtomicInteger i = new AtomicInteger(10);
//        try {
//            Thread.sleep(10 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(i.decrementAndGet());
    }

    public void distinctPrimary(String... numbers) {
        // List<String> l = Arrays.asList(numbers);
        // List<Integer> r = l.stream().map(e -> new Integer(e)).filter(e ->
        // Primes.isPrime(e)).distinct()
        // .collect(Collectors.toList());
        // System.out.println("distinctPrimary result is: " + r);
    }
}
