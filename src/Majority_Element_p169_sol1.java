/**
 * Bit manipulation
 * 
 * Although we have a better voting solution which runs in O(n), and this solution runs in O(32n), I still think this approach is very
 * interesting.
 * 
 * We are building a new integer one bit by one bit. For each digit, we just to decide assign it 0 or 1. In the given input, one input
 * appears more than 1/2, so if we count 1 or 0 in that digits of all numbers in array, we will know 0 or 1, which appears most. Then
 * we just assign the value accordingly.
 * 
 * Remark:
 * 
 * When we are counting 1s and 0s, we should always let the check condition be (<<i&1 == 0?) because, even we have 1 in that digit,
 * the value of ..0001000.. is still not 1, but if we have 0 in that digit, the value ...000 0 000.., will always be 0. So check 0!
 *  
 * @author hpPlayer
 * @date Sep 23, 2015 7:17:40 PM
 */
public class Majority_Element_p169_sol1 {
    public int majorityElement(int[] nums) {
        int result = 0;
        for(int i = 0; i < 32; i++){
            int zero = 0, one = 0;
            for(int j = 0; j < nums.length; j++){
                if ((nums[j]&(1<<i)) == 0){//we must put == 0 here since 1 may in different indexes which is not == 1, but 0 can in different places still make == 0 true
                    zero ++;
                }else{
                    one ++;
                }
            }
            
            if(one > zero){
                result |= 1<<i;
            }
        }
        
        return result;
    }
}
