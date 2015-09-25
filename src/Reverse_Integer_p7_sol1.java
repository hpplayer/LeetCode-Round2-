/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

 */

/**
 * Math + boundary check problem.
 * 
 * Each time we get the last digit of input x, then left shift result and append it to the updated result.
 * The difficulty part is dealing with sign and overflow.
 * For sign, actually we don't need extra code with that, since mod operation will keep the sign. 
 * For overflow, we can either use define result as long then convert, or just use some observations.
 * We observed that if our current result already > int_max / 10 and we still have digits left in input x, then no matter what those digits are,
 * our result will be overflow for sure. For such case, we should return directly. If current value == int_max / 10, then we know the last digit
 * must be 1, otherwise the original input will already > int_max/10, int_max/10 + 1 will  give an integer within range, so we are safe to let
 * the loop continue in such case
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 2:17:14 PM
 */
public class Reverse_Integer_p7_sol1 {
    public int reverse(int x) {
        int result = 0;
        
        //we let x keep it original sign, both of them will converge t o 0
        while(x != 0){
            //if the front part already larger than than int_max/10, and we still have digits left in x
            //then no matter what the digit is, our final result will be overflow
            //if front part == int_max/10, then the input part must start with 1 otherwise we will have overflow
            //in input already like reverse(2 463847412) = 214748364 2, but the input already overflow 
        	//Int_max = 2147483647
            if(Math.abs(result) > Integer.MAX_VALUE/10) return 0;
            result = result * 10 + x%10;
            x /= 10;
        }
        
        return result;
    }
}
