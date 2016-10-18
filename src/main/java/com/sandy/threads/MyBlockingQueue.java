package com.sandy.threads;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue <T> {

    private DataHolder<T> dataHolder;

    public MyBlockingQueue(final int limit) {
        dataHolder = new DataHolder(limit);
    }

    public void put(T obj) {
        synchronized (dataHolder) {
            if (dataHolder.isQueueFull()) {
                try {
                    dataHolder.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dataHolder.add(obj);
            dataHolder.notifyAll();
        }
    }

    public T take() {
        synchronized (dataHolder) {
            if (dataHolder.isQueueEmpty()) {
                try {
                    dataHolder.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T obj = dataHolder.get();
            dataHolder.notifyAll();
            return obj;
        }
    }

    private class DataHolder <T> {
        private int limit;
        private Queue<T> queue = new ArrayDeque<T>();

        public DataHolder(final int limit) {
            this.limit = limit;
        }

        public boolean isQueueEmpty() {
            return queue.size() <= 0;
        }

        public boolean isQueueFull() {
            return queue.size() >= limit;
        }

        public void add(T obj) {
            queue.add(obj);
        }

        public T get() {
            return queue.poll();
        }
    }

}
