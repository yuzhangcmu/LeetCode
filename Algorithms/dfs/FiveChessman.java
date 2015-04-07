package Algorithms.dfs;

/*
 * 输入一个19*19的矩阵，只包含数字0、1、2，表示两人下五子棋的棋牌状态，1、2分别表示两人的棋子，0表示空格。
 * 要求判断当前状态下是否有人获胜（横向、竖向或者斜线方向连成5个同色棋子）。题目说明输入样例保证每条线上至多
 * 只有连续5个同色棋子，并且保证至多只有1人获胜。如果有人获胜，输出获胜者（1或2）加一个冒号，
 * 接着输出获胜的五连珠的第一个棋子的坐标，从上到下从左到右序号最小的为第一个，序号从1开始编号。如果无人获胜，输出no。
 * */
public class FiveChessman {
    private static class Coordinate {
        int col;
        int row;
        
        public Coordinate (int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    public static class Visit {
        boolean right;
        boolean down;
        boolean rightDown;
        boolean leftDown;
        
        public Visit (boolean right, boolean down, boolean rightDown, boolean leftDown) {
            this.right = right;
            this.down = down;
            this.rightDown = rightDown;
            this.leftDown = leftDown;
        }
    }
    
    public static void main(String[] args) {
        int[][] input = {
          {1, 0, 0, 0, 1, 0},
          {0, 1, 0, 1, 0, 2},
          {0, 0, 1, 0, 2, 0},
          {0, 1, 0, 2, 0, 0},
          {0, 0, 2, 0, 1, 0},
          {0, 2, 0, 0, 0, 0}          
        };
        
        Coordinate result = findWin(input);
        if (result != null) {
            System.out.println("" + result.row + " " + result.col);
        }
        
    }
    
    // in 代表棋盘
    public static Coordinate findWin(int[][] in) {
        if (in == null || in.length == 0) {
            return null;
        }
        
        int row = in.length;
        int col = in[0].length;

        Visit[][] visit = new Visit[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // try to get the result from 4 directions.
                for (int k = 0; k < 4; k++) {
                    visit[i][j] = new Visit(false, false, false, false);
                    Coordinate ret = findWinHelp(in, j, i, in[i][j], k, visit, 0);
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }     
        
        return null;
    }

    // row, col 代表我们现在正在扫描的点的坐标 
    // side 记录目前是哪一方的棋子
    // direction 记录扫描方向，有四个方向扫描：右，右下，左下，下。 
    // num: 代表扫描到第几个棋子了。
    public static Coordinate findWinHelp(int[][] in, int col, int row, int side, int direction, Visit[][] visit, int num) {
        // 扫描不可以超过范围
        if (col >= in.length || row >= in.length || row < 0 || col < 0) {
            return null;
        }

        // if it is 0, just return.
        if (in[row][col] == 0) {
            return null;
        }

        // 必须是同边的棋子。例如，都是1，或都是2.
        if (side != in[row][col]) {
            return null;
        }

        // we get the result.
        if (num == 4) {
            return new Coordinate(col, row);
        }
        
        // 如果未设置标记，新建标记。
        if (visit[row][col] == null) {
            visit[row][col] = new Visit(false, false, false, false);
        }

        // 对各个方向进行不同的搜索方向。 
        if (direction ==  0) {
            if (visit[row][col].right) {
                return null;
            }
            visit[row][col].right = true;

            // right
            return findWinHelp(in, col + 1, row, side, direction, visit, num + 1);
        } else if (direction == 1) {
            if (visit[row][col].down) {
                return null;
            }

            visit[row][col].down = true;

            // down
            return findWinHelp(in, col, row + 1, side, direction, visit, num + 1);
        } else if (direction == 2) {
            if (visit[row][col].rightDown) {
                return null;
            }

            visit[row][col].rightDown = true;

            // right down
            return findWinHelp(in, col + 1, row + 1, side, direction, visit, num + 1);
        } else if (direction == 3) {
            if (visit[row][col].leftDown) {
                return null;
            }

            visit[row][col].leftDown = true;

            // left down
            return findWinHelp(in, col - 1, row + 1, side, direction, visit, num + 1);
        }
        
        return null;
    }
}
