public class RecMultiply {

    public static int recMultiply(int a, int b) {
        return recMultiply(0, a, b);
    }

    public static int recMultiply(int acc, int a, int b)
    {
        if (b == 0)
            return acc;
        else if ((b & 1) == 1)
            return recMultiply(acc + a, a << 1, b >> 1);
        else
            return recMultiply(acc, a << 1, b >> 1);
    }

    public static void main(String[] args) {

        final int a = 772;
        final int b = 2312;
        System.out.println("Multiplying " + a + " and " + b + ":");
        System.out.println("Traditional: " + (a * b));
        System.out.println("Recursive: " + recMultiply(a, b));
    }
}

