import java.util.Map;
import java.util.HashMap;

public class Palindrome {

    public static boolean isPalindromePerm(String s) {
        int nChars = countChars(s);
        boolean oddLength = (nChars % 2 == 1);
        boolean foundOdd = false;
        Map<Character,Integer> freqs = computeFrequencies(s);

        for (char c: freqs.keySet()) {
            int freq = freqs.get(c);
            if (freq % 2 == 1)
                if (oddLength && !foundOdd)
                    foundOdd = true;
                else
                    return false;
        }
        return true;
    }

    private static Map<Character,Integer> computeFrequencies(String s) {

        Map<Character, Integer> freqs = new HashMap<Character,Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                char lower = lower(c);
                if (freqs.containsKey(lower))
                    freqs.put(lower, freqs.get(lower) + 1);
                else
                    freqs.put(lower, 1);
            }
        }
        return freqs;
    }

    private static int countChars(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                count++;
        }

        return count;
    }

    private static char lower(char c) {
        if (c >= 'A' && c <= 'Z')
            return (char) ((int)c + (int) 'a' - (int) 'A');
        else
            return c;
    }

    public static void main(String[] args) {

        String[] strings = {"Tact Coa", "Tact O Coa", "Tact Coat"};
        for (String s: strings)
            System.out.println(s + " -> " + isPalindromePerm(s));
    }
}

