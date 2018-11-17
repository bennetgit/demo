package spring.demo.exercise.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by facheng on 18-6-6.
 */
public class BloomFilterSample {

    static int size = Integer.MAX_VALUE;
    static Random generator = new Random();

    public static void main(String[] args) {
        BloomFilter filter = BloomFilter.create(Funnels.integerFunnel(), size);//false  positive probability is default --> 0.03
        Set<Integer> hashSet = new HashSet<>();
        int tempNumber;
        int errorNum = 0;

        for (int i = 0; i < 100; i++) {
            tempNumber = generator.nextInt();
            if (filter.mightContain(tempNumber) != hashSet.contains(tempNumber)) {
                errorNum++;
            }

            filter.put(tempNumber);
            hashSet.add(tempNumber);
        }

        System.out.println("error count: " + errorNum + ", error rate = " + String.format("%f", (float) errorNum / size));
    }
}
