package Algorithms.array;

public class FindMedianSortedArrays {
    public static void main(String[] strs) {
        int A[] = {1, 2};
        int B[] = {1, 2};
        
        System.out.println(findMedianSortedArrays(A, B));
    }
    
    public static double findMedianSortedArrays1(int A[], int B[]) {
        if (A == null || B == null) {
            return 0;
        }
        
        int len = A.length + B.length;
        
        double ret = 0;
        // 偶数个元素
        if (len % 2 == 0) {
            ret = (findKth(A, B, 0, 0, len / 2) + findKth(A, B, 0, 0, len / 2 + 1)) / (double)2.0;
        } else {
            // 奇数个元素
            ret = findKth(A, B, 0, 0, len / 2 + 1);            
        }
        
        return ret;
    }
    
    // Find the Kth large number.
    public static int findKth(int A[], int B[], int indexA, int indexB, int k) {
        int lenA = A.length;
        int lenB = B.length;
        
        if (indexA >= lenA) {
            return B[indexB + k - 1];
        } else if (indexB >= lenB) {
            return A[indexA + k - 1];
        }
        
        // Base Case, pay attention. 在这里必须要退出。因为k = 1的时候，不可能再分了。
        if (k == 1) {
            return Math.min(A[indexA], B[indexB]);
        }
        
        // -1是因为索引本身是从0开始的。而前k大元素含有k个元素。
        int mid = k / 2 - 1;
        
        // 注意，越界条件是 >= lenA. 怎么老是犯这个错误。。
        int keyA = indexA + mid >= lenA ? Integer.MAX_VALUE: A[indexA + mid];
        int keyB = indexB + mid >= lenB ? Integer.MAX_VALUE: B[indexB + mid];
        
        // 因为丢弃了k / 2个元素
        int kNew = k - k / 2;
        
        if (keyA < keyB) {
            return findKth(A, B, indexA + k / 2, indexB, kNew);
        } else {
            return findKth(A, B, indexA, indexB + k / 2, kNew);
        }
    }
    
    public static double findMedianSortedArrays(int A[], int B[]) {
        //2257
        if (A == null || B == null) {
            return 0;
        }
        
        int len = A.length + B.length;
        if (len % 2 == 0) {
            return (double)(dfs(A, B, 0, 0, len / 2) + dfs(A, B, 0, 0, len / 2 + 1)) / 2.0;
        } else {
            return dfs(A, B, 0, 0, len / 2 + 1);
        }
    }
    
    public static double dfs(int A[], int B[], int aStart, int bStart, int k) {
        if (aStart >= A.length) {
            return B[bStart + k - 1];
        } else if (bStart >= B.length) {
            return A[aStart + k - 1];
        }
        
        if (k == 1) {
            return Math.min(A[aStart], B[bStart]);
        }
        
        // k = 4;
        // mid = 1;
        int mid = k / 2 - 1;
        
        if (aStart + mid >= A.length) {
            // drop the left side of B.
            return dfs(A, B, aStart, bStart + k / 2, k - k / 2);
        } else if (bStart + mid >= B.length) {
            // drop the left side of A.
            return dfs(A, B, aStart + k / 2, bStart, k - k / 2);
        } else if (A[aStart + mid] > B[bStart + mid]) {
            // drop the left side of B.
            return dfs(A, B, aStart, bStart + k / 2, k - k / 2);
        } else if (A[aStart + mid] < B[bStart + mid]) {
            // drop the left side of A.
            return dfs(A, B, aStart + k / 2, bStart, k - k / 2);
        }
        
        return A[aStart + mid];        
    }
}
