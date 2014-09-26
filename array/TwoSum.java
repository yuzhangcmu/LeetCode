package Algorithms.array;
import java.util.HashMap;


public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int[] rst = new int[2];
         
        int len = numbers.length;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        for (int i = 0; i< len; i++) {
            Integer j = hm.get(target-numbers[i]);
            if ((j = hm.get(target-numbers[i])) != null) {
                rst[0] = j+1;
                rst[1] = i+1;
                return rst;
            }
            
            hm.put(numbers[i], i);
        }
        
        return rst;
    }

}
