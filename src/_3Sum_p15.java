import java.util.*;

/**
 * Two pointer problem.
 * It is easier than I thought, we firstly locate the first number, then iteratively search the next pair of numbers in array. To
 * speed up the search, we sort the array first, so end pointer will always larger than start pointer. And after locate the first number,
 * which is supposed to be a non-positive number, we will begin search all numbers after it. It is 3 sum, so we use two pointers to points
 * two numbers after it. We may have a couple of 2 numbers that can sum current number to 0, so we will search all remaining array until
 * two pointers meet. For each pointer, don't forget skip all duplicates. Before search the array, we can shrink the search range by look
 * at the sum of initial two pointers, if it is smaller, then move start pointer, if it is larger, then move end pointer
 * 
 * @author hpPlayer
 * @date Sep 18, 2015 11:54:30 PM
 */

public class _3Sum_p15 {
	   public List<List<Integer>> threeSum(int[] nums) {
	        Arrays.sort(nums);
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        for(int i = 0; i < nums.length; i++){
	        	if(nums[i] > 0) return result;
	            if(i > 0 && nums[i] == nums[i-1]) continue;//skip duplicates
	            int target = -nums[i];
	            int start = i+1;//new search begin right after i
	            int end = nums.length - 1;
	            while(start < end){//start and end must point to two numbers
	                if(nums[start] + nums[end] == target){
	                    List<Integer> temp = new LinkedList<Integer>();
	                    temp.add(nums[i]);
	                    temp.add(nums[start]);
	                    temp.add(nums[end]);
	                    result.add(temp);
	                    
	                    //we may have a bunch of (start, end) that can reach target, so we will continue search
	                    //Skip current start and end
	                    
	                    //need move start and end, to avoid outOfboundary error in following two while loops
	                    start ++;
	                    end --;
	                    
	                    while(start < end && nums[start] == nums[start-1]) start ++;
	                    while(start < end && nums[end] == nums[end+1]) end --;
	                }else if(nums[start] + nums[end] < target){
	                    start ++;//too small
	                }else{
	                    end --;// too large
	                }
	            }
	        }
	        
	        return result;
	    }
}
