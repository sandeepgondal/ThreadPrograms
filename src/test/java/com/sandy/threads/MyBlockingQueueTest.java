package com.sandy.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by gondals on 18/10/16.
 */
public class MyBlockingQueueTest {

    public static void main(String[] args) {
        runWithMyBlockingQueue();
//        runWithJavaBlockingQueue();
    }

    private static void runWithMyBlockingQueue() {
        MyBlockingQueue blockingQueue = new MyBlockingQueue(5);

        // Producer
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                String data = "String" + i;
                blockingQueue.put(data);
                System.out.println("Added Object : " + data);
            }
        }).start();

        // Consumer
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                // Consumer is slow
                sleep();

                System.out.println("Got data : " + blockingQueue.take());
            }
        }).start();
    }

    private static void runWithJavaBlockingQueue() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(5);

        // Producer
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                String data = "String" + i;
                try {
                    blockingQueue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Added Object : " + data);
            }
        }).start();

        // Consumer
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                String data = null;
                try {
                    data = (String) blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Got data : " + data);
            }
        }).start();
    }

    private static void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
