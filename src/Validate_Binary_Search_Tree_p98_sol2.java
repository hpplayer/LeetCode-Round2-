import java.util.Stack;

/**
 * In-order tree traversal
 * 
 * The tricky part is to find the relation between prev and curr node. In BST, we always have prev node < curr Node
 * 
 * The solution here is basically a general in-order traversal, where we iteratively visit left path, if reach tail, then 
 * pop node and try to visit this node's right tree. So our prev node is always the node before pop, i.e. the node above in the stack
 *   b
 *  /
 * a
 * this case a is node before b pop, so a is prev node of b in in-order traversal
 * 
 * a
 *  \ 
 *   b
 * this case a is the node before b pop,  so a is prev node of b in in-order traversal
 * 
 * We don't allow prev.val >= curr.val. We traverse all tree, and check each pair of prev and curr, then return result accordingly
 * 
 * @author hpPlayer
 * @date Oct 1, 2015 1:30:23 AM
 */
public class Validate_Binary_Search_Tree_p98_sol2 {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        TreeNode prev = null;//prev node in in-order traversal
        
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                 curr = stack.pop();//done this node and left subtree, visit its right subtree 
                 if(prev != null){
                     //in in-order trarversal, our prev node should always < current Node
                     if(prev.val >= curr.val) return false;
                 }
                 
                 prev = curr;
                 curr = curr.right;
            }
        }
        
        return true;
    }
}
