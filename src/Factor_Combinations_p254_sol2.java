import java.util.*;
/**
 * Iterative solution
 * 
 * This solution uses the exactly same idea sol1 does, but converting it to iterative version.
 * In recursive, we can modify the temp list then reset it back to make temp list clean to next loop
 * But in iterative solution, we couldn't do that. So we have to copy the temp list twice to make it clean to next loop.
 * Therefore, even though the algorithm is exactly same, the speed of sol2 is slower than sol1
 * 
 * @author hpPlayer
 * @date Oct 14, 2015 1:43:57 PM
 */

public class Factor_Combinations_p254_sol2 {
    private class MyNode{
        int n;
        int start;
        List<Integer> list;
        private MyNode(int n, int start, List<Integer> list){
            this.n = n;
            this.start = start;
            this.list = list;
        }
    }
    
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push( new MyNode(n, 2, new ArrayList<Integer>())  );
        
        while(!stack.isEmpty()){
            MyNode node = stack.pop();
            List<Integer> temp = node.list;
            int start = node.start;
            int newN = node.n;
            
            for(int i = start; i * i <= newN; i++){
                if(newN%i != 0) continue;
                List<Integer> curr = new ArrayList<Integer>(temp);
                curr.add(i);
                curr.add(newN/i);
                result.add(curr);
                
                //we have to fix the list, so we need a new list in each MyNode
                List<Integer> newList = new ArrayList<Integer>(temp);
                newList.add(i);
                stack.push(new MyNode(newN/i, i, newList));
            }
            
        }
        
        return result;
    }
}
