/*
Single Number III

Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/**
 * bit manipulation problem
 * 
 * The tricky part is to come up with the idea (key part is to make use of XOR result) and be familiar with diff &= -diff
 * 
 * Since all other elements appeared two times, it is easy to come up with the idea of using XOR operation scan the result.
 * But the result array will only be the a^b. How can we get the number of a and b respectively? Well xor indicates the difference
 * between a and b, if we can pick one of the difference bits, then use this bit as a marker/filter to separate the array,
 * we can finally reduce the problem to be problem Single Number (P136).
 * So now the problem is how to pick the bit that can differentiate a and b?
 * Since we have the XOR result, we just need to pick one of the bit that is 1 in xor result.
 * Here we use equation diff &= - diff to pick the last bit that is 1.
 * +1 will make target bit becomes 1, all other bits are reversed, so we can pick the 1 in target bit
 * ex: n = ...100, -n = ~n + 1 = ...011 + 1, n&-n = ...100 (all prev part "&" will give 0)
 * Note: recall the operation n & (n-1) is to remove the last 1
 * ex: n = ..100, n - 1 = ...011, n&(n-1) = ...000 (all prev part will be kept same)
 * 
 * After pick the marker/filter, we just need to separate the array based on this input. No matter what other nums will be in
 * subpart, they must appear as a pair. So the XOR result in subpart will give one of the target (similar to problem Single Number
 * p136)
 * 
 * Remark:
 * 1) the last 1 may appear in any index, so we cannot simply let num&diff == 1. But we are guaranteed the other part & result 
 * is 0. So we have to use == 0 to split input array
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 5:59:55 PM
 */
public class Single_Number_III_p260_sol1 {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        //firstly get the xor result of result1 ^ result2
        for(int num : nums){
        	xor ^= num;
        }
        
        //diff now is the xor of result1 and result2
        //we now need to pick one of the bit that result1 and result2 are different
        //i.e. pick a 1 in xor result
        // n&(n-1) is to remove the last 1 and keep other bits
        // n&(~n + 1) is to pick the last 1 and clear other bits 
        xor &= -xor;
        
        //now we can seperate the array based on diff, one part has 1 in that bit
        //one part does not have 1 in that bit. We just do xor in each part, then we can get result easily
        int[] result = new int[2];
        for(int num : nums){
            if((num&xor) == 0){
                result[0] ^= num;
            }else{
                //since this filter bit may not necessary in the last index, our % result may not = 1
                //it can be one of the multiples of 2. So it is better to use (==0) to make the condition
                result[1] ^= num;
            }
        }
        
        return result;
    }
}
