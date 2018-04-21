import java.util.List;
import java.util.LinkedList;

public class StringPermNoDup {

    public static List<String> permutationsNoDup(String s) {

        List<String> result = new LinkedList<String>();
        
        if (s.length() == 0) {
            result.add("");
            return result;
        }

        char first = s.charAt(0);
        List<String> subperms = permutationsNoDup(s.substring(1));

        for (String subperm: subperms) {
            List<String> allcombs = insertCharCombinations(subperm, first);
            result.addAll(allcombs);
        }

        return result;
    }

    private static List<String> insertCharCombinations(String s, char c) {

        List<String> result = new LinkedList<String>();
        if (s.isEmpty())
            result.add("" + c);
        else
            for (int i = 0; i <= s.length(); i++) {
                if (i == 0)
                    result.add(c + s.substring(i));
                else if (i == s.length())
                    result.add(s + c);
                else
                    result.add(s.substring(0, i) + c + s.substring(i));
            }

        return result;
    }


    public static void main(String[] args) {

        List<String> perms = permutationsNoDup("abcd");
        System.out.println("Permutations: " + perms);
    }
}
