package Algorithms.algorithm.interviews.uber;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import Algorithms.tree.TreeNode;
public class Example {
    public static void main(String[] strs) throws NoSuchAlgorithmException {
//        printArray(sqrt2(100));
//        printArray(sqrt2(300));
//        printArray(sqrt2(400));
//        printArray(sqrt2(1));
//        printArray(sqrt2(0));
//        printArray(sqrt2(-4));

//        int start = 1;
//        for (int i = 0; i < 1000; i++) {
//            System.out.print(start + " ");
//            start = getNextPrime2(start);
//        }
        
//        List<Record> records = new ArrayList<Record>();
//        records.add(new Record("a", 0, 2));
//        records.add(new Record("a", 1, 3));
//        records.add(new Record("a", 3, 5));
//        records.add(new Record("a", 2, 4));
//        records.add(new Record("a", 3, 5));
//        records.add(new Record("a", 4, 8));
//        records.add(new Record("a", 5, 10));
//        
//        List<LogResult> ret = countRecord2(records);
//        
//        for (LogResult logRet: ret) {
//            System.out.println(logRet.time + " " + logRet.number_of_users);
//        }
//        
//        System.out.println(findPeak2(records));
        
//        int[] A = {-1,0,1,2,3,5,8,9};
//        System.out.println(findIndex2(A));
//        
//        int[] A3 = {0,0,1,2,3,5,8,9};
//        System.out.println(findIndex3(A3));
//        
//        System.out.println(stringShift2("abC", 24));
//        
//        ArrayList<String> list = new ArrayList<String>();
//        list.add("cba");
//        list.add("abc");
//        list.add("bcd");
//        list.add("dogs");
//        list.add("xyz");
//        list.add("zab");
//        list.add("c");
//        list.add("d");
//        getSame(list);
        System.out.println(getShort("www.uber.com"));
        
        System.out.println(getFullUrl("f626cf"));
        
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(4);
        
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(1);
        
        root.left = node1;
        root.right = node2;
        
        root.left.left = node3;
        
        node2.left = node4;
        node2.right = node5;
        
        node5.right = node6;
        
        System.out.println(getPath(root, 10));
    }
    
    static HashMap<String, String> map = new HashMap<String, String>();
    static HashMap<String, String> mapFull = new HashMap<String, String>();
    
    public static void printArray(int[] in) {
        for (int i = 0; i < in.length; i++) {
            System.out.print(in[i] + " ");
        }
        
        System.out.println();
    }
    
	/* sqrt(100) => 10,1 , sqrt(300) => 10,3   300 = 10^2 * 3 */
	// Running time: O(sqrt(n)) 
	public static int[] sqrt(int num){
		int[] rst = {-1,-1};
		
		if (num < 0){// edge case
			return rst;
		}
		
		int n = (int) Math.sqrt(num);
		while (n >= 1){
			if (num % (n*n) == 0){
				rst[0] = n;
				rst[1] = num/(n*n);
				return rst;
			}
			n--;
		}
		return rst;
	}
	
	// Author: Yu Zhang.
	public static int[] sqrt2(int n) {
	    int[] rst = {-1, -1};

	    if (n < 0) {
	        return rst;
	    }
	    
	    if (n == 0) {
	        rst[0] = 0;
	        rst[1] = 0;
	        return rst;
	    }
	    
	    int sqrNum = (int) Math.sqrt(n);
	    
	    //System.out.println("sqrNum:" + sqrNum);
	    
	    while (sqrNum >= 1) {
	        if (n % (sqrNum * sqrNum) == 0) {
	            rst[0] = n;
	            rst[1] = n / (sqrNum * sqrNum);
	            // bug: forget to return.
	            return rst;
	        }
	        
	        sqrNum--;
	    }
	    
	    return rst;
	}
	
	/* given a number, find the next prime which is bigger than it */
	// Running time: O(nlogm) => m is an average recursion depth for each number, how to optimize it?
	public static int getNextPrime(int value){
		if (value <= 1){
			return 2;
		}
		
		int target = value + 1;
		
		// Yu Zhang optimize. Odds will not be prime.
		if (target % 2 == 0) {
		    target++;
		}
		
		while (!isPrime(target)){
		    // Yu Zhang optimize 1: target += 2.
		    target += 2;
			//target++;
		}
		
		return target;
	}
	
	public static boolean isPrime(int target){
		int n = 2;
		while (n*n <= target){// may overflow here!
			if (target % n == 0){
				return false;
			}
			n++;
		}
		return true;
	}
	
	// Yu Zhang solution:
	//http://stackoverflow.com/questions/6654671/whats-the-built-in-function-that-finds-next-largest-prime-number-in-java
	//https://www.youtube.com/watch?v=nkIt8M9asaM
	public static int getNextPrime2 (int val) {
	    if (val <= 1) {
	        return 2;
	    }
	    
	    val++;
	    if (val % 2 == 0) {
	        val++;
	    }
	    
	    while (!isPrime2(val)) {
	        val += 2;
	        if (val < 0) {
	            return -1;
	        }
	    }
	    
	    return val;
	}
	
	public static boolean isPrime2(int target){
	    int n = 3;
	    
	    // yu zhang: use / to avoid overflow.
	    
	    int sqrt = (int)Math.sqrt(target);
	    while (n <= sqrt) {
	        if (target % n == 0) {
	            return false;
	        }
	        
	        // bug: should addup n;
	        // Yu Zhang optimize: target will never be divided by odd.
	        n += 2;
	    }
	    
	    return true;
    }
	
	public static class Record {
	    String username;
	    int log_time;
	    int logout_time;
        public Record(String username, int log_time, int logout_time) {
            super();
            this.username = username;
            this.log_time = log_time;
            this.logout_time = logout_time;
        }
	}
	
	public static class LogResult {
	    int time;
	    int number_of_users;
	    
	    LogResult (int time, int number_of_users) {
	        this.time = time;
	        this.number_of_users = number_of_users;
	    }
	}
	
	/* given many logs <username,log_time,logout_time>, output <time,number_of_users> */
	// use two priority queue: O(2nlogn) ; just sort: O(2nlog2n) 
	public List<LogResult> countRecord(List<Record> records){
		if (records == null || records.size() == 0){
			return new ArrayList<LogResult>();
		}
		
		List<LogResult> rst = new ArrayList<LogResult>();
		Collections.sort(records, new Comparator<Record>(){
			@Override
			public int compare(Record o1, Record o2) {
				// TODO Auto-generated method stub
				return o1.log_time - o2.log_time;
			}
		});
		
		PriorityQueue<Integer> endheap = new PriorityQueue<Integer>(records.size(),new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		
		PriorityQueue<Integer> startheap = new PriorityQueue<Integer>(records.size(),new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		});
		
		int curr,i;
		
		for (i=0;i<records.size();i++){
		    Record tmp = records.get(i);
			startheap.offer(tmp.log_time);
			endheap.offer(tmp.logout_time);
		}
		
		// output
		curr = 0;
		while (startheap.size() > 0 || endheap.size() > 0){
			int curr1 = startheap.size() > 0 ? startheap.peek() : -1;
			int curr2 = endheap.size() > 0 ? endheap.peek() : -1;
			if (curr1 < 0 || curr1 > curr2){// only end time left
				while (endheap.size() > 0 && endheap.peek() == curr2){
					curr--;
					endheap.poll();
				}
				rst.add(new LogResult(curr2,curr));
			}
			else if (curr2 < 0 || curr1 < curr2){// go with start time
				while (startheap.size() > 0 && startheap.peek() == curr1){
					curr++;
					startheap.poll();
				}
				rst.add(new LogResult(curr1,curr));
			}
			else{// curr1 == curr2
				while (endheap.size() > 0 && endheap.peek() == curr2){
					curr--;
					endheap.poll();
				}
				while (startheap.size() > 0 && startheap.peek() == curr1){
					curr++;
					startheap.poll();
				}
				rst.add(new LogResult(curr1,curr));
			}
		}
		
		return rst;
	}
	
	/* given many logs <username,log_time,logout_time>, output <time,number_of_users> */
    // use two priority queue: O(2nlogn) ; just sort: O(2nlog2n) 
	
	// Yu Zhang solution.
    public static List<LogResult> countRecord2(List<Record> records){
        ArrayList<LogResult> ret = new ArrayList<LogResult>();
        
        if (records == null || records.size() == 0){
            return new ArrayList<LogResult>();
        }
        
        ArrayList<LogResult> timeList = new ArrayList<LogResult>();
        for (Record record: records) {
            timeList.add(new LogResult(record.log_time, 1));
            timeList.add(new LogResult(record.logout_time, -1));
        }
        
        // bug: forget the right way to write comparator.
        Collections.sort(timeList, new Comparator<LogResult>(){
            public int compare(LogResult o1, LogResult o2) {
                return o1.time - o2.time;
            }
        });
        
        int num = 0;
        for (int i = 0; i < timeList.size(); i++) {
            num += timeList.get(i).number_of_users;
            
            if (i == timeList.size() - 1 
                    || (timeList.get(i).time != timeList.get(i + 1).time)) {
                ret.add(new LogResult(timeList.get(i).time, num));
            }
        }
        
        return ret;
    }
	
	// decode ways: 1-26 => 'A'-'Z'
    public int numDecodings(String s){
    	if (s == null || s.length() == 0){
    		return 0;
    	}
    	int last = 0;
    	int curr = s.charAt(0) == '0' ? 0 : 1;
    	int next = last + curr;
    	int i,copy = 0;
    	
    	for (i = 0;i < s.length();i++){
    		next = 0;
    		if (s.charAt(i) >= '1' && s.charAt(i) <= '9'){
    			next = curr;
    		}
    		int value = copy*10 + s.charAt(i)-'0';
    		
    		if (value >= 10 && value <= 26){
    			next += last;
    		}
    		copy = value % 10;
    		
    		last = curr;
    		curr = next;
    	}
    	
    	return next;
    }
    
    // Yu Zhang solution.
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        int[] D = new int[len + 1];
        for (int i = 0; i <= len; i++) {
            if (i == 0) {
                D[i] = 1;
            } else {
                D[i] = 0;
                if (isValidSingle(s.charAt(i - 1))) {
                    D[i] += D[i - 1];
                }
                
                if (i >= 2 && isValidTwo(s.substring(i - 2, i))) {
                    D[i] += D[i - 2];
                }
            }
        }
        
        return D[len];
    }
    
    public boolean isValidSingle(char c) {
        return c != '0';
    }
    
    public boolean isValidTwo(String s) {
        int num = Integer.parseInt(s);
        return num <= 26 && num >= 10;
    }
    
    // solution 3:
    public int numDecodings3(String s) {
        // bug 1: return 0 when len is 0.
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int len = s.length();
        
        // The result of first i digits.
        int pre = 1;
        int prepre = 0;
        
        for (int i = 1; i <= len; i++) {
            // i >= 1
            int cur = 0;
            if (i >= 2 && isValidTwo(s.substring(i - 2, i))) {
                cur += prepre;
            }
            
            // The digit should not be 0.
            if (s.charAt(i - 1) != '0') {
                cur += pre;
            }
            
            prepre = pre;
            pre = cur;
        }
        
        return pre;
    }
    
    // Wordbreak => [lock,locker, erning] ; lockerning : true, lockern : false
    public boolean wordBreak(String s, Set<String> dict){
    	if (dict == null || s == null || s.length() == 0 || dict.size() == 0){
    		return false;
    	}
    	int m = s.length();
    	int range = 0;
    	int i,j;
    	boolean[] dp = new boolean[m+1];
    	dp[0] = true;
    	String curr;
    	
    	for (i=1;i<=s.length();i++){
    		for (j=0;j<i;j++){
    			curr = s.substring(j,i);// current substring
    			if (dict.contains(curr) && (dp[j] || j < range)){
    				range = Math.max(range,i-1);
    				dp[i] = true;
    				break;
    			}
    		}
    	}
    	
    	return dp[m];
    }
    
    // yu zhang solution.
    public boolean wordBreak2(String s, Set<String> dict) {
        if(s == null || dict == null) {
            return false;
        }
        
        // bug 2: length()
        int len = s.length();
        // bug 1: use boolean not int.
        boolean[] D = new boolean[len + 1];
        
        for (int i = 0; i <= len; i++) {
            if (i == 0) {
                D[i] = true;
            } else {
                for (int j = 0; j < i; j++) {
                    if (D[j] && dict.contains(s.substring(j, i))) {
                        D[i] = true;
                        break;
                    }
                }
            }
        }
        
        return D[len];
    }
    
    // find the most plights in the sky
 	class Point{
		int x; // start time
		int y; // end time

		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
 	}
    
    public int findPeak(List<Point> flights){
    	if (flights == null || flights.size() == 0){
    		return 0;
    	}
    	int max = 0,i,curr = 0;
    	int k = flights.size();
    	PriorityQueue<Integer> startheap = new PriorityQueue<Integer>(k,new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
    	});
    	PriorityQueue<Integer> endheap = new PriorityQueue<Integer>(k,new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
    	});
    	for (i=0;i<flights.size();i++){
    		if (flights.get(i).x > flights.get(i).y){// edge case for the input
    			continue;
    		}
    		startheap.offer(flights.get(i).x);
    		endheap.offer(flights.get(i).y);
    	}
    	// get peak points via loop scan
    	int curr1,curr2;
    	while (startheap.size() > 0 && endheap.size() > 0){
    		curr1 = startheap.peek();
    		curr2 = endheap.peek();
    		
    		if (curr1 < curr2){
				while (startheap.size() > 0 && startheap.peek() == curr1){
					curr++;
					startheap.poll();
				}
				
			}
			else if (curr2 < curr1){
				while (endheap.size() > 0 && endheap.peek() == curr2){
					curr--;
					endheap.poll();
				}
			}
			else{// curr1 == curr2
				while (startheap.size() > 0 && startheap.peek() == curr1){
					curr++;
					startheap.poll();
				}
				while (endheap.size() > 0 && endheap.peek() == curr2){
					curr--;
					endheap.poll();
				}
			}
			max = Math.max(max, curr);
		}
    	
    	return max;
    }
    
 // Yu Zhang solution.
    public static int findPeak2(List<Record> records){
        ArrayList<LogResult> ret = new ArrayList<LogResult>();
        
        if (records == null || records.size() == 0){
            return 0;
        }
        
        ArrayList<LogResult> timeList = new ArrayList<LogResult>();
        for (Record record: records) {
            timeList.add(new LogResult(record.log_time, 1));
            timeList.add(new LogResult(record.logout_time, -1));
        }
        
        // bug: forget the right way to write comparator.
        Collections.sort(timeList, new Comparator<LogResult>(){
            public int compare(LogResult o1, LogResult o2) {
                return o1.time - o2.time;
            }
        });
        
        int num = 0;
        int max = 0;
        for (int i = 0; i < timeList.size(); i++) {
            num += timeList.get(i).number_of_users;
            
            if (i == timeList.size() - 1 
                    || (timeList.get(i).time != timeList.get(i + 1).time)) {
                ret.add(new LogResult(timeList.get(i).time, num));
            }
            
            max = Math.max(max, num);
        }
        
        return max;
    }
    
    /* given many logs <username,log_time,logout_time>, output <time,number_of_users> */
	// use two priorityqueue: O(2nlogn) ; just sort: O(2nlog2n) 
//	public List<result> countRecord(List<record> records){
//		if (records == null || records.size() == 0){
//			return new ArrayList<result>();
//		}
//		List<result> rst = new ArrayList<result>();
//		Collections.sort(records, new Comparator<record>(){
//			@Override
//			public int compare(record o1, record o2) {
//				// TODO Auto-generated method stub
//				return o1.log_time - o2.log_time;
//			}
//		});
//		PriorityQueue<Integer> endheap = new PriorityQueue<Integer>(records.size(),new Comparator<Integer>(){
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o1-o2;
//			}
//		});
//		PriorityQueue<Integer> startheap = new PriorityQueue<Integer>(records.size(),new Comparator<Integer>(){
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				// TODO Auto-generated method stub
//				return o1-o2;
//			}
//		});
//		int curr,i;
//		for (i=0;i<records.size();i++){
//			record tmp = records.get(i);
//			startheap.offer(tmp.log_time);
//			endheap.offer(tmp.logout_time);
//		}
//		// output
//		curr = 0;
//		while (startheap.size() > 0 || endheap.size() > 0){
//			int curr1 = startheap.size() > 0 ? startheap.peek() : -1;
//			int curr2 = endheap.size() > 0 ? endheap.peek() : -1;
//			if (curr1 < 0 || curr1 > curr2){// only end time left
//				while (endheap.size() > 0 && endheap.peek() == curr2){
//					curr--;
//					endheap.poll();
//				}
//				rst.add(new result(curr2,curr));
//			}
//			else if (curr2 < 0 || curr1 < curr2){// go with start time
//				while (startheap.size() > 0 && startheap.peek() == curr1){
//					curr++;
//					startheap.poll();
//				}
//				rst.add(new result(curr1,curr));
//			}
//			else{// curr1 == curr2
//				while (endheap.size() > 0 && endheap.peek() == curr2){
//					curr--;
//					endheap.poll();
//				}
//				while (startheap.size() > 0 && startheap.peek() == curr1){
//					curr++;
//					startheap.poll();
//				}
//				rst.add(new result(curr1,curr));
//			}
//		}
//		
//		return rst;
//	}
	
	// spiral matrix
    public List<Integer> spiralMatrix(int[][] matrix){
    	if (matrix == null || matrix.length == 0){
    		return new ArrayList<Integer>();
    	}
    	List<Integer> rst = new ArrayList<Integer>();
    	
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int top = 0;
    	int bottom = m-1;
    	int left = 0;
    	int right = n-1;
    	int i;
    	int direction = 0;
    	while (true){
    		if (direction == 0){
    			for (i=left;i<=right;i++){
    				rst.add(matrix[top][i]);
    			}
    			top++;
    		}
    		else if (direction == 1){
    			for (i=top;i<=bottom;i++){
    				rst.add(matrix[i][right]);
    			}
    			right--;
    		}
    		else if (direction == 2){
    			for (i=right;i>=left;i--){
    				rst.add(matrix[bottom][i]);
    			}
    			bottom--;
    		}
    		else{
    			for (i=bottom;i>=top;i--){
    				rst.add(matrix[i][left]);
    			}
    			left++;
    		}
    		//break
    		if (left > right || top > bottom){
    			break;
    		}
    		// switch direction
    		direction = (direction+1) % 4;
    	}
    	
    	return rst;
    }
    
    // Solution 4: Use Four corners.
    // Author: Yu Zhang.
    // spiral matrix
    public List<Integer> spiralOrder4(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        if (matrix == null ||matrix.length == 0) {
            // 注意在非法的时候，应该返回空解，而不是一个NULL值
            return ret;
        }
        
        // Record how many rows and cols we still have.
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // The four corners.
        int top = 0;
        int left = 0;
        int bottom = rows - 1;
        int right = cols - 1;
        
        // every time we go through two rows and two cols.
        for (; rows > 0 && cols > 0; rows -= 2, cols -= 2, top++, left++, bottom--, right--) {
            // the first line.
            for (int i = left; i <= right; i++) {
                ret.add(matrix[top][i]);
            } 
            
            // the right column.
            for (int i = top + 1; i < bottom; i++) {
                ret.add(matrix[i][right]);
            }
            
            // the down line;
            if (rows > 1) {
                for (int j = right; j >= left; j--) {
                    ret.add(matrix[bottom][j]);
                }
            }
            
            // the left column.
            if (cols > 1) {
                for (int i = bottom - 1; i > top; i --) {
                    ret.add(matrix[i][left]);
                }
            }
        }
        
        return ret;
    }
    
    // find the index of array in which A[i] = i, sorted array
    /* problem1: no duplicate inside */
    public int findIndex(int[] num){
    	if (num == null || num.length == 0){
    		return -1; // defined to be the edge case number
    	}
    	int left = -1;
    	int right = num.length;
    	int mid;
    	
    	while (right - left > 1){
    		mid = left + (right-left)/2;
    		if (num[mid] == mid){
    			return mid;
    		}
    		else if (num[mid] > mid){// left
    			right = mid;
    		}
    		else{
    			left = mid;
    		}
    	}
    	return -1;
    }
    
    /* problem2: duplicate exists */
    public static int findIndex2(int[] num){
    	if (num == null || num.length == 0){
    		return -1;
    	}
    	return findMagic2(num,0,num.length-1);
    }
    
    public int findMagic(int[] num, int left, int right){
    	if (left < 0 || right >= num.length || left > right){
    		return -1;
    	}
    	int mid,curr;
    	
    	mid = left + (right-left)/2;
    	if (num[mid] == mid){
    		return mid;
    	}
    	else{
    		// optimize here, shorten the length of binary search scope
    		curr = Math.min(num[mid], mid-1);
    		int leftIndex = findMagic(num,left,curr);
    		if (leftIndex >= 0){
    			return leftIndex;
    		}
    		curr = Math.max(mid+1, num[mid]);
    		int rightIndex = findMagic(num,curr,right);
    		return rightIndex;
    	}
    }
    
    // Yu Zhang solution:
    
    public static ArrayList<Integer> findIndex3(int[] num){
        if (num == null || num.length == 0){
            return null;
        }
        
        ArrayList<Integer> ret = new ArrayList<Integer>(); 
        findMagic3(num,0,num.length-1, ret);
        return ret;
    }
    
    public static int findMagic2(int[] num, int left, int right){
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (num[mid] == mid) {
            return mid;
        }
        
        if (num[mid] >= left) {
            int ret = findMagic2(num, left, mid - 1);
            if (ret != -1) {
                return ret;
            }
        }
        
        if (num[mid] <= right) {
            int ret = findMagic2(num, mid + 1, right);
            if (ret != -1) {
                return ret;
            }
        }
        
        return -1;
    }
    
    // There are duplicate solutions.
    public static void findMagic3(int[] num, int left, int right, ArrayList<Integer> ret){
        if (left > right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        
        if (num[mid] == mid) {
            ret.add(mid);
        }
        
        if (num[mid] >= left) {
            findMagic3(num, left, mid - 1, ret);
        }
        
        if (num[mid] <= right) {
            findMagic3(num, mid + 1, right, ret);
        }
    }
    
    /* string shift */
    public String stringShift(String s,int shift){
    	if (s == null || s.length() == 0 || shift%26 <= 0){
    		return s;
    	}
    	StringBuilder sb = new StringBuilder();
    	char curr;
    	shift = shift%26;
    	
    	for (int i=0;i<s.length();i++){
    		if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
    			curr = (char)('a'+(s.charAt(i)-'a'+shift)%26);
    			sb.append(curr);
    		}
    		else if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
    			curr = (char)('A'+(s.charAt(i)-'A'+shift)%26);
    			sb.append(curr);
    		}
    		else{
    			// throw an exception here!
    		}
    	}
    	
    	return sb.toString();
    }
    
    /* string shift */
    public static String stringShift2(String s,int shift){
        if (s == null || s.length() == 0 || shift%26 <= 0){
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        
        shift = shift % 26;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int num = 0;
            if (c <= 'z' && c >= 'a') {
                num = c - 'a';
                num += shift;
                num %= 26;
                num += 'a';
            } else if (c <= 'Z' && c >= 'A') {
                num = c - 'A';
                num += shift;
                num %= 26;
                num += 'A';
            }
            
            char c1 = (char)num;
            sb.append(c1);
        }

        
        return sb.toString();
    }
    
    public static void getSame(ArrayList<String> input) {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        if (input == null) {
            return;
        }
        
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>(); 
        
        for (String str: input) {
            char c1 = str.charAt(0);
            
            int shift = c1 - 'a';
            
            int len = str.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);
                int num = c - 'a';
                
                num -= shift;
                if (num < 0) {
                    num += 26;
                }
                
                num += 'a';
                sb.append((char)num);
            }
            
            String strNew = sb.toString();
            if (map.containsKey(strNew)) {
                map.get(strNew).add(str);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(str);
                map.put(strNew, list);
            }
        }
        
        System.out.println(map.toString());
    }
    
    public static String getShort(String url) throws NoSuchAlgorithmException {
        if (url == null) {
            return "";
        }
        
        if (mapFull.containsKey(url)) {
            return mapFull.get(url);
        }
        
        String original = url;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        System.out.println("original:" + original);
        System.out.println("digested(hex):" + sb.toString());
        
        String md5 = sb.toString();

        String shortUrl = md5.substring(0, 6);        
        if (map.containsKey(shortUrl)) {
            for (int i = 0; i < shortUrl.length(); i++) {
                StringBuilder sbChange = new StringBuilder(shortUrl);
                for (char c = 'a'; c <= 'z'; c++) {
                    sbChange.setCharAt(i, c);
                    shortUrl = sbChange.toString();
                    if (!map.containsKey(shortUrl)) {
                        break;
                    }
                }
            }
            map.put(shortUrl, url);
        } else {
            map.put(shortUrl, url);
        }
        
        mapFull.put(url, shortUrl);
        
        return "http://uber.gl/" + shortUrl;
    }
    
    public static String getFullUrl(String url){
        if (url == null) {
            return "";
        }
        
        if (map.containsKey(url)) {
            return map.get(url);
        } else {
            return "";
        }
    }
    
    public static ArrayList<ArrayList<Integer>> getPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        
        getPath(root, new ArrayList<Integer>(), target, ret);
        return ret;
    }
    
    public static void getPath(TreeNode root, ArrayList<Integer> path, int target,
            ArrayList<ArrayList<Integer>> ret) {
        if (root == null) {
            return;
        }
        
        path.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            ret.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        
        if (target < 0) {
            path.remove(path.size() - 1);
            return;
        }
        
        getPath(root.left, path, target, ret);
        getPath(root.right, path, target, ret);
        path.remove(path.size() - 1);
    }
}
