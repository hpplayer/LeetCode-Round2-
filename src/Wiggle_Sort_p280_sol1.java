/*
Wiggle Sort

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
*/

/**
 * Observation + sorting
 * 
 * This solution is very problem specific.
 * We found the peak is always at odd index. So we can compare each index with its previous cell.
 * If current cell's index is odd, then its previous part should be <= it. Otherwise we will swap them
 * Similarly, if current cell's index is even, then its previous part should be >= it. Otherwise we will swap them.
 * So we always fix the array pair by pair. Is it possible our later operation will destroy the order of previous pair?
 * That's not possible. Say i is odd index, i + 1 is even index, so nums[i+1] < nums[i], if we need swap nums[i+1] with nums[i+2],
 * then that must because nums[i+2] is even smaller than nums[i+1], so we need to move it from an even index to an odd index, thus
 * the nums[i+2] must be < nums[i+1] (we will not move if they equal), so the order is still kept. Same logic also applies to 
 * the other case.
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 2:06:05 AM
 */
public class Wiggle_Sort_p280_sol1 {
    public void wiggleSort(int[] nums) {
        //0 is inital case, since it does not have previous value
        for(int i = 1; i < nums.length; i++){
            //if it is even and > the value at odd index before it, then we need to swap them
            //if it is odd and < the value at the even index before it, then we need to swap them
            if( (i%2 == 0 && nums[i] > nums[i-1]) || (i%2 == 1 && nums[i] < nums[i-1])){
                //swap
                int temp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = temp;
            }
        }
    }
}
