import java.util.Arrays;

public class SortedMerge {

    public static void sortedMerge(int[] a, int na, int[] b) {

        int ia = na - 1;
        int ib = b.length - 1;
        int target = na + b.length - 1;

        while (ia >= 0 && ib >= 0) {
            if (a[ia] >= b[ib]) {
                a[target] = a[ia];
                ia--;
            }
            else {
                a[target] = b[ib];
                ib--;
            }
            target--;
        }
    }

    public static void main(String[] args) {

        int[] a1 = {1, 2, 3, 7, 8, 9, 10, 11, 15, 16, 17, -1, -1, -1,
            -1, -1, -1, -1};
        int na1 = 11;
        int[] b1 = {4, 5, 6, 12, 13, 14};

        int[] a2 = {1, 2, 4, 8, -1, -1, -1, -1, -1, -1, -1, -1};
        int na2 = 4;
        int[] b2 = {3, 5, 6, 7, 9};

        int[] a3 = {1, 2, 2, 3, 3, -1, -1, -1, -1, -1, -1, -1};
        int na3 = 5;
        int[] b3 = {2, 2, 2, 3, 3};

        sortedMerge(a1, na1, b1);
        sortedMerge(a2, na2, b2);
        sortedMerge(a3, na3, b3);

        System.out.println("Sorted a1: " + Arrays.toString(a1));
        System.out.println("Sorted a2: " + Arrays.toString(a2));
        System.out.println("Sorted a3: " + Arrays.toString(a3));
    }
}
