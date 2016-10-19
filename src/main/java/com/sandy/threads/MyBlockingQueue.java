package com.sandy.threads;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue <T> {

    private int limit;
    private Queue<T> queue = new ArrayDeque();

    public MyBlockingQueue(final int limit) {
        this.limit = limit;
    }

    public void put(T obj) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >= limit)
                queue.wait();

            queue.add(obj);
            queue.notifyAll();
        }
    }

    public T take() throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == 0)
                queue.wait();

            queue.notifyAll();
            return queue.poll();
        }
    }
}
