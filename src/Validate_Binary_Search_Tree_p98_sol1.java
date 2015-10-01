/*
Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/

/**
 * 
 * Pre-order traversal !
 * 
 * The tricky part:
 * 1) notice that the all nodes in left subtree must < root and all nodes in right subtree must > root, not just left and right child
 * 2) set the boundary of initial DFS, i.e. what is the initial value for min and max
 * 
 * To avoid the trap in 1), we will set the max and min boundary specifically for each node. 
 * To handle corner case in 2), we will set initial min and max to be null, thus we allow TreeNodes have val like int_min and int max
 * 
 * Sol1 is recursive pre-order traversal
 * Sol2 is iterative in-order traversal
 * 
 * @author hpPlayer
 * @date Oct 1, 2015 1:19:12 AM
 */
public class Validate_Binary_Search_Tree_p98_sol1 {
    public boolean isValidBST(TreeNode root) {
        return DFS(root, null, null);
    }
    
    public boolean DFS(TreeNode root, Integer min, Integer max){
        if(root == null) return true;
        
        //to avoid the mix with real min/max value in treeNode, we use null instead to set the boundary
        if(min != null && root.val <= min) return false;
        
        if(max != null && root.val >= max) return false;
        
        
        return DFS(root.left, min, root.val) && DFS(root.right, root.val, max);
    }
}
