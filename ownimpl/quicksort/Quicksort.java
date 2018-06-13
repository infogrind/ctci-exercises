import java.util.Arrays;

public class Quicksort {

    public static void quicksort(int[] array) {
        quicksort(array, 0, array.length);
    }

    private static void quicksort(int[] array, int l, int h) {

        if (l == h)
            return;

        int m = partition(array, l, h);
        quicksort(array, l, m);
        quicksort(array, m + 1, h);
    }

    private static int partition(int[] array, int l, int h) {

        int firsthigh = l; // left of this: < pivot
        int p = h - 1;  // pivot index

        for (int i = l; i < h; i++) {
            if (array[i] < array[p]) {
                swap(array, i, firsthigh);
                firsthigh++;
            }
        }

        swap(array, p, firsthigh);
        return firsthigh;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String args[]) {

        int[] array = {2, 4, 1, 9, 4, 3, -1, 100, 33, 33, 0, -2};
        quicksort(array);

        System.out.println(Arrays.toString(array));
    }
}
