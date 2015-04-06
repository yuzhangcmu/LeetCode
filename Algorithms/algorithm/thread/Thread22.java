package Algorithms.algorithm.thread;

public class Thread22 extends Thread {
    String resource1;
    String resource2;

    public Thread22(String resource1, String reource2) {
        this.resource1 = resource1;
        this.resource2 = reource2;
        System.out.println("coming2");
    }

    public void run() {
        synchronized (resource2) {
            System.out.println("Thread 2: locked resource 2");

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            synchronized (resource1) {
                System.out.println("Thread 2: locked resource 1");
            }

        }

    }
}