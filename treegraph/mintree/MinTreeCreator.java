public class MinTreeCreator {
    public static Node bstFromArray(int[] array) {
        return bstFromArrayRec(array, 0, array.length);
    }

    public static Node bstFromArrayRec(int[] array, int start, int end) {
        System.out.println("Computing subtree from " + start + " until " + end);
        final int l = end - start;
        System.out.println("-> l = " + l);
        if (l <= 0)
            return null;
        else if (l == 1)
            return new Node(array[start], null, null);
        else {
            final int d = (int) (Math.floor(Math.log(l) / Math.log(2))) + 1;
            System.out.println("-> d = " + d);
            final int leftSubtreeSize = Math.min((int) (Math.pow(2, d-1) - 1),
                    (int) (l - Math.pow(2, d-2)));
            System.out.println("-> Left subtree has " + leftSubtreeSize + " elements");
            return new Node(array[leftSubtreeSize],
                    bstFromArrayRec(array, start, start + leftSubtreeSize),
                    bstFromArrayRec(array, start + leftSubtreeSize + 1, end));
        }
    }

    public static void main(String[] args) {
        int a0[] = new int[0];
        int a1[] = {1};
        int a2[] = {1, 2};
        int a3[] = {1,2,3,4,5,6,7,8,9,10,11};
        int a4[] = {1,2,3,4,5,6,7,8,9,10,11,12};

        System.out.println("Testing a0");
        Node n = bstFromArray(a0);
        System.out.println("n = " + n);

        System.out.println("\nTesting a1");
        bstFromArray(a1);

        System.out.println("\nTesting a2");
        bstFromArray(a2);

        System.out.println("\nTesting a3");
        bstFromArray(a3);

        System.out.println("\nTesting a4");
        bstFromArray(a4);
    }
}

