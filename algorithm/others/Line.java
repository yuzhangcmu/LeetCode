package Algorithms.algorithm.others;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;



public class Line {
    
    /*
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null) {
                return -1;
            }
            return left.val - right.val;
        }
    };*/
    
    public class  ListNodeComparator implements Comparator<ListNode>{
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null) {
                return -1;
            }
            return left.val - right.val;
        }
    }
    
    public static void main(String[] args) {
        Line li = new Line();
        
        Point p1 = new Point(2, 3);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(-5, 3);
        
        Point[] p = {p1, p2, p3};
        
        li.maxPoints(p);
        
        Line aa = new Line();
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(2, aa.new ListNodeComparator());
        
    }
    
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        
        Queue<ListNode> heap = new PriorityQueue<ListNode>(2, new ListNodeComparator());
        
        double k;
        int dup = 0;
        int max = 0;
        
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> map = new HashMap<Double, Integer>();  
            
            // the point itself.
            dup = 1;
            
            for (int j = i + 1; j < points.length; j++) {
                if (points[j].y == points[i].y && points[i].x == points[j].x) {
                    // the same point with the point.
                    dup++;
                    continue;
                }
                
                // count the K.
                if (points[j].x == points[i].x) {
                    k = Double.MAX_VALUE;
                } else {
                    // because there is +0 and -0.... so add 0 to it.
                    k = (double)(points[j].y - points[i].y)/(double)(points[j].x - points[i].x);
                    if (k == 0) {
                        k = 1;
                    }
                    if (-0 == 0){
                        k = 1;
                    }
                }
                
                if (map.containsKey(k)) {
                    map.put(k, map.get(k) + 1);
                } else {
                    // the line has at least 2 numbers.
                    map.put(k, 1);
                }
            }
            
            for (int n: map.values()) {
                if (n + dup > max) {
                    max = n + dup;
                }
            }
            
            max = Math.max(max, dup);
        }
        
        return max;
    }
}
