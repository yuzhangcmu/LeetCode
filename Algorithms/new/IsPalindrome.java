
public class IsPalindrome {
	public static void main(String[] strs) {
		System.out.print(isPalindrome(12344321));
	}
	
    public static boolean isPalindrome(int x) {
        long y = 0;
        
        if (x < 0) {
        	return false;
        }
        
        int tmp = x;
        while (tmp > 0) {
            int a = tmp % 10;
            tmp /= 10;
            
            y *= 10;
            y += a;
        }
        
        return y == x;
    }
}