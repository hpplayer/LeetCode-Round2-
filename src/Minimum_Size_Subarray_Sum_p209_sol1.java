/*
Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ¡Ý s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

/**
 * a classic two pointer problem, or we can call it sliding window problem.
 * We will move end pointer until the current cumulative sum >= target, then we will start move start pointer and try to shrink the window size
 * The tricky part is dealing with corner case, if two pointer by sudden points to a same cell, then we couldn't let start pointer pass our end pointer
 * For such case, our win's size is 1, which is the smallest possible size, so we can return size 1 immediately.
 * 
 * The time complexity is O(n) and space complexity is O(1)
 * 
 * Sol2 is binary search approach, which costs O(nlogn) time complexity and O(n) space complexity
 * 
 * So sol1 is more recommended
 * @author hpPlayer
 * @date Sep 20, 2015 9:47:14 PM
 */
public class Minimum_Size_Subarray_Sum_p209_sol1 {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length <= 0) return 0;
        
        int start = 0, end = 0;
        int sum = nums[end];//now end pointer points to index 0, so sum should contain nums[0]
        int size = Integer.MAX_VALUE;
        while(end < nums.length){
            if(sum < s){
                end ++;
                if(end < nums.length) sum += nums[end];
            }else{
                size = Math.min(size, end - start + 1);
                sum -= nums[start];
                if(start == end) return 1;//if two pointers meet, we know curr win size is 1, which is the smallest possible win size 
                start ++;
            }
        }
        
        return size == Integer.MAX_VALUE? 0 : size;
    }
}
