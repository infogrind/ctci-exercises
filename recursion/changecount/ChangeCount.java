import java.util.Arrays;

public class ChangeCount {

    public static int countChange(int n, int[] coins, boolean useMemo) {

        int[][] memo = new int[n+1][coins.length + 1];
        for (int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], -1);

        return countChange(n, coins, coins.length, memo, useMemo);
    }

    private static int countChange(int n, int[] coins, int nCoins, int[][] memo, boolean useMemo) {

        if (n == 0)
            return 1;
        else if (nCoins == 0)
            return 0;
        else {

            if (memo[n][nCoins] == -1 || !useMemo) {
                int count = 0;
                if (n >= coins[nCoins - 1])
                    count += countChange(n - coins[nCoins - 1], coins, nCoins, memo, useMemo);
                count += countChange(n, coins, nCoins - 1, memo, useMemo);

                memo[n][nCoins] = count;
            }

            return memo[n][nCoins];
        }
    }

    public static int bottomUpCount(int n, int[] coins) {

        int[][] counts = new int[n + 1][coins.length + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= coins.length; j++) {
                if (i == 0)
                    counts[i][j] = 1;
                else if (j == 0)
                    counts[i][j] = 0;
                else {
                    int count = 0;
                    if (i >= coins[j-1])
                        count += counts[i - coins[j - 1]][j];
                    count += counts[i][j-1];

                    counts[i][j] = count;
                }
            }
        }

        return counts[n][coins.length];
    }

    public static void main(String[] args) {
        int[] usChange = {1, 5, 10, 25};

        int[] amounts = {0, 1, 5, 7, 30, 823, 2000, 10000};
        for (int i = 0; i < amounts.length; i++) {
            System.out.println("Count for " + amounts[i] + " (memoized):  " +
                    countChange(amounts[i], usChange, true));
            System.out.println("Count for " + amounts[i] + " (bottom-up): " +
                    bottomUpCount(amounts[i], usChange));
        }
    }
}
