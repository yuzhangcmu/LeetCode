package Algorithms.algorithm.others;

import java.util.HashMap;

public class KthSort {
    public static class Graph {
        String name;
        HashMap<Graph, Integer> subs;
        public Graph(String name) {
            this.name = name;
        }
    }
    
    public static void main(String[] strs) {
//        int[] input = new int[]{1,2,4,3,9,11,0};
//        sort(input);
//        for(int n: input) {
//            System.out.print(n + " ");
//        }
        Graph c1 = new Graph("c1");
        
        //for()
    }
    
    public static void sort(int[] input) {
        if (input == null) {
            return;
        }
        
        int len = input.length;
        for (int i = 1; i < len; i++) {
            int cur = input[i];
            int j = i - 1;
            while (j >= 0 && input[j] > cur) {
                input[j + 1] = input[j];
                j--;
            }
            
            input[j + 1] = cur;
        }
    }

}
