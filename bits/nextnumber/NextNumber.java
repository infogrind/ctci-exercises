public class NextNumber {

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

    public static void printNextNumbers(int n) {

        int i = firstOneAfterZero(n);
        int j = firstZeroAfterOne(n);

        if (i == -1 || j == -1) {
            System.out.println("Error: no next largest or smaller for number " + n);
            return;
        }

        int nextSmaller = n - (1 << i) + (1 << (i - 1));
        int nextLarger = n + (1 << j) - (1 << (j - 1));

        System.out.println(nextSmaller);
        System.out.println(nextLarger);
    }

    private static int firstOneAfterZero(int n) {

        if (n == 0)
            return -1;

        int pos = 0;

        // Skip initial ones
        while (pos < 32 && (n & 1) == 1) {
            n >>= 1;
            pos++;
        }

        if (pos == 32)
            return -1;

        if (n == 0)
            return -1; // number was of the form 0....01....1 -> no solution

        // Go through the zeros until we find first one
        while ((n & 1) == 0) {
            n >>= 1;
            pos++;
        }

        return pos;
    }

    private static int firstZeroAfterOne(int n) {

        if (n == 0)
            return -1;  // no solution

        int pos = 0;

        // Skip initial zeros
        while ((n & 1) == 0) {
            n >>= 1;
            pos++;
        }

        while (pos < 32 && (n & 1) == 1) {
            n >>= 1;
            pos ++;
        }

        // If pos == 32 then we've gone past the end without finding another
        // zero -> error
        if (pos == 32)
            return -1;

        return pos;
    }

    public static void main(String[] args) {

        int[] numbers = {0, 1, 5, 10};
        for (int n: numbers) {
            System.out.println(n + " (" + intToBits(n) + "):");
            printNextNumbers(n);
        }
    }
}
