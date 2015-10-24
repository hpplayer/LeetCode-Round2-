/*
Find Minimum in Rotated Sorted Array

Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*/

/**
 * Observation + two pointer problem
 * 
 * The tricky part is to observe that the min value is always in rotated part (i.e. nums[start] > nums[mid] or nums[mid] > nums[end])
 * 
 * If we got rotated array there will be two cases. Case 1), rotation affect left part: 5 1 2 3 4, where nums[start] > nums[mid] or 
 * Case 2) rotation affect right part: 3 4 5 1 2, where nums[start] < nums[mid]. In both cases, the smallest cell is always in 
 * rotated part, so we need firstly check which part is rotated, then search in that part. Of course if we find the array is not
 * rotated, where we don't have rotated part, we just directly return the nums[start]
 * 
 * Remark:
 * 1) we need care about the single cell case, where we have nums[start] == nums[mid]. Since if we are still in loop, there must
 * be a rotated part, therefore the first cell will not be the smallest cell, we need search right part. In other words, we can 
 * treat the single cell as a special form of not rotated part
 * 2)If we check the rotated part by comparing tail cell with mid cell, then we don't even to worry about single cell case, because
 * we will never have a rotated sorted array with nums[mid] == nums[start]
 * @author hpPlayer
 * @date Oct 23, 2015 10:31:46 PM
 */
public class Find_Minimum_in_Rotated_Sorted_Array_p153_sol1 {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while(start < end && nums[start] > nums[end]){
            //if the array is not rotated (nums[start] <= nums[end]), then we directly return nums[start] as min
            int mid = start + (end - start)/2;
            if(nums[mid] >= nums[start]){
                //right part is rotated, search right part
                //we need include nums[mid] == nums[start] here, since single cell can be treated as a special
                //form of sorted array
                //num[mid] > nums[start], so do not include nums[mid]
                start = mid + 1;
            }else{
                //left part is rotated, search left part
                //nums[mid] <= nums[start], so include nums[mid]
                end = mid;
            }
            
        }
        
        //we may stop when start == end or array is not rotated, so it is more safe to return nums[start]
        return nums[start];
    }
}
