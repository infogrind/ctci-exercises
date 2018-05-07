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

        private Node rebalanceLeft() {
            // TODO: complete
            throw new Error("not implemented");
        }

        private Node rebalanceRight() {
            // TODO: complete
            throw new Error("not implemented");
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
