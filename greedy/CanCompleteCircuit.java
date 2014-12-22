package Algorithms.greedy;

public class CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
            // Bug 0: should not return false;
            return -1;
        }
        
        int total = 0;
        int sum = 0;
        
        int startIndex = 0;
        
        int len = gas.length;
        for (int i = 0; i < len; i++) {
            int dif = gas[i] - cost[i];
            sum += dif;
            
            if (sum < 0) {
                // Means that from 0 to this gas station, none of them can be the solution.
                startIndex = i + 1; // Begin from the next station.
                sum = 0; // reset the sum.
            }
            
            total += dif;
        }
        
        if (total < 0) {
            return -1;
        }
        
        return startIndex;
    }
}