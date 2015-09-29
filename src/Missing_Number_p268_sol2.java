/**
 * Bit manipulation solution
 * 
 * The difficulty is to realize that 0 ^ x ^ x = 0 and 0 ^ x = x
 * 
 * We firstly set result to be 0, then XOR an ascending i and num in nums.
 * Here i is like the number in expected array while num is the number in real array.
 * If we have an counterpart of i in nums, then 
 * 0 ^ i ^ num = 0
 * But if we has a missing one, then
 * 0 ^ i = i.
 * So we XOR 0 with all elements in nums, then XOR i from 0 to n,
 * the final XOR result will be our answer
 * @author hpPlayer
 * @date Sep 28, 2015 12:56:07 PM
 */
public class Missing_Number_p268_sol2 {
    public int missingNumber(int[] nums) {
        int result = 0;
        int i = 0;
        for(; i < nums.length; i++){
            result ^= i ^ nums[i];
        }
        
        return result^i;
    }
}
