import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Collections;

public class BoxHeight {

    public static int maxHeight(List<Box> boxes) {

        ArrayList<Box> sortedBoxes = new ArrayList<Box>(boxes);
        Collections.sort(sortedBoxes, new Comparator<Box>() {
            @Override
            public int compare(Box b1, Box b2) {
                if (b1.width > b2.width)
                    return -1;
                else if (b1.width == b2.width)
                    return 0;
                else
                    return 1;
            }
        });

        return maxHeight(sortedBoxes, null, 0);
    }

    private static int maxHeight(ArrayList<Box> boxes, Box constraint, int nextCandidate) {

        if (nextCandidate == boxes.size())
            return 0;
        else {
            Box curBox = boxes.get(nextCandidate);

            // Advance until either end or first suitable box
            while (invalidSequence(constraint, curBox)) {

                nextCandidate++;
                if (nextCandidate == boxes.size())
                    return 0;

                curBox = boxes.get(nextCandidate);
            }

            int h1 = curBox.height + maxHeight(boxes, curBox, nextCandidate + 1);
            int h2 = maxHeight(boxes, constraint, nextCandidate + 1);

            return Math.max(h1, h2);
        }
    }

    private static boolean invalidSequence(Box constraint, Box next) {

        if (constraint == null)
            return false; // everything is possible if no previous box
        else
            return  next.width >= constraint.width || next.height >= constraint.height
                || next.depth >= constraint.depth;
    }

    public static void main(String[] args) {

        List<Box> boxes = new LinkedList<Box>();
        boxes.add(new Box(1,1,1));
        boxes.add(new Box(10,5,1));
        boxes.add(new Box(4,6,3));
        boxes.add(new Box(5,7,4));
        boxes.add(new Box(1,1,9));
        boxes.add(new Box(2,15,6));
        boxes.add(new Box(1,12,4));

        System.out.println("Max height: " + maxHeight(boxes));
    }
}

