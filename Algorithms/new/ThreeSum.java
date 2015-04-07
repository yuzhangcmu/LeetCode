import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ThreeSum {
	public static void main(String[] str) {
		int[] num = {0,0,0,0};
		System.out.println(threeSum(num));
	}
	
    public static List<List<Integer>> threeSum(int[] num) {
        ArrayList<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return ret;
        }
        
        int len = num.length;
        
        // sort the array to get unique solutions.
        Arrays.sort(num);
        for (int i = 0; i < len - 2; i++) {
            // skip the duplicate numbers.
            while (i != 0 && num[i] == num[i - 1]) {
                continue;
            }
            
            int n2 = i + 1;
            int n3 = len - 1;
            
            while (n2 < n3) {
                int sum = num[i] + num[n2] + num[n3];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    list.add(n2);
                    list.add(n3);
                    ret.add(list);
                    
                    n2++;
                    n3--;
                    
                    // skip the duplication.
                    while (n2 < len - 1 && num[n2] == num[n2 - 1]) {
                        n2++;
                    }
                    
                    while (n3 > i && num[n3] == num[n3 + 1]) {
                        n3--;
                    }
                } else if (sum > 0) {
                    n3--;
                } else {
                    n2++;
                }
            }
        }
        
        return ret;
    }
}