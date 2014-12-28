public class LengthOfLastWord {
	public static void main(String[] strs) {
		System.out.println(lengthOfLastWord(" a"));
	}
	
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        String strNew = s.trim();
        
        int len = strNew.length();
        
        int start = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                start = i + 1;
                break;
            }
        }
        
        return (len - start);
    }
}