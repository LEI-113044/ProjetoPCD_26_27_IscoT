package iscot.coordination;

/**
 * A countdown synchronizer that allows a thread to wait for a count to reach zero.
 *
 * <p>Each call to {@link #countdown()} decrements the count by one until it reaches
 * zero. Threads waiting in {@link #await(int)} are notified when that happens.</p>
 */
public class ModifiedCountdownLatch {
    private int count;

    /**
     * Creates a latch with the specified initial count.
     *
     * @param count number of completions to await, typically the number of expected clients
     */
    public ModifiedCountdownLatch(int count) {this.count = count;}

    /**
     * Decrements the count if it is greater than zero.
     *
     * <p>When the count reaches zero, wakes all threads waiting in {@link #await(int)}.
     * Calls made after the count has reached zero have no effect.</p>
     */
    public synchronized void countdown() {
        if (count > 0) {
            count--;
            if (count == 0) {
                notifyAll();
            }
        }
    }

    /**
     * Waits until the count reaches zero or the maximum wait period elapses.
     *
     * <p>The method may return because the count reached zero or because the timeout
     * elapsed; it does not indicate which condition occurred. A non-positive wait
     * period causes the method to return without waiting if the count is still positive.</p>
     *
     * @param waitPeriod maximum time to wait, in milliseconds
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public synchronized void await(int waitPeriod) throws InterruptedException {
        long remainingTime = waitPeriod;
        long startTime = System.currentTimeMillis();

        while (count > 0 && remainingTime > 0) {
            wait(remainingTime);
            long elapsed = System.currentTimeMillis() - startTime;
            remainingTime = waitPeriod - elapsed;
        }
    }
}