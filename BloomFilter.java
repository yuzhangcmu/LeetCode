package Algorithms;

import java.util.ArrayList;
import java.util.BitSet;

public class BloomFilter {
    public class HashFunc {
        public int callFun(String s) {
            return 0;
        }
    }
    
    BitSet bits; 
    int n;
    
    public BloomFilter(int n, ArrayList<HashFunc> list) {
        super();
        this.bits = new BitSet(n);
        this.n = n;
        this.list = list;
    }

    ArrayList<HashFunc> list; 
    
    public void add(String s) {
        for (HashFunc fun: list) {
            int code = fun.callFun(s);
            code = code % n;
            
            bits.set(code);
        }
    }
    
    public boolean contains(String s) {
        for (HashFunc fun: list) {
            int code = fun.callFun(s);
            code = code % n;
            
            if (!bits.get(code)) {
                return false;
            }
        }
        
        return true;
    }

}
