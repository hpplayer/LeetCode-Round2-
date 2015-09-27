/*
Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
*/

/**
 * Array Rotate problem.
 * 
 * Rotation is a classic problem in array. There are several famous solutions to do it. The difficulty is to get know those approachs
 * Sol1 provides a The Reversal Algorithm, 
 * 
 * if we need reverse ab, we can firstly get a^rb, then we can get a^rb^r, then we can get (a^rb^r)^r = ba (see ProgrammingPearls2nd)
 * Similarly, our array can be split into two parts. First part is len - k elements, second part is k elements. 
 * We firstly reverse first part, then reverse second part, finally we can get rotated array
 * 
 * Sol1 provides a reversal algorithm
 * Sol2 provides a block-swapping algorithm
 * 
 * Both solutions are very specific to the array rotation
 * @author hpPlayer
 * @date Sep 25, 2015 6:23:18 PM
 */
public class Rotate_Array_p189_sol1 {
    public void rotate(int[] nums, int k) {
        int n = nums.length - 1;
        k = k%nums.length;
        reverse(0, n - k, nums);
        reverse(n-k + 1, n, nums);
        reverse(0, n, nums);
    }
    
    public void reverse(int start, int end, int[] nums){
        while(start < end){
            nums[start] += nums[end];
            nums[end] = nums[start] - nums[end];
            nums[start] -= nums[end];
            start ++;
            end --;
        }
    }
}
