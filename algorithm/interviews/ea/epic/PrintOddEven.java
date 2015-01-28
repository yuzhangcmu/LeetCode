package Algorithms.algorithm.interviews.ea.epic;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintOddEven {
    public static void main1(String[] strs) {
        // 1. Create a Scanner using the InputStream available.
        Scanner scanner = new Scanner( System.in );
        
        int odd = Integer.MIN_VALUE;
        int even = Integer.MAX_VALUE;
        
        while (true) {
            // 2. Don't forget to prompt the user
            System.out.print( "Please input value(between Integer.MIN_VALUE and Integer.MAX_VALUE), input \"0\" to end: " );
            
            // 3. Use the Scanner to read a line of text from the user.
            String input = scanner.nextLine();
            // 4. Now, you can do anything with the input string that you need to.
            // Like, output it to the user.
            System.out.println( "input = " + input );
            
            if (input.equals("0")) {
                
                // when no input.
                if (odd == Integer.MIN_VALUE) {
                    System.out.println("Didn't input valid odd.");
                } else {
                    System.out.println("The largest odd is:" + odd);
                }
                
                if (even == Integer.MAX_VALUE) {
                    System.out.println("Didn't input valid even.");    
                } else {
                    System.out.println("The smalest even is:" + even);
                }
                
                break;
            }
            
            int num = Integer.parseInt(input);
            
            // even;
            if (num % 2 == 0) {
                even = Math.min(even, num);
            } else {
                odd = Math.max(odd, num);
            }
        }
    }
    
    public static void main(String[] strs) {
        System.out.println(hacker(4));
    }
    
    public static ArrayList<String> hacker(int digits) {
        ArrayList<String> rst = new ArrayList<String>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (digits <= 0) {
            return rst;
        }
        
        helper(digits, rst, list);
        return rst;
    }
    
    private static void helper(int digits, ArrayList<String> rst, ArrayList<Integer> list) {
        if (list.size() == digits) {
            StringBuilder tmp = new StringBuilder();
            for (int i: list) {
                tmp.append(i + "");
            }
            rst.add(tmp.toString());
            return;
        }
        
        for (int i = 0; i <= 9; i++) {
            // BUG 1: the size if wrong.
            if (list.size() == 0 || i > list.get(list.size() - 1)) {
                list.add(i);
                helper(digits, rst, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
