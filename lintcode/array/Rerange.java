package Algorithms.lintcode.array;

class Rerange {
    public static void main(String[] strs) {
        int A[] = {1,2,3,4,-1, 2, 5, 5, -5, 6, -7,-8,843,6,1};
        int B[] = {1,2,-3,-4,-1, -2, -5, -5, -5, 6, -7,-8,843,6,1};
        rerange(A);

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        
        rerange(B);
        System.out.println();

        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i] + " ");
        }
    }
   
   /**
     * @param A: An integer array.
     * @return an integer array
     */
    // SOLUTION 1: 判断正数多还是负数多。 
    public static int[] rerange1(int[] A) {
        // write your code here
        
        // Check the input parameter.
        if (A == null || A.length == 0) {
            return A;
        }
        
        int len = A.length;
        
        int left = -1;
        int right = A.length;
        
        // divide the negative and positive integers.
        while (true) {
            while (left < A.length - 1 && A[++left] < 0);
            
            while (left < right && A[--right] > 0);
            
            if (left >= right) {
                break;
            }
            
            swap(A, left, right);
        }
        
        // LEFT: point to the first positive number.
        int negNum = left;
        int posNum = len - left;
        
        int les = Math.min(negNum, posNum);
        int dif = Math.abs(negNum - posNum);
        
        // 如果负数比较多，把多的负数扔到后面去
        if (negNum > posNum) {
            int cnt = dif;
            int l = les;
            int r = len - 1;
            while (cnt > 0) {
                swap(A, l, r);
                l++;
                r--;
                cnt--;
            }
            
            // 负数多的时候，负数在前，反之，正数在前
            left = -1;
            // 跳过右边不需要交换的值
            right = A.length - dif;
        } else {
            // 正数在前
            left = -2;
            // 跳过右边不需要交换的值
            right = A.length - dif + 1;
        }
        
        /*
          -1 -2 -5 -6  3  4  les = 2;
          4  -2 -5 -6  3 -1
        */
        // swap the negative and the positive
        while (true) {
            left += 2;
            right -= 2;
            
            if (left >= les) {
                break;
            }
            swap(A, left, right);
        }

        return A;
   }
   
   public static void swap(int[] A, int n1, int n2) {
       int tmp = A[n1];
       A[n1] = A[n2];
       A[n2] = tmp;
   }
   
   /*
   Solution 2:
   */
   public static int[] rerange2(int[] A) {
        // write your code here
        
        // Check the input parameter.
        if (A == null || A.length <= 2) {
            return A;
        }
        
        int len = A.length;
        
        int cntPositive = 0;
        
        for (int num: A) {
            if (num > 0) {
                cntPositive++;    
            }
        }
        
        // If positive numbers are more than negative numbers,
        // Put the positive numbers at first.
        int posPointer = 1;
        int negPointer = 0;
        
        // means 
        boolean pos = false;
        
        if (cntPositive > A.length / 2) {
            // Have more Positive numbers;
            posPointer = 0;
            negPointer = 1;
            
            pos = true;
        }
        
        int i = 1;
        int j = len - 2;
        
        if (pos) {
            while (true) {
                // Put the positive numbers at the end.
                if (i < len && A[i] < 0) {
                    i += 2;
                }
                
                if (j > i && A[j] > 0) {
                    j -= 2;
                }
                
                if (i >= j) {
                    break;
                }
                
                swap(A, i, j);
            }
        } else {
            while (true) {
                // Put the negative numbers at the end.
                if (i < len && A[i] > 0) {
                    i += 2;
                }
                
                if (j > i && A[j] < 0) {
                    j -= 2;
                }
                
                if (i >= j) {
                    break;
                }
                
                swap(A, i, j);
            }
        }
        
        // Reorder the negative and the positive numbers.
        while (true) {
            // Should move if it is in the range.
            while (posPointer < len && A[posPointer] > 0) {
                posPointer += 2;
            }
            
            // Should move if it is in the range.
            while (negPointer < len && A[negPointer] < 0) {
                negPointer += 2;
            }
            
            if (posPointer >= len || negPointer >= len) {
                break;
            }
            
            swap(A, posPointer, negPointer);
        }
        
        return A;
   }
   
   /*
   Solution 3:
   */
   public static int[] rerange3(int[] A) {
        // write your code here
        
        // Check the input parameter.
        if (A == null || A.length <= 2) {
            return A;
        }
        
        int len = A.length;
        
        int cntPositive = 0;
        
        // store the positive numbers index.
        int i1 = 0;
        
        for (int i2 = 0; i2 < len; i2++) {
            if (A[i2] > 0) {
                cntPositive++;
                
                // Put all the positive numbers at in the left part.
                swap(A, i1++, i2);
            }
        }
        
        // If positive numbers are more than negative numbers,
        // Put the positive numbers at first.
        int posPointer = 1;
        int negPointer = 0;
        
        if (cntPositive > A.length / 2) {
            // Have more Positive numbers;
            posPointer = 0;
            negPointer = 1;
            
            // Reverse the array.
            int left = 0;
            int right = len -1;
            while (left < right) {
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
                left++;
                right--;
            }
        }
        
        // Reorder the negative and the positive numbers.
        while (true) {
            // Should move if it is in the range.
            while (posPointer < len && A[posPointer] > 0) {
                posPointer += 2;
            }
            
            // Should move if it is in the range.
            while (negPointer < len && A[negPointer] < 0) {
                negPointer += 2;
            }
            
            if (posPointer >= len || negPointer >= len) {
                break;
            }
            
            swap(A, posPointer, negPointer);
        }
        
        return A;
   }
   
   /*
   Solution 4:
   把reverse的步骤简化了一下
   */
   public static int[] rerange(int[] A) {
        // write your code here
        
        // Check the input parameter.
        if (A == null || A.length <= 2) {
            return A;
        }
        
        int len = A.length;
        
        int cntPositive = 0;
        
        // store the positive numbers index.
        int i1 = 0;
        
        for (int i2 = 0; i2 < len; i2++) {
            if (A[i2] > 0) {
                cntPositive++;
                
                // Put all the positive numbers at in the left part.
                swap(A, i1++, i2);
            }
        }
        
        // If positive numbers are more than negative numbers,
        // Put the positive numbers at first.
        int posPointer = 1;
        int negPointer = 0;
        
        if (cntPositive > A.length / 2) {
            // Have more Positive numbers;
            posPointer = 0;
            negPointer = 1;
            
            // Reverse the array.
            int left = 0;
            int right = len -1;
            while (right >= cntPositive) {
                swap(A, left, right);
                left++;
                right--;
            }
        }
        
        // Reorder the negative and the positive numbers.
        while (true) {
            // Should move if it is in the range.
            while (posPointer < len && A[posPointer] > 0) {
                posPointer += 2;
            }
            
            // Should move if it is in the range.
            while (negPointer < len && A[negPointer] < 0) {
                negPointer += 2;
            }
            
            if (posPointer >= len || negPointer >= len) {
                break;
            }
            
            swap(A, posPointer, negPointer);
        }
        
        return A;
   }
}
