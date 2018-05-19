public class CountTwos {

    public static int countTwos(int n) {
        int d = 1;
        int count = 0;

        do {
            d = d * 10;
            count += (n / d) * (d / 10);
            count += Math.min(Math.max(0, n % d - (2*d/10 - 1)), d/10);
        } while (n / d > 0);

        return count;
    }

    public static int countTwosSlow(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++)
            count += countTwoDigits(i);
        return count;
    }

    private static int countTwoDigits(int n) {
        String nstr = "" + n;
        int count = 0;
        for (char c: nstr.toCharArray())
            if (c == '2')
                count++;
        return count;
    }

    public static void main(String[] args) {

        int[] numbers = {2, 5, 9, 25, 32, 132, 3287, 10000, 12222, 43222,
            43228};

        for (int n : numbers)
            System.out.println(n + " -> " + countTwos(n) + ", " +
                    countTwosSlow(n));
    }
}
