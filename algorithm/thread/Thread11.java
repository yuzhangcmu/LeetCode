package Algorithms.algorithm.thread;

public class Thread11 extends Thread {
    String resource1;
    String resource2;

    public Thread11(String resource1, String reource2) {
        this.resource1 = resource1;
        this.resource2 = reource2;
        System.out.println("coming1");
    }

    public void run() {
        synchronized (resource1) {
            System.out.println("Thread 1: locked resource 1");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            synchronized (resource2) {
                System.out.println("Thread 1: locked resource 2");
            }
        }
    }
}