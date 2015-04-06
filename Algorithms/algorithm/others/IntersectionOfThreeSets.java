package Algorithms.algorithm.others;
import java.util.HashMap;
import java.util.LinkedList;


public class IntersectionOfThreeSets {
    public static LinkedList<Integer> findIntersection(int[] set1, int[] set2, int[] set3) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        
        HashMap<Integer, Integer> tmp = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < set1.length; i++) {
            tmp.put(set1[i],1);
        }
        
        for(int i = 0; i < set2.length; i++) {
            Integer re = tmp.get(set2[i]);
            if (re != null) {
                tmp.put(set2[i],2);
            }
        }
        
        for(int i = 0; i < set3.length; i++) {
            Integer re = tmp.get(set3[i]);
            if (re != null && re == 2) {
                result.add(set3[i]);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] a1 = {1,2,20,3,8,9};
        int[] a2 = {1,2,3,7,20,9};
        int[] a3 = {4,3,90,8,20,9};
        LinkedList<Integer> re = IntersectionOfThreeSets.findIntersection(a1,a2,a3);
        System.out.printf(re.toString());
    }

}
