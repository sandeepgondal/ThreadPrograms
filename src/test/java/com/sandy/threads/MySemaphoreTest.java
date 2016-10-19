package com.sandy.threads;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MySemaphoreTest {

    public static void main(String[] args) {
//        runWithMySemaphoreAcquire();
        runWithMySemaphoreTryAcquire();
//        runWithJavaSemaphore();
    }

    private static void runWithMySemaphoreAcquire() {
        MySemaphore semaphore = new MySemaphore(5);

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                semaphore.acquire();
                System.out.println("Got Semaphore");
                sleep();
                semaphore.release();
            }).start();
        }
    }

    private static void runWithMySemaphoreTryAcquire() {
        MySemaphore semaphore = new MySemaphore(5);

        for (int i = 0; i < 50; i++) {
            if (semaphore.tryAcquire()) {
                new Thread(() -> {
                    System.out.println("Got Semaphore");
                    sleep();
                    semaphore.release();
                }).start();
            } else {
                System.out.println("Semaphore not available, sleeping ...");
                sleep();
            }
        }
    }

    private static void runWithJavaSemaphore() {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 50; i++) {
            if (semaphore.tryAcquire()) {
                new Thread(() -> {
                    System.out.println("Got Semaphore");
                    sleep();
                    semaphore.release();
                }).start();
            } else {
                System.out.println("Semaphore not available, sleeping ...");
                sleep();
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt(5)) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
