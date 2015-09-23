import java.util.*;

/**
 * DP solution.
 * 
 * This problem can be solved by a bunch of solutions, backtrack, iterative counting.
 * But I like this DP solution as it is very amazing.
 * 
 * In this approach, we always insert the new pair of () in front, then we can insert old pairs of () in two positions, inside new pair, or behind 
 * the new pair. If now we are looking at ith iteration, we firstly put ith () in front, then we have to insert i-1 pairs to compose new combinations.
 * So we will look at previous results from 0 to i-1, and looking for two results that their sum of pairs will be be i -1. So the final series of this
 * combination would start from () + f(i-1) to (f(i-1)) + "", where f() is the result of previous result
 * 
 * Sol2 is recursive backtracking solution with simple logic
 * Sol3 is an iterative solution with observations from sol2
 * @author hpPlayer
 * @date Sep 22, 2015 11:47:57 PM
 */

public class Generate_Parentheses_p22_sol1 {
    public List<String> generateParenthesis(int n) {
        List<List<String>> dp = new ArrayList<List<String>>();//including 0
        dp.add(Arrays.asList(""));//inital case
        
        for(int i = 1; i < n + 1; i++){
            List<String> temp = new ArrayList<String>();
            
            //scanning all previous combinations < n
            for(int j = 0; j < i; j++){
                for(String left : dp.get(j)){
                    for(String right : dp.get(i-1-j)){
                        temp.add("(" + left + ")" + right);       
                    }
                }
            }
            dp.add(temp);   
        }
        
        return dp.get(n);
    }
}
