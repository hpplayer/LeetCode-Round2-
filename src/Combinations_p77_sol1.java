import java.util.*;

/*
Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/		

/**
 * BFS solution
 * 
 * This solution is very similar to problem Permutations 1(p46) and Permutations2 (p47). Where we use queue or linkedList to add one 
 * value during each big loop. In p46 and p47, our big loop will loop len(nums) times since our final result's length is len(nums). 
 * Similarly, in this solution, we will loop k times, since final result's length is k
 * 
 * In this solution, we will iteratively add number until our sublist's size is k.
 * For the new number, we will only add number after the last number in the list. For the initial case that we don't have number
 * in list, we will add new number from 1 to n. Example: say last number in list is 5, then we will only add number starts from 
 * 6, 7,... otherwise we would get duplicates like [2,3] and [3,2]. We will do this to each sublists until sublist's size reach k
 * 
 * So the main flow:
 * Loop k times, 
 * each loop, we will add a new number to all generated sublists
 * the new number's value should start after the last number in sublists to avoid duplicates
 * Thus the range of new number is from val after last number to n(inclusive)
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 2:03:25 AM
 */

public class Combinations_p77_sol1 {
	public static void main(String[] args){
		for(List<Integer> list : new Combinations_p77_sol1().combine(4, 2)){
			System.out.println(list);
		}
	}
    public List<List<Integer>> combine(int n, int k) {
        //we need declare result to be linkedList, so we can pollFirst()
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        //add initial case, so our combination can start from each possible number
        result.add(new ArrayList<Integer>());
        
        //loop until we got ideal size
        for(int i = 0; i < k; i++){
            //we will add one more value to each previous list
            //like BFS, we need record the size first
            int size = result.size();
            for(int j =0; j < size; j++){
                //poll a previous list from front
                List<Integer> list = result.pollFirst();
                //we need decide where to start, we will always start after the last number in 
                //list to avoid duplicates like [2,3] and [3,2]
                //for initial case, where we don't have last number in list, we will start with 1
                int start = list.size() == 0? 1 : list.get(list.size()-1) + 1;//start is the number we need insert
                //loop, until we build all combinations with numbers after start
                for(; start <= n; start ++){
                    List<Integer> temp = new ArrayList<Integer>(list);
                    temp.add(start);
                    result.add(temp);
                }
            }
        }
        
        return result;
    }
}
