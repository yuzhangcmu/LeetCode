package Algorithms.hash;

import java.awt.Point;
import java.util.HashMap;

public class MaxPoints {
    public int maxPoints(Point[] points) {
        int max = 0;
        
        if (points == null) {
            return 0;
        }
        
        int len = points.length;
        
        for (int i = 0; i < len; i++) {
            // Create a map to recode all the numbers of elements of every K.
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            
            // ItSelf.
            int dup = 0;
            
            for (int j = i; j < len; j++) {
                // the same point.
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dup++;
                    continue;
                }
                
                double k = Double.MAX_VALUE;
                if (points[i].x != points[j].x) {
                    k = 0 + (double)(points[i].y - points[j].y)/(double)(points[i].x - points[j].x);
                }
                
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + 1);
                } else {
                    map.put(k, 1);
                }
            }
            
            max = Math.max(max, dup);
            for (int n: map.values()) {
                max = Math.max(max, n + dup);
            }
        }
        
        return max;
    }
}