package Algorithms.permutation;

public class NextPermutation {
    public void nextPermutation(int[] num) {
        // There should be at least 2 digitals.        
        if (num == null || num.length <= 1) {
            return;
        }
        
        int len = num.length;
        
        // From the tail to find the first digital which drop
        // example: 1 2 4321
        //            i
        int i = len - 2;
        for (; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                break;
            }
        }
        
        // example: 1 2 4321
        //            i  j
        // then swap i and j.
        // Find the first digital which is bigger than the digital just found.
        // swap: 1 3 4221
        // if i == -1, it means that it should be like: 432, 从尾部往前，没有下降的情况
        if (i != -1) {
            for (int j = len - 1; j > i; j--) {
                if (num[j] > num[i]) {
                    swap(num, i, j);
                    break;
                }
            }    
        }
        
        
        // than just make the 4221 to reverse.
        // result: 1 3 1224
        int left = i + 1;
        int right = len - 1;
        while (left < right) {
            swap(num, left, right);
            left++;
            right--;
        }
    }
    
    // swap the i, j element in the array.
    public void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    
    // Solution 2:
    public void nextPermutation2(int[] num) {
        if (num == null) {
            return;
        }
        
        int len = num.length;
        
        // Find the index which drop.
        int dropIndex = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (i != len - 1 && num[i] < num[i + 1]) {
                dropIndex = i;
                break;
            }
        }
        
        // replace the drop index.
        if (dropIndex != -1) {
            for (int i = len - 1; i >= 0; i--) {
                if (num[i] > num[dropIndex]) {
                    swap(num, dropIndex, i);
                    break;
                }
            }    
        }
        
        // reverse the link.
        int l = dropIndex + 1;
        int r = len - 1;        
        while (l < r) {
            swap(num, l, r);
            l++;
            r--;
        }
    }
}
