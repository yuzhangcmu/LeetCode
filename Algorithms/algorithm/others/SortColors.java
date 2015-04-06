package Algorithms.algorithm.others;
import java.util.Collection;
import java.util.LinkedList;

public class SortColors {
    public void sortColors(int[] A) {
        
        int red = 0; 
        int curr = 0;
        int blue = A.length - 1;
        
        while(curr <= blue) {
            /*
            while (A[blue] == 2 && curr < blue) {
                blue--;
            }
            
            while (A[red] == 0 && curr < blue) {
                red ++;
            }
            
            if (red >= curr){
                curr = red + 1;
            }*/
            
            if (A[curr] == 0) {
                swap(A, curr, red);
                curr++;
                red++;
            } else if (A[curr] == 1) {
                curr++;
            } else {
                swap(A, curr, blue);
                blue--;
            }
        }
        
    }
    
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static void main(String[] args){
        SortColors sort = new SortColors();
        
        int A[] = {1,0};
        sort.sortColors(A);
        
        //Collection a = new LinkedList(A);
        
        System.out.printf("%d %d",A[0], A[1]);
        
    }
}