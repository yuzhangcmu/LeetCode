package Algorithms.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Merge {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            return ret;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                // sort the intervals by the start.
                return o1.start - o2.start;
            }
        });
        
        // 作为最后一个插入的区间
        Interval last = intervals.get(0);
        
        // 这里要考虑性能。使用iterator的话，对linkedlist会更快.        
        Iterator<Interval> itor = intervals.iterator();
        while (itor.hasNext()) {
            Interval cur = itor.next();
            // cur 在last的右边
            if (cur.start > last.end) {
                // 将cur作为新的last.
                ret.add(last);
                last = cur;
            // cur与last有重合的部分，合并之    
            } else {
                int s = last.start;
                int e = Math.max(last.end, cur.end);
                last = new Interval(s, e);
            }
        }
        
        // 把最后一个区间加上
        ret.add(last);
        
        return ret;
    }
}