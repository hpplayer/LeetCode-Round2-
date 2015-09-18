import java.util.Stack;

/**
 * Use stack<Integer> to record results. Each time we will search two things number or the sign before number
 * 
 * If the sign before current number is + or -, then we have to push current value to stack, since we don't
 * know if current value will be firstly used by later operators like * or /. However, if the sign before number is * or /, then we can pop 
 * previous number from stack and do the operations. So, basically, we will push numbers into stack if sign before is * or /, and we will
 * poll previous number from stack and update it then push it again into the stack if sign before is + or -
 * 
 * After we scan the whole string, we will annually push the last number into the stack. Then we just need to pop numbers from stack and add them
 * together.
 * 
 * Remark:
 * For sign "-", we can simply let the number be -number, thus later we can treat them as positive number
 * 
 * @author hpPlayer
 * @date Sep 17, 2015 8:32:14 PM
 */

public class Basic_Calculator_II_p227_sol1 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        char sign = '+';//sign before num
        int num = 0;//num after sign
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + c - '0';
            }else if (c == '+' || c == '-' || c == '*' || c == '/'){
                updateStack(stack, num, sign);
                num = 0;
                sign = c;
            }
        }
        //last number and the sign before it
        updateStack(stack, num, sign);
        int result = 0;
        while(!stack.isEmpty()){
            result += stack.pop();
        }
        
        return result;
    }
    
    public void updateStack(Stack<Integer> stack, int num, char sign){
        if(sign == '*'){
            stack.push(stack.pop() * num);
        }else if (sign == '/'){
            stack.push(stack.pop()/ num);
        }else if (sign == '+'){
            stack.push(num);
        }else{
            stack.push(-num);
        }
    }
}
