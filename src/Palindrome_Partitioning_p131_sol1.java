import java.util.*;

/*
Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

/**
 * DP + backtracking solution
 * 
 * The tricky part is to come up with the idea of building DP matrix to save time in finding palindrome
 * 
 * Why we want use DP here? because it can help us save from duplicate calculation. Ex: {"a", "a", "aaa"}, {"aa", "aaa"}
 * Without DP, we have to check "aaa" two times. So dp does help us save time
 * 
 * We will use a DP matrix, dp[i][j] means if substring from i to j is a palin or not. The way we build DP matrix is from
 * mid to two sides. So we firstly look at the core of palin, which may be single char or two chars. Then we look at len 3, len4, etc.
 * To say curr substring is palinr, we have to make sure dp[i+1][j-1] is true and current pair of char is matched. After building
 * the DP table, we just use dp value to do standard backtracking and add substring accordingly
 * 
 * Remark:
 * 1) The way we used to build the DP table is exactly same with the one we used to build DP table for problem Longest Palindromic Substring(p5)
 * 2) This problem can also be solved by pure backtracking solution just with isPalin(), but it is trivial, so I did not list it here.
 * 3) This problem can also be solved by iterative way with an inner class and stack. But I don't have time to add the solution
 * 4) Time complexity should be O(2^n)
 * @author hpPlayer
 * @date Oct 25, 2015 7:31:01 PM
 */
public class Palindrome_Partitioning_p131_sol1 {
    public boolean[][] isPalin;
    
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        
        //boundary check
        if(s == null || s.length() == 0) return result;
        
        //create dp table
        isPalin = getTable(s);
        
        DFS(result, new ArrayList<String>(), 0, s);
        
        return result;
    }
    
    public void DFS(List<List<String>> result, List<String> temp, int index, String s){
        if(index == s.length()){
            result.add(new ArrayList<String>(temp));
            return;
        }
        
        for(int i = index; i < s.length(); i++){
            //Based on dp table, if we found substring from index to i is a valid palin substring
            //we will do DFS on it
            if(isPalin[index][i]){
                String newStr = s.substring(index, i + 1);
                temp.add(newStr);
                DFS(result, temp, i + 1, s);
                temp.remove(temp.size()-1);
            }
        }
    }
    
    public boolean[][] getTable(String s){
        int n = s.length();
        //dp[i][j] means from index i to j in string s whether composes a palindrome substring
        boolean[][] dp = new boolean[n][n];
        
        //update all single mid 
        for(int i = 0; i < n; i++){
            dp[i][i] = true;    
        }
        
        //update all double mid
        for(int i = 0; i < n - 1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
            }
        }
        
        //update rest table
        //loop based on the length of different substring
        for(int len = 3; len <= n; len++){
            //check each possible start point
            for(int i = 0; i + len <=n; i++){
                //check the char at the other end of palindrome substring
                int j = i + len - 1;
                
                //if current pair of char match, and the dp value of previous two pairs is true
                //then we found a new substring plain with current len
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){
                    dp[i][j] = true;
                }
            }
        }
        
        return dp;
    }
}
