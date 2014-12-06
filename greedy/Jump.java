package Algorithms.greedy;

public class Jump {
    public int jump(int[] A) {
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
                    break; // 没必要再继续找，因为越早找到的i肯定越靠前，说明这一跳的距离越远
                }
            }
        }

        return sum;
    }
}