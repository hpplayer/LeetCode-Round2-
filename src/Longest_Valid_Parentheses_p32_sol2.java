/**
 * DP solution
 * 
 * Tricky part is to find there are two match cases 1) match the ( just before it 2) match the ( before the previous complete part
 * Also, don't forget to extend the () if possible
 * This problem is an advanced version of problem Valid Parentheses (p20)
 * 
 * 
 * As other dp problems, we need create a dp array. The value in dp array means the longest valid parentheses ended at current index.
 * So we will skip all "(" as Parentheses won't end at "(". For ")", we can firstly check the char just before it. If it is "(", then 
 * we found a match, then we check the value in index i-2 to see if we can further extended it. If the char before it is not "(", then
 * we will try to match with the "(" before the complete part ended at index i-1, say this index is k, similarly, then we check the value
 * in k - 1 to see if we can further extended it. Notice: our final length can also contains the nested Parentheses like (()) will be counted
 * as 4. So for second case, we also need to see if we can include nested Parentheses if possible
 *  
 * @author hpPlayer
 * @date Sep 29, 2015 10:26:46 PM
 */
public class Longest_Valid_Parentheses_p32_sol2 {
    public int longestValidParentheses(String s) {
        //the value in dp[] means the longest Valid Parentheses ended at i 
        int[] dp = new int[s.length()];
        int result = 0;
        
        for(int i = 1; i < s.length(); i++){
            //if current char is '(', then the Parentheses will always be invalid
            if(s.charAt(i) == '(') continue;
            
            //if we can match ')' with '(' at i-1
            if(s.charAt(i-1) == '('){
                //at least we have len 2
                dp[i] = 2;
                //maybe we can extend it
                dp[i] += i - 2>= 0? dp[i-2] : 0;
            }else{
                //if we can't match ')' with ')' at i -1, then we will try to match 
                //current ')' with '(' at index k which is before the complete matches recorded at i -1
                int k = i - dp[i-1] - 1;
                
                if(k >= 0 && s.charAt(k) == '('){
                    //at least we have len 2
                    dp[i] = 2;
                    //maybe we can extend it and add complete matches recorded at i -1
                    dp[i] += dp[i-1] + (k-1 >= 0? dp[k-1] : 0);
                }
            }
            
            result = Math.max(result, dp[i]);
        }
        
        return result;
        
    }
}
