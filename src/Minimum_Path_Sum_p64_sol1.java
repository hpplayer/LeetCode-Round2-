/*
Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/

/**
 * DP solution
 * 
 * This problem is very similar to Unique Paths II (p63), not hard
 * The matrix dp solution is trivial, so I did not put it here
 * 
 * I put this solution here because I think below solution is very elegant and handle boundary case beautifully
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 12:07:14 AM
 */
public class Minimum_Path_Sum_p64_sol1 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length; 
        
        ///we need information from last row, so need an extra dp array
        int[] dp = new int[n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) dp[0] = grid[0][0];//special case that we only copy value from grid
                //we handle first row and column beautifully, for first row, we just compare dp[j-1] and dp[j-1]
                //for first column, we just compare dp[j] and dp[j], for other cases, we compare dp[j] and dp[j-1]
                else dp[j] = grid[i][j] + Math.min( (i == 0? dp[j-1] : dp[j]), (j == 0? dp[j] : dp[j-1])  );
            }
        }
        
        return dp[n-1];
    }
}
