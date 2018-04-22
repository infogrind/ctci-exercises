public class Pos {
    final int row;
    final int col;
    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
