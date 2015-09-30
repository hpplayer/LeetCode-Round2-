/*
Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 ¡ú 1,3,2
3,2,1 ¡ú 1,2,3
1,1,5 ¡ú 1,5,1

*/

/**
 * Math problem
 * 
 * The tricky part is to find the algorithm to correctly find next permutation, and the solution is very problem-specific
 * 
 * The problem works in this way:
 * To generate the next permutation, we will try to find the smallest number that is larger than current number. If we can't find such 
 * number, it means we reach the end, and we need to reverse the array. So, at least, we need a reverse().
 * 
 * Now the problem becomes how to find a such number. The intuitive way is to swap a pair that the first number is smaller than second number
 * But the requirement is to find the smallest number larger than current number, so we want the first number be the rightmost possible, and
 * second number be the smallest number larger than first number. So actually, we will look for the last pair in array that has nums[i] < nums[j]
 * here j can be i + 1, i + 2, or other values, but we should guarantee all numbers after i are following descending order or we would find 
 * another pair that nums[i] < nums[j]. Like I said above, we want both i and j to be the rightmost possible, now i is found and we are searching
 * for j.  If the nums after i is following descending order, and we require the nums[j] > nums[i], thus j would be the last value larger than
 * nums[i]. Example, 125431, here 2 is i and 3 is j. We just swap them, 135421. Since i < j, swap nums[i] and nums[j] would still follow
 * descending order, thus the smallest number start with new head nums[j] is just reversing the numbers after nums[j]. In this example:
 * 135421-> 131245. We found 131245 is the next permutation of 125431! So now we are done. Obviously, we need another swap() to swap 
 * numbers
 * 
 * 
 * @author hpPlayer
 * @date Sep 29, 2015 8:03:48 PM
 */
public class Next_Permutation_p31_sol1 {
    public void nextPermutation(int[] nums) {
        //firstly find the rightmost pair that nums[i] < nums[i+1], i.e. the one we need swap next step
        int i = nums.length -1;
        for(; i >= 1; i--){
            if(nums[i] > nums[i-1]) break;
        }
        
        //if i == 0, it means we couln't find such a pari, then it means we reach the maximum, we just reverse
        //and we are done
        if(i == 0){
            reverse(0, nums);
            return;
        }
        
        //then we will find the rightmost index that nums[j] > nums[i], i.e. the smallest number > nums[i]
        //i.e. the one that we need swap with the num[i] found in above step
        int j = nums.length -1;
        
        for(; j >= i; j--){
            if(nums[j] > nums[i-1]) break;
        }
        
        //then swap nums[j] with nums[i]. That means we will put the smallest number > nums[i] in front   
        //and it is because nums[j] will be the smallest head we can find in next permutations
        swap(i-1, j, nums);
        
        
        //Since nums after i -1 will be in descending order, to make the smallest number start with nums[j],
        //we just need to reverse the subarray from nums[i+1] to tail, then we will get the next permutation
        reverse(i, nums);
        
    }
    
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int i, int[] nums){
        int left = i, right = nums.length - 1;
        //while we still have a pair need to be swapped
        while(left < right){
            swap(left, right, nums);
            left ++;
            right --;
        }
    }
}
