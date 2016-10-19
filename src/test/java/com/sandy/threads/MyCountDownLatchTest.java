package com.sandy.threads;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MyCountDownLatchTest {

    public static void main(String[] args) {
        runWithMyCountDownLatch();
//        runWithJavaCountDownLatch();
    }

    private static void runWithMyCountDownLatch() {
        MyCountDownLatch countDownLatch = new MyCountDownLatch(5);

        Runnable runnable = () -> {
            System.out.println("Processing ...");
            sleepForRandomTime();
            System.out.println("Done with Thread");
            countDownLatch.countDown();
        };

        for (int i = 0; i < 5; i++)
            new Thread(runnable).start();

        countDownLatch.await();
        System.out.println("Inside main method. Done with all threads");
    }

    private static void runWithJavaCountDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Runnable runnable = () -> {
            System.out.println("Processing ...");
            sleepForRandomTime();
            System.out.println("Done with Thread");
            countDownLatch.countDown();
        };

        for (int i = 0; i < 5; i++)
            new Thread(runnable).start();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Inside main method. Done with all threads");
    }

    private static void sleepForRandomTime() {
        try {
            Thread.sleep(Math.abs(new Random().nextInt(10)) * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
