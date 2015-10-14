/*
Single Number II

Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/	

/**
 * Bit manipulation problem
 * 
 * This is still a bit manipulation problem, even now I am doing it for second time, I still think it is very hard.
 * The tricky part is to get understand of state machine, order and logic. Simple code but very hard to understand
 * 
 * Our basic idea is to look each num as a seperate part. We don't care how many numbers are in input array, or what orders 
 * they have. We only look at each num. Now each number has two patterns 1) appears three times 2) appears one time
 * Our idea is to keep nums that appears one time in a state, then reset it to 0 if it appears three times.

 * Since the input array may be unsorted, we may have weird temp values for ones twos threes, but based on our logic in the end
 * all numbers appear three times will clear its number in variable "ones" and "twos", so the remaining value for ones must
 * be our target number
 * 
 * To make the solution more easy-understanding, I use three variables here.
 * "Ones" records numbers that appear only once. "Twos" records numbers that appear twice. "Threes" records numbers that appear three times
 * As I mentioned above, we only need to care about each separate part. So those variables hold all numbers that appears 
 * assigned times. Finally, based on our logic, all numbers will be reset to 0, except the one in "ones", which is our target.
 * 
 * Ones will be set in first and reset to 0 in second appear, then set again in third time
 * Why? we don't want keep ones in second loop, which may incorrectly combined with twos to make the illusion that a num appears
 * three times. To do that, we will use ^ operator
 * 
 * Twos will be set in second and keep in third time. We put it before where ones is set
 * Why? we don't want set twos if we just see a number appears once. We want set it in next loop. So we put it above ones.
 * To do that, we will use |= (ones & num). So we will set twos only after a num appears twice. Also even we reset ones
 * to 0, when we see a num second time, we will still keep twos there. 
 * 
 * Threes will be set when ones and twos share a same number.
 * so we use threes = ones & twos similar to what we do to set twos. But here we require ones and twos to be same to set threes
 * so we use = not |=
 * 
 * Last if threes are set, we will reset ones and twos to 0, to filter out those numbers
 * 
 * Remark:
 * The logic is hard, but the code is simple. My suggestion is to practice it many times.
 * 
 * @author hpPlayer
 * @date Oct 13, 2015 8:42:07 PM
 */
public class Single_Number_II_p137_sol1 {
	public static void main(String[] args){
		int[] nums = {2,2,3, 2};
		System.out.println(new Single_Number_II_p137_sol1().singleNumber(nums));
	}
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for(int num : nums){
            //we need set ones and twos in reverse order,
            //so twos will only be set when new incoming num == ones, i.e. we set ones and twos in two loops
            twos |= (num & ones);
            
            //we will reset ones if num appears two times, so we won't incorrectly have ones at twos
            //at the same time before appears 3 times
            ones ^= num;
            
            //only when twos and ones both are set, should we set threes
            //since we use |= to set twos, even now ones = 0 (due to reset in second time), we will still
            //keep twos 
            
            threes = (ones & twos);
            
            //if threes are set, then we need to clear ones and twos
            //if one and two and three are same value, then n &= ~n will set n = 0
            ones &= ~threes;
            twos &= ~threes;
        }

        return ones;
    }
}
