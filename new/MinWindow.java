import java.util.HashMap;


public class MinWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minWindow("ab", "b"));
	} 
	
	public static String minWindow1(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        
        if (lenT > lenS) {
            // No such window
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < lenT; i++) {
            char c = T.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        
        int cnt = 0;
        int size = map.size();
        
        int left = -1;
        
        String sub = "";
        int minLen = Integer.MAX_VALUE;
        
        // bug 1: should use ';' instead of ','
        for (int right = 0; right < lenS; right++) {
            // bug 2: write i instead of right.
            char c = S.charAt(right);
            
            // bug 5: should add this statement to skip the invalid characters.
            if (!map.containsKey(c)) {
                continue;
            }
            
            map.put(c, map.get(c) - 1);
            
            if (map.get(c) == 0) {
                cnt++;
            }
            
            //compress the left side.
            if (cnt == size) {
                while (left < lenS - 1) {
                    left++;
                    
                    char cLeft = S.charAt(left);
                    if (!map.containsKey(cLeft)) {
                        // bug 6.
                        // skip the invalid characters.
                        continue;  
                    }
                    
                    map.put(cLeft, map.get(cLeft) + 1); 
                    
                    // bug: should use > 0, because it means that the characters is not enought
                    // if the number of characters reduce to below level, we should break;
                    if (map.get(cLeft) > 0) {
                        cnt--;
                        // Bug 7: 
                        if (minLen > right - left + 1) {
                            minLen = right - left + 1;
                            sub = S.substring(left, right + 1);
                        }
                        
                        break;
                    }
                    
                    

                }
            }
        }
        
        return sub;
    }
    
    public static String minWindow(String S, String T) {
        int lenS = S.length();
        int lenT = T.length();
        
        String sub = "";
        
        if (lenT > lenS) {
            // No such window
            return sub;
        }
        
        // 隐式初始化后，它们默认是0
        // http://developer.51cto.com/art/200906/128274.htm
        
        // store the number of the characters.
        int[] src = new int[128];
        int[] des = new int[128];
        
        int size = 0, cnt = 0;
        
        for (int i = 0; i < lenT; i++) {
            int num = T.charAt(i);
            src[num]++;
            
            if (src[num] == 1) {
                size++;
            }
        }
        
        int minLen = Integer.MAX_VALUE;
        
        int left = -1;
        
        for (int right = 0; right < lenS; right++) {
            char c = S.charAt(right);
            if (src[c] != 0) {
                des[c]++;
                
                // bug 1: the statement should be in the 'src[c] != 0'
                if (des[c] == src[c]) {
                    cnt++;
                }
            }
            
            if (cnt == size) {
                while (left < lenS - 1) {
                    left++;
                    
                    char cLeft = S.charAt(left);
                    
                    // skip the invalid character.
                    if (src[cLeft] == 0) {
                        continue;
                    }
                    
                    des[cLeft]--;
                    if (src[cLeft] > des[cLeft]) {
                        cnt--; // the count reduce to the lower bound.
                        
                        // put the statement here.
                        int len = right - left + 1;
                        if (len < minLen) {
                            minLen = len;
                            sub = S.substring(left, right + 1);
                        }
                        break;
                    }
                }
            }
        }
        
        return sub;
    }
}
