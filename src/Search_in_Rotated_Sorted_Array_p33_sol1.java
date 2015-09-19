/**
 * Two-pointer problem.
 * The solution can be extracted from binary search in sorted array. But here we will define the search range first.
 * Rotate array can be divided into two parts, one part is sorted and the other one is rotated. We can use the information of 
 * sorted part to define the search range. If target is in sorted part, then we just do normal binary search in sorted part.
 * If target is in rotated part, then we will search the rotated part as new rotated sorted array, and divide this part further.
 * 
 * The difficulty of the problem is boundary. 1) Even the sorted part may be half cut, like 2341, like we know 23 is sorted part, 41 is rotated
 * part, and our target is 1. We cannot say 1 is in sorted part unless we compare both boundaries of the sorted part and make sure it is not here
 * So remember compare both boundaries of sorted part to locate our target. 2) The corner case, when one part is composed of single unit.
 * In this case, we can treat the single unit as a extreme case of sorted part, then we need to search the unsorted part. From code below,
 * we can see the nums[start] == nums[mid] or start == mid both means left part contains single number, as there is no duplicates, we won't 
 * have nums[start] == nums[mid] unless start == mid. For those cases, we treat left part as sorted part, and redefine search range accordingly
 * 
 * @author hpPlayer
 * @date Sep 18, 2015 1:10:49 PM
 */
public class Search_in_Rotated_Sorted_Array_p33_sol1 {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return mid;
            /*
            if(mid == start){
                start = mid + 1;
                continue;
            } 
            */
            if(nums[start] <= nums[mid]){
                if(target < nums[mid] && target >= nums[start]){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }else{
                if(target > nums[mid] && target <= nums[end]){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }
        }
        
        return -1;
    }
}
