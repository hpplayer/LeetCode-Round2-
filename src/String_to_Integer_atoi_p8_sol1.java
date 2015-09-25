/*
String to Integer (atoi)


Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.


spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number,
or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

*/

/**
 * String problem and maybe need some observations
 * 
 * First, let me explain what the problem want us to do. The chars in given string are unknown. The problem wants us
 * to catch the first integer that does not contain non-digit char inside it, and the char before it can only be "+" or
 * "-" or " +". The searching of integer stops when we found a non-digit char or we reach the tail
 * 
 * The difficulty is dealing with overflow
 * 
 * We can use "long" to catch the overflow, but it is unnecessary nor efficient, as we may waste time to get redundant
 * chars. We can actually use the trick in Reverse Integer(p7) to check overflow. The trick is comparing current result
 * with int_max/10, if this first part already larger than int_max/10 and we still digits left, then we will definitely 
 * get overflow. If current result == int_max/10, then we check the digit that needs to be insert. The last digit of 
 * int_max is 7, so if current digit > 7, we will get overflow. the last digit of int_min is 8, so if current digit > 8
 * we will get overflow as well, but if digit = 8, we already reach the limit of int_min, thus we can also return it. 
 * 
 * After overflow check, if we are still in loop, then we can safely append new digit and update the result
 * 
 * Remark:
 * trim() and Character.isDigit() are really useful in string manipulation
 * @author hpPlayer
 * @date Sep 24, 2015 11:37:16 PM
 */
public class String_to_Integer_atoi_p8_sol1 {
	public static void main(String[] args){
		System.out.println(new String_to_Integer_atoi_p8_sol1().myAtoi("+"));
	}
    public int myAtoi(String str) {
        str = str.trim();
        if(str.length() == 0) return 0;//if we don't have digits left
        
        int i = 0;//pointer in string 
        
        int sign = 1;
        
        //dealing with optional sign
        if(str.charAt(i) == '+'){
            i++;
        }else if(str.charAt(i) == '-'){
            i++;
            sign = -1;
        }
        
        int result = 0;
        
        //we only look for the first consective intergers 
        while(i < str.length() && Character.isDigit(str.charAt(i))){
            char c = str.charAt(i);
            //if we got overflow or reach the int_min.
            //if previous part are same, and the last digit is 8, 9, then we will get overflow for +int
            //for -int, although 8 will not cost overflow but since we have already reach it, we can just return 
            //as well
            if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && c - '0' >= 8) ){
                return sign == -1? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + c - '0';
            i++;
        }
        
        return sign * result;
    }
}
