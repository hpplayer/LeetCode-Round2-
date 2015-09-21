/**
 * Math + String problem.
 * 
 * Our program should does the same thing as we calculate the product on paper, but we need add several things to make the program work
 * 1) we need an array to record the raw product result, so that we can accumulate the product of each pair of digit:
 *     12
 *   * 34
 *   ------
 *     48
 *    36
 *    3(10)8 <- raw result.
 *  In above example, our raw result would maintain 10, without dealing with carry
 * The length of array should be (len(num1) + len(num2)). like 99 * 100 =  9900 > 99 * 99, the maximum product of 2 digits will be still 
 * smaller than 4 digits. So we can safely build array with above length without overflow.
 * 
 * 2) We observed that the sum of (digit number) can be used to locate the array index. Using above example, index of (10) is 1
 * in array, which is accumulated by the digit pair of (0,1) and (1, 0), the sum of which is 1
 * 
 * 3) To deal with carry more convenient, we want start from tail. Here we use the approach that reverse input string first, then
 * do the calculation. The head of array is actually the product of last digits, so when we build result string, we always insert
 * the next value in front
 * 
 * 4) We observed the maximum num of digits will be no larger than (len(num1) + len(num2)), but it may also be a smaller number
 * like 1 * 999, still return three digits, 0 * 999 even return 0 digits. Those results indicate, we may have zeros in later index of array,
 * which means our digits won't reach that far. So before we return the result, we need scan the starting zeros, and remove them
 * if needed
 * 
 * @author hpPlayer
 * @date Sep 20, 2015 1:54:29 PM
 */
public class Multiply_Strings_p43_sol1 {
    public String multiply(String num1, String num2) {
        //we observe that the digits of final result will not exceed the sum of input digits
        int digits[] = new int[num1.length() + num2.length()];
        
        //reverse to deal with carry easier
        String reverse1 = new StringBuilder(num1).reverse().toString();
        String reverse2 = new StringBuilder(num2).reverse().toString();
        
        for(int i = 0; i < reverse1.length(); i++){
            for(int j = 0; j < reverse2.length(); j++){
                //we found that the sum of digit number can locate the final index of result
                //same sum of digit number will be accumulated in same index of result
                digits[i+j] += (reverse1.charAt(i) - '0') * (reverse2.charAt(j) - '0');
            }
        }
        
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < digits.length; i++){
            int carry = digits[i] / 10;
            int remainder = digits[i] % 10;
            if(i + 1 < digits.length) digits[i+1] += carry; 
            result.insert(0, remainder);
        }
        
        //while we have at leas two digits and start digit is 0
        while(result.charAt(0) == '0' && result.length() > 1) result.deleteCharAt(0);
        
        return result.toString();
    }
}
