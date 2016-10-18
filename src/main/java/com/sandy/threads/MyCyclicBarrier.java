package com.sandy.threads;

public class MyCyclicBarrier {

    volatile private int noOfThreads;
    volatile private int count;
    private Runnable runnable;

    public MyCyclicBarrier(final int noOfThreads, final Runnable runnable) {
        this.noOfThreads = noOfThreads;
        count = 0;
        this.runnable = runnable;
    }

    public void await() {
        count++;
        if (count == noOfThreads)
            new Thread(runnable).start();
    }
}
