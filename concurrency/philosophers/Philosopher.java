import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {

    public static final int NB_PHILOSOPHERS = 7;
    private static final int EATING_TIME_MS = 1000;

    private static Lock[] chopsticks = new ReentrantLock[NB_PHILOSOPHERS];

    static {
        for (int i = 0; i < NB_PHILOSOPHERS; i++)
            chopsticks[i] = new ReentrantLock();
    }

    private int i;

    public Philosopher(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {

        chopsticks[i].lock();
        chopsticks[(i+1) % NB_PHILOSOPHERS].lock();
        System.out.println("Philosopher " + i + " eating");

        try {
            Thread.sleep(EATING_TIME_MS);
        }
        catch (InterruptedException e) {
            System.out.println("Philosopher " + i + " interrupted!");
        }
        System.out.println("Philosopher " + i + " done.");
        chopsticks[i].unlock();
        chopsticks[(i+1) % NB_PHILOSOPHERS].unlock();
    }
}
