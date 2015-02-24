package Algorithms.algorithm.thread;

import java.util.ArrayDeque;
import java.util.Stack;

class NewThread implements Runnable {
    Thread t, t2;

    int cnt = 0;
    
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public static void main() {
//        int[] A = {4, 2, 2, 8, 5, 5, 5, 8, 0};
//        mario(A);    
//        
//        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
//        Stack<Integer> stk = new Stack<Integer>();
//        
        String[] strs = ",test1,test2".split(".");
        System.out.println(strs);
    }
    
    public static int mario(int[] A){
        if(A == null || A.length == 0){
            return 0;
        }
        
        int max = 0;
        int res = 0;
        int[] container = new int[A.length];
        
        for(int i = 0; i < A.length; i++){
            container[i] = max;
            max = Math.max(max, A[i]);
        }
        max = 0;
        for(int i = A.length -1; i >= 0; --i){
            container[i] = Math.min(max, container[i]);
            max = Math.max(max,A[i]);
            res += container[i] - A[i] > 0 ? container[i] - A[i] : 0;
        }
        return res;
    }

    NewThread() {
//        // 创建第二个新线程
//        t = new Thread(this, "Demo Thread1");
//        t2 = new Thread(this, "Demo Thread2");
//        System.out.println("Child thread: " + t);
//        t.start(); // 开始线程
//        t2.start();
    }
    
    public void addCnt() {
        synchronized(lock2) {
            //System.out.println("Child lock2: ");
            cnt++;
        }
    }
    
    public void addCnt2() {
        synchronized(lock1) {
            //System.out.println("Child lock1: ");
            cnt++;
        }
    }

    // 第二个线程入口
    public void run() {
        try {
            for (int i = 100; i > 0; i--) {
                // System.out.println("Child Thread: ");
                // 暂停线程
                if (i % 2 == 0) {
                    addCnt();
                } else {
                    addCnt2();
                }
                
                System.out.println("cnt is: " + cnt);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}