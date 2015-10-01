/**
 * Divide and Conquer approach
 * 
 * The tricky part is how to divide the problem
 * 
 * For each cell, the max subarray sum will only comes from 3 parts
 * 1) left part only
 * 2) right part only 
 * 3) a subArray includes some elements from left and right and value in cell
 * For such cell, we will pick the mid one in current array.
 * That's the way we divide the problem.
 * 
 * For 3), say now we split array based on cell "mid", we just check all accumulative sums ended at mid - 1
 * and check all accumulative sums started from mid + 1, then add the value in mid.
 * 
 * We will compare sum from 1), 2) and 3), then return max one as the max sum for current array
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 11:03:34 PM
 */
public class Maximum_Subarray_p53_sol2 {
    public int maxSubArray(int[] nums) {
        return helper(0, nums.length - 1, nums);
    }
    
    public int helper(int left, int right, int[] nums){
        if(left > right){
            //we don't allow left exceeds right, this condition includes the 0 and len case
            //since we will only have right pointer pass 0 and left pointer pass len cases
            return Integer.MIN_VALUE;//return min_val, equals not consider this case
        }
        
        int mid = left + (right - left)/2;//be careful don't let right < left
        
        int left_val = helper(left, mid - 1, nums);//value from left part alone
        int right_val = helper(mid + 1, right, nums);//value from right part alone
        
        int currLeft_val = 0;//value from left part if we include mid
        int temp = 0;// record accumulative sum
        for(int i = mid-1; i >= left; i--){
            temp += nums[i];
            currLeft_val = Math.max(currLeft_val, temp);
        }
        
        int currRight_val = 0;//value from right part if we include mid
        temp = 0;// record accumulative sum
        for(int i = mid+1; i <= right; i++){
            temp += nums[i];
            currRight_val = Math.max(currRight_val, temp);
        }        
        
        return Math.max(left_val, Math.max(right_val, currLeft_val + nums[mid] + currRight_val));
        
    }
}
