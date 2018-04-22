import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;

public class StringPermDup {

    public static List<String> generatePerms(String s) {
        Map<Character, Integer> charCount = getCharCount(s);
        return generatePerms(charCount);
    }

    private static List<String> generatePerms(Map<Character, Integer> charCount)
    {
        List<String> result = new LinkedList<String>();
        if (charCount.keySet().isEmpty())
            result.add("");
        else if (charCount.keySet().size() == 1) {
            char c = charCount.keySet().iterator().next();
            int count = charCount.get(c);
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < count; i++)
                buf.append(c);
            result.add(buf.toString());
        }
        else {
            Set<Character> firstChars = new HashSet<Character>(
                    charCount.keySet());

            for (char c: firstChars) {
                if (charCount.get(c) == 1)
                    charCount.remove(c);
                else
                    charCount.put(c, charCount.get(c) - 1);

                List<String> subPerms = generatePerms(charCount);
                for (String perm: subPerms)
                    result.add("" + c + perm);

                if (!charCount.containsKey(c))
                    charCount.put(c, 1);
                else
                    charCount.put(c, charCount.get(c) + 1);
            }
        }

        return result;
    }

    private static Map<Character, Integer> getCharCount(String s) {

        Map<Character, Integer> result = new HashMap<Character, Integer>();
        for (int i = 0; i < s. length(); i++) {
            char c = s.charAt(i);
            if (result.containsKey(c))
                result.put(c, result.get(c) + 1);
            else
                result.put(c, 1);
        }

        return result;
    }

    public static void main(String[] args) {

        final String s = "abac";
        List<String> perms = generatePerms(s);
        System.out.println("Permutations for " + s + ": " + perms);
        System.out.println(perms.size() + " permutations found");
    }
}
