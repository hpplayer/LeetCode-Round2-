import java.util.*;

/*
Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add operators +, -, or * between the digits
so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
*/	

/**
 * Backtracking solution
 * 
 * The algorithm itself is not hard, we split current string into two parts and always do recursion on right part. There are some details that needs
 * to be careful.
 * 1) overflow, since we have "-" sign, when calculating the temp sum, we allow current sum is larger than target. This implies the 
 * temp sum may be very large and cause overflow problem.
 * 2) signs, since we have "*", which means current num should be calculated with the number already append to temp result.
 * To handle such case, we always remember the last number we append to result. So if we need use "*", then we just subtract last number from
 * temp result, multiply it then append to temp result again. For "-" case, we can just treat it as a special case of "+" i.e. we add a negative number + "-n"!
 * 3) 0s. The only valid number starts with 0 is 0 self, other number with any length would be invalid. Think about "01", "030", etc. are all invalid.
 * There are many ways to check heading 0 cases. But the best way is simply check if head is 0, then return result after the temp number has more than 1 length.
 * 4) appending signs. In our DFS, we always consider split right part, and add a sign before it
 * So to cover the corner case, the head number, which does not need signs before it, we put head number in an extra loop in the main program,
 * and the DFS() just work on the following part
 * 
 * @author hpPlayer
 * @date Oct 8, 2015 5:43:37 PM
 */

public class Expression_Add_Operators_p282_sol1 {
	public static void main(String[] args){
		//System.out.println(" ".length());
		System.out.println(new Expression_Add_Operators_p282_sol1().addOperators("000", 0));
	}
	
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<String>();
        if(num == null || num.length() == 0 ) return result;
        
      //the split operator is from 1 to tail
        for(int i = 1; i <= num.length(); i++){
            //we can't have a valid number starts with 0 and longer than 1
            if(num.charAt(0) == '0' && i > 1) break; 
            
            long left = Long.valueOf(num.substring(0, i));
            DFS(left + "", num.substring(i), left, target, left, result);
        }
        
        return result;
    }
    
    //we may got overflow problem, so define sum as long
    //left is our temp result, right is remaining part
    //sum is temp sum, lastused is the last number appended to temp result
    public void DFS (String left, String right, long sum, int target, long lastUsed, List<String> result){
        //if we have used all digits and got target number
        if(target == sum && right.length() == 0){
            result.add(left);
            return;
        }
        
        //do recursion on right part
      //the split operator is from 1 to tail
        for(int i = 1; i <= right.length(); i++){
            //we can't have a valid number starts with 0 and longer than 1
            if(right.charAt(0) == '0' && i > 1) return; 
            long right1 = Long.valueOf(right.substring(0, i));
            String right2 = right.substring(i);
            
            //  + 
            long newSum = sum + right1;
            DFS(left + "+" + right1, right2, newSum, target, right1, result);
            
            // -, right > 0, so we can treat it as + (-right)
            newSum = sum - right1;
            DFS(left + "-" + right1, right2, newSum, target, -right1, result);
            
            //*
            newSum = sum - lastUsed + (lastUsed * right1);
            DFS(left + "*" + right1, right2, newSum, target, lastUsed * right1, result);
        }
    }
}
