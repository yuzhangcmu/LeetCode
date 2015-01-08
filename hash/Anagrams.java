package Algorithms.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        List<String> ret = new ArrayList<String>();
        
        if (strs == null) {
            return ret;
        }
        
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            String s = strs[i];
            
            // Sort the string.            
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String strSort = new String(chars);   
            
            // Create a ArrayList for the sorted string.            
            if (!map.containsKey(strSort)) {
                map.put(strSort, new ArrayList<String>());
            }
            
            // Add a new string to the list of the hashmap.
            map.get(strSort).add(s);
        }
        
        // go through the map and add all the strings into the result.
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> list = entry.getValue();
            
            // skip the entries which only have one string.
            if (list.size() == 1) {
                continue;
            }
            
            // add the strings into the list.
            ret.addAll(list);
        }
        
        return ret;
    }
    
    public static void main(String[] strs1) {
    	String[] strs = {"str1", "str2", "s1tr"};
    	System.out.println(anagrams2(strs));
    }
    
    public static List<String> anagrams2(String[] strs) {
        List<String> ret = new ArrayList<String>();
        if (strs == null) {
            return ret;
        }
        
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] chars = s.toCharArray();
            
            Arrays.sort(chars);
            String sSort = new String(chars);
            
            if (map.containsKey(sSort)) {
                map.get(sSort).add(s);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(s);
                map.put(sSort, list);
            }
        }
        
        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> list = entry.getValue();
            if (list.size() > 1) {
                ret.addAll(list);
            }
        }
        
        return ret;
    }
}