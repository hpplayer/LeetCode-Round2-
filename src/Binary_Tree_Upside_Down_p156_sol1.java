/*
Binary Tree Upside Down

Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/

/**
 * Recursive reverse Tree
 * 
 * Similar to reverse LinkedList, but here we need to reverse nodes in left path AND append curr.right to left child's left
 * 
 * So the main reverse program is very similar to reverse LinkedList, but here since we also need append parent's right child
 * to next recursion, so to be convenient here we use three variables. 
 * 
 * Sol1 is recursive solution
 * Sol2 is iterative version of sol1
 * 
 * @author hpPlayer
 * @date Oct 5, 2015 1:05:03 AM
 */
public class Binary_Tree_Upside_Down_p156_sol1 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return reverse(root, null, null);
    }
    
    //we will reverse nodes on left path, and append parent's right node to left child's left
    public TreeNode reverse(TreeNode curr, TreeNode parent, TreeNode ParentRight){
        if(curr == null) return parent;//parent is new root
        
        TreeNode newRoot = reverse(curr.left, curr, curr.right);
        
        //reverse nodes in left path
        curr.right = parent;
        
        //append parent's right node to left child's left it we have parent (for old root, we don't have parent)
        curr.left = ParentRight;
        
        return newRoot;
    }
}
