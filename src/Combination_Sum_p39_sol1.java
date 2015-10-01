import java.util.*;

/*
Combination Sum

Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, ¡­ , ak) must be in non-descending order. (ie, a1 ¡Ü a2 ¡Ü ¡­ ¡Ü ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/	
		
/**
 * Iterative version of DFS!
 * 
 * This problem can also be solved by recursion. But it is trivial, so I did not list it here.
 * 
 * The tricky part is to speed up the code. To save the time in pop() and push() of stack and copy list,
 * we will check new sum before create a new node and push to the stack. It will save us a lot of time!
 * 
 * Like recursion, we need an index to tell use where we can go for next iteration. So our inner class has a variable 
 * record the index. We also need current sum and current path, if we want to check sum and insert into result. So we
 * have path and sum in inner class as well. We firstly push all initial node into the stack. Those initial nodes are 
 * all node contains only one value, i.e. each cell of array. Then, it is very similar to recursion, we check each combination
 * based on the information stored in MyNode
 * 
 * @author hpPlayer
 * @date Oct 1, 2015 12:05:46 AM
 */
public class Combination_Sum_p39_sol1 {
    public class MyNode{
        int sum;
        int index;
        List<Integer> path;
        
        public MyNode(int index, int sum, ArrayList<Integer> path){
            this.index = index;
            this.sum = sum;
            this.path = path;
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(candidates.length == 0) return result;
        
        Arrays.sort(candidates);//sort the array to make us can stop search left candidate if sum > target
        
        Stack<MyNode> stack = new Stack<MyNode>();
        
        //the root node is not one node, but all single values in array
        for(int i = 0; i < candidates.length; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(candidates[i]);
            stack.push(new MyNode(i, candidates[i], temp));
        }
        
        //general DFS visiting after we push paths to the stack
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            if(node.sum > target) continue;//we still need to check here in case the root node already > target
            //found a path meets requirement
            if(node.sum == target){
                result.add(node.path);
                continue;
            } 
            
            for(int i = node.index; i < candidates.length; i++){
                if(candidates[i] + node.sum > target) break;//break the loop if already large
                
                //generate a new path list to avoid mix with later loops
                ArrayList<Integer> temp = new ArrayList<Integer>(node.path);
                temp.add(candidates[i]);
                stack.push(new MyNode(i, node.sum + candidates[i], temp));
            }
        }
        
        return result;
    }
}
