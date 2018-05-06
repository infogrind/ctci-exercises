import java.util.Random;

public class Poison {

    private static int randInt(int n) {
        Random rand = new Random();
        return (int) (n * rand.nextDouble());
    }

    public static void main(String[] args) {

        final int nBottles = 1000;
        boolean[] strips = new boolean[10];
        for (int i = 0; i < strips.length; i++)
            strips[i] = false;

        final int poisoned = randInt(nBottles);

        for (int s = 0; s < strips.length; s++)
            for (int b = 0; b < nBottles; b++)
                if (((b >> s) & 1) == 1 && b == poisoned)
                    strips[s] = true;

        int nPoisoned = 0;
        int setter = 1;
        for (int s = 0; s < strips.length; s++) {
            if (strips[s])
                nPoisoned |= setter;
            setter <<= 1;
        }

        System.out.println("Original number of poisoned bottle: " + poisoned);
        System.out.println("Detected number: " + nPoisoned);
    }
}

