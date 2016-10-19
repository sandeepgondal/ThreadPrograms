package com.sandy.threads;

public class MySemaphore {

    private int permits;
    private int count;
    private MyLock myLock;

    public MySemaphore(final int permits) {
        this.permits = permits;
        this.count = 0;
        this.myLock = new MyLock();
    }

    public void acquire() throws InterruptedException {
        synchronized (myLock) {
            while(count > permits)
                myLock.wait();

            count++;
        }
    }

    public void release() {
        synchronized (myLock) {
            count--;
            myLock.notifyAll();
        }
    }

    public boolean tryAcquire() {
        synchronized (myLock) {
            if (count < permits) {
                count++;
                return true;
            }
            return false;
        }
    }

    private class MyLock {
    }
}
