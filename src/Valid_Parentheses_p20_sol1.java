import java.util.*;

/*
Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

/**
 * DP solution
 * 
 * The difficulty is to compose the meaning of value in dp table and extend the Parentheses if necessary
 * 
 * We create a dp table, where the value in it means the length of valid Parentheses end at current index.
 * for each char, we got its pairing parentheses, which is just before the valid Parentheses stored in previous index.
 * If the pair match, then we will extend valid Parentheses stored in previous index by 2. 
 * Then we will lookup the value before the  pairing parentheses to see if we can get a longer valid Parentheses
 * Finally, we reach the tail, we check the value in tail with the length, and return result;
 * 
 * Here I use HashMap to avoid writing lengthy condition of matching pair
 * 
 * This solution could also solved by using stack. It is trivial, so I didn't put it here.
 * @author hpPlayer
 * @date Sep 26, 2015 1:59:15 AM
 */

public class Valid_Parentheses_p20_sol1 {  
	public static void main(String[] args){
		System.out.println(isValid("][()]"));
	}
    public static boolean isValid(String s) {
        HashMap<Character, Character> hs = new HashMap<Character, Character>();
        hs.put('(', ')');
        hs.put('[', ']');
        hs.put('{', '}');
        
        int n = s.length();
        int[] dp = new int[n];
        
        for(int i = 1; i < n; i++){
            int left = i - dp[i-1] - 1;//get the char before last Parentheses
            
            //if we have perfect Parentheses start from 0, then next '(' will always let left < 0, just skip it
            if(left < 0) continue;
            char l = s.charAt(left);
            char r = s.charAt(i);
            if(hs.containsKey(l) && r == hs.get(l)){
                //if found a new valid Parentheses
                dp[i] = dp[i-1] + 2;
                //if we can extend Parentheses further
                if(left - 1 >= 0) dp[i] += dp[left - 1];
            }
        }
        
       //System.out.println(Arrays.toString(dp));
        return dp[n-1] == n;
    }
}
