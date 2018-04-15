import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class BSTSequences {

    static void printSequences(Set<Node> trees, List<Node> prefix) {
        if (trees.isEmpty()) {
            for (Node n : prefix)
                System.out.print(n.label + ", ");
            System.out.println();
        }
        else {
            // Add each tree to prefix and traverse the remainder
            for (Node n: trees) {
                List<Node> newPrefix = new LinkedList<Node>(prefix);
                newPrefix.add(n);

                Set<Node> newTrees = new HashSet<Node>(trees);
                newTrees.remove(n);
                if (n.left != null)
                    newTrees.add(n.left);
                if (n.right != null)
                    newTrees.add(n.right);

                printSequences(newTrees, newPrefix);
            }
        }
    }

    public static void main(String[] args) {
        Node tree = new Node("A", new Node("B", new Node("D", null, null), null),
                new Node("C", new Node("E", new Node("F", null, null), new Node("G", null, null)),
                    null));

        Set<Node> initialTree = new HashSet<Node>();
        initialTree.add(tree);

        printSequences(initialTree, new LinkedList<Node>());
    }
}
