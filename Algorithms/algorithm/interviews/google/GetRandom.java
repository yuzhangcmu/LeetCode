package Algorithms.algorithm.interviews.google;

// GOOGLE INTERVIEW:
// get4096() can return 4096 byte from a file.
// implement the getRadomNum(int readNumber) to read some data from the file.
// For example: getRadomNum(1000) will get the first 1000 bytes, call getRadomNum(1000) again 
// will return the next 1000 bytes.

public class GetRandom {
    public static void main(String[] strs) {
        //GetRandom o1 = new GetRandom();
        
        System.out.println(Math.random());
        int t = (int)(Math.random() * 100);
        
        System.out.println(t);
        
//        byte[] ret = o1.getRadomNum(6);
//        for (byte b: ret) {
//            System.out.print(b + " ");
//        }
//        
//        System.out.println();
//        
//        byte[] ret1 = o1.getRadomNum(2);
//        for (byte b: ret1) {
//            System.out.print(b + " ");
//        }
    }
    
    private final static int BUFFER_SIZE = 5;
    
    int index;
    
    byte[] buffer;
    
    public GetRandom() {
        index = -1; // when it is -1, means that the buffer is empty.
                    // index: means the start point of the data in the buffer.
        
    }
    
    public byte[] get4096() {
        byte[] buffer1 = new byte[BUFFER_SIZE];  
        for (int i = 0; i < BUFFER_SIZE; i++) {
            buffer1[i] = (byte) i;
        }
        
        return buffer1;
    }
    
    public byte[] getRadomNum(int readNumber) {
        byte[] ret = new byte[readNumber];
        
        int cnt = 0;
        
        // readout the buffer.
        while (cnt < readNumber) {
            if (index == -1) {
                buffer = get4096();
                index = 0; // reset the index;
            }
            
            while (index < BUFFER_SIZE && cnt < readNumber) {
                ret[cnt] = buffer[index];
                cnt++;
                index++;
            }
            
            // the buffer is empty.
            if (index == BUFFER_SIZE) {
                index = -1;
            }
        }
        
        return ret;
    }

}
