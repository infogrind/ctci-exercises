import java.util.Arrays;

public class URLify {

    public static void urlify(char[] s, int length) {
        int nspaces = countSpaces(s, length);
        if (nspaces == 0)
            return;

        int bufsize = 2 * nspaces;
        int r = length - 1;
        int w = r + bufsize;
        while (w != r) {
            System.out.println("r/w = " + r + " / " + w + ", diff = " + (w - r));

            char c = s[r];
            if (c != ' ') {
                s[w] = s[r];
                r--;
                w--;
            }
            else {
                s[w] = '0';
                s[w-1] = '2';
                s[w-2] = '%';
                r--;
                w = w - 3;
            }
        }
    }

    private static int countSpaces(char[] s, int length) {

        int count = 0;
        for (int i = 0; i < length; i++)
            if (s[i] == ' ')
                count++;

        return count;
    }

    public static void main(String[] args) {
        char[] s = "Mr John Smith          ".toCharArray();
        urlify(s, 13);
        System.out.println("URLified string: " + Arrays.toString(s));
    }
}
