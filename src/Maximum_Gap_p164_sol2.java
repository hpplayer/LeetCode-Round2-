/**
 * Radix solution
 * 
 * The tricky part is to be familiar with radix sort. Actually, this solution just needs a standard radix sort 
 * 
 * In radix sort, we sort the number digit by digit. To achieve that we needs a temp[] that holds temp result and count[] to count
 * the appearance of each number in current digit. Then we assign nums to temp based on values in count[] and previous sorting result(
 * which is reflected in nums[] during the radix sort). More details can be found below
 * 
 * Remark:
 * when assign values to temp[] from count[] and nums[], remember to use --[], since count is 1 based while index is 0 based, we need conversion!!
 * 
 * @author hpPlayer
 * @date Oct 24, 2015 11:23:42 PM
 */
public class Maximum_Gap_p164_sol2 {
    public int maximumGap(int[] nums) {
        radix(nums);
        
        int result = 0;
        
        for(int i = 1; i < nums.length; i++){
            result = Math.max(result, nums[i] - nums[i-1]);
        }
        
        return result;
    }
    
    public void radix(int[] nums){
        //a temp array that takes temp result
        int[] temp = new int[nums.length];
        
        //max is the max number in nums, so we know when to stop radix
        int max = 0;
        //exp is the helper variable that helps us retrieve ith digit in num
        int exp = 1;
        
        //find max 
        for(int num : nums){
            max = Math.max(max, num);
        }
        
        //the loop goes as long as we have digit left
        while(max / exp > 0){
            //count array that counts how many each number(0-9) appears in current digit
            int[] count = new int[10];
            
            for(int num : nums){
                //update counter based on appearance
                count[num/exp%10] ++;
            }
            
            for(int i = 1; i < 10; i++){
                //update counter based on prev numbers
                count[i] += count[i-1];
            }
            
            //assign each number in half sorted nums[] to new temp[] based on the number in current digit
            //we assign the number backward and the prev larger value will get higher index if current digit is same,
            //so the remaining part will keep the order we sorted before.
            //ex: say we have 123 132. so 132 will get higher index and still keep the order 123 132 when looking at digit 1
            for(int i = nums.length - 1; i >= 0; i--){
                //assign num in nums[] basesd on current digit
                //count is 1 based, temp[] is 0 based, so we use --[] to update count[], i.e. convert first then update
                temp[ --count[ nums[i]/exp%10 ] ] = nums[i];
            }
            
            
            //we have done assignment, we copy temp array to nums
            for(int i = 0; i < nums.length; i++){
                nums[i] = temp[i];
            }
            
            //we have done current digit, increase exp, look next digit
            exp *= 10;
            
        }
        
    }
}
