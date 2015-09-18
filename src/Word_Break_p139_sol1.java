import java.util.Set;
/**
 * DP problem, but still need the help of iterative search
 * Like other DP problems, we need build an array that records previous results
 * Each dp[i] = dp[j] + substring from j to i
 * For each dp[i], we iteratively search all indexes before it and try to find a such index that its dp[j] is true and 
 * we have a substring from j tp i that is in wordDict
 * We use this method to fill the array, and finally return the last value in array
 * 
 * Remark:
 * To be more specific, each substring can be divided into two parts:
 * left part: 0 - j-1(inclusive), where we can query from DP[] and right part: j-i (both are inclusive), where we need to search
 * the Dict
 * Of course we may have case that we contain the whole substring in word, that means left part is 0, so be careful about it
 * @author hpPlayer
 * @date Sep 18, 2015 1:22:22 AM
 */

public class Word_Break_p139_sol1 {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            //search all possible substrings from 0 to i (inclusive)
            for(int j = 0; j <= i; j++){
                //we will set dp[i] to true in two 2 cases 1) we have left part and right part 2) we contain whole
                //word, which means we only has left or right part
                //we split the string into two parts left and right, left is from 0 to j-1 (inclusive), second is 
                //from j (inclusive) to i (inclusive)
                if( (j == 0 || dp[j-1]) && wordDict.contains(s.substring(j,i+1))){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()-1];
    }
}
