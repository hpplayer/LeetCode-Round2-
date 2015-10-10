/*
Paint House II

There are a row of n houses, each house can be painted with one of the k colors.
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
*/

/**
 * DP solution
 * 
 * Similar to problem Paint Fence (p276) and problem House Robber (p198), where current cell's value depends on the previous decision, but
 * here is an advanced version. The tricky part is to observe actually we just need three parameters from previous house. For 
 * general colors, we just want its new value be replaced as current cost + min Cost from previous house. For the color that 
 * gives the min Costs for previous house, we need its value be replaced by current value + second min Cost from previous house.
 * So we need three parameters: min costs from prev house, second min costs from prev house and the index for the color that gives
 * the min cost.
 * 
 * Remark:
 * During the update, we need three temp variables to help us get values of curr row for those parameters.
 * 
 * 
 * @author hpPlayer
 * @date Oct 10, 2015 3:05:52 AM
 */
public class Paint_House_II_p265_sol1 {
    public int minCostII(int[][] costs) {
        //boundary check
        if(costs.length == 0) return 0;
        
        //we need three parameters from prev row
        //which color gives min total price, the index of this color and which color gives the second
        //min total price
        int prevMin1 = Integer.MAX_VALUE, prevMin2 = Integer.MAX_VALUE, index = 0;
        
        for(int i = 0; i < costs.length; i++){
            //we need three temp variables here to help us get value for current row while not 
            //overwrite previous value before we are done with current row
            int tempMin1 = Integer.MAX_VALUE, tempMin2 = Integer.MAX_VALUE, tempIndex = 0;
            for(int j = 0; j <costs[0].length; j++){
                //for the min color that gives previous min value, we will add the second min value to it
                //for other colors we will add the min value to them
                int val = costs[i][j] + (j == index? prevMin2 : prevMin1);
                
                if(val < tempMin1){
                    //we found a value smaller than min1, then decrease curr min1 to min2, then update min1
                    tempMin2 = tempMin1;
                    tempMin1 = val;
                    tempIndex = j;
                }else if (val < tempMin2){
                    //we found a value smaller than min2, then we simply replace the old min2
                    tempMin2 = val;
                }
            }
            
            //we done curr row, update prev info to curr info, then go to next row
            prevMin1 = tempMin1;
            prevMin2 = tempMin2;
            index = tempIndex;
        }
        
        
        //finally we just need to return min value, which is in prevMin1
        return prevMin1;
    }
}
