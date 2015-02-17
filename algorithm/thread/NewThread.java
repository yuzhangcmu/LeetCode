package Algorithms.algorithm.thread;

class NewThread implements Runnable {
    Thread t, t2;

    int cnt = 0;
    
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    NewThread() {
        // 创建第二个新线程
        t = new Thread(this, "Demo Thread");
        t2 = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
        t.start(); // 开始线程
        t2.start();
    }
    
    public  void addCnt() {
        synchronized(lock2) {
            cnt++;
        }
    }
    
    public void addCnt2() {
        synchronized(lock1) {
            cnt++;
        }
    }

    // 第二个线程入口
    public void run() {
        try {
            for (int i = 100; i > 0; i--) {
                //System.out.println("Child Thread: " + i);
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