public class Box {
    final int width;
    final int depth;
    final int height;

    public Box(int width, int depth, int height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    @Override
    public String toString() {
        return "(" + width + "," + depth + "," + height + ")";
    }
}
