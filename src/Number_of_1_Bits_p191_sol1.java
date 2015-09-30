/*
Number of 1 Bits

Write a function that takes an unsigned integer and returns the number of ¡¯1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ¡¯11' has binary representation 00000000000000000000000000001011, so the function should return 3.
*/

/**
 * Bit manipulation problem
 * 
 * The tricky part is to come up with idea of removing 1 from input.
 * 
 * Here we always remove the rightmost 1. It is very creative and fast.
 * The number of loops we used to remove 1s is the number of 1s in input
 * 
 * Instead of counting 1s in each digit, we can  return the count when input become 0 so save time
 * 
 * We also can use >>> to rightshift unsiged int, and do it for 32 loops, to count 1s, but it is trivial so I didn't list it here
 * 
 * @author hpPlayer
 * @date Sep 30, 2015 1:47:59 AM
 */
public class Number_of_1_Bits_p191_sol1 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        
        //n may be negative, so we should allow n <0 or n > 0
        while(n != 0){
            n &= n-1; //n&n-1 will remove rightmost 1, 
            result ++;
        }
        
        return result;
    }	
}
