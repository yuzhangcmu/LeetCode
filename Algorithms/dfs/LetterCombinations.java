package Algorithms.dfs;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        ArrayList<Character> path = new ArrayList<Character>();
        
        letterCombinationsHelp(digits, path, ret, 0);
        
        return ret;
    }
    
    /*
     表示从digit的index处开始直到结尾的数字对应的字符的组合数 
    */
    public void letterCombinationsHelp(String digits, ArrayList<Character> path, List<String> ret, int index) {
        if (digits == null) {
            return;
        }
        
        int len = digits.length();
        if (path.size() == len) {
            StringBuilder sb = new StringBuilder();
            for (char c: path) {
                sb.append(c);
            }
            ret.add(sb.toString());
            return;
        }
        
        List<Character> chars = getChar(digits.charAt(index));
        
        for (int i = 0; i < chars.size(); i++) {
            path.add(chars.get(i));
            letterCombinationsHelp(digits, path, ret, index + 1);
            path.remove(path.size() - 1);
        }
    }
    
    public List<Character> getChar(char c) {
        ArrayList<Character> list = new ArrayList<Character>();
        
        if (c == '1') {
            return list;
        } else if (c == '2') {
            list.add('a');
            list.add('b');
            list.add('c');
        } else if (c == '3') {
            list.add('d');
            list.add('e');
            list.add('f');
        } else if (c == '4') {
            list.add('g');
            list.add('h');
            list.add('i');
        } else if (c == '5') {
            list.add('j');
            list.add('k');
            list.add('l');
        } else if (c == '6') {
            list.add('m');
            list.add('n');
            list.add('o');
        } else if (c == '7') {
            list.add('p');
            list.add('q');
            list.add('r');
            list.add('s');
        } else if (c == '8') {
            list.add('t');
            list.add('u');
            list.add('v');
        } else if (c == '9') {
            list.add('w');
            list.add('x');
            list.add('y');
            list.add('z');
        }
        
        return list;
    }
}
