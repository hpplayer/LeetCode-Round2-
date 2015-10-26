/*
Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
*/

/**
 * DP solution
 * 
 * The tricky part is to realize that the max product can also come form the product two min negative value
 * 
 * The max product may come from the product of two max pos num, but may also come from the product of two min neg num.
 * So we will keep both the local max and local min. Since we will change the value of max/min on fly, we have to use two other variables to hold
 * the original values during the update. For local max, we can choose take the product of curr num with previous max/min product, or just use curr num
 * Same applies to local min. But for global max, we can only update it based on local max one 
 * 
 * Remark:
 * We have official solution in attached manuals
 * 
 * @author hpPlayer
 * @date Oct 26, 2015 1:38:00 AM
 */
public class Maximum_Product_Subarray_p152_sol1 {
    public int maxProduct(int[] nums) {
        //boundary check
        if(nums.length == 0) return 0;
        
        //we have to keep the local min value as well, so we can cover the case the max product from two min neg num
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            //since we will change the max/min value, we have to backup old value of max/min
            int mn = min, mx = max;
            
            //local max may come from product of two min neg value as well
            max = Math.max(mn * nums[i], Math.max(mx * nums[i], nums[i]));
            
            //same to local min
            min = Math.min(mx * nums[i], Math.min(mn * nums[i], nums[i]));
            
            //but for global max product, it can only come from local max
            result = Math.max(result, max);
        }
        
        return result;
        
    }
}
