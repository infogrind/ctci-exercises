import java.util.Random;

public class Simulation {

    private static final int NB_RUNS = (1 << 24);

    private static Random rand = new Random();

    private static BoysGirls simulateFamily() {
        int nBoys = 0;
        while (true) {
            if (rand.nextDouble() >= 0.5)
                return new BoysGirls(nBoys, 1);
            else
                nBoys++;
        }
    }

    public static void main(String[] args) {
        int boys = 0;
        int girls = 0;

        for (int i = 0; i < NB_RUNS; i++) {
            BoysGirls result = simulateFamily();
            boys += result.boys;
            girls += result.girls;
        }

        double ratio = ((double) boys) / ((double) girls);

        System.out.println("Result after " + NB_RUNS + " runs:");
        System.out.println(boys + "\t boys");
        System.out.println(girls + "\t girls");
        System.out.println("Ratio: " + ratio + " boys per girls");
    }
}
