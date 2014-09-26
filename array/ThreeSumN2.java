package Algorithms.array;
import java.util.ArrayList;
import java.util.Arrays;


public class ThreeSumN2 {
    public static void main(String[] args){
        //int[] num = {-1,0,2,3,-1,1,-3};
        
        
        
        int[] num = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,
                -4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6
                ,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6
                ,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1
                ,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12
                ,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,
                13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        
        
        //int[] num = {7,-1,14,-12,-8,7,2,-15,8,8,-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,-5,-11,1,3,-6};
        //int[] num = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        //int[] num = {1,2,-2,-1};
        //int[] num = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        //int[] num = {-2,0,0,2,2};
        
        //Arrays.sort(num);
        sort(num, 0, num.length - 1);
        
        int[] newT = removeDuplicate(num);
        
        print(newT);
        
        
        System.out.printf("\n");
        
        
        
        ArrayList<ArrayList<Integer>> result = threeSum(num);
        
        System.out.printf(result.toString());
    }
    
    
    public static int[] removeDuplicate(int[] input){

        int len = input.length;
        
        int[] output = new int[len];
        
        int j = 0;
        
        for (int i = 0; i < len; i ++) {        
            if (i > 0) {
                while (input[i] == input[i-1]){
                    i ++;
                }
            }
        
            output[j] = input[i];
            j++;
        }
        
        return output;
        

    }
    
    public static void print(int num[]){
        System.out.printf("After sort: \n");
        
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
        
        //System.out.printf("left: %d\n", left);
        
        if (left >= right){
            return;
        }
        
        int midValue = input[left];
        
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
        
        //sort(num, 0, num.length - 1);
        
        Arrays.sort(num);
        
        ArrayList<Integer> newList = new ArrayList<Integer>();
        
        int j = 0;
        int[] numNew = new int[num.length];
        
        for (int i = 0; i < num.length; i++) {
            if(i > 1 && num[i] == num[i-1] && num[i] == num[i-2] && num[i] != 0){
                continue;
            }
            
            numNew[j] = num[i];
            j++;
        }
        
        
        ArrayList<ArrayList<Integer>> result = 
                new ArrayList<ArrayList<Integer>>();
        
        if(num.length < 3){
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        threeSumHelp(result, num, num.length); 
        
        return result;
        
    }
    
    public static void threeSumHelp(ArrayList<ArrayList<Integer>> result,
            int num[], int length) {
                
        int index = 0;
        while(index < length){
            if (num[index] != 0){
                break;
            }
            index ++;
        }
    
        // we should choose at least 3 numbers.
        for(int i = 0; i < length - 2; i++){
            // add the first element.
            
            if (i > 0){
                if (num[i] == num[i-1]){
                    continue;
                }
            }
            
            int left = i + 1;
            int right = length - 1;
            
            while(left < right){
                while (left > i + 1
                         && num[left] == num[(left - 1)]) {
                    left++;
                    //System.out.printf("left ++ left %d right %d length: %d\n", left, right, length);
                    //continue;
                }
                while (right < length - 1
                        && num[(right)] == num[(right + 1)]) {
                    right--;
                    //System.out.printf("right -- left %d right %d length: %d\n", left, right, length);
                    //continue;
                }
                
                if (left >= right){
                    break;
                }
                
                
                //}
                int sum =  num[(left)] + num[(right)] + num[(i)];
                if (sum > 0){
                    right --;
                }
                else if(sum < 0){
                    left ++;
                }
                else{
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(num[(i)]);
                    list.add(num[(left)]);
                    list.add(num[(right)]);
                    result.add(list);
                    right --;
                    left ++;
                }
                
                
            }
        }
        
       
    }

}