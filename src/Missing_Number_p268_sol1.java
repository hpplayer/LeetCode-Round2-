/*
Missing Number

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

/**
 * Math approach
 * 
 * The difficulty part is to come up with the idea of using sum
 * 
 * Since the expected array should have number from 0-n, and the real array has one number missing, we can calculate the difference of sum to
 * find the missing number. Like expected array : 0 1 2, real array 0 2, then use sum of expected - sum of real = 3 - 2 = 1, then we can find 
 * missing number 1. When calculating sum of expected array, we use the sum equation (a1 + an)*n/2
 * 
 * Sol1 is math approach
 * Sol2 is bit manipulation approach
 * 
 * @author hpPlayer
 * @date Sep 28, 2015 12:52:03 PM
 */
public class Missing_Number_p268_sol1 {
    public int missingNumber(int[] nums) {
        //ideal range 0 : n and len should be n + 1, but we are missing one element in array, so the length is n
        int expectedSum = (0 + nums.length) * (nums.length + 1)/2;
        int realSum = 0;
        for(int num : nums){
            realSum += num;
        }
        
        return expectedSum - realSum;
    }
}
