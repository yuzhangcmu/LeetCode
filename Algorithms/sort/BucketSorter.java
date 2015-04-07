package Algorithms.sort;


/**
 * @author yovn
 *
 */
public class BucketSorter {
    public void sort(int[] keys,int from,int len,int max)
    {
    	int[] count = new int[max];
    	int[] tmp = new int[len];
    	
        // count the keys.
    	for (int i = 0; i < len; i++) {
    		count[keys[from + i]]++;
    	}
    	
    	// calculate the position.
    	// BUG 1: i go from 1 not 0.
    	for (int i = 1; i < max; i++) {
    		count[i] = count[i] + count[i - 1];
    	}
    	
    	// back the array.
    	System.arraycopy(keys, from, tmp, 0, len);
    	
    	// Place the objects into the right position.
    	for (int i = len - 1; i >= 0; i--) {
    		keys[--count[tmp[i]]] = tmp[i];
    	}
    }
    /**
     * @param args
     */
    public static void main(String[] args) {

        int[] a={1,4,8,3,2,9,5,0,7,6,9,10,9,13,14,15,11,12,17,16};
        BucketSorter sorter=new BucketSorter();
        sorter.sort(a,0,a.length,20);//actually is 18, but 20 will also work
        
        
        for(int i=0;i<a.length;i++)
        {
            System.out.print(a[i]+",");
        }

    }

}