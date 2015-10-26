/*
Binary Tree Maximum Path Sum

Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

/**
 * Post-order traversal problem
 * 
 * 
 * The tricky part is to get clear with the way we calculate the path sum. We don't need to calculate the sum in the rightmost Node. We can just
 * let left and right tree provide the max sum and calculate the path sum at root Node. So this problem is actually a post-order traversal problem
 * 
 * As discussed above, for each subtree, we can calculate the max sum at root Node. But when return to parent Node, we can only take one path
 * from current subtree, either from left or right path. We will pick the path that gives larger sum. The min maxSum at current tree at least can be 0,
 * i.e. we can discard current tree.
 * 
 * Sol1 provides a recursive post-order traversal
 * Sol2 provides an iterative post-order traversal
 * 
 * @author hpPlayer
 * @date Oct 25, 2015 11:25:16 PM
 */
public class Binary_Tree_Maximum_Path_Sum_p124_sol1 {
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        
        DFS(root);
        
        return max;
    }
    
    int max;
    
    public int DFS(TreeNode root){
        //boundary case
        if(root == null) return 0;
        
        //if sum comes from left is < 0, then we can discard it since it will not help us get larger sum
        int left = Math.max(0, DFS(root.left));
        
        //same to right 
        int right = Math.max(0, DFS(root.right));
        
        //calculate the sum from left and right tree at root. If must be the max sum for current subtree
        max = Math.max(max, left + right + root.val);
        
        //return to parent node, we can only take one path, either from left or right path, pick the larger one
        
        return root.val + Math.max(left, right);
    }
}
