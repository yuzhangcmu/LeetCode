package Algorithms.sort;

/********************************************************
 * 
 * 08-722 Data Structures for Application Programmers
 * Lecture 14 Advanced Sorting
 * 
 * Naive version of Merge Sort
 * 
 *********************************************************/
import java.util.Arrays;

public class MergeSort {

    private static final int SIZE = 10000;

    public static int[] mergeSort(int[] data) {
        // parameter valid judge.
        if (data == null) {
            return null;
        }

        // the base case.
        int len = data.length;
        if (len <= 1) {
            return data;
        }

        // divide into two arrays.
        // create left half and right half.
        int left[] = new int[len/2];
        int right[] = new int[len - len/2];

        System.arraycopy(data, 0, left, 0, len/2);
        System.arraycopy(data, len/2, right, 0, len - len/2);

        // call itself to sort left half
        left = mergeSort(left);
        right = mergeSort(right);
        
        return merge(left, right);
    }

    // Precondition: two input arrays are sorted and they are not null.
    private static int[] merge(int[] left, int[] right) {
        int len = left.length + right.length;

        int[] ret = new int[len];

        int leftPoint = 0;
        int rightPoint = 0;

        int cur = 0;
        while (leftPoint < left.length && rightPoint < right.length) {
            if (left[leftPoint] < right[rightPoint]) {
                ret[cur++] = left[leftPoint++];
            } else {
                ret[cur++] = right[rightPoint++];
            }
        }

        if (leftPoint >= left.length) {
            while (rightPoint < right.length) {
                ret[cur++] = right[rightPoint++];
            }
        } else {
            while (leftPoint < left.length) {
                ret[cur++] = left[leftPoint++];
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] a = new int[SIZE];
        for (int i = 0; i < SIZE; i++)
            a[i] = (int) (Math.random() * SIZE);

        //mergeSort(a);

        int[] test = { 42, 12, 89, 27, 94, 63, 3, 78 };
        System.out.println(Arrays.toString(mergeSort(test)));

        // test merge method
        int[] left = { 12, 42, 63, 89 };
        int[] right = { 3, 27, 78, 94 };
        System.out.println(Arrays.toString(merge(left, right)));

        // test merge method
        int[] left2 = {};
        int[] right2 = { 3, 27, 78, 94 };
        System.out.println(Arrays.toString(merge(left2, right2)));
    }

}