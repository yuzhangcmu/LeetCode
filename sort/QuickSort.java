package Algorithms.sort;

/*********************************************************
 * 
 * 08-722 Data Structures for Application Programmers
 * Lab 5 Comparing MergeSort with QuickSort
 * 
 * A simple QuickSort implementation
 * 
 *********************************************************/

import java.util.*;

public class QuickSort {
    //private static final int SIZE = 100000;
    private static final int SIZE = 5000;
    private static Random rand = new Random();

    public static void main(String args[]) {
        int[] array = new int[SIZE];

        for (int i = 0; i < SIZE; i++)
            array[i] = rand.nextInt();
            //array[i] = i;
        
        //int[] array = {3, 4, 6, 1, 7, 8, 6, 7, 6, 6};
        int[] array1 = {6,1,3,2,5};
        //int[] array1 = {6,6,6,6};

        // reversely ordered
        /*
        for(int i=0;i<SIZE; i++) array[i] = SIZE - i;
         */

        quickSort(array1);

        // to make sure sorting works.
        // add "-ea" vm argument
        //assert isSorted(array1);
        //System.out.println(partition(array1, 0, array1.length - 1, array1[array1.length - 1]));
        System.out.println(isSorted(array1));
        //printArray(array);
    }
    
    public static void printArray(int[] arr) {
//        System.out.println();
//        for(int i: arr) {
//            System.out.print(i + " ");
//        }
    }

    public static void quickSort(int[] arr) {
        recQuickSort(arr, 0, arr.length - 1);
    }

    private static void recQuickSort(int[] arr, int left, int right) {
        // Just the input parameter.
        if (arr == null || left >= right) {
            return;
        }

        // we just set the right node to be pivot.
        int pivPosition = partition1(arr, left, right, arr[right]);

        recQuickSort(arr, left, pivPosition - 1);
        recQuickSort(arr, pivPosition + 1, right);
    }
    
    private static int partition1(int[] arr, int left, int right, int pivot) {
        int l = left;
        int r = right;
        
        //System.out.printf("\nEnter partition1. left:%d, right:%d\n", left, right);
        
        while (l < r) {
            while (l < r && arr[l] < pivot) {
                l++;
            }
            
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            
            //System.out.printf("\nswap while arr before. l:%d, r:%d\n", l, r);
            printArray(arr);
            swap(arr, l, r);
            //System.out.printf("\nswap while arr end. l:%d, r:%d\n", l, r);
            printArray(arr);
        }
        
        //System.out.printf("\nEnd while swap while arr Begin. l:%d, r:%d\n", l, r);
        printArray(arr);
        swap(arr, l, right);
        //System.out.printf("\nEnd while swap while arr end. l:%d, r:%d\n", l, r);
        printArray(arr);
        return l;
    }
    
    // partition the array and return the new pivot position.
    private static int partition(int[] arr, int left, int right, int pivot) {
        // set the pivot.
        int l = left - 1 ;
        int r = right;

        /*
           example: 
           let 6 to be the pivot.

           (1) At the beginning:
           3 4 6 1 7 8 6
          l            r
            

           (2) After the first while loop:
           3 4 6 1 7 8 6 
               l r  

           (3) swap them, then continue to move i and j:
           3 4 1 6 7 8 6 
               l r                 

           (3) swap them, then continue to move i and j:
           3 4 1 6 7 8 6 
                 l     pivot
                 r
           (4) swap the left and the pivot.
           3 4 1 6 7 8 6 
                 l     pivot           

        */

        while (true) {
            // Find the first element which does not fulfill the rule
            // It will not move out of range because the right node is pivot.
            // 使用< 很重要，这样可以避免l跑到pivot的位置，就是right的位置
            //while (l < r && arr[++l] <= pivot);
            while (arr[++l] < pivot);

            // Find the first element which does not fulfill the rule
            // Don't need to move r to be left of LEFT.
            
            // use >= to keep elements >= pivot in the right.
            while (r > l && arr[--r] >= pivot);

            // If r <= l, means that all the elements is in the right place.
            if (r <= l) {
                break;
            }

            // Swap the first two elements that does not fit the rule.
            swap(arr, r, l);
            //printArray(arr);
        }
        
        // The l pointer point to the first element which is bigger than the pivot.
        // So we can put the pivot just here. Because put a big or equal one in the last will not change the rule that:
        // all the smaller one is in the left and the right one is in the right.
        swap(arr, l, right);

        return l;
    }

    // private helper method to swap two values in an array
    private static void swap(int[] arr, int dex1, int dex2) {
        int tmp = arr[dex1];
        arr[dex1] = arr[dex2];
        arr[dex2] = tmp;
    }

    /**********************************************************
     * Check if array is sorted. A simple debugging tool
     **********************************************************/
    private static boolean isSorted(int[] array) {
        return isSorted(array, 0, array.length - 1);
    }

    private static boolean isSorted(int[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (array[i] < array[i - 1])
                return false;
        return true;
    }

}
