package Algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        // 1916.
        List<String> ret = new ArrayList<String>();
        if (strs == null) {
            return ret;
        }
        
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String tmp = new String(chars);
            
            ArrayList<String> list = map.get(tmp);
            if (list == null) {
            } else {
                list.add(str);
            }
        }
        
        for (List<String> strList: map.values()) {
            if (strList.size() > 1) {
                //ret.addAll(strList);
            }
        }
        
        return ret;
    }
}