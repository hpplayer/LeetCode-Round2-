import java.util.Stack;

/*
Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23
Note: Do not use the eval built-in library function.
*/

/**
 * Use stack<Integer> to record results. Each time we will search a number and the sign before it.
 * We use +1 and -1 to indicate the + and - sign. 
 * If next sign is still + or -, then we can safely add this number to result with the sign before it, and update the sign accordingly
 * If next sign is (, then we push result and the sign before ( into the stack, so we can use one variable "result" repeatedly, and treat
 * scope among () as a separated scope. So additional, we need reset all parameters to initial state as we just start a new input
 * If next sign is ), then we firstly insert the final pair of number and sign in (), since we always insert when sign is "+" or "-", but now
 * the last sign is ). Then we firstly pop the last sign from stack, which will be attached before the from (), then we pop the result before
 * (), and combine them together.
 * Like I said, we always need to manually check the last pair of sign and number as there is no +/- sign after them. So we do the final insertion.
 * Then return our result
 * 
 * @author hpPlayer
 * @date Sep 17, 2015 8:49:50 PM
 */
public class Basic_Calculator_p224_sol1 {
    public int calculate(String s) {
        int Number = 0;//single number
        int Sign = 1;
        int result = 0;//accumulative result 
        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                Number = Number * 10 + c - '0';
            }else if (c == '+'){
                result += Number*Sign;
                Number = 0;
                Sign = 1;
            }else if(c == '-'){
                result += Number*Sign;
                Number = 0;
                Sign = -1;
            }else if(c == '('){
                stack.push(result);
                stack.push(Sign);//sign before "("
                
                //bascially we reset all parameters, so we start a new session for ()
                Sign = 1;//reset Sign
                Number = 0;//reset Number
                result = 0;//reset result;
                
            }else if(c == ')'){
                //first treat it as +/-
                result += Number * Sign;
                result *= stack.pop();//get sign
                result += stack.pop();//get result
                Number = 0;//reset number
                //no need reset sign since it will be set later 
            }
        }
        
        //we only add number when meet ) + -, if the input is single number or last series of num not ending with ), need add it manually
        if(Number != 0){
            result += Sign*Number;
        }
        return result;
    }
}
