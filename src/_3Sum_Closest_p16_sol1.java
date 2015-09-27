import java.util.Arrays;

/*
3Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

/**
 * Two pointer problem.
 * 
 * It is very similar to problem 3_sum (p15)
 * The tricky part is to don't be confused with difference and sum.
 * Our sum is the result we want to return while the difference is the condition we used to update sum.
 * We scan whole loop, for each index, we use another two pointers to scan the remaining array.
 * If we found a smaller difference, then we update the result.
 * For the sum, if it is larger than target, we let right pointer move left. if it is smaller, we let the left pointer move right
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 11:50:48 PM
 */

public class _3Sum_Closest_p16_sol1 {
	public static void main(String[] args){
		int nums[] = {1,1,-1,-1,3};
		System.out.println(new _3Sum_Closest_p16_sol1().threeSumClosest(nums, -1));
	}
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        
        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.length; i++){
            int start = i + 1;
            int end = nums.length - 1;
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(sum == target) return target;
                //minDiff will always store abs_diff, so no need to abs(minDiff)
                if(Math.abs(sum - target) < minDiff){
                    minDiff = Math.abs(sum - target);
                    result = nums[i] + nums[start] + nums[end];
                }
                
                if(sum < target){
                    start ++;
                }else{
                    //sum > target
                    end --;
                }
            }
        }
        
        return result;
    }
}
