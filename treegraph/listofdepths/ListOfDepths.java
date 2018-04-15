import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class ListOfDepths {
    public static List<List<Node>> listOfDepths(Node root) {
        Queue<List<Node>> q = new LinkedList<List<Node>>();
        List<List<Node>> retVal = new LinkedList<List<Node>>();

        List<Node> l1 = new LinkedList<Node>();
        l1.add(root);
        q.add(l1);

        while (!q.isEmpty()) {
            List<Node> nodesAtDepth = q.remove();
            retVal.add(nodesAtDepth);
            
            List<Node> lowerNodes = new LinkedList<Node>();
            for (Node n : nodesAtDepth) {
                if (n.left != null)
                    lowerNodes.add(n.left);
                if (n.right != null)
                    lowerNodes.add(n.right);
            }
            if (!lowerNodes.isEmpty())
                q.add(lowerNodes);
        }
        return retVal;
    }

    public static void main(String[] args) {
        final Node root = new Node(1,
                new Node(2,
                    new Node(4, new Node(7, null, null), new Node(8, null, null)),
                    null),
                new Node(3,
                    new Node(5, null,
                        new Node(9, new Node(11, new Node(12, null, null), new Node(13, null, null)), null)),
                    new Node(6, new Node(10, null, null), null)
                    )
                );

        final List<List<Node>> depths = listOfDepths(root);

        int i = 0;
        for (List<Node> l: depths) {
            System.out.print("Level " + i + ": ");
            for (Node n: l) {
                System.out.print(n + ",");
            }
            System.out.println();
            i++;
        }
    }
}
