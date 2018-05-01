public class Insertion {

    private static String intToBits(int n) {

        StringBuilder sb = new StringBuilder();
        int mask = 1 << 31;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0)
                sb.append("1");
            else
                sb.append("0");
            n = n << 1;
        }
        return sb.toString();
    }

    private static int bitsToInt(String bits) {
        int mask = 1;
        int result = 0;
        int twopower = 1;
        for (int i = bits.length() - 1; i >= 0; i--) {
            if (bits.charAt(i) == '1')
                result += twopower;
            else if (bits.charAt(i) != '0')
                throw new IllegalArgumentException("Invalid bit string: " + bits);
            twopower = twopower << 1;
        }
        return result;
    }

    public static int insert(int n, int m, int i, int j) {

        int mask = (~0 << (j+1)) | ~(~0 << i);
        return (n & mask) | (m << i);
    }


    public static void main(String[] args) {

        int[] numbers = {2, 3, bitsToInt("1101"), 15};
        for (int n : numbers) {
            System.out.println("" + n + ": " + intToBits(n));
        }

        int n = bitsToInt("10000000000");
        int m = bitsToInt("10011");
        System.out.println("n: " + intToBits(n));
        System.out.println("Inserted: " + intToBits(insert(n, m, 2, 6)));
    }
}
