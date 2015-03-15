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

public class RangeModule
{    
    /*
     * data structure: Two Arraylists
     * addrange: add into the sorted array. Every time, consider if left and right intervals are merged,
     * */
    private ArrayList<Integer> lowers;
    private ArrayList<Integer> uppers;
    
    public RangeModule() {
        lowers = new ArrayList<Integer>();
        uppers = new ArrayList<Integer>();
    }
    
    private int get_numberOfRanges() {
        return lowers.size();
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
        
        int counts = get_numberOfRanges();
        
        int lowerInsertIndex = Collections.binarySearch(lowers, lower);
        int upperInsertIndex = Collections.binarySearch(uppers, upper);
        
        // When we can't find the target in the array using BinarySearch,
        // The function will return a negative value. So we should get the 
        // insertion position using " -lowerInsertIndex - 1"
        if (lowerInsertIndex < 0) {
            lowerInsertIndex = -lowerInsertIndex - 1;
        }
        
        if (upperInsertIndex < 0) {
            upperInsertIndex = -upperInsertIndex - 1;
        }
        
        boolean lowerMerge = false;
        boolean upperMerge = false;
        
        if (lowerInsertIndex - 1 >= 0 
                && lowerInsertIndex - 1 < counts 
                && uppers.get(lowerInsertIndex - 1) >= lower) {
            // It means that we should merge the interval in 
            // the left of the inserted interval.
            lowerMerge = true;
        }
        
        if (upperInsertIndex < counts 
                && lowers.get(upperInsertIndex) <= upper) {
            // It means that we should merge the interval in 
            // the right of the inserted interval.
            upperMerge = true;
        }
        
        // Count how many intervals should be deleted:
        // They are covered by the new interval.
        for (int i = 0; i < upperInsertIndex - lowerInsertIndex; i++) {
            lowers.remove(lowerInsertIndex);
            uppers.remove(lowerInsertIndex);
        }
        
        // Add the new Interval. lowerInsertIndex is the position to be insert.
        lowers.add(lowerInsertIndex, lower);
        uppers.add(lowerInsertIndex, upper);
        
        // Merge the interval in the left of the new interval.
        if (lowerMerge) {
            lowers.remove(lowerInsertIndex);
            uppers.remove(lowerInsertIndex - 1);
            
            // Because delete a interval, so when you try to delete the right interval, the index should be 
            // shifted to left one step.
            lowerInsertIndex--;
        }
        
        // Merge the interval in the right of the new interval.
        if (upperMerge) {
            lowers.remove(lowerInsertIndex + 1);
            uppers.remove(lowerInsertIndex);
        }
        
        
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
        
        int size = get_numberOfRanges();
        
        int lowerPosition = Collections.binarySearch(lowers, lower);
        
        if (lowerPosition < 0) {
            lowerPosition = -lowerPosition - 1;
        }
            
        // The lower is out of bound, try to find the last interval.
        if(lowerPosition == size) {
            return upper <= uppers.get(lowerPosition - 1);
        }
            
        // just the same start with one interval
        if(lowers.get(lowerPosition) == lower) {
            return upper <= uppers.get(lowerPosition);
        }
            
        // The start position is smaller than the first interval
        if(lowerPosition == 0) {
            return false;
        }
        
        // try to search the last interval. if the end position is smaller than the 
        // upper bound of the last interval. We got the interval covered by the last interval.
        return upper <= uppers.get(lowerPosition - 1);
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
        
        int leftIndex = Collections.binarySearch(lowers, lower);
        
        // Recover the position.
        if(leftIndex < 0) {
            leftIndex = -leftIndex - 1;
        }
            
        // The range is out of bound.
        if(leftIndex == size) {
            return;
        }
            
        //determine where to start
        // Get the end of the interval before it.
        if(lowers.get(leftIndex) != lower) {
            leftIndex--;
            if(leftIndex < 0) {
                leftIndex = 0;
            } else {
                // cut the right bound of the left interval before the target range.
                uppers.set(leftIndex, Math.min(lower - 1, uppers.get(leftIndex)));
                leftIndex++;
            }
        }
        
        for (int i = 0; i < size - leftIndex; i++) {
            // remove all the intervals covered by the target range.
            if (uppers.get(leftIndex) <= upper){
                lowers.remove(leftIndex);
                uppers.remove(leftIndex);
            } else{
                if(lowers.get(leftIndex) > upper) {
                    return;
                } else {
                    // cut the left bound of the right interval after the target range.
                    lowers.set(leftIndex, upper + 1);
                }
            }
        }
    }
    
    public void print(){
        System.out.println("Print out the range:");
        int size = this.get_numberOfRanges();
        for(int i = 0;i < size;i++){
            System.out.println(lowers.get(i) + " " + uppers.get(i));
        }
    }
    
    public static void main(String[] args) {
        RangeModule ranges = new RangeModule();
        ranges.AddRange(0, 2);
        ranges.AddRange(6, 9);
        ranges.AddRange(15, 15);
        ranges.AddRange(19, 21);
        ranges.AddRange(16,18);
        
        ranges.AddRange(4,17);
        ranges.print();
        
        System.out.println(ranges.QueryRange(16, 19));
        ranges.DeleteRange(20, 26);
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
