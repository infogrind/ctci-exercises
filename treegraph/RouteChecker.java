import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

public class RouteChecker {
    
    public static boolean routeExists(Node from, Node to) {
        Queue<Node> queue = new LinkedList<Node>();
        Set<Node> seen = new HashSet<Node>();

        queue.add(from);
        seen.add(from);

        while (!queue.isEmpty()) {
            Node n = queue.remove();
            if (n == to)
                return true;

            for (Node c: n.children()) {
                if (!seen.contains(c)) {
                    queue.add(c);
                    seen.add(c);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Node n1 = new SimpleNode("n1", new Node[0]);
        Node n2 = new SimpleNode("n2", new Node[0]);
        Node n3 = new SimpleNode("n3", new Node[0]);
        Node n4 = new SimpleNode("n4", new Node[0]);
        Node n5 = new SimpleNode("n5", new Node[0]);
        Node n6 = new SimpleNode("n6", new Node[0]);

        n1.addChild(n2);
        n1.addChild(n3);
        n3.addChild(n1);
        
        n4.addChild(n5);
        n4.addChild(n6);

        System.out.println("Route from n1 to n5? " + routeExists(n1, n5));

        n2.addChild(n4);

        System.out.println("Route from n1 to n5? " + routeExists(n1, n5));
    }
}
