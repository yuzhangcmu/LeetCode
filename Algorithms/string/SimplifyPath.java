package Algorithms.string;

import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] strs) {
        System.out.println(simplifyPath("//home"));
    }
    
    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }
        
        /*
        path = "/home/", => "/home"    --> Split to : 空格 home
        path = "/a/./b/../../c/", => "/c" --> splite to: 空格 a . b .. .. c
        */
        // 注意 split的输入是一个字符串
        String[] strs = path.split("/");
        
        Stack<String> s = new Stack<String>();
        
        for (String str: strs) {
            if (str.equals("..")) {
                // we should pop out a element.
                if (!s.isEmpty()) {
                    s.pop();
                }
            // should skip the space and the '.'
            } else if (!str.equals(".") && !str.equals("")) {
                s.push(str);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.insert(0, s.pop());
            sb.insert(0, '/');
        }
        
        // if we get a empty string, should return /
        if (sb.length() ==0) {
            sb.append('/');
        }
        
        return sb.toString();
    }
}
