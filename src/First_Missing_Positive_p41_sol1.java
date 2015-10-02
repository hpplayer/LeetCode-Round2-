import java.util.Arrays;

/*
First Missing Positive

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/**
 * Array problem. 
 * 
 * From my view, it is more like a brainstorming problem. Thus the tricky part is to find the way to solve it in O(n)
 * 
 * The solution is not hard, we will scan the array. Each time we found a value that is not in correct index, we will try to place it 
 * in the right place, which is val - 1, like [2, 1], 2 should be placed in 2 - 1, i.e. index 1. There are 3 cases that we need skip the 
 * cell even it is in incorrect index: 1) the corresponding index for value is too small, like < 0  2) the corresponding index for value 
 * is too large, like >= nums.length, 3) the target index stores a value same with current value, which means if we try to swap them,
 * we will fall into an endless loop. So for above cases, we will skip the cell even the value is in incorrect index. Otherwise, we will
 * swap the value and check the new swapped value to see if it is in correct place.
 * 
 * Remark:
 * 1) Be careful with the index conversion. value n should be placed in the index n - 1.
 * 2) If we use value to locate the index, then we need to be careful about value change, since we may locate an incorrect index based 
 * on the new value!
 * 
 * @author hpPlayer
 * @date Oct 1, 2015 10:21:37 PM
 */
public class First_Missing_Positive_p41_sol1 {
	public static void main(String[] args){
		int[] nums = {2, 1};
		System.out.println(new First_Missing_Positive_p41_sol1().firstMissingPositive(nums));
		
	}
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            //if current value is in right place, or if its value has out of boundary or we found duplicates 
            //any may let us fall into endless loop, we will skip all of them

            if(nums[i] == i + 1 || nums[i] - 1 <0 || nums[i] - 1 >= nums.length || nums[i] == nums[nums[i] - 1]) continue;
            //otherwise, we will swap current value to its right place
            //notice, the right place it the value - 1, ex: [1] right place is 0, [2] right place is 1
            
            int temp = nums[i];
            nums[i] = nums[nums[i] - 1];
            nums[temp - 1] = temp;//Notice: the nums[i] has been update above, so we can't use it update array anymore
            i--;//let loop continue stop at current point to check the new swapped value
        }

        //scan the array and find the first value that != index + 1
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1) return i + 1; 
        }
        
        //otherwise, len + 1 is the first missing positive, like 2 is the first missing pos value for [1]
        return nums.length + 1;
    }
}
