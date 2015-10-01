import java.util.*;
/*
Combination Sum II

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/	
		
/**
 *  Iterative version of DFS
 * 
 * The tricky part is to handle duplicates.
 * 
 * 
 * We couldn't skip duplicates before we create a node for those duplicates. Why? because we may have a solution that 
 * needs all of those duplicates, so at least the node for those duplicates should has the index of first duplicate.
 * Thus, we will always skip duplicates after we push the nodes into the stack. Besides, the only difference with 
 * problem Combination Sum(p39) is each time our inner loop should start from index + 1.
 * 
 * This problem can also be solved with recursion. But it is trivial, so I did not list it here
 * 
 * @author hpPlayer
 * @date Oct 1, 2015 12:53:37 AM
 */
public class Combination_Sum_II_p40_sol1 {
    public class MyNode{
        int index;
        int sum;
        List<Integer> path;
        
        public MyNode(int index, int sum, ArrayList<Integer> path){
            this.index = index;
            this.sum = sum;
            this.path = path;
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(n == 0) return result;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        //push root nodes to the stack
        for(int i = 0; i < n; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(candidates[i]);
            stack.push(new MyNode(i, candidates[i], temp));
            
            //move to the last node in duplicates so next loop will skip it and we won't have duplicate start
            //nodes in stack
            while(i + 1 < n && candidates[i] == candidates[i+1]) i++;
        }
        
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            if(node.sum == target){
                result.add(node.path);
                continue;
            }
            
            for(int i = node.index + 1; i < n; i++){
                //if current val make sum > target, then break and no need to continue
                if(node.sum + candidates[i] > target) break;
                ArrayList<Integer> temp = new ArrayList<Integer>(node.path);
                temp.add(candidates[i]);
                stack.push(new MyNode(i, node.sum + candidates[i], temp));
                
                while(i + 1 < n && candidates[i] == candidates[i+1]) i++;
            }
            
        }
        
        return result;
    }
}
