public class BinToString {

    public static String binToString(double n) {

        StringBuilder result = new StringBuilder(".");

        int bit = 0;
        for (int i = 0; i < 32; i++) {
            n = 2 * n;
            bit = (int) n;
            result.append(bit);
            n = n - bit;

            if (n == 0.0)
                break;
        }

        if (n > 0)
            return "ERROR: n = " + n;
        else
            return result.toString();
    }

    public static void main(String[] args) {

        double[] numbers = {0.72, 0.75, 0.1, 0.625, 0.5+0.25 + 0.125 + 0.0625};
        for (double n: numbers)
            System.out.println(n + " -> " + binToString(n));
    }
}
