import java.util.*;
/**
 * String problem.
 * We can use backtracking to solve this problem, but it is trivial.
 * 
 * Here is the iterative solution.
 * Each time we will build a new String list, then for each code in current digit, we will combine them with Strings in the previous
 * string list and insert into current String list. In this way, we will generate a new list including current code.
 * Example:
 * Digits: 23, '2' has code [abc], '3' has code [def]
 * Assume now we already got ["a", "b", "c"], then we build a new list [], for each code of 2, we will combine it with existing
 * list, so then we got ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * recursive solution is more like DFS, and iterative solution is more like BFS
 * @author hpPlayer
 * @date Sep 21, 2015 12:10:00 AM
 */

public class Letter_Combinations_of_a_Phone_Number_p17_sol1 {
    String[] hs = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits.length() == 0) return result;
        
        result.add("");
        
        for(int i = 0; i < digits.length(); i++){
            List<String> temp = new ArrayList<String>();
            for(String str : result){
                for(int j = 0; j < hs[digits.charAt(i)-'0'].length(); j++){
                    temp.add(str + hs[digits.charAt(i)-'0'].charAt(j));
                }
            }
            
            result = temp;
        }
        
        return result;
    }
}
