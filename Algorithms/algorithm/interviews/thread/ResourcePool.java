package Algorithms.algorithm.interviews.thread;

import java.util.HashMap;
import java.util.HashSet;


/*
 * Thread Pool Test
Implement a generic resource pool. Generic refers to the fact that the pool manages instances of type R where R is a generic/parameterized type (i.e. Java 5+ generics). The pool allows resources to be acquired (checked out of the pool) and then later released (checked back into the pool). New resources may be added to the pool, and existing resources may be removed from the pool. The resource pool must be thread&safe. This means that multiple threads must be able to acquire, release, add or remove resources simultaneously. Two threads should not be able to acquire the same resource simultaneously. Use a defensive strategy (as opposed to design by contract) when implementing your resource pool as you do not know how your pool will be used or if it will be used correctly.
The resource pool must have a default/no&argument constructor, although additional constructors are fine as well. The resource pool must implement at least the following methods:
• void open()
• boolean isOpen()
• void close()
• void closeNow()
• boolean add(R resource)
• boolean remove(R resource)
• boolean removeNow(R resource)
• R acquire()
• R acquire(long timeout java.util.concurrent.TimeUnit unit) • void release(R resource)
These methods may throw any exceptions you determine to be appropriate. The following rules govern the behavior of these methods:
• The pool shall not allow any resource to be acquired unless the pool is open
• Resources may be released at any time
• Resources may be added or removed at any time
• The acquire() method must block until a resource is available, unless the
pool is closed, in which case a null should be returned or an exception
thrown immediately
• If a resource cannot be acquired within the timeout interval specified in
the acquire(long TimeUnit) method, either a null may be returned or
an exception may be thrown
• The add(R) method returns true if the given resource was not previously
managed by the pool or false if the given resource was already managed by the pool. If a resource r has not yet been added to the pool, then calling
add(r) twice will return true the first time and false the second time.
• The remove(R) method returns true if the given resource was removed
from the pool or false if the given resource was not managed by the pool. If a resource r has been added to the pool, then calling remove(r) twice will return true the first time and false the second time.
• When remove(R) is called, if the given resource is already in use, the method must block until the resource is no longer in use
• The method removeNow(R) removes the given resource immediately without waiting for it to be released. The return value has the same meaning as remove(R).
• The close() method should block until all acquired resources have been released
• The method closeNow() closes the pool immediately without waiting for all acquired resources to be released
• The release() method returns an acquired resource to the pool, but only if that resource is still managed by the pool (i.e. has not been removed)
• For optimization purposes assume that acquire/release operations occur frequently and add/remove operations occur infrequently
Please prepare notes on why you made design decisions that you did, and how you would improve it given more time. Please describe how you know it is thread&safe, and how you would test it to make sure. Unit tests may help to verify your implementation's functionality. Please submit your implementation in the form of one or more .java files or an archive containing a Maven, Eclipse, or other project. The coding test should probably represent an effort of 4&5 hours. Code will be evaluated as follows:
1. Correctness: it works according to specification including the requirement that it be thread&safe. We will verify correctness through automated unit testing as well as manual code review. We will look in particular to potential synchronization issues.
2. Overall design and implementation choices: use of appropriate language features and data structures, complexity of solution, performance of solution
3. Coding style, cleanliness of delivery, helpful comments in code
4. Explanation of design decisions

 * */
public class ResourcePool<R> {
    private boolean open;
    
    
    HashMap<R, Boolean> set;
    
    public ResourcePool() {
        
    }
    
    public synchronized void open() {
        open = true;
    }
    
    /**
     * 
     * @return
     */
    public boolean isOpen() {
        return open;
    }
    
    public synchronized void close() {
        open = false;
    }
    
    public void closeNow() {
        
    }
    
    public boolean add(R resource) {
        return true;
    }
    
    public boolean remove(R resource) {
        return true;
    }
    
    /**
     * 
     * @param resource
     * @return
     */
    public boolean removeNow(R resource) {
        return true;
    }
    
    public R acquire() throws Exception {
        if (!isOpen()) {
            throw new Exception("The resource poll is still close!");
        }
        
        while (true) {
            
        }
    }
    
    public R acquire(long timeout, java.util.concurrent.TimeUnit unit) {
        return null;
    }
    
    public void release (R resource) {
        
    }
}
