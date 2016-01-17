package Algorithms;

import java.util.ArrayList;

class TrieNode {
    // Initialize your data structure here.
    boolean isLeaf;
    boolean visited = false;

    TrieNode[] children;

    public TrieNode() {
        isLeaf = false;
        children = new TrieNode[26];
    }

    public boolean isLeaf() {
        // TODO Auto-generated method stub
        return isLeaf;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public static void main(String[] strs) {
        Trie trie = new Trie();
        trie.insert("a");
        trie.insert("abe");
        trie.insert("ac");
        trie.insert("bg");
        
        //System.out.println(trie.firstStartsWith("abe"));
        System.out.println(trie.findNext("abc"));
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
        cur.isLeaf = true;
    }
    
    public String findNext(String word) {
        int len = word.length();
        for (int i = len; i >= 0; i--) {
            String preFix = word.substring(0, i);
            
            String nextWord = null;
            if (i == len) {
                nextWord = firstStartsWith(preFix, '1');
            } else {
                nextWord = firstStartsWith(preFix, word.charAt(i));
            }
            
            if (nextWord != null) {
                return nextWord;
            }
        }
        
        return null;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            }

            cur = cur.children[idx];
        }

        return cur.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return false;
            }
            
            cur = cur.children[idx];
        }

        return true;
    }
    
    public String firstStartsWith(String prefix, char next) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                return null;
            }
            
            cur = cur.children[idx];
        }
        
        String firstStartWith = prefix;
        
        TrieNode tailNode = cur;
        
        boolean firstChar = true;
        
        while (tailNode == cur || !tailNode.isLeaf()) {
            int i = 0;
            
            if (firstChar) {
                i = next - 'a' + 1;
            }
            firstChar = false;
            
            for (; i < 26; i++) {
                if (tailNode.children[i] != null) {
                    char nextChar = (char)('a' + i);
                    
                    firstStartWith += nextChar;
                    tailNode = tailNode.children[i];
                }
            }
            
            // No child
            if (i >= 26) {
                break;
            }
        }
        
        if (firstStartWith.equals(prefix)) {
            return null;
        }

        return firstStartWith;
    }
}