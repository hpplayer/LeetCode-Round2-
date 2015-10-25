/*
Distinct Subsequences

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without
disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/**
 * DP solution
 * 
 * The tricky part is to come up with the dp solution
 * 
 * This problem is similar to problem Edit Distance(p72), but now we only allow to delete chars in s. Also in p72, we need to get the min distance to reach t,
 * here we need to get the ways to reach t. So there is still a little difference.
 * 
 * We still create a dp matrix for string s and t. The value in dp matrix means from [0,i] in s how many ways we can form [0,j] in t.
 * 
 * For matched char pair, we have two choices: 1) use it so get value from dp[i-1][j-1] 2) not use it as they are unmatched pair case, so get value
 * from dp[i-1][j] 
 * For unmatched char pair, we have only one choice: not use it, so we get value from dp[i-1][j]
 * 
 * Finally, we just need to return the value in last cell, and it will be our solution
 * 
 * Remark:
 * 1. We can reduce the space complexity from O(mn) to O(n), since each cell only requires prev cell in same row and the cell above prev cell in same row,
 * but I did not put it here due to time
 * 2. This problem is similar to problem Interleaving String (p97), but in that problem we only fill the boolean[][], now we need fill int[][], so
 * I would say it is more similar to problem Edit Distance(p72)
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 12:25:34 AM
 */
public class Distinct_Subsequences_p115_sol1 {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        //boundary case
        if(m < n) return 0;
        
        //include extra col and row, so we can cover the case that not use first char in S, or t is empty
        //dp value means from 0 to i in s how many ways we can form the substring from 0 to j in t
        int[][] dp = new int[m+1][n+1];
        
        //if t is empty (first col), we have exactly one way to form t from s, i.e. remove all chars
        //so fill first column to be 1
        for(int i = 0; i <= m; i++){
            dp[i][0] = 1;
        }
        
        //fill dp table
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                
                //matched pair, we have two choices, use curr char in s or not
            	//we add extra row/col, so the index in matrix is one unit larger than real index in string
                if(s.charAt(i-1) == t.charAt(j-1)){
                    //[i-1][j-1] represents we use curr char
                    //[i-1][j] represents we not use curr char, so we still use prev char in s to match
                    //curr char in t
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    //not matched pair, we have only one choice
                    dp[i][j] = dp[i-1][j];
                    
                }
                
            }
        }
        
        //finally return dp[m][n], which represents from 0 to m in string s how many ways we can form substring
        //from 0 to n in string t
        return dp[m][n];
    }
}
