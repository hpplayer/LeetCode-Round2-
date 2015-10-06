/*
Unique Paths II

Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/**
 * DP solution
 * 
 * This problem is not hard, I put the solution here is just to keep as a practice of DP optimization, i.e. we can reduce 
 * a 2D dp matrix to a 1D dp array
 * 
 * For this problem, we just need a row to record ways in last row, so 1D array is enough. Be careful with the boundary case!
 * Like first row, first column
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 11:38:34 PM
 */
public class Unique_Paths_II_p63_sol1 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //too boundary cases that have 0 ways 
        if(obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        
        int[] dp = new int[n];//since we need last row's info, we need an array
        dp[0] = 1;// dp[0] now is start point, which is 1 
        
        //fill first row as the boundary case
        for(int i = 1; i < n; i++){
            if(obstacleGrid[0][i] == 1) dp[i] = 0;
            else dp[i] = dp[i-1];
        }
        
        //we have taken care of first row, just skip it
        for(int i = 1; i < m; i++){
           if(obstacleGrid[i][0] == 1) dp[0] = 0;//take first column as special case
            for(int j = 1; j < n; j++ ){
                if(obstacleGrid[i][j] == 1) dp[j] = 0;
                else dp[j] = dp[j] + dp[j-1];//sum of top and left cells
            }
        }
        
        return dp[n-1];
    }
}
