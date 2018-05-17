import java.util.Arrays;

public class MissingNumber {

    public static int missingNumber(int[] a) {
        
        int nBits = bitsUsed(a.length);
        int[] complementOnesCount = complementCount(a.length);
        int[] onesCount = countOnes(a);
        int expectedOnes = 1 << (nBits - 1);

        int result = 0;

        System.out.println("onesCount: " + Arrays.toString(onesCount));
        System.out.println("complement: " + Arrays.toString(
                    complementOnesCount));
        System.out.println("expected count: " + expectedOnes);

        for (int i = 0; i < nBits; i++)
            if (onesCount[i] + complementOnesCount[i] < expectedOnes)
                result |= (1 << i);

        return result;
    }

    private static int bitsUsed(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n >>= 1;
        }

        return count;
    }

    private static int[] complementCount(int n) {
        int nBits = bitsUsed(n);
        int[] count = new int[nBits];
        for (int i = 0; i < count.length; i++)
            count[i] = 0;

        for (int i = n + 1; i < (1 << nBits); i++)
            for (int j = 0; j < nBits; j++)
                count[j] += (i >> j) & 1;

        return count;
    }

    private static int[] countOnes(int[] a) {
        int nBits = bitsUsed(a.length);
        int[] count = new int[nBits];
        for (int i = 0; i < count.length; i++) count[i] = 0;

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < nBits; j++)
                count[j] += (a[i] >> j) & 1; // Get bit j of a[i]

        return count;
    }

    public static void main(String[] args) {
        int n = 1000;
        int c = 0;
        int[] a = new int[n];

        if (args.length != 1) {
            System.out.println("Syntax: number");
            System.exit(1);
        }

        int skip = Integer.parseInt(args[0]);

        for (int i = 0; i < a.length; i++) {
            if (c == skip)
                c++;
            a[i] = c;
                c++;
        }

        int missing = missingNumber(a);
        System.out.println("Missing number = " + missing);
    }
}
