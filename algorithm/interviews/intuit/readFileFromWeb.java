package Algorithms.algorithm.interviews.intuit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhangyu
 * 
 */
public class readFileFromWeb {
    public static void main(String[] strs) {
        Map<String, Integer> freqOfWords = new HashMap<String, Integer>();
        String[] words = "coming together is a beginning keeping together is progress working together is success"
                .split(" ");
        for (String word : words) {
            Integer frequency = freqOfWords.get(word);
            if (frequency == null) {
                frequency = 1;
            } else {
                frequency++;
            }
            freqOfWords.put(word, frequency);
        }
        
        TreeMap<String, Integer> map = new TreeMap<String, Integer>(Collections.reverseOrder());
        map.putAll(freqOfWords);
        System.out.println(map);
        System.out.println(map.pollFirstEntry());
        System.out.println(map.descendingMap());
    }
}
