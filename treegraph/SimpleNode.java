import java.util.List;
import java.util.ArrayList;

class SimpleNode implements Node {
    final String name;
    final List<Node> children;

    public SimpleNode(String name, Node[] children) {
        this.name = name;
        this.children = new ArrayList<Node>();
        for (Node c : children) {
            this.children.add(c);
        }
    }

    public void addChild(Node n) {
        this.children.add(n);
    }

    public List<Node> children() { return children; }
    public String getName() { return name; }
}
