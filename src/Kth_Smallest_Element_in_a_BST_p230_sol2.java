import java.util.Stack;
/**
 * Similar to recursive solution, I put it here just want to keep it as practice for iterative in-order traversal
 * The difference is that, we always start count when we pop a node from stack, which means we have done the visit of this node,
 * so we can let count ++ 
 * 
 * @author hpPlayer
 * @date Sep 25, 2015 1:21:59 AM
 */

public class Kth_Smallest_Element_in_a_BST_p230_sol2 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode temp = stack.pop();
                k --;// backtrack, we start count nodes
                if(k == 0) return temp.val;//if found kth node
                curr = temp.right;//otherwise we will visit right tree
            }
        }
        return -1;//if not found
    }
}
