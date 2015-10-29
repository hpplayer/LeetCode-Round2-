/*
Plus One

Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

/**
 * Math problem
 * 
 * The tricky part is to realize that we can return result in mid if carry = 0
 * 
 * Unlike problem Add Two Numbers (p2), where we have to go through all digits.
 * Here we can just return result if we found carry is 1. Actually we don't need an extra variable for carry at all.
 * if we found current num + 1 < 10, then we add 1 and return result
 * if we found current num + 1 > 10, then we set it to 0, and continue
 * 
 * If we found after go through all digits, we still cannot return result, then we know we have to add 1 in front, so build a new 
 * array and add 1 in front
 * 
 * @author hpPlayer
 * @date Oct 29, 2015 1:32:24 AM
 */
public class Plus_One_p66_sol1 {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i] += 1;
            if(digits[i] < 10) return digits;
            digits[i] = 0;
        }
        
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
