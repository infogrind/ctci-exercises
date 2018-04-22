import java.util.Map;
import java.util.HashMap;

public class BoolEval {

    public static int countEval(String expr, boolean result) {
        return countEval(expr, result, new HashMap<String, Integer>(),
                new HashMap<String, Integer>());
    }

    private static int countEval(String expr, boolean result,
            Map<String, Integer> trueMemo, Map<String, Integer> falseMemo)
    {

        if (expr.equals("0"))
            return (result == false) ? 1 : 0;
        else if (expr.equals("1"))
            return (result == true) ? 1 : 0;

        if (expr.length() <= 1)
            throw new RuntimeException("Unexpected expression: " + expr);

        if (result == true && trueMemo.containsKey(expr))
            return trueMemo.get(expr);
        else if (result == false && falseMemo.containsKey(expr))
            return falseMemo.get(expr);

        int count = 0;

        for (int i = 1; i < expr.length(); i += 2) {
            char op = expr.charAt(i);
            String left = expr.substring(0, i);
            String right = expr.substring(i + 1);

            if (op == '&' && result == true)
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
            else if (op == '&' && result == false) {
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
            }
            else if (op == '|' && result == true) {
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
            }
            else if (op == '|' && result == false) {
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
            }
            else if (op == '^' && result == true) {
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
            }
            else if (op == '^' && result == false) {
                count += countEval(left, true, trueMemo, falseMemo) * countEval(right, true, trueMemo, falseMemo);
                count += countEval(left, false, trueMemo, falseMemo) * countEval(right, false, trueMemo, falseMemo);
            }
            else
                throw new RuntimeException("Invalid expression: " + expr);
        }

        if (result == true)
            trueMemo.put(expr, count);
        else
            falseMemo.put(expr, count);

        return count;
    }

    public static void main(String[] args) {

        String[] exprs = {"1^0|0|1", "0&0&0&1^1|0", "0&0&0&1^1|0&0&0&1^1|0", "0&0&0&1^1|0&0&0&1^1|0&0&0&1^1|0&0&0&1^1|0"};
        boolean[] results = {false, true, true, true};

        for (int i = 0; i < exprs.length; i++)
            System.out.println("countEval(" + exprs[i] + ", " + results[i] +
                    ") -> " + countEval(exprs[i], results[i]));
    }
}
