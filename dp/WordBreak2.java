package Algorithms.dp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {
    public static void main(String[] strs) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaa";
        Set<String> dict = new HashSet<String>();
        dict.add("bin");
        dict.add("apple");
        dict.add("app");
        dict.add("le");
        dict.add("aaaaaa");
        dict.add("aaaaa");
        dict.add("aaaa");
        dict.add("aaa");
        dict.add("aa");
        dict.add("a");
        dict.add("aaaaaaa");
        dict.add("aaaaaaaa");
        dict.add("aaaaaaaaa");
        
        System.out.println("Test");

        Algorithms.permutation.Stopwatch timer2 = new Algorithms.permutation.Stopwatch();
        
        // HASH保存记忆
        wordBreak1(s, dict);
        
        System.out
        .println("Computing time with DFS1: "
                + timer2.elapsedTime() + " millisec.");
        
        Algorithms.permutation.Stopwatch timer1 = new Algorithms.permutation.Stopwatch();
        
        // 递归模板，加剪枝 
        wordBreak2(s, dict);
        
        System.out
        .println("Computing time with DFS2: "
                + timer1.elapsedTime() + " millisec.");
        
        Algorithms.permutation.Stopwatch timer3 = new Algorithms.permutation.Stopwatch();
        
        // DFS+ 剪枝 3: 设置Flag 变量
        //http://fisherlei.blogspot.com/2013/11/leetcode-wordbreak-ii-solution.html
        wordBreak3(s, dict);
        
        System.out
        .println("Computing time with DFS3: "
                + timer3.elapsedTime() + " millisec.");
        
        Algorithms.permutation.Stopwatch timer4 = new Algorithms.permutation.Stopwatch();

        // DP + DFS
        wordBreak4(s, dict);
        
        System.out
        .println("Computing time with DFS4: "
                + timer4.elapsedTime() + " millisec.");

        //System.out.println(list.toString());
    }
    
    // 我们用DFS来解决这个问题吧 
    public static List<String> wordBreak1(String s, Set<String> dict) {
    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    	if (s == null || s.length() == 0 || dict == null) {
    		return null;
    	}

    	return dfs(s, dict, map);
    }

    // 解法1：我们用DFS来解决这个问题吧 
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
			for (int i = 1; i <= len; i++) {
				String sub = s.substring(0, i);

				// 左边的子串可以为空，或是在字典内
				if (!dict.contains(sub)) {
					continue;
				}

				// 字符串划分为2边，计算右边的word break.
				List<String> listRight = dfs(s.substring(i, len), dict, map);


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
    
    /*
	// 解法2：我们用普通的递归模板来试一下。
    */
    
    // 我们用DFS来解决这个问题吧 
    public static List<String> wordBreak2(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        
        // 记录切割过程中生成的字母
        List<String> path = new ArrayList<String>();
            
        dfs2(s, dict, path, ret, 0);
        
        return ret;
    }

    // 我们用DFS模板来解决这个问题吧 
    public static void dfs2(String s, Set<String> dict, 
            List<String> path, List<String> ret, int index) {
    	int len = s.length();
        if (index == len) {
        	// 结束了。index到了末尾
        	StringBuilder sb = new StringBuilder();
        	for (String str: path) {
        		sb.append(str);
        		sb.append(" ");
        	}
        	// remove the last " "
        	sb.deleteCharAt(sb.length() - 1);
        	ret.add(sb.toString());
        	return;
        }
        
        // 如果不加上这一行会超时。就是说不能break的时候，可以直接返回
        // 但这也许只是一个treak, 其实这种方法还是不大好。
        if (!iswordBreak(s.substring(index), dict)) {
        	return;
        }

        for (int i =  index; i < len; i++) {
        	// 注意这些索引的取值。左字符串的长度从0到len
        	String left = s.substring(index, i + 1);
        	if (!dict.contains(left)) {
        		// 如果左字符串不在字典中，不需要继续递归
        		continue;
        	}

        	path.add(left);
        	dfs2(s, dict, path, ret, i + 1);
        	path.remove(path.size() - 1);
        }
    }
    
    public static boolean iswordBreak(String s, Set<String> dict) {
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        if (len == 0) {
            return true;
        }
        
        boolean[] D = new boolean[len + 1];

        // initiate the DP. 注意，这里设置为true是不得已，因为当我们划分字串为左边为0，右边为n的时候，
        // 而右边的n是一个字典string,那么左边必然要设置为true，才能使结果为true。所以空字符串我们需要
        // 认为true
        D[0] = true;
        
        // D[i] 表示i长度的字符串能否被word break.
        for (int i = 1; i <= len; i++) {
        	// 把子串划分为2部分，分别讨论, j 表示左边的字符串的长度
        	// 成立的条件是：左边可以break, 而右边是一个字典单词
        	D[i] = false;
        	for (int j = 0; j < i; j++) {
        		if (D[j] && dict.contains(s.substring(j, i))) {
        			// 只要找到任意一个符合条件，我们就可以BREAK; 表示我们检查的
        			// 这一个子串符合题意
        			D[i] = true;
        			break;
        		}
        	}
        }

        return D[len];
    }
    
    /*
    // 解法3：重新剪枝。
    */
    // 我们用DFS来解决这个问题吧 
    public static List<String> wordBreak3(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        
        // 记录切割过程中生成的字母
        List<String> path = new ArrayList<String>();
        
        int len = s.length();
        
        // 注意：一定要分配 Len+1 否则会爆哦.
        boolean canBreak[] = new boolean[len + 1];
        for (int i = 0; i < len + 1; i++) {
            canBreak[i] = true;
        }
            
        dfs3(s, dict, path, ret, 0, canBreak);
        
        return ret;
    }

    // 我们用DFS模板来解决这个问题吧 
    public static void dfs3(String s, Set<String> dict, 
            List<String> path, List<String> ret, int index,
            boolean canBreak[]) {
        int len = s.length();
        if (index == len) {
            // 结束了。index到了末尾
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str);
                sb.append(" ");
            }
            // remove the last " "
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
            return;
        }
        
        // if can't break, we exit directly.
        if (!canBreak[index]) {
            return;
        }

        // cut branch.
        int beforeChange = ret.size();
        for (int i =  index; i < len; i++) {
            // 注意这些索引的取值。左字符串的长度从0到len
            String left = s.substring(index, i + 1);
            if (!dict.contains(left)) {
                // 如果左字符串不在字典中，不需要继续递归
                continue;
            }
            
            // if can't find any solution, return false, other set it 
            // to be true;
            path.add(left);
            dfs3(s, dict, path, ret, i + 1, canBreak);

            path.remove(path.size() - 1);
        }

        // 注意这些剪枝的代码. 关键在于此以减少复杂度
        if (ret.size() == beforeChange) {
            canBreak[index] = false;    
        }
    }

    /*
    // 解法4：先用DP来求解某些字段是否能word break，然后再做 
    */
    public static List<String> wordBreak4(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        
        List<String> path = new ArrayList<String>();
        
        int len = s.length();
        
        // i: 表示从i索引开始的字串可以word break.
        boolean[] D = new boolean[len + 1];
        D[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j <= len - 1; j++) {
                // 左边从i 到 j
                D[i] = false;
                if (D[j + 1] && dict.contains(s.substring(i, j + 1))) {
                    D[i] = true;
                    break;
                }
            }
        }

        dfs4(s, dict, path, ret, 0, D);
        
        return ret;
    }

    public static void dfs4(String s, Set<String> dict, 
            List<String> path, List<String> ret, int index,
            boolean canBreak[]) {
        int len = s.length();
        if (index == len) {
            // 结束了。index到了末尾
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str);
                sb.append(" ");
            }
            // remove the last " "
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
            return;
        }
        
        // if can't break, we exit directly.
        if (!canBreak[index]) {
            return;
        }

        for (int i =  index; i < len; i++) {
            // 注意这些索引的取值。左字符串的长度从0到len
            String left = s.substring(index, i + 1);
            if (!dict.contains(left)) {
                // 如果左字符串不在字典中，不需要继续递归
                continue;
            }
            
            // if can't find any solution, return false, other set it 
            // to be true;
            path.add(left);
            dfs4(s, dict, path, ret, i + 1, canBreak);
            path.remove(path.size() - 1);
        }

    }
}