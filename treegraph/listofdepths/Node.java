public class Node
{
    final int value;
    final Node left;
    final Node right;

    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
