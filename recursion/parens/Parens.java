import java.util.List;
import java.util.LinkedList;

public class Parens {

    public static List<String> parensCombinations(int n) {
        return parensCombinations(n, 0, 0, "");
    }

    private static List<String> parensCombinations(
            int n, int level, int closed, String prefix)
    {
        List<String> result = new LinkedList<String>();
        if (level == 0 && closed == n)
            result.add(prefix);
        else {
            if (level + closed < n)
                result.addAll(parensCombinations(n, level + 1, closed, prefix + "("));
            if (level > 0)
                result.addAll(parensCombinations(n, level - 1, closed + 1, prefix + ")"));
        }

        return result;
    }

    public static void main(String[] args) {

        for (int i = 2; i <= 4; i++)
            System.out.println("Combinations of " + i + ": " + parensCombinations(i));
    }
}
