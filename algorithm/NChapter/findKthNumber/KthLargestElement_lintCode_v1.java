package Algorithms.algorithm.NChapter.findKthNumber;
import java.util.ArrayList;

/*
 * LintCode Version 1: Use arrays to solve it.
 * */

class KthLargestElement_lintCode_v1 {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public static void main(String[] strs) {
        KthLargestElement_lintCode_v1 Kth = new KthLargestElement_lintCode_v1();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(9);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);
        numbers.add(8);
        
        System.out.println(Kth.kthLargestElement(3, numbers));
    }
    
    public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        // write your code here
        // write your code here
        Integer[] num = new Integer[numbers.size()];
        numbers.toArray(num);
        
        return findKthNumberHelp(num, num.length + 1 - k, 0, num.length - 1);
    }

    public int findKthNumberHelp(Integer[] num, int k, int start, int end) {
        int left = start;
        int right = end;
        int pivot = left;
        while (left <= right) {
            while (left <= right && num[left] <= num[pivot]) {
                left++;
            }

            while (left <= right && num[right] >= num[pivot]) {
                right--;
            }

            if (left < right) {
                swap(num, left, right);
            }
        }

        swap(num, pivot, right);

        if (right + 1 == k) {
            return num[right];
        }

        if (right + 1 > k) {
            // find in the left side.
            return findKthNumberHelp(num, k, start, right - 1);
        } else {
            return findKthNumberHelp(num, k, right + 1, end);
        }
    }

    private void swap(Integer[] num, int one, int two) {
        int tmp = num[one];
        num[one] = num[two];
        num[two] = tmp;
    }
};
