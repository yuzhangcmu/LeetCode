package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FindDifferentInterval {
    public static void main(String[] strs) {
        //findDifferent(list1, list2);
    }
    
    private static class StartType {
        int time;
        int count;
        public StartType(int time, int count) {
            super();
            this.time = time;
            this.count = count;
        }
    }

    public static void findDifferent(ArrayList<StartType> list1, ArrayList<StartType> list2) {
        if (list1 == null || list2 == null) {
            return;
        }
        
        ArrayList<StartType> list = new ArrayList<StartType>();
        
        int index1 = 0;
        int index2 = 0;

        while (true) {
            int time1 = 0;
            int time2 = 0;
            
            if (index1 == list1.size() && index2 == list2.size()) {
                break;
            }
            
            if (index1 == list1.size()) {
                list.add(new StartType(time2, list2.get(index2).count));
                index2++;
            } else if (index2 == list2.size()) {
                list.add(new StartType(time1, list1.get(index1).count));
                index1++;
            }
            
            time1 = list1.get(index1).time;
            time2 = list2.get(index2).time;
            
            if (time1 == time2) {
                int cnt = list1.get(index1).count +list2.get(index2).count;
                list.add(new StartType(time1, cnt));
                index1++;
                index2++;
            } else if (time1 < time2) {
                list.add(new StartType(time1, list1.get(index1).count));
                index1++;
            } else {
                list.add(new StartType(time2, list2.get(index2).count));
                index2++;
            }
        }
        
        int number = 0;
        for (int i = 0; i < list.size(); i++) {
        }
    }
}
