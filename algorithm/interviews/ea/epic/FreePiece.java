package Algorithms.algorithm.interviews.ea.epic;

public class FreePiece {
    static class Position {
        int x;
        int y;
        public Position(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
    
    static boolean eat = false;
    
    public static void main(String[] strs) {
        int[][] input = {{1, 0, 2, 0},{1, 0, 2, 1},{1, 0, 0, 2}, {0, 1, 2, 0}};
        
        Position result = putPiece(input);
        
        System.out.println("Print position, row:" + result.x + " column:" + result.y + " (Begin from 0)");
    }
    
    public static Position putPiece(int[][] input) {
        Position position = new Position(-1,-1);
        
        String s1 = "1234";
        
        int rows = input.length;
        int cols = input[0].length;
        
        // record the number of potiential 
        int[][] cnt = new int[rows][cols];
        
        // From left to right
        
        for (int i = 0; i < rows; i++) {
            eat = false;
            for (int j = 0; j < cols; j++) {
                cnt[i][j] = 0;
                judge(input, i, j, cnt);
            }
        }
        
        // from right to left
        for (int i = 0; i < rows; i++) {
            eat = false;
            for (int j = cols - 1; j >= 0; j--) {
                judge(input, i, j, cnt);
            }
        }
        
        // from up to down
        for (int j = 0; j < cols; j++) {
            eat = false;
            for (int i = 0; i < rows; i++) {
                judge(input, i, j, cnt);
            }
        }
        
        // from down to up
        for (int j = 0; j < cols; j++) {
            eat = false;
            for (int i = rows - 1; i >= 0; i--) {
                judge(input, i, j, cnt);
            }
        }
        
        int max = 0;
        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < cols; j++) {
                System.out.print(cnt[i][j]);
                if (cnt[i][j] > max) {
                    max = cnt[i][j];
                    position.x = i;
                    position.y = j;
                }
            }
        }
        System.out.println();
        
        return position;
    }
        
    
    public static void judge(int[][] input, int i, int j, int[][] cnt) {
        if (input[i][j] == 2) {
            eat = true;
        } else if (input[i][j] == 1) {
            eat = false;
        } else {
            if (eat) {
                cnt[i][j]++;
            }
        }
    }
}
