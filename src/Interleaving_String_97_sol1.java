/*
Interleaving String

Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
*/	
		

/**
 * DP solution.
 * 
 * We create a dp boolean matrix[m][n]. The value in the matrix is updated based on the match of char in s1/s2 with s3. If we can find
 * a match, then we change the value to true meaning we have at least one way to interleave string in this way.Later when we update
 * next match, we can continue following this way to match rest strings. However, if it is not a match, then we set the value to false
 * so later match will not come from this way.
 * 
 * We add extra row and extra column in the matrix, which represents empty char. By doing that we can cover the boundary case that
 * we only pick chars from one substring(s1 or s2) so far. It happens when the start substring in s3 solely comes from s1 or s2.
 * Therefore, when we retrive char in string, we need - 1 to convert the index from 1 based to 0 based
 * 
 * 
 * Remark:
 * 1) we need set dp[0][0] = true, which means we use empty char in s1/s2 to match empty in s3. Obviously, it is a matched pair.
 * 2) We can reduce the space complexity to O(m) or O(n) by repeatedly using one array. But then the code will be more complicated, so for clearness
 * I still use matrix[][] here.
 * 3) Don't forget to check len(s1) + len(s2) == len(s3), otherwise we may get incorrect cases (s3 too short or too long)!!!!!!!!!!!!!
 * 
 * This problem can also be solved by backtracking + memorization, which can be seen from sol2
 * In sol2 we will return false as long as match cannot continue, so it is supposed to stop faster than DP. But we may involve duplicate
 * matches, so naive backtracking solution will get LTE, but with memorization, it will be faster than dp
 * 
 * In DP solution the time is stable which is O(mn)
 * 
 * Sol1 is DP solution
 * Sol2 is backtracking solution with memorization
 * 
 * @author hpPlayer
 * @date Oct 22, 2015 3:51:31 PM
 */
public class Interleaving_String_97_sol1 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        
        //boundary check
        if(m + n != s3.length()) return false;
        
        //s1 is the column, s2 is the row
        boolean[][] dp = new boolean[m+1][n+1];
        
        //empty s1, empty s2 matches empty s3
        dp[0][0] = true;
        
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                
                //if we can use curr char in s1 to match curr char in s3
                //since we add 0 column and row, we need - 1 to get correct index
                if(i > 0 && dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) dp[i][j] = true;
                
                //if we can use curr char in s2 to match curr char in s3
                //since we add 0 column and row, we need - 1 to get correct index                
                if(j > 0 && dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)) dp[i][j] = true;
            }
        }
        
        //the boolean value is updated based on the match of s1/s2 with s3
        //since we have checked len before, now we expect one of the last char in s1/s2 matches last char in s3
        //If no one match, then we will return false
        return dp[m][n];
    }
}
