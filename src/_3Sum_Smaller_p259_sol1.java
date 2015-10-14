import java.util.Arrays;

/*
3Sum Smaller

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy
the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?

*/

/**
 * 
 * The tricky part is how get get the smaller number after fixing two numbers.
 * 
 * The main part is similar to other 3sum problem. We just use three pointers to scan the array. Say, now we have start, i and end, totally
 * three pointers. If we found sum is too large, then we can easily move end pointer leftward to get a smaller sum. However, what if 
 * current sum is < target? We will sort the array first. So we know if now we fixed start and i pointers, all numbers from start + 1
 * to end pointer will all have sum < target (it like we fixed i and start pointer, but move end pointer leftward, of course, the sum 
 * will all < target). So we even don't need to move pointer to include those pairs, we just get the count by calculation
 * 
 * Example:
 * [-2, 0, 1, .., 3], target = 4, after two pointer points to -2, 0 are fixed, then we know any number from 1 to 3 will have 3 sum < 4.
 * So we even don't need to move pointer in such case!
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 10:12:16 PM
 */
public class _3Sum_Smaller_p259_sol1 {
    public int threeSumSmaller(int[] nums, int target) {
        //sort the array first, so we know where to search
        Arrays.sort(nums);
        
        int result = 0;
        
        for(int i = 0; i+2 < nums.length; i++){
            //skip rest, if we don't have 2 elements left behind
            int start = i + 1, end = nums.length - 1;
            while(start < end){
                //while we still have 2 elements left
                int sum = nums[start] + nums[i] + nums[end];
                
                if(sum < target){
                    //if we fix i and start, all numbers from start + 1 to end(inclusive) will have 3 sum smaller
                    //than target, the number of paris is: end - (start + 1) + 1 (+1 to convert index to length) =end - start 
                    result += end - start;
                    start ++;//we have included all pairs with fixed start, search next
                }else{
                    //to large, cannot form a pair, end --
                    end --;
                }
            }
        }
        
        return result;
    }
}
