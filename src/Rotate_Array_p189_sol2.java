import java.util.Arrays;

/**
 * Array problem.
 * 
 * block-swapping algorithm. Based on the ProgrammingPearls, it is faster than reversal algorithm.
 * 
 * Each time, we swapped k elements to the correct place, then move to next range, until we have placed all elements in correct place
 * Since we will shrink our scope on the array, we need extra pointer that points to the start of new subarray, and also a pointer to indicate 
 * the length of new subarray(for convenience). Also since we will shrink our subarray, our input k may be larger than the length of subarray.
 * In those cases, we need to update k by k%len. In other words, even if our k is not same with the oringal input or the k after first k%len,
 * we will still let the loop continue until k = 0, which means we don't need to rotate the array anymore.
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 7:10:05 PM
 */
public class Rotate_Array_p189_sol2 {
	public static void main(String[] args){
		int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
		rotate(nums, 38);
	}
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        k %= len;
        int start = 0;
        //int temp = k;
        //System.out.println(k);
      while(k > 0){//while we still need to rotate//while(k == temp && k != 0){ 
        
            //swap all elements in current range
            for(int i = 0; i < k; i++){
                swap(start + i, start + len - k + i, nums);
            }
            
            start += k;//move to next part that k elements away
            len -= k;//shrink len with k elements
            
            k %= len;//Since the len decreased, we need to update k
        }
        /*
        System.out.println(Arrays.toString(nums));
        System.out.println(len);
        System.out.println(k);
        */
    }
    
    public static void swap(int a, int b, int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
