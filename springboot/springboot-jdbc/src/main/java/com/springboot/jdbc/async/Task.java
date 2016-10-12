package com.springboot.jdbc.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Task {

    private static Random random = new Random();

    @Async
    public void doTaskOne() throws InterruptedException {

        long start = System.currentTimeMillis();
        System.out.println("开始任务一");
        Thread.sleep(getRandom());
        System.out.println("结束任务一，耗时：" + (System.currentTimeMillis() - start) + "毫秒");

    }

    @Async
    public void doTaskTwo() throws InterruptedException {

        long start = System.currentTimeMillis();
        System.out.println("开始任务二");
        Thread.sleep(getRandom());
        System.out.println("结束任务二，耗时：" + (System.currentTimeMillis() - start) + "毫秒");

    }

    @Async
    public void doTaskThree() throws InterruptedException {

        long start = System.currentTimeMillis();
        System.out.println("开始任务三");
        Thread.sleep(getRandom());
        System.out.println("结束任务三，耗时：" + (System.currentTimeMillis() - start) + "毫秒");

    }

    private long getRandom() {
        return random.nextInt(10000);
    }
}
