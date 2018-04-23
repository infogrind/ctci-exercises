public class RotBinSearch {

    public static int rotBinSearch(int[] a, int x) {
        return rotBinSearch(a, x, 0, a.length);
    }

    private static int rotBinSearch(int[] a, int x, int start, int end) {

        if (start >= end)
            return -1; // nothing found
        else {
            int mid = (start + end) / 2;
            int startval = a[start];
            int endval = a[end - 1];
            int midval = a[mid];

            if (x == midval)
                return mid;
            else if (x < midval)
                if (startval <= x)
                    return rotBinSearch(a, x, start, mid);
                else if (startval > midval) // wrap occurred to the left
                    return rotBinSearch(a, x, start, mid);
                else // startval > x, startval <= midval -> last chance right
                    return rotBinSearch(a, x, mid + 1, end);
            else
                if (endval >= x)
                    return rotBinSearch(a, x, mid + 1, end);
                else if (endval < midval)
                    return rotBinSearch(a, x, mid + 1, end);
                else
                    return rotBinSearch(a, x, start, mid);
        }
    }

    public static void main(String[] args) {

        int[][] a = {{15, 16, 19, 20, 25, 1, 3, 4, 5, 6, 10, 14},
            {4, 5, 6, 10, 14, 15, 16, 19, 20, 25, 1, 3},
            {20, 25, 1, 3, 4, 5, 6, 10, 14, 15, 16, 19}};

        for (int i = 0; i < a.length; i++)
            System.out.println("Searching in array " + i + ": " +
                    rotBinSearch(a[i], 5));

        System.out.println("Searching missing value: " +
                rotBinSearch(a[0], 7));
    }
}


