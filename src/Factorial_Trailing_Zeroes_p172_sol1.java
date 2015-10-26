/*
Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

/**
 * The math problem
 * 
 * The tricky part is to be clear with the logic
 * 
 * We only need to count the number of 5 we got. Some numbers may contain extra 5 like 25, 75, etc. So we will remove one 5 from all numbers,
 * and count the number of 5 we got again. We repeat do this until we can no longer remove 5. We are very lucky since the operation we used
 * to remove 5 is n/5, and the operation we used to count num of 5 is also n/5. We simply play around n/5 to reduce and count n until n < 5
 * 
 * Since the code is so short, I will combine recursive and iterative solution int the same file
 * 
 * Sol1 provides recursive and iterative solutions, by using n/5 to count and reduce n.
 * Sol2 provides an iterative solution, that fixing n while increasing a temp variable
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 10:31:55 PM
 */
public class Factorial_Trailing_Zeroes_p172_sol1 {
    public int trailingZeroes(int n) {
        if(n < 5) return 0;
        
        return n/5 + trailingZeroes(n/5);
    }
    
    public int trailingZeroes2(int n) {
        int result = 0;
        
        while(n >= 5){
            n /= 5;
            result += n;
        }
        
        return result;
    }
}
