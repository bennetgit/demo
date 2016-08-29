package com.wfc.testcomm;

import org.junit.Test;

public class TestClass {

    @Test
    public void testClass() {
        int[] array = { 1, 2, 3, 1, 1, 1, 10 };
        System.out.println("结果  ----> " + skipArr(array));
    }

    public static int skipArr(int[] array) {
        boolean canSkip = false;
        for (int i=0;i<array.length;i++) {
            if (array[i] + i > array.length) {
                canSkip = true;
            }
        }
        if (!canSkip) {
            return -1;
        }
        int i = 0;
        int tempVal = 0;
        int index = 0;
        for (;;) {
            if (++i > array.length) {
                return -1;
            }
            if (tempVal + index > array.length) {
                return i;
            } else {
                tempVal = array[index];
                index = tempVal + index;
            }
        }
    }
}
