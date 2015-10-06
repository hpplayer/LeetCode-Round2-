import java.util.*;
/*
Permutations

Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

/**
 * BFS solution
 * 
 * The tricky part is that we need to be familiar with LinkedList. If we declare a LinekdList, then we can poll value from front or tail
 * like a Deque. This problem could also be solved by backtracking with a boolean matrix. But it is trivial, so I did not list it here
 * 
 * The main idea is to insert a num each time, the positions to be inserted range from head to tail.
 * We firstly begin with empty list, then we insert the first num, now our result get a list with size 1
 * then we insert the second num, we can insert it in head or tail
 * Then we insert the third num, we can insert in head, mid or tail.
 * We will repeat above steps until all nums are inserted.
 * 
 * Remark:
 * problem Permutations II(p47) and problem Combinations (p77) use the similar BFS solution, check it out!
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 7:46:57 PM
 */


public class Permutations_p46_sol1 {
    public List<List<Integer>> permute(int[] nums) {
        //to allow us pop value from front, we need to declare list as linkedlist type
        LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
        //add empty list to as initial case
        result.add(new ArrayList<Integer>());
        
        //for each num, we will insert it into every position of exisiting lists in result
        //like [1,2], we firstly insert 1, then we will insert 2 before it and after it and get [1,2], [2,1]
        for(int num : nums){
            int size = result.size();//like BFS, we will change result in each loop, thus record size first
            
            for(int i = 0; i < size; i++){
                List<Integer> lst = result.pollFirst();//get and remove top list
                for(int j = 0; j <= lst.size(); j++){
                    //we will insert num into each position of temp, from 0(front) - size (tail)
                    List<Integer> temp = new ArrayList<Integer>(lst);
                    temp.add(j, num);
                    result.add(temp);
                }
                
            }
        }
        
        return result;
    }
}
