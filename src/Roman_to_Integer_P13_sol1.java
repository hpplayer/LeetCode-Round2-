import java.util.*;
/*
Roman to Integer

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

/**
 * Math problem
 * 
 * The tricky part: 1) must has basic understanding of Roman Integer, like what is M(1000), C(100).. 2)observations
 * This problem is very similar to Integer to Roman(p12)
 * 
 * Let's start this problem with some examples:
 * 1 in Roman integer is I
 * 2 in Roman integer is II
 * 3 in Roman integer is III
 * 4 in Roman integer is IV
 * 5 in Roman integer is V
 * 6 in Roman integer is VI
 * 7 in Roman integer is VII
 * 8 in Roman integer is VIII
 * 9 in Roman integer is IX
 * 10 in Roman integer is X
 * 
 * In p12, we need to treat special case 4 and 9 differently. Similarly here we need also treat them as special case.
 * Except 4s and 9s, We found for all Roman digits, we can just convert them to integers, then adding together.
 * But for 4 and 9, if the first digit is smaller than second digit, then we will use subtraction (subtracting first digit).
 * So our program will do the similar way. We scan the string backward and use an variable "prev" to record the last appeared digit.
 * If current digit < appeared digit, then we will subtract current digit from result, otherwise, we just add current digit.
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 10:59:45 PM
 */
public class Roman_to_Integer_P13_sol1 {
    private static Map<Character, Integer> hs = new HashMap<Character, Integer>(){
    	{put('I', 1); put('V', 5); put('X', 10); put('L', 50); put('C', 100); put('D', 500); put('M', 1000);}
    };
	
    public int romanToInt(String s) {
        int result = 0;
        int prev = 0;
        
        for(int i = s.length() - 1; i >=0; i--){
            int curr = hs.get(s.charAt(i));
            
            result += curr < prev? -curr : curr;
            prev = curr;
        }
        
        return result;
    }
}
