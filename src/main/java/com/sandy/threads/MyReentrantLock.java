package com.sandy.threads;

public class MyReentrantLock {

    private MyLock mylock;

    public MyReentrantLock() {
        this.mylock = new MyLock();
    }

    public void lock() {
        synchronized (mylock) {
            Thread threadHoldingLock = mylock.currentThread;
            if (threadHoldingLock == null || threadHoldingLock == Thread.currentThread()) {
                mylock.count++;
                mylock.currentThread = Thread.currentThread();
                mylock.notifyAll();
            } else {
                try {
                    mylock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unlock() {
        synchronized (mylock) {
            Thread threadHoldingLock = mylock.currentThread;
            if (threadHoldingLock != null && threadHoldingLock == Thread.currentThread()) {
                mylock.count--;
                if (mylock.count == 0) {
                    mylock.currentThread = null;
                    mylock.notifyAll();
                }
            }
        }
    }

    public boolean tryLock() {
        if (mylock.count == 0 || mylock.currentThread == Thread.currentThread()) {
            synchronized (mylock) {
                if (mylock.count == 0 || mylock.currentThread == Thread.currentThread()) {
                    mylock.count++;
                    mylock.currentThread = Thread.currentThread();
                    return true;
                }
            }
        }
        return false;
    }

    private class MyLock {
        private Thread currentThread = null;
        private int count = 0;
    }
}
