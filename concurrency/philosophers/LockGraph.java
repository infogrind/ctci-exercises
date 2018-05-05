import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class LockGraph {

    private static final boolean DEBUG = true;

    private Map<SafeLock, LinkedList<String>> lockDeps = new HashMap<SafeLock,LinkedList<String>>();
    private Map<String, Map<String,Integer>> threadDeps = new HashMap<String, Map<String,Integer>>();
    
    /**
     * Returns the name of the thread that currently owns lock l, or
     * <code>null</code> if that lock has no owner.
     */
    public synchronized String getLockOwner(SafeLock l) {

        if (!lockDeps.containsKey(l))
            return null;
        else
            return lockDeps.get(l).getFirst();
    }

    /**
     * Adds the specified thread as waiting (or owning) for lock l. If the lock
     * has currently no owner, the given thread will be logged as owner.
     */
    public synchronized void addLockWaiter(SafeLock l, String thread) {

        debug("Adding thread " + thread + " as waiter for lock " + l);

        if (!lockDeps.containsKey(l)) {
            debug("No threads are currently owning this lock");
            // No threads currently owning or waiting for lock l
            LinkedList<String> threads = new LinkedList<String>();
            threads.add(thread);
            lockDeps.put(l, threads);
        }
        else {
            LinkedList<String> deps = lockDeps.get(l);
            debug("This lock has already owning/waiting threads: " + deps);
            if (!deps.isEmpty()) {
                String threadBefore = deps.getLast();
                recordThreadDependency(thread, threadBefore);
            }
            deps.addLast(thread);
        }
    }

    /**
     * Indicates that the given lock has been unlocked. As a consequence, the
     * waiting queue for that lock will be shifted by one (or emptied). Also,
     * the dependency count of the successor thread needs to be adjusted.
     */
    public synchronized void notifyUnlock(SafeLock l) {

       if (!lockDeps.containsKey(l))
          throw new IllegalArgumentException("Lock " + l + " is not locked.");
       else {
           LinkedList<String> deps = lockDeps.get(l);
           String prevOwner = deps.removeFirst();
           if (!deps.isEmpty()) {
               decreaseDependencyCount(deps.getFirst(), prevOwner);
           }
       }

    }

    /**
     * Checks if the addition of a lock wait of the given thread to the given
     * lock would create a cycle.
     */
    public synchronized boolean wouldCauseCycle(SafeLock l, String thread) {

        debug("Checking if thread " + thread + " waiting on " + l + " would cause a cycle");

        if (!lockDeps.containsKey(l)) {
            // This lock is available, so no cycle can be created.
            debug("Lock is not owned, so no cycle is possible");
            return false;
        }
        else {
            debug("Lock has dependencies: " + lockDeps.get(l));
            Queue<String> queue = new LinkedList<String>();
            queue.addAll(lockDeps.get(l));
            while (!queue.isEmpty()) {
                String dep = queue.remove();
                debug("Checking dependency " + dep);
                if (dep.equals(thread)) {
                    // Cycle found!
                    debug("Cycle found!");
                    return true;
                }
                else {
                    if (threadDeps.containsKey(dep))
                        for (String s: threadDeps.get(dep).keySet()) {
                            debug("Adding " + s + " as next dependency to check");
                            queue.add(s);
                        }
                    else {
                        debug("No further dependencies for thread " + thread);
                    }
                }
            }
            return false; // no cycle
        }
    }


    /**
     * Records that the first thread waits for the second one with respect to
     * some lock.
     */
    private synchronized void recordThreadDependency(String waiter, String waited) {

        if (!threadDeps.containsKey(waiter)) {
            threadDeps.put(waiter, new HashMap<String, Integer>());
        }

        Map<String,Integer> threadDepCounts = threadDeps.get(waiter);
        if (threadDepCounts.containsKey(waited))
            threadDepCounts.put(waited, threadDepCounts.get(waiter) + 1);
        else
            threadDepCounts.put(waited, 1);
    }


    /**
     * Decreases the thread dependency count from waiter to waited by one, or
     * removes it entirely if it would be set to zero.
     */
    private synchronized void decreaseDependencyCount(String waiter, String waited) {

        if (!threadDeps.containsKey(waiter))
            throw new RuntimeException("Potential bug: no thread dependency entry found for " + waiter + ".");
        else {
            Map<String,Integer> threadDepCounts = threadDeps.get(waiter);
            if (!threadDepCounts.containsKey(waited))
                throw new RuntimeException("Bug? no dependency count found for " + waiter + " -> " + waited);
            int count = threadDepCounts.get(waited);
            if (count == 1)
                threadDepCounts.remove(waited);
            else
                threadDepCounts.put(waited, count - 1);
        }
    }

    private void debug(String msg) {
        if (DEBUG) {
            final String thread = Thread.currentThread().getName();
            System.out.println(thread + ": " + msg);
        }
    }
}
