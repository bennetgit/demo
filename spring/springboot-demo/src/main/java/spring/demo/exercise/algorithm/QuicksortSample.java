package spring.demo.exercise.algorithm;

import java.util.Arrays;

/**
 * Created by facheng on 18-5-16.
 */
public class QuicksortSample {


    //1、选择基准直
    //2、分治法递归排序

    static int partition(int[] a, int startIndex, int endIndex) {

        int privotKey = a[startIndex];
        int temp;

        int i = startIndex;
        int j = endIndex;

        while (true) {
            while (a[j] >= privotKey && j > i) {
                j--;
            }

            while (a[i] <= privotKey && j > i) {
                i++;
            }

            temp = a[j];
            a[j] = a[i];
            a[i] = temp;

            if (i == j) {
                break;
            }
        }

        //基准值归位
        a[startIndex] = a[i];
        a[i] = privotKey;
        System.out.println("partition end--------- start index -->" + startIndex + " -----------> end index -->" + endIndex);
        return i;
    }

    static void quickSort(int[] array, int startIndex, int endIndex) {

        if (startIndex >= endIndex) {
            return;
        }

        int keyIndex = partition(array, startIndex, endIndex);

        quickSort(array, startIndex, keyIndex);
        quickSort(array, keyIndex + 1, endIndex);
    }

    public static void main(String[] args) {
        int[] array = {6, 2, 5, 2, 1, 3, 7, 3, 6, 8, 10};
        quickSort(array, 0, 10);
        System.out.println(Arrays.toString(array));
    }

}
