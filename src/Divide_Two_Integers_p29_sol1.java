/*
Divide Two Integers

Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
*/		
		
/**	
 * Math and bit manipulation problem.
 * 
 * The tricky part is be careful with overflow and come up with the idea of divide number based on 2 hex.
 * 
 * We are not allowed to use *, /, % to calculate the result, it implies that we couldn't do it in decimal, so instead, we can try
 * binary. The result of division just tells us how many divisors can compose a dividend. Thus if we manually use << to put divisors together,
 * then we know the use of << will increase dividend by 2 times each time, it is like we will use 2, 4, 8,...2n times of divisor.
 * So we can accumulate times of divisor in result. Differed from general division, here each time we will try to increase the divisor by 2 times
 * by contrast, in general division, we can increase the divisor by 1 - 9 times. So each time we will get a digit(or number) that corresponds
 * to a 2n times of divisor, and will redo this until finally we can compose a max num smaller than dividend
 * 
 * Remark:
 * To avoid overflow, we will declare all variables (except result) to be long type
 * 
 * @author hpPlayer
 * @date Sep 29, 2015 1:03:46 PM
 */
public class Divide_Two_Integers_p29_sol1 {
	public static void main(String[] args){
		System.out.println(new Divide_Two_Integers_p29_sol1().divide(-1010369383, -2147483648));
	}
    public int divide(int dividend, int divisor) {
        //for two int inputs, we will only have 2 cases that cause overflow
        //1) divisor = 0    2)dividend is int_min, but divisor = -1
        if(divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        
        //use XOR to get sign value. If both neg or pos, neg will be false, otherwise it will be true
        boolean neg = (dividend < 0) ^ (divisor <0);
        
        //to be clear with calculation, we will convert inputs to pos, but the built-in abs() will output the 
        //same data type as the input, so, to avoid overflow, we will case input to long
        long abs_dividend = Math.abs( (long) dividend);
        long abs_divisor = Math.abs( (long) divisor);
        int result = 0;
        
        //Differed from general division, here each time we will try to increase the divisor by 2 times
        //where in general division, we can increase the divisor by 1 - 9 times. Then we will decrease 
        //the dividend, and redo the operations
        while(abs_dividend >= abs_divisor){
            //temp is the temp result from increase the divisor by 2 times
            long temp = abs_divisor, multiply = 1;
            //we will try to get the max value smaller than abs_dividend by increasing temp 2 times each time
            while(abs_dividend >= (temp <<1)){
                temp <<= 1;
                multiply <<= 1;
            }
            
            abs_dividend -= temp;
            result += multiply;
        }
        
        return neg? -result : result;
    }
}
