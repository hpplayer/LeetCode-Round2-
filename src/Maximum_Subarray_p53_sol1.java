/*
Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/**
 * DP solution.
 * 
 * This problem is very similar to House Robber (p198), we can either use previous sum, or just use current value.
 * 
 * The solution below uses constant space, but we can firstly build a dp[], then observe and optimize the space
 * The value in dp[] is the maxSum ended at current index. It may come from taking cumulative sum from previous subarray
 * or may come from the cell it self, we take the max one. Then we compare this subArray sum with result and decide whether 
 * to update it
 * 
 * Sol1 is dp approach, time complexity is O(n)
 * Sol2 is divide-and-conquer approach, time complexity is O(nLogN)
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 10:30:53 PM
 */
public class Maximum_Subarray_p53_sol1 {
    public int maxSubArray(int[] nums) {
        //currMax is the max subarray ended at current index
        //it may be the value in current index alone, or a contiguous subarray ended at current index
        int currMax = nums[0];
        int result = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            //we can either take value before it or not
            currMax = Math.max(currMax + nums[i], nums[i]);
            result = Math.max(currMax, result);
        }
        
        return result;
        
    }
}
