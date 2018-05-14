public class Intersect {

    public static Point intersection(Point s1, Point e1, Point s2, Point e2) {

        // TODO: Treat special case when a line is vertical (infinite slope)

        double m1 = (e1.y - s1.y) / (e1.x - s1.x);
        double b1 = s1.y - m1 * s1.x;
        double m2 = (e2.y - s2.y) / (e2.x - s2.x);
        double b2 = s2.y - m2 * s2.x;

        if (m1 == m2)
            return null; // parallel lines or on top of each other

        boolean s2aboveLine1 = m1 * s2.x + b1 <= s2.y;
        boolean e2aboveLine1 = m1 * e2.x + b1 <= e2.y;
        boolean segment2divided = s2aboveLine1 ^ e2aboveLine1;

        boolean s1aboveLine2 = m2 * s1.x + b2 <= s1.y;
        boolean e1aboveLine2 = m2 * e1.x + b2 <= e1.y;
        boolean segment1divided = s1aboveLine2 ^ e1aboveLine2;

        if (!(segment1divided && segment2divided))
            // No intersection
            return null;

        System.out.println("Segment 1: " + m1 + "x + " + b1);
        System.out.println("Segment 2: " + m2 + "x + " + b2);

        double xInt = (b2 - b1) / (m1 - m2);
        double yInt = m1 * xInt + b1;
        return new Point(xInt, yInt);
    }

    public static void main(String[] args) {

        Point s1 = new Point(0.0,0.0);
        Point e1 = new Point(1.0,1.0);

        Point s2 = new Point(0.0,1.0);
        Point e2 = new Point(0.5,0.0);

        Point is = intersection(s1, e1, s2, e2);
        System.out.println("Intersection: " + is);
    }
}
