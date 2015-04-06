import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FindSubstring {
	public static void main(String[] strs) {
		String S = "barfoothefoobarman";
		String[] L = {"foo","bar"};
		
		System.out.print(findSubstring(S, L));
	}
	
    public static List<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (S == null || L == null || L.length == 0) {
            return ret;
        }
        
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        HashMap<String, Integer> des = new HashMap<String, Integer>();

        for (String s: L) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                // bug 1: should be , not .
                map.put(s, 1);
            }
        }
        
        int wordLen = L[0].length();
        
        int size = L.length;
        int cnt = 0;
        
        int len = S.length();
        for (int i = 0; i < len; i++) {
            map.clear();
            cnt = 0;
            
            // pay attention: should use j <= len.
            for (int j = i; j <= len - wordLen; j += wordLen) {
                String sub = S.substring(j, j + wordLen);
                
                if (!map.containsKey(sub)) {
                    break;
                }
                
                if (des.containsKey(sub)) {
                    des.put(sub, 1 + des.get(sub));
                } else {
                    des.put(sub, 1);
                }
                
                if (des.get(sub) > map.get(sub)) {
                    break;
                }
                
                cnt++;
                
                if (cnt == size) {
                    ret.add(i);
                    break;
                }
            }
        }
        
        return ret;
    }
}