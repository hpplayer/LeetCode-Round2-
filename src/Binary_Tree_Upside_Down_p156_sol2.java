/**
 * 
 * Iterative solution of sol1.
 * 
 * This solution is very similar to iterative reverse of LinkedList, but since now we need assign parent's right child to current node's left,
 * so we need an extra variable
 * 
 * Remark:
 * 
 * A tirck here:
 * when we are assigning variables, we can set the variable that we used to set another variable
 * Example:
 * TreeNode left = curr.left;
 * curr.left = parentRight;
 * 
 * we set curr.left after we just used it to set left
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 1:10:04 AM
 */
public class Binary_Tree_Upside_Down_p156_sol2 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode curr = root, parent = null;//similar to reverse LinkedList
        TreeNode parentRight = null;//we need an extra varaible to set current's left
        
        while(curr != null){
            TreeNode left = curr.left;//next node that we will visit, similar to "next" in reverse LinkedList
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }
        
        return parent;
    }
}
