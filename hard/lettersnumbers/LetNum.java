public class LetNum {

    private enum Type { L, N };

    public static Pair longestLNSequence(String s) {
        char[] a = s.toCharArray();
        int nLetters = countLetters(a);
        int nNumbers = countNumbers(a);

        if (nLetters == nNumbers)
            return new Pair(0, a.length - 1);
        else if (nLetters > nNumbers)
            return longestLNSequence(a, nNumbers, nLetters, Type.L);
        else
            return longestLNSequence(a, nLetters, nNumbers, Type.N);
    }

    private static Pair longestLNSequence(
            char[] a, int nMin, int nMaj, Type majType) {

        int l = 0;
        int r = a.length - 1;

        while (l < r && nMin != nMaj) {
            if (type(a[l]) == majType) {
                l++;
                nMaj--;
            } else if (type(a[r]) == majType) {
                r--;
                nMaj--;
            }
            else {
                /* Move both until we find majType again, then reset the other
                 */
                int oldL = l;
                int oldR = r;
                while (l < r && type(a[l]) != majType && type(a[r]) != majType) {
                    l++;
                    r--;
                    nMin--;
                }
                if (l >= r)
                    /* This can not really occur, as it would mean that there
                     * are only minority type character in the center. In this
                     * case we would have already found a balanced case earlier.
                     */
                    throw new RuntimeException("Impossible case detected - probably a bug!");
                else if (type(a[l]) == majType)
                    r = oldR;
                else
                    l = oldL;
            }
        }

        if (l == r)
            return new Pair(0,0);
        else
            return new Pair(l, r);
    }

    private static Type type(char c) {
        if (c >= '0' && c <= '9')
            return Type.N;
        else
            return Type.L;
    }

    private static int countLetters(char[] a) {
        int result = 0;
        for (char c: a)
            if (type(c) == Type.L) result++;

        return result;
    }

    private static int countNumbers(char[] a) {
        int result = 0;
        for (char c: a)
            if (type(c) == Type.N) result++;
        
        return result;
    }

    public static void main(String[] args) {
        String[] strings = {"Hello 933", "Aserfsdf", "1234348234",
            "f9283yr83awa8fh", "aa000000aaa", "1234asdf", "1a2b3c4d5e", "a", "1", ""};
        for (String s: strings)
            processString(s);
    }

    private static void processString(String s) {
        Pair p = longestLNSequence(s);
        if (p.a == 0 && p.b == 0)
            System.out.println("()" + s);
        else if (p.a == 0 && p.b == s.length() - 1)
            System.out.println("(" + s + ")");
        else if (p.a == 0)
            System.out.println("(" + s.substring(0, p.b + 1) + ")" + s.substring(p.b + 1));
        else if (p.b == s.length() - 1)
            System.out.println(s.substring(0, p.a) + "(" + s.substring(p.a) + ")");
        else
            System.out.println(s.substring(0, p.a) + "(" + s.substring(p.a, p.b + 1) + ")" +
                    s.substring(p.b + 1));
    }

}
