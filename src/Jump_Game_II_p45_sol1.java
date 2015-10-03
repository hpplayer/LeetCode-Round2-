/*
Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/


/**
 * Greedy + sliding window
 * 
 * The tricky part is to notice that we can't be too greedy. If we always only consider the cell at from max jump, we may fall into endless
 * loop.
 * Think about the case
 * [2,4,2,0,5, 0, 1]
 * For first jump, if we only consider the cell from max jump 4, then we will reach 0 thus fall into loop
 * The correct way is to search range by range
 * 
 * Sol1 is like a variation of sliding window, we will move window as a whole not same as general sliding window where we extend right bound
 * then left bound. We will search the range of next window based on the information from current window. In this problem, we will always
 * has path to the destination (given by author), so each time at least our window will move 1 step away. For each while loop, we will
 * locate the right bound of next window, left bound is always old right bound + 1 as we don't want miss any cell in next range.
 * 
 * Why we check right reach end at two places (inside for loop and while loop?) because we may have boundary case that initial place
 * is already reach the end. The checkpoint inside for loop is always checking next jump, so we need another checkpoint to include this
 * extreme case
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 5:42:46 PM
 */
public class Jump_Game_II_p45_sol1 {
	public static void main(String[] args){
		int nums[] = {0, 0};
		System.out.println(new Jump_Game_II_p45_sol1().jump(nums));
	}
    public int jump(int[] nums) {
        //win bound, initally all 0
        int left = 0, right = 0, step = 0;
        
        //our loop will continue until we reach the last cell
        //we will also check next step inside the loop, why we don't write while(true)? 
        //because, we may reach the last cell in the first index and even not go into the loop!
        while(right < nums.length -1){
            //since we assume we can always reach the last cell, our next search range must be forward
            //otherwise will fall into a loop
            int nextRight = right + 1;//in case the problem doesn't tell us whether we can reach tail (p55), newRight initial value must be 0
            
            //check each cell in current search range
            for(int i = left; i <= right; i++){//search each cell among left and right (inclusive)
                if(i + nums[i] >= nums.length - 1){
                    //if we can reach last step in next step, just return it
                    return step + 1;
                }
                nextRight = Math.max(nextRight, i + nums[i]);
            }
            
            left = right + 1;//the smallest number in next search range
            right = nextRight;//the largest number in next search range 
            step ++;
        }
        
        return step;
    }
}
