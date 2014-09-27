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
    private static final int SIZE = 100000;
    private static Random rand = new Random();

    public static void main(String args[]) {
        int[] array = new int[SIZE];

        for (int i = 0; i < SIZE; i++)
            array[i] = rand.nextInt();

        // reversely ordered
        /*
        for(int i=0;i<SIZE; i++) array[i] = SIZE - i;
         */

        quickSort(array);

        // to make sure sorting works.
        // add "-ea" vm argument
        assert isSorted(array);
        
        System.out.println(isSorted(array));
        //printArray(array);
    }
    
    public static void printArray(int[] arr) {
        System.out.println();
        for(int i: arr) {
            System.out.println(i + " ");
        }
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
        int pivPosition = partition(arr, left, right, arr[right]);

        recQuickSort(arr, left, pivPosition - 1);
        recQuickSort(arr, pivPosition + 1, right);
    }

    // partition the array and return the new pivot position.
    private static int partition(int[] arr, int left, int right, int pivot) {
        int len = arr.length;

        // set the pivot.
        int l = left ;
        int r = right - 1;

        /*
           example: 
           let 4 to be the pivot.

           (1) At the beginning:
           2 8 7 1 3 5 6 4
         i                 j
            

           (2) After the first while loop:
           2 8 7 1 3 5 6 4 
             i     j  

           (3) swap them, then continue to move i and j:
           2 3 7 1 8 5 6 4 
               i j                 

           (3) swap them, then continue to move i and j:
           2 3 1 7 8 5 6 4 
               j i       pivo

           (4) swap the left and the pivo.
           2 3 1 7 8 5 6 4 
               j i       pivo           

        */

        while (true) {
            // Find the first element which does not fullfill the rule
            // When l move to the pivot, it will not move again because arr[l] == pivot.
            while (arr[l] < pivot) {
                l++;
            }

            // Find the first element which does not fullfill the rule
            while (r > 0 && arr[r] > pivot) {
                r--;
            }

            // If r <= l, means that all the elements is in the right place.
            if (r <= l) {
                break;
            }

            // Swap the first two elements that does not fit the rule.
            swap(arr, r, l);
        }
        
        // The l pointer point to the first element which is bigger than the pvio.
        // So we can put the pvio just here. Because put a big one in the last will not change the rule that:
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
