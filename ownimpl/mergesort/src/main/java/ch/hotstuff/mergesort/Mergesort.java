package ch.hotstuff.mergesort;

public class Mergesort {

    public static void mergesort(int[] array) {
        mergesort(array, 0, array.length);
    }
    

    private static void mergesort(int[] array, int from, int until) {

        if (until - from <= 1)
            return;

        int mid = (until + from) / 2;
        mergesort(array, from, mid);
        mergesort(array, mid, until);
        merge(array, from, mid, until);
    }

    private static void merge(int[] array, int from, int mid, int until) {

        int[] temp = new int[until - from];
        int i = 0;
        int left = from;
        int right = mid;

        while (left < mid) {
            if (right >= until || array[left] <= array[right]) {
                temp[i] = array[left];
                left++;
            }
            else {
                temp[i] = array[right];
                right++;
            }
            i++;
        }

        for (i = 0; i < temp.length; i++)
            array[from + i] = temp[i];
    }
}
