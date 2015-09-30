/*
Pow(x, n)

Implement pow(x, n).
*/

/**
 * Math problem + divide and conquer.
 * To avoid repeat calculation, we will let the power goes like n->n/2->n/4
 * So we only need to do log(n) calculations to get the result.
 * Example:
 * 2^8 = 2^4 * 2^4 = (2^2 * 2^2) * (2^2 * 2^2) = ((2^1 * 2^1) * (2^1 * 2^1)) * ((2^1 * 2^1) * (2^1 * 2^1))
 * Each time, we just need to calculate the result of left part then times the result one more time, and we will get result
 * 
 * This is the basic idea, but we may have several corner cases:
 * 1) our power may be odd, in that case, we can still use divide-and-conquer, but now we need to times x as well:
 * 2^9 = 2 * 2^8
 * 2) our power may be negative, in that case, we need covert power to be positive, then convert x to 1/x:
 * 2^-8 = (1/2) * 8
 * 
 * There are many ways to implement above idea, please check myPow() and myPow2(). myPow() calculate the half result, then
 * times itself to achieve x^2, while myPow()2 directly put x^2 to next recursion.
 * 
 * sol1 is recursive solution while sol2 is iterative solution which is more similar to myPow2()
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 4:14:58 PM
 */
public class Pow_p50_sol1 {
	public static void main(String[] args){
		System.out.println(new Pow_p50_sol1().myPow(0, 5));
	}
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        
        //if n is < 0,then we need convert x to 1/x
        if(n < 0){
            n = -n;
            x = 1/x;
        }
        
        //like 2^4 = 2^2 * 2^2
        double half = myPow(x, n/2);
        
        return n%2 == 1? x * half * half : half * half;
    }
    
    public double myPow2(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;
        
        //if n is < 0,then we need convert x to 1/x
        if(n < 0){
            n = -n;
            x = 1/x;
        }
        
        
        return n%2 == 1? x * myPow(x*x, n/2) :myPow(x*x, n/2);
    }
}
