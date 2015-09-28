import java.util.*;

/*
Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
*/

/**
 * Postorder traversal
 * 
 * Tricky part: get the idea of creating hashMap to store the depth from subtree
 * This problem can be solved by recursive solution, where we count the depth of left/right subtree and using a global boolean variable to record
 * result, but it is trivial so I did not list it here
 * 
 * We will visit each node of the tree. For each node, we will visit its left and right subtree and store the depth in the HashMap. If the depth 
 * is differed by more than 1, we will return false immediately. The hashmap is very important to us in this solution. It will tell us whether
 * we have visited left/right subtree of current node. If we don't have subtree, then we will assign its tree depth to be 0, if we have subtree
 * and we have visited it before then we will retrieve the depth from hashMap. To follow the order of post-order traversal, we always firstly 
 * push left child to stack, after done left tree, then push the right child to stack. Due to the use of map, our post-order traversal here is
 * differed from the post-oder traversal problem (p145) where we use "prev" and "curr" pointer and decide which part in post-order by comparing the 
 * order of prev and curr.
 * 
 * I put this solution here as a pratice of post-order tree traversal
 * 
 * @author hpPlayer
 * @date Sep 27, 2015 11:43:04 PM
 */
public class Balanced_Binary_Tree_p110_sol1 {
    public boolean isBalanced(TreeNode root) {
        //boundary case
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        //key: node, value: max height
        //since we use HashMap, our traversal of tree can be differed from previous postorder traversal
        HashMap<TreeNode, Integer> hs = new HashMap<TreeNode, Integer>();
        
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            TreeNode left = node.left;
            TreeNode right = node.right;
            
            //if we have done left/right subtree and stored node in map, or we may not have left/right subtree
            if((left == null || hs.containsKey(left)) &&  (right == null || hs.containsKey(right))){
                int l = left == null? 0 : hs.get(left);
                int r = right == null? 0 : hs.get(right);
                
                //if differed by more than 1
                if(Math.abs(l-r) > 1) return false;
                
                stack.pop();//done this node and its subtree, pop it out
                hs.put(node, Math.max(l, r) + 1);
            }else{
                //we come here to search left and right subtree
                
                //if we have left tree and has not visited it before, then go visit it 
                if(left != null && !hs.containsKey(left)){
                    stack.push(left);
                }else{
                    //else, we come here to search right tree
                    stack.push(right);
                }
            }
        }
        
        return true;
    }
}
