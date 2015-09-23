import java.util.*;

/**
 * Backtrack
 * 
 * recursion is very good at generating combinations.
 * Now we have two items, left "(" and right ")"
 * if remaining left > remaining right, that means we can't build valid () in following process, so we just return
 * if remaining left =0 and remaining right = 0, that means we have done a combination, add it to result
 * Otherwise, we are freely to generate combinations based on remaining left and right.
 * If we have left ( remain, then we can always try to append ( to previous result
 * Also, since we only allow right >= left, we can freely append ) to previous result
 * 
 * 
 * @author hpPlayer
 * @date Sep 23, 2015 12:07:16 AM
 */

public class Generate_Parentheses_p22_sol2 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        DFS(result, "", n, n);
        return result;
    }
    
    public void DFS(List<String> result, String temp, int left, int right){
        if(left > right) return;//we don't have enough ")" remaining, invalid pass
        
        //a new combination
        if(left == 0 && right == 0){
            result.add(temp);
            return;
        }
        
        //if left > 0,  also by previous check, we also have enough right, it means we can freely insert (,
        if(left > 0){
            DFS(result, temp + "(", left - 1, right);
        }
        
        //if we have more ) than (, we can also try to insert ), in addition to above insertion of (
        if(right > left){
            DFS(result, temp + ")", left, right -1);
        }
    }
}
