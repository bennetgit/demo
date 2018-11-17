package spring.demo.exercise.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by facheng on 18-7-9.
 */
public class RecipesBarrier {

    static String barrierPath = "/curator_recipes_barrier_path";

    static DistributedBarrier barrier;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

                client.start();
                barrier = new DistributedBarrier(client, barrierPath);
                try {
                    barrier.setBarrier();
                    barrier.waitOnBarrier();
                    System.out.println(Thread.currentThread().getName() + "启动");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }

        Thread.sleep(2000l);
        barrier.removeBarrier();

    }
}
