package Algorithms.algorithm.interviews.twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Input: int[] A = {1, 1, 2, 3, 4, 5, 2}; k = 3
 * return the highest frequency numbers.
 * return: [1, 2, 3] or [1, 2, 4] or [1, 2, 5]
 * */

public class FindMax {
    public static void main(String[] strs) {
        int[] A2 = {1, 0, 0, 1, 0, 0, 1, 0};
        
        //int[] A2 = {1, 0, 0, 1, 0, 0, 1};
        //int[] A2 = {1, 1, 1};
        //int[] A2 = {1};
        //int[] A2 = {0, 0};
        //int[] A2 = {0, 0, 1, 1};
        //int[] A2 = {0, 1, 1, 1, 1};
        //int[] A2 = {1, 0, 0, 0, 0};
        //int[] A2 = {0,0,0, 1};
        //int[] A2 = {0};
        //int[] A2 = {1};
        //int[] A2 = {1, 1, 1};
        
        System.out.println(findMax(A2));
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
    }
    
    public static int find(int[] arr) {
        int cnt = 0;
        
        int max = Integer.MIN_VALUE;
        
        int lMax = 0;
        int rMax = 0;
        
        int l = 0; 
        
//        boolean allOne = true;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] == 0) {
//                allOne = false;
//            }
//        }
//        
//        if (allOne) {
//            return arr.length;
//        }
        
        for (int i = 0; i < arr.length; i++) {
            if (cnt < 0) {
                l = i;
                cnt = 0;
            }
            
            if (arr[i] == 0) {
                cnt++;
            } else {
                cnt--;
            }
            
            if (cnt > max) {
                max = Math.max(max, cnt);
                lMax = l;
                rMax = i;
            }
        }
        
        //System.out.println("max:" + max);
        if (max == -1) {
            return arr.length;
        }
        
        int num = 0;
        for (int i = 0; i <= arr.length - 1; i++) {
            if (i <= rMax && i >= lMax) {
                if (arr[i] == 0) {
                    num++;
                }    
                continue;
            }
            
            if (arr[i] == 1) {
                num++;
            }
        }
        
        return num;
    }
    
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        int len = arr.length;
        
        int curStart = 0;
        int end = 0;
        
        int start = 0;
        
        for (int i = 0; i < len; i++) {
            if (sum < 0) {
                sum = 0;
                curStart = i;
            }
            
            if (arr[i] == 1) {
                sum --;
            } else {
                sum++;
            }
            
            if (sum > max) {
                max = sum;
                start = curStart;
                end = i;
            }
        }
        
        int ret = 0;
        for (int i = 0; i < len; i++) {
            if (i <= end && i >= start) {
                if (arr[i] != 1) {
                    ret++;
                }
            } else {
                if (arr[i] == 1) {
                    ret++;
                }
            }
        }
        
        System.out.println("start: " + start + " end:" + end);
        System.out.println("ret: " + ret);
        
        return ret;
    }
}
