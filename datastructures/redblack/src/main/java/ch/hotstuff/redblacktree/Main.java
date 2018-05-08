package ch.hotstuff.redblacktree;

public class Main {

    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();
        for (int i = 1; i < 100; i++) {
            System.out.println("Inserting " + i);
            rbt.insert(i);
            rbt.testIntegrity();
        }
    }
}
