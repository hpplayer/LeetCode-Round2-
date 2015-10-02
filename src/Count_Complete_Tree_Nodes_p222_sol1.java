/*

Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.
*/

/**
 * 
 * Tree problem
 * 
 * Each time, we will visit the leftmost and rightmost treeNode. If there is at same level, then current tree is a perfect
 * tree, we can simply return the nodes as 2^h - 1. If they are not in same level, then we have no clue where is the imperfect
 * binary tree, so we have to do recursion on left and right subtree respectively.
 * 
 * There has approach2 that looks the leftmost tree of two subtrees. If there at same level, then visit left tree,
 * if not, visit right tree. So here is a trade-off. In approach1, we can detect perfect tree and return immediately. But if it is
 * not perfect tree, then we have to check two trees. While in approach2, we can only detect perfect subtree, so each time we have 
 * at least one subtree with unknown information. For speed, approach2 can be easily written in recursive and iterative solution.
 * While the translation of approach1 to iterative way will get LTE error due to costs on HashMap and Stack.
 * Approach2 can be easily written in iterative way without extra container.
 * 
 * So here, sol1 is recursive solution of approach1
 * Sol2 is the iterative solution of approach2
 * We include both approaches here
 * 
 * Remark:
 * we always use 1<<n to get 2^n, since it is faster
 * 
 * @author hpPlayer
 * @date Oct 2, 2015 12:03:04 AM
 */

public class Count_Complete_Tree_Nodes_p222_sol1 {
	public static void main(String[] args){
		TreeNode root = new TreeNode(0);
		TreeNode left = new TreeNode(0);
		TreeNode right = new TreeNode(0);
		root.left = left;
		root.right = right;
		
		System.out.println(new Count_Complete_Tree_Nodes_p222_sol1().countNodes(root));
	}
	
    public int countNodes(TreeNode root) {
        if(root == null) return 0;//boundary case
        
        TreeNode left = root, right = root;//we will check the height of leftmost and rightmost node
        int height = 0;
        
        while(right != null){
            left = left.left;
            right = right.right;
            height ++;
        }
        //since we count height even we reach null, we can treat it as 1 based, which is perfect for
        //counting the total nodes in tree i.e. 2^h - 1
        if(left == null) return (1<<height) - 1;//perfect tree, return count directly 
        
        //since we only look at the leftmost and rightmost node, we don't where is the last node of leaf node
        //we have to check each tree respectively
        return countNodes(root.left) + 1 + countNodes(root.right);
    }
}
