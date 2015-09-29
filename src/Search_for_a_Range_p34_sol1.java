import java.util.Arrays;
/*
Search for a Range

Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

/**
 * Binary search problem
 * 
 * The difficulty: we need use binary search to find left and right boundary of range, so we should be care about boundary case and be cautious
 * about falling into dead loop. I believe this is the hard level problem in binary search, keep practicing!
 * 
 * To search the range, we will find the the first and last number in array that same with target.
 * During binary search, we will always move left pointer rightward and move right pointer leftward. So it is intuitively, we will use two 
 * binary search to find the target. one search will use left pointer tell us the first num = target, another search will use right pointer 
 * tell use the last num = target. In the first search, we will let right pointer stops at the number after first num = target, it may either
 * a number = target or it may a number > target, so our left pointer will then move to the first num = target, then right and left pointer will
 * meet. Similarly, in the second search, we will let left pointer stops at the number before last num = target, it may either
 * a number = target or it may a number < target, so our right pointer will then move to the last num = target, then right and left pointer will
 * meet. But the problem is if there is only one target value in the input, and we choose to let left pointer stop at the last num = target, we
 * may fall into a dead loop that we will endless stop at num = target. To avoid such case, we will use a trick, we manually let mid move 1 step
 * rightward, so we firstly move right pointer and avoid such a dead loop
 * 

 * 
 * @author hpPlayer
 * @date Sep 29, 2015 12:11:41 AM
 */
public class Search_for_a_Range_p34_sol1 {
	public static void main(String[] args){
		int[] nums = {2, 2};//{5, 7, 7, 8, 8, 10};
		System.out.println(Arrays.toString(new Search_for_a_Range_p34_sol1().searchRange(nums, 2)));
	}
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        int[] result = new int[2];
        Arrays.fill(result, -1);
        
        //we will not check the last num since we may even not have target in nums
        while(left < right){
           //search for the first num == target
            int mid = left + (right - left)/2;
            
            if(nums[mid] < target){
                //nums[mid] != target, we will search right part without considering mid
                left = mid + 1;
            }else{
                //we may have nums[mid] = target, we will search left part with considering mid
                right = mid;
            }
        }
        //if we have not found our target
        if(nums[left] != target) return result;
        result[0] = left;
        
        //reset, since if we come here left should already point to one target, no need reset left
        right = nums.length - 1;
        
        while(left < right){
            //to avoid stuck in the target we already found in above loop, we will force mid goes right
            int mid = left + (right - left)/2 + 1;
            
            //search for the last num == target
            if(nums[mid] > target){
                 //nums[mid] != target, we will search left part without considering mid
                right = mid - 1;
            }else{
                left = mid;
            }
        }
        
        result[1] = right;
        
        return result;
    }
}
