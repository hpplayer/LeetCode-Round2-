import java.util.*;

/**
 * Tree traversal problem.
 * 
 * The tricky part is dealing with nulls
 * 
 * Here I use preorder tree traversal
 * Instead of writing several if statements, we just use a == null || b == null to check null, if one is null, then the other 
 * one must be null in order to get a same tree, so if their null value are not same, we can simply return false, otherwise,
 * we just let loop continue. If both are not null, then we just compare the value, and insert the left and right child.
 * As usual, I use an extra inner class to contain two nodes, otherwise I have to use two stacks
 * 
 * This problem can also be solved with recursion, but it is trivial, so I did not list it here
 * 
 * @author hpPlayer
 * @date Sep 26, 2015 4:07:04 PM
 */
public class Same_Tree_p100_sol1 {
    public class Pair{
        TreeNode a;
        TreeNode b;
        public Pair(TreeNode a, TreeNode b){
            this.a = a;
            this.b = b;
        }
    }
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        Stack<Pair> stack = new Stack<Pair>();
        stack.push(new Pair(p, q));
        
        while(!stack.isEmpty()){
            Pair temp = stack.pop();
            TreeNode a = temp.a;
            TreeNode b = temp.b;
            
            //handle null case
            if(a == null || b == null){
                if(a != b){
                    return false;
                }
                continue;//both null, skip
            }
            
            //now the TreeNode a and b should not be null
            if(a.val != b.val) return false;
            
            stack.push(new Pair(a.left, b.left));
            stack.push(new Pair(a.right, b.right));
        }
        
        return true;
    }
}
