package Algorithms.algorithm.others;
import java.util.ArrayList;
import java.util.HashMap;


public class Anagrams {
    public static void main(String[] args) {
        Anagrams an = new Anagrams();
        String[] strs = {"car","dog","god","rac","mon","bac","acr","mea","cab", "abd", "adb"};
        //String[] strs = {"abc", "bac"};
        System.out.println(an.anagrams(strs).toString());
    }
    
    private int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        
//        int a = 777877;
//        int b = 123451;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
    
    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) {
                result.addAll(tmp);
            }
        }

        return result;
    }

}
