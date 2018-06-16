import java.util.Arrays;

public class Masseuse {

    public static int longestSlot(int[] slots) {

        if (slots.length == 0)
            return 0;
        else if (slots.length == 1)
            return slots[0];
        else if (slots.length == 2)
            return Math.max(slots[0], slots[1]);

        int bestTwoBack = slots[0];
        int bestOneBack = slots[1];

        for (int i = 2; i < slots.length; i++) {
            int with = slots[i] + bestTwoBack;
            int without = bestOneBack;

            int best = Math.max(with, without);
            bestTwoBack = bestOneBack;
            bestOneBack = best;
        }
        
        return bestOneBack;
    }

    public static void main(String[] args) {

        int[][] slotset = {{30, 15, 60, 75, 45, 15, 15, 45},
            {15, 45, 45, 60, 15, 15, 60, 30}};

        for (int[] slots: slotset) {
            System.out.println("slots: " + Arrays.toString(slots));
            System.out.println("optimum: " + longestSlot(slots));
        }
    }
}
