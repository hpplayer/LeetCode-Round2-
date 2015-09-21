/**
 * This iterative solution is very similar to myPow2() in sol1, where each recursion we are passing x*x and n/2;
 * Take example from sol1:
 * 2^8 can also be calculated as:
 * 2^8 = (2^2) ^ 4 = (4^2) ^2 = (16^2) ^ 1
 * 
 * So in iterative solution, we just take exponential result of current temp result to next iteration while half the power.
 * If the power is odd, then we manually times x with temp result
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 4:48:29 PM
 */
public class Pow_p50_sol2 {
    public double myPow(double x, int n) {
        if(n < 0){
            x = 1/x;
            n = -n;
        }
        
        double result = 1.0;//we are get product, so the initial value must be 1, plus it will help deal with n ==0
        
        for(int i = n; i > 0; x*=x, i /=2){
            if(i%2 == 1){
                result = x*result;        
            }
        }
        
        return result;
    }
}
