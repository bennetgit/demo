package com.wfc.cxf.pattern.quicksort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

    static void quickSort(int[] s, int l, int r) {
        if (l < r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x)
                    j--;
                if (i < j) {
                    s[i++] = s[j];
                }

                while (i < j && s[i] < x)
                    i++;
                if (i < j) {
                    s[j--] = s[i];
                }

            }
            s[i] = x;
            quickSort(s, l, i - 1);
            quickSort(s, i - 1, r);
        }
    }

    private static void testQuickSort() {
        int[] intArr = { 3, 2, 3, 1, 5 };
        int i = 0, j = 4;
        int x = intArr[0];
        while (i < j) {

            // j开始找到小于或等于基准数x的元素
            while (i < j && intArr[j] > x)
                j--;
            if (i < j) {
                intArr[i++] = intArr[j];
            }

            // i开始找到大于基准数x的元素
            while (i < j && intArr[i] < x)
                i++;
            if (i < j) {
                intArr[j--] = intArr[i];
            }

        }

        System.out.print(Arrays.toString(intArr));
    }

    private static int loop(int arg, int result) {
        if (arg < 1) {
            return 1;
        }
        return result + loop(arg / 2, arg % 2);
    }

    public static void main(String[] args) {
        // testQuickSort();

        // int i = 9;
        // System.out.print(loop(789, 0));
        List<String> strs = new ArrayList<String>();

    }


}
