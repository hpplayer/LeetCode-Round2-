import java.util.*;

/*

Factor Combinations


Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
*/		
		
/**
 * Backtracking solution
 * 
 * The tricky part is don't be confused with the missing boundary track in recursion, also be clear about the non-descending
 * order in recursion, as it will also make sure we don't have duplicates in the result.
 * 
 * The basic idea is simple, we go through all numbers that < n, if the % gives 0, then add it into our result, and do recursion
 * on the n/i.
 * 
 * But there are several things we need to be clear. First in the same loop, to avoid duplicate outputs, we only need to first
 * half of the factors, i.e. stop at factor i where i * i <= n. Say now we have  a * b = n. We know "a" comes from the start case,
 * so we don't want further decompose "a". So we only need to consider "b". but if "b" can be composed of some small value that
 * is < a, then we know such case must already included in previous cases. So to avoid duplicates, we must decompose b starts
 * from a number >= 'a'. We include "a" is because "a" is still under consideration, and we have not covered it in result.
 * To summary, for each input n, we will do loop on a from its start value to a * a <= n, then we do further recursion on the 
 * n/a part, in further recursion, to avoid duplicates, we force those recursions start from a number >= a
 * 
 * Remark:
 * 1. an interesting change in this backtracking solution is that we don't actually add a boundary check in the recursion, or 
 * we don't need to. So don't be stuck in the missing boundary check.
 * 2. Remember to add "start" to avoid duplicate result
 * 
 * Sol1 provides the recursive backtracking solution
 * Sol2 provides the iterative solution with similar idea
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 1:02:59 PM
 */

public class Factor_Combinations_p254_sol1 {
	public static void main(String[] args){
		for(List<Integer> list : new Factor_Combinations_p254_sol1().getFactors(12)){
			System.out.println(list);
		}
		
	}
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        DFS(result, new ArrayList<Integer>(), n, 2);
        return result;
    }
    
    public void DFS(List<List<Integer>> result, List<Integer> temp, int n, int start){
        //we need "start" variable here so we wouldn't start from a small number that has been 
        //analyzed before
        //ex: 12, without "start", we can get either 2 * 2 * 3 or 3 * 2 * 2, and they are duplicates
 
        for(int i = start; i  * i <= n; i++){
            //to avoid duplicates in the same loop, we don't need to go through all i.
            //since 2*3 = 3*2, we just need to go half of factors, and it will cover all cases in same loop
            
            //skip non-factors
            if(n%i != 0) continue;
            
            //found a pair of factor that can compose n, we add them to result
            List<Integer> curr = new ArrayList<Integer>(temp);
            curr.add(i);
            curr.add(n/i);
            result.add(curr);
            
            //now we will do further analysis on n/i.
            //We don't need to do recursion on i, as we have covered all its decomposition case in previous loops
            //For n/i, to avoid including the duplicate case, we will decompose it starts from i, which we havn't
            //covered yet
            temp.add(i);
            DFS(result, temp, n/i, i);
            temp.remove(temp.size()-1);
        }
    }
}
