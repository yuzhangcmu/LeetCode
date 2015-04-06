package Algorithms.algorithm.others;
import java.util.LinkedList;


public class FindSame {
    public static LinkedList<Integer> findSameNum(int[] A, int[] B) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        int i = 0,j = 0;
        
        while(true) {
            if (A[i] == B[j]) {
                result.add(A[i]);
                i++;
                j++;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
            
            if (i == A.length || j == B.length) {
                break;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] A = {0,1,2,4,7,8,10};
        int[] B = {2,3,4,5,6,10};
        
        LinkedList<Integer> result = FindSame.findSameNum(A, B);
        System.out.printf(result.toString());
        
        System.out.printf("%d", (-5)%3);
    }
}
