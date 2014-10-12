package Algorithms.algorithm.others;
import java.util.ArrayList;


public class SpiralOrder {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        // remember such kinds of board.
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        spiralOrderhelp(matrix, result, matrix.length, matrix[0].length);
        
        return result;
    }
    
    // m, n: the middle m lines and n columns.
    public void spiralOrderhelp(int[][] matrix, ArrayList<Integer> result, int m, int n) {
        if (m <= 0 || n <= 0) {
            return;
        }
        
        // setup the begin point.
        int lineB = (matrix.length - m)/2;
        int colB = (matrix[0].length - n)/2;
        
        int line = lineB;
        int col = colB-1; // begin from the first element.
        
        while (col < colB+n-1) {
            result.add(matrix[line][++col]);
        }
        
        
        while (line < lineB+m-1) {
            result.add(matrix[++line][col]);
        }
        
        // eliminate the duplicate calculating.
        while (col > colB && line != lineB) {
            result.add(matrix[line][--col]);
        }
        
        // eliminate the duplicate calculating.
        while (line > lineB+1 && col != colB) {
            result.add(matrix[--line][col]);
        }
        
        spiralOrderhelp(matrix, result, m-2, n-2);
    }
    
    public static void main(String[] args) {
        int[][] input = {{2,3}};
        SpiralOrder test = new SpiralOrder();
        ArrayList<Integer> result = test.spiralOrder(input);
        System.out.printf(result.toString());
    }
}
