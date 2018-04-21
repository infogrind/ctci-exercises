import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class RodCutting {

    public static LinkedList<Integer> optimalRodCutting(int length, int[] prices) {

        if (length > prices.length)
            throw new IllegalArgumentException("Not enough prices given");

        Map<Integer, LinkedList<Integer>> memo =
            new HashMap<Integer,LinkedList<Integer>>();
        return optimalRodCutting(length, prices, memo);
    }

    private static int cuttingPrice(LinkedList<Integer> segments, int[] prices) {

        int result = 0;
        for (Integer i: segments)
            result += prices[i-1];

        return result;
    }

    private static LinkedList<Integer> optimalRodCutting(
            int length, int[] prices, Map<Integer,LinkedList<Integer>> memo)
    {

        if (length == 0)
            return new LinkedList<Integer>();

        int maxPrice = 0;
        int bestLength = 0;
        LinkedList<Integer> bestCutting = null;

        for (int i = 1; i <= length; i++) {

            if (!memo.containsKey(length - i))
                memo.put(length - i, optimalRodCutting(length - i, prices, memo));

            LinkedList<Integer> optimalCuttingRest = memo.get(length - i);

            int curTotalPrice = prices[i - 1] + cuttingPrice(optimalCuttingRest, prices);
            if (curTotalPrice > maxPrice) {
                maxPrice = curTotalPrice;
                bestLength = i;
                bestCutting = optimalCuttingRest;
            }
        }

        LinkedList<Integer> result = (LinkedList<Integer>) bestCutting.clone();
        result.add(bestLength);

        return result;
    }

    public static void main(String[] args) {

        /* Example from:
         * https://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
         */
        int[] prices = {1,5,8,9,10,17,17,20};

        LinkedList<Integer> bestCut = optimalRodCutting(8, prices);
        int maxValue = cuttingPrice(bestCut, prices);

        System.out.println("Best cutting: " + bestCut);
        System.out.println("Value: " + maxValue);
    }

}

