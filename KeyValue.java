package Algorithms;

import java.util.HashMap;
import java.util.Scanner;

public class KeyValue {
    private static HashMap<String, String> map;

    public static void main(String[] strs) {
        map = new HashMap<String, String>();

        Scanner scanner = new Scanner( System.in );
        
        while (true) {           
            // 2. Don't forget to prompt the user
            System.out.print( "Type the option you want to do(1: input data. 2: Get data): " );

            // 3. Use the Scanner to read a line of text from the user.
            String input = scanner.nextLine();
            
            if (input.equals("1")) {
                System.out.print( "Please input the key: " );
                String key = scanner.nextLine();
                System.out.println( "key = " + key );
                
                System.out.print( "Please input the value: " );
                String value = scanner.nextLine();
                System.out.println( "value = " + value );
                
                map.put(key, value);
                
                System.out.print( "Insert the key/value pair to the database." );
            } else if (input.equals("2")){
                System.out.print( "Please input the key: " );
                String key = scanner.nextLine();
                System.out.println( "key = " + key );
                
                String value = map.get(key);
                if (value == null) {
                    System.out.println( "The key does not exit." );    
                } else {
                    System.out.println( "Value = " + map.get(key) );
                }
            }
            
            System.out.println();
        }
        
    }
    
    //public 
}
