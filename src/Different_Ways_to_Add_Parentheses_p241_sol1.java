import java.util.*;

/*
Different Ways to Add Parentheses

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
 The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
*/		
		
/**
 * Backtracking solution
 * 
 * The tricky part is to find the split point, which is operator in this problem.
 * 
 * The problem asks us to add parentheses to the given equation in different ways. So when we are looking at each operator, the number before/after it 
 * may change based on different additions of parentheses. Thus, it implies us we can use recursion approach. We will stop at each operator,
 * and do recursion on both side. After done backtracking, we can use operator to calculate different combinations of before and after part.
 * If the input does not have operators, that means it is a pure integer, in such case, we will directly add input into the result, and return
 * to last recursion
 * 
 * @author hpPlayer
 * @date Sep 28, 2015 12:15:09 PM
 */
public class Different_Ways_to_Add_Parentheses_p241_sol1 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<Integer>();
        
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
                //split current input based on the sign
                List<Integer> list1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> list2 = diffWaysToCompute(input.substring(i+1, input.length()));
                
                for(Integer i1: list1){
                    for(Integer i2: list2){
                        int temp = 0;//record temp result
                        switch(input.charAt(i)){
                            case '*':
                                temp = i1 * i2;
                                break;
                            case '+':
                                temp = i1 + i2;
                                break;
                            case '-':
                                temp = i1 - i2;
                                break;
                        }
                        result.add(temp);
                    }
                }
            }
        }
        
        //if current input does not have sign, then it means current input is an integer
        if(result.size()== 0){
            result.add(Integer.valueOf(input));
        }
        
        return result;
    }
}
