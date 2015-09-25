/*
Power of Two

Given an integer, write a function to determine if it is a power of two.
*/

/**
 * Bit manipulation problem
 * 
 * The tricky part is observing that num <= 0 will never be power of 2
 * 
 * I know we can use (n-1)&n to deal with the last bit of int.
 * How does it work?
 * If the int is power of 2, then it should follow the style as: ..0000 1 000..
 * so if subtract 1 from it, it will become ..0000 0 111....thus give &, it should return 0, but if we have another bit has 1, it will never return 0
 * 
 * But, how about the negative case? Especially the int_min, if we subtract 1 from int_min, it will overflow. 
 * Fortunately, any number <= 0 will never be the power of a positive 2, so we can avoid such case by checking is input n is positive 
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 1:30:15 AM
 */
public class Power_of_Two_p231_sol1 {
	
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n&(n-1)) == 0;
    }
    
}
