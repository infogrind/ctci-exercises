public class FlipsNeeded {

    public static int flipsNeeded(int a, int b) {
        return countOnes(a ^ b);
    }

    public static int countOnes(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("29, 15 -> " + flipsNeeded(29, 15));
    }
}
