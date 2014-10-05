package Algorithms.permutation;

/*****************************************
 *  Stopwatch class to be used as a timer
 *****************************************/

public class Stopwatch { 
    

    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    } 

    // return time (in milliseconds) since this object was created
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return now - start;
    } 
    
} 