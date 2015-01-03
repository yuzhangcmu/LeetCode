package Algorithms.algorithm.interviews.uber;

import java.util.*;
public class Example {
	/* sqrt(100) => 10,1 , sqrt(300) => 10,3   300 = 10^2 * 3 */
	// Running time: O(sqrt(n)) 
	public int[] sqrt(int num){
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
	
	/* given a number, find the next prime which is bigger than it */
	// Running time: O(nlogm) => m is an average recursion depth for each number, how to optimize it?
	public int getNextPrime(int value){
		if (value <= 1){
			return 2;
		}
		int target = value+1;
		while (!isPrime(target)){
			target++;
		}
		
		return target;
	}
	public boolean isPrime(int target){
		int n = 2;
		while (n*n <= target){// may overflow here!
			if (target % n == 0){
				return false;
			}
			n++;
		}
		return true;
	}
	/* given many logs <username,log_time,logout_time>, output <time,number_of_users> */
	// use two priorityqueue: O(2nlogn) ; just sort: O(2nlog2n) 
	public List<result> countRecord(List<record> records){
		if (records == null || records.size() == 0){
			return new ArrayList<result>();
		}
		List<result> rst = new ArrayList<result>();
		Collections.sort(records, new Comparator<record>(){
			@Override
			public int compare(record o1, record o2) {
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
			record tmp = records.get(i);
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
				rst.add(new result(curr2,curr));
			}
			else if (curr2 < 0 || curr1 < curr2){// go with start time
				while (startheap.size() > 0 && startheap.peek() == curr1){
					curr++;
					startheap.poll();
				}
				rst.add(new result(curr1,curr));
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
				rst.add(new result(curr1,curr));
			}
		}
		
		return rst;
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
    // wordbreak => [lock,locker, erning] ; lockerning : true, lockern : false
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
}
