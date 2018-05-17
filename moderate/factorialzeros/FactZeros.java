public class FactZeros {

    public static int factZerosFast(int n) {
        int count = 0;
        for (int i = 5; n / i > 0; i = i * 5)
            count += n / i;

        return count;
    }

    public static int factZeros(int n) {
        TwosFives count = new TwosFives(0,0);
        for (int i = 2; i <= n; i++)
            count = count.add(countTwosFives(i));

        return count.min();
    }

    private static TwosFives countTwosFives(int n) {
        int twos = 0;
        int fives = 0;
        while (n % 10 == 0) {
            twos++;
            fives++;
            n = n / 10;
        }

        while (n % 2 == 0) {
            twos++;
            n >>= 1;
        }

        while (n % 5 == 0) {
            fives++;
            n = n / 5;
        }

        return new TwosFives(twos, fives);
    }

    public static void main(String[] args) {
        
        int[] numbers = {2, 3, 4, 5, 10, 20, 100, 1000, 10000, 100000};
        for (int i: numbers) {
            System.out.println(i + " -> " + factZeros(i) + " zeros");
            System.out.println(i + " -> " + factZerosFast(i) + " zeros (fast)");
        }
    }
}
