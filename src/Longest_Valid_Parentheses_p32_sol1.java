import java.util.Stack;

/*

Longest Valid Parentheses

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
*/


/**
 * Stack problem
 * 
 * The tricky part is to find the way to calculate the len of valid parentheses
 * 
 * Since we are looking for the longest parentheses, we have to mark the indexes. So it is better to store index instead of
 * chars in stack, otherwise there is no way that we can tell how long we have gone so far. The stack will only contain index of
 * unpaired parentheses, it may be ')' or '('. If the incoming parenthese can pair the parenthese on top of stack. Then we will
 * just start calculation. we will pop the top parenthese out of the stack and check what is left in stack. If it is an empty stack,
 * then we know we can pair all parentheses from 0 to i, if we still got unpaired parentheses (say top index is j) in stack,
 * then we know at least we can pair all parentheses from j to i. 
 * 
 * @author hpPlayer
 * @date Sep 29, 2015 9:12:27 PM
 */
public class Longest_Valid_Parentheses_p32_sol1 {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        
        for(int i = 0; i < s.length(); i++){
            //if we found a valid pair
            if(s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '('){
                stack.pop();
                if(stack.isEmpty()){
                    //if we can make all Parentheses before i become pairs, then i + 1 is the current max len
                    result = i + 1;
                }else{
                    //some unpaird Parentheses before, at least we can make all Parentheses after peek() to i
                    //become pairs, then i - stack.peek() is currenet len
                    
                    result = Math.max(result, i - stack.peek());
                }
            }else{
                //unpaird Parentheses, it maybe invalid ')' or '(', or maybe valid '(' that waits for pair
                stack.push(i);
            } 
        }
        
        return result;
    }
}
