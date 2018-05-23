import java.util.List;
import java.util.LinkedList;

public class KthMultiple {

    public static List<Integer> kthMultiple(int k) {

        LinkedList<Integer> result = new LinkedList<Integer>();

        int i = 0;
        int n = 0;

        while (i < k) {
            n++;
            if (isGood(n)) {
                result.addLast(n);
                i++;
            }
        }

        return result;
    }

    private static boolean isGood(int n) {
        while (n % 3 == 0)
            n /= 3;
        while (n % 5 == 0)
            n /= 5;
        while (n % 7 == 0)
            n /= 7;

        return n == 1;
    }

    public static void main(String[] args) {

        List<Integer> multiples = kthMultiple(20);
        System.out.println(multiples);
    }
}
