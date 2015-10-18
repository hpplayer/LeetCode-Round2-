/**
 * In-order traversal solution
 *  
 * The algorithm used here is exactly same with sol1, but now we use recursive in-order traversal instead 
 * 
 * @author hpPlayer
 * @date Oct 18, 2015 2:55:16 PM
 */
public class Recover_Binary_Search_Tree_p99_sol2 {
    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;
    
    public void recoverTree(TreeNode root) {
        inOrder(root);
        
        //recover swapped nodes
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    public void inOrder(TreeNode root){
        if(root == null) return;
        
        inOrder(root.left);
        
        //we have done left tree, now look at curr Node
        if(prev != null && prev.val > root.val){
            //first swapped node must be detected as prev node, think about simple case [0, 1]
            if(first == null) first = prev;
            //second swapped node must be detected as curr node
            if(first != null) second = root;
        }
        
        //update prev and visit right tree
        prev = root;
        
        inOrder(root.right);
    }
}
