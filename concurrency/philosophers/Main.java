public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < Philosopher.NB_PHILOSOPHERS; i++) {
            Thread p = new Philosopher(i);
            p.start();
        }
    }
}
