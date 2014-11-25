package Algorithms.algorithm.others;

public class Reverse_binary {
    public static void main(String[] strs) {
        System.out.println(reverse(20034556));
    }
    
    public static int reverse(int in) {
        int ret = 0;
        
        for (int i = 0; i < 4; i++) {
            int tmp = (in & 0xFF);
            ret <<= 8;
            ret += tmp;
            in >>= 8;
        }
        
        return ret;
    }
}
