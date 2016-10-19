package com.sandy.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLockTest {

    public static void main(String[] args) {
        runWithMyReentrantLock();
//        runWithJavaReentrantLock();
        System.out.println("Done in main");
    }

    private static void runWithMyReentrantLock() {
        MyReentrantLock lock = new MyReentrantLock();

        new Thread(() -> {
            System.out.println("This is thread 1");

            if (lock.tryLock()) {
                System.out.println("Lock acquired by Thread 1");
                sleep(2000);

                lock.lock();
                System.out.println("Again lock acquired by Thread 1");
                sleep(2000);

                lock.unlock();
                lock.unlock();
            } else
                System.out.println("Could not acquire lock by Thread 1");

        }).start();

        new Thread(() -> {
            System.out.println("This is thread 2");

            while (true) {
                if (lock.tryLock()) {
                    System.out.println("Lock acquired by Thread 2");
                    lock.unlock();
                    break;
                } else {
                    System.out.println("Could not acquire lock by Thread 2");

                    sleep(1000);
                }
            }

        }).start();
    }

    private static void runWithJavaReentrantLock() {
        Lock lock = new ReentrantLock();

        new Thread(() -> {
            System.out.println("This is thread 1");

            if (lock.tryLock()) {
                System.out.println("Lock acquired by Thread 1");
                sleep(2000);

                lock.lock();
                System.out.println("Again lock acquired by Thread 1");
                sleep(2000);

                lock.unlock();
                lock.unlock();
            } else
                System.out.println("Could not acquire lock by Thread 1");

        }).start();

        new Thread(() -> {
            System.out.println("This is thread 2");

            while (true) {
                if (lock.tryLock()) {
                    System.out.println("Lock acquired by Thread 2");
                    lock.unlock();
                    break;
                } else {
                    System.out.println("Could not acquire lock by Thread 2");

                    sleep(1000);
                }
            }

        }).start();
    }

    private static void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
