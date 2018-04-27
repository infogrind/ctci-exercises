public class Node {
    public final int data;
    public final Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        if (this.next != null)
            return "" + this.data + ", " + this.next.toString();
        else
            return "" + this.data;
    }

}
