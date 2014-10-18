package Algorithms.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {
    public static void main(String[] strs) {
        Set<String> dict = new HashSet<String>();
            
        dict.add("apple");
        dict.add("yuzhang");
        
        System.out.println(wordBreak("appleyuzhang", dict).toString());
    }
    
    // 我们用DFS来解决这个问题吧 
    public static List<String> wordBreak(String s, Set<String> dict) {
    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    	if (s == null || s.length() == 0 || dict == null) {
    		return null;
    	}

    	return dfs(s, dict, map);
    }

    // 我们用DFS来解决这个问题吧 
    public static List<String> dfs(String s, Set<String> dict, HashMap<String, List<String>> map) {
		if (map.containsKey(s)) {
			return map.get(s);
		}

		List<String> list = new ArrayList<String>();
		int len = s.length();

		if (len == 0) {
			list.add("");
		} else {
			// i 表示左边字符串的长度
			// 注意：i千万不要从0开始计算，否则会产生死循环。递归的要点是：你要把问题分解成更小的子问题。
			// 如果i = 0，代表左字符串为空，我们又回到求解s本身的word break，那就是一个死循环！
			for (int i = 1; i <= len; i++) {
				String sub = s.substring(0, i);

				// 左边的子串可以为空，或是在字典内
				if (!dict.contains(sub)) {
					continue;
				}

				// 字符串划分为2边，计算右边的word break.
				List<String> listRight = dfs(s.substring(i, len), dict, map);

				// 右边不能break的时候，我们跳过.
				if (listRight.size() == 0) {
					continue;
				}

				// 把左字符串加到右字符串中，形成新的解.
				for (String r: listRight) {
					StringBuilder sb = new StringBuilder();
					sb.append(sub);
					if (i != 0 && i != len) {
						// 如果左边为空，或是右边为空，不需要贴空格
						sb.append(" ");
					}
					sb.append(r);
					list.add(sb.toString());
				}
			}
		}

		map.put(s, list);
		return list;
    }
}
