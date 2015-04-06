package Algorithms.algorithm.others;

public class PartitionArray {
    public static void main(String[] strs) {
        PartitionArray pa = new PartitionArray();
        int A[] = {1,2,6,7,9,3,4,6}; 
        
        System.out.println(pa.partition(A, 0, A.length - 1, 7));
        
        for(int i: A) {
            System.out.println(i);
        }
    }
    
    public int partition(int A[], int start, int end, int key) {
        int left = start;
        int right = end;
        
        while (left < right) {
            while (left < right && A[left] <= key) {
                left++;
            }
            
            while (left < right && A[right] > key) {
                right--;
            }
            
            swap(A, left, right);
        }
        
        
        
        return left;
    }
    
    public void swap(int[] A, int one, int two) {
        int tmp = A[one];
        A[one] = A[two];
        A[two] = tmp;
    }

}
