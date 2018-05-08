package ch.hotstuff.redblacktree;

import static org.junit.Assert.fail;

import org.junit.Test;

public class RedBlackTest {

	@Test
	public void testAutoIntegrity() {
		RedBlackTree rbt = new RedBlackTree();
        for (int i = 1; i < 100; i++) {
            rbt.insert(i);
            try {
            		rbt.testIntegrity();
            }
            catch (RuntimeException e) {
            		fail("Integrity check failed for i = " + i + ", please check");
            }
        }
	}

}
