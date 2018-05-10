import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class ThreePower {

    public static void threePowers(int n) {
        Map<Integer, Set<Integer[]>> map =
            new HashMap<Integer, Set<Integer[]>>();

        for (int a = 1; a <= n; a++) {
            for (int b = a; b <= n; b++) {
                int power = a*a*a + b*b*b;
                if (!map.containsKey(power))
                    map.put(power, new HashSet<Integer[]>());

                Integer[] ab = new Integer[2];
                ab[0] = a;
                ab[1] = b;

                map.get(power).add(ab);
            }
        }

        for (int power : map.keySet())
            if (map.get(power).size() > 2)
                printResult(power, map.get(power));
    }

    private static void printResult(int power, Set<Integer[]> elements) {

        System.out.print(power);
        for (Integer[] e: elements)
            System.out.print(" = " + e[0] + "^3 + " + e[1] + "^3");
        System.out.println();
    }

    public static void main(String[] args) {
        threePowers(1000);
    }
}
