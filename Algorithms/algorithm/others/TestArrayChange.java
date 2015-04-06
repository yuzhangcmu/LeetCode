package Algorithms.algorithm.others;

public class TestArrayChange {
    public static void main(String[] args) {
        char[][] A = {
                {'1','2'},
                {'3','4'}
        };
        TestArrayChange.changeValue(A);
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(A[i][j]);
            }
            System.out.println();
        }
    }

    public static void changeValue(char[][] A) {
        A[0][0] = '5';
        return;
    }
}
