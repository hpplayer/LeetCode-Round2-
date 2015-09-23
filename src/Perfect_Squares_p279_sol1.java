import java.util.Arrays;
/**
 * DP solution.
 * 
 * The difficulty is how to fill the dp array.
 * 
 * The value of dp array means the number of perfect squares we need to reach current value. To keep the value to be minimal, we want just add
 * one perfect square + one previous result, the sum of which will equal our current value. We can do that because each number n > 1 at least 
 * can be composed of (n-1) + 1 * 1 though it may not be the minimal numbers of perfect squares to get result. To deal with case that if num n
 * itself is a perfect square, we need number 0 in array.
 * 
 * @author hpPlayer
 * @date Sep 22, 2015 3:51:50 PM
 */

public class Perfect_Squares_p279_sol1 {
    public int numSquares(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++){
            for(int j = 1; j*j <= i; j++){
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }
        
        return dp[n];
    }
}
