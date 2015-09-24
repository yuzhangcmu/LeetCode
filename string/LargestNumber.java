package Algorithms.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LargestNumber {
    public static void main(String[] strs) {
        int[] num = {1, 2};
        largestNumber(num);
        
        ArrayList<Card> cards = new ArrayList<Card>(5);
        Card card1 = new Card(1);
        Card card2 = new Card(4);
        Card card3 = new Card(2);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        if (card1.compareTo(card2) > 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        
        // uses compareTo method implemented in Card object to compare them
        Collections.sort(cards);
        
        // uses compare method implements in Comparator class
        //Collections.sort(cards, new CompareBySuitRank());
        
        for (Card card : cards)
            System.out.println(card.toString());    

    }
    
    public static class Card implements Comparable<Card>{
        int val;
        
        public String toString() {
            return "" + val;
        }
        
        public Card(int val) {
            super();
            this.val = val;
        }

        public int compareTo(Card o) {
            return this.val - o.val;
        }
    }
    
    public static String largestNumber(int[] num) {
        // 1045
        // 1111 begin.
        if (num == null) {
            return null;
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int n1: num) {
            list.add(n1);
        }
        
        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                String s1 = "" + o1 + o2;
                String s2 = "" + o2 + o1;
                
                return s2.compareTo(s1);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for (int n: list) {
            sb.append(n);
        }
        
        if (sb.charAt(0) == '0') {
            return "0";
        }
        
        return sb.toString();
    }
}
