package Algorithms.algorithm.thread;

public class Deadlock_II {
    public static void main(String[] args) {
        final String s1 = "ratan";
        final String s2 = "vimal";
        // t1 tries to lock resource1 then resource2

        // t2 tries to lock resource2 then resource1

        Thread11 t1 = new Thread11(s1, s2);
        Thread22 t2 = new Thread22(s1, s2);

        t1.start();
        t2.start();
    }
}


