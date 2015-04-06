package Algorithms.algorithm.interviews.godaddy;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public static int findTotalNumber(int num) {
        
        int n1 = 0;
        int count=0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (num > 0) {
            n1++;
            if(num%10==0){
                count = 0;
            }
            else  {
                ++count;
            }
            list.add(num % 10);
            num /= 10;
        }
        
        int ret = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == 0) {
                break;
            }
            ret++;
        }
        
        System.out.println(list);
        System.out.println(ret);
        
        return n1;
    }
    
   
    
    public static List<Integer> primeFactors(int number) {
        int n = number;
        List<Integer> factors = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        
        
        
        return factors;
    }

    public static void main(String[] args) {
        findTotalNumber(123010);
//        System.out.println("Primefactors of 29");
//        for (Integer integer : primeFactors(29)) {
//            System.out.println(integer);
//        }
//        System.out.println("Primefactors of 3");
//        for (Integer integer : primeFactors(3)) {
//            System.out.println(integer);
//        }
//        System.out.println("Primefactors of 32");
//        for (Integer integer : primeFactors(32)) {
//            System.out.println(integer);
//        }
    }
}