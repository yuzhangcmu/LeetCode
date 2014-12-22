package Algorithms.lintcode.array;

class SortKColors {
    public static void main(String[] strs) {
        int A[] = {2,2,3,1,1,1,2,3,2,2};
        sortKColors(A, 3);
        
        for (int i = 0; i < A.length; i++) {
            System.out.print(" " + A[i]);
        }
    }
    
    
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    /*
    Solution 1: Using the quick sort.
    */ 
    public static void sortKColors1(int[] colors, int k) {
        // write your code here
        if (colors == null) {
            return;
        }
        
        quickSort(colors, 0, colors.length - 1);
    }
    
    public static void quickSort(int[] colors, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int pivot = colors[right];
        
        int pos = partition(colors, left, right, pivot);
        
        quickSort(colors, left, pos - 1);
        quickSort(colors, pos + 1, right);
    }
    
    public static int partition(int[] colors, int left, int right, int pivot) {
        int leftPoint = left - 1;
        int rightPoint = right;
        
        while (true) {
            while (colors[++leftPoint] < pivot);
            
            while (leftPoint < rightPoint && colors[--rightPoint] > pivot);
            
            if (leftPoint >= rightPoint) {
                break;
            }
            
            swap(colors, leftPoint, rightPoint);
        }
        
        swap(colors, leftPoint, right);
        return leftPoint;
    }
    
    public static void swap(int[] colors, int left, int right) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }
    
    // Solution 2: inplace, O(n) 
    public static void sortKColors(int[] colors, int k) {
        // write your code here
        if (colors == null) {
            return;
        }
        
        int len = colors.length;
        for (int i = 0; i < len; i++) {
            // Means need to deal with A[i]
            while (colors[i] > 0) {
                int num = colors[i];
                if (colors[num - 1] > 0) {    
                    // 1. There is a number in the bucket, 
                    // Store the number in the bucket in position i;
                    colors[i] = colors[num - 1];
                    colors[num - 1] = -1;
                } else if (colors[num - 1] < 0) {
                    // 2. Bucket is using.
                    colors[num - 1]--;
                    // delete the A[i];
                    colors[i] = 0;
                } else if (colors[num - 1] == 0) {
                    // 3. The bucket is empty.
                    colors[num - 1] = -1;
                    // delete the A[i];
                    colors[i] = 0;
                }
            }
        }
        
        int index = len - 1;
        for (int i = k - 1; i >= 0; i--) {
            int cnt = -colors[i];
            
            // Empty number.
            if (cnt == 0) {
                continue;
            }
                                
            while (cnt > 0) {
                colors[index--] = i + 1;
                cnt--;
            }
        }
    }
}

