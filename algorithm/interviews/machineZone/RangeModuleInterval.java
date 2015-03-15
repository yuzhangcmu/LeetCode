// A Range Module is a module that tracks ranges of numbers.
// Your task is to design and implement an efficient version of
// this module that has less space and time complexity. Yes,
// of course, there can multiple implementations of this, but we
// are not looking at a single one. Make sure you choose the right
// data­structures so that your implementation is efficient.

package Algorithms.algorithm.interviews.machineZone;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Data structure: Using ArrayList, this can be random access and pretty fast.
 * 
 * AddRange: 
 * Add into the sorted array. 
 * 1. Consider if we should merge the left and the right interval of the new interval
 * 2. Delete the intervals that will be covered by the new interval.
 * 3. Merge the left and the right interval into the new interval.

 * QueryRange: 
 * We can use Binary search to make it fast.
 * Then we try to find out if the interval is covered by the current one.
 * 
 * DeleteRange:
 * 1. Resize the left interval before the target interval.
 * 2. Delete all the intervals covered.
 * 3. Resize the right interval after the target interval. 
 */

public class RangeModuleInterval
{
    public class Interval implements Comparable<Interval>{
        int lower;
        int upper;
        
        public Interval(int lower, int upper) {
            super();
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public int compareTo(Interval o) {
            // TODO Auto-generated method stub
            return this.lower - o.lower;
        }
    }
    
    /*
     * data structure: Two Arraylists
     * addrange: add into the sorted array. Every time, consider if left and right intervals are merged,
     * */
    private ArrayList<Interval> intervals;
    
    public RangeModuleInterval() {
        intervals = new ArrayList<Interval>();
    }
    
    private int get_numberOfRanges() {
        return intervals.size();
    }
    
    // AddRange: Given an input range it starts tracking the range.
    // Eg: AddRange(10, 200) – starts tracking range 10 – 200
    // AddRange(150, 180) – starts tracking range 150 – 180.
    // AddRange(250, 500) – starts tracking range 250 – 500.
    // Make sure that you efficiently track overlapping ranges.
    
    /*
    Addrange: 
        * Add into the sorted array. 
        * 1. Consider if we should merge the left and the right interval of the new interval
        * 2. Delete the intervals that will be covered by the new interval.
        * 3. Merge the left and the right interval into the new interval.
        * */
    public void AddRange(int lower, int upper)
    {
        // The lower should be smaller than the upper.
        if (lower > upper) {
            return;
        }
        
        int size = get_numberOfRanges();
        
        Interval intervalNew = new Interval(lower, upper);
        
        int index = Collections.binarySearch(intervals, intervalNew);
        
        // When we can't find the target in the array using BinarySearch,
        // The function will return a negative value. So we should get the 
        // insertion position using " -lowerInsertIndex - 1"
        if (index < 0) {
            index = -index - 1;
        }
        
        for (int i = Math.max(0, index - 1); i < size; i++) {
            Interval cur = intervals.get(i);

            // The current one is on the right.
            if (cur.lower > intervalNew.upper) {
                break;
            } else if (cur.upper < intervalNew.lower) {
                // The current one is on the left.
                continue;
            } else {
                // They are overlap.
                intervalNew.lower = Math.min(intervalNew.lower, cur.lower);
                intervalNew.upper = Math.max(intervalNew.upper, cur.upper);
                
                // if merge the left interval, we should change the insert position.
                if (i == index - 1) {
                    index--;
                }
                
                // Delete the old one.
                intervals.remove(i);
                
                // Continue probe the current index.
                i--;
                size--;
            }
        }
        
        // Add the new interval.
        intervals.add(index, intervalNew);
    }

    // QueryRange: Given an input range, this returns whether the range
    // is being tracked or not. Eg: QueryRange(50, 100) –
    // Returns TRUE as this is being tracked
    // QueryRange(180, 300) – Returns False as only a partial of this range
    // is being tracked QueryRange(600, 1000) – Returns False as this range is not tracked
    
    /*
     * Query: 
     * We can use Binary search to make it fast.
     * Then we try to find out if the interval is covered by the current one.
     * */
    public boolean QueryRange(int lower, int upper)
    {
        // invalid input.
        if (lower > upper) {
            return false;
        }
        
        int index = Collections.binarySearch(intervals, new Interval(lower, upper));
        
        if (index < 0) {
            index = -index - 1;
        }
            
        // Change the compare index.
        if (index == get_numberOfRanges()) {
            index--;
        }
        
        // if the lower bound id smaller than the current one, compare it to the last interval.
        if (lower < intervals.get(index).lower) {
            index = Math.max(0, index - 1);
        }
        
        Interval cur = intervals.get(index);
        
        // The compare interval should cover the target one.
        return cur.lower <= lower && cur.upper >= upper;
    }

    // DeleteRange: Given input range is untracked after this call has been made.
    // If the range does not exists then it is a no­op.
    // Eg: DeleteRange(50, 150) – stops tracking range 50 – 150
    
    /*
    DeleteRange:
        * 1. Resize the left interval before the target interval.
        * 2. Delete all the intervals covered.
        * 3. Resize the right interval after the target interval.
        */ 
    public void DeleteRange(int lower, int upper)
    {
        // Invalid input
        if (lower > upper) {
            return;
        }
        
        int size = get_numberOfRanges();
        
        int index = Collections.binarySearch(intervals, new Interval(lower, upper));
        
        // Recover the position.
        if(index < 0) {
            index = -index - 1;
        }
            
        for (int i = Math.max(0, index - 1); i < size; i++) {
            Interval cur = intervals.get(i);

            // The current one is on the right.
            if (cur.lower > upper) {
                break;
            } else if (cur.upper < lower) {
                // The current one is on the left.
                continue;
            } else if (cur.lower >= lower && cur.upper <= upper) {
                // The covered intervals.
                intervals.remove(i);
                i--;
                size--;
            } else {
                if (cur.lower < lower && cur.upper > upper) {
                    // cut the current to two parts.
                    intervals.add(i + 1, new Interval(upper + 1, cur.upper));
                    cur.upper = lower - 1;
                    break;
                } else if (cur.lower < lower) {
                    cur.upper = lower - 1;
                } else {
                    // The current one is on the right, resize the left bound.
                    cur.lower = upper + 1;
                }
            }
        }
    }
    
    public void print(){
        System.out.println("Print out the range:");
        int size = this.get_numberOfRanges();
        for(int i = 0;i < size;i++){
            System.out.println(intervals.get(i).lower + " " + intervals.get(i).upper);
        }
    }
    
    public static void main(String[] args) {
        RangeModuleInterval ranges = new RangeModuleInterval();
        ranges.AddRange(0, 2);
        ranges.AddRange(6, 9);
        ranges.AddRange(15, 15);
        ranges.AddRange(19, 21);
        ranges.AddRange(16,18);
//        
        ranges.AddRange(4,17);
        ranges.print();
        
        System.out.println(ranges.QueryRange(16, 18));
        System.out.println("Delete Range");
        ranges.DeleteRange(12, 12);
        ranges.print();
    }

    // You do NOT need to make Range module persistent (writable/readable on disk).

    // In­memory implementation is fine.
    // You do NOT need to submit a test program that uses these APIs . However, feel free
    // to do so if it would be helpful to you in designing or debugging the API.

    // Make sure the work that you submit compiles. It is NOT mandatory that you implement
    // all the interfaces. But make sure that your design and choice of data structure is
    // good enough to implement all interfaces if you had considerably more time.

    // You may use the C standard library, but no other libraries.
    // All source code must be your own.
    // Please strive for:
    // 1. Clean design—Make your code readable and reuseable where possible; give thought
    // to your interface
    // 2. Efficiency—Try to make each functionality as fast as possible
    // 3. Robustness—Handle error sensibly; handle large or unusual sets of words.

    // Please briefly explain your design choices in your comments.

};
