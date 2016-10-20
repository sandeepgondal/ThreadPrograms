package com.sandy.threads;

public class VolatileTest {

    volatile private static int VOLATILE_VAR = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            int x = VOLATILE_VAR;
            while(true) {
                if (x != VOLATILE_VAR) {
                    System.out.println("Updated Volatile var : " + VOLATILE_VAR);
                    x = VOLATILE_VAR;
                }
            }

        }).start();

        new Thread(() -> {
            while (true) {
                ++VOLATILE_VAR;
                System.out.println("Volatile var changed : " + VOLATILE_VAR);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }

}
