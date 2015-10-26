/*
 Dungeon Game 
  
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
The dungeon consists of M x N rooms laid out in a 2D grid.
Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2(K) -3	 3
-5	  -10	 1
10	   30	-5(P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
*/


/**
 * DP solution
 * 
 * The tricky part is to come up with the dp solution and find that it is more convenient to fill the dp matrix from bottom-up. Also we need be 
 * careful about the value in dp matrix. Acutally this problem is not so hard as it may look like.
 * 
 * We will build a DP matrix to solve this problem, the value in dp matrix means the min health we need to enter this room and survive in the rest
 * We will build the DP matrix bottom up. Why? because we don't know how to set the inital value in dp[0][0], but for dp[m-1][n-1], we know its
 * min value will be 1 or negate(dungeon[m-1][n-1] + 1). 
 * 
 * Why we need to negate the value of dungeon[][]? Because our dp value is the min health. If we can gain health from current room, actually
 * it will reduce the requirement for health, while if we will lose health from current room, actually it will increase the requirement for health.
 * So we will negate the value of dungeon[][]
 * 
 * In general case, each room can enter two rooms. Of course we will choose the room with smaller health requirement. Then we add negate value
 * of dungeon value in current cell to update the health requirement in current dp cell. We need to notice that the min health requirement to survive
 * in each cell is at least 1. So if we can gain enough health in current cell to counteract all health requirement, we still set the health requirement to 1
 * 
 * For last row and last column, it has one room to go, so we need to preprocess these two boundaries
 * 
 * 
 * Remark:
 * 
 * 1. This problem is similar to problem Minimum Path Sum(p64) and problem Unique Paths(p62), where we both use DP to solve matrix problem 
 * 2. This problem can also be optimized by rolling array technique to save space, but due to the time limitation, I will not add it here
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 3:00:15 PM
 */
public class Dungeon_Game_p174_sol1 {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        //boundary check
        if(m == 0) return 0;
        
        int n = dungeon[0].length;
        
        //build the DP table. The value in dp matrix means the min health we need to enter this room to save princess 
        int[][] dp = new int[m][n];
        
        //for the last room, if there is no demon, we need at least 1 health, if there is demon, then we need
        //at least lose health + 1 health to enter this room. In other words, after we dop certain amount of health. So here + 1 is like the health requirement from behind cells we need to consider in general case. 
        //we still have 1 hp left
        dp[m-1][n-1] = Math.max(1, -dungeon[m-1][n-1]  + 1);
        
        //two boundaries
        
        //the room in last column can only enter the room below it, so the value in it only depends on the dungeon value in current room and the dp value in the room below it
        for(int i = m - 2; i >= 0; i--){
            dp[i][n-1] = Math.max(1, -dungeon[i][n-1] + dp[i+1][n-1]);
        }
        
        //the room in last row can only enter the room on the right, so the value in it only depends on the dungeon value in current room and the dp value in the right room
        for(int i = n - 2; i >= 0; i--){
            dp[m-1][i] = Math.max(1, -dungeon[m-1][i] + dp[m-1][i+1]);
        }        
        
        //general case
        
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                //the value in dungeon matrix indicates whether we need more health, or we can counteract some
                //health requirement from room behind. Since we define the dp value to be the min health to enter
                //this and survive in the rest, we will negate the value of dungeon[][] value.
                
                //for each room we have two choices, either go down or go right. Of course, we want to choose the
                //one that needs less health.
                
                //If we find dungeon[][] value + health requirement from behind cells < 1, then we still need to 
                //set the min health requirement at current cell to be 1, otherwise the knight will die
                dp[i][j] = Math.max(1, Math.min(dp[i][j+1], dp[i+1][j]) - dungeon[i][j] );
            }
        }
        
        return dp[0][0];
    }
}
