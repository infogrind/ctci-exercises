public class RedBlackTree {

    private enum Color { RED, BLACK }

    private static class Node {
        int value;
        Color color;
        Node left;
        Node right;

        public Node(int value, Color color, Node left, Node right) {
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node(" + value + ", " + color + ", " + left + ", " +
                right + ")";
        }

        public boolean contains(int x) {
            if (value == x)
                return true;
            else if (x < value)
                if (left == null)
                    return false;
                else
                    return left.contains(x);
            else // x > value
                if (right == null)
                    return false;
                else
                    return right.contains(x);
        }

        public Node insert(int x) {
            if (value == x)
                return this; // nothing to insert
            else if (value < x)
                if (left == null) {
                    left = new Node(value, Color.RED, null, null);
                    return this;
                }
                else {
                    // Recursive insert
                    left = left.insert(x);
                    return rebalanceLeft();
                }
            else { // value > x
                if (right == null) {
                    right = new Node(value, Color.RED, null, null);
                    return this;
                }
                else {
                    right = right.insert(x);
                    return rebalanceRight();
                }
            }
        }

        /**
         * This function is called if a new node has been recursively
         * inserted into the left child. In this scenario, the present node
         * plays the role of grandfather (G). The returned value is passed
         * recursively to the parent, which will set it as new child - this
         * is to enable rotations. Note that when this function is called, we
         * automatically know that the left child is not null.
         */
        private Node rebalanceLeft() {
            /* If left child is black, there is no red violation to fix so we
             * can just return.
             */
            if (left.color == Color.BLACK)
                return this;
            else if ((left.left == null || left.left.color == Color.BLACK) &&
                    (left.right == null || left.right.color == Color.BLACK))
                /* No red grandchildren on left side -> all good. */
                return this;
            else if (right != null && right.color == Color.RED) {
                /* Case: N, P, and U are red. This is the case that is handled
                 * the same, irrespective of whether N is a left or right child.
                 */
                redSwitchUPG();
                return this;
            }
            /* Here we know:
             * - P is red
             * - N is red (but it could be left or right child of P)
             * - U is null or black
             */
            else if (left.left != null && left.left.color == Color.RED)
                /* N is left child */
                return leftLeftRotation();
            else if (left.right != null && left.right.color == Color.BLACK)
                /* N is right child */
                return leftRightRotation();
            
            /* No need for an else clause - we have exhausted all possibilities.
             * Let's put a safety check for good measure.
             */
            else
                throw new RuntimeException("Uncovered case found");
        }

        /**
         * This method performs the required rotation if N and P are left
         * children and U is black (or null).
         */
        private Node leftLeftRotation() {
            final Node G = this;
            final Node P = G.left;
            final Node N = P.left;
            final Node a = P.right;

            if (P.color != Color.RED || N.color != Color.RED)
                throw new RuntimeException(
                        "Unexpected node colors for left-left rotation");

            P.right = G;
            G.left = a;
            return P;
        }

        /**
         * This method performs the required rotation if P is a left child and N
         * a right child and U is black (or null).
         */
        private Node leftRightRotation() {
            final Node G = this;
            final Node P = G.left;
            final Node N = P.right;
            final Node x = N.left;
            final Node y = N.right;

            if (P.color != Color.RED || N.color != Color.RED)
                throw new RuntimeException(
                        "Unexpected colors for left-right rotation");

            N.left = P;
            N.right = G;
            P.right = x;
            G.left = y;
            return N;
        }

        /**
         * This method performs the required operation when all of N, U, and P
         * are left. It is symmetric no matter whether P (and N) are left or
         * right descendants of G. Therefore it is factored out here such that
         * both rebalanceLeft and rebalanceRight can use it.
         */
        private void redSwitchUPG() {
            left.color = Color.BLACK;
            right.color = Color.BLACK;
            this.color = Color.RED;
        }


        /** This function is analogous to rebalanceRight, just that
         * everything is mirrored. When this function is called, we know that
         * the right child is not null.
         */
        private Node rebalanceRight() {
            /* If right child is black, there is no red violation here and we
             * can just return.
             */
            if (right.color == Color.BLACK)
                return this;
            else if ((right.left == null || right.left.color == Color.BLACK) &&
                    (right.right == null || right.right.color == Color.BLACK))
                /* No red grandchildren, so nothing to do here either. */
                return this;
            else if (left != null && left.color == Color.RED) {
                /* Case: N, P and U are red. We proceed analog to left case. */
                redSwitchUPG();
                return this;
            }
            /* Here we know:
             * - P is red (and a right child of G)
             * - N is red (but it could be left or right child of P)
             * - U is null or black
             */
            else if (right.right != null && right.right.color == Color.RED)
                /* N is right child */
                return rightRightRotation();
            else if (right.left != null && right.left.color == Color.RED)
                /* N is left child */
                return rightLeftRotation();
            else
                /* At this point we excluded the case that all grandchildren are
                 * black, and that either grandchild is red, so this is the
                 * proverbial "should not happen" case.
                 */
                throw new RuntimeException("Uncovered case found");
        }


        /**
         * This method performs the required rotation if N and P are both right
         * children and U is black (or null).
         */
        private Node rightRightRotation() {
            final Node G = this;
            final Node P = G.right;
            final Node N = P.right;
            final Node a = P.left;

            if (P.color != Color.RED || N.color != Color.RED)
                throw new RuntimeException("Unexpected colors");

            P.left = G;
            G.right = a;
            return P;
        }


        /**
         * This method performs the required rotation if P is a right child and
         * N a left child and U is black (or null).
         */
        private Node rightLeftRotation() {
            final Node G = this;
            final Node P = G.right;
            final Node N = P.left;
            final Node x = N.left;
            final Node y = N.right;

            if (P.color != Color.RED || N.color != Color.RED)
                throw new RuntimeException("Unexpected colors");

            N.left = G;
            N.right = P;
            G.right = x;
            P.left = y;
            return N;
        }

    }

    Node root = null;

    public boolean contains(int x) {
        if (root == null)
            return false;
        else
            return root.contains(x);
    }

    public void insert(int x) {
        if (root == null)
            root = new Node(x, Color.RED, null, null);
        else {
            root = root.insert(x);
            root.color = Color.BLACK;
        }
    }
}
