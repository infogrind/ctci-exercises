public class PairwiseSwap {

    private static final int EVEN_MASK = 1431655765;
    private static final int ODD_MASK = -1431655766;

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

    public static int pairwiseSwap(int n) {
        return ((n << 1) & ODD_MASK) | ((n >> 1) & EVEN_MASK);
    }

    public static void main(String[] args) {

        int[] numbers = {0, 1, 17, 123421, -1233};
        for (int n: numbers)
            System.out.println(n + ":\n" + intToBits(n) + " -> \n" + intToBits(pairwiseSwap(n)));

    }
}
