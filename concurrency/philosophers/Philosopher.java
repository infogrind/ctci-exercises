import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {

    public static final int NB_PHILOSOPHERS = 2;
    private static final int EATING_TIME_MS = 1000;

    private static final boolean DEBUG = true;

    private static SafeLock[] chopsticks = new SafeLock[NB_PHILOSOPHERS];

    static {
        for (int i = 0; i < NB_PHILOSOPHERS; i++)
            chopsticks[i] = new SafeLock();
    }

    private int i;

    public Philosopher(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {

        debug("Trying to get left chopsticks #" + i);
        chopsticks[i].lock();
        debug("Got left chopsticks #" + i);
        try {
            debug("Sleeping a bit");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        debug("Trying to get right chopsticks #" + ((i+1) % NB_PHILOSOPHERS));
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

    private static void debug(String msg) {
        if (DEBUG) {
            final String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + msg);
        }
    }
}
