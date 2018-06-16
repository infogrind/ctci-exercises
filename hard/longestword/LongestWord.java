import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LongestWord {

    public static String longestWord(List<String> words) {

        Set<String> dict = createDict(words);
        int maxLength = 0;
        String bestWord = null;

        for (String w: words) {
            if (isComposedString(w, dict) && w.length() > maxLength) {
                maxLength = w.length();
                bestWord = w;
            }
        }

        return bestWord;
    }

    private static Set<String> createDict(List<String> strings) {
        Set<String> result = new HashSet<String>();
        for (String s: strings)
            result.add(s);

        return result;
    }

    private static boolean isComposedString(String s, Set<String> dict) {
        Map<Integer, Boolean> memo = new HashMap<Integer, Boolean>();
        return isComposedString(s, dict, memo, 0);
    }

    private static boolean isComposedString(
            String s, Set<String> dict, Map<Integer,Boolean> memo,
            int startIdx)
    {
        if (memo.containsKey(startIdx))
            return memo.get(startIdx);

        for (int i = startIdx + 1; i < s.length(); i++) {
            final String substr = s.substring(startIdx, i);
            if (dict.contains(substr)) {
                boolean validRemainder = isComposedString(s, dict, memo, i);
                if (validRemainder) {
                    memo.put(startIdx, true);
                    return true; // short circuit
                }
            }
        }

        if (dict.contains(s.substring(startIdx)) && startIdx > 0)
            memo.put(startIdx, true);
        else
            memo.put(startIdx, false); // only whole word matches


        return memo.get(startIdx);
    }

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<String>() {{
            add("cat");
            add("banana");
            add("dog");
            add("do");
            add("gw");
            add("nana");
            add("walk");
            add("walker");
            add("dogwalker");
        }};

        List<String> list2 = new ArrayList<String>() {{
            add("table");
            add("bed");
            add("chair");
            add("lamp");
            add("bedlamp");
            add("cloth");
            add("tablecloth");
            add("map");
            add("bedlampcloth");
            add("tablecoverlabel");
        }};

        System.out.println("list1: " + list1);
        System.out.println("longest: " + longestWord(list1));
        System.out.println("list2: " + list2);
        System.out.println("longest: " + longestWord(list2));

    }
}
