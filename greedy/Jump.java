package Algorithms.greedy;

public class Jump {
    public static void main(String[] strs) {
        int[] A = {2, 3, 1, 1, 4};
        System.out.println(jump(A));
    }
    
    public static int jump1(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int len = A.length;

        int sum = 0;

        int des = len - 1;
        while (des > 0) { // destination index
            for (int i = 0; i < des; i++) { // 不断向前移动dest
                if (A[i] + i >= des) { // 说明从i位置能1步到达dest的位置
                    sum++;
                    des = i; // 更新dest位置，下一步就是计算要几步能调到当前i的位置
                    //break; // 没必要再继续找，因为越早找到的i肯定越靠前，说明这一跳的距离越远
                             // 这一行可以去掉， des = i，不符合for的条件，会自动break.
                    System.out.println("sum:" + sum);
                    System.out.println("des:" + des);
                }
            }
        }

        return sum;
    }
    
    // solution 2: one pass greedy.
    public static int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;    
        }
        
        // bug:
        /*
        Input:  [1]
        Output: 1
        Expected:   0
        */
        if (A.length == 1) {
            return 0;
        }
        
        int len = A.length;
        
        int start = 0;
        int end = 0;
        
        int steps = 0;
        while (end < len - 1) {
            int max = 0;
            steps++;
            for (int i = start; i <= end; i++) {
                max = Math.max(max, i + A[i]);
                
                if (max >= len - 1) {
                    return steps;
                }
            }
            
            start = end + 1;
            end = max;
            
            if (start > end) {
                break;
            }
        }
        
        return Integer.MAX_VALUE;
    }
}