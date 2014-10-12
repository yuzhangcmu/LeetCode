package Algorithms.algorithm.interviews.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class SortWeight {
    public static void main(String[] args) {
        String input1 = "crab hotdog 9.0 chicken 9.2 pig 9.2";
        String input2 = "pizza 1 hotdog 2.0";
        String input3 = "pizza 500 hotdog 2.0";
        String input4 = "pizza 500 2.0";        
        
        LinkedList<String> list1 = sortWeight2(input1);
        LinkedList<String> list2 = sortWeight2(input2);
        LinkedList<String> list3 = sortWeight2(input3);
        LinkedList<String> list4 = sortWeight2(input4);
        
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
        System.out.println(list4.toString());
    }
    
    public static LinkedList<String> sortWeight(String input) {
        LinkedList<String> ret = new LinkedList<String>();
        if (input == null) {
            return ret;
        }
        
        String[] strs = input.split(" ");
        
        float defautWeight = 5;
        
         
        String food = null;
        float weight = 0;
        
        // 使用hashmap来记录food-weight 对
        HashMap<Float, ArrayList<String>> map = new HashMap<Float, ArrayList<String>>();
        
        // Go through the string.
        for (String s: strs) {
            // 这是第一对食物-重量对
            if (weight != -1) {
                food = s;
                weight = -1;
            } else {
                float tmp = stringToNumber(s);
                // This is a float, so just add a food to the list.
                if (tmp != -1) {
                    weight = tmp;
                    addFoodToMap(map, food, weight);
                } else {
                    // This is not a float, means that there should be
                    // a new food.
                    addFoodToMap(map, food, defautWeight);
                    
                    // 重新开始计算新一轮的食物对 
                    food = s;
                    weight = -1;
                }
            }
        }
        
        //System.out.println(map.toString());
        
        if (weight == -1) {
            addFoodToMap(map, food, defautWeight);
        }
        
        ArrayList<Float> array = new ArrayList<Float>(map.keySet());
        Collections.sort(array);
        
        for (Float w: array) {
            ArrayList<String> foods = map.get(w);
            for (String element: foods) {
                ret.addFirst(element);
            }
        }
        
        return ret;
    }
    
    public static class Pair implements Comparable<Pair> {
        String food;
        float weight;
        
        Pair (String food, float weight) {
            this.food = food;
            this.weight = weight;
        }

        // 这样子写会达成反序的效果 
        public int compareTo(Pair o) {
            if (o.weight - this.weight < 0) {
                return -1;
            }
            
            return 1;
        }
    }
    
    /*
     * 实现方法2：自定义一个Pair数据类型，并且用compare来自动排序 
     * */
    public static LinkedList<String> sortWeight2(String input) {
        LinkedList<String> ret = new LinkedList<String>();
        if (input == null) {
            return ret;
        }
        
        String[] strs = input.split(" ");
        
        float defautWeight = 5;
        
        
        String food = null;
        float weight = 0;
        
        // 创建一个空的食物-weight 链
        ArrayList<Pair> list = new ArrayList<Pair>();
        
        // Go through the string.
        for (String s: strs) {
            // 首个食物-weight对 
            if (weight != -1) {
                food = s;
                weight = -1;
            } else {
                float tmp = stringToNumber(s);
                // This is a float, so just add a food to the list.
                if (tmp != -1) {
                    weight = tmp;
                    list.add(new Pair(food, weight));
                } else {
                    // This is not a float, means that there should be
                    // a new food.
                    list.add(new Pair(food, defautWeight));
                    
                    // 开始新一轮的食物-weight链
                    food = s;
                    weight = -1;
                }
            }
        }
        
        //System.out.println(map.toString());
        
        if (weight == -1) {
            list.add(new Pair(food, defautWeight));
        }
        
        Collections.sort(list);
        Iterator<Pair> iter = list.iterator();
        while (iter.hasNext()) {
            ret.add(iter.next().food);
        }
        
        return ret;
    }
    
    
    public static void addFoodToMap(HashMap<Float, ArrayList<String>> map, String food,
             float weight) {
        // 检查 在map中是否存在
        ArrayList<String> list = map.get(weight);
        if (list != null) {
            // 添加一个新的重量
            list.add(food);
        } else {
            // 把食物放在重量后面对应的链上
            ArrayList<String> listNew = new ArrayList<String>();
            listNew.add(food);
            map.put(weight, listNew);
        }
    }

    // when it is not a float, return -1;
    public static float stringToNumber(String cur) {
        float result = -1;
        
        try {
            result = Float.parseFloat(cur);
        } catch (NumberFormatException e) {
            result = -1;
        }
        
        return result;
    }
}
