package ch.hotstuff.mergesort;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.Random;

public class MergesortTest {

    @Test
    public void testRandomInts() {

        // Set up
        Random rand = new Random();
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt();

        // Run
        Mergesort.mergesort(array);

        // Test
        for (int i = 1; i < array.length; i++)
            assertTrue("Element in position " + i + " smaller than previous",
                    array[i] >= array[i-1]);
    }
}
