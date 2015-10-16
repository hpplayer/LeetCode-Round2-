/*
Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer (p7)
*/

/**
 * Bit manipulation
 * 
 * The tricky part is to understand how to set the result bit by bit.
 * 
 * A very convenient way is to directly set the bit at its index. We use &1 operation to get the last bit in input, then we leftshift it
 * by 31 times to set the result bit at 32th index. We keep doing this until we set the last index, which we need leftshift by 0 times.
 * 
 * 
 * Remark:
 * 1. If we use << operator to send the bit to target index, then we may make thing become complicate. The loop will goes 31 times, otherwise we may get overflow.
 * If we only shift 31 times, then we may have last bit unset. So we will add an extra operation after loop to set last bit, which is inconvenient. 
 * 2. This problem can also be solved by merge-sort. But we may be very familiar with Hex, so it can be treated as a bonus part. If
 * we have fully prepared, then we can try to remember and use such solution
 * 
 * @author hpPlayer
 * @date Oct 16, 2015 1:49:03 PM
 */

public class Reverse_Bits_p190_sol1 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        
        for(int i = 31; i >= 0; i--){
            //to set a bit at index 32, we have to left shift it by 31 times
            result |= (1&n) << i;
            
            //right shift input n, to get the bit in next index
            
            n >>= 1;
        }
        
        return result;
    }
}
