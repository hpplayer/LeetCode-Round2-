/*
Valid Number

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous.
You should gather all requirements up front before implementing one.
*/

/**
 * String problem
 * 
 * The tricky part it to handle boundary case properly.
 * We scan the string char by char, each check each of them. This problem is similar to problem String to Integer (atoi) (p8)
 * But in that problem, we just need to check the first consecutive integer part and the main challenge is overflow. Here we have
 * to scan whole string.
 * 
 * To be a valid number, we must have following pattern:
 * 
 * "+/-" + digits + "." + digits + "e" + "+/-" + digits
 * 
 * The "+/-" sign is optional
 * We must at least got one digits, if we have "e", then we need two digits
 * The '." sign is optional, and we only need one digits with ".", either before it or after it
 * 
 * The basic idea is scanning the string based on above pattern. And finally check if whole string meets above pattern.
 * 
 * Remark:
 * Be careful about the "e", we must have digits before and after "e", otherwise it is an invalid number
 * 
 * @author hpPlayer
 * @date Oct 6, 2015 1:57:44 PM
 */

public class Valid_Number_p65_sol1 {
    public boolean isNumber(String s) {
        s = s.trim();//remove heading and trailing spaces
        int i = 0;//pointer used to scan s
        boolean hasDigits = false;//marker that whether we have numbers in string
        int len = s.length();
        
        //remember the pattern: "+/-" + num + "." + num + "e" + "+/-" + num
        
        //check sign
        if(i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            i++;
        }
        
        //check digits
        while(i < len && Character.isDigit(s.charAt(i))){
            hasDigits = true;
            i++;
        }
        
        //check dot
        if(i < len && s.charAt(i) == '.'){
            i++;
        }
        
        //check digits
        while(i < len && Character.isDigit(s.charAt(i))){
            hasDigits = true;
            i++;
        }
        
        //check "e", we must have number before e to make it valid
        if(hasDigits && i < len && s.charAt(i) == 'e'){
            hasDigits = false;//start a new part after e
            i++;
                //we treat number after e as a new non-decimal number, firstly check sign
                if(i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')){
                    i++;
                }             
        }
        
        //check digits
        while(i < len && Character.isDigit(s.charAt(i))){
            hasDigits = true;
            i++;
        }         
        
        //our result must have numbers, if we got 'e' then we also require having numbers after e
        //we also require following above pattern can let us reach the end, otherwise either the pattern is unfollowed
        // or we got extra chars
        return hasDigits && i == len;
   }
}
