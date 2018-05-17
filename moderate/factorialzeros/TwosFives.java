public class TwosFives {
    final int twos;
    final int fives;
    public TwosFives(int twos, int fives) {
        this.twos = twos;
        this.fives = fives;
    }

    public TwosFives add(TwosFives that) {
        return new TwosFives(twos + that.twos, fives + that.fives);
    }

    public int min() {
        return Math.min(twos, fives);
    }
}
