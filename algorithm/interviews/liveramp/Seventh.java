package Algorithms.algorithm.interviews.liveramp;

import java.util.ArrayList;

public class Seventh {
    public static void main(String[] strs) {
        //System.out.println(f());
        //System.out.println(f8(17));
        
        //fibonacciLoop(5);
        //System.out.println(fibonacci(9));
        
        System.out.println(getLen("ca"));
        
        
        String[] strs1 = {"2", "ca", "fae"};
        System.out.println(reduceLetter(strs1));
        //System.out.println(countReduce("eae"));
    }
    
    public static int getLen(String str) {
        int len = str.length();
        
        int left = 0;
        int right = 0;
        
        if (len <= 1) {
            return 0;
        }
        
        // even
        if (len % 2 == 0) {
            left = len / 2 - 1;
            right = left + 1;
        } else {
            // odd;
            left = len / 2;
            right = left;
        }
        
        int sum = 0;
        while (left >= 0) {
            sum += Math.abs(str.charAt(left) - str.charAt(right));
            
            left--;
            right++;
        }
        
        return sum;
        
    }
    
    private static int reduceLetter(String[] s){
        if (s == null || s.length <= 1 || s[0].equals(0)){
          return 0;
        }
        int len = Integer.parseInt(s[0]);
        int rst = 0;
        
        if (len > s.length - 1) {
            return rst;
        }
        
        for (int i = 1; i <= len; i++){
          String curr = s[i];
          rst += countReduce(curr);
        }
        return rst;
      }
    
      private static int countReduce(String s){
        if (s == null || s.length() <= 1){
          return 0;
        }
        int count = 0;
        int start = 0;
        int end = s.length() - 1;
        while (start < end){
          char left = s.charAt(start);
          char right = s.charAt(end);
          if (left > right){
            count += left - right;
          } else {
            count += right - left;
          }
          
          start++;
          end--;
        }
        return count;
      }
    
    public static int f() {
        int[] arr = new int[] {3, 7, 1, 11, 5};
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            i++;
            if (i == j) {
                return arr[i];
            }
            j--;
        }
        return arr[j];
    }
    
    public static int f8(int n) {
        int i = 0;
        int j = 1;
        while (j < n) {
            i += j;
            j++;
        }
        return i;
    }
    
    private static ArrayList<Integer> fibonacci(int n){
        ArrayList<Integer> rst = new ArrayList<Integer>();
        if (n <= 0){
          return rst;
        }
        for (int i = 1; i <= n; i++){
          if (i == 1){
            rst.add(1);
            continue;
          }
          if (i == 2){
            rst.add(1);
            continue;
          }
          int num = rst.get(rst.size() - 1) 
                  + rst.get(rst.size() - 2);
          rst.add(num);
        }
        return rst;
      }
    
    public static int fibonacciLoop(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        //ArrayList<Integer> ret = new ArrayList<Integer>(); 
        
        int fibo1=1, fibo2=1, fibonacci=1;
        for(int i= 3; i<= number; i++){
            fibonacci = fibo1 + fibo2; //Fibonacci number is sum of previous two Fibonacci number
            
            System.out.println(fibonacci);
            //ret.
            
            fibo1 = fibo2;
            fibo2 = fibonacci;
 
        }
        System.out.println(fibonacci);
        return fibonacci; //Fibonacci number
    }     
}
