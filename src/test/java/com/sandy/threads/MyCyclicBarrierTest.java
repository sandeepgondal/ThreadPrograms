package com.sandy.threads;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by gondals on 18/10/16.
 */
public class MyCyclicBarrierTest {
    private static final int NO_OF_THREADS = 5;

    public static void main(String[] args) {
        runWithMyCyclicBarrier();
//        runWithJavaCyclicBarrier();
    }

    private static void runWithMyCyclicBarrier() {
        MyCyclicBarrier cyclicBarrier = new MyCyclicBarrier(NO_OF_THREADS, () -> {
            System.out.println("DONE !!! with all thread execution");
        });

        for (int i = 0; i < NO_OF_THREADS; i++) {
            new Thread(() -> {
                System.out.println("Inside thread");
                sleepRandomTime();
                cyclicBarrier.await();
            }).start();
        }
    }

    private static void runWithJavaCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NO_OF_THREADS, () -> {
            System.out.println("DONE !!! with all thread execution");
        });

        for (int i = 0; i < NO_OF_THREADS; i++) {
            new Thread(() -> {
                System.out.println("Inside thread");

                sleepRandomTime();

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void sleepRandomTime() {
        try {
            int millis = Math.abs(new Random().nextInt(10)) * 100;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
