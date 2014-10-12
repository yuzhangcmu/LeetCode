package Algorithms.sequence;
public class Reverse {
    public static void main(String[] args) {  
        System.out.println(reverse(-1234));  
        System.out.println(reverse(100));  
    } 
    
    public static int reverse(int x) {
        long n = x;
        
        long ret = 0;
        while (n != 0) {
            long left = n % 10;
            ret *= 10;
            ret += left;
            n /= 10;
        }
        
        return (int)ret;
    }
}