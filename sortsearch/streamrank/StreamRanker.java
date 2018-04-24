public class StreamRanker {

    private Node bst = null;

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private int value;
        private int leftCount;
        private int rightCount;

        public Node(Node parent, int value) {
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.value = value;
            this.leftCount = 1; // tracks smaller or equal
            this.rightCount = 0;
        }
        
        public void insert(int x) {
            if (x == value)
                leftCount++;
            else if (x <= value) {
                leftCount++;
                if (left == null)
                    left = new Node(this, x);
                else
                    left.insert(x);
            }
            else {
                rightCount++;
                if (right == null)
                    right = new Node(this, x);
                else
                    right.insert(x);
            }
        }

        public int rank() {

            // Go up until you enter a node through its right descendant
            // branch (or you arrive at the root).
            int result = leftCount;
            Node n = this;

            // Keep moving n up as long as we can go up a left branch into a
            // parent.
            while (n.parent != null && n.parent.left == n)
                n = n.parent;

            // Now n is either the root or it has a parent accessible via the
            // right branch.
            while (n.parent != null && n.parent.right == n) {
                n = n.parent;
                result += n.leftCount;
            }

            return result;
        }

        /**
         * Returns the node with the largest value that is smaller or equal to
         * x, or null if no such node exists.
         */
        public Node find(int x) {

            if (x == value)
                return this;
            else if (x < value)
                if (left == null)
                    return null; // No value smaller or equal x exists
                else
                    return left.find(x);
            else // x > value
                if (right == null)
                    return this; // Nothing more to the right, this is the largest
                else if (right.value > x)
                    return this; // Anything on right side is larger
                else
                    return right.find(x);
        }

    }

    public void track(int x) {
        if (bst == null)
            bst = new Node(null, x);
        else
            bst.insert(x);
    }

    public int rank(int x) {
        if (bst == null)
            return 0;
        else {
            Node n = bst.find(x);
            if (n == null)
                return 0;
            else
                return n.rank();
        }
    }

    
    public static void main(String[] args) {

        StreamRanker sr = new StreamRanker();
        int[] sequence = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for (int i = 0; i < sequence.length; i++)
            sr.track(sequence[i]);

        int[] numbers = {1, 3, 4, 5, 7, 9, 10, 13, 14};
        for (int i: numbers)
            System.out.println("rank(" + i + ") = " + sr.rank(i));
    }
}
