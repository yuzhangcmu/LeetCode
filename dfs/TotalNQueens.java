package Algorithms.dfs;

import java.util.ArrayList;

public class TotalNQueens {
    public int totalNQueens(int n) {
        if (n == 0) {
            return 0;
        }

        ArrayList<Integer> cols = new ArrayList<Integer>();
        
        return dfs(n, cols, 0); 
    }
    
    public int dfs(int n, ArrayList<Integer> cols, int row) {
        // 如果row 超过范围，返回1
        // base case 已经完成任务，应该是1种解法 因为前面已经固定，现在也不能再放了
        if (row == n) {
            return 1;
        }

        int sum = 0;

        // 在当前行，尝试放置棋子
        for (int i = 0; i < n; i++) {
            // 不能放的位置 跳过
            if (!isValid(cols, i)) {
                continue;
            }

            cols.add(i);
            sum += dfs(n, cols, row + 1);
            cols.remove(cols.size() - 1);
        }

        return sum;
    }

    public boolean isValid(ArrayList<Integer> cols, int col) {
        for (int i = 0; i < cols.size(); i++) {
            if (col == cols.get(i)) {
                // same column.
                return false;
            }

            // diagonal. From the left up to the right down.
            if (cols.size() - i == Math.abs(col - cols.get(i))) {
                return false;
            }
        }

        return true;
    }
}