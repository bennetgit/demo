package spring.demo.exercise.algorithm;

/**
 * Created by facheng on 18-5-16.
 */
public class MiddleSearchSample {

    static int search(int[] a, int targetValue) {

        int targetIndex = 0;

        int low = 0;
        int high = a.length;
        int middle;
        while (true) {
            if (low > high) {
                break;
            }
            middle = (low + high) / 2;

            if (a[middle] == targetValue) {
                return middle;
            }

            if (a[middle] < targetValue) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 7, 8, 10, 99, 100};
        System.out.println(search(array, 99));
    }
}
