public class FlipToWin {

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

    public static int flipToWin(int n) {

        int max = 0;
        int c1 = 0;
        int c2 = 0;
        int mode = 1;
        boolean prevZero = false;
        int bit = 0;

        for (int i = 0; i < 32 && n != 0; i++) {
            bit = n & 1;
            if (bit == 1) {
                if (mode == 1)
                    c1++;
                else //mode == 2
                    c2++;
                prevZero = false;
            }
            else { // bit == 0
                if (prevZero) { // need to restart counting
                    c1 = 0;
                    c2 = 0;
                    mode = 1;
                }
                else { // first zero
                    int newMax = c1 + c2 + 1;
                    max = newMax > max ? newMax : max;
                    if (mode == 1) {
                        mode = 2;
                        c2 = 0;
                    }
                    else {
                        mode = 1;
                        c1 = 0;
                    }
                }

                prevZero = true;
            }
            n = n >> 1;
        }

        // At the end we need to check for maximum again!
        if (!prevZero) {
            // If everything were 1s, we'd need to limit!
            int newMax = Math.min(32, c1 + c2 + 1);
            if (newMax > max)
                max = newMax;
        }

        return max;
    }

    public static void main(String[] args) {

        int[] numbers = {0, 1, 5, 1775, ~0, bitsToInt("11111011101111"), bitsToInt("111110011101111")};
        for (int n: numbers)
            System.out.println(n + " (" + intToBits(n) + ") -> " + flipToWin(n));

        System.out.println("~0 & 1 = " + ((~0) & 1));
    }
}
