import java.util.*;
/**
 * Iterative solution with stack
 * 
 * The difficulty is to only generate valid and non-duplicate pair 
 * 
 * In order to only generate the valid pair of (), we need some controls over insertion of ( and )
 * For (, we can freely insert it, as long as we have it left, i.e. the num of left in current string < n
 * For ), we can only insert it if num of left in current string >= num of right in current string
 * If we have inserted all () and now length of string is 2 * n, then we know we have produced a final combination, just insert it to result
 * 
 * To keep record the num of ) for each sub combination, we need an extra stack. The value in this stack is just the number of ")" in current string
 * 
 * The check condition in this iterative solution is very similar to sol2
 * @author hpPlayer
 * @date Sep 23, 2015 12:35:21 AM
 */

public class Generate_Parentheses_p22_sol3 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if(n == 0) return result;
        
        Stack<String> stack = new Stack<String>();
        Stack<Integer> stack2 = new Stack<Integer>();//num of ) in current string
        
        stack.push("(");
        stack2.push(0);
        
        while(!stack.isEmpty()){
            String currStr = stack.pop();
            int currRight = stack2.pop();
            
            if(currStr.length() == 2*n){//if the length is enough
                result.add(currStr);
                continue;
            }
            
            //if we still have not reach n num of ( 
            if(currStr.length() - currRight < n){
                stack.push(currStr + "(");
                stack2.push(currRight);
            }
            
            //we can only add a ) if we can match it with one of previous )
            //we assume one ( matches one ), so if 2 * num of ) < currStr.length,
            //it means we have unmatched (, thus we can safely add )
            if(2 * currRight < currStr.length()){
                stack.push(currStr + ")");
                stack2.push(currRight + 1);                
            }
            
        }
        
        return result;
    }
}
