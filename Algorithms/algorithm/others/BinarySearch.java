package Algorithms.algorithm.others;

public class BinarySearch {
    public static void main(String[] strs) {
        BinarySearch bs = new BinarySearch();
        int[] num = new int[]{0,1,2,3,4,5};
        int target = 9;
        System.out.print(bs.binarySearch(num, target));
        //int[] num2 = new int[0];
        //System.out.print(bs.binarySearch(num2, target));
    }
    
    public int binarySearch(int num[], int target) {
        if (num == null || num.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = num.length - 1;
        
        String test = "test";
        String t2 = test.substring(0,0);
        System.out.printf(test.substring(0,0));
        
//        while (left <= right) {
//            int mid = left + (right - left)/2;
//            if (num[mid] > target) {
//                right = mid - 1;
//            } else if (num[mid] < target) {
//                left = mid + 1;
//            } else {
//                return mid;
//            }
//        }
        // [1, 2]
        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            if (num[mid] > target) {
                right = mid;
            } else if (num[mid] < target) {
                left = mid;
            } else {
                return mid;
            }
        }
        
        if (num[left] == target) {
            return left;
        } else if (num[right] == target) {
            return right;
        }
        
        return -1;
    }
}
