package com.sandy.threads;

public class ThreadSynchronization {

    public static void main(String[] args) {
        new ThreadSynchronization().execute();
    }

    private void execute() {
        SyncDataHolder syncDataHolder = new SyncDataHolder(true, 100);

        Runnable odd = () -> {
            int count = 0;
            while (count < syncDataHolder.limit) {
                synchronized (syncDataHolder) {
                    if (syncDataHolder.oddTurn) {
                        System.out.println(count);
                        count += 2;
                        syncDataHolder.oddTurn = false;
                        syncDataHolder.notifyAll();
                    } else {
                        try {
                            syncDataHolder.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Runnable even = () -> {
            int count = 1;
            while (count < syncDataHolder.limit) {
                synchronized (syncDataHolder) {
                    if (!syncDataHolder.oddTurn) {
                        System.out.println(count);
                        count += 2;
                        syncDataHolder.oddTurn = true;
                        syncDataHolder.notifyAll();
                    } else {
                        try {
                            syncDataHolder.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        new Thread(even).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(odd).start();
    }

    private class SyncDataHolder {
        private boolean oddTurn;
        private int limit;

        public SyncDataHolder(final boolean oddTurn, final int limit) {
            this.oddTurn = oddTurn;
            this.limit = limit;
        }
    }

}
