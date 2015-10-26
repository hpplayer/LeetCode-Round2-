import java.util.*;
/**
 * Post-order traversal with HashMap and stack
 * 
 * The algorithm is same with sol1, but I use an iterative way here.
 * For left subtree and right subtree, if the max sum is negative, we can discard it and treat is as 0 sum.
 * When return to parent Node, we have to pick only one path, either from left or right, whichever gives larger sum
 * 
 * Remark:
 * The way I used to traverse the tree is similar to problem Balanced Binary Tree(p110)
 * @author hpPlayer
 * @date Oct 25, 2015 11:22:21 PM
 */

public class Binary_Tree_Maximum_Path_Sum_p124_sol2 {
    public int maxPathSum(TreeNode root) {
        HashMap<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        stack.push(root);
        int result = Integer.MIN_VALUE;
        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            if (( curr.left == null || hs.containsKey(curr.left) ) && (curr.right == null || hs.containsKey(curr.right))){
                    stack.pop();
                    int left = curr.left == null? 0 : Math.max(0, hs.get(curr.left));
                    int right = curr.right == null? 0 : Math.max(0, hs.get(curr.right));
                    
                    result = Math.max(result, left + right + curr.val);
                    hs.put(curr, Math.max(left, right) + curr.val);
                    
            }else{
                if(curr.left != null && !hs.containsKey(curr.left)){
                    stack.push(curr.left);
                }else{
                    stack.push(curr.right);
                    
                }
            }
        }  
        
        return result;
    }
}
