import java.util.List;

interface Node {
    public List<Node> children();
    public String getName();
    public void addChild(Node n);
}
