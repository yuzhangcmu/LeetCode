package Algorithms.algorithm.sort;

import java.util.Arrays;

/*
 * 给定一个未排序的数组，请给出波浪状排序：
 * Example:
 * input:   1 2 5 4 3 9
 * output:  1 4 3 2 5 9
 * 
 * input:   1 2 2 5 3 9
 * output:  1 5 2 3 2 9
 * */
public class WaveSort {
	public static void main(String[] str) {
		int[] in = {1,2,5,4,3,9, 12};

		for (int i: in) {
			System.out.print(i + " ");	
		}
		System.out.println();
		
		waveSort(in);
		for (int i: in) {
            System.out.print(i + " ");  
        }
	}

	public static void waveSort(int[] in) {
	    if (in == null) {
	        return;
	    }
	    
	    // there should be at least 3 numbers.
	    if (in.length <= 2) {
	        return;
	    }
	    
	    int len = in.length;
	    
	    Arrays.sort(in);
	    
	    for (int i = 0; i < len; i++) {
	        if (i % 2 == 0) {
	            if (i + 1 < len) {
	                int tmp = in[i];
	                in[i] = in[i + 1];
	                in[i + 1] = tmp;
	            }
	        }
	    }
	}
	
	public static void findKthNumber(int[] in, int k) {
	    
	}
}
