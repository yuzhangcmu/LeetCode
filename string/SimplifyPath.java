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
        path = "/home/", => "/home"    --> Split to : home
        path = "/a/./b/../../c/", => "/c" --> splite to: a . b .. .. c
        */
        // 注意 split的输入是一个字符串 /+ 可以匹配1个或多个/
        String[] strs = path.split("/+");
        
        
        
        Stack<String> s = new Stack<String>();
        
        for (String str: strs) {
            //System.out.println("Print:" + str);
            
            if (str.equals("..")) {
                // we should pop out a element.
                if (!s.isEmpty()) {
                    s.pop();
                }
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
