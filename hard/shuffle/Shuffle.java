import java.util.Random;
import java.util.Arrays;

public class Shuffle {

    public static int[] shuffle(int n) {

        int[] array = new int[n];
        for (int i = 0; i < array.length; i++)
            array[i] = i+1;

        Random rnd = new Random();

        for (int i = 0; i < array.length; i++) {
            int r = realmod(rnd.nextInt(), array.length - i);
            swap(array, i, r + i);
        }

        return array;
    }

    private static int realmod(int a, int m) {
        return (a % m + m) % m;
    }

    private static void swap(int[] array, int i, int j) {

        if (i < 0)
            throw new IllegalArgumentException("i negative");
        if (i >= array.length)
            throw new IllegalArgumentException("i too large");
        if (j < 0)
            throw new IllegalArgumentException("j negative");
        if (j >= array.length)
            throw new IllegalArgumentException("j too large");

        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int[] a = shuffle(10);
            System.out.println(Arrays.toString(a));
        }
    }
}
