/*
Number of Digit One

Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Hint:

Beware of overflow.
*/

/**
 * Math + divide-and-conquer
 * 
 * The difficulty is to come with the idea of divide-and-conquer and found the trick of calculating 1s from sub part (just calculating 
 * 1s in one sub-part, then times the number of sub-parts we got). I would say the trick used in this problem is very specific, and does 
 * not has a general solution
 * 
 * Lets define the base, the base is number has same length with input, but starts with 1
 * ex1 input: 321, base will be 100. ex2 input 9999, base will be 1000
 * lets define the first digit in input p as FD
 * 
 * Each input n can be divided into two parts
 * numbers < FD * base + numbers > FD * base
 * when calculating the first part, if current FD > 1, then we need to add extra 1s that from the first digit, i.e 1s from FD in base 
 * 
 * Remark:
 * when calculate the first part, it is not necessarily to pass (FD*base-1) to next recursion.
 * We can save a lot of time by just calculating 1s in one range, then times the FD
 * In ex1, we can just calculate 1s from [0-99], then times 3, then we will get 1s in first part
 * In ex2, we can just calculate 1s from [0-999], then times 9, then we will get 1s in first part
 * 
 * 
 * More details:
 * The first part is from 0 to FirstDigit in N* base (exclusive)
 * In ex1, it is [0, 300), in ex2, it is [0, 9000)
 * when we calculate this part, we can just calculate 1s in one range then times the first digit
 * 
 * We also need to calculate extra 1s on first part:
 * numbers that starts with 1
 * In ex1, it is [100, 199], in ex2, it is [1000, 1999]
 * 
 * The second part is the remainder, i.e. n - first part:
 * numbers in the range of [FirstDigit in N* base, n]
 * In ex1, it is [300, 321], in ex2 it is [9000, 9999] 
 * 
 * Sol1 is the recursive solution, where split each input number into two parts 1) >= FD * base 2) < FD * base, and we also need to consider
 * extra 1s from 2)
 * Sol2 is an iterative solution, where we iteratively look the input digit by digit, and calculate the 1s in three cases of current digit
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 1:34:51 PM
 */
public class Number_of_Digit_One_p233_sol1 {
    public int countDigitOne(int n) {
        if(n <= 0) return 0;//no 1s
        if(n == 1) return 1;//input 1 has only one 1
        
        
        //get the base
        int base = 1;
        while(n/base >= 10){
            base *= 10;
        }
        
        int FD = n/base;//get first digit
        
        //our base is calculated based on current length, so we will never let the FD be 0
        int extraOnes = 0;
        if(FD == 1){
            extraOnes = n - base + 1;// + 1 for numbers ending with 0s like 10, 100
        }else if(FD > 1){
            extraOnes = base;//full streak, adding all nunbers start with 1
        }
        
        //1s from [0, FD*base), extra 1s from 1*base, and remainders from [FD*BASE, n] 
        return countDigitOne(base -1)*FD + extraOnes + countDigitOne(n - base*FD);
    }
}
