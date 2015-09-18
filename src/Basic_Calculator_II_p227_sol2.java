/**
 * This is a tricky version of sol1. We always keep the number before and after the sign
 * Here we use one variable result to store the result calculated so far.
 * To deal with * and / properly, we will simply let prev *(/) curr number
 * To deal with + and / properly, we can safely add prev to result, but we don't know the sign after curr, may be its * or /, so we 
 * still keep curr there
 * 
 * Remark:
 * To avoid unnecessary sign from spaces, we firstly remove all spaces from string by s.trim().replaceAll(" +", ""), where " +" means more than one space
 * 
 * @author hpPlayer
 * @date Sep 17, 2015 8:39:33 PM
 */
public class Basic_Calculator_II_p227_sol2 {
    public int calculate(String s) {
        s = s.trim().replaceAll(" +", "");//remove any seq of spaces
        int prev = 0;//number before sign
        int curr = 0;//number after sign
        int result = 0;
        char sign = '+';
        for(int i = 0; i < s.length(); i++){
            curr = 0;//reset curr before each loop
            while(i < s.length() && Character.isDigit(s.charAt(i))){
                curr = curr * 10 + s.charAt(i) - '0';
                i++;
            }
            
            //now we have found the number (curr) after last sign
            //if sign is +/-, then we can update prev, and set prev = curr
            //if sign is *//, then we have to continue, and include curr to prev
            if(sign == '+'){
                result += prev;
                prev = curr;
            }else if (sign == '-'){
                result += prev;
                prev = -curr;
            }else if(sign == '*'){
                prev *= curr;
            }else if (sign == '/'){
                prev /= curr;
            }
            
            //in case i is out of boundary
            if(i < s.length()) sign = s.charAt(i);
        }

        //we only add 
        if(prev != 0) result += prev;
        
        return result;
    }
}
