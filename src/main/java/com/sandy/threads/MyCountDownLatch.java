package com.sandy.threads;

public class MyCountDownLatch {

    private int capacity;
    private int count;
    private MyLockObject lock;

    public MyCountDownLatch(final int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.lock = new MyLockObject();
    }

    public void countDown() {
        synchronized (lock) {
            count++;
            if (count >= capacity)
                lock.notifyAll();
        }
    }

    public void await() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyLockObject {
    }

}
