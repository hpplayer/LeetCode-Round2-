/**
 * Scan the array two times, forward and backward.
 * 
 * It's like p135, for each element, we will only look at numbers in its left side in forward scan and look at numbers in its right side
 * in backward scan
 * 
 * Here, we make use of output array. In first scan, record the cumulative product in each number's left side. During this scan, we can make
 * use of number in previous cell to get previous cumulative product. However, in the backward scan, since each next cell already contains
 * number in current index, we can't make use of values in array to get cumulative product any more. Thus, we need an extra variable to record
 * cumulative product from right side for each number, then let it times the cumulative product from left side that already in array.
 * 
 * This solution takes only O(n) time and O(1) space (except the output array).
 * 
 * @author hpPlayer
 * @date Sep 19, 2015 11:51:30 PM
 */
public class Product_of_Array_Except_Self_p238_sol1 {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];
        int[] result = new int[nums.length]; 
        result[0] = 1;
        for(int i =  1; i < nums.length; i++){
            result[i] = result[i-1] * nums[i-1]; 
        }
        
        //nums[len -1] has been done, no need to look it again
        //since result[] has already contains the result from left side, we can't use those information
        //as a counter to record the cumulative product from right side, so we need an extra variable
        int temp = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            temp *= nums[i+1];
            result[i] *= temp;
        }
        
        return result;
    }
}
