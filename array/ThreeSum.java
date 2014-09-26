package Algorithms.array;
import java.util.ArrayList;
import java.util.Arrays;


public class ThreeSum {
    public static void main(String[] args){
        //int[] num = {-1,0,2,3,-1,1,-3};
        
        
        
        int[] num = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,
                -4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6
                ,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6
                ,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1
                ,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12
                ,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,
                13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
                
        
        //Arrays.sort(num);
        sort(num, 0, num.length - 1);
        
        print(num);
        
        
        System.out.printf("\n");
        
        
        
        ArrayList<ArrayList<Integer>> result = threeSum(num);
        
        System.out.printf(result.toString());
    }
    
    public static void print(int num[]){
        for (int i = 0; i < num.length; i++) {
            System.out.printf("%d ", num[i]);
        }
        
        System.out.printf("\n");
    }
    
    /* return the middle position. all the data below middle value will
     * be move to the left, others to the right. */
    public static int divide(int[] input, int left, int right, int midValue){
        int i = left;
        int j = right;
        
        if (right == left){
            return left;
        }
        
        while(i < j){
            while (i < j && input[j] >= midValue){
                j --;
            }
            
            input[i] = input[j];
            //i ++;
            
            while (i < j && input[i] <= midValue){
                i ++;
            }
            
            input[j] = input[i];
            //j --;
        }
        
        input[i] = midValue;
        
        return i;
        
    }
    
    public static void sort(int[] input, int left, int right){
        
        int midValue = input[left];
        
        if (left == right){
            return;
        }
        
        //System.out.printf("midValue = %d left:%d, right:%d \n", midValue, left, right);
        
        int middle = divide(input, left, right, midValue);
        
        //System.out.printf("After divide. middle = %d\n", middle);
        //print(input);
        
        if (middle-1 >= left){
            sort(input, left, middle - 1);
        }
        
        if (middle + 1 <= right ){
            sort(input, middle + 1, right);
        }
    }
    
    
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        //int[] a = num;
        
        ArrayList<Integer> newList = new ArrayList<Integer>();
        
        //int count = 0;
        //boolean
        
        for (int i = 0; i < num.length; i++) {
            if(i > 1 && num[i] == num[i-1] && num[i] == num[i-2]){
                continue;
            }
            
            newList.add(num[i]);
        }
        
        System.out.printf("Arraylist: %s\n", newList.toString());
        
        
        ArrayList<ArrayList<Integer>> result = 
                new ArrayList<ArrayList<Integer>>();
        
        if(num.length < 3){
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        threeSumHelp(result, list, newList, 0); 
        
        return result;
        
    }
    
    public static int sum(ArrayList<Integer> list){
        int sum = 0;
        for(int i = 0; i < list.size(); i++){
            sum += list.get(i);
        }
        
        return sum;
    }
    
    public static void threeSumHelp(ArrayList<ArrayList<Integer>> result,
            ArrayList<Integer> list,
            ArrayList<Integer> num, int pos) {
        //int[] a = num;
        
        if (list.size() == 3){
            //System.out.printf("sum of list:%d\n", sum(list));
            if (sum(list) == 0){
                result.add(new ArrayList<Integer>(list));
            }
            
            return;
            
        }
        
        //System.out.printf("num length: %d\n list:%s\n", 
          //      num.length, list.toString());
        
        for (int i = pos; i < num.size(); i++) {
            /* if the next element is the same with the current one, just skip. */
            if (i + 1 < num.size() && num.get(i+1) == num.get(i)){
                continue;
            }
            
            list.add(num.get(i));
            threeSumHelp(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
        
        return;
    }
}
