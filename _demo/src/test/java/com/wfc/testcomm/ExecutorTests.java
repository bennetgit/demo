package com.wfc.testcomm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ExecutorTests {

    private ThreadPoolExecutor executor;

    private AtomicInteger atomicInteger;
    private List<Future<Integer>> runners = new ArrayList<Future<Integer>>();

    private Object o1 = new Object();
    private Object o2 = new Object();
    private Object o3 = new Object();

    private volatile boolean b1 = true, b2 = true, b3 = true, b4 = true;

    @Before
    public void init() {
        atomicInteger = new AtomicInteger(0);
//        executor = new ThreadPoolExecutor(5, 10, 60l, TimeUnit.SECONDS, new LinkedBlockingQueue<Callable>(),
//                new BasicThreadFactory.Builder().daemon(true).namingPattern("executor_%d").build(),
//                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Ignore
    @Test
    public void testExecutor() {
        for (int i = 0; i < 10; i++) {
            runners.add(executor.submit(new Runner()));
        }
        try {
            for (Future<Integer> future : runners) {
                Integer result = future.get();
                atomicInteger.addAndGet(result);
            }
            System.out.println(atomicInteger);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class Runner implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return 1;
        }

    }

    @Ignore
    @Test
    public void testSync() {

        synchronized (o3) {
            Thread t1 = new Thread(new Sync1());
            Thread t2 = new Thread(new Sync2());
            t1.start();
            t2.start();
            System.out.println(Thread.holdsLock(o3));
        }

        int i = 0;
        while (b1 || b2 || b3 || b4) {
            System.out.println("第" + i++ + "次sleep");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testThread() {
        Thread t0 = new Thread();
        Thread t1 = new Thread(new Sync2());
        t0.start();
        t1.start();
        t1.start();
        t1.start();
        t1.start();
    }

    @Test
    public void testMapSort() {
        Map<String, Object> map = new HashMap<String, Object>();
    }

    private class Sync2 implements Runnable {

        @Override
        public void run() {

            synchronized (o2) {
                b1 = false;
                System.out.println("[Sync2]Incoming o2======");
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            synchronized (o1) {
                b2 = false;
                System.out.println("[Sync2]Incoming o1======");
            }
        }

    }

    private class Sync1 implements Runnable {

        @Override
        public void run() {

            synchronized (o1) {
                b3 = false;
                System.out.println("[Sync1]Incoming o1======");
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (o2) {
                b3 = false;
                System.out.println("[Sync1]Incoming o2======");
            }
        }
    }

}
