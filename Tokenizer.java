package Algorithms;

import java.util.ArrayList;

public class Tokenizer {
    public enum State {
        GAP,
        INSIDE_STRING,
        INSIDE_QUATER
    }
    
    public static void main(String[] strs) {
        System.out.println("Test case");
        String str = "Test case";
        str = "util.exe -file   abc -s [abc dd]]]   \"efg    []\"\"]z\"";
        System.out.println(tokenize(str));
    }
    
    public static void AddToken(String token, ArrayList<String> list) {
        if (!token.isEmpty()) {
            list.add(token);
        }
    }

    public static ArrayList<String> tokenize(String input) {
        ArrayList<String> ret = new ArrayList<String>();
        
        State state = State.GAP;        
        String token = "";
        
        char divider = ' ';
        
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            switch(state) {
                case GAP:
                    if (c == ' ') {
                        continue;
                    } else {
                        if (c == '[' || c == '"') {
                            if (c == '[') {
                               divider = ']';
                            } else {
                               divider = c;
                            }
                            state = State.INSIDE_QUATER;
                        } else {
                            state = State.INSIDE_STRING;
                            
                            // Add c to the current token;
                            token = token + c;
                        }
                    }
                    break;
                    
                case INSIDE_QUATER:    
                    if (c == divider) {
                        // End of the string.
                        if (i == len - 1 || input.charAt(i + 1) == ' ') {
                            state = State.GAP;
                            AddToken(token, ret);
                            token = "";
                            continue;
                        } else if (input.charAt(i + 1) == divider) {
                            i++;
                        }
                    }
                    
                    token = token + c;
                    break;
                    
                case INSIDE_STRING:
                    if (c == ' ') {
                        state = State.GAP;
                        AddToken(token, ret);
                        token = "";
                        continue;
                    }
                    
                    token = token + c;
                    break;
            default:
                break;
            }
        }
        
        return ret;
    }
}
