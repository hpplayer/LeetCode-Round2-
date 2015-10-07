/*
Search in Rotated Sorted Array II

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/

/**
 * 
 * Variation of binary search
 * 
 * This problem has a little difference from problem Search_in_Rotated_Sorted_Array (p33)
 * I put it here as an extra practice for binary search, we firstly check if target is in sorted range, which would be not so intuitive
 * Here I check if the target is not in sorted range. So it is more easily to write correct code.
 * 
 * For the duplicates, we may have case like "111111121111", i.e. nums[start] = nums[mid] = nums[end], for such
 * case we couldn't decide where to start our next new search. Since our solution compares nums[start] with nums[mid]
 * we can only skip the cell pondered by "start" pointer. So the worst running complexity is O(n)
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 10:32:16 PM
 */

public class Search_in_Rotated_Sorted_Array_II_p81_sol1 {
    public boolean search(int[] nums, int target) {
        int start  = 0, end = nums.length - 1;
        
        //check each num so we will check start == end
        //We always get a sorted part and a half part
        //we can check sorted part to decide where to go next
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return true;
            
            //if left part is sorted
            if(nums[start] < nums[mid]){
                if(target < nums[start] || target > nums[mid]){
                    //target is in rotated part
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else if(nums[start] > nums[mid]){
                //right part is rotated
                
                //target is in rotated part
                if(target < nums[mid] || target > nums[end]){
                    end = mid -1;
                }else{
                    start = mid + 1;
                }
            }else{
                //duplicates, we know nums[mid] != target, so nums[start] != target
                //based on current information, we can only move left pointer to skip one cell
                //thus in the worest case, we would have target: 2, and array like 11111111, then
                //the running time would be O(n)
                start ++;
            }
        }
        
        return false;
    }
}
