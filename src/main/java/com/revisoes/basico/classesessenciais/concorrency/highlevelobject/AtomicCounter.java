package com.revisoes.basico.classesessenciais.concorrency.highlevelobject;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.revisoes.basico.classesessenciais.concorrency.synchronization.SynchronizedCounter;

/**
 * For this simple class {@link SynchronizedCounter}, synchronization is an
 * acceptable solution. But for a more complicated class, we might want to avoid
 * the liveness (<em>Deadlock, Starvation and Livelock</em>) impact o
 * unnecessary synchronization. Replacing the int field with an AtomicInteger
 * allows us to prevent thread interference without resorting to
 * synchronization, as in AtomicCounter.
 * 
 * example about Concurrent Random Numbers in main method.
 */
class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }

    // 
    public static void main(String[] args) {

        // For concurrent access, using ThreadLocalRandom instead of Math.random()
        // results in less contention and, ultimately, better performance.
        int randomInt = ThreadLocalRandom.current().nextInt(11, 22);
        
        System.out.println(randomInt);
    }
}
