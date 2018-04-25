import java.util.Arrays;

public class StreamRanker {

    private Node bst = null;

    private class Node {
        private Node left;
        private Node right;
        private int value;
        private int leqCount;

        public Node(int value) {
            this.left = null;
            this.right = null;
            this.value = value;
            this.leqCount = 1; // tracks smaller or equal
        }
        
        public void insert(int x) {
            if (x == value)
                leqCount++;
            else if (x < value) {
                leqCount++;
                if (left == null)
                    left = new Node(x);
                else
                    left.insert(x);
            }
            else { // x > value
                if (right == null)
                    right = new Node(x);
                else
                    right.insert(x);
            }
        }

        /** 
         * Returns the number of nodes with value less or equal to x in the
         * subtree whose root is this node.
         */
        public int rank(int x) {
            if (x == value)
                return leqCount;
            else if (x < value)
                if (left == null)
                    return 0; // no <= node in the tree
                else
                    return left.rank(x);
            else // x > value
                if (right == null)
                    return leqCount;
                else
                    return leqCount + right.rank(x);
        }
    }

    public void track(int x) {
        if (bst == null)
            bst = new Node(x);
        else
            bst.insert(x);
    }

    public int rank(int x) {
        if (bst == null)
            return 0;
        else
            return bst.rank(x);
    }

    
    public static void main(String[] args) {

        StreamRanker sr = new StreamRanker();
        int[] sequence = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int i = 0; i < sequence.length; i++)
            sr.track(sequence[i]);

        int[] numbers = {1, 3, 4, 5, 7, 9, 10, 13, 14};
        System.out.println("Sequence checked: " + Arrays.toString(sequence));
        for (int i: numbers)
            System.out.println("rank(" + i + ") = " + sr.rank(i));
    }
}
