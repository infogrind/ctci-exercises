public class Position {

    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object that) {

        if (that == null || !(that instanceof Position))
            return false;
        else {
            Position thatPos = (Position) that;
            return this.x == thatPos.x && this.y == thatPos.y;
        }
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}
