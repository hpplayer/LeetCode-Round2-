/**
 * Math solution.
 * 
 * The idea of this solution is same with sol1, but here we fixing n and increasing a temp variable by 5 each time
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 10:38:14 PM
 */
public class Factorial_Trailing_Zeroes_p172_sol2 {
    public int trailingZeroes(int n) {
        int result = 0;
        long temp = 5;
        
        while(temp <= n){
            result += n/temp;
            temp *= 5;
        }
        
        return result;
    }
}
