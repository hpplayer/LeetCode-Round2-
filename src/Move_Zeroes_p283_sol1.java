/*
Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/



/**
 * Two pointer solution
 * 
 * The trick part is don't be confused with the logic. One pointer scans the array, and one pointer points to the first zero cell in array
 * 
 * we keep a pointer that always points to the first zero value
 * if we have single zero, then we will use swap to make it move forward, so pointer always points to this zero
 * if we have multiple consecutive zeros, then after swap the first zero with non-zero value, our pointer will point to the second zero
 * 
 * In this algorithm, we will swap num of non-zero cell times.
 * 
 * Remark:
 * We also provide another solution below, which avoids the operation of swap.
 * But instead, we will scan more cells in array: n + num of zero cells. 
 * So this is kinda trade off. If we have a lot of zeros, then we prefer use moveZeroes() with swap, as we won't do swap on zero cell
 * If we have a lot of non-zeros, then we maybe prefer moveZeroes2(), as we don't do swap on non zero cells (but we do assign values).
 * 
 * Personally I think moveZeroes() is better. As it is guaranteed to work for O(n) time. For moveZeroes2(), the worst case is O(2n) time
 * 
 * @author hpPlayer
 * @date Oct 27, 2015 2:37:04 PM
 */
public class Move_Zeroes_p283_sol1 {
    public void moveZeroes(int[] nums) {
        int zeros = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[zeros];
                nums[zeros] = nums[i];
                nums[i] = temp;
                zeros ++;
            }
        }
    }
    
    public void moveZeroes2(int[] nums) {
        int insertIndex = 0;
        
        for(int num : nums){
            if(num != 0) nums[insertIndex ++] = num;
        }
        
        while(insertIndex < nums.length){
            nums[insertIndex ++] = 0;
        }
    }
}
