package Algorithms.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinations2 {
    public static void main(String[] strs) {
        int[] sum = { 0, 1, 2, 3 };
        System.out.println(possibleComb(sum));
        System.out.println(letterCombinations("123"));
    }

    // We create a map to map the digit to the characters.
    static String[] map1 = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
            "wxyz" };

    public static List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        ArrayList<Character> path = new ArrayList<Character>();

        letterCombinationsHelp(digits, path, ret, 0);

        return ret;
    }

    /*
     * 表示从digit的index处开始直到结尾的数字对应的字符的组合数
     */
    public static void letterCombinationsHelp(String digits,
            ArrayList<Character> path, List<String> ret, int index) {
        if (digits == null) {
            return;
        }

        int len = digits.length();
        if (path.size() == len) {
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            ret.add(sb.toString());
            return;
        }

        // get the possiable characters.
        // for example, get abc from '2'
        String combs = getChar(digits.charAt(index));

        for (int i = 0; i < combs.length(); i++) {
            path.add(combs.charAt(i));
            letterCombinationsHelp(digits, path, ret, index + 1);
            path.remove(path.size() - 1);
        }
    }

    public static String getChar(char c) {
        return map1[c - '0'];
    }

    public static List<String> possibleComb(int[] num) {
        if (num == null || num.length == 0) {
            return null;
        }

        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> ret = new ArrayList<String>();
        ArrayList<Character> path = new ArrayList<Character>();

        printRes(num, path, ret, 0, map);
        return ret;
    }

    public static void printRes(int[] num,
            ArrayList<Character> path, List<String> ret, int index,
            HashMap<Integer, String> map) {
        if (num == null) {
            return;
        }

        int len = num.length;
        if (index == len) {
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            ret.add(sb.toString());
            return;
        }

        // get the characters.
        // for example, get abc from '2'
        String combs = map.get(num[index]);

        if (combs != null) {
            for (int i = 0; i < combs.length(); i++) {
                path.add(combs.charAt(i));
                printRes(num, path, ret, index + 1, map);
                path.remove(path.size() - 1);
            }
        } else {
            printRes(num, path, ret, index + 1, map);
        }
        
    }
}