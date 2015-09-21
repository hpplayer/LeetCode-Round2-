/**
 * well, this is binary-search approach to solve this problem.
 * We firstly create an array that record accumulative sum at each index.
 * Then we scan the array and find the cell that contains accumulative sum >= target sum, we call accumulative sum here as sum a.
 * Then we will try to search the first cell that its sum > (sum a - target sum), so we know its index with index of sum a will give a window whose
 * sum is at most target sum, i.e we don't have redundant element in this window. This is like we use binary search to find the left bound of current
 * min window, and the index of sum a is right bound of current min window. Then we continue let right bound move and search next min window
 * So actually this solution is very similar to sol2, but based on cumulative sum. Thus we need extra space and binary search
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 10:03:14 PM
 */
public class Minimum_Size_Subarray_Sum_p209_sol2 {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length <= 0) return 0;
        
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        
        for(int i= 1; i < sum.length; i++){
            sum[i] = sum[i-1] + nums[i];
        }
        
        int size = Integer.MAX_VALUE;
        int start = 0;
        for(int i = 0; i < sum.length; i++){
            if(sum[i] >= s){
                //search the boundary which split our current array into two parts
                //one part has sum >= s, another part has sum < current sum - s
                start = binarySearch(start, i, sum[i] - s, sum);
                size = Math.min(size, i - start + 1);
                //start = newStart;
            }
        }
        
        return size == Integer.MAX_VALUE? 0 : size;
    }
    
    public int binarySearch(int start, int end, int target, int[] nums){
        //we will search each element, including the last number which pointed by both pointers
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] <= target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        
        return start;
    }
}
