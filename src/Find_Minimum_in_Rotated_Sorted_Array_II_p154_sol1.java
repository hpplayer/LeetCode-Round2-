/*
Find Minimum in Rotated Sorted Array II

Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

/**
 * Observation + two pointer solution
 * 
 * The tricky part is to realize that now we have duplicate therefore equal case may not necessarily mean single cell, so it is better to move 
 * pointer based on rotated part. Besides, If we can't find rotated part, then it means they are all duplicates like [1, 1, 1, 0, 1] or 
 * [1, 0, 1, 1, 1], therefore we can do nothing, but shrink the search range and search again
 * 
 * So the main part of this solution is similar to the solution to p153, but we slightly modify the code according to the duplicate cases
 * 
 * It is better to move pointer based on rotated part not sorted part. Because in sorted part, we allow the part has nums[start] == nums[mid],
 * in problem Find Minimum in Rotated Sorted Array (p153), we know such cases indicates single cell, so we just move pointer to right. But now
 * the equal case may come from duplicates, so it is better to look at the rotated part instead to avoid such corner case
 * @author hpPlayer
 * @date Oct 23, 2015 11:02:14 PM
 */

public class Find_Minimum_in_Rotated_Sorted_Array_II_p154_sol1 {
	public static void main(String[] args){
		int[] nums = {1, 2, 1};
		System.out.println(findMin(nums));
	}
    public static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        //the main part of this solution is similar to solution to problem Find Minimum in Rotated Sorted Array (p153)
        //but now we need be careful with two things 1) in case nums[start], nums[mid], nums[end] are all duplicates or sorted parts are rounded by duplicates, we have to shrink range and search again
        //2) it is better for us to move pointer based on rotated part otherwise, the corner case nums[start] = nums[mid] would confuse us (single cell or duplicates)
        while(start < end && nums[start] >= nums[end]){
            int mid = start + (end - start)/2;
            
            if(nums[mid] > nums[end]){
                //right part is rotated, search right part
                start = mid + 1;
            }else if (nums[start] > nums[mid]){
                //left part is rotated, search left part
                end = mid;
            }else{
                //we got duplicates, we can do nothing but shrink range and search again
                start ++;
                end --;
            }
        }
        
        return nums[start];
    }
}
