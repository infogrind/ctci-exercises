import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class SafeLock {

    private static final boolean DEBUG = true;

    private Lock lock = new ReentrantLock();
    private static LockGraph lockGraph = new LockGraph();

    public synchronized void lock() {
        
        final String threadName = Thread.currentThread().getName();
        final String holder = lockGraph.getLockOwner(this);

        debug("Asking for lock " + lock);
        debug("Current owner of the lock: " + holder);

        synchronized(SafeLock.class) {
            if (lockGraph.wouldCauseCycle(this, threadName))
                throw new DeadlockException("Thread " + threadName + " waiting for thread " +
                    holder + " would cause deadlock.");
            else {
                debug("this would not cause a deadlock");
                lockGraph.addLockWaiter(this, threadName);
                debug("Granting lock " + this + " to " + threadName);
            }
        }
        lock.lock();
        debug("-> Lock " + lock + " acquired");
    }

    public synchronized void unlock() {

        final String threadName = Thread.currentThread().getName();
        final String holder = lockGraph.getLockOwner(this);
        if (!threadName.equals(holder))
            throw new NotHolderException("Thread " + threadName + " is not holder of thread.");
        else {
            lockGraph.notifyUnlock(this);
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return lock.toString();
    }

    private static void debug(String msg) {
        if (DEBUG) {
            final String thread = Thread.currentThread().getName();
            System.out.println(thread + ": " + msg);
        }
    }

}

