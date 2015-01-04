package Algorithms.binarySearch;

public class FindPeakElement {
    public int findPeakElement1(int[] num) {
        if (num == null) {
            return 0;
        }
        
        if (num.length == 1) {
            return 0;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (i == 0) {
                if (num[i] > num[i + 1]) {
                    return i;
                }
                continue;
            }
            
            if (i == num.length - 1) {
                if (num[i] > num[i - 1]) {
                    return i;
                }
                continue;
            }
            
            if (num[i] > num[i + 1] && num[i] > num[i - 1]) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int findPeakElement(int[] num) {
        if (num == null) {
            return 0;
        }
        
        if (num.length == 1) {
            return 0;
        }
        
        int l = 0;
        int r = num.length - 1;
        
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (num[mid] > num[mid + 1] && num[mid] > num[mid - 1]) {
                return mid;
            }
            
            if (num[mid] > num[mid - 1] && num[mid] < num[mid + 1]) {
                // rising area. move right;
                l = mid;
            } else if (num[mid] < num[mid - 1] && num[mid] > num[mid + 1]) {
                r = mid;
            } else {
                l = mid;                
            }
        }
        
        return num[l] > num[r] ? l: r;
    }
}