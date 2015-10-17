/*
Decode Ways

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/**
 * DP problem. Exhausted recursive solution will return LTE
 * 
 * The difficulty is handling corner cases including 0, 2 digits start with 0, 2 digits >= 26
 * To be convenient, we use a function canPair() to check the validity of pair.
 * The DP approach goes like this: we scan the whole input string, for each digit, we will look at its value and its prev digit
 * 1) If current digit is not 0, then we will have one way to decode it based on previous decode result, otherwise we cannot decode it as treat
 * it like a single digit.
 * 2) If prev + curr digits compose a valid number from 10 - 26, then we will have an extra way to decode it based on prev digit's decode result
 * then we will sum 1) and 2) to fill current cell
 * 
 * Initially, we can build a dp[] to make code more easily understanding. But we can further simplify the array to only 3 variables
 * since we only need three values for each digit: value from 1 step away, value from 2 steps away, result of current value which
 * will be updated as the value from 1 step away in next loop
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 7:06:12 PM
 */
public class Decode_Ways_p91_sol1 {
	public static void main(String[] args){
		System.out.println(new Decode_Ways_p91_sol1().numDecodings("00"));
	}
	
    public int numDecodings(String s) {
        if(s== null || s.length() == 0) return 0;
        int num1 = s.charAt(0) == '0'? 0 : 1;//num 1 step away
        //we will set its initial value to 1, so if inital pair is valid, then we can at least have one way to 
        //decode this initial pair
        int num2 = 1;//num 2 steps away
        
        for(int i = 1; i < s.length(); i++){
            int sum = 0;//current digit's value
            if(s.charAt(i) != '0'){
                sum = num1;
            }
            
            if(canPair(s.substring(i-1))){
                sum += num2;
            }
            
            num2 = num1;
            num1 = sum;
        }
        
        return num1;
    }
    
    public int numDecodings2(String s) {
        if(s == null || s.length() == 0) return 0;
        
        int len = s.length();
        int[] dp = new int[len];
        dp[0] = s.charAt(0) == '0'? 0 : 1;
        for(int i =1; i < s.length(); i++){
            if(s.charAt(i) != '0'){
                dp[i] = dp[i-1];
            }
            
            if(canPair(s.substring(i-1))){
                dp[i] += i == 1? 1 : dp[i-2];
            }
            
        }
        
        return dp[len-1];
    }
    
    
    public boolean canPair(String s){
        if(s.charAt(0) == '0') return false;
        
        return Integer.valueOf(s.substring(0, 2)) < 27; 
    }
}
