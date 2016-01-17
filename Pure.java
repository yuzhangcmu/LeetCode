package Algorithms;

public class Pure {
    static int cnt = 0;
    
    public static void main(String[] strs) {
        f(19);
       
        System.out.println(cnt);
    }

    public static int f(int x) {
        cnt++;
        if (x < 1) return 1;
        else return f(x - 1) + g(x);
    }
    
    public static int g(int x) {
        //cnt++;
        if (x < 2) return 1;
        else return f(x - 1) + g(x / 2);
    }
}
