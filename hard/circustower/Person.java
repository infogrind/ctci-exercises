public class Person {
    final int height;
    final int weight;
    public Person(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() { return "(" + height + ", " + weight + ")"; }
}
