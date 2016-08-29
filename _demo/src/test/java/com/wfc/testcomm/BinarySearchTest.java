package com.wfc.testcomm;

import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void testBinarySearch() {
        int[] searchs = { 1, 2, 3, 4, 5, 6, 7, 10 };
        int index = binarySearch(searchs, 0, searchs.length, 6);
        if (index > 0) {
            System.out.println("找到了 " + index);
        } else {
            System.out.println("没找到");
        }
    }

    private int binarySearch(int[] arrs, int startIndex, int endIndex, int key) {
        int low = startIndex;
        int high = endIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1; // 无符号右移
            if (key < arrs[mid]) {
                high = mid - 1;
            } else if (key > arrs[mid]) {
                low = mid + 1;
            } else {
                return arrs[mid];
            }
        }
        return -(low + 1);
    }

}
