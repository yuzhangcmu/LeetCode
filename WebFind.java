package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WebFind {
    
    public class UrlGraph {
        String val;
        ArrayList<UrlGraph> childrens;
        
        public UrlGraph(String val) {
            super();
            this.val = val;
            this.childrens = new ArrayList<UrlGraph>();
        }
    }
    
    public UrlGraph webClown(String url, HashMap<String, UrlGraph> map) {
        if (map.containsKey(url)) {
            return map.get(url);
        }
        
        UrlGraph node = new UrlGraph(url);
        
        ArrayList<String> list = getUrls(url);
        for (String sub: list) {
            node.childrens.add(webClown(sub, map));
        }
        
        map.put(url, node);
        return node;
    }

    public ArrayList<String> getUrls(String url) {
        //return
    }
}
