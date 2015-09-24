package Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
<a><b>
    <c>foo</c>
    <c>bar</c>
  </b>
  <d>blah</d>
</a>

Node
  name
  text
  List<Node> children

Tokenizer
  Token getToken();

Token
  type //BEGIN, TEXT, END
  value

(BEGIN, "a"), 
    (BEGIN, "b"), 
        (BEGIN, "c"), 
            (TEXT, "foo"), 
        (END, "c"), 
        (BEGIN, "c"),
            (TEXT, "bar"), 
        (END, "c"),
    (END, "b"),  
    (BEGIN, "d"),  
        (TEXT, "blah"),
    (END, "d")
(END, "a")        
 */

public class SnapChat {
    public class Tokenizer {
        public Token getToken() {
            return null;
        }
    }

    public class Token {
        String type;
        String value;
    }

    public class Node {
        public String name;
        public String text;
        public List<Node> children;
        public Node(String name, String text) {
            super();
            this.name = name;
            this.text = text;
            this.children = new ArrayList<Node>();
        }
    }
    
    public Node generateTree(Tokenizer tokennizer) throws Exception {
        Node parent = null;
        
        Node root = null;
        
        Stack<Node> stk = new Stack<Node>();
        
        while (true) {
            Token toke = tokennizer.getToken();
            if (toke == null) {
                break;
            }
            
            if (toke.type == "TEXT") {
                if (parent == null) {
                    throw new Exception("The format is wrong.");
                }
                parent.text = toke.value;
            } else if (toke.type == "BEGIN"){
                Node node = new Node(toke.value, "");
                
                if (parent == null) {
                    parent = node;
                    root = parent;
                } else {
                    stk.push(parent);
                    parent.children.add(node);
                    parent = node;
                }
                
                //parent.children.add(node);
            } else if (toke.type == "END"){
                parent = stk.pop();
            }
        }
        
        return root;
    }
}
