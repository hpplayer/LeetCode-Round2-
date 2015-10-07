/*
Bitwise AND of Numbers Range 

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
*/	

/**
 * Bit manipulation
 * 
 * & operation is very strict, any single 0 will make the final result has 0 in this bit.
 * To avoid 0 in all numbers, we can only look at the left common part ex: 100 - 111, we will look for the left share part, which is 1.
 * We won't have 1 in our result if the input number do not have same length: ex: 111, 10, since their left share part will always be 0.
 * So after found left part, we just append 00 after it, and the produced number will be our result
 * 
 * In sol1, the way we used to find left share part is simply right-shift both input, until we found the left common part. During the shift,
 * we will also record the counts "postfix", so we know how many 0s we need to append after left common part. To append 0s, we just need to
 * left shit, then we are done
 * 
 * Sol1 uses right shift to get left common part, then use left shift to rebuild the solution
 * Sol2 uses n&(n-1) trick to directly build the solution
 * 
 * Sol2 is fast but not so intuitive, so sol1 is more recommended
 * @author hpPlayer
 * @date Oct 7, 2015 12:42:39 AM
 */
public class Bitwise_AND_of_Numbers_Range_p201_sol1 {
    public int rangeBitwiseAnd(int m, int n) {
        int postfix  = 0;//count how many shifts we do
        
        //rightshifr two inputs until we found left common part
        while( m != n){
            m >>= 1;
            n >>= 1;
            postfix ++;
        }
        
        return m << postfix;
    }
}
