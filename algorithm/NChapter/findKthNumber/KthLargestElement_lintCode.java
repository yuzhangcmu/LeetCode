package Algorithms.algorithm.NChapter.findKthNumber;

import java.util.ArrayList;

// The link: 

// http://www.lintcode.com/en/problem/kth-largest-element/

class KthLargestElement_lintCode {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        // write your code here
        if (k < 1 || numbers == null) {
            return 0;
        }
        
        return getKth(numbers.size() - k + 1, numbers, 0, numbers.size() - 1);
    }
    
    public int getKth(int k, ArrayList<Integer> numbers, int start, int end) {
        // Choose the last one as the pivot
        int pivot = numbers.get(end);
        
        int left = start;
        int right = end;
        
        while (true) {
            while (numbers.get(left) < pivot && left < right) {
                left++;    
            }
            
            while (numbers.get(right) >= pivot && right > left) {
                right--;
            }
            
            if (left == right) {
                break;
            }
            
            swap(numbers, left, right);
        }
        
        // left: the first one which is bigger than pivot.
        swap(numbers, left, end);
        
        if (k == left + 1) {
            return pivot;
        // Try to find the element from the left side.
        } else if (k < left + 1) {
            return getKth(k, numbers, start, left - 1);
        } else {
        // Try to find the element from the right side.            
            return getKth(k, numbers, left + 1, end);
        }
    }
    
    /*
        Swap the two nodes.
    */
    public void swap(ArrayList<Integer> numbers, int n1, int n2) {
        int tmp = numbers.get(n1);
        numbers.set(n1, numbers.get(n2));
        numbers.set(n2, tmp);
    }
};
