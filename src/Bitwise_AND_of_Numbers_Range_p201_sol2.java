/**
 * Bit manipulation problem
 * 
 * This solution further optimizes sol1. As we will look for the left common part and make right part 0s. Here we directly do those operations on n
 * We will iteratively set bits in n backward to 0, until we found the left common part. Note: the left common part + 0s will be smaller than n and m,
 * since any digit in right part != 0, will make it larger than left common part + 0s. So we just need to find the first value that makes n <= 
 * m and that will be our solution. Use some examples to illustrate this observation better
 * Ex: n: 1100 vs m: 1001, the first value < m by set 0s, is 1000, so the result is 1000
 * 
 * @author hpPlayer
 * @date Oct 7, 2015 12:58:08 AM
 */
public class Bitwise_AND_of_Numbers_Range_p201_sol2 {
    public int rangeBitwiseAnd(int m, int n) {
        while(n > m){
            n &= (n-1);//used to remove rightmost 1
        }
        
        return n;
    }
}
